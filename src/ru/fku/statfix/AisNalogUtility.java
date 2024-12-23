package ru.fku.statfix;


import java.io.PrintWriter;
import java.io.StringWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AisNalogUtility {
	private static Server server;
	private static Client client;
	
	public static void main(String[] args) {
		try {
			String arg = args[0];
			if (arg.equals("-server")) {
				runServer();
			}
			if (arg.equals("-client")) {
				runClient();
			}
		} catch (Exception e) {
			StringWriter stack = new StringWriter();
			e.printStackTrace(new PrintWriter(stack));
			log.error(stack.toString());
		}
	}

	private static void runServer() {
		try {
			server = new Server();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void runClient() {
		try {
			client = new Client();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
