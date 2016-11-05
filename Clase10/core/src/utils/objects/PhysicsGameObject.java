package utils.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Luis on 23/10/2016.
 */

public abstract class PhysicsGameObject extends GameObject {
    protected World world;
    public Body body;
    protected boolean setToDestroy;
    protected boolean destroyed;


    public PhysicsGameObject(World world){
        super();
        this.world = world;
    }

    public PhysicsGameObject(World world, TextureAtlas textureAtlas, Vector2 position){
        super(textureAtlas, position);
        this.world = world;
        defineBody();
    }

    public PhysicsGameObject(World world, TextureRegion textureRegion, Vector2 position){
        super(textureRegion,position);
        this.world = world;
        defineBody();
    }

    protected void destroy(){
        setToDestroy = true;
    }

    public abstract void defineBody();


}
