package co.simplon.babycarehub.naps;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/naps")
@CrossOrigin
public class NapController {

    private final NapService service;

    public NapController(NapService service) {
	this.service = service;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody NapCreateDto inputs) {
	service.create(inputs);
    }

}
