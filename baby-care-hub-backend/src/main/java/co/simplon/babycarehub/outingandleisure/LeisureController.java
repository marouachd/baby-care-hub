package co.simplon.babycarehub.outingandleisure;

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
@RequestMapping("/leisures")
@CrossOrigin
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

}
