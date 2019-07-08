package como.taco;

public class Client {

	/**
	 * Information regarding this build
	 */
	private static final Client C1 = new Client();

	private int version = 1;

	private final String Name = "LSux";

	/**
	 * Build info getters
	 */
	
	public static void initalizeGUI() {
<<<<<<< HEAD
		System.out.println("Test");
=======
		System.out.println("Cancer");
>>>>>>> 8a4251dc7e9f60972d8e02346e636f7ed08e1047
	}
	public Client getClient() {
		return C1;
	}

	public int getVersion() {
		return version;
	}

	public String getName() {
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
