package com.github.nikandpro.requestWeb.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.nikandpro.modelDB.SubTag;
import com.github.nikandpro.tools.Service;

import java.io.IOException;
import java.sql.SQLException;

public class SubTagDeserialize extends StdDeserializer<SubTag> {

    public SubTagDeserialize() {
        super(SubTag.class);
    }

    @Override
    public SubTag deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        System.out.println("show SubTagDeserialize");
        JsonNode node = parser.getCodec().readTree(parser);
        SubTag subTag = new SubTag();
        subTag.setId(0);
        subTag.setName(node.get("name").asText());
        try {
            subTag.setTag(Service.findTag(node.get("tag").asInt()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return subTag;
    }
}
