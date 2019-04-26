package springmvc;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import springmvc.backend.BackendConfig;
import springmvc.controller.Webconfig;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// backend-hez tartozó konfiguráció
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { BackendConfig.class };
	}

	// frontend-hez tartotó konfiguráció
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { Webconfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(System.getProperty("java.io.tmpdir"),
				5 * 1024 * 1024, 20 * 1024 * 1024, 0);
		registration.setMultipartConfig(multipartConfigElement);
	}

}
