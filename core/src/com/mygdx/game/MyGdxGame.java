package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import actor.Player;
import view.BattleScreen;
import view.SplashScreen;

public class MyGdxGame extends Game {

	public SplashScreen splashScreen;

	private OrthographicCamera camera;

    private Player player;

	public MyGdxGame(Player player){
		super();
	}

	@Override
	public void create() {
		splashScreen = new SplashScreen(this);
		setScreen(splashScreen);
	}


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
