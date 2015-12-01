package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import view.BattleScreen;

public class MyGdxGame extends Game {

	public BattleScreen battleScreen;

	private OrthographicCamera camera;

	@Override
	public void create() {

		battleScreen = new BattleScreen(this);
		setScreen(battleScreen);
	}
}
