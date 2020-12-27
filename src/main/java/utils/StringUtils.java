package utils;

import org.apache.commons.lang3.CharUtils;

import java.util.Objects;

/**
 * @Author Erin.J
 * @Date 2020/7/16 6:15 PM
 **/
public class StringUtils {

    public static Boolean isNotBlank(String str) {
        if (Objects.isNull(str)) {
            return Boolean.FALSE;
        } else {
            if (str.isEmpty()) {
                return Boolean.FALSE;
            } else {
                return Boolean.TRUE;
            }
        }
    }

    public static Boolean isBlank(String str) {
        return !isNotBlank(str);
    }

    public static boolean isAsciiPrintable(CharSequence cs) {
        if (cs == null) {
            return false;
        } else {
            int sz = cs.length();

            for(int i = 0; i < sz; ++i) {
                if (!CharUtils.isAsciiPrintable(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }
}
