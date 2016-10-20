package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Luis on 19/10/2016.
 */

public class Pipe {
    private Texture top;
    private Texture bottom;
    public static  final int PIPE_WIDTH = 52;
    private static  final int PIPE_GAP = 100;
    private static  final int FLUCTUATION = 130;
    private static  final int LOWEST_OPENING = 120;
    private Random random;
    private Vector2 topPosition, bottomPosition;

    public Pipe(float x){
        top = new Texture("toptube.png");
        bottom = new Texture("bottomtube.png");
        random = new Random();
        topPosition = new Vector2(
            x,random.nextInt(FLUCTUATION) +PIPE_GAP + LOWEST_OPENING
        );
        bottomPosition = new Vector2(
                x,topPosition.y-PIPE_GAP-bottom.getHeight()
        );
    }

    public void render(SpriteBatch sp){
        sp.draw(bottom,bottomPosition.x,bottomPosition.y);
        sp.draw(top,topPosition.x,topPosition.y);
    }

    public void reposition(float x){

    }
}
