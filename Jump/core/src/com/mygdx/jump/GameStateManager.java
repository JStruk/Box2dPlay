package com.mygdx.jump;

import java.util.Stack;

/**
 * Created by Justin on 2015-04-07.
 */
public class GameStateManager {
    private Game game;

    private Stack<GameState> gameStates;

    public static final int PLAY = 912837;

    public GameStateManager(Game game) {
        this.game = game;
        gameStates = new Stack<GameState>();
        pushState(PLAY);
    }

    public Game game() { return game; }

    public void update(float dt) {
        gameStates.peek().update(dt);
    }

    public void render() {
        gameStates.peek().render();
    }

    private GameState getState(int state) {
       // if(state == PLAY) return new Jump(this);
        return null;
    }

    public void setState(int state) {
        popState();
        pushState(state);
    }

    public void pushState(int state) {
        gameStates.push(getState(state));
    }

    public void popState() {
        GameState g = gameStates.pop();
        g.dispose();
    }

}
















