package config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import filter.CORSFilter;
import interceptor.CorsInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EnableWebMvc
@Component
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<CORSFilter> csrfFilterRegistration() {
        FilterRegistrationBean<CORSFilter> registration = new FilterRegistrationBean<>(new CORSFilter());
        registration.setName("corsFilter");
        registration.addUrlPatterns("/*");
        registration.setAsyncSupported(true);
        registration.setOrder(1);
        System.out.println("3");
        return registration;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.valueOf("application/json;charset=UTF-8"));
        mediaTypes.add(MediaType.valueOf("text/html"));
        converter.setSupportedMediaTypes(mediaTypes);
        converter.setFeatures(SerializerFeature.QuoteFieldNames, SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNonStringValueAsString);
        //, SerializerFeature.WriteNonStringValueAsString 设置这个属性，输出的Json值所有类型都为String
        converters.add(converter);

        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN));
        converters.add(stringHttpMessageConverter);
    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        /**
         * 所有请求都允许跨域，使用这种配置就不需要
         * 在interceptor中配置header了
         */
        corsRegistry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("/*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("*")
                .maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        CorsInterceptor corsInterceptor = new CorsInterceptor();
        registry.addInterceptor(corsInterceptor).addPathPatterns("/**").excludePathPatterns("/*.ico", "/*/api-docs",
                "/swagger**", "/swagger-resources/**", "/webjars/**", "/configuration/**");
    }
}
