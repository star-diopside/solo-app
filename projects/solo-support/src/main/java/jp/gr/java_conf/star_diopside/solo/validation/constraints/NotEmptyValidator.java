package jp.gr.java_conf.star_diopside.solo.validation.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class NotEmptyValidator implements ConstraintValidator<NotEmpty, CharSequence> {

    @Override
    public void initialize(NotEmpty constraintAnnotation) {
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }

        return StringUtils.isNotEmpty(value);
    }
}
