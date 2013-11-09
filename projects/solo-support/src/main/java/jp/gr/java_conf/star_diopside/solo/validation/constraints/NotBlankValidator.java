package jp.gr.java_conf.star_diopside.solo.validation.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class NotBlankValidator implements ConstraintValidator<NotBlank, CharSequence> {

    @Override
    public void initialize(NotBlank constraintAnnotation) {
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }

        return StringUtils.isNotBlank(value);
    }
}
