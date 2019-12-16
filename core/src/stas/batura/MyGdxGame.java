package stas.batura;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import stas.batura.screens.GameScreen;

public class MyGdxGame extends Game {

	private int width;
	private int height;

	@Override
	public void create () {


		GameScreen gameScreen = new GameScreen();
		setScreen(gameScreen);
	}

	@Override
	public void render () {
	}

	public void update(){

	}
	
	@Override
	public void dispose () {

	}
}
