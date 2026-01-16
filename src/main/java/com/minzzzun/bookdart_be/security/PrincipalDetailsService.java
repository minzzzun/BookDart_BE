package com.minzzzun.bookdart_be.security;

import com.minzzzun.bookdart_be.domain.User;
import com.minzzzun.bookdart_be.exception.NoMatchingDataException;
import com.minzzzun.bookdart_be.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	public PrincipalDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
    /**
	 *  principalDetails 생성을 위한 함수.
	 *  username으로 user 조회, principalDetails 생성
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new NoMatchingDataException("username : " + username);
		}
		return new PrincipalDetails(user);
	}
	
}