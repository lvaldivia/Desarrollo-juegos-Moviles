package com.upc.desarrollo.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.upc.desarrollo.Config;

/**
 * Created by Luis on 26/10/2016.
 */

public class ElementCreator {
    private final int GROUND = 2;
    private final int PIPE = 3;
    private final int COIN = 4;
    private final int BRICK = 5;

    public enum Layers {
        GROUND(2, "GROUND"),
        PIPE(3, "PIPE"),
        COIN(4, "COIN"),
        BRICK(5, "BRICK");

        private String stringValue;
        private int intValue;

        private Layers(int intValue, String stringValue) {
            this.stringValue = stringValue;
            this.intValue = intValue;
        }
    }

    public ElementCreator(World world, TiledMap map) {
        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        for (Layers layer : Layers.values()) {
            for (MapObject mapObject :
                    map.getLayers().get(layer.intValue).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rect = ((RectangleMapObject) mapObject).getRectangle();
                bodyDef.type = BodyDef.BodyType.StaticBody;
                float positionX = (rect.getWidth() / 2 + rect.getX())/ Config.PPM;
                float positionY = (rect.getHeight()/2+rect.getY())/Config.PPM;
                bodyDef.position.set(positionX,
                        positionY);
                float width = rect.getWidth()/2/Config.PPM;
                float height = rect.getHeight()/2/Config.PPM;
                shape.setAsBox(width,height);
                body = world.createBody(bodyDef);
                fixtureDef.shape = shape;
                body.createFixture(fixtureDef);
            }
        }
    }
}
