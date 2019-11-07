package fi.haagahelia.blogmanagment.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fi.haagahelia.blogmanagment.domain.Member;
import fi.haagahelia.blogmanagment.domain.MemberRepository;



@Service
public class UserDetailServiceImpl implements UserDetailsService{
	private final MemberRepository repository;

	@Autowired
	public UserDetailServiceImpl(MemberRepository memberRepository) {
		this.repository = memberRepository;
	}

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {   
    	Member curruser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPassword(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    }    
}