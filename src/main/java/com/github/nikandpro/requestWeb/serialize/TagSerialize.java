package com.github.nikandpro.requestWeb.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.nikandpro.modelDB.SubTag;
import com.github.nikandpro.modelDB.Tag;
import com.github.nikandpro.tools.Service;

import java.io.IOException;
import java.sql.SQLException;

public class TagSerialize extends StdSerializer<Tag> {

    public TagSerialize() {
        super(Tag.class);
    }

    @Override
    public void serialize(Tag tag, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        System.out.println("check TagSerializerr");
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", tag.getId());
        jsonGenerator.writeStringField("title", tag.getTitle());
        jsonGenerator.writeArrayFieldStart("subTags");
        try {
            for (SubTag subTag : Service.listSubtag(tag)) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("id", subTag.getId());
                jsonGenerator.writeStringField("name", subTag.getName());
                jsonGenerator.writeEndObject();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
