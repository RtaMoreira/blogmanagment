package fi.haagahelia.blogmanagment.web;

import java.io.IOException;
import java.util.Base64;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.blogmanagment.domain.ArticleRepository;
import fi.haagahelia.blogmanagment.domain.Article;


public class ImageController {
	
	private ArticleRepository repository;


	@RequestMapping(value = "(image/{idArticle}", method = RequestMethod.GET)
	@ResponseBody
	public byte[] getImage(@PathVariable(value = "idArticle") Long idArticle) throws IOException {

		Article art = repository.findById(idArticle).get();
		System.out.println("ARTICLE ID FOR IMAGE : "+idArticle);
		byte[] image = Base64.getDecoder().decode(art.getImageURL());

	    return image;
	}
}
