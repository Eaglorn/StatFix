package ru.fku.statfix;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

public class Computer {
	private @Getter String name = "";
	private @Getter String ekp = "";
	private @Getter @Setter String version = "";
	private @Getter @Setter LocalDateTime dateChange;
	private @Getter ArrayList<Operation> operations = new ArrayList<>();

	public Computer(String name, String version) {
		this.name = name;
		this.ekp = name.substring(0, 5);
		this.version = version;
		this.dateChange = LocalDateTime.now();
	}

	public Computer(String name, String version, Operation operation) {
		this.name = name;
		this.ekp = name.substring(0, 5);
		this.version = version;
		this.operations.add(operation);
		this.dateChange = LocalDateTime.now();
	}

	public Computer(String name, String ekp, String version, String dateChange, ArrayList<Operation> operations) {
		this.name = name;
		this.ekp = ekp;
		this.version = version;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		this.dateChange = LocalDateTime.parse(dateChange, formatter);
		this.operations = operations;
	}

	public void dateUpdate() {
		dateChange = LocalDateTime.now();
	}
}
