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
        jsonGenerator.writeNumberField("id", food.getId());
        jsonGenerator.writeStringField("name", food.getName());
        jsonGenerator.writeStringField("description", food.getDescription());
        jsonGenerator.writeStringField("ingredients", food.getIngredients());
        jsonGenerator.writeStringField("cookingTime", food.getCookingTime());
        jsonGenerator.writeStringField("price", food.getPrice());
        jsonGenerator.writeStringField("have", food.getHave());
        jsonGenerator.writeStringField("portions", food.getPortions());
        jsonGenerator.writeNumberField("rating", food.getRating());
        jsonGenerator.writeObjectField("user", food.getUser());
        jsonGenerator.writeEndObject();
    }
}
