package org.example.widgetsapi.controller;

import org.example.widgetsapi.entity.Point;
import org.example.widgetsapi.entity.Widget;
import org.example.widgetsapi.service.WidgetService;
import org.example.widgetsapi.utils.WidgetTestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class WidgetControllerTest {

    private WidgetService widgetServiceMock;
    private WidgetController widgetResourceSUT;

    @BeforeEach
    public void init() {
        widgetServiceMock = mock(WidgetService.class);
        widgetResourceSUT = new WidgetController(widgetServiceMock);
    }

    @Test
    public void shouldAcceptAWidgetCreationWithNoZIndexProvided() {
        //Given
        final Widget inputWidget = WidgetTestBuilder.createTestWidget(null);

        //When
        final Widget widget = widgetResourceSUT.createWidget(inputWidget);

        //Then
        assertNotEquals(inputWidget, widget);
        verify(widgetServiceMock).createWidget(any(Point.class), any(Integer.class), any(Integer.class));
        verifyNoMoreInteractions(widgetServiceMock);
    }

    @Test
    public void shouldCreateAWidgetWhenAZIndexIsProvidedInRequestBody() {
        //Given
        final Widget inputWidget = WidgetTestBuilder.createTestWidget(WidgetTestBuilder.Z_INDEX);

        //When
        final Widget widget = widgetResourceSUT.createWidget(inputWidget);

        //Then
        assertNotEquals(inputWidget, widget);
        verify(widgetServiceMock).createWidget(any(Point.class), any(Integer.class), any(Integer.class), any(Integer.class));
        verifyNoMoreInteractions(widgetServiceMock);
    }
}
