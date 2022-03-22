package jp.co.axa.apidemo.service;

import jp.co.axa.apidemo.constants.ApiCodes;
import jp.co.axa.apidemo.constants.Constants;
import jp.co.axa.apidemo.exception.MyCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("javainuse".equals(username)) {
            return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        } else {
            throw new MyCustomException(Constants.NOT_FOUND, ApiCodes.NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }
}
