package com.github.nikandpro.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.nikandpro.modelDB.Food;
import com.github.nikandpro.modelDB.SubTag;
import com.github.nikandpro.modelDB.Tag;
import com.github.nikandpro.modelDB.User;
import com.github.nikandpro.requestWeb.deserialize.FoodDeserialize;
import com.github.nikandpro.requestWeb.deserialize.SubTagDeserialize;
import com.github.nikandpro.requestWeb.deserialize.UserDeserialize;
import com.github.nikandpro.requestWeb.serialize.FoodSerialize;
import com.github.nikandpro.requestWeb.serialize.TagSerialize;
import com.github.nikandpro.requestWeb.serialize.UserSerialize;

public class ObjectMapperFactory {

    public static ObjectMapper createObjectMapper(Class<?> nameClass) {
        SimpleModule sm = new SimpleModule();
        ObjectMapper om = new ObjectMapper();
        if (User.class == nameClass) {
            sm.addSerializer(User.class, new UserSerialize());
            sm.addDeserializer(User.class, new UserDeserialize());
        } else if (Food.class == nameClass) {
            sm.addSerializer(Food.class, new FoodSerialize()).addSerializer(User.class, new UserSerialize());
            sm.addDeserializer(Food.class, new FoodDeserialize());
        } else if (Tag.class == nameClass) {
            sm.addSerializer(Tag.class, new TagSerialize());
        } else if (SubTag.class == nameClass) {
            sm.addDeserializer(SubTag.class, new SubTagDeserialize());
        }
        return om.registerModule(sm);
    }
}
