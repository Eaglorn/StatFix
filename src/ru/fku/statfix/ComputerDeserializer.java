package ru.fku.statfix;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

class ComputerDeserializer implements JsonDeserializer<Computer> {
    @Override
    public Computer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String name = jsonObject.get("name").getAsString();
        String ekp = jsonObject.get("ekp").getAsString();
        String version = jsonObject.get("version").getAsString();
        String dateChange = jsonObject.get("dateChange").getAsString();
        String fixsString = jsonObject.get("fixs").getAsString();

        ArrayList<String> fixs = new ArrayList<>(Arrays.asList(fixsString.split(", ")));

        return new Computer(name, ekp, version, dateChange, fixs);
    }
}