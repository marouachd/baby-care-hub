package co.simplon.babycarehub.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.babycarehub.dtos.LabelValue;
import co.simplon.babycarehub.services.GenderService;

@RestController
@RequestMapping("/gender")
public class GenderController {

    private final GenderService service;

    public GenderController(GenderService service) {
	this.service = service;
    }

    @GetMapping
    public Collection<LabelValue> getAll() {
	return service.getAll();
    }

}
