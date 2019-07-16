package como.taco;

import como.taco.GUI.ModCategories;

public class Criticals extends Hack {

	public Criticals() {
		super("Criticals", ModCategories.COMBAT);

	}

	public void Crit() {
		if (getStatus()) {
			mc.player.setPosition(mc.player.posX, mc.player.posY + 0.5, mc.player.posZ);
		}
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpdate() {
		
	}

	@Override
	public void onRender() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeVariable(int num) {
		// TODO Auto-generated method stub

	}

}
