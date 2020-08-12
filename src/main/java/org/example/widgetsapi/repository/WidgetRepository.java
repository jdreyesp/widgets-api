package org.example.widgetsapi.repository;

import org.example.widgetsapi.entity.Widget;

import java.util.TreeSet;
import java.util.UUID;

public interface WidgetRepository {
    TreeSet<Widget> getAll();
    Widget add(Widget widget);
    boolean remove(UUID uuid);
}
