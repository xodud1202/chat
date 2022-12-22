package com.xodud1202.chat.support.config;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * THYMELEAF Configuration
 * @author xodud1202
 * @since  2022.12.22
 */
@Configuration
public class TycThymeleafConfig {
	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private Environment env;

	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(applicationContext);
		templateResolver.setPrefix(env.getProperty("spring.thymeleaf.prefix"));
		templateResolver.setSuffix(env.getProperty("spring.thymeleaf.suffix"));
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setCacheable(false);

		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		templateEngine.addDialect(new LayoutDialect());
		templateEngine.addDialect(new Java8TimeDialect());
		return templateEngine;
	}

	@Bean
	public ViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();

		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
		viewResolver.setOrder(Ordered.HIGHEST_PRECEDENCE);

		// below views will not be handled by this reslover.
		viewResolver.setExcludedViewNames(new String[] {"common/**/*"});

		return viewResolver;
	}
}
