package org.example.widgetsapi.repository;

import org.example.widgetsapi.entity.Point;
import org.example.widgetsapi.entity.Widget;

import java.util.TreeSet;

public interface WidgetRepository {
    TreeSet<Widget> getAll();
    Widget create(Point point, int zIndex, int width, int height);
}
