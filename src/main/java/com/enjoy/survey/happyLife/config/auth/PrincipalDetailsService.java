package com.enjoy.survey.happyLife.config.auth;


import com.enjoy.survey.happyLife.User.UserDao;
import com.enjoy.survey.happyLife.User.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userDao.findByUsername(username);
        return new PrincipalDetails(userEntity);
    }
}
