package co.simplon.babycarehub.customValidation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class PictureTypeValidator implements
	ConstraintValidator<PictureType, MultipartFile> {

    private static final List<String> VALID_FILE_TYPES = new ArrayList<String>();
    static {
	VALID_FILE_TYPES.add("image/gif");
	VALID_FILE_TYPES.add("image/png");
	VALID_FILE_TYPES.add("image/jpeg");
    }

    @Override
    public boolean isValid(MultipartFile value,
	    ConstraintValidatorContext context) {
	if (value == null) {
	    return true;
	}

	if (VALID_FILE_TYPES
		.contains(value.getContentType())) {
	    return true;

	}

	return false;
    }
}
