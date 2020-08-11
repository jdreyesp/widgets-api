package org.example.widgetsapi.service;

import org.example.widgetsapi.entity.Point;
import org.example.widgetsapi.entity.Widget;
import org.example.widgetsapi.repository.WidgetRepository;
import org.example.widgetsapi.utils.WidgetTestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WidgetServiceTest {

    private WidgetService widgetServiceSUT;
    private WidgetRepository widgetRepositoryMock;

    @BeforeEach
    public void init() {
        widgetRepositoryMock = Mockito.mock(WidgetRepository.class);
        widgetServiceSUT = new WidgetService(widgetRepositoryMock);
    }

    @Test
    public void shouldCreateForeGroundWidgetWhenZIndexNotProvided() {
        //Given
        final TreeSet<Widget> testWidgets = new TreeSet<>();
        testWidgets.add(WidgetTestBuilder.createTestWidget(1));
        testWidgets.add(WidgetTestBuilder.createTestWidget(2));
        testWidgets.add(WidgetTestBuilder.createTestWidget(3));

        //When
        when(widgetRepositoryMock.getAll()).thenReturn(testWidgets);
        widgetServiceSUT.createWidget(WidgetTestBuilder.POINT, WidgetTestBuilder.WIDTH, WidgetTestBuilder.HEIGHT);

        //Then
        ArgumentCaptor<Integer> zIndexArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(widgetRepositoryMock).create(any(Point.class), zIndexArgumentCaptor.capture(), any(Integer.class), any(Integer.class));
        assertEquals(4, zIndexArgumentCaptor.getValue());
    }

    @Test
    public void shouldCreateForeGroundWidgetWithDefaultIndexWhenEmptyWidgetCollection() {
        //Given

        //When
        when(widgetRepositoryMock.getAll()).thenReturn(new TreeSet<>());
        widgetServiceSUT.createWidget(WidgetTestBuilder.POINT, WidgetTestBuilder.WIDTH, WidgetTestBuilder.HEIGHT);

        //Then
        ArgumentCaptor<Integer> zIndexArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(widgetRepositoryMock).create(any(Point.class), zIndexArgumentCaptor.capture(), any(Integer.class), any(Integer.class));
        assertEquals(WidgetService.DEFAULT_ZINDEX, zIndexArgumentCaptor.getValue());
    }

}
