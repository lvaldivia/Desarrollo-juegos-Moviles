package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by pcsilval on 15/10/2016.
 */
public abstract class State {

    protected OrthographicCamera camera;
    protected Vector2 position;
    public State(){
        camera = new OrthographicCamera();
        position = new Vector2();
    }
    protected abstract void handleInput();
    protected abstract void render(SpriteBatch sp);
    protected abstract void update(float dt);
    protected abstract void dispose();

}
