package ru.fku.statfix;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class Computer {
	private @Getter String name = "";
	private @Getter String ekp = "";
	private @Getter String version = "";
	private @Getter @Setter Date dateChange;
	private @Getter ArrayList<String> fixs = new ArrayList<>();

	public Computer(String name, String version, ArrayList<String> fixs) {
		this.name = name;
		this.ekp = name.substring(0, 5);
		this.version = version;
		this.fixs = fixs;
		this.dateChange = new Date();
	}

	public Computer(String name, String ekp, String version, String dateChange, ArrayList<String> fixs) {
		this.name = name;
		this.ekp = ekp;
		this.version = version;
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		try {
			this.dateChange = formater.parse(dateChange);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.fixs = fixs;
	}

	public void dateUpdate() {
		dateChange = new Date();
	}
}
