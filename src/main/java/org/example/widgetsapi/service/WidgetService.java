package org.example.widgetsapi.service;

import org.example.widgetsapi.entity.Point;
import org.example.widgetsapi.entity.Widget;
import org.example.widgetsapi.repository.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.TreeSet;

@Service
public class WidgetService {

    public static final int DEFAULT_ZINDEX = 0;

    private WidgetRepository widgetRepository;

    @Autowired
    public WidgetService(WidgetRepository widgetRepository) {
        this.widgetRepository = widgetRepository;
    }

    public synchronized Widget createWidget(Point point, int width, int height) {
        final TreeSet<Widget> widgets = widgetRepository.getAll();
        final int zIndex = widgets.isEmpty() ? DEFAULT_ZINDEX : widgets.last().getZindex() + 1;

        return createWidget(point, zIndex, width, height);
    }

    public synchronized Widget createWidget(Point point, int zIndex, int width, int height) {
        return widgetRepository.create(point, zIndex, width, height);
    }
}
