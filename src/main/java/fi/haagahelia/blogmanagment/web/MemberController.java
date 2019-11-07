package fi.haagahelia.blogmanagment.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fi.haagahelia.blogmanagment.domain.ArticleRepository;
import fi.haagahelia.blogmanagment.domain.MemberRepository;

@Controller
public class MemberController {
	private static Authentication auth;
	@Autowired
	private MemberRepository memRepo;

	@Autowired
	private ArticleRepository repository;
	
	
	// show all articles
	@RequestMapping(value = "/profile")
	public String articlesList(Model model) {

		auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUser = auth.getName(); // get logged in username

		model.addAttribute("articles", repository.findByAuthor(memRepo.findByUsername(loggedUser)));

		return "memberProfile";
	}

	// login
	@RequestMapping(value = "/login")
	public String login() {
		
		return "login";
	}

}
