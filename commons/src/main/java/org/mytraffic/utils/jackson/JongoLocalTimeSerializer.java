package org.mytraffic.utils.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalTime;

/**
 * {@link java.time.LocalTime} serializer for Jongo.
 *
 * @author avasquez
 * @author mariobarque
 */
public class JongoLocalTimeSerializer extends JsonSerializer<LocalTime> {

    @Override
    public Class<LocalTime> handledType() {
        return LocalTime.class;
    }

    @Override
    public void serialize(LocalTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeNumber(value.toNanoOfDay());
    }

}
