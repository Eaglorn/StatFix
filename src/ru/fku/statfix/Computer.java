package ru.fku.statfix;

import java.util.ArrayList;

import lombok.Getter;

public class Computer {
	private @Getter String name = "";
	private @Getter String ekp = "";
	private @Getter String version = "";
	private @Getter ArrayList<String> fixs = new ArrayList<>();

	public Computer(String name, String version, ArrayList<String> fixs) {
		this.name = name;
		this.ekp = name.substring(0, 4);
		this.version = version;
		this.fixs = fixs;
	}
}
