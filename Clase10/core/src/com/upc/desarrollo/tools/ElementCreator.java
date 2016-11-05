package com.upc.desarrollo.tools;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
import com.upc.desarrollo.objects.Brick;
import com.upc.desarrollo.objects.Coin;


/**
 * Created by Luis on 04/11/2016.
 */

public class ElementCreator {

    public ElementCreator(World world, TiledMap map, TextureAtlas atlas){
        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        for (MapObject mapObject: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)mapObject).getRectangle();
            bodyDef.type= BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX()+rect.getWidth()/2)/ Config.PPM,(rect.getY()+ rect.getHeight()/2)/Config.PPM);
            shape.setAsBox(rect.getWidth()/2/Config.PPM,rect.getHeight()/2/Config.PPM);
            body = world.createBody(bodyDef);
            fixtureDef.shape = shape;
            fixtureDef.filter.categoryBits = Config.OBJECT_BIT;
            body.createFixture(fixtureDef);
        }

        for (MapObject mapObject: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)mapObject).getRectangle();
            bodyDef.type= BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX()+rect.getWidth()/2)/Config.PPM,(rect.getY()+ rect.getHeight()/2)/Config.PPM);
            shape.setAsBox(rect.getWidth()/2/Config.PPM,rect.getHeight()/2/Config.PPM);
            body = world.createBody(bodyDef);
            fixtureDef.shape = shape;
            fixtureDef.filter.categoryBits = Config.OBJECT_BIT;
            body.createFixture(fixtureDef);
        }

        for (MapObject mapObject: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)mapObject).getRectangle();
            new Coin(world,atlas,map,rect);
        }

        for (MapObject mapObject: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)mapObject).getRectangle();
            new Brick(world,atlas,map,rect);
        }
    }
}
