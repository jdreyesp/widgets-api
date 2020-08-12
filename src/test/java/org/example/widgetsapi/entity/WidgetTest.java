package org.example.widgetsapi.entity;

import org.example.widgetsapi.utils.WidgetTestBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WidgetTest {

    @Test
    public void shouldOrderWidgetsByZIndex() {
        //Given
        Widget widget1 = WidgetTestBuilder.createTestWidget(1);
        Widget widget2 = WidgetTestBuilder.createTestWidget(2);

        //When
        int compareWidgets = widget1.compareTo(widget2);

        //Then
        assertTrue(compareWidgets < 0);
    }

    @Test
    public void shouldDistinguishWidgetsByUUID() {
        //Given
        Widget widget1 = new Widget(UUID.randomUUID(), WidgetTestBuilder.POINT, Optional.of(WidgetTestBuilder.Z_INDEX), WidgetTestBuilder.WIDTH, WidgetTestBuilder.HEIGHT, LocalDateTime.now());
        Widget widget2 = new Widget(UUID.randomUUID(), WidgetTestBuilder.POINT, Optional.of(WidgetTestBuilder.Z_INDEX), WidgetTestBuilder.WIDTH, WidgetTestBuilder.HEIGHT, LocalDateTime.now());

        UUID uuid = UUID.randomUUID();
        Widget widgetUUID1 = new Widget(uuid, WidgetTestBuilder.POINT, Optional.of(WidgetTestBuilder.Z_INDEX), WidgetTestBuilder.WIDTH, WidgetTestBuilder.HEIGHT, LocalDateTime.now());
        Widget widgetUUID2 = new Widget(uuid, WidgetTestBuilder.POINT, Optional.of(WidgetTestBuilder.Z_INDEX), WidgetTestBuilder.WIDTH, WidgetTestBuilder.HEIGHT, LocalDateTime.now());

        //When
        boolean equalsWidgets = widget1.equals(widget2);
        boolean equalsSameUUID = widgetUUID1.equals(widgetUUID2);

        //Then
        assertFalse(equalsWidgets);
        assertTrue(equalsSameUUID);
    }
}
