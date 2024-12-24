package ru.fku.statfix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import lombok.Getter;

public class Operation {
	private @Getter ArrayList<String> fixs = new ArrayList<>();
	private @Getter Date date;

	public Operation(Date date) {
		this.date = date;
	}

	public void sort() {
		Collections.sort(fixs, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return Integer.compare(Integer.parseInt(s1), Integer.parseInt(s2));
			}
		});
	}
}
