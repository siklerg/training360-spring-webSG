package springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = Webconfig.class)
public class Webconfig implements WebMvcConfigurer{

	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public ViewResolver viewResolver() {
	ThymeleafViewResolver resolver = new ThymeleafViewResolver();
	resolver.setTemplateEngine(templateEngine());
	resolver.setCharacterEncoding("UTF-8");
	return resolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
	SpringTemplateEngine engine = new SpringTemplateEngine();
	engine.setTemplateResolver(templateResolver());
	return engine;
	}

	@Bean
	public ITemplateResolver templateResolver() {
	SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
	resolver.setApplicationContext(applicationContext);
	resolver.setPrefix("/WEB-INF/templates/");
	resolver.setSuffix(".html");
	resolver.setTemplateMode("HTML5");
	return resolver;
	}

}
