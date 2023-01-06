package com.mystudio.pong;

import org.mini2Dx.core.graphics.Graphics;

public class PowerUp implements GameObject{ // müsste abstract sein
	
	Sprite sprite; 
	Vector2 position;
	float size;
	
	public void spawn(){};
	
	public void applyPowerUp(){};
	
    @Override
    public void initialise() {

    }

    @Override
    public void update() {

    }

    @Override
    public void interpolate(float alpha) {

    }

    @Override
    public void render(Graphics g) {

    }
    
    
}
