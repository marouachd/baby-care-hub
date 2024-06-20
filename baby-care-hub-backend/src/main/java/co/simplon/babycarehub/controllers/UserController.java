package co.simplon.babycarehub.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.babycarehub.dtos.UserDetail;
import co.simplon.babycarehub.dtos.UserDto;
import co.simplon.babycarehub.dtos.UserUpdateDto;
import co.simplon.babycarehub.entities.RoleEntity;
import co.simplon.babycarehub.entities.UserEntity;
import co.simplon.babycarehub.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {

	this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@ModelAttribute UserDto inputs) {
	service.create(inputs);
    }

    @GetMapping("/{id}/detail")
    public UserDetail detail(@PathVariable("id") Long id) {
	return service.detail(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void update(@PathVariable("id") Long id,
	    @ModelAttribute UserUpdateDto inputs) {
	service.update(id, inputs);

    }

    @GetMapping("/childminder/{role}")
    public List<UserEntity> getAllByRole(
	    @PathVariable("role") RoleEntity role) {
	return service.getAllChildminder(role);
    }

}
