package ru.fku.statfix;

public class StatFix {
	private static Server server;
	// private static Client client;

	public static void main(String[] args) {
		/*
		 * String arg = args[0]; if (arg.equals("-server")) { runServer(args[1],
		 * args[2]); }
		 *
		 * if (arg.equals("-client")) { runClient(args[1]); }
		 */
		runServer("c:/statfix/info/", "c:/statfix/", "24.12.4.1");
	}

	/*
	 * private static void runClient(String importPath) { client = new Client(); }
	 */

	private static void runServer(String pathImport, String pathExport, String version) {
		server = new Server(pathImport, pathExport, "24.12.4.1");
		server.run();
	}
}
