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

		System.out.println("Test");

		System.out.println("Cancer");
		System.out.println("Noobie");
		
		System.out.println("Libtard");


		System.out.println("Test");

		System.out.println("Cancer");
		
		System.out.println("Leon is Racist");


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
