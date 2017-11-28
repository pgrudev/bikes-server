package pl.pgrudev.nextbike;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.pgrudev.nextbike.model.Universe;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Connector {
    private final static Logger logger = LogManager.getLogger(Connector.class);

    public static Universe downloadNewData() {
        Universe universe = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Universe.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            // URL url = new URL("https://nextbike.net/maps/nextbike-official.xml");
            URL url = (new File("C:\\Users\\Paweł\\Desktop\\nextbike-official.xml").toURI().toURL());
            logger.debug("Bikes data downloading");
            universe = (Universe) unmarshaller.unmarshal(url);
            logger.info("Bikes data downloaded");

        } catch (JAXBException | MalformedURLException e) {
            logger.error("Could not download bikes data, ", e);
            throw new RuntimeException("Could not download bikes data");
        }
        return universe;
    }
}
