import java.io.File;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class Jetty {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
        //test
		// TODO Auto-generated method stub
		Server server = new Server();
		Connector connector = new SelectChannelConnector();

		connector.setPort(8080);
		server.setConnectors(new Connector[] { connector });
		
		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath("/jetty");
		webapp.setResourceBase("../jetty/src/main/webapp");
		webapp.setTempDirectory(new File("e:/temp"));
		
		server.setHandler(webapp);
		server.start();
		server.join();
	}

}
