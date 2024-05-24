package co.simplon.babycarehub.customValidation;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PictureSizeValidator implements
	ConstraintValidator<PictureSize, MultipartFile> {

    private static final long KB = 50000000;

    private long pictureSizeInKB;

    @Override
    public void initialize(PictureSize fileSize) {
	this.pictureSizeInKB = fileSize.maxSizeInKB();
    }

    @Override
    public boolean isValid(MultipartFile value,
	    ConstraintValidatorContext context) {
	if (value == null) {
	    return true;
	}
	return value.getSize() < (pictureSizeInKB * KB);
    }
}
