package com.github.nikandpro.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.nikandpro.modelDB.User;
import com.github.nikandpro.requestWeb.deserialize.UserDeserialize;
import com.github.nikandpro.requestWeb.serialize.UserSerialize;

public class ObjectMapperFactory {

    public static ObjectMapper createObjectMapper(Class<?> nameClass) {
        SimpleModule sm = new SimpleModule();
        ObjectMapper om = new ObjectMapper();
        if (User.class == nameClass) {
            sm.addSerializer(User.class, new UserSerialize());
            sm.addDeserializer(User.class, new UserDeserialize());
        }
        return om.registerModule(sm);
    }
}
