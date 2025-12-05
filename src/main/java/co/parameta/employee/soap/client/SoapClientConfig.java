package co.parameta.employee.soap.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * Configuration for SOAP client.
 * Configures WebServiceTemplate for SOAP client calls.
 */
@Configuration
public class SoapClientConfig {

    @Value("${soap.service.url:http://localhost:8080/ws}")
    private String soapServiceUrl;

    /**
     * Configures JAXB marshaller for SOAP messages.
     */
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("co.parameta.employee.soap.model");
        return marshaller;
    }

    /**
     * Configures WebServiceTemplate for SOAP client calls.
     */
    @Bean
    public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller) {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setMarshaller(marshaller);
        webServiceTemplate.setUnmarshaller(marshaller);
        webServiceTemplate.setDefaultUri(soapServiceUrl);
        return webServiceTemplate;
    }
}

