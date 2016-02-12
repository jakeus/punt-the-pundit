package com.jakeus.ptp.fundraiser;

import com.jakeus.ptp.PuntThePundit;

public interface State {	
	public void create(PuntThePundit ptp);
	public void execute(PuntThePundit ptp);
}
