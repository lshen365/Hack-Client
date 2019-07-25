package como.taco;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;

import como.taco.GUI.GuiOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;

public class Client {

	/**
	 * Information regarding this build
	 */
	private static Client C1;

	private static int version = 1;

	private static final String Name = "LSux";

	// In-Game Initializer
	private static ingameGUI gui;

	// List of all Hacks
	public static ArrayList<Modules> modList;
	public static ArrayList<Modules> enabledMods;

	public static void init() {
		RenderUtil.initXrayBlocks();
		Display.setTitle(Name + " v. " + version);
		gui = new ingameGUI();
		C1 = new Client();
		modList = new ArrayList<Modules>();
		enabledMods = new ArrayList<Modules>();
		EntityUtil.initFriends();
		addAllMods();
	}

	// Called in GUIIngame
	public static void drawGUI() {
		gui.draw();
	}

	public Client getClient() {
		return C1;
	}

	public int getVersion() {
		return version;
	}

	public static String getName() {
		return Name;
	}

//	public void startClient() {
//	Probably not necessary because I made an initialize function 
//	}

	public static void onKeyPressed(int key) {
		for (Modules mod : modList) {
			mod.onPressed(key);
		}
		checkEnabledModules();
	}

	/**
	 * In the EntityRenderer Class the hook has been placed
	 */
	public static void onRender() {
		for (Modules mod : enabledMods) {
			mod.onRender();
		}
	}

	/**
	 * In the EntityPlayerSP Class the hook has been placed
	 */
	public static void onUpdate() {
		for (Modules mod : enabledMods) {
			mod.onUpdate();
		}
		findDisabledMods();
	}

	public static void findDisabledMods() {
		for (int i = 0; i < enabledMods.size(); i++) {
			if (enabledMods.get(i).getStatus() == false) {
				enabledMods.remove(i);
			}
		}
	}

	public static void checkEnabledModules() {
		for (Modules mod : modList) {
			if (mod.getStatus() == true) {
				enabledMods.add(mod);
			}
			if (mod.getStatus() == false) {
				enabledMods.remove(mod);
			}
		}
	}

	public static boolean checkForModule(Modules m) {
		for (Modules c : enabledMods) {
			if (c.getName().equals(m.getName())) {
				return true;
			}
		}
		return false;
	}

	public static void addAllMods() {
		modList.add(new GuiOverlay());
		modList.add(new Sprint());
		modList.add(new Flight());
		modList.add(new Fullbright());
		modList.add(new MobAura()); // Index spot 4
		modList.add(new FreeCam());
		modList.add(new Speed());
		modList.add(new Criticals());
		modList.add(new LongJump());
		modList.add(new AirStrafe());
		modList.add(new AntiKB());
		modList.add(new Tracer());
		modList.add(new Xray());
	}

	public static Modules getMod(Modules m) {
		for (Modules c : enabledMods) {
			if (c.getName().equals(m.getName())) {
				return c;
			}
		}
		return null;
	}
	
	public static Modules getNonEnabledMod(Modules m) {
		for(Modules c: modList) {
			if(c.getName().equals(m.getName())) {
				return c;
			}
		}
		return null;
	}
	/**
	 * Shuts down the client
	 */
//	public void endClient() {
//		Runtime.getRuntime().addShutdownHook(new Thread() {
//			public void run() {
//				System.out.println("Shutting Down Client");
//			}
//		});
//	}

}
