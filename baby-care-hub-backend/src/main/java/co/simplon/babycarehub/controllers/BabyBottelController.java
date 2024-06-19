package co.simplon.babycarehub.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.babycarehub.dtos.BabyBottelCreateDto;
import co.simplon.babycarehub.entities.BabyBottelEntity;
import co.simplon.babycarehub.services.BabyBottelService;

@RestController
@RequestMapping("/bottels")
@CrossOrigin
public class BabyBottelController {

    private final BabyBottelService service;

    public BabyBottelController(BabyBottelService service) {
	this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(
	    @RequestBody BabyBottelCreateDto inputs) {
	service.create(inputs);
    }

    @GetMapping("/{date}/{childId}")
    public List<BabyBottelEntity> getAllByDateAndChildId(
	    @PathVariable("date") Date date,
	    @PathVariable("childId") Long childId) {
	System.out.println("date" + date);
	return service.getAllByDateAndChildId(date,
		childId);
    }
}
