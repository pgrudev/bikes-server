package pl.pgrudev.client;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.pgrudev.nextbike.model.Station;

import java.util.Date;
import java.util.List;

@Document
public class User {
    @Id
    private ObjectId repositoryId;
    private String firstName;
    private String lastName;
    private List<Station> favouriteStations;
    private String login;
    private String password;
    private int userLevel;
    @CreatedDate
    private Date repositoryCreatedDate;
    @LastModifiedDate
    private Date repositoryLastModifiedDate;

    public User() {
    }

    public User(String firstName, String lastName,String login, int userLevel) {
        this.repositoryId = new ObjectId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.userLevel = userLevel;
    }

    public ObjectId getId() {
        return repositoryId;
    }

    public void setId(ObjectId repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Station> getFavouriteStations() {
        return favouriteStations;
    }

    public void setFavouriteStations(List<Station> favouriteStations) {
        this.favouriteStations = favouriteStations;
    }

    public Date getRepositoryCreatedDate() {
        return repositoryCreatedDate;
    }

    public void setRepositoryCreatedDate(Date repositoryCreatedDate) {
        this.repositoryCreatedDate = repositoryCreatedDate;
    }

    public Date getRepositoryLastModifiedDate() {
        return repositoryLastModifiedDate;
    }

    public void setRepositoryLastModifiedDate(Date repositoryLastModifiedDate) {
        this.repositoryLastModifiedDate = repositoryLastModifiedDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", favouriteStations=" + favouriteStations +
                ", login='" + login + '\'' +
                ", userLevel='" + userLevel + '\'' +
                ", repositoryCreatedDate=" + repositoryCreatedDate +
                ", repositoryLastModifiedDate=" + repositoryLastModifiedDate +
                '}';
    }
}
