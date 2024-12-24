package ru.fku.statfix;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

class ComputerSerializer implements JsonSerializer<Computer> {
    @Override
    public JsonElement serialize(Computer computer, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", computer.getName());
        jsonObject.addProperty("ekp", computer.getEkp());
        jsonObject.addProperty("version", computer.getVersion());
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        jsonObject.addProperty("dateChange", formater.format(computer.getDateChange()));
        
        for (Operation operation : computer.getOperations()) {
            JsonObject operationObject = new JsonObject();
            operationObject.addProperty("date", operation.getDate().toString());
            operationObject.addProperty("fixes", String.join(", ", operation.getFixs())); // Фиксы на одной строке
            jsonObject.add("operations", operationObject);
        }
        
        return jsonObject;
    }
}
