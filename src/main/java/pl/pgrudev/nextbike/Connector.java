package pl.pgrudev.nextbike;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.pgrudev.nextbike.model.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.net.MalformedURLException;
import java.net.URL;

public class Connector {
    private final static Logger logger = LogManager.getLogger(Connector.class);

    public static <T extends TransferClass> T downloadNewData(Type type, String param) {
        switch (type) {
            case UNIVERSE:
                return download(Universe.class, "", param);
            case CITY:
                return download(City.class, "?city=", param);
            case STATION:
                return download(Station.class, "?place=", param);
            case COUNTRY:
                return download(Country.class, "?domains=", param);
            default:
                return null;
        }
    }

    private static <T extends TransferClass> T download(Class classType, String suffix, String param) {
        T result = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(classType);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            //todo put in properties file

            // URL url = (new File("C:\\Users\\Paweł\\Desktop\\nextbike-official.xml").toURI().toURL());

            URL url = new URL("https://nextbike.net/maps/nextbike-official.xml" + suffix + param);
            logger.debug("Bikes data downloading");
            result = (T) unmarshaller.unmarshal(url);
            logger.info("Bikes data downloaded");

        } catch (JAXBException | MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public enum Type {
        UNIVERSE,
        COUNTRY,
        CITY,
        STATION
    }

}
