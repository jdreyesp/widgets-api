package org.example.widgetsapi.controller;

import org.example.widgetsapi.entity.Widget;
import org.example.widgetsapi.service.WidgetService;
import org.example.widgetsapi.utils.WidgetTestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

public class WidgetControllerTest {

    private WidgetService widgetServiceMock;
    private WidgetController widgetControllerSUT;

    @BeforeEach
    public void init() {
        widgetServiceMock = mock(WidgetService.class);
        widgetControllerSUT = new WidgetController(widgetServiceMock);
    }

    @Test
    public void shouldCallWidgetServiceWhenCreatingWidget() {
        //Given
        final Widget inputWidget = WidgetTestBuilder.createTestWidget();
        when(widgetServiceMock.createWidget(inputWidget)).thenReturn(inputWidget);

        //When
        final Widget widget = widgetControllerSUT.createWidget(inputWidget);

        //Then
        verify(widgetServiceMock).createWidget(widget);
        verifyNoMoreInteractions(widgetServiceMock);
    }
}
