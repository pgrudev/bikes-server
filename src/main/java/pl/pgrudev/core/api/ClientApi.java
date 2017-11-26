package pl.pgrudev.core.api;

import pl.pgrudev.core.api.annotations.LoginNotRequired;
import pl.pgrudev.core.api.annotations.PublicApi;
import pl.pgrudev.core.session.Response;

import javax.inject.Named;
import java.util.List;

@PublicApi
@Named
public interface ClientApi {
    public Response login(String login, String password);

    public Response logout();

    @LoginNotRequired
    public Response ping();

    public Response getStation(int stationId);

    public Response getStations(List<Integer> stationsId);

    public Response getStationsDictionary();

    public Response addFavouriteStation(int stationId);

    public Response removeFavouriteStation(int stationId);

    public Response getUserInfo();

    public Response teaserStats();

    @LoginNotRequired
    public boolean isLogged();
}
