package com.upc.desarrollo.ui;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.upc.desarrollo.Config;

/**
 * Created by Luis on 04/11/2016.
 */

public class Hud implements Disposable {
    private Viewport viewport;
    private Stage stage;
    private SpriteBatch sp;
    private Label countdownLabel;
    private Label marioLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label scoreLabel;
    private int worldTimer;
    private int score;
    private float timeCount;

    public Hud(SpriteBatch sp){
        this.sp = sp;
        worldTimer = 300;
        timeCount = 0;
        score = 0;
        viewport = new FitViewport(Config.GAME_WIDTH,Config.GAME_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport,sp);
        countdownLabel = new Label(String.format("%03d",worldTimer),
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d",score),
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        marioLabel = new Label("MARIO",
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("WORLD",
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("1-1",
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME",
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Table table = new Table();
        table.setFillParent(true);
        table.top();
        table.add(marioLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();
        stage.addActor(table);
    }

    public void update(float delta){

    }


    public Camera getCamera(){
        return stage.getCamera();
    }

    public void draw(){
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
