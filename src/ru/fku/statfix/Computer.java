package ru.fku.statfix;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Computer {
	private @Getter String name = "";
	private @Getter String ekp = "";
	private @Getter String version = "";
	private @Getter @Setter Date dateChange;
	private @Getter ArrayList<Operation> operations = new ArrayList<>();

	public Computer(String name, String version) {
		this.name = name;
		this.ekp = name.substring(0, 5);
		this.version = version;
		this.dateChange = new Date();
	}

	public Computer(String name, String version, Operation operation) {
		this.name = name;
		this.ekp = name.substring(0, 5);
		this.version = version;
		this.operations.add(operation);
		this.dateChange = new Date();
	}

	public Computer(String name, String ekp, String version, String dateChange, ArrayList<Operation> operations) {
		this.name = name;
		this.ekp = ekp;
		this.version = version;
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		try {
			this.dateChange = formater.parse(dateChange);
		} catch (ParseException e) {
			StringWriter stack = new StringWriter();
			e.printStackTrace(new PrintWriter(stack));
			log.error(stack.toString());
		}
		this.operations = operations;
	}

	public void dateUpdate() {
		dateChange = new Date();
	}
}
