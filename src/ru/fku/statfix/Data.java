package ru.fku.statfix;

import java.util.ArrayList;
import java.util.Arrays;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Data {
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
			ArrayList<String> fixs = computer.getFixs();

			if(!version.equals(computer.getVersion())) {
				computer.getFixs();

				for(String fix : listFixs) {
					fixs.add(fix);
				}
			} else {
				for(String fix : listFixs) {
					if(!fixs.contains(fix)) {
						fixs.add(fix);
					}
				}
			}
		} else {
			computers.add(new Computer(name, version, listFixs));
		}
	}
}
