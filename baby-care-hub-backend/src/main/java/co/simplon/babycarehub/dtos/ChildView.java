package co.simplon.babycarehub.dtos;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

public interface ChildView {

    Long getId();

    String getFirstName();

    String getLastName();

    LocalDate getBirthdayDate();

    MultipartFile getPersonalPicture();

}