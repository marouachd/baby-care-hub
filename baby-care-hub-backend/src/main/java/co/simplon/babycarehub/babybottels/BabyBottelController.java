package co.simplon.babycarehub.babybottels;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
}
