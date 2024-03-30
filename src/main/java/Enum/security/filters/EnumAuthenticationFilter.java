package Enum.security.filters;

import Enum.dto.request.LoginRequest;
import Enum.dto.response.ApiResponse;
import Enum.exceptions.EnumBaseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import static Enum.utils.JwtUtils.generateAccessToken;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
public class EnumAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){

        try{
            InputStream inputStream = request.getInputStream();
            LoginRequest loginRequest =  objectMapper.readValue(inputStream, LoginRequest.class);

            String email = loginRequest.getEmail();
            String password = loginRequest.getPassword();


            Authentication toAuthenticate = new UsernamePasswordAuthenticationToken(email, password);

            Authentication authenticationResult  = authenticationManager.authenticate(toAuthenticate);

            SecurityContextHolder.getContext().setAuthentication(authenticationResult);

            return authenticationResult;

        } catch (IOException e) {
            throw new EnumBaseException(e.getMessage());
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain filterChain, Authentication authResult) throws IOException {

        Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
        var role = roles.stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String token = generateAccessToken(role);
        var apiResponse = ApiResponse.builder().data(token).build();

        response.setContentType(APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getOutputStream(), apiResponse);

//        AbstractAuthenticationProcessingFilter
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {

        response.setContentType(APPLICATION_JSON_VALUE);
        var apiResponse =  ApiResponse.builder().data(failed.getMessage()).build();
        objectMapper.writeValue(response.getOutputStream(), apiResponse);

    }
}
