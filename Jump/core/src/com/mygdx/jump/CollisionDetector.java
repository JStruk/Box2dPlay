package com.mygdx.jump;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Justin on 2015-04-07.
 */
public class CollisionDetector implements ContactListener {
public static boolean isHit;
    @Override
    public void beginContact(Contact c) {
        Fixture fa = c.getFixtureA();
        Fixture fb = c.getFixtureB();

        if(fa.getUserData() != null && fa.getUserData().equals("foot")) {
          isHit = true;
            System.out.println("onplatform");
        }
        if(fb.getUserData() != null && fb.getUserData().equals("foot")) {
            isHit = true;
        }
        if(fa.getUserData().equals("ground") && fb.getUserData().equals("foot")) {
            isHit = false;
        }
        if(fb.getUserData().equals("ground") && fa.getUserData().equals("foot")) {
            isHit = false;
        }

    }

    @Override
    public void endContact(Contact c) {

        Fixture fa = c.getFixtureA();
        Fixture fb = c.getFixtureB();

        if(fa.getUserData() != null && fa.getUserData().equals("foot")) {
            isHit = false;
            System.out.println("false");
        }
        if(fb.getUserData() != null && fb.getUserData().equals("foot")) {
            isHit = false;
        }

    }
    public static boolean hitTest() {
        return isHit;
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
