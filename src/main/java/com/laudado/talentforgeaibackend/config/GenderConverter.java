package com.laudado.talentforgeaibackend.config;

import com.laudado.talentforgeaibackend.enums.Gender;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class GenderConverter implements Converter<String, Gender> {

    @Override
    public Gender convert(@NonNull String source) {
        return Gender.fromValue(source);
    }
}