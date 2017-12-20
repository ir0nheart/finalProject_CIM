package cim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cim.dao.UserDAO;
import cim.entity.UserInfo;
import cim.model.MyUserPrincipal;

@Service
	public class MyAppUserDetailsService implements UserDetailsService {
@Autowired
private UserDAO userDao;

public MyAppUserDetailsService() {
    super();
}

// API

@Override
public UserDetails loadUserByUsername(final String username) {
    final UserInfo user = userDao.getUser(username);
    if (user == null) {
        throw new UsernameNotFoundException(username);
    }
   return (UserDetails)new MyUserPrincipal(user);
}
}
