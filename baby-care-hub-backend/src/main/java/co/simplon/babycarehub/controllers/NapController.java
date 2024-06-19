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

import co.simplon.babycarehub.dtos.NapCreateDto;
import co.simplon.babycarehub.entities.NapEntity;
import co.simplon.babycarehub.services.NapService;

@RestController
@RequestMapping("/naps")
@CrossOrigin
public class NapController {

    private final NapService service;

    public NapController(NapService service) {
	this.service = service;
    }

    @PostMapping("/{type}")
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody NapCreateDto inputs,
	    @PathVariable("type") String type) {
	service.create(inputs, type);
    }

    @GetMapping("/{date}/{childId}/{type}")
    public List<NapEntity> getAllByDateAndChildIdAndType(
	    @PathVariable("date") Date date,
	    @PathVariable("childId") Long childId,
	    @PathVariable("type") String type) {
	System.out.println("date" + date);
	return service.getAllByDateAndChildIdAndType(date,
		childId, type);
    }

}
