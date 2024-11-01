package com.sio.memory;

import com.sio.memory.Card;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class PlateauController implements Initializable {

	@FXML
	private Label labelNbCoups;
	@FXML
	private GridPane grilleJeu;

	private PauseTransition delay;
	private ArrayList<Card> playedCards;
	
	private final int nbCasesParCote = 4;
	private int nbCoups = 0;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		delay = new PauseTransition(Duration.seconds(1));
		playedCards = new ArrayList<>();
		initPlateau();
	}
	
	@FXML
	public void restart() {
		initPlateau();
	}

	// initialisation du plateau de jeu
	private void initPlateau() {
		int nbCartes = nbCasesParCote * nbCasesParCote;

		delay.stop();
		playedCards.clear();
		grilleJeu.getChildren().clear();

		nbCoups = 0;
		updateLabelCoups();

		ArrayList<Card> listeRandomisedCards = getRandomisedCards(nbCartes / 2);

		for (int numLigne = 0; numLigne < nbCasesParCote; numLigne++) {
			for (int numColonne = 0; numColonne < nbCasesParCote; numColonne++) {
				Card carte = listeRandomisedCards.remove(0);

				// création de l'évènement
				EventHandler<MouseEvent> eventHandler = new EventHandler<>() {
					@Override
					public void handle(MouseEvent e) {
						launchClick(e);
					}
				};
				// ajout de l'évènement sur la carte
				carte.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
				// ajout de la carte au GridPane
				grilleJeu.add(carte, numColonne, numLigne);
			}
		}
	}

	// création d'une liste contenant des paires de cartes dans un ordre aléatoire
	private ArrayList<Card> getRandomisedCards(int maxCardNumber) {
		ArrayList<Card> listeRandomisedCards = new ArrayList<>();

		for (int cptCard = 1; cptCard <= maxCardNumber; cptCard++) {
			// on ajoute deux cartes ayant le même numéro dans la liste
			listeRandomisedCards.add(new Card(cptCard));
			listeRandomisedCards.add(new Card(cptCard));
		}

		// on mélange la liste
		Collections.shuffle(listeRandomisedCards);

		return listeRandomisedCards;
	}

	// mise à jour du label contenant le nombre de coups
	private void updateLabelCoups() {
		if (nbCoups < 10) {
			labelNbCoups.setText(String.valueOf("0" + nbCoups));
		}
		else {
			labelNbCoups.setText(String.valueOf(nbCoups));
		}
	}
	
	// méthode appelée lors du clic sur une carte
	private void launchClick(MouseEvent event) {
		Card clickedCard = (Card) event.getSource();

		if (clickedCard.isHidden()) {
			if (playedCards.isEmpty()) {
				playedCards.add(clickedCard);
				clickedCard.showCard();
			}
			else if (playedCards.size() == 1) {
				playedCards.add(clickedCard);
				clickedCard.showCard();
				
				if (playedCards.get(0).getNumero() != clickedCard.getNumero()) {
					delay.setOnFinished((ActionEvent t) -> {
						playedCards.get(0).hideCard();
						clickedCard.hideCard();
						playedCards.clear();
					});
					delay.play();
				} else {
					playedCards.clear();
				}
				nbCoups++;
				updateLabelCoups();
			}
		}
	}
}
