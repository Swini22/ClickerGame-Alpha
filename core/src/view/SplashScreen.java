package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

import controller.BattleRenderer;

public class SplashScreen implements Screen {

	MyGdxGame game;
	BattleScreen battleScreen;

	private OrthographicCamera cam;
	private SpriteBatch batch;

	private Texture logoTexture;

	float timepassed;

	public SplashScreen(MyGdxGame game) {
		this.game = game;
		battleScreen = new BattleScreen(game);

		batch = new SpriteBatch();

		cam = new OrthographicCamera(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		cam.position.set(Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2, 0);
		cam.update();

		logoTexture = new Texture("bear.png");

		timepassed = 0;

	}

	@Override
	public void render(float delta) {

		//timepassed += delta;

		//if(timepassed > 1000){
			game.setScreen(battleScreen);
		/**} else {

			cam.update();

			batch.setProjectionMatrix(cam.combined);
			Gdx.gl.glClearColor(0, 1, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();
			batch.draw(logoTexture, 0, 0);

			batch.end();

		}**/


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
