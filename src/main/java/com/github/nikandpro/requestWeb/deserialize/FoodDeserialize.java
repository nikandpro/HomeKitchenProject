package com.github.nikandpro.requestWeb.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.nikandpro.modelDB.Food;

import java.io.IOException;

public class FoodDeserialize extends StdDeserializer<Food> {

    public FoodDeserialize() {
        super(Food.class);
    }

    @Override
    public Food deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = parser.getCodec().readTree(parser);
        Food food = new Food();
        food.setId(0);
        food.setName(node.get("name").asText());
        food.setDescription(node.get("description").asText());
        food.setIngredients(node.get("ingredients").asText());
        food.setCookingTime(node.get("cookingTime").asText());
        food.setPrice(node.get("price").asText());
        food.setHave(node.get("have").asText());
        food.setPortions(node.get("portion").asText());
        food.setLocation(node.get("location").asText());

        return food;
    }
}
