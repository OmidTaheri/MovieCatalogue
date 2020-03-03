
package ir.satintech.filmbaz.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class SendMovieResponse implements Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("poster")
    @Expose
    private String poster;
    @SerializedName("year")
    @Expose
    private int year;
    @SerializedName("director")
    @Expose
    private String director;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("imdb_rating")
    @Expose
    private String imdbRating;
    @SerializedName("imdb_votes")
    @Expose
    private String imdbVotes;
    @SerializedName("imdb_id")
    @Expose
    private String imdbId;
    public final static Creator<SendMovieResponse> CREATOR = new Creator<SendMovieResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public SendMovieResponse createFromParcel(Parcel in) {
            return new SendMovieResponse(in);
        }

        public SendMovieResponse[] newArray(int size) {
            return (new SendMovieResponse[size]);
        }

    }
    ;

    protected SendMovieResponse(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.poster = ((String) in.readValue((String.class.getClassLoader())));
        this.year = ((int) in.readValue((int.class.getClassLoader())));
        this.director = ((String) in.readValue((String.class.getClassLoader())));
        this.country = ((String) in.readValue((String.class.getClassLoader())));
        this.imdbRating = ((String) in.readValue((String.class.getClassLoader())));
        this.imdbVotes = ((String) in.readValue((String.class.getClassLoader())));
        this.imdbId = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SendMovieResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SendMovieResponse withId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SendMovieResponse withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public SendMovieResponse withPoster(String poster) {
        this.poster = poster;
        return this;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public SendMovieResponse withYear(int year) {
        this.year = year;
        return this;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public SendMovieResponse withDirector(String director) {
        this.director = director;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public SendMovieResponse withCountry(String country) {
        this.country = country;
        return this;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public SendMovieResponse withImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
        return this;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public SendMovieResponse withImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
        return this;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public SendMovieResponse withImdbId(String imdbId) {
        this.imdbId = imdbId;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("title", title).append("poster", poster).append("year", year).append("director", director).append("country", country).append("imdbRating", imdbRating).append("imdbVotes", imdbVotes).append("imdbId", imdbId).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(imdbRating).append(title).append(imdbVotes).append(poster).append(year).append(director).append(imdbId).append(country).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SendMovieResponse) == false) {
            return false;
        }
        SendMovieResponse rhs = ((SendMovieResponse) other);
        return new EqualsBuilder().append(id, rhs.id).append(imdbRating, rhs.imdbRating).append(title, rhs.title).append(imdbVotes, rhs.imdbVotes).append(poster, rhs.poster).append(year, rhs.year).append(director, rhs.director).append(imdbId, rhs.imdbId).append(country, rhs.country).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(title);
        dest.writeValue(poster);
        dest.writeValue(year);
        dest.writeValue(director);
        dest.writeValue(country);
        dest.writeValue(imdbRating);
        dest.writeValue(imdbVotes);
        dest.writeValue(imdbId);
    }

    public int describeContents() {
        return  0;
    }

}
