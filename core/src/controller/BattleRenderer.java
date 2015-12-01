package controller;

import actor.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MyGdxGame;

public class BattleRenderer {

	public static final float ENEMY_HEIGHT = 300;
	public static final float ENEMY_WIDTH = 300;

	MyGdxGame game;

	private OrthographicCamera cam;

	private Enemy enemy;

	private SpriteBatch batch;
	private Texture enemyTexture;
	private Texture background;

	private Rectangle enemyRectangle;

	BitmapFont font;

	float attackCooldown;

	public BattleRenderer(MyGdxGame game) {

		UserInputProcessor inputProcessor = new UserInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);
		this.game = game;
		batch = new SpriteBatch();

		cam = new OrthographicCamera(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		cam.position.set(Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2, 0);
		cam.update();

		font = new BitmapFont();

		initBattleScreen();

	}

	public void initBattleScreen() {

		background = new Texture("background.jpg");

		createNewEnemy();

		enemyRectangle = new Rectangle();
		enemyRectangle.setSize(ENEMY_WIDTH, ENEMY_HEIGHT);
		enemyRectangle.setPosition(
				Gdx.graphics.getWidth() / 2 - enemyRectangle.getWidth() / 2,
				Gdx.graphics.getHeight() / 2 - enemyRectangle.getHeight() / 2);

	}

	public void render(float delta) {

		attackCooldown = attackCooldown - delta;

		cam.update();

		/*
		 * Creates a new Enemy if the current one doesn't have any lifepoints
		 * left
		 */
		if (enemy.getLifePoints() <= 0) {

			createNewEnemy();

		}

		batch.setProjectionMatrix(cam.combined);
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0);
		batch.draw(enemyTexture, enemyRectangle.getX(), enemyRectangle.getY(),
				enemyRectangle.getWidth(), enemyRectangle.getHeight());

		font.draw(batch, Float.toString(enemy.getLifePoints()), 100, 100);
		batch.end();
	}

	public void createNewEnemy() {

		enemy = new Enemy(10000);
		enemyTexture = new Texture("bear.png");

	}

	public void damageEnemy() {

		EnemyHandler.damageEnemy(enemy);

	}

	public void enemyClicked() {

		damageEnemy();

	}

	public class UserInputProcessor implements InputProcessor {
		@Override
		public boolean touchDown(int x, int y, int pointer, int button) {
			if (button == Input.Buttons.LEFT) {

				if (enemyRectangle.contains(x, Gdx.graphics.getHeight() - y)) {

					enemyClicked();
				}

				return true;
			}
			return false;
		}

		@Override
		public boolean keyDown(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean keyTyped(char character) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			// TODO Auto-generated method stub
			return false;
		}
	}
}
