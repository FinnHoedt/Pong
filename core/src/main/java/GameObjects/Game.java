package GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mystudio.pong.ComputerPlayer;
import com.mystudio.pong.Settings;
import org.mini2Dx.core.graphics.Graphics;

public class Game implements GameObject{

    private Ball [] ball = new Ball[2];
    private Score score;
    private LeftPlatform leftPlatform;
    private RightPlatform rightPlatform;
    private SpawnManager spawnManager;
    private int ballCount = 1;
    private boolean gameStart;
    private ComputerPlayer pc;

    @Override
    public void initialise() {
        gameStart = false;
        score = new Score();
        leftPlatform = new LeftPlatform();
        rightPlatform = new RightPlatform();
        spawnManager = new SpawnManager(this);
        spawnManager.initialise();
        ball[0] = new Ball(leftPlatform, rightPlatform, spawnManager, this);
        ball[1] = new Ball(leftPlatform, rightPlatform, spawnManager, this);
        pc = new ComputerPlayer(ball, leftPlatform, rightPlatform, this);
    }

    @Override
    public void update() {
        for(int i = 0; i< ballCount; i++) {
            ball[i].update();
        }
        leftPlatform.update();
        rightPlatform.update();
        spawnManager.update();
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
        spawnManager.render(g);
    }

    public void preTransitionIn(Settings settings) {
        for(int i = 0; i< ballCount; i++) {
            ball[i].initialise();
        }
        score.initialise();
        leftPlatform.initialise();
        leftPlatform.changeKeybinds(settings.getLeftPlatformUp(), settings.getLeftPlatformDown());
        leftPlatform.changeColor(settings.getLeftPlatformColor());
        rightPlatform.initialise();
        rightPlatform.changeKeybinds(settings.getRightPlatformUp(), settings.getRightPlatformDown());
        rightPlatform.changeColor(settings.getRightPlatformColor());
        resetGame();
    }

    public void upLeftScore() {
        score.upLeftScore();
        resetGame();
    }

    public void upRightScore() {
        score.upRightScore();
        resetGame();
    }

    private void resetGame() {
        ballCount = 1;
        gameStart = false;
        ball[0].ballReset();
        spawnManager.initialise();
        leftPlatform.resetHeight();
        rightPlatform.resetHeight();
        spawnManager.setSpawn(gameStart);
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
                spawnManager.setSpawn(gameStart);
            }
        }
    }

    public Boolean getGameStart() {
        return gameStart;
    }

    public int getBallCount() {
        return ballCount;
    }
}
