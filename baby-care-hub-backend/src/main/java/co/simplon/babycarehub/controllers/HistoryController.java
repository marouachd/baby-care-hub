package co.simplon.babycarehub.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.babycarehub.entities.History;
import co.simplon.babycarehub.services.HistoryService;

@RestController
@RequestMapping("/history")
@CrossOrigin
public class HistoryController {

    private final HistoryService service;

    public HistoryController(HistoryService service) {
	this.service = service;
    }

    @GetMapping("/{id}")
    public List<History> getAll(
	    @PathVariable("id") Long id) {
	return service.getAllByChildminderId(id);
    }

}
