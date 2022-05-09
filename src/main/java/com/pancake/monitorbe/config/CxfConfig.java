package com.pancake.monitorbe.config;


import javax.xml.ws.Endpoint;

import com.pancake.monitorbe.service.SoapService;
import com.pancake.monitorbe.service.impl.SoapServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SOAP服务（CXF框架）配置
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/5/4 1:20
 */
@Configuration
public class CxfConfig {

    @Bean
    public ServletRegistrationBean createServletRegistrationBean() {
        return new ServletRegistrationBean(new CXFServlet(), "/ws/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public SoapService soapService() {
        return new SoapServiceImpl();
    }

    //此处要注意导入正取的Endpoint、EndpointImpl包
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), soapService());
        endpoint.publish("/SoapService");
        return endpoint;
    }
}
