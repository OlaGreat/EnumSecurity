package Enum.security.managers;

import Enum.exceptions.AuthenticationNotSupportedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;



import static Enum.exceptions.ExceptionMessages.AUTHENTICATION_NOT_SUPPORTED;

@Component
@RequiredArgsConstructor
public class EnumAuthenticationManager implements AuthenticationManager {

    private final AuthenticationProvider authenticationProvider;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authenticationProvider.supports(authentication.getClass())){
            Authentication authenticationResult = authenticationProvider.authenticate(authentication);
            return authenticationResult;
        }

        throw new AuthenticationNotSupportedException(AUTHENTICATION_NOT_SUPPORTED.getMessage());
    }
}
