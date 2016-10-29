package com.upc.desarrollo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.upc.desarrollo.screens.PlayScreen;

public class Games extends Game {

	private SpriteBatch spriteBatch;
	@Override
	public void create () {

		spriteBatch = new SpriteBatch();
		setScreen(new PlayScreen(spriteBatch));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		spriteBatch.dispose();
	}
}
