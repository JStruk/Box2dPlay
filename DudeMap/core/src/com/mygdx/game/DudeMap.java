package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class DudeMap extends ApplicationAdapter implements InputProcessor {
    Texture img;
    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    Stage stage;
    Image dude;
    TextureRegion rgn;
    int fDudeX, fDudeY;
    int nx, ny;
    SpriteBatch sb;
    Texture texture;
    Sprite sDude;
    TextButton tbUp;
    TextButton tbDown;
    TextButton tbLeft;
    TextButton tbRight;
    TextButton.TextButtonStyle tbsUp;
    TextButton.TextButtonStyle tbsDown;
    TextButton.TextButtonStyle tbsLeft;
    TextButton.TextButtonStyle tbsRight;
    Skin skUp;
    Skin skDown;
    Skin skLeft;
    Skin skRight;
    TextureAtlas taBtns;
    Boolean bU=false, bD=false, bR=false, bL=false;

    @Override
    public void create() {//http://www.gamefromscratch.com/post/2014/04/16/LibGDX-Tutorial-11-Tiled-Maps-Part-1-Simple-Orthogonal-Maps.aspx
        //http://www.gamefromscratch.com/post/2014/04/15/A-quick-look-at-Tiled-An-open-source-2D-level-editor.aspx
        //Thanks Mike from gamefromscratch, great tutorials to get me up and running with tiled :D
        stage = new Stage();
        dude = new Image();
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        Gdx.input.setInputProcessor(stage);
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("characters.pack"));
        rgn = atlas.findRegion("guyfront");
        // dude = new Image(rgn);
        sb = new SpriteBatch();
        sDude = new Sprite(rgn);
        sDude.setBounds(375, (h - 200), 125, 175);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 500, 500);
        BitmapFont font;

        font = new BitmapFont();
        skUp = new Skin();
        skDown = new Skin();
        skLeft = new Skin();
        skRight = new Skin();
        taBtns = new TextureAtlas(Gdx.files.internal("dudearrows.pack"));//Importing the .pack into a texture atlas that holds multiple images and can be referenced within a TextButtonStyle
        skUp.addRegions(taBtns);//Applying a texture atlas into a skin
        skDown.addRegions(taBtns);
        skLeft.addRegions(taBtns);
        skRight.addRegions(taBtns);
        tbsUp = new TextButton.TextButtonStyle();//TextButtonStyle Holds all the images that will be applied to the TextButton
        tbsDown = new TextButton.TextButtonStyle();
        tbsLeft = new TextButton.TextButtonStyle();
        tbsRight = new TextButton.TextButtonStyle();
        tbsUp.font = font;
        tbsDown.font = font;
        tbsLeft.font = font;
        tbsRight.font = font;
        tbsUp.up = skUp.getDrawable("up");//Setting positions and the image to use when the button is in those positions
        tbsUp.down = skUp.getDrawable("uppressed");
        tbsDown.up = skDown.getDrawable("down");
        tbsDown.down = skDown.getDrawable("downpressed");
        tbsLeft.up = skLeft.getDrawable("left");
        tbsLeft.down = skLeft.getDrawable("leftpressed");
        tbsRight.up = skLeft.getDrawable("right");
        tbsRight.down = skLeft.getDrawable("rightpressed");
        tbUp = new TextButton("", tbsUp);
        tbUp.setSize(w * 200 / 1794, h * 200 / 1080);
        tbUp.setPosition(100, (h / 4) - 50);
        tbUp.setName("up");
        tbUp.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                bU = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                bU=false;
            }
        });
        tbDown = new TextButton("", tbsDown);//Applying the TextButtonStyle to the TextButton giving it all of its positions and images as well as any text but I didn't use
        tbDown.setSize(w * 200 / 1794, h * 200 / 1080);
        tbDown.setPosition(100, (h / 4) - 250);
        tbDown.addListener(new InputListener() {//Creating an InputListener to assign to each button instead of writing the same code four times :D
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                bL = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        tbLeft = new TextButton("", tbsLeft);
        tbLeft.setSize(w * 200 / 1794, h * 200 / 1080);
        tbLeft.setPosition(-25, (h / 4) - 150);
        tbLeft.setName("left");
        tbLeft.addListener(new InputListener() {//Creating an InputListener to assign to each button instead of writing the same code four times :D
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                bL = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
        tbRight = new TextButton("", tbsRight);
        tbRight.setSize(w * 200 / 1794, h * 200 / 1080);
        tbRight.setPosition(225, (h / 4) - 150);
        tbRight.addListener(new InputListener() {//Creating an InputListener to assign to each button instead of writing the same code four times :D
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                bR = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                bR=false;
            }
        });
        stage.addActor(tbUp);
        stage.addActor(tbDown);
        stage.addActor(tbLeft);
        stage.addActor(tbRight);
        //  camera.translate(0, 2700);
        camera.update();
        tiledMap = new TmxMapLoader().load("firstmap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        //  Gdx.input.setInputProcessor(this);
        dude.setHeight(500);
        dude.setWidth(500);
        // stage.addActor(dude);
        dude.setX(fDudeX);
        dude.setY(fDudeY);

    }

    @Override
    public void render() {
        camera.position.set((fDudeX + 265), (fDudeY + 2950), 100);
        //   camera.position.set(dude.getX(), dude.getY(), 0);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //   super.render();
        //   stage.draw();

        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        super.render();
        sb.begin();
        sDude.draw(sb);
        sb.end();
        stage.draw();
        if (bU) {
            sDude.translateY(10);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
