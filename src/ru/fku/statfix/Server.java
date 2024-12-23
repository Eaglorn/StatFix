package ru.fku.statfix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {
	private String pathImport;
	private String pathExport;
	private Data data;

	public Server(String pathImport, String pathExport) {
		this.pathImport = pathImport;
		this.pathExport = pathExport + "statfix.json";

		if (Files.exists(Paths.get(this.pathExport))){
			try {
				StatFix.setData(new Gson().fromJson(new JsonReader(new FileReader(pathExport)), Data.class));
				this.data = StatFix.getData();
			} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
				StringWriter stack = new StringWriter();
				e.printStackTrace(new PrintWriter(stack));
				log.error(stack.toString());
			}
		}
		run();
	}

	private void run() {
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
	}
}
