package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Config;
import com.mygdx.game.objects.Bird;
import com.mygdx.game.objects.Pipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcsilval on 15/10/2016.
 */
public class Game extends State {
    private Texture bg;
    private Bird bird;
    private List<Pipe> pipes;
    private final static int  PIPE_SPACING = 125;
    public Game(){
        super();
        bg = new Texture("bg.png");
        bird = new Bird(0,300);
        camera.setToOrtho(false,Config.GAME_WIDTH/2,
                Config.GAME_HEIGHT/2);
        pipes = new ArrayList<Pipe>();
        for (int i = 1; i <=4; i++) {
            pipes.add(new Pipe(i*(PIPE_SPACING + Pipe.PIPE_WIDTH)));
        }
    }
    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    protected void render(SpriteBatch sp) {
        sp.setProjectionMatrix(camera.combined);
        sp.begin();
        sp.draw(bg, camera.position.x - (camera.viewportWidth*0.5f),0);
        bird.render(sp);
        for (Pipe pipe: pipes) {
            pipe.render(sp);
        }
        sp.end();
    }

    @Override
    protected void update(float dt) {
        bird.update(dt);
        camera.position.x = bird.getPosition().x;
        camera.update();
        handleInput();
    }

    @Override
    protected void dispose() {

    }
}
