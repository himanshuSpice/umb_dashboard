package com.spice.service.creation.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
@ImportResource(value = { "classpath:db.xml" })
@ComponentScan("com.spice.service")
@PropertySource(value={"classpath:messages.properties","classpath:db-local.properties"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class WebConfig extends WebMvcConfigurerAdapter {                                    
	  @Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select()                                  
	          .apis(RequestHandlerSelectors.any())              
	          .paths(PathSelectors.any())                          
	          .build()
	          .apiInfo(apiInfo());                                           
	    }
	    
	    
	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("swagger-ui.html")
	          .addResourceLocations("classpath:/META-INF/resources/");
	        registry.addResourceHandler("/webjars/**")
	          .addResourceLocations("classpath:/META-INF/resources/webjars/");
	    }
	    
	    
	    
	    @Bean(name = "messageSource")
		  public ResourceBundleMessageSource getMessageSource() {
	    	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		      messageSource.setBasename("messages");
		      messageSource.setDefaultEncoding("UTF-8");
		      messageSource.setUseCodeAsDefaultMessage(true);
		      return messageSource;
		  }
	    
	    @Bean
	    public RestTemplate restTemplate() {
	   /* 	 int timeout = 5000;
	    	 HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
	         httpRequestFactory.setConnectionRequestTimeout(timeout);
	         httpRequestFactory.setConnectTimeout(timeout);
	         httpRequestFactory.setReadTimeout(timeout);*/
	        return new RestTemplate();
	    }
	   
	    @Bean(name = "validationSource")
		  public ResourceBundleMessageSource getValidationSource() {
		      ResourceBundleMessageSource validationSource = new ResourceBundleMessageSource();
		      validationSource.setBasename("validation");
		      validationSource.setDefaultEncoding("UTF-8");
		      return validationSource;
		  }
		    
	    @Bean
	    public LocaleResolver localeResolver(){
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(new Locale("en"));
		resolver.setCookieName("myLocaleCookie");
		resolver.setCookieMaxAge(4800);
		return resolver;
	    }
		
	    @Override
	    public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("mylocale");
		registry.addInterceptor(interceptor);
	    }
	    
		
	    @Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.allowCredentials(false);
		}
	    
	    @SuppressWarnings("deprecation")
		private ApiInfo apiInfo() {
		    return  new ApiInfo(
		      "SpiceMfs API",
		      "Some custom description of API.",
		      "API TOS",
		      "Terms of service",
		      "Spice Labs",
		      "License of API",
		      "API license URL");
		}
	    
	}


