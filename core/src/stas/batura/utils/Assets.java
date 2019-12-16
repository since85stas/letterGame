package stas.batura.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;


/**
 * Created by seeyo on 03.12.2018.
 */

public class Assets implements Disposable, AssetErrorListener {

    public static final String TAG = Assets.class.getName();
    public static final Assets instance = new Assets();

    private AssetManager assetManager;

    private Assets() {
    }

    public StarAssets starAssets;
    public TileAssets tileAssets;
//    public SkinAssets skinAssets;
    public LockAssets lockAssets;
    public SoundsBase soundsBase;

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.load("mini_star.png", Pixmap.class);
        assetManager.load("mini_lock.png", Texture.class);

        assetManager.load("ship64.png"   ,Texture.class);

        assetManager.load("A_letter.png"   ,Texture.class);
        assetManager.load("B_letter.png"   ,Texture.class);
        assetManager.load("pop3.ogg", Sound.class);
        assetManager.load("pingpongbat.ogg",Sound.class);

//        assetManager.load("skin/craftacular-ui.json",Skin.class);

        assetManager.finishLoading();

        Pixmap starTexture    = assetManager.get  ("mini_star.png");
        Texture lockTexture    = assetManager.get("mini_lock.png");
        Texture charAtexture = assetManager.get("A_letter.png");
        Texture charBtexture = assetManager.get("B_letter.png");

        Texture heroTexture = assetManager.get("ship64.png");

        Sound bubbleSound = assetManager.get("pop3.ogg");
        Sound tookSound   = assetManager.get("pingpongbat.ogg");
//        Skin mySkin = assetManager.get("skin/craftacular-ui.json");

//        enemyAssets = new EnemyAssets(walkTexture);
        starAssets       = new StarAssets(starTexture);
//        skinAssets       = new SkinAssets(mySkin);
        lockAssets       = new LockAssets(lockTexture);
        soundsBase       = new SoundsBase(bubbleSound,tookSound);
//        crosshairAssets = new CrosshairAssets(crossTexture);


    }


    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset: " + asset.fileName, throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }


    public class TileAssets {
        private static final int FRAME_COLS = 3; // #1
        private static final int FRAME_ROWS = 1; // #2
        public Texture texture1;
        public Texture texture2;
        public TileAssets (Texture texture1, Texture texture2 ) {
            this.texture1 = texture1;
            this.texture2 = texture2;
            Gdx.app.log(TAG,"animation load");
        }

        public Texture getTexture(int i) {
            switch (i) {
                case 0:
                    return texture1;
                case 1:
                    return texture2;
            }
            return null;
        }
    }

    public class BrokenAssets {
        //        public final Animation<TextureRegion> walkAnimation;
        public final Texture brokenTexture;
        TextureRegion[] walkFrames; // #5
        SpriteBatch spriteBatch; // #6
        TextureRegion currentFrame; //
        public BrokenAssets (Texture texture) {
            brokenTexture = texture;
            Gdx.app.log(TAG,"animation load");
        }
    }

    public class LettersAsserts{

        public final Texture textA;
        public final Texture textB;

        public LettersAsserts(Texture tA, Texture tB) {
            textA = tA;
            textB = tB;
        }
    }

    public class HeroAsserts{

        public final Texture textHero;

        public HeroAsserts(Texture h) {
            textHero = h;
        }
    }

    public class StarAssets {
        public Texture texture;
        public Texture achieveTexture;
        public Texture menuTexture;
        public StarAssets(Pixmap pixmap) {
//            this.texture = texture;
            Pixmap pixmap_achieve = new Pixmap((int)(ScreensConstants.ACHIEVE_HEIGHT*Gdx.graphics.getWidth()),
                    (int)(ScreensConstants.ACHIEVE_HEIGHT*Gdx.graphics.getHeight()),
                    pixmap.getFormat());
            pixmap_achieve.drawPixmap(pixmap,
                    0, 0, pixmap.getWidth(), pixmap.getHeight(),
                    0, 0, pixmap_achieve.getWidth(), pixmap_achieve.getHeight()
            );
            Texture texture = new Texture(pixmap_achieve);
            pixmap_achieve.dispose();
            achieveTexture = texture;
            texture = new Texture(pixmap);
            this.texture = texture;
            pixmap.dispose();
            Gdx.app.log(TAG,"animation load");
        }

    }

    public class LockAssets {
        public Texture texture;
        public LockAssets(Texture texture) {
            this.texture = texture;
            Gdx.app.log(TAG,"animation load");
        }
    }


    public class SoundsBase {
        public Sound bubbleSound;
        public Sound tookSound;
        public SoundsBase (Sound bubbleSound, Sound tookSound) {
            this.bubbleSound = bubbleSound;
            this.tookSound   = tookSound;
            Gdx.app.log(TAG,"animation load");
        }
    }




}
