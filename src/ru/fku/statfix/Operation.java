package ru.fku.statfix;

import java.util.ArrayList;
import java.util.Date;

import lombok.Getter;

public class Operation {
	private @Getter ArrayList<String> fixs = new ArrayList<>();
	private @Getter Date date;
	
	public Operation(Date date) {
		this.date = date;
	}
}
