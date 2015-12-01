package view;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;

import controller.BattleRenderer;

public class BattleScreen implements Screen {

	MyGdxGame game;
	BattleRenderer renderer;

	public BattleScreen(MyGdxGame game) {
		this.game = game;
		renderer = new BattleRenderer(game);
	}

	@Override
	public void render(float delta) {
		renderer.render(delta);
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
