package com.github.nikandpro.requestWeb.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.nikandpro.modelDB.Food;

import java.io.IOException;

public class FoodSerialize extends StdSerializer<Food> {

    public FoodSerialize() {
        super(Food.class);
    }

    @Override
    public void serialize(Food food, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", Integer.toString(food.getId()));
        jsonGenerator.writeStringField("name", food.getName());
        jsonGenerator.writeStringField("description", food.getDescription());
        jsonGenerator.writeStringField("cookingTime", food.getCookingTime());
        jsonGenerator.writeStringField("have", food.getHave());
        jsonGenerator.writeStringField("portions", food.getPortions());
        jsonGenerator.writeStringField("rating", Integer.toString(food.getRating()));
        jsonGenerator.writeStringField("user", food.getUser().getFname()+" "+food.getUser().getLname());
        jsonGenerator.writeEndObject();
    }
}
