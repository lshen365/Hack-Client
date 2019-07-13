package como.taco;

import como.taco.GUI.ModCategories;

public abstract class Hack extends Modules {

	private ModCategories type;

	public Hack(String name, int key, ModCategories type) {
		super(name, key);
		this.type = type;
	}

	public Hack(String name, ModCategories type) {
		super(name);
		this.type = type;
	}

	public ModCategories getType() {
		return type;
	}

}
