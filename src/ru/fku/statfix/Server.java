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
	private String version;
	private Data data;

	public Server(String pathImport, String pathExport, String version) {
		this.pathImport = pathImport;
		this.pathExport = pathExport;
		this.version = version;
	}

	public void run() {
		data = Data.load(pathExport, version);
		boolean isChange = false;
		if (data != null) {
			File folder = new File(pathImport);
			File[] files = folder.listFiles();
			if (files != null) {
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
				isChange = true;
			}
			if (isChange) {
				data.save(pathExport);
			}
		}
	}
}
