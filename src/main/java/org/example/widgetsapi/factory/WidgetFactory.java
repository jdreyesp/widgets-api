package org.example.widgetsapi.factory;

import org.example.widgetsapi.entity.Point;
import org.example.widgetsapi.entity.Widget;

import java.time.LocalDateTime;
import java.util.UUID;


public class WidgetFactory {

    public static Widget of(final Point point, final Integer zIndex, int width, int height) {
        return new Widget(UUID.randomUUID(), point, zIndex, width, height, LocalDateTime.now());
    }

}
