package com.upc.desarrollo.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.upc.desarrollo.Config;
import com.upc.desarrollo.objects.InteractiveTiledObject;
import com.upc.desarrollo.objects.Mario;
import com.upc.desarrollo.objects.Mushroom;

/**
 * Created by Luis on 04/11/2016.
 */

public class WorldContactListener implements ContactListener {


    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        int cdef = fixtureA.getFilterData().categoryBits | fixtureB.getFilterData().categoryBits;

        switch (cdef){
            case Config.MARIO_HEAD_BIT | Config.BRICK_BIT:
            case Config.MARIO_HEAD_BIT | Config.COIN_BIT:
                if(fixtureA.getFilterData().categoryBits == Config.MARIO_HEAD_BIT){
                    ((InteractiveTiledObject)fixtureB.getUserData()).onHeadHit();
                }else{
                    ((InteractiveTiledObject)fixtureA.getUserData()).onHeadHit();
                }
                break;


            case Config.ITEM_BIT | Config.MARIO_BIT:
                if(fixtureA.getFilterData().categoryBits == Config.ITEM_BIT){
                    ((Mushroom)fixtureA.getUserData()).collect((Mario) fixtureB.getUserData());
                }else {
                    ((Mushroom)fixtureB.getUserData()).collect((Mario)fixtureA.getUserData());
                }
                break;

        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
