package GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mystudio.pong.ComputerPlayer;
import org.mini2Dx.core.graphics.Graphics;

public class Game implements GameObject{

    private Ball [] ball = new Ball[2];
    private Score score;
    private LeftPlatform leftPlatform;
    private RightPlatform rightPlatform;
    private SpawnManager manager;
    private int ballCount = 1;
    boolean gameStart;
    private ComputerPlayer pc;

    @Override
    public void initialise() {
        gameStart = false;
        score = new Score();
        leftPlatform = new LeftPlatform();
        rightPlatform = new RightPlatform();
        manager = new SpawnManager(this);
        ball[0] = new Ball(leftPlatform, rightPlatform, manager, this);
        ball[1] = new Ball(leftPlatform, rightPlatform, manager, this);
        pc = new ComputerPlayer(ball, leftPlatform, rightPlatform, this);
    }

    @Override
    public void update() {
        for(int i = 0; i< ballCount; i++) {
            ball[i].update();
        }
        leftPlatform.update();
        rightPlatform.update();
        manager.update();
        gameStart();
        pc.update();
        pc.update2();
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
        manager.render(g);
    }

    public void preTransitionIn() {
        for(int i = 0; i< ballCount; i++) {
            ball[i].initialise();
        }
        score.initialise();
        leftPlatform.initialise();
        rightPlatform.initialise();

    }

    public void upLeftScore() {
        score.upLeftScore();
        ballCount = 1;
        gameStart = false;
        ball[0].ballReset();
    }

    public void upRightScore() {
        score.upRightScore();
        ballCount = 1;
        gameStart = false;
        ball[0].ballReset();
    }

    public void addBall() {
        ballCount = 2;
        ball[1].initialise();
        ball[1].setGameStart(true);
        ball[1].getBallCollision().set(ball[0].getBallCollision().getX(), ball[0].getBallCollision().getY());
        ball[1].setBallDirection(-ball[0].getBallDirection());
        ball[1].setBallSpeed(ball[0].getBallSpeed()/2);
    }

    public void gameStart(){
        if(!gameStart) {
            if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                gameStart = true;
                ball[0].setGameStart(true);
                manager.initialise();
            }
        }
    }

    public int getBallCount() {
        return ballCount;
    }
}
