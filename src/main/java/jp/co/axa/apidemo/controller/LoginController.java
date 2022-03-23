package jp.co.axa.apidemo.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jp.co.axa.apidemo.config.TokenProvider;
import jp.co.axa.apidemo.constants.ApiCodes;
import jp.co.axa.apidemo.constants.Constants;
import jp.co.axa.apidemo.exception.MyCustomException;
import jp.co.axa.apidemo.model.request.LoginRequest;
import jp.co.axa.apidemo.model.response.LoginResponse;
import jp.co.axa.apidemo.service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @ApiOperation(value = "LoginController")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Something went wrong"),
            @ApiResponse(code = 401, message = "Invalid Credentials")})
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest request) throws Exception {

        authenticate(request.getUsername(), request.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        jp.co.axa.apidemo.model.response.ApiResponse response = new jp.co.axa.apidemo.model.response.ApiResponse<>();
        response.setStatus(HttpStatus.OK);
        response.setCode(ApiCodes.SUCCESS);
        response.setData(new LoginResponse(token));

        return new ResponseEntity<jp.co.axa.apidemo.model.response.ApiResponse<?>>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new MyCustomException(Constants.USER_DISABLED, ApiCodes.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
        } catch (BadCredentialsException e) {
            throw new MyCustomException(Constants.INVALID_CREDENTIALS, ApiCodes.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
        }
    }
}
