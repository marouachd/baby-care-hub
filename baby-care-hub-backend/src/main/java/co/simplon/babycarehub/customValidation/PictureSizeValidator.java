package co.simplon.babycarehub.customValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class PictureSizeValidator implements
	ConstraintValidator<PictureSize, MultipartFile> {

    private static final long KB = 500000;

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
