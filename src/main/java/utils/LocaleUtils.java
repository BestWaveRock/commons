package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class LocaleUtils {

    private Logger logger = LoggerFactory.getLogger(LocaleUtils.class);

    private LocaleUtils() {
        logger.info("构造了 LocaleUtils");
    }

    public static Locale getCurrentLocale() {
        return LocaleContextHolder.getLocale();
    }
}
