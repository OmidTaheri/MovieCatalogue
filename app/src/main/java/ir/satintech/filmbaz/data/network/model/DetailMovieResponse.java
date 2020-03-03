
package ir.satintech.filmbaz.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class DetailMovieResponse implements Parcelable
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
    @SerializedName("rated")
    @Expose
    private String rated;
    @SerializedName("released")
    @Expose
    private String released;
    @SerializedName("runtime")
    @Expose
    private String runtime;
    @SerializedName("director")
    @Expose
    private String director;
    @SerializedName("writer")
    @Expose
    private String writer;
    @SerializedName("actors")
    @Expose
    private String actors;
    @SerializedName("plot")
    @Expose
    private String plot;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("awards")
    @Expose
    private String awards;
    @SerializedName("metascore")
    @Expose
    private String metascore;
    @SerializedName("imdb_rating")
    @Expose
    private String imdbRating;
    @SerializedName("imdb_votes")
    @Expose
    private String imdbVotes;
    @SerializedName("imdb_id")
    @Expose
    private String imdbId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("genres")
    @Expose
    private List<String> genres = null;
    @SerializedName("images")
    @Expose
    private List<String> images = null;
    public final static Creator<DetailMovieResponse> CREATOR = new Creator<DetailMovieResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public DetailMovieResponse createFromParcel(Parcel in) {
            return new DetailMovieResponse(in);
        }

        public DetailMovieResponse[] newArray(int size) {
            return (new DetailMovieResponse[size]);
        }

    }
    ;

    protected DetailMovieResponse(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.poster = ((String) in.readValue((String.class.getClassLoader())));
        this.year = ((String) in.readValue((String.class.getClassLoader())));
        this.rated = ((String) in.readValue((String.class.getClassLoader())));
        this.released = ((String) in.readValue((String.class.getClassLoader())));
        this.runtime = ((String) in.readValue((String.class.getClassLoader())));
        this.director = ((String) in.readValue((String.class.getClassLoader())));
        this.writer = ((String) in.readValue((String.class.getClassLoader())));
        this.actors = ((String) in.readValue((String.class.getClassLoader())));
        this.plot = ((String) in.readValue((String.class.getClassLoader())));
        this.country = ((String) in.readValue((String.class.getClassLoader())));
        this.awards = ((String) in.readValue((String.class.getClassLoader())));
        this.metascore = ((String) in.readValue((String.class.getClassLoader())));
        this.imdbRating = ((String) in.readValue((String.class.getClassLoader())));
        this.imdbVotes = ((String) in.readValue((String.class.getClassLoader())));
        this.imdbId = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.genres, (String.class.getClassLoader()));
        in.readList(this.images, (String.class.getClassLoader()));
    }

    public DetailMovieResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DetailMovieResponse withId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DetailMovieResponse withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public DetailMovieResponse withPoster(String poster) {
        this.poster = poster;
        return this;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public DetailMovieResponse withYear(String year) {
        this.year = year;
        return this;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public DetailMovieResponse withRated(String rated) {
        this.rated = rated;
        return this;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public DetailMovieResponse withReleased(String released) {
        this.released = released;
        return this;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public DetailMovieResponse withRuntime(String runtime) {
        this.runtime = runtime;
        return this;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public DetailMovieResponse withDirector(String director) {
        this.director = director;
        return this;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public DetailMovieResponse withWriter(String writer) {
        this.writer = writer;
        return this;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public DetailMovieResponse withActors(String actors) {
        this.actors = actors;
        return this;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public DetailMovieResponse withPlot(String plot) {
        this.plot = plot;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public DetailMovieResponse withCountry(String country) {
        this.country = country;
        return this;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public DetailMovieResponse withAwards(String awards) {
        this.awards = awards;
        return this;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public DetailMovieResponse withMetascore(String metascore) {
        this.metascore = metascore;
        return this;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public DetailMovieResponse withImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
        return this;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public DetailMovieResponse withImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
        return this;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public DetailMovieResponse withImdbId(String imdbId) {
        this.imdbId = imdbId;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DetailMovieResponse withType(String type) {
        this.type = type;
        return this;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public DetailMovieResponse withGenres(List<String> genres) {
        this.genres = genres;
        return this;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public DetailMovieResponse withImages(List<String> images) {
        this.images = images;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("title", title).append("poster", poster).append("year", year).append("rated", rated).append("released", released).append("runtime", runtime).append("director", director).append("writer", writer).append("actors", actors).append("plot", plot).append("country", country).append("awards", awards).append("metascore", metascore).append("imdbRating", imdbRating).append("imdbVotes", imdbVotes).append("imdbId", imdbId).append("type", type).append("genres", genres).append("images", images).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(metascore).append(genres).append(imdbVotes).append(runtime).append(type).append(director).append(imdbId).append(country).append(plot).append(released).append(id).append(imdbRating).append(title).append(awards).append(poster).append(images).append(actors).append(year).append(writer).append(rated).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DetailMovieResponse) == false) {
            return false;
        }
        DetailMovieResponse rhs = ((DetailMovieResponse) other);
        return new EqualsBuilder().append(metascore, rhs.metascore).append(genres, rhs.genres).append(imdbVotes, rhs.imdbVotes).append(runtime, rhs.runtime).append(type, rhs.type).append(director, rhs.director).append(imdbId, rhs.imdbId).append(country, rhs.country).append(plot, rhs.plot).append(released, rhs.released).append(id, rhs.id).append(imdbRating, rhs.imdbRating).append(title, rhs.title).append(awards, rhs.awards).append(poster, rhs.poster).append(images, rhs.images).append(actors, rhs.actors).append(year, rhs.year).append(writer, rhs.writer).append(rated, rhs.rated).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(title);
        dest.writeValue(poster);
        dest.writeValue(year);
        dest.writeValue(rated);
        dest.writeValue(released);
        dest.writeValue(runtime);
        dest.writeValue(director);
        dest.writeValue(writer);
        dest.writeValue(actors);
        dest.writeValue(plot);
        dest.writeValue(country);
        dest.writeValue(awards);
        dest.writeValue(metascore);
        dest.writeValue(imdbRating);
        dest.writeValue(imdbVotes);
        dest.writeValue(imdbId);
        dest.writeValue(type);
        dest.writeList(genres);
        dest.writeList(images);
    }

    public int describeContents() {
        return  0;
    }

}
