package org.example.widgetsapi.repository;

import org.example.widgetsapi.entity.Widget;
import org.example.widgetsapi.factory.WidgetFactory;

import java.time.LocalDateTime;
import java.util.TreeSet;
import java.util.UUID;

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
    public Widget add(Widget widget) {
        final Widget widgetToAdd = WidgetFactory.of(UUID.randomUUID(),
                widget.getCoordinates(),
                widget.getZindex(),
                widget.getWidth(),
                widget.getHeight(),
                LocalDateTime.now());
        widgets.add(widgetToAdd);
        widget = null; //help GC
        return widgetToAdd;
    }

    @Override
    public boolean remove(UUID uuid) {
        return widgets.removeIf(widget -> widget.getId().compareTo(uuid) == 0);
    }

}
