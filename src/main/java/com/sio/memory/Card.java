package com.sio.memory;

import java.io.InputStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card extends ImageView {
	private int numero;
	private boolean hidden = true;

	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public Card(int numero) {
		this.numero = numero;

		setFitWidth(70);
		setFitHeight(70);
				
		this.hideCard();
	}
 
	public boolean isHidden() {
		return hidden;
	}
	
	public void showCard() {
		hidden = false;
		
		InputStream input = Card.class.getResourceAsStream("/images/card" + numero + ".png");
		Image image = new Image(input);
		setImage(image);
	}
		
	public void hideCard() {
		hidden = true;
		
		InputStream input = Card.class.getResourceAsStream("/images/card0.png");
		Image image = new Image(input);
		setImage(image);
	}
}

