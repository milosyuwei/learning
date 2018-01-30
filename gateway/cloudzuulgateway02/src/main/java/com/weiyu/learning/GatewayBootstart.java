package com.weiyu.learning;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by weiyu on 2018/1/25.
 */
@EnableZuulProxy
@SpringCloudApplication //注意这里使用了更加简化的@SpringCloudApplication
public class GatewayBootstart {
    private static Logger log = LoggerFactory.getLogger(GatewayBootstart.class);

    /**
     * 这里的方法返回值，不能写成com.netflix.zuul.IZuulFilter
     * 可以写成com.netflix.zuul.ZuulFilter，或者com.jadyer.demo.GatewayFilter
     * 虽然语法上允许返回IZuulFilter，但实际测试发现返回IZuulFilter时，网关功能却没有生效
     */
    @Bean
    public ZuulFilter gatewayFilter() {

        /**
         * 利用Zuul的过滤器，可以实现对外服务的安全控制
         * -------------------------------------------------------------------------
         * 这里实现了在请求被路由之前检查请求中是否有accesstoken参数
         * 若有就进行路由，若没有就拒绝访问，返回401 Unauthorized错误
         * http://127.0.0.1:4100/mycall/add?a=11&b=22：返回"权限不足"
         * http://127.0.0.1:4100/mycall/add?a=11&b=22&accesstoken=token：返回正常
         * -------------------------------------------------------------------------
         */
        return new ZuulFilter(){

            //设置该过滤器总是生效，即总是执行拦截请求
            @Override
            public boolean shouldFilter() {
                return true;
            }

            @Override
            public Object run() {
                RequestContext ctx = RequestContext.getCurrentContext();
                HttpServletRequest request = ctx.getRequest();
                log.info(String.format("收到 %s 请求 %s", request.getMethod(), request.getRequestURL().toString()));

                Object accessToken = request.getParameter("accesstoken");
                if(accessToken == null) {
                    ctx.getResponse().setContentType("text/html;charset=UTF-8");
                    log.warn("accesstoken为空");
                    //令zuul过滤该请求，不对其进行路由
                    ctx.setSendZuulResponse(false);
                    //设置其返回的错误码和报文体
                    //这里没有设置应答码为401，是因为401会导致客户端走到它的断路器里面（HystrixCalculatorService）
                    //所有设置为200，让应答报文体跳过客户端的断路器，返回给前台
                    ctx.setResponseStatusCode(401);
                    ctx.setResponseBody("权限不足");
                    return null;
                }
                log.info("accesstoken验证通过");
                return null;
            }

            /**
             * 如下所示，Zuul定义了四种不同生命周期的过滤器类型
             * pre    ：可以在请求被路由之前调用
             * routing：在路由请求时候被调用
             * post   ：在routing和error过滤器之后被调用
             * error  ：处理请求时发生错误时被调用
             */
            @Override
            public String filterType() {
                return "pre";
            }

            @Override
            public int filterOrder() {
                //通过int值来定义过滤器的执行顺序
                return 0;
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayBootstart.class, args);
    }
}
