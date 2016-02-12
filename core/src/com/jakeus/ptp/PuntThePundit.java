package com.jakeus.ptp;

import com.badlogic.gdx.ApplicationAdapter;
import com.jakeus.ptp.fundraiser.FundraiserGameState;
import com.jakeus.ptp.fundraiser.State;

public class PuntThePundit extends ApplicationAdapter {

	State currentState;
	public Box2DApplication app = new Box2DApplication();
	
	public void setState(State state, boolean create) {		
		currentState = state;
		if(create) {
			currentState.create(this);
		}
	}
	
    @Override
    public void create() {      
    	setState(FundraiserGameState.getState(), true);
    }

    @Override
    public void render() {               
        currentState.execute(this);
    }
}
