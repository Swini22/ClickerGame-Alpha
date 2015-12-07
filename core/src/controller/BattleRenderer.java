package controller;

import actor.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.MyGdxGame;

import java.util.ArrayList;

import javax.swing.ScrollPaneLayout;

public class BattleRenderer {

	public static final float ENEMY_HEIGHT = 300;
	public static final float ENEMY_WIDTH = 300;

	MyGdxGame game;

	private OrthographicCamera cam;

	private Stage stage;

	private Enemy enemy;

	private SpriteBatch batch;
	private Texture enemyTexture;
	private Texture background;

	private Rectangle enemyRectangle;

	BitmapFont font;
	Skin skin;

	float attackCooldown;

	public BattleRenderer(MyGdxGame game) {

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		this.game = game;
		batch = new SpriteBatch();

		cam = new OrthographicCamera(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		cam.position.set(Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2, 0);
		cam.update();

		font = new BitmapFont();

		createBasicSkin();
		TextButton mainMenuButton = new TextButton("New game", skin); // Use the initialized skin
		mainMenuButton.setPosition(0, Gdx.graphics.getHeight());
		stage.addActor(mainMenuButton);

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

		if (enemy.getLifePoints() <= 0) {

			createNewEnemy();

		}

		cam.update();

		batch.setProjectionMatrix(cam.combined);
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0);
		batch.draw(enemyTexture, enemyRectangle.getX(), enemyRectangle.getY(),
				enemyRectangle.getWidth(), enemyRectangle.getHeight());

		font.draw(batch, Float.toString(enemy.getLifePoints()), 100, 100);
		batch.end();

		stage.act();
		stage.draw();
	}

	private void createBasicSkin(){
		//Create a font
		BitmapFont font = new BitmapFont();
		skin = new Skin();
		skin.add("default", font);

		//Create a texture
		Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("background", new Texture(pixmap));

		//Create a button style
		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
		textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
		textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);

		SelectBox.SelectBoxStyle selectBoxStyleStyle = new SelectBox.SelectBoxStyle();
		selectBoxStyleStyle.font = skin.getFont("default");
		selectBoxStyleStyle.background = skin.newDrawable("background", Color.BLACK);
		selectBoxStyleStyle.scrollStyle = new ScrollPane.ScrollPaneStyle();
		skin.add("default", selectBoxStyleStyle);

	}

	public void createNewEnemy() {

		enemy = EnemyHandler.createNewEnemy();
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
