package co.simplon.babycarehub.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.babycarehub.dtos.LabelValue;
import co.simplon.babycarehub.services.BabyCareModeService;

@RestController
@RequestMapping("/guard-mode")
@CrossOrigin
public class GuardModeController {

    private final BabyCareModeService service;

    public GuardModeController(
	    BabyCareModeService service) {
	this.service = service;

    }

    @GetMapping
    public Collection<LabelValue> getAll() {
	return service.getAll();
    }
}
