package pl.pgrudev.nextbike;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.pgrudev.nextbike.model.TransferClass;
import pl.pgrudev.nextbike.model.Universe;

import java.io.*;
import java.net.URL;
import java.util.stream.Collectors;

public class Connector {
    private final static Logger logger = LogManager.getLogger(Connector.class);

    public static <T extends TransferClass> T downloadNewData(Type type, String param) {
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

    private static Universe download(String suffix, String param) {
        Universe result = null;
        try {
            //todo put in properties file
            logger.debug("Bikes data downloading");
            //URL url = new URL("https://nextbike.net/maps/nextbike-official.json" + suffix + param);
            File local = new File("C:\\Users\\Paweł\\Desktop\\nextbike-official.json");
            URL url = new URL(local.toURI().toURL().toString() + suffix + param);
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
