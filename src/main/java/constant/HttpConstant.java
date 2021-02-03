package constant;

/**
 * 请求常量池
 */
public class HttpConstant {

    /**
     * 响应码
     */
    public static class Code {

        /**
         * 成功
         */
        public static final Integer SUCCESS = 200;

        /**
         * 失败
         */
        public static final Integer ERROR = 500;

    }



    /**
     * 响应码
     */
    public static class ErrorCode {

        /**
         * 成功
         */
        public static final Integer SUCCESS = 0;

        /**
         * 业务处理错误，具体查看指定原因
         */
        public static final Integer ERROR = -1;

        /**
         * 未登录
         */
        public static final Integer NOT_LOGIN = 300;

        /**
         * 找不到接口
         */
        public static final Integer NOT_FIND = 404;

    }

}
