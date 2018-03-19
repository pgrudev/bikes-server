package pl.pgrudev.nextbike;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import pl.pgrudev.nextbike.model.TransferClass;
import pl.pgrudev.nextbike.model.Universe;
import scala.annotation.meta.param;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

public class Connector {
    private final static Logger logger = LogManager.getLogger(Connector.class);

    private String source;

    public Connector(String source) {
        this.source = source;
    }

    public <T extends TransferClass> T downloadNewData(Type type, String param) {
        logger.info("Request downloading data for: " + type);
        switch (type) {
            case UNIVERSE:
                return (T) download("", param);
            case CITY:
                Universe universeCity = download("?city=", param);
                return (T) universeCity.getCountries().get(0).getCityList().get(0);
            case STATION:
                Universe universeStation = download("?place=", param);
                return (T) universeStation.getCountries().get(0).getCityList().get(0).getStations().get(0);
            case COUNTRY:
                Universe universeCountry = download("?countries=", param);
                return (T) universeCountry;
            default:
                return null;
        }
    }

    private Universe download(String suffix, String param) {
        Universe result = null;
        try {
            logger.debug("Bikes data downloading");
            URL url = new URL( source + suffix + param);
            InputStream input = url.openStream();
            String inputString = new BufferedReader(new InputStreamReader(input))
                    .lines().collect(Collectors.joining("\n"));
            Gson gson = new Gson();
            result = gson.fromJson(inputString, Universe.class);
            logger.info("Bikes data downloaded");
        } catch (IOException e) {
            logger.error("IOException occurred while downloading data: " + e);
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
