package core;

import config.ApplicationContextHolder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

/**
 * 加载配置
 */
public class Resources {

    private static MessageSource messageSource = ApplicationContextHolder.getBean(MessageSource.class);

    public static String getMessage(String key, Object... params) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, params, key,locale);
    }

}
