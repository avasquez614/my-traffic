package org.mytraffic.utils.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalTime;

/**
 * {@link java.time.LocalTime} deserializer for Jongo.
 *
 * @author avasquez
 * @author mariobarque
 */
public class JongoLocalTimeDeserializer extends JsonDeserializer<LocalTime> {

    @Override
    public LocalTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException{
        return LocalTime.ofNanoOfDay(jp.getLongValue());
    }

}
