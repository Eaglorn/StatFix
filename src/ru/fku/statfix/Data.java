package ru.fku.statfix;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.FormattingStyle;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Data {
	public static Data load (String pathExport) {
		if (Files.exists(Paths.get(pathExport + "info.json"))){
			try {
				Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm:ss").create();
				Data data = gson.fromJson(new JsonReader(new FileReader(pathExport + "info.json")), Data.class);
				return data;
			} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
				StringWriter stack = new StringWriter();
				e.printStackTrace(new PrintWriter(stack));
				log.error(stack.toString());
			}
			return null;
		} else {
			return new Data();
		}
	}

	private @Getter ArrayList<Computer> computers = new ArrayList<>();

	public Computer findComputerByName(String name) {
        for (Computer computer : computers) {
            if (computer.getName().equalsIgnoreCase(name)) {
                return computer;
            }
        }
        return null;
    }

	public void initComputer(String name, String value) {
        String[] parts = value.split(" ");
        ArrayList<String> listParts = new ArrayList<>(Arrays.asList(parts));
        String version = listParts.get(0);
        ArrayList<String> listFixs = new ArrayList<>();
        if(!listParts.isEmpty()) {
        	for(String part: listParts) {
        		if(!part.equals(version)) {
        			listFixs.add(part);
        		}
        	}
        }
		Computer computer = findComputerByName(name);
		if(computer != null) {
			if(!version.equals(computer.getVersion())) {
				computer.getOperations().clear();
				if(listFixs.size() != 0) {
					Operation operation = new Operation(new Date());
					for(String fix : listFixs) {
						operation.getFixs().add(fix);
					}
					computer.getOperations().add(operation);
				}
			} else {
				ArrayList<Operation> operations = computer.getOperations();
				ArrayList<String> fixs = new ArrayList<>();
				
				for(Operation operation: operations) {
					for(String fix : operation.getFixs()) {
						fixs.add(fix);
					}
				}
				
				if(listFixs.size() != 0) {
					Operation operation = new Operation(new Date());
					for(String fix : listFixs) {
						if(!fixs.contains(fix)) {
							operation.getFixs().add(fix);
						}
					}
					computer.getOperations().add(operation);
				}
			}

			computer.dateUpdate();
		} else {
			if(listFixs.size() != 0) {
				Operation operation = new Operation(new Date());
				for(String fix : listFixs) {
					operation.getFixs().add(fix);
				}
				computers.add(new Computer(name, version, operation));
			} else {
				computers.add(new Computer(name, version));
			}
			
		}
	}

	public void save(String pathExport) {
		try (FileWriter file = new FileWriter(pathExport + "info.json")) {
			Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm:ss").create();
			file.write(gson.toJson(this, Data.class));
			file.flush();
		} catch (IOException e) {
			StringWriter stack = new StringWriter();
			e.printStackTrace(new PrintWriter(stack));
			log.error(stack.toString());
		}
		try (FileWriter file = new FileWriter(pathExport + "infoPretty.json")) {
			Gson gson = new GsonBuilder().setFormattingStyle(FormattingStyle.PRETTY).registerTypeAdapter(Computer.class, new ComputerSerializer()).create();
			file.write(gson.toJson(this, Data.class));
			file.flush();
		} catch (IOException e) {
			StringWriter stack = new StringWriter();
			e.printStackTrace(new PrintWriter(stack));
			log.error(stack.toString());
		}
	}
}
