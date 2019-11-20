package fi.haagahelia.blogmanagment.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import fi.haagahelia.blogmanagment.domain.ArticleRepository;
import fi.haagahelia.blogmanagment.domain.Member;
import fi.haagahelia.blogmanagment.domain.MemberRepository;

@Controller
public class MemberController {
	private static Authentication auth;
	@Autowired
	private MemberRepository memRepo;

	@Autowired
	private ArticleRepository repository;
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
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
	
	// add new member
	@RequestMapping(value = "/register")
	public String addMember(Model model) {
		
		model.addAttribute("member", new Member());
		return "register";
	}
	// save member
	@RequestMapping(value = "/saveMember", method = RequestMethod.POST)
	public String saveMember(Member member) {
		// add role
		member.setRole("USER");
		
		//bcrypt password
		member.setPassword(encoder.encode(member.getPassword()));
		memRepo.save(member);
		return "redirect:login";
	}

}
