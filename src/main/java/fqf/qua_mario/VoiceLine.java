package fqf.qua_mario;

public enum VoiceLine {
	SIDEFLIP("sideflip");

	private final String EVENT_NAME;
	VoiceLine(String event_name) {
		this.EVENT_NAME = event_name;
	}
	public String getEventName() {
		return EVENT_NAME;
	}
}
