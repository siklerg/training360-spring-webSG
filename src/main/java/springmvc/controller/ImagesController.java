package springmvc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ImagesController {
	private Map<String, Resource> images = new HashMap<>(Map.of("spring.svg", new ClassPathResource("/spring.svg")));

	@RequestMapping(value = "images/{name:.+}", method = RequestMethod.GET)
	@ResponseBody
	public Resource getImage(@PathVariable("name") String name) {
		return images.get(name);
	}

	@RequestMapping(value = "/upload-image", method = RequestMethod.GET)
	public ModelAndView getUploadImageForm() {

		return new ModelAndView("upload-image", "uploadImageForm", new UploadImageForm());
	}

	@RequestMapping(value = "/upload-image", method = RequestMethod.POST)
	public String uploadImage(@ModelAttribute UploadImageForm uploadImageForm) {
		try {
			images.put(uploadImageForm.getName(), new ByteArrayResource(uploadImageForm.getFile().getBytes()));
			System.out.println(images.toString());
		} catch (IOException e) {
			throw new IllegalStateException("Can not upload file!", e);
		}
		return "redirect:/";
	}
}
