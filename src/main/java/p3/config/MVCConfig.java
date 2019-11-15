package p3.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfig {
    private Logger logger= LogManager.getLogger(MVCConfig.class);
    @Bean
    public WebMvcConfigurer mvcConfigurer(){
        return new WebMvcConfigurer(){
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                logger.info("添加CORS信息。。。。。");
                registry.addMapping("/**")
                        .allowedOrigins("*")/*指定允许其他域名访问*/
                        .allowedMethods("*")/*响应类型*/
                        .allowCredentials(true).maxAge(3600);/*允许跨域传输cookie*/

            }
        };
    }
}
