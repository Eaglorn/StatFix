package ru.fku.statfix;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import lombok.Getter;

public class Operation {
	private @Getter ArrayList<String> fixs = new ArrayList<>();
	private @Getter LocalDateTime date;
	private @Getter String version = "";

	public Operation(LocalDateTime date, String version) {
		this.date = date;
		this.version = version;
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
