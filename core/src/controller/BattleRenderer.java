package controller;

import actor.Enemy;
import actor.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
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
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;

import java.awt.event.InputEvent;

public class BattleRenderer {

	public static final float ENEMY_HEIGHT = 600;
	public static final float ENEMY_WIDTH = 600;

	MyGdxGame game;

	private OrthographicCamera cam;

	private Stage stage;

	private Enemy enemy;
    private Player player;

	private SpriteBatch batch;
	private Texture enemyTexture;
	private Texture background;

	private Rectangle enemyRectangle;
    private boolean enemyMovesUp;
    private float enemyAnimationTimeSpent = 0;

	TextButton inventoryMenuButton, heroMenuButton, resetMenuButton, optionsMenuButton, exitMenuButton;

	BitmapFont font;
	Skin skin;

	public BattleRenderer(MyGdxGame game) {

        player = new Player();
        player.setGold(0);
        player.setExp(0);
        player.setName("Mik");

		stage = new Stage();

        //Input Processors
        InputProcessor userInput = new UserInputProcessor();
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(userInput);
        Gdx.input.setInputProcessor(inputMultiplexer);

		this.game = game;

		batch = new SpriteBatch();

		cam = new OrthographicCamera(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		cam.position.set(Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() / 2, 0);
		cam.update();

        font = new BitmapFont();

        createBasicSkin();
        createMenu();

        initBattleScreen();

        enemyMovesUp = true;

	}

    private void createMenu(){
        //Menu
        heroMenuButton = new TextButton("Hero", skin); // Use the initialized skin
        heroMenuButton.setSize(300, 200);
        heroMenuButton.setPosition(0, Gdx.graphics.getHeight() - heroMenuButton.getHeight());
        heroMenuButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                //TODO open Hero Menu
            }
        });
        stage.addActor(heroMenuButton);

        inventoryMenuButton = new TextButton("Inventory", skin); // Use the initialized skin
        inventoryMenuButton.setSize(300, 200);
        inventoryMenuButton.setPosition(0, Gdx.graphics.getHeight() - inventoryMenuButton.getHeight() * 2);
        inventoryMenuButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                //TODO open Hero Menu
            }
        });
        stage.addActor(inventoryMenuButton);

        resetMenuButton = new TextButton("Reset", skin); // Use the initialized skin
        resetMenuButton.setSize(300, 200);
        resetMenuButton.setPosition(0, Gdx.graphics.getHeight() - resetMenuButton.getHeight() * 3);
        resetMenuButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                //TODO open Hero Menu
            }
        });
        stage.addActor(resetMenuButton);

        optionsMenuButton = new TextButton("Options", skin); // Use the initialized skin
        optionsMenuButton.setSize(300, 200);
        optionsMenuButton.setPosition(0, Gdx.graphics.getHeight() - optionsMenuButton.getHeight() * 4);
        optionsMenuButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                //TODO open Hero Menu
            }
        });
        stage.addActor(optionsMenuButton);

        exitMenuButton = new TextButton("Exit", skin); // Use the initialized skin
        exitMenuButton.setSize(300, 200);
        exitMenuButton.setPosition(0, Gdx.graphics.getHeight() - exitMenuButton.getHeight() * 5);
        exitMenuButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                //TODO open Hero Menu
            }
        });
        stage.addActor(exitMenuButton);
    }

	public void initBattleScreen() {

		background = new Texture("bg_forest.jpg");

		createNewEnemy();

	}

	public void render(float delta) {

        updateEnemy(delta);

		cam.update();

		batch.setProjectionMatrix(cam.combined);
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getHeight(),  Gdx.graphics.getWidth());
		batch.draw(enemyTexture, enemyRectangle.getX(), enemyRectangle.getY(),
				enemyRectangle.getWidth(), enemyRectangle.getHeight());

		font.draw(batch, Float.toString(enemy.getLifePoints()), 100, 100);
		batch.end();

		stage.act();
		stage.draw();
	}

    private void updateEnemy(float delta){

        updateEnemyPosition(delta);

        if (enemy.getLifePoints() <= 0) {

            onEnemyDeath();

        }
    }

    private void onEnemyDeath(){

        BattleHandler.calcEnemyDeathReward(player, enemy);

        createNewEnemy();

    }

    private void updateEnemyPosition(float delta){

        enemyAnimationTimeSpent += delta;

        if(enemyAnimationTimeSpent > 2){
            if(enemyMovesUp){
                enemyMovesUp = false;
            } else {
                enemyMovesUp = true;
            }
            enemyAnimationTimeSpent = 0;
        }

        if(enemyMovesUp){
            enemyRectangle.setY(enemyRectangle.getY() + delta * 30);
        } else {
            enemyRectangle.setY(enemyRectangle.getY() - delta * 30);
        }

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

        enemyRectangle = new Rectangle();
        enemyRectangle.setSize(ENEMY_WIDTH, ENEMY_HEIGHT);
        enemyRectangle.setPosition(
                Gdx.graphics.getWidth() / 2 - enemyRectangle.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - enemyRectangle.getHeight() / 2);

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
