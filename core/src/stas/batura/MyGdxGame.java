package stas.batura;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import stas.batura.screens.GameScreen;
import stas.batura.utils.Assets;
import stas.batura.utils.ScreensConstants;

public class MyGdxGame extends Game {

	private int width;
	private int height;

	@Override
	public void create () {

		// creating sizes
		ScreensConstants.instance.initScreensConst();

		// создаем текстуры
		AssetManager am = new AssetManager();
		Assets.instance.init(am);

		startNewGame();
	}

	public void startNewGame() {
		GameScreen gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		//update(dt);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		getScreen().render(dt);
	}

	public void update(){

	}
	
	@Override
	public void dispose () {

	}
}
