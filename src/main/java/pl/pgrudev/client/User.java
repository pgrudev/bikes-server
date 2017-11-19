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
    @CreatedDate
    private Date repositoryCreatedDate;
    @LastModifiedDate
    private Date repositoryLastModifiedDate;

    public User() {
    }

    public User(String firstName, String lastName) {
        this.repositoryId = new ObjectId();
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", repositoryCreatedDate=" + repositoryCreatedDate +
                ", repositoryLastModifiedDate=" + repositoryLastModifiedDate +
                '}';
    }
}
