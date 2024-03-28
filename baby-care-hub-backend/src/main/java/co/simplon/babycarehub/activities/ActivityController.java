package co.simplon.babycarehub.activities;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.babycarehub.dtos.LabelValue;

@RestController
@RequestMapping("/activities")
@CrossOrigin
public class ActivityController {

    private final ChildActivityService service;

    public ActivityController(
	    ChildActivityService service) {
	this.service = service;

    }

    @GetMapping
    public Collection<LabelValue> getAll() {
	return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(
	    @RequestBody ChildActivityCreateDto inputs) {
	service.create(inputs);
    }

}
