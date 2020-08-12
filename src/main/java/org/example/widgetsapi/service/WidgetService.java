package org.example.widgetsapi.service;

import org.example.widgetsapi.entity.Widget;
import org.example.widgetsapi.factory.WidgetFactory;
import org.example.widgetsapi.repository.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

@Service
public class WidgetService {

    public static final int DEFAULT_ZINDEX = 0;

    private WidgetRepository widgetRepository;

    @Autowired
    public WidgetService(WidgetRepository widgetRepository) {
        this.widgetRepository = widgetRepository;
    }

    public synchronized Widget createWidget(Widget widget) {
        return Optional.ofNullable(widget.getZindex()).isPresent() ? createWithZIndex(widget) : createInForeGround(widget);
    }

    private Widget createWithZIndex(Widget widget) {
        final Widget createdWidget = widgetRepository.add(widget);

        //Shift the elements
        final TreeSet<Widget> widgets = widgetRepository.getAll();
        final SortedSet<Widget> widgetsTailSet = widgets.tailSet(widget, false);

        widgetsTailSet.forEach(widg -> {
            widgetRepository.remove(widg.getId());
            Widget newWidget = WidgetFactory.of(widg.getId(), widg.getCoordinates(), Optional.of(widg.getZindex().get() + 1), widg.getWidth(), widg.getHeight(), null);
            widgetRepository.add(newWidget);
        });

        return createdWidget;
    }

    private Widget createInForeGround(Widget widget) {
        final TreeSet<Widget> widgets = widgetRepository.getAll();
        final int zIndex = widgets.isEmpty() ? DEFAULT_ZINDEX : widgets.last().getZindex().get() + 1;
        final Widget newWidget = WidgetFactory.of(widget.getId(), widget.getCoordinates(), Optional.of(zIndex), widget.getWidth(), widget.getHeight(), null);
        widget = null; //help GC
        return widgetRepository.add(newWidget);
    }
}
