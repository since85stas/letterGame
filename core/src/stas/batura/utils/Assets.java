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
import static stas.batura.alphabet.AplphabetConsts.*;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import stas.batura.alphabet.AplphabetConsts;


/**
 * Created by seeyo on 03.12.2018.
 */

public class Assets implements Disposable, AssetErrorListener {

    public static final String TAG = Assets.class.getName();
    public static final Assets instance = new Assets();

    private AssetManager assetManager;

    private Assets() {
    }

    public Texture mainBackTexture;

    public StarAssets starAssets;
    public LettersAsserts lettersAsserts;
    public BackAsserts backAsserts;
    public HeroAsserts heroAsserts;
    public GarbAsserts garbAsserts;
    public TileAssets tileAssets;
//    public SkinAssets skinAssets;
    public LockAssets lockAssets;
    public SkinAssets skinAssets;
    public SoundsBase soundsBase;

    public BitmapFont hudFont;

    public BitmapFont resultFont;

    public EnglAlphAssets englAlphAssets;

    public ControlArrows controlArrows;

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.load("mini_star.png", Pixmap.class);
        assetManager.load("mini_lock.png", Texture.class);

        assetManager.load("fish.png"   ,Texture.class);
        assetManager.load("ouch.png"   ,Texture.class);

        assetManager.load("bg.png", Texture.class);
        assetManager.load("star12.tga", Texture.class);

        assetManager.load("A_letter.png"   ,Texture.class);
        assetManager.load("B_letter.png"   ,Texture.class);
        assetManager.load("asteroid64.png"   ,Texture.class);
        assetManager.load("roket.png"   ,Texture.class);
        assetManager.load("english_alph.png"   ,Texture.class);
        assetManager.load("black_buttons.png", Texture.class);
        assetManager.load("fish_test_screen_size.png",Texture.class);

        // loading skin with parameters
        java.util.Map<String,BitmapFont> fontsByName = initFonts();

        SkinLoader ldr =  new GeneratedFontSkinLoader( new InternalFileHandleResolver() {
            @Override
            public FileHandle resolve(String fileName) {
                Gdx.app.log(TAG,"AssertManager>>>>>>>>>>>>" + fileName);
                return super.resolve(fileName);
            }
        }, fontsByName);
        assetManager.setLoader( Skin.class, ldr );
        assetManager.load( "skin/craftacular-ui.atlas", TextureAtlas.class );
        assetManager.load( "skin/craftacular-ui.json", Skin.class );
//        assetManager.load("pop3.ogg", Sound.class);
//        assetManager.load("pingpongbat.ogg",Sound.class);

//        assetManager.load("skin/craftacular-ui.json",Skin.class);

        assetManager.finishLoading();

        Pixmap starTexture    = assetManager.get  ("mini_star.png");
        Texture lockTexture    = assetManager.get("mini_lock.png");
        Texture charAtexture = assetManager.get("A_letter.png");
        Texture charBtexture = assetManager.get("B_letter.png");

        Texture backTexture = assetManager.get("bg.png");
        Texture srtarBackT = assetManager.get("star12.tga");

        Texture heroTexture = assetManager.get("fish.png");

        Texture alphText = assetManager.get("english_alph.png");

//        Sound bubbleSound = assetManager.get("pop3.ogg");
//        Sound tookSound   = assetManager.get("pingpongbat.ogg");
//        Skin mySkin = assetManager.get("skin/craftacular-ui.json");

//        enemyAssets = new EnemyAssets(walkTexture);

        mainBackTexture = assetManager.get("fish_test_screen_size.png");

        starAssets       = new StarAssets(starTexture);
        lettersAsserts   = new LettersAsserts(charAtexture,charBtexture);
        backAsserts      = new BackAsserts(backTexture,srtarBackT);
        heroAsserts      = new HeroAsserts(heroTexture, (Texture) assetManager.get("ouch.png"));
//        skinAssets       = new SkinAssets(mySkin);
        lockAssets       = new LockAssets(lockTexture);
//        soundsBase       = new SoundsBase(bubbleSound,tookSound);
//        crosshairAssets = new CrosshairAssets(crossTexture);

        // creating garbage
        Texture garb1 = assetManager.get("asteroid64.png");
        Texture garb2 = assetManager.get("roket.png");
        List<Texture> garbL = new ArrayList<>();
        garbL.add(garb1);
        garbL.add(garb2);
        garbAsserts = new GarbAsserts(garbL);

        englAlphAssets = new EnglAlphAssets(alphText);

        controlArrows = new ControlArrows( (Texture) assetManager.get("black_buttons.png") );

        skinAssets = new SkinAssets( (Skin) assetManager.get("skin/craftacular-ui.json") );

        generateHudFont();

        generateResultFont();


    }

    protected java.util.Map<String,BitmapFont> initFonts() {

        Gdx.app.log( "INIT", "Loading fonts..." );

        FileHandle fontFile = Gdx.files.internal("zorque.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        java.util.Map<String,BitmapFont> fontsByName = new HashMap<String,BitmapFont>();
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();

        param.borderColor = Color.BLACK;
        param.borderWidth = 0.5f;
        param.shadowOffsetX = 1;
        param.shadowOffsetY = -1;
        param.shadowColor = Color.BLACK;
        float ppi = Gdx.graphics.getPpiY();
        param.size = ScreensConstants.instance.fontSmallSize;
        fontsByName.put( "small-font", generator.generateFont( param ));

        param.borderColor = Color.BLACK;
        param.borderWidth = 2;
        param.shadowOffsetX = 3;
        param.shadowOffsetY = -3;
        param.shadowColor = Color.BLACK;
        param.size = ScreensConstants.instance.fontGameSize;
        fontsByName.put( "main-font", generator.generateFont( param ));



//        param.size = (int)(Gdx.graphics.getHeight() *Constants.HUD_FONT_INBALLS);
//        fontsByName.put( "inball-font", generator.generateFont( param ));

        generator.dispose();
        return fontsByName;
    }


    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset: " + asset.fileName, throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }


    class TileAssets {
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

    class BrokenAssets {
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

    public class BackAsserts{

        public final Texture backText;
        public final Texture starText;

        public BackAsserts(Texture back, Texture star) {
            backText = back;
            starText = star;
        }
    }

    public class GarbAsserts {

        private List<Texture> garbageList;

          private GarbAsserts (List<Texture> garbageL) {
              garbageList = garbageL;
          }

          public Texture getTexture() {
              double oneProb = 1./garbageList.size();
              DistributedRandomNumberGenerator generator = new DistributedRandomNumberGenerator();
              for (int i = 0; i < garbageList.size(); i++) {
                  generator.addNumber(i,oneProb);
              }
              int lettN = generator.getDistributedRandomNumber();
              return garbageList.get(lettN);
          }
    }

    public class HeroAsserts{

        public final Texture textHero;
        public final Texture textColl;

        public HeroAsserts(Texture h, Texture coll) {
            textColl = coll;
            textHero = h;
        }
    }

    class StarAssets {
        public Texture texture;
        public Texture achieveTexture;
        public Texture menuTexture;
        public StarAssets(Pixmap pixmap) {
//            this.texture = texture;
            Pixmap pixmap_achieve = new Pixmap( ScreensConstants.instance.achieveHeight,
                    ScreensConstants.instance.achieveHeight,
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

    class LockAssets {
        public Texture texture;
        public LockAssets(Texture texture) {
            this.texture = texture;
            Gdx.app.log(TAG,"animation load");
        }
    }

    class SoundsBase {
        public Sound bubbleSound;
        public Sound tookSound;
        public SoundsBase (Sound bubbleSound, Sound tookSound) {
            this.bubbleSound = bubbleSound;
            this.tookSound   = tookSound;
            Gdx.app.log(TAG,"animation load");
        }
    }

    public class EnglAlphAssets {
        private static final int FRAME_COLS = 4; // #1
        private static final int FRAME_ROWS = 7; // #2

        List<TextureRegion> letters; // #5

        List<String> alphabet;

        private EnglAlphAssets (Texture texture) {
            TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth()/FRAME_COLS,
                    texture.getHeight()/FRAME_ROWS); // #10

            alphabet = getCharacters(ENGLISH_ID);
//            walkFrames = new TextureRegion[FRAME_COLS + FRAME_ROWS];
            int index = 0;
            letters = new ArrayList<>();
            for (int i = 0; i < FRAME_ROWS; i++) {
                for (int j = 0; j < FRAME_COLS; j++) {
                    TextureRegion reg = tmp[i][j];
                    letters.add(reg);
                }
            }
            Gdx.app.log(TAG,"animation let load");
        }

        public TextureRegion getLetterTexture (String lett) {
            int id = alphabet.indexOf(lett);
            if (id >= 0 && id < 27 ) {
                return letters.get(id);
            } else {
                IndexOutOfBoundsException exception = new IndexOutOfBoundsException();
                throw exception;
            }
        }
    }

    public class ControlArrows {
        private static final int FRAME_COLS = 2; // #1
        private static final int FRAME_ROWS = 2; // #2

        List<TextureRegion> arrows; // #5


        private ControlArrows (Texture texture) {
            TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth()/FRAME_COLS,
                    texture.getHeight()/FRAME_ROWS); // #10

            int index = 0;
            arrows = new ArrayList<>();
            for (int i = 0; i < FRAME_ROWS; i++) {
                for (int j = 0; j < FRAME_COLS; j++) {
                    TextureRegion reg = tmp[i][j];
                    arrows.add(reg);
                }
            }
            Gdx.app.log(TAG,"animation let load");
        }

        public TextureRegion getLetterTexture (int arrowId) {
            if (arrowId >= 0 && arrowId < 4 ) {
                return arrows.get(arrowId);
            } else {
                IndexOutOfBoundsException exception = new IndexOutOfBoundsException();
                throw exception;
            }
        }
    }



    public class SkinAssets {

        public Skin skin;

        public SkinAssets(Skin skin) {
            this.skin = skin;

        }

    }

    private void generateHudFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("zorque.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = ScreensConstants.instance.fontGameSize;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 2;
        parameter.shadowOffsetX = 3;
        parameter.shadowOffsetY = -3;
        parameter.shadowColor = Color.BLACK;
        hudFont = generator.generateFont(parameter);
    }

    private void generateResultFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("zorque.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = ScreensConstants.instance.fontGameSize;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 2;
        parameter.shadowOffsetX = 3;
        parameter.shadowOffsetY = -3;
        parameter.shadowColor = Color.BLACK;
        resultFont = generator.generateFont(parameter);
    }

}
