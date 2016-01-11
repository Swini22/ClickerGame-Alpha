package controller;

import actor.Enemy;
import actor.Player;
import controller.battle.HealthBar;

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
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;

import java.awt.event.InputEvent;
import java.util.Random;

public class BattleRenderer {

	public static final float ENEMY_HEIGHT = Gdx.graphics.getHeight()/3;
	public static final float ENEMY_WIDTH = ENEMY_HEIGHT; // all Enemys are perfect cubes

	MyGdxGame game;

	private OrthographicCamera cam;

	private Stage stage;

	private Enemy enemy;
    private Player player;

	private SpriteBatch batch;
	private Texture enemyTexture;
	private Texture goldTexture;
	private Texture background;

    private Label enemyHealthLabel;

	private Rectangle enemyRectangle;
    private boolean enemyMovesUp;
	private boolean dmgVisible;
	private int dmgVisibilityCounter = 0;
    private float enemyAnimationTimeSpent = 0;
    private float dbUpdateTime = 0;

	TextButton inventoryMenuButton, heroMenuButton, resetMenuButton, optionsMenuButton;

	HealthBar healthBar;

	BitmapFont font;
	Skin skin;

	public BattleRenderer(MyGdxGame game) {

        player = new Player("Player", 0 ,0);

		stage = new Stage();

		goldTexture = new Texture("gold.png");

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

		background = new Texture("bg_forest.jpg");

		healthBar = new HealthBar();
        healthBar.setSize(Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 40);
        healthBar.setPosition((Gdx.graphics.getWidth() / 2) - (healthBar.getWidth() / 2), (Gdx.graphics.getHeight() / 10) * 7);
        stage.addActor(healthBar);

        initBattleScreen();

        enemyMovesUp = true;

        enemyHealthLabel = new Label(String.valueOf(enemy.getLifePoints()), skin);
        enemyHealthLabel.setPosition((Gdx.graphics.getWidth() / 2) - (enemyHealthLabel.getWidth() / 2), (Gdx.graphics.getHeight() / 10) * 8);
        stage.addActor(enemyHealthLabel);

	}

    private void createMenu(){
        //Menu
        heroMenuButton = new TextButton("Hero", skin); // Use the initialized skin
        heroMenuButton.setSize(300, 200);
        heroMenuButton.setPosition(0, Gdx.graphics.getHeight() - heroMenuButton.getHeight());
        heroMenuButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.heroScreen);
            }
        });
        stage.addActor(heroMenuButton);

        inventoryMenuButton = new TextButton("Inventory", skin); // Use the initialized skin
        inventoryMenuButton.setSize(300, 200);
        inventoryMenuButton.setPosition(0, Gdx.graphics.getHeight() - inventoryMenuButton.getHeight() * 2);
        inventoryMenuButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.inventoryScreen);
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
                game.setScreen(game.optionScreen);
            }
        });
        stage.addActor(optionsMenuButton);

    }

	public void initBattleScreen() {


		createNewEnemy();

	}

	public void render(float delta) {

		updateEnemy(delta);

        enemyHealthLabel.setText(String.valueOf(enemy.getLifePoints()));
        healthBar.setHealth(enemy.getLifePoints(), enemy.getMaxLifePoints());

		cam.update();

		batch.setProjectionMatrix(cam.combined);
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(enemyTexture, enemyRectangle.getX(), enemyRectangle.getY(),
				enemyRectangle.getWidth(), enemyRectangle.getHeight());
		batch.draw(goldTexture, heroMenuButton.getX()+ heroMenuButton.getWidth()+50,
				heroMenuButton.getX()+ Gdx.graphics.getHeight()-heroMenuButton.getHeight(),
				Gdx.graphics.getWidth()/5 ,Gdx.graphics.getHeight()/10);
		font.draw(batch, Double.toString(player.getGold()),heroMenuButton.getX()+heroMenuButton.getWidth()+goldTexture.getWidth()+50 , heroMenuButton.getX()+ Gdx.graphics.getHeight());

		if (dmgVisible){
			dmgVisibilityCounter+=1;
			if (dmgVisibilityCounter > 5) {
				dmgVisibilityCounter = 0;
				dmgVisible = false;
			}
			batch.draw(chooseVisibleDMG(), enemyRectangle.getX(), enemyRectangle.getY(),
					enemyRectangle.getWidth(), enemyRectangle.getHeight());
		}

		font.draw(batch, Float.toString(enemy.getLifePoints()), 100, 100);

		batch.end();

		stage.act(delta*30);
		stage.draw();
	}

    private void updateEnemy(float delta){

        updateEnemyPosition(delta);

        if (enemy.getLifePoints() <= 0) {

            onEnemyDeath();

        }
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

    private void onEnemyDeath(){

        BattleHandler.calcEnemyDeathReward(player, enemy);

        createNewEnemy();

    }

	public void createNewEnemy() {

        enemy = EnemyHandler.createNewEnemy();
        enemyTexture = EnemyHandler.createNewEnemyTexture();

        enemyRectangle = new Rectangle();
        enemyRectangle.setSize(ENEMY_WIDTH, ENEMY_HEIGHT);
        enemyRectangle.setPosition(
                Gdx.graphics.getWidth() / 2 - enemyRectangle.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - enemyRectangle.getHeight() / 2);
	}

	public Texture chooseVisibleDMG() {
		int r = (int)(Math.random() * 2) + 1;
		if(r == 1){
			return new Texture("visible_dmg_1.png");
		}
		else{
			return new Texture("visible_dmg_2.png");
		}


	}

	public float damageEnemy() {

		return EnemyHandler.damageEnemy(enemy);

	}

	public void enemyClicked() {

		float damage = damageEnemy();

        Label damageLabel = new Label(String.valueOf(damage), skin);
        damageLabel.setPosition(Gdx.graphics.getWidth() / 2, (Gdx.graphics.getWidth() / 2) - (damageLabel.getWidth() / 2));
        stage.addActor(damageLabel);
        createBouncingActor(damageLabel);
        createFadingActor(damageLabel);

		dmgVisible= true;
	}

    public void createBouncingActor(Actor actor){

        Random rand = new Random();

        float maxY = Gdx.graphics.getHeight() / 2;
        float maxX = Gdx.graphics.getWidth() / 2;

        MoveToAction action = Actions.action(MoveToAction.class);
        action.setPosition(rand.nextFloat() * (maxX - 0) + 0, rand.nextFloat() * (maxY - 0) + 0);
        action.setDuration(100f);
        action.setInterpolation(Interpolation.bounceOut);
        actor.addAction(action);
    }

    public void createFadingActor(Actor actor){

        SequenceAction action = Actions.sequence(Actions.alpha(1), Actions.fadeOut(100f));
        actor.addAction(action);
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

    private void createBasicSkin(){
        //Create a font
        BitmapFont font = new BitmapFont();
		font.getData().setScale(3, 3);
        skin = new Skin();
        skin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
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

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = skin.getFont("default");
//        labelStyle.background = skin.newDrawable("background", Color.WHITE);
        skin.add("default", labelStyle);

    }
}
