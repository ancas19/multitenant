package com.co.multitenant.companies.domain.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Collection;
import java.util.List;

public class Mapper {
    private static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
    }

    private Mapper() {
    }

    public static <D, T> D map(final T entidad, Class<D> dto) {
        return modelMapper.map(entidad, dto);
    }

    public static <D, T> List<D> mapAll(final Collection<T> listaEntidad, Class<D> dto) {
        return listaEntidad.stream()
                .map(entity -> map(entity, dto))
                .toList();
    }
}
