package com.github.nikandpro.requestWeb.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.nikandpro.modelDB.User;

import java.io.IOException;

public class UserSerialize extends StdSerializer<User> {

    public UserSerialize() {
        super(User.class);
    }

    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", Integer.toString(user.getId()));
        jsonGenerator.writeStringField("fname", user.getFname());
        jsonGenerator.writeStringField("lname", user.getLname());
        jsonGenerator.writeStringField("patron", user.getPatronymic());
        jsonGenerator.writeStringField("mail", user.getMail());
        jsonGenerator.writeStringField("adress", user.getAdress());
        jsonGenerator.writeStringField("rating", user.getUserStatus().name());
        jsonGenerator.writeEndObject();
    }
}
