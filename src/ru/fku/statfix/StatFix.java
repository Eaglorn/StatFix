package ru.fku.statfix;

import java.io.PrintWriter;
import java.io.StringWriter;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StatFix {
	private static Server server;
	private static Client client;
	private static @Getter @Setter Data data = new Data();

	public static void main(String[] args) {
		try {
			String arg = args[0];
			if (arg.equals("-server")) {
				runServer(args[1], args[2]);
			}



			if (arg.equals("-client")) {
				runClient(args[1]);
			}
		} catch (Exception e) {
			StringWriter stack = new StringWriter();
			e.printStackTrace(new PrintWriter(stack));
			log.error(stack.toString());
		}
	}

	private static void runServer(String pathImport, String pathExport) {
		try {
			server = new Server(pathImport, pathExport);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void runClient(String importPath) {
		try {
			client = new Client();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
