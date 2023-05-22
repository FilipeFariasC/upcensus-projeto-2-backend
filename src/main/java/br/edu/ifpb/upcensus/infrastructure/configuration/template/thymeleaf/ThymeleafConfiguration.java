package br.edu.ifpb.upcensus.infrastructure.configuration.template.thymeleaf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.dialect.SpringStandardDialect;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import br.edu.ifpb.upcensus.infrastructure.configuration.template.thymeleaf.dialect.DialectUtils;

@Configuration
public class ThymeleafConfiguration {
	
    @Bean
    TemplateEngine textTemplateEngine(ITemplateResolver stringTemplateResolver, DialectUtils utils) {
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(stringTemplateResolver);
        templateEngine.addDialect(new Java8TimeDialect());
        templateEngine.addDialect(new SpringStandardDialect());
        templateEngine.addDialect(utils);

        return templateEngine;
    }

    @Bean
    StringTemplateResolver stringTemplateResolver() {
        StringTemplateResolver resolver = new StringTemplateResolver();
        resolver.setCacheable(true);
        resolver.setTemplateMode(TemplateMode.TEXT);

        return resolver;
    }
    
}
