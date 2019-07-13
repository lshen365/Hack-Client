package como.taco;

import java.util.ArrayList;

import como.taco.GUI.GuiOverlay;
import net.minecraft.client.Minecraft;

public class Client {

	/**
	 * Information regarding this build
	 */
	private static Client C1;

	private int version = 1;

	private static final String Name = "LSux";

	// In-Game Initializer
	private static ingameGUI gui;

	// List of all Hacks
	public static ArrayList<Modules> modList;
	public static ArrayList<Modules> enabledMods;

	public static void init() {
		gui = new ingameGUI();
		C1 = new Client();
		modList = new ArrayList<Modules>();
		enabledMods = new ArrayList<Modules>();

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
	}

	public static void checkEnabledModules() {
		for (Modules mod : modList) {
			if (mod.getStatus() == true) {
				enabledMods.add(mod);
			}
		}
	}

	public static void addAllMods() {
		modList.add(new GuiOverlay());
		modList.add(new Sprint());
		modList.add(new Flight());
		modList.add(new Fullbright());
		modList.add(new MobAura()); //Index spot 4
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
