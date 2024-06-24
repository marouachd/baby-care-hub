package co.simplon.babycarehub.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.babycarehub.dtos.MealCreateDto;
import co.simplon.babycarehub.entities.MealEntity;
import co.simplon.babycarehub.entities.SnackEntity;
import co.simplon.babycarehub.services.MealService;

@RestController
@RequestMapping("/meals")
public class MealController {

    private final MealService service;

    public MealController(MealService service) {
	this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody MealCreateDto inputs) {
	service.create(inputs);
    }

    @GetMapping
    public List<SnackEntity> getAll() {
	return service.getAll();
    }

    @GetMapping("/{date}/{childId}/{type}")
    public List<MealEntity> getAllByDateAndChildIdAndType(
	    @PathVariable("date") Date date,
	    @PathVariable("childId") Long childId,
	    @PathVariable("type") String type) {
	System.out.println("date" + date);
	return service.getAllByDateAndChildIdAndType(date,
		childId, type);
    }

}
