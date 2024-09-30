package fqf.qua_mario.characters;

import java.util.EnumMap;

public class CharaMario extends Character {
	public static final CharaMario INSTANCE = new CharaMario();
	private CharaMario() {
		this.name = "Mario";
		this.fullName = "Mario Mario";

		this.statFactors = new EnumMap<>(CharaStat.class);
	}

	@Override
	public String getSoundPrefix() {
		return "mario_";
	}
}
