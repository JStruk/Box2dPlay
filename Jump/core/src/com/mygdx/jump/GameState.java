package com.mygdx.jump;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Justin on 2015-04-07.
 */
public class GameState {

    protected GameStateManager gsm;
    protected Game game;

    protected SpriteBatch sb;
    protected OrthographicCamera cam;
    protected OrthographicCamera hudCam;

    protected GameState(GameStateManager gsm) {
        this.gsm = gsm;
        game = gsm.game();
        sb = game.getSpriteBatch();
        cam = game.getCamera();
        hudCam = game.getHUDCamera();
    }

    public void handleInput() {

    }

    public void update(float dt) {

    }

    public void render() {

    }

    public void dispose() {

    }

}










