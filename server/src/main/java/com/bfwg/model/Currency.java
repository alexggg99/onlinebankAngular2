package com.bfwg.model;

import com.bfwg.repository.serializer.CurrencyDeserializer;
import com.bfwg.repository.serializer.CurrencySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@JsonSerialize(using = CurrencySerializer.class)
@JsonDeserialize(using = CurrencyDeserializer.class)
public enum Currency {

    EUR(1, "EUR"), USD(2, "USD"), RUR(3, "RUR");

    private int id;

    private String name;

    Currency(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Stream<Currency> stream() {
        return Stream.of(Currency.values());
    }

    public static Currency getEnum(String value) {
        if(value == null)
            throw new IllegalArgumentException();
        for(Currency v : values())
            if(value.equalsIgnoreCase(v.getName())) return v;
        throw new IllegalArgumentException();
    }

}
