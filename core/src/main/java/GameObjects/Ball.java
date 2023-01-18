package GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.mini2Dx.core.engine.geom.CollisionCircle;
import org.mini2Dx.core.graphics.Graphics;

import java.util.Random;

public class Ball implements GameObject{

    private CollisionCircle ballCollision;
    private Random rand;

    //private Sprite sprite;
    private float ballPosX, ballPosY, ballDirection, ballPlusX, ballPlusY, ballSpeedStart, ballDiameter;
    private float ballSpeed;
    boolean gameStart, lastPoint;

    /**
     * ball is initialized
     */
    @Override
    public void initialise() {
        gameStart = false;
        ballDiameter = 10;
        ballPosX = Gdx.graphics.getWidth()/2;
        ballPosY = Gdx.graphics.getHeight()/2;
        ballSpeedStart = 8;
        ballSpeed = ballSpeedStart;
        ballDirection = -1f;
        rand = new Random();
        ballCollision = new CollisionCircle(ballPosX, ballPosY, ballDiameter);

        //sprite = new Sprite(new Texture(Gdx.files.internal("pongBall.png")));
    }

    /**
     * ball resets after point
     */
    public void ballReset(){
        gameStart = false;
        ballPosX = Gdx.graphics.getWidth()/2;
        ballPosY = Gdx.graphics.getHeight()/2;
        ballCollision.set(ballPosX, ballPosY);
        speedDirection();
        randomBallDirection();

    }

    /**
     * ball is updated
     */
    @Override
    public void update() {
        ballCollision.preUpdate();
        //ballStart();
        if(gameStart) {
            calcNewPos();
        }
    }

    /**
     * ball is updated
     */
    @Override
    public void interpolate(float alpha) {
        ballCollision.interpolate(null, alpha);
    }

    /**
     * ball is rendered
     */
    @Override
    public void render(Graphics g) {
        g.fillCircle(ballCollision.getRenderX(), ballCollision.getRenderY(), ballCollision.getRenderRadius());
        //g.drawSprite(sprite, ballCollision.getRenderX(), ballCollision.getRenderY());
    }

    /**
     * test if space is pressed to start the game
     */
    public void ballStart(){
        if(!gameStart) {
            if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                gameStart = true;
            }
        }
        if(gameStart) {
            calcNewPos();
        }
    }

    /**
     * calculates new position from ball
     */
    public void calcNewPos(){
        int sign;
        if(ballDirection == 0) {
            sign = 1;
        }
        else {
            sign = (int) (ballDirection / Math.abs(ballDirection));
        }
        ballPlusX = (float) (1 / (Math.sqrt(Math.pow(ballDirection, 2) + 1)));
        ballPlusY = (float) (Math.sqrt(-Math.pow(ballPlusX, 2) + 1) * sign);
        ballPlusX = ballPlusX * ballSpeed;
        ballPlusY = ballPlusY * Math.abs(ballSpeed);
        ballCollision.set(ballCollision.getX() + ballPlusX, ballCollision.getY() + ballPlusY);
    }

    /**
     * changes vertical direction of ball
     */
    public void changeVerticalDirection() {
        ballDirection = -ballDirection;
    }

    /**
     * changes horizontal direction of ball
     */
    public void changeHorizontalDirection() {
        ballSpeed = -ballSpeed;
        randomBallDirection();
    }

    /**
     * gives the ball a random direction
     */
    public void randomBallDirection(){
        ballDirection = (rand.nextFloat() - 0.5f) * 2.5f;
    }

    /**
     * get the collision of the ball
     */
    public CollisionCircle getBallCollision() {
        return ballCollision;
    }

    /**
     * get the direction of the ball after reset based on the last point of a player
     */
    private void speedDirection(){
        if(lastPoint){
            ballSpeed = ballSpeedStart * (1);
        }
        else{
            ballSpeed = ballSpeedStart * (-1);
        }
    }

    /**
     * get the sign of the speed from the ball
     */
    private float getBallSpeedSign(){
        return Math.abs(ballSpeed)/ballSpeed;
    }

    /**
     * set who got the last point
     */
    public void setLastPoint(boolean lastPoint){
        this.lastPoint = lastPoint;
    }

    /**
     * raises speed of the ball
     */
    public void raiseSpeed(float plusSpeed){
        ballSpeed += getBallSpeedSign() * plusSpeed;
    }

    public void setBallDirection(float ballDirection) {
        this.ballDirection = ballDirection;
    }

    public float getBallDirection() {
        return ballDirection;
    }

    public void setBallSpeed(float ballSpeed) {
        this.ballSpeed = ballSpeed;
    }

    public float getBallSpeed() {
        return ballSpeed;
    }

    public void setGameStart() {
        gameStart = true;
    }


}
