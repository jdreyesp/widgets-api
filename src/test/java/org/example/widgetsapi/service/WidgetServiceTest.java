package org.example.widgetsapi.service;

import org.example.widgetsapi.entity.Widget;
import org.example.widgetsapi.repository.WidgetRepository;
import org.example.widgetsapi.utils.WidgetTestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.TreeSet;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class WidgetServiceTest {

    private WidgetService widgetServiceSUT;
    private WidgetRepository widgetRepositoryMock;

    @BeforeEach
    public void init() {
        widgetRepositoryMock = Mockito.mock(WidgetRepository.class);
        widgetServiceSUT = new WidgetService(widgetRepositoryMock);
    }

    @Test
    @DisplayName("Creation without Z index and non empty collection")
    public void shouldCreateForeGroundWidgetWhenZIndexNotProvided() {
        //Given
        final TreeSet<Widget> testWidgets = new TreeSet<>();
        testWidgets.add(WidgetTestBuilder.createTestWidget(1));
        testWidgets.add(WidgetTestBuilder.createTestWidget(2));
        testWidgets.add(WidgetTestBuilder.createTestWidget(3));

        final Widget widget = WidgetTestBuilder.createTestWidget();

        //When
        when(widgetRepositoryMock.getAll()).thenReturn(testWidgets);
        widgetServiceSUT.createWidget(widget);

        //Then
        ArgumentCaptor<Widget> widgetArgumentCaptor = ArgumentCaptor.forClass(Widget.class);
        verify(widgetRepositoryMock, times(4)).add(widgetArgumentCaptor.capture());
        assertEquals(4, widgetArgumentCaptor.getValue().getZindex().get());
    }

    @Test
    @DisplayName("Creation without Z index and empty collection")
    public void shouldCreateForeGroundWidgetWithDefaultIndexWhenEmptyWidgetCollection() {
        //Given

        //When
        when(widgetRepositoryMock.getAll()).thenReturn(new TreeSet<>());
        widgetServiceSUT.createWidget(WidgetTestBuilder.createTestWidget());

        //Then
        ArgumentCaptor<Widget> widgetArgumentCaptor = ArgumentCaptor.forClass(Widget.class);
        verify(widgetRepositoryMock).add(widgetArgumentCaptor.capture());
        assertEquals(WidgetService.DEFAULT_ZINDEX, widgetArgumentCaptor.getValue().getZindex().get());
    }

    @Test
    @DisplayName("Creation with specific Z index")
    public void shouldCreateWidgetWithSpecificZIndexAndShiftGreaters() {
        //Given
        final TreeSet<Widget> testWidgets = new TreeSet<>();
        testWidgets.add(WidgetTestBuilder.createTestWidget(1));
        testWidgets.add(WidgetTestBuilder.createTestWidget(10));
        testWidgets.add(WidgetTestBuilder.createTestWidget(11));

        //When
        when(widgetRepositoryMock.getAll()).thenReturn(testWidgets);
        widgetServiceSUT.createWidget(WidgetTestBuilder.createTestWidget(5));

        //Then
        ArgumentCaptor<Widget> widgetArgumentCaptor = ArgumentCaptor.forClass(Widget.class);

        verify(widgetRepositoryMock, times(3)).add(widgetArgumentCaptor.capture()); //1 insertion + 2 shifts
        verify(widgetRepositoryMock, times(2)).remove(any(UUID.class)); //2 shifts

        assertTrue(widgetArgumentCaptor.getAllValues().stream().anyMatch(widget -> widget.getZindex().get() == 11)); //The old second should be shifted
        assertTrue(widgetArgumentCaptor.getAllValues().stream().anyMatch(widget -> widget.getZindex().get() == 12)); //The old third should be shifted
    }
}
