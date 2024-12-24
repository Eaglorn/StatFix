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
        
        jsonObject.addProperty("fixs", String.join(", ", computer.getFixs()));
        
        return jsonObject;
    }
}
