package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Jumpy extends ApplicationAdapter implements GestureDetector.GestureListener {
    SpriteBatch batch;
    Sprite sDude;
    ShapeRenderer shapeRenderer;
    Texture tDude;
    GestureDetector gestureDetector;
    boolean isJumping = false;
    int nDinyY, nOld, nY = 200;
    int vx = 0;
    World world = new World(new Vector2(0, -10), true);
    Box2DDebugRenderer debugRenderer;



    @Override
    public void create() {
        BodyDef bodyDef = new BodyDef();
// We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.DynamicBody;
// Set our body's starting position in the world
        bodyDef.position.set(100, 300);

// Create our body in the world using our body definition
        Body body = world.createBody(bodyDef);

// Create a circle shape and set its radius to 6
        CircleShape circle = new CircleShape();
        circle.setRadius(6f);

// Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f; // Make it bounce a little bit

// Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);

// Remember to dispose of any shapes after you're done with them!
// BodyDef and FixtureDef don't need disposing, but shapes do.
        circle.dispose();
        debugRenderer = new Box2DDebugRenderer();
        /*batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        tDude = new Texture("diny.jpg");
        sDude = new Sprite(tDude);*/
      //  Body body = new Body();
      //  Body body = world.createBody(bodyDef);
      //  Fixture fixture = body.createFixture(fixtureDef);
    // Box2DSprite box2DSprite = new Box2DSprite(someSpriteOrTextureYouHave);
     //  body.setUserData(box2DSprite); // will draw on whole body
       // fixture.setUserData(box2DSprite); // will only draw on the Fixture

        gestureDetector = new GestureDetector(this);
        gestureDetector = new GestureDetector(this);
        Gdx.input.setInputProcessor(gestureDetector);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
     //  debugRenderer.render(world, camera.combined);
       /* batch.begin();
        batch.draw(circle);
        batch.draw(sDude, 200, nY, 50, 50);
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 0, 1);
        shapeRenderer.rect(200, (Gdx.graphics.getHeight() / 4), 100, 10);
        shapeRenderer.end();
       // sDude.x+=vx;
      // nY=+vy;
    //    if (Gdx.input.isTouched()) {
      //      sDude.applyLinearImpulse(new Vector2(0, 20), body.getPosition());
        //}
       // if (isJumping) {
         //   while (nOld > nDinyY) {
           //     nY++;
            //}
       // }
        isJumping = false;*/
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        if (!isJumping) {
            nOld = nY;
            nDinyY = (nY + 100);
            //vy=+13;
            isJumping = true;
        }
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}
