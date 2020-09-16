package com.github.nikandpro.requestWeb.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.nikandpro.modelDB.Food;
import com.github.nikandpro.modelDB.User;
import com.github.nikandpro.modelDB.statuses.UserStatus;
import com.github.nikandpro.tools.SecurityService;

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

        return food;
    }
}
