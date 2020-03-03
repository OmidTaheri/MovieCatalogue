
package ir.satintech.filmbaz.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class Movie implements Parcelable
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
    private String year;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("imdb_rating")
    @Expose
    private String imdbRating;
    @SerializedName("genres")
    @Expose
    private List<String> genres = null;
    @SerializedName("images")
    @Expose
    private List<String> images = null;
    public final static Creator<Movie> CREATOR = new Creator<Movie>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return (new Movie[size]);
        }

    }
    ;

    protected Movie(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.poster = ((String) in.readValue((String.class.getClassLoader())));
        this.year = ((String) in.readValue((String.class.getClassLoader())));
        this.country = ((String) in.readValue((String.class.getClassLoader())));
        this.imdbRating = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.genres, (String.class.getClassLoader()));
        in.readList(this.images, (String.class.getClassLoader()));
    }

    public Movie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie withId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Movie withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Movie withPoster(String poster) {
        this.poster = poster;
        return this;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Movie withYear(String year) {
        this.year = year;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Movie withCountry(String country) {
        this.country = country;
        return this;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public Movie withImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
        return this;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Movie withGenres(List<String> genres) {
        this.genres = genres;
        return this;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Movie withImages(List<String> images) {
        this.images = images;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("title", title).append("poster", poster).append("year", year).append("country", country).append("imdbRating", imdbRating).append("genres", genres).append("images", images).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(imdbRating).append(title).append(genres).append(poster).append(images).append(year).append(country).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Movie) == false) {
            return false;
        }
        Movie rhs = ((Movie) other);
        return new EqualsBuilder().append(id, rhs.id).append(imdbRating, rhs.imdbRating).append(title, rhs.title).append(genres, rhs.genres).append(poster, rhs.poster).append(images, rhs.images).append(year, rhs.year).append(country, rhs.country).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(title);
        dest.writeValue(poster);
        dest.writeValue(year);
        dest.writeValue(country);
        dest.writeValue(imdbRating);
        dest.writeList(genres);
        dest.writeList(images);
    }

    public int describeContents() {
        return  0;
    }

}
