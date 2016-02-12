package com.jakeus.ptp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.jakeus.ptp.fundraiser.FundraiserGameState;
import com.jakeus.ptp.fundraiser.State;

public class Box2DApplication extends ApplicationAdapter {
    public SpriteBatch batch;
    public Vector3 mousePosition;
    public OrthographicCamera worldCamera;
    public State currentState;
    //public Box2D
    public World world;
    public Box2DDebugRenderer renderer;
    public Body body;
    public Body bodyB;
    public Body bodyPole;
    public Body bodyPivot;
    public Body groundBody;

    public int touchy;
    public int applyForce = 0;
    public int applySuperForce = 0;
    public int counter = 0;
    public int counter2 = 0;
    public FixtureDef fixtureDef;
    public FixtureDef fixtureDefPole;
    public int tapHim = 0;    
}
