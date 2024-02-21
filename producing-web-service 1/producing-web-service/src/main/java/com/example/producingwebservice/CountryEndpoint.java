package com.example.producingwebservice;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.tempuri.GetBlazeResponse;
import org.tempuri.GetBlazeResponseResponse;

import jakarta.xml.bind.JAXBElement;

import javax.xml.namespace.QName;

@Endpoint
public class CountryEndpoint {
	private static final String NAMESPACE_URI = "http://tempuri.org/";


	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetBlazeResponse")
	@ResponsePayload
	public GetBlazeResponseResponse getCountry(@RequestPayload GetBlazeResponse request) throws URISyntaxException, IOException {
		GetBlazeResponseResponse finalResponse = new GetBlazeResponseResponse();
		JAXBElement<String> element = request.getRs();
		String soapRequest = element.getValue();
		int startIndex = soapRequest.indexOf("<request>");
        int endIndex = soapRequest.indexOf("</request>") + "</request>".length();
        String xmlRequest=soapRequest.substring(startIndex, endIndex);
        
//      System.out.println(xmlRequest);
        
        // Sending the request and receiving the response
        
        // Setting up the Link of the Nextgen logic app.
        URI nextGenLogicAppURL = new URI("https://logic-dcs-test-dev.azurewebsites.net/api/test-blaze-nextgen-soap/triggers/Blaze-NextGen-SoapConnector/invoke?api-version=2022-05-01&sp=%2Ftriggers%2FBlaze-NextGen-SoapConnector%2Frun&sv=1.0&sig=y-AOomfS2DLtjOt4PYtMmenXu2w3aJCAbKQ27enDnms");

        // Creating an instance of HttpClient
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(nextGenLogicAppURL);

        // Setting the content type of the request as XML
        httpPost.setHeader("Content-type", "application/xml");

        // Setting the body of the request as the received XML Payload from Blaze.
        StringEntity reqEntity = new StringEntity (xmlRequest);
//        StringEntity reqEntity = new StringEntity ("<request><invocation><applicationNumber>DF-A0108513001</applicationNumber><applicationDateTime>2019-04-29T15:55:32</applicationDateTime><decisionService>HongKongAuto</decisionService><applicationCategory>Company</applicationCategory></invocation></request>");
        httpPost.setEntity(reqEntity);

        // Sending the request and getting the response
        CloseableHttpResponse response = httpclient.execute(httpPost);

        // Printing the response received from the logic app to the console
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");
        QName qname = new QName("", "string");
        JAXBElement<String> jaxbElement = new JAXBElement<String>(qname, String.class, responseString);
        finalResponse.setGetBlazeResponseResult(jaxbElement);
//      System.out.println(responseString);

        // Cleaning up the HttpClient resources
        response.close();
        httpclient.close();
        
		return finalResponse;
	}
}