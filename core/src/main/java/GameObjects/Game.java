package GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mystudio.pong.Collision;
import com.mystudio.pong.ComputerPlayer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.Transition;
import sun.jvm.hotspot.gc.shared.Space;

import java.security.Key;

public class Game implements GameObject {

    private Ball ball[] = {new Ball(), new Ball()};
    private Score score;
    private LeftPlatform leftPlatform;
    private RightPlatform rightPlatform;
    private Collision collision;

    private SpawnHandler spawnHandler;
    private int ballCount = 1;
    private Boolean start;

    private ComputerPlayer pc;

    public Game() {
        score = new Score();
        leftPlatform = new LeftPlatform();
        rightPlatform = new RightPlatform();
        spawnHandler = new SpawnHandler(this);
        collision = new Collision(leftPlatform, rightPlatform, ball, score, spawnHandler, this);

        //pc = new ComputerPlayer(ball, rightPlatform, this);
    }

    @Override
    public void initialise() {
        start = true;
    }

    @Override
    public void update() {
        for(int i = 0; i< ballCount; i++) {
            ball[i].update();
        }
        leftPlatform.update();
        rightPlatform.update();
        spawnHandler.update();
        collision.checkCollision();
        spawnHandler.update();
        if(start) {
            gameStart();
        }

        //pc.update();
    }

    @Override
    public void interpolate(float alpha) {
        for(int i = 0; i< ballCount; i++) {
            ball[i].interpolate(alpha);
        }
        leftPlatform.interpolate(alpha);
        rightPlatform.interpolate(alpha);
    }

    @Override
    public void render(Graphics g) {
        score.render(g);
        for(int i = 0; i< ballCount; i++) {
            ball[i].render(g);
        }
        leftPlatform.render(g);
        rightPlatform.render(g);
        spawnHandler.render(g);
    }

    public void addBall() {
        ballCount = 2;
        ball[1].initialise();
        //ball[1].setGameStartTrue();
        collision.addBall();
    }

    public void removeBall() {
        ballCount = 1;
    }

    public int getBallCount() {
        return ballCount;
    }

    public void preTransitionIn() {
        for(int i = 0; i< ballCount; i++) {
            ball[i].initialise();
        }
        ball[0].raiseSpeed(2);
        score.initialise();
        leftPlatform.initialise();
        rightPlatform.initialise();
    }

    public void gameStart() {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            ball[0].setGameStart();
            spawnHandler.initialise();
            start = false;
        }
    }

    public void setStart() {
        start = true;
    }
}
