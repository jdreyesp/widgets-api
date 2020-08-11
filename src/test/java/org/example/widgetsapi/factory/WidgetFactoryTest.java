package org.example.widgetsapi.factory;

import org.example.widgetsapi.entity.Point;
import org.example.widgetsapi.entity.Widget;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WidgetFactoryTest {

    @Test
    public void shouldCreateWidget() {
        //Given
        final int x = 1, y = 2;
        final Point point = new Point(x,y);
        final int width = 10, height = 20, zIndex = 5;

        //When
        final Widget widget = WidgetFactory.of(point, zIndex, width, height);

        //Then
        assertNotNull(widget.getId());
        assertEquals(x, widget.getCoordinates().getX());
        assertEquals(y, widget.getCoordinates().getY());
        assertEquals(width, widget.getWidth());
        assertEquals(height, widget.getHeight());

        assertEquals(zIndex, widget.getZIndex());
    }
}
