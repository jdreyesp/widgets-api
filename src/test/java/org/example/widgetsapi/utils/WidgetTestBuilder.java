package org.example.widgetsapi.utils;

import org.example.widgetsapi.entity.Point;
import org.example.widgetsapi.entity.Widget;
import org.example.widgetsapi.factory.WidgetFactory;

import java.util.Optional;
import java.util.UUID;

public class WidgetTestBuilder {
    public static final int WIDTH = 10, HEIGHT = 10, Z_INDEX = 0, X = 2, Y = 3;
    public static final Point POINT = new Point(X, Y);

    public static Widget createTestWidget(Integer zIndex) {
        return WidgetFactory.of(UUID.randomUUID(), POINT, Optional.ofNullable(zIndex).or(() -> Optional.of(Z_INDEX)), WIDTH, HEIGHT, null);
    }

    public static Widget createTestWidget() {
        return WidgetFactory.of(POINT, Optional.of(Z_INDEX), WIDTH, HEIGHT);
    }
}
