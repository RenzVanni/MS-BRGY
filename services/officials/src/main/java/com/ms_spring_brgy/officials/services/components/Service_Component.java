package com.ms_spring_brgy.officials.services.components;

import java.util.Objects;
import java.util.function.Consumer;

public class Service_Component {
    public static <T> void updateService(T oldValue, T newValue, Consumer<T> setter) {
        if(!Objects.equals(oldValue, newValue) && newValue != null) {
            setter.accept(newValue);
        }
    }
}
