package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Box2dPlay extends ApplicationAdapter implements GestureDetector.GestureListener {
    SpriteBatch batch;
    Sprite sprite;
    Texture img;
    World world;
    Body body;
    Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;
    OrthographicCamera camera;
    GestureDetector gestureDetector;
    boolean bUp = false;

    float fTorque = 0.0f;
    boolean drawSprite = true;

    final float PIXELS_TO_METERS = 100f;

    @Override
    public void create() {

        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        sprite = new Sprite(img);

        sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);

        world = new World(new Vector2(0, 0f), true);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) / PIXELS_TO_METERS, (sprite.getY() + sprite.getHeight() / 2) / PIXELS_TO_METERS);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth() / 2 / PIXELS_TO_METERS, sprite.getHeight()
                / 2 / PIXELS_TO_METERS);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;

        body.createFixture(fixtureDef);
        shape.dispose();
        gestureDetector = new GestureDetector(this);
        Gdx.input.setInputProcessor(gestureDetector);

        // Create a Box2DDebugRenderer, this allows us to see the physics 
        //simulation controlling the scene
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.
                getHeight());
    }

    private float elapsed = 0;

    @Override
    public void render() {
        camera.update();
        // Step the physics simulation forward at a rate of 60hz
        world.step(1f / 60f, 6, 2);

        // Apply fTorque to the physics body.  At start this is 0 and will do
        //  nothing.  Controlled with [] keys
        // fTorque is applied per frame instead of just once
        body.applyTorque(fTorque, true);

        // Set the sprite's position from the updated physics body location
        sprite.setPosition((body.getPosition().x * PIXELS_TO_METERS) - sprite.getWidth() / 2, (body.getPosition().y * PIXELS_TO_METERS) - sprite.getHeight() / 2)
        ;
        // Ditto for rotation
        //  sprite.setRotation((float)Math.toDegrees(body.getAngle()));

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        // Scale down the sprite batches projection matrix to box2D size
        debugMatrix = batch.getProjectionMatrix().cpy().scale(PIXELS_TO_METERS, PIXELS_TO_METERS, 0);
        body.setGravityScale(100.0f);
        body.setLinearVelocity(0f, -1f);
        if (bUp) {
            // body.applyForceToCenter(0f,10f,true);
            body.setLinearVelocity(0f, 3f);
        }
        // body.applyTorque();
        batch.begin();

        if (drawSprite)
            batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(), sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());

        batch.end();

        // Now render the physics world using our scaled down matrix
        // Note, this is strictly optional and is, as the name suggests, just 
        // for debugging purposes
        debugRenderer.render(world, debugMatrix);
    }

    @Override
    public void dispose() {
        img.dispose();
        world.dispose();
    }

    public boolean keyUp(int keycode) {

        // On right or left arrow set the velocity at a fixed rate in that 
        // direction

        /*if(keycode == Input.Keys.RIGHT)
            body.setLinearVelocity(1f, 0f);
        if(keycode == Input.Keys.LEFT)
            body.setLinearVelocity(-1f,0f);

        if(keycode == Input.Keys.UP)
            body.applyForceToCenter(0f,10f,true);
        if(keycode == Input.Keys.DOWN)
            body.applyForceToCenter(0f, -10f, true);

        // On brackets ( [ ] ) apply fTorque, either clock or counterclockwise
        if(keycode == Input.Keys.RIGHT_BRACKET)
            fTorque += 0.1f;
        if(keycode == Input.Keys.LEFT_BRACKET)
            fTorque -= 0.1f;

        // Remove the fTorque using backslash /
        if(keycode == Input.Keys.BACKSLASH)
            fTorque = 0.0f;

        // If user hits spacebar, reset everything back to normal
        if(keycode == Input.Keys.SPACE) {
            body.setLinearVelocity(0f, 0f);
            body.setAngularVelocity(0f);
            fTorque = 0f;
            sprite.setPosition(0f,0f);
            body.setTransform(0f,0f,0f);
        }

        // The ESC key toggles the visibility of the sprite allow user to see 
       // physics debug info
        if(keycode == Input.Keys.ESCAPE)
            drawSprite = !drawSprite;
*/
        return true;
    }

    // @Override
    public boolean keyTyped(char character) {
        return false;
    }


    // On touch we apply force from the direction of the users touch.
    // This could result in the object "spinning"
    //   @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //   body.applyForce(1f,1f,screenX,screenY,true);
        //body.applyfTorque(0.4f,true);
        return true;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        bUp = true;
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
