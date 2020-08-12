package org.example.widgetsapi.factory;

import org.example.widgetsapi.entity.Point;
import org.example.widgetsapi.entity.Widget;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


public class WidgetFactory {

    public static Widget of(final Point point, final Optional<Integer> zIndex, int width, int height) {
        return of(null, point, zIndex, width, height, null);
    }

    public static Widget of(final UUID uuid, final Point point, final Optional<Integer> zIndex, int width, int height, LocalDateTime localDateTime) {
        return new Widget(uuid, point, zIndex, width, height, localDateTime);
    }

}
