package at.sno.game.objects;

import org.newdawn.slick.*;

import java.util.ArrayList;

public class EasyGame extends BasicGame {

    private MeinUfo mUfo;
    private ArrayList<MeinUfo> mUfoList;
    private Image background;
    private Crusher crusher;
    public EasyGame() {
        super("EasyGame");
    }
    private Sound sound;
    private Music music;
    private int lautstaerke = 0;
    private int hit =0;
    private int miss =0;
    private AngelCodeFont font;



    public static void main(String[] args) throws SlickException {
        AppGameContainer container = new AppGameContainer(new EasyGame());
        container.setDisplayMode(1024, 768, false);
        //container.setClearEachFrame(false);
        container.setMinimumLogicUpdateInterval(25);
        container.setTargetFrameRate(60);
        container.setShowFPS(true);
        container.start();
    }


    @Override
    public void init(GameContainer container) throws SlickException {
        background = new Image ("assets/pics/background.png");
        mUfoList = new ArrayList<MeinUfo>();
        for( int i=1;i<=10;i++){
            mUfoList.add(new MeinUfo(100,100,new Image("assets/pics/meinufo.png")));
            crusher = new Crusher(512,700,new Image ("assets/pics/crusher.png"),container.getInput());
            music = new Music("testdata/kirby.ogg", true);
            music.loop();
            music.setVolume(1);
            sound = new Sound("testdata/burp.aif");
            font = new AngelCodeFont("testdata/demo2.fnt","testdata/demo2_00.tga");
        }

    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        Input input = container.getInput();

        // Fenster mit ESC scließen
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            container.exit();
        }

        if (input.isKeyPressed(Input.KEY_2)) {
            lautstaerke = lautstaerke + 1;
            if (lautstaerke >= 10) lautstaerke = 10;
            music.setVolume(lautstaerke / 10f);
        }
        if (input.isKeyPressed(Input.KEY_1)) {
            lautstaerke = lautstaerke - 1;
            if (lautstaerke >= 10) lautstaerke = 10;
            music.setVolume(lautstaerke / 10f);
        }


        for (MeinUfo u: mUfoList){

            if (crusher.intersects(u.getShape())) {
                System.out.println("collide");
                sound.play();
                u.setRandomPosition();
                hit = hit +1;
            } if (u.getY() > 768) {
                miss = miss+1;
                u.setRandomPosition();
            }
            u.update(delta);
        }
        crusher.update(delta);



    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        background.draw();
        for (MeinUfo u: mUfoList) u.draw(g);
        crusher.draw(g);
        font.drawString(80, 5, "hits: "+hit , Color.black);
        font.drawString(80, 30, "miss: "+miss , Color.red);

    }


}

