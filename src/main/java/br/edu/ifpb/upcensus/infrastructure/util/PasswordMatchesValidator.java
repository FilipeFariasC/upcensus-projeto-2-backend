package br.edu.ifpb.upcensus.infrastructure.util;



import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
 

import br.edu.ifpb.upcensus.infrastructure.annotation.user.PasswordMatches;
import br.edu.ifpb.upcensus.presentation.user.request.UserRequest;
 
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserRequest> {
 
    @Override
    public boolean isValid(final UserRequest user, final ConstraintValidatorContext context) {
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
