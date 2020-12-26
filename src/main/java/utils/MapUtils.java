package utils;

import java.util.Map;
import java.util.Objects;

public class MapUtils {

    public static Boolean isNotEmpty(Map map) {
        if (Objects.isNull(map)) {
            return Boolean.FALSE;
        } else {
            if (map.size() > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        }
    }

}
