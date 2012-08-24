import static org.junit.Assert.*;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class CamelXmlTest extends CamelSpringTestSupport {

	// expected message bodies
	protected Object[] expectedBodies = {
			"<something id='1'>expectedBody1</something>",
			"<something id='2'>expectedBody2</something>" };
	// templates to send to input endpoints
	@Produce(uri = "cxfrs://http://localhost:9090/route2?resourceClasses=com.fusesource.samples.CustomerServiceResource")
	protected ProducerTemplate inputEndpoint;
	@Produce(uri = "cxfrs://http://localhost:9090/route1?resourceClasses=com.fusesource.samples.CustomerServiceResource")
	protected ProducerTemplate input2Endpoint;

	@Test
	public void testCamelRoute() throws Exception {

		// define some expectations
		// TODO add some expectations here!!
		// send some messages to input endpoints
		for (Object expectedBody : expectedBodies) {
			inputEndpoint.sendBody(expectedBody);
		}

		assertMockEndpointsSatisfied();
	}

	@Override
	protected ClassPathXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("META-INF/spring/camel.xml");
	}

}
