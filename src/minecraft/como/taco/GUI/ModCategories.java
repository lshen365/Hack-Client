package como.taco.GUI;

import como.taco.Client;
import como.taco.Hack;
import como.taco.Modules;

public enum ModCategories {
	RENDER, WORLD, MOVEMENT, COMBAT, OTHER;

	public static int size(ModCategories c) {
		int i = 0;
		for (Modules m : Client.modList) {
			if (m instanceof Hack) {
				if (((Hack) m).getType().equals(c))
					i++;
			}
		}
		return i;
	}

	public static int placeInList(Hack m, ModCategories mc) {
		int i = 0;
		for (Modules c : Client.modList) {
			if (c instanceof Hack) {
				if (((Hack) c).getType().equals(mc) && !c.equals(m)) {
					i++;
					continue;
				}
				if (((Hack) c).getType().equals(mc) && c.equals(m)) {
					return i;
				}
			}
		}

		return 0;
	}
}
