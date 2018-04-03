package com.bfwg.repository.serializer;

import com.bfwg.model.Currency;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CurrencySerializer extends StdSerializer<Currency> {

    public CurrencySerializer() {
        super(Currency.class);
    }

    @Override
    public void serialize(Currency currency, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("id");
        jsonGenerator.writeNumber(currency.getId());
        jsonGenerator.writeFieldName("name");
        jsonGenerator.writeString(currency.getName());
        jsonGenerator.writeEndObject();
    }
}
