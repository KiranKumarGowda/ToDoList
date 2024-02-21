package com.example.producingwebservice;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<>(servlet, "/ws/*");
	}

	@Bean(name = "b_soap")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema b_soapSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SoapPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://tempuri.org/");
		wsdl11Definition.setSchema(b_soapSchema);
//		wsdl11Definition.setSchemaCollection(b_soapSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema b_soapSchema() {
		return new SimpleXsdSchema(new ClassPathResource("b_soap.xsd"));
	}
	
//	@Bean
//	public XsdSchemaCollection b_soapSchema() {
//		return new CommonsXsdSchemaCollection(new ClassPathResource("b_soap.xsd"), new ClassPathResource("b_soap_InlineSchema1.xsd"), new ClassPathResource("b_soap_InlineSchema2.xsd"), new ClassPathResource("b_soap_InlineSchema3.xsd"));
//	}
}