package com.github.nikandpro.requestWeb.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.nikandpro.modelDB.User;
import com.github.nikandpro.modelDB.statuses.UserStatus;
import com.github.nikandpro.tools.SecurityService;
import com.github.nikandpro.tools.Service;

import java.io.IOException;

public class UserDeserialize extends StdDeserializer<User> {

    public UserDeserialize() {
        super(User.class);
    }

    @Override
    public User deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = parser.getCodec().readTree(parser);
        User user = new User();
        user.setId(0);
        user.setLname("");
        user.setFname("");
        user.setPatronymic("");
        user.setAdress("");
        user.setMail(node.get("mail").asText());
        user.setPassword(SecurityService.encryption(node.get("password").asText()));
        user.setUserStatus(UserStatus.valueOf(node.get("status").asText()));

        return user;
    }
}
