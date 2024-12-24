package ru.fku.statfix;

import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

class ComputerSerializer implements JsonSerializer<Computer> {
	@Override
	public JsonElement serialize(Computer computer, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", computer.getName());
		jsonObject.addProperty("ekp", computer.getEkp());
		jsonObject.addProperty("version", computer.getVersion());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		jsonObject.addProperty("dateChange", formatter.format(computer.getDateChange()));
		JsonArray operationsArray = new JsonArray();
		for (Operation operation : computer.getOperations()) {
			JsonObject operationObject = new JsonObject();
			operationObject.addProperty("date", formatter.format(operation.getDate()));
			operationObject.addProperty("fixs", String.join(", ", operation.getFixs()));
			operationsArray.add(operationObject);
		}
		jsonObject.add("operations", operationsArray);
		return jsonObject;
	}
}
