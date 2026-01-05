package com.example.tob.helpers.annotations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;


public class EmailUniqueValidation implements ConstraintValidator<UniqueEmailCustomize, String> {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)) {
            return true;
        }
        long count = entityManager.createQuery("select count(1) from Member where email = :email and systemDeleteFlag = false", Long.class)
                .setParameter("email", value)
                .getSingleResult();
        return count == 0;
    }

}
