package pl.pgrudev.core.api;

import pl.pgrudev.core.session.Response;

import java.util.List;

@PublicApi
public interface ClientApi {
    public Response login(String login, String password);

    public Response logout();

    public Response ping();

    public Response getStation(int stationId);

    public Response getStations(List<Integer> stationsId);

    public Response getStationsDictionary();

    public Response addFavouriteStation(int stationId);

    public Response removeFavouriteStation(int stationId);

    public Response getUserInfo();

    public Response teaserStats();

    public boolean isLogged();
}
