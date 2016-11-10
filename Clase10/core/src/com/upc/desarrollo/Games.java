package com.upc.desarrollo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.upc.desarrollo.screens.GameOver;
import com.upc.desarrollo.screens.PlayScreen;

public class Games extends Game {

	private SpriteBatch spriteBatch;
	public static Games instance;
	@Override
	public void create () {
        CustomAssetManager.init();
        instance = this;
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
        CustomAssetManager.dispose();
	}
}
