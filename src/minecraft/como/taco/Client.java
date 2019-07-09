package como.taco;

public class Client {

	/**
	 * Information regarding this build
	 */
	private static Client C1;

	private int version = 1;

	private static final String Name = "LSux";
	
	/**
	 * ingameGUI Initializer
	 */
	private static ingameGUI gui;

	/**
	 * Build info getters
	 */
	
	public static void init() {
		gui = new ingameGUI();
		C1 = new Client();
	}
	
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

	public void startClient() {

	}

	/**
	 * Shuts down the client
	 */
	public void endClient() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				System.out.println("Shutting Down Client");
			}
		});
	}
}
