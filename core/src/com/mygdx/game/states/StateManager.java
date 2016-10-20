package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by pcsilval on 15/10/2016.
 */
public class StateManager {
    public static Stack<State> states;
    public static void init(){
        states = new Stack<State>();
    }

    public static void push(State state){
        states.add(state);
    }

    public static void pop(){
        states.pop();
    }

    public static void set(State state){
        if(states.size()>0){
            states.pop().dispose();
        }
        states.add(state);
    }

    public static void update(float dt){
        states.peek().update(dt);
    }

    public static void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
