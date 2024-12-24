package ru.fku.statfix;

public class StatFix {
	private static Server server;
	//private static Client client;

	public static void main(String[] args) {
		/*String arg = args[0];
		if (arg.equals("-server")) {
			runServer(args[1], args[2]);
		}

		if (arg.equals("-client")) {
			runClient(args[1]);
		}*/
		long startTime = System.currentTimeMillis();
		runServer("c:/statfix/info/", "c:/statfix/");
		long endTime = System.currentTimeMillis(); // Конец измерения времени
        long duration = endTime - startTime;
        System.out.println("Время выполнения: " + duration + " миллисекунд");
	}

	/*private static void runClient(String importPath) {
		client = new Client();
	}*/

	private static void runServer(String pathImport, String pathExport) {
			server = new Server(pathImport, pathExport);
			server.run();
	}
}
