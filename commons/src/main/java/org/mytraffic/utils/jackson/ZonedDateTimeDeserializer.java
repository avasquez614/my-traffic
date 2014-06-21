package org.mytraffic.utils.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.mytraffic.utils.datetime.DateTimeUtils;

import java.io.IOException;
import java.time.ZonedDateTime;

/**
 * {@link java.time.ZonedDateTime} deserializer for REST.
 *
 * @author avasquez
 * @author mariobarque
 */
public class ZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {

    @Override
    public ZonedDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        return DateTimeUtils.parseDateTime(jp.getText());
    }

}
