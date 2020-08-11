package org.example.widgetsapi.repository;

import org.example.widgetsapi.entity.Point;
import org.example.widgetsapi.entity.Widget;
import org.example.widgetsapi.factory.WidgetFactory;

import java.util.TreeSet;

/**
 * In-memory implementation of the z-index collection of elements. It's represented by a {@link TreeSet<Widget>}
 */
public class InMemoryWidgetRepository implements WidgetRepository {

    private static volatile TreeSet<Widget> widgets = new TreeSet<>();

    @Override
    public TreeSet<Widget> getAll() {
        return widgets;
    }

    @Override
    public Widget create(Point point, int zIndex, int width, int height) {
        final Widget widget = WidgetFactory.of(point, zIndex, width, height);
        widgets.add(widget);
        return widget;
    }

}
