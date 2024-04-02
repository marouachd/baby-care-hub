package co.simplon.babycarehub.actualities;

import java.sql.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actualities")
@CrossOrigin
public class ActualityController {

    private final ActualityService service;

    public ActualityController(ActualityService service) {
	this.service = service;
    }

    @GetMapping("/{date}/{childId}")
    public ActualityEntity actualityDetail(
	    @PathVariable("date") Date date,
	    @PathVariable("childId") Long childId) {
	return service.actualityDetail(date, childId);

    }

}
