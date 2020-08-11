package org.example.widgetsapi.utils;

import org.example.widgetsapi.entity.Point;
import org.example.widgetsapi.entity.Widget;
import org.example.widgetsapi.factory.WidgetFactory;

import java.time.LocalDateTime;
import java.util.UUID;

public class WidgetTestBuilder {
    public static final int WIDTH = 10, HEIGHT = 10, Z_INDEX = 0, X = 2, Y = 3;
    public static final Point POINT = new Point(X, Y);

    public static Widget createTestWidget(Integer zIndex) {
        return WidgetFactory.of(POINT, zIndex, WIDTH, HEIGHT);
    }

    public static Widget createTestWidget(Integer zIndex, LocalDateTime lastModificationDate) {
        return new Widget(UUID.randomUUID(), POINT, zIndex, WIDTH, HEIGHT, lastModificationDate);
    }
}
