package co.simplon.babycarehub.controllers;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.babycarehub.dtos.ChildLeisureCreateDto;
import co.simplon.babycarehub.dtos.LabelValue;
import co.simplon.babycarehub.entities.ChildLeisureEntity;
import co.simplon.babycarehub.services.LeisureService;

@RestController
@RequestMapping("/leisures")
public class LeisureController {

    private final LeisureService service;

    public LeisureController(LeisureService service) {
	this.service = service;

    }

    @GetMapping
    public Collection<LabelValue> getAll() {
	return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(
	    @RequestBody ChildLeisureCreateDto inputs) {
	service.create(inputs);
    }

    @GetMapping("/{date}/{childId}")
    public List<ChildLeisureEntity> getAllByDateAndChildId(
	    @PathVariable("date") Date date,
	    @PathVariable("childId") Long childId) {
	System.out.println("date" + date);
	return service.getAllByDateAndChildId(date,
		childId);
    }

}
