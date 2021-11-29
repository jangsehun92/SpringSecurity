package newbee.jsh.security.webConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS={"classpath:/static/",
                                                                "classpath:/templates/",
                                                                "classpath:/META-INF/resources/",
                                                                "classpath:/META-INF/webjars/"};

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //Spring boot가 바라보는 정적 자원의 위치를 변경해준다.(기본 classpath:/static/)
        registry.addResourceHandler("/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }
    
}
