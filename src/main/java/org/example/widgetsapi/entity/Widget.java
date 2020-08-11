package org.example.widgetsapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Widget implements Comparable<Widget> {

    private UUID id;
    private Point coordinates;
    private int zIndex;
    private int width, height;
    private LocalDateTime lastModificationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Widget widget = (Widget) o;
        return id.equals(widget.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Widget o) {
        return zIndex - o.zIndex;
    }
}

