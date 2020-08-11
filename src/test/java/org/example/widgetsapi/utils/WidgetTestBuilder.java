package org.example.widgetsapi.utils;

import org.example.widgetsapi.entity.Point;
import org.example.widgetsapi.entity.Widget;
import org.example.widgetsapi.factory.WidgetFactory;

public class WidgetTestBuilder {
    public static final int WIDTH = 10, HEIGHT = 10, Z_INDEX = 0, X = 2, Y = 3;
    public static final Point POINT = new Point(X, Y);

    public static Widget createTestWidget(int zIndex) {
        return WidgetFactory.of(POINT, zIndex, WIDTH, HEIGHT);
    }

}
