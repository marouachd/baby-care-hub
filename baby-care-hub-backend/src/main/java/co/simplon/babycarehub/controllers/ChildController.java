package co.simplon.babycarehub.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.babycarehub.dtos.ChildDto;
import co.simplon.babycarehub.services.ChildService;

@RestController
@RequestMapping("/child")
public class ChildController {

    private final ChildService service;

    public ChildController(ChildService service) {
	this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@ModelAttribute ChildDto inputs) {
	service.create(inputs);
    }
}
