package fi.haagahelia.blogmanagment.web;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.swing.ImageIcon;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.blogmanagment.domain.ArticleRepository;

@Controller
public class ImageController {
	@Autowired
	private ArticleRepository repository;

	@GetMapping(
			  value = "/get-image-with-media-type",
			  produces = MediaType.IMAGE_JPEG_VALUE
			)
			public @ResponseBody byte[] getImageWithMediaType() throws IOException {
			    InputStream in = getClass()
			      .getResourceAsStream("src/main/resources/static/img/business.jpeg");
			    return IOUtils.toByteArray(in);
			}
	
	
	@RequestMapping(value = "/imageController/{articleId}")
	@ResponseBody
	public Image helloWorld(@PathVariable long articleId)  {
	  Base64.getDecoder().decode(repository.findById(articleId).get().getImageURL());//obtain Image instance by id somehow from DAO/Hibernate
	  ImageIcon imageIcon = new ImageIcon(Base64.getDecoder().decode(repository.findById(articleId).get().getImageURL()));
	  Image test = imageIcon.getImage();
	  return test;
	}
}
