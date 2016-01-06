package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.swing.text.html.Option;

import actor.Player;
import view.AllyScreen;
import view.BattleScreen;
import view.HeroScreen;
import view.InventoryScreen;
import view.OptionScreen;
import view.SplashScreen;

public class MyGdxGame extends Game {

	public SplashScreen splashScreen;
	public BattleScreen battleScreen;
    public AllyScreen allyScreen;
    public InventoryScreen inventoryScreen;
    public HeroScreen heroScreen;
    public OptionScreen optionScreen;

	private OrthographicCamera camera;

    private Player player;

	public MyGdxGame(Player player){
		super();
	}

	@Override
	public void create() {

        splashScreen = new SplashScreen(this);
        battleScreen = new BattleScreen(this);
        allyScreen = new AllyScreen(this);
        inventoryScreen = new InventoryScreen(this);
        heroScreen = new HeroScreen(this);
        optionScreen = new OptionScreen(this);

		setScreen(splashScreen);

	}


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
