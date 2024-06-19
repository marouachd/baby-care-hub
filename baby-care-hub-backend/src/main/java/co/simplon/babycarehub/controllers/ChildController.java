package co.simplon.babycarehub.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.babycarehub.dtos.ChildDetail;
import co.simplon.babycarehub.dtos.ChildDto;
import co.simplon.babycarehub.dtos.ChildUpdateDto;
import co.simplon.babycarehub.entities.ChildEntity;
import co.simplon.babycarehub.services.ChildService;
import jakarta.validation.Valid;

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
    public void create(
	    @Valid @ModelAttribute ChildDto inputs) {
	service.create(inputs);
    }

    @PostMapping("/desactive/{id}")
    public void desactive(@PathVariable("id") Long id) {
	service.desactive(id);
    }

    @PatchMapping("/accepte/{id}")
    public void accepte(@PathVariable("id") Long id) {
	service.accepte(id);
    }

    @GetMapping
    public List<ChildEntity> getAll() {
	return service.getAll();
    }

    @GetMapping("/parent/{id}")
    public List<ChildEntity> getAllByParentId(
	    @PathVariable("id") Long id) {
	return service.getAllByParentId(id);
    }

    @GetMapping("/childminder/{id}")
    public List<ChildEntity> getAllByChildminderCode(
	    @PathVariable("id") Long id) {
	return service.getAllByChildminderCode(id);
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

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Long id,
	    @ModelAttribute ChildUpdateDto inputs) {
	System.out.println("Type de la variable id : "
		+ id.getClass().getName());
	service.update(id, inputs);
    }

    @PatchMapping("/active/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activeChild(@PathVariable("id") Long id,
	    @RequestBody ChildDto inputs) {

	service.activeChild(id, inputs);
    }

    @PostMapping("/refuse-guard/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void refuseGuard(@PathVariable("id") Long id) {

	service.refuseGuard(id);
    }

    @GetMapping("/childminders/{ids}")
    public Map<Long, List<ChildEntity>> getAllByChildminderCodes(
	    @PathVariable("ids") List<Long> ids) {
	Map<Long, List<ChildEntity>> childEntitiesMap = new HashMap<>();

	for (Long id : ids) {
	    List<ChildEntity> childEntities = service
		    .getAllByChildminderCode(id);
	    childEntitiesMap.put(id, childEntities);
	}

	return childEntitiesMap;
    }

}
