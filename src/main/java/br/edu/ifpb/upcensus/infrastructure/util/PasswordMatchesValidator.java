package br.edu.ifpb.upcensus.infrastructure.util;



import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
 

import br.edu.ifpb.upcensus.infrastructure.annotation.user.PasswordMatches;
import br.edu.ifpb.upcensus.presentation.user.request.SignUpRequest;
 
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, SignUpRequest> {
 
    @Override
    public boolean isValid(final SignUpRequest user, final ConstraintValidatorContext context) {
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
