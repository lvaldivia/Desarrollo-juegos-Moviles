package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.Menu;
import com.mygdx.game.states.StateManager;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Gdx.gl.glClearColor(1, 0, 0, 1);
        StateManager.init();
        StateManager.set(new Menu());
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        StateManager.render(batch);
        StateManager.update(
                Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
