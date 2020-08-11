package org.example.widgetsapi.repository;

import org.example.widgetsapi.entity.Widget;
import org.example.widgetsapi.utils.WidgetTestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryWidgetRepositoryTest {

    private WidgetRepository widgetRepositorySUT;

    @BeforeEach
    public void init() {
        widgetRepositorySUT = new InMemoryWidgetRepository();
    }

    @Test
    public void shouldCreateAWidgetWithEmptyCollection() {
        //Given

        //When
        Widget widget = widgetRepositorySUT.create(WidgetTestBuilder.POINT, WidgetTestBuilder.Z_INDEX, WidgetTestBuilder.WIDTH, WidgetTestBuilder.HEIGHT);

        //Then
        TreeSet<Widget> widgets = widgetRepositorySUT.getAll();
        assertEquals(1, widgets.size());
        assertEquals(widget, widgets.first());
    }

    @Test
    public void shouldCreateAWidgetWithExistingCollection() {
        //Given

        //When
        widgetRepositorySUT.create(WidgetTestBuilder.POINT, WidgetTestBuilder.Z_INDEX, WidgetTestBuilder.WIDTH, WidgetTestBuilder.HEIGHT);

        /** Last widget will be the one with greater z_index */
        Widget widget = widgetRepositorySUT.create(WidgetTestBuilder.POINT, WidgetTestBuilder.Z_INDEX + 1, WidgetTestBuilder.WIDTH, WidgetTestBuilder.HEIGHT);

        //Then
        TreeSet<Widget> widgets = widgetRepositorySUT.getAll();
        assertEquals(2, widgets.size());
        assertNotEquals(widget, widgets.first());
        assertEquals(widget, widgets.last());
    }

    @Test
    public void shouldGetEmptyWidgetListWhenRepositoryIsCreated() {
        //Given

        //When
        TreeSet<Widget> widgets = widgetRepositorySUT.getAll();

        //Then
        assertTrue(widgets.isEmpty());
    }
}
