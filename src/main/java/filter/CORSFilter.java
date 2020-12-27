package filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
 
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
 
//重点
@Order
@WebFilter(filterName = "corsFilter", urlPatterns = {"/*"})
public class CORSFilter implements Filter{

    private static final Logger logger = LoggerFactory.getLogger(CORSFilter.class);
     
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("=============初始化filter============");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        setCorsFilter(request, response);   // 设置跨域响应头

        String method = request.getMethod();
		if (method.equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            // 校验token有效性
        }
        filterChain.doFilter(servletRequest, servletResponse);
        logger.info("==============filter==============");
    }

    @Override
    public void destroy() {
        logger.info("===============销毁filter===========");
    }

    /****
     * 设置跨域问题
     * @param request
     * @param response
     */
    private static void setCorsFilter(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin")); // 这里最好明确的写允许的域名
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "trm_access_token,X-Requested-With,Content-Type");
    }
}