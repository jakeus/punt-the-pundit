package com.jakeus.ptp.fundraiser;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.badlogic.gdx.utils.Array;
import com.jakeus.ptp.Box2DApplication;
import com.jakeus.ptp.PuntThePundit;

public class FundraiserGameState implements State {
		
	boolean kicked = false;
	private Texture tapTexture;
	private static State theState = new FundraiserGameState();
	
	public static State getState() {		
		return theState;
	}
	private Array<Body> theBodies = new Array<Body>();
	@Override
	public void create(final PuntThePundit ptp) {
    	tapTexture = new Texture("tap.png");
    	
		ptp.app.worldCamera = new OrthographicCamera(16*30, 10*30);        
		ptp.app.worldCamera.position.set(0, 0, 0);
		ptp.app.worldCamera.update();
        
        //box2d init
		ptp.app.world = new World(new Vector2(0, -28f), true);
		ptp.app.batch = new SpriteBatch();        
		ptp.app.renderer = new Box2DDebugRenderer();
        
        
        Pundit p = new Pundit();
        p.theSprite = new Sprite(new Texture("trump.png"));
        p.theSprite.setScale(0.125f);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(50, -40);
        ptp.app.body = ptp.app.world.createBody(bodyDef);   
        PolygonShape shape = new PolygonShape();        
        shape.setAsBox(10, 20);
        
        ptp.app.fixtureDef = new FixtureDef();
        
        ptp.app.fixtureDef.shape = shape;
        ptp.app.fixtureDef.density = 0.01f;
        ptp.app.fixtureDef.restitution = 0.1f;
        
        
        ptp.app.body.createFixture(ptp.app.fixtureDef);
        ptp.app.body.setUserData(p);
        shape.dispose();
        
        
        
        ptp.app.world.setContactListener(new ContactListener() {
			
			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void endContact(Contact contact) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beginContact(Contact contact) {
				// TODO Auto-generated method stub				
				
				if(contact.getFixtureA().getBody().getUserData() != null && contact.getFixtureA().getBody().getUserData() instanceof Pundit
						&& contact.getFixtureB().getBody().getUserData() != null && contact.getFixtureB().getBody().getUserData() instanceof Pole) {
					System.out.println("super boot");
					ptp.app.applySuperForce = 1;
					kicked = true;
				}		
				
			}
		});
        
        
        
        
        BodyDef bodyDefB = new BodyDef();
        bodyDefB.type = BodyDef.BodyType.DynamicBody;
        bodyDefB.position.set(0, -60);
        
        ptp.app.bodyB = ptp.app.world.createBody(bodyDefB);   
        PolygonShape shapeB = new PolygonShape();        
        shapeB.setAsBox(20, 5, new Vector2(0, 0), 0);
        
        FixtureDef fixtureDefB = new FixtureDef();
        
        fixtureDefB.shape = shapeB;
        fixtureDefB.density = 1f;
        fixtureDefB.restitution = 0.1f;
        
        ptp.app.bodyB.createFixture(fixtureDefB);
        Pole p1 = new Pole();
       
        p1.theSprite = new Sprite(new Texture("boot.png"));
         
        p1.theSprite.setScale(0.15f);
        ptp.app.bodyB.setUserData(p1);
        shapeB.dispose();        
        
        
        BodyDef bodyDefPole = new BodyDef();
        bodyDefPole.type = BodyDef.BodyType.DynamicBody;
        bodyDefPole.position.set(0, 60);
        
        ptp.app.bodyPole = ptp.app.world.createBody(bodyDefPole);   
        PolygonShape shapePole = new PolygonShape();        
        shapePole.setAsBox(5, 30, new Vector2(0, -30), 0);
        
        ptp.app.fixtureDefPole = new FixtureDef();
        
        ptp.app.fixtureDefPole.shape = shapePole;
        ptp.app.fixtureDefPole.density = 10000.0f;
        ptp.app.fixtureDefPole.restitution = 0f;
        
        ptp.app.bodyPole.createFixture(ptp.app.fixtureDefPole);

        Pundit p2 = new Pundit();
        p2.theSprite = new Sprite(new Texture("pole.png"));
        p2.theSprite.setScale(0.15f);
        
        ptp.app.bodyPole.setUserData(p2);

        shapePole.dispose();    
        
        
        
        
      
        
        
        
        
        BodyDef bodyDefPivot = new BodyDef();
        bodyDefPivot.type = BodyDef.BodyType.StaticBody;
        bodyDefPivot.position.set(0, -5);
        
        ptp.app.bodyPivot = ptp.app.world.createBody(bodyDefPivot);   
        CircleShape shapePivot = new CircleShape();
        
        shapePivot.setRadius(10f);
        
        FixtureDef fixtureDefPivot = new FixtureDef();
        
        fixtureDefPivot.shape = shapePivot;
        fixtureDefPivot.density = 1.0f;
        fixtureDefPivot.restitution = 0.25f;
        
        ptp.app.bodyPivot.createFixture(fixtureDefPivot);
        

        shapePivot.dispose();    
        
        
        WeldJointDef jointDef = new WeldJointDef();
        jointDef.bodyA = ptp.app.bodyPole;
        jointDef.bodyB = ptp.app.bodyB;
        jointDef.type = JointDef.JointType.WeldJoint;
        jointDef.collideConnected = false;
        ptp.app.world.createJoint(jointDef);
        
        RevoluteJointDef jointDef3 = new RevoluteJointDef();
        jointDef3.bodyA = ptp.app.bodyPole;
        jointDef3.bodyB = ptp.app.bodyPivot;
        jointDef3.type = JointDef.JointType.RevoluteJoint;
        jointDef3.collideConnected = false;
        ptp.app.world.createJoint(jointDef3);        
        
        RevoluteJointDef jointDef5 = new RevoluteJointDef();
        jointDef5.bodyA = ptp.app.bodyB;
        jointDef5.bodyB = ptp.app.bodyPivot;
        jointDef5.type = JointDef.JointType.RevoluteJoint;
        jointDef5.collideConnected = false;
        jointDef5.localAnchorA.x = -10;
        jointDef5.localAnchorA.y = 60;
        ptp.app.world.createJoint(jointDef5);  
        
        
        BodyDef bodyDefGround = new BodyDef();
        bodyDefGround.type = BodyDef.BodyType.StaticBody;
        bodyDefGround.position.set(0, -100);
        ptp.app.groundBody = ptp.app.world.createBody(bodyDefGround);
        
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(1000, 20);

        FixtureDef groundFixture = new FixtureDef();
        groundFixture.shape = groundShape;
        groundFixture.density = 1f;

        ptp.app.groundBody.createFixture(groundFixture);
        //body.setUserData(sprite);
        groundShape.dispose();
        
        
        
        
        
Gdx.input.setInputProcessor(new InputProcessor() {
			
			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				// TODO Auto-generated method stub
				ptp.app.applyForce = 1;				
				ptp.app.bodyPole.setGravityScale(1);
				return false;
			}
			
			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				if(ptp.app.bodyPole.isActive()) {
						float angle = 0.0174533f*(screenX - ptp.app.touchy);
						if(angle <= -2.2) {
							angle = -2.2f;
						}
						if(angle >= 0.2) {
							angle = 0.2f;
						}
						ptp.app.bodyPole.setTransform(ptp.app.bodyPole.getPosition(), angle);

				ptp.app.bodyPole.setGravityScale(0);
				}
				//bodyB.setTransform(bodyB.getPosition(), 0.0174533f*(screenX - touchy));
				return false;
			}
			
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				//System.out.println("X: " + screenX + "   Y: " + screenY);
				ptp.app.touchy = screenX;
				if(ptp.app.tapHim == 2) {
					ptp.app.body.applyForceToCenter(new Vector2(10000000f, 10000000f), false);
					ptp.app.bodyB.applyAngularImpulse(10000f, false);
				}
				return true;
			}
			
			@Override
			public boolean scrolled(int amount) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean keyUp(int keycode) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean keyTyped(char character) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean keyDown(int keycode) {
				// TODO Auto-generated method stub
				return false;
			}
		});		
	}

	@Override
	public void execute(PuntThePundit ptp) {	
        
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                     
        
        ptp.app.renderer.render(ptp.app.world, ptp.app.worldCamera.combined);
        
        ptp.app.batch.setProjectionMatrix(ptp.app.worldCamera.combined);
        
        ptp.app.worldCamera.position.x = ptp.app.body.getPosition().x;
        ptp.app.worldCamera.position.y = ptp.app.body.getPosition().y;
        ptp.app.worldCamera.update();


        //mousePosition.set(mousePositionX, mousePositionY, 0);
        if(ptp.app.applyForce == 1) {
        	ptp.app.bodyB.applyForceToCenter(new Vector2(10000000f, 10000000f), true);
        	ptp.app.counter++;
        	if(ptp.app.counter > 200) {
        		ptp.app.counter = 0;
        		ptp.app.applyForce = 0;
        	}
        }
        if(ptp.app.applySuperForce == 1) {
        	ptp.app.body.applyForceToCenter(new Vector2(100000000f, 100000000f), true);
        	System.out.println(ptp.app.body.getAngularVelocity());

        	//ptp.app.bodyB.applyAngularImpulse(1f, true);
        	ptp.app.counter2++;
        	if(ptp.app.counter2 > 100) {
        		ptp.app.counter2 = 0;
        		ptp.app.applySuperForce = 0;
        	}
        	ptp.app.tapHim = 1;
        }
    		
        
        if(ptp.app.tapHim == 1 && ptp.app.body.getLinearVelocity().y < 0) {
        	ptp.app.tapHim++;
        	System.out.println("tap him!");        	
        }
        //bodyPole.applyForceToCenter(new Vector2(10000000f, 0), true);
        //worldCamera.unproject(mousePosition);    
        
        ptp.app.batch.begin();  
        ptp.app.world.getBodies(theBodies);
        theBodies.reverse();
        
        for(Body b : theBodies) {

        	Sprite s = null;
        	if(b.getUserData() != null && b.getUserData() instanceof Pundit) {
        		Pundit p = (Pundit) b.getUserData();
        		s = p.theSprite;
        	}
        	else if(b.getUserData() != null && b.getUserData() instanceof Pole) {
        		Pole p = (Pole) b.getUserData();
        		s = p.theSprite;
        	}
        	
        	if(null != s) {        		
        		ptp.app.batch.draw(s, 
        				b.getPosition().x-s.getWidth()/2, 
        				b.getPosition().y-s.getHeight()/2,
        				s.getWidth()/2, 
        				s.getHeight()/2, 
        				s.getWidth(), 
        				s.getHeight(), 
        				s.getScaleX(), 
        				s.getScaleY(), 
        				b.getAngle()*180/3.14f);                
        	}               	
        }
        
        if(ptp.app.tapHim == 2) {
        	ptp.app.batch.draw(tapTexture, ptp.app.worldCamera.position.x-tapTexture.getWidth()/8, ptp.app.worldCamera.position.y-100, tapTexture.getWidth()/4, tapTexture.getHeight()/4);
        }
        ptp.app.batch.end();  
        

        if(kicked) {
        	ptp.app.body.setAngularVelocity(2.0f);
        }
        
        ptp.app.world.step(1/30f, 1, 2);        
	}
}
