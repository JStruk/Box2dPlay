package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Juddy extends Game {
    Stage stage;
    BitmapFont font;
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
    InputListener inlis;
    String sName;
    int nBtn;
    int nSHeight, nSWidth;
	@Override
	public void create () {
        nSHeight = Gdx.graphics.getHeight();//Get the stage Height
        nSWidth = Gdx.graphics.getWidth();//Get the stage Width
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        skUp= new Skin();
        skDown=new Skin();
        skLeft=new Skin();
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
        tbsLeft.font=font;
        tbsRight.font=font;
        tbsUp.up = skUp.getDrawable("up");//Setting positions and the image to use when the button is in those positions
        tbsUp.down = skUp.getDrawable("uppressed");
        tbsDown.up = skDown.getDrawable("down");
        tbsDown.down = skDown.getDrawable("downpressed");
        tbsLeft.up=skLeft.getDrawable("left");
        tbsLeft.down=skLeft.getDrawable("leftpressed");
        tbsRight.up=skLeft.getDrawable("right");
        tbsRight.down=skLeft.getDrawable("rightpressed");
        tbUp = new TextButton("", tbsUp);
        tbUp.setSize(nSWidth * 200 / 1794, nSHeight * 200 / 1080);
        tbUp.setPosition(100, (nSHeight / 4)-50);
        tbUp.setName("up");
        tbDown = new TextButton("",tbsDown);//Applying the TextButtonStyle to the TextButton giving it all of its positions and images as well as any text but I didn't use
        tbDown.setSize(nSWidth * 200 / 1794, nSHeight * 200 / 1080);
        tbDown.setPosition(100, (nSHeight / 4) - 250);
        tbLeft = new TextButton("",tbsLeft);
        tbLeft.setSize(nSWidth*200/1794, nSHeight*200/1080);
        tbLeft.setPosition(-25, (nSHeight / 4) - 150);
        tbLeft.setName("left");
        tbRight = new TextButton("", tbsRight);
        tbRight.setSize(nSWidth*200/1794, nSHeight*200/1080);
        tbRight.setPosition(225,(nSHeight/4)-150);
        stage.addActor(tbUp);
        stage.addActor(tbDown);
        stage.addActor(tbLeft);
        stage.addActor(tbRight);
        InputListener inlis = new InputListener(){//Creating an InputListener to assign to each button instead of writing the same code four times :D
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
               if(tbRight.equals(event.getTarget())){
                    System.out.println("Down");
                }
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(tbRight.equals(event.getTarget())){
                    System.out.println("Right");
                }
            }
        };
        tbRight.addListener(inlis);
        tbLeft.addListener(inlis);
        tbDown.addListener(inlis);
        tbUp.addListener(inlis);

    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(255, 255, 255,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
        stage.draw();
	}
}
