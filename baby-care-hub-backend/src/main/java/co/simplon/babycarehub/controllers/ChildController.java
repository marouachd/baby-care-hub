package co.simplon.babycarehub.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.babycarehub.dtos.ChildDetail;
import co.simplon.babycarehub.dtos.ChildDto;
import co.simplon.babycarehub.dtos.ChildUpdateDto;
import co.simplon.babycarehub.entities.ChildEntity;
import co.simplon.babycarehub.services.ChildService;

@RestController
@RequestMapping("/child")
@CrossOrigin
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

    @GetMapping
    public List<ChildEntity> getAll() {
	return service.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
	service.delete(id);
    }

    @GetMapping("/{id}/detail")
    public ChildDetail detail(@PathVariable("id") Long id) {
	return service.detail(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
	    @ModelAttribute @PathVariable("id") Long id,
	    ChildUpdateDto inputs) {
	service.update(id, inputs);
    }
}
