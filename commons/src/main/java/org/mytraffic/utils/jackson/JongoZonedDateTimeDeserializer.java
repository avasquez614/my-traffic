package org.mytraffic.utils.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.ZonedDateTime;

/**
 * {@link java.time.ZonedDateTime} deserializer for Jongo.
 *
 * @author avasquez
 * @author mariobarque
 */
public class JongoZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {

    @Override
    public ZonedDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException{
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(jp.getLongValue()), ctxt.getTimeZone().toZoneId());
    }

}
