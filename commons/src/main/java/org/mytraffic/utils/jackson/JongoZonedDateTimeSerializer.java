package org.mytraffic.utils.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.ZonedDateTime;

/**
 * {@link java.time.ZonedDateTime} serializer for Jongo.
 *
 * @author avasquez
 * @author mariobarque
 */
public class JongoZonedDateTimeSerializer extends JsonSerializer<ZonedDateTime> {

    @Override
    public Class<ZonedDateTime> handledType() {
        return ZonedDateTime.class;
    }

    @Override
    public void serialize(ZonedDateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeNumber(value.toInstant().toEpochMilli());
    }

}
