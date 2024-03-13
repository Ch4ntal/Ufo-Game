package at.sno.newgame;

import java.util.ArrayList;
import java.util.Iterator;

import at.sno.newgame.nyancat;
import at.sno.newgame.poop;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class TheGame extends BasicGame {
    private poop Mpoop;
    private ArrayList<poop> MpoopList;
    private Image background;
    private nyancat nyancat;
    private Sound sound;
    private Music music;
    private int lautstaerke = 0;
    private int hit = 0;
    private int miss = 0;
    private AngelCodeFont font;

    public TheGame() {
        super("TheGame");
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer container = new AppGameContainer(new at.sno.newgame.TheGame());
        container.setDisplayMode(1024, 768, false);
        container.setMinimumLogicUpdateInterval(25);
        container.setTargetFrameRate(60);
        container.setShowFPS(true);
        container.start();
    }

    public void init(GameContainer container) throws SlickException {
        this.background = new Image("assets/pics/nyan_cat_background.jpg");
        this.MpoopList = new ArrayList();

        for(int i = 1; i <= 10; ++i) {
            this.MpoopList.add(new poop(100, 100, new Image("assets/pics/poop.png")));
            this.nyancat = new nyancat(400, 91, new Image("assets/pics/nyancat.png"), container.getInput());
            this.music = new Music("assets/music/nyancat.wav", true);
            this.music.loop();
            this.music.setVolume(1.0F);
            this.sound = new Sound("assets/sounds/deathsound.wav");
            this.font = new AngelCodeFont("testdata/demo2.fnt", "testdata/demo2_00.tga");
        }

    }

    public void update(GameContainer container, int delta) throws SlickException {
        Input input = container.getInput();
        if (input.isKeyPressed(1)) {
            container.exit();
        }

        if (input.isKeyPressed(3)) {
            ++this.lautstaerke;
            if (this.lautstaerke >= 10) {
                this.lautstaerke = 10;
            }

            this.music.setVolume((float)this.lautstaerke / 10.0F);
        }

        if (input.isKeyPressed(2)) {
            --this.lautstaerke;
            if (this.lautstaerke >= 10) {
                this.lautstaerke = 10;
            }

            this.music.setVolume((float)this.lautstaerke / 10.0F);
        }

        poop u;
        for(Iterator var4 = this.MpoopList.iterator(); var4.hasNext(); u.update(delta)) {
            u = (poop)var4.next();
            if (this.nyancat.intersects(u.getShape())) {
                System.out.println("collide");
                this.sound.play();
                u.setRandomPosition();
                ++this.hit;
            }

            if (u.getX() > 1024) {
                ++this.miss;
                u.setRandomPosition();
            }
        }

        this.nyancat.update(delta);
    }

    public void render(GameContainer container, Graphics g) throws SlickException {
        this.background.draw();
        Iterator var3 = this.MpoopList.iterator();

        while(var3.hasNext()) {
            poop u = (poop)var3.next();
            u.draw(g);
        }

        this.nyancat.draw(g);
        this.font.drawString(80.0F, 5.0F, "hits: " + this.hit, Color.black);
        this.font.drawString(80.0F, 30.0F, "miss: " + this.miss, Color.red);
    }
}

