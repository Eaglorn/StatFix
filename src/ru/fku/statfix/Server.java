package ru.fku.statfix;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {
	private String pathImport;
	private String pathExport;
	private Data data = new Data();

	public Server(String pathImport, String pathExport) {
		this.pathImport = pathImport;
		this.pathExport = pathExport;
	}

	public void run() {
		data = Data.load(pathExport);
		if(data != null) {
			File folder = new File(pathImport);
			File[] files = folder.listFiles();
			for (File file : files) {
			    if (file.isFile()) {
			    	String fileContent = "";
						try {
							fileContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
						} catch (IOException e) {
							StringWriter stack = new StringWriter();
							e.printStackTrace(new PrintWriter(stack));
							log.error(stack.toString());
						}
			        data.initComputer(file.getName(), fileContent);
			    }
			}
			data.save(pathExport);
		}
	}
}
