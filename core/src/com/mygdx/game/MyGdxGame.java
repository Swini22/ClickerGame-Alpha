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

	public final SplashScreen splashScreen;
	public final BattleScreen battleScreen;
    public final AllyScreen allyScreen;
    public final InventoryScreen inventoryScreen;
    public final HeroScreen heroScreen;
    public final OptionScreen optionScreen;

	private OrthographicCamera camera;

    private Player player;

	public MyGdxGame(Player player){
		super();
        splashScreen = new SplashScreen(this);
        battleScreen = new BattleScreen(this);
        allyScreen = new AllyScreen(this);
        inventoryScreen = new InventoryScreen(this);
        heroScreen = new HeroScreen(this);
        optionScreen = new OptionScreen(this);
	}

	@Override
	public void create() {
		setScreen(splashScreen);
	}


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
