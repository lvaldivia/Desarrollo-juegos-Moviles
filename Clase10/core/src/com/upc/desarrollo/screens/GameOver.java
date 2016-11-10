package com.upc.desarrollo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.upc.desarrollo.Config;
import com.upc.desarrollo.Games;

import utils.states.State;

/**
 * Created by Luis on 09/11/2016.
 */

public class GameOver extends State {
    private Stage stage;
    public GameOver(SpriteBatch spriteBatch) {
        super(spriteBatch);
        viewport = new FitViewport(Config.GAME_WIDTH,Config.GAME_HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport,spriteBatch);
        Table table = new Table();
        table.center();
        table.setFillParent(true);
        Label gameOverLabel = new Label("Game Over",
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        gameOverLabel.setAlignment(Align.center);
        table.add(gameOverLabel).expandX();
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.draw();
    }

    @Override
    public void handleInput(float dt) {
        super.handleInput(dt);
        if(Gdx.input.justTouched()){
            Games.instance.setScreen(new PlayScreen(spriteBatch));
            dispose();
        }
    }

    @Override
    public void dispose() {
        //super.dispose();
        //stage.dispose();
    }
}
