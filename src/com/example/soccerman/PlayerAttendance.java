package com.example.soccerman;

public enum PlayerAttendance
{
	YES("Yes"), NO("No"), NOT_RESPONDED("Not yet responded");
	
	private final String displayText;
	
	private PlayerAttendance(String display)
	{
		this.displayText = display;
	}
	
	public String getDisplayText()
	{
		return this.displayText;
	}
}
