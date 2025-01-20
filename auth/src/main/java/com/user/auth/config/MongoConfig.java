package com.user.auth.config;

import com.user.auth.util.MongoUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;

public class MongoConfig extends AbstractMongoClientConfiguration {


    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new MongoUtil.ObjectIdToLongConverter()
        ));
    }
    @Override
    protected String getDatabaseName() {
        return "user_auth";
    }
}
