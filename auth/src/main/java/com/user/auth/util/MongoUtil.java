package com.user.auth.util;

import com.user.auth.entity.DatabaseSequence;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


public class MongoUtil {

    public static class ObjectIdToLongConverter implements Converter<ObjectId, Long> {
        @Override
        public Long convert(ObjectId source) {
            return Long.valueOf(source.toString().hashCode());
        }
    }
}
