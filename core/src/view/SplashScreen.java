package view;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;

import controller.BattleRenderer;

public class SplashScreen implements Screen {

	MyGdxGame game;
	BattleScreen battleScreen;

	public SplashScreen(MyGdxGame game) {
		this.game = game;
		battleScreen = new BattleScreen(game);
	}

	@Override
	public void render(float delta) {
		game.setScreen(battleScreen);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}
