package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Config;

/**
 * Created by pcsilval on 15/10/2016.
 */
public class Menu extends State {

    private Texture background;
    private Texture startBtn;
    private Vector2 position;
    public Menu(){
        super();
        background = new Texture("bg.png");
        startBtn = new Texture("playbtn.png");
        position = new Vector2(
                Config.GAME_WIDTH*0.5f -
                        (startBtn.getWidth()*0.5f)
                ,Config.GAME_HEIGHT*0.5f -
                (startBtn.getHeight()*0.5f)
        );
    }

    @Override
    protected void render(SpriteBatch sp) {
        sp.begin();
        sp.draw(background, 0,0,Config.GAME_WIDTH,Config.GAME_HEIGHT);
        sp.draw(startBtn,position.x,position.y);
        sp.end();
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            StateManager.set(new Game());
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
    }

    @Override
    protected void dispose() {
        background.dispose();
        startBtn.dispose();
    }
}
