package org.example.widgetsapi.controller;

import org.example.widgetsapi.entity.Widget;
import org.example.widgetsapi.service.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/widget")
public class WidgetController {

    private WidgetService widgetService;

    @Autowired
    public WidgetController(WidgetService widgetService) {
        this.widgetService = widgetService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Widget createWidget(@RequestBody Widget widget) {
        return Optional.ofNullable(widget.getZindex()).isEmpty() ?
                widgetService.createWidget(widget.getCoordinates(), widget.getWidth(), widget.getHeight()) :
                widgetService.createWidget(widget.getCoordinates(), widget.getZindex(), widget.getWidth(), widget.getHeight());
    }
}
