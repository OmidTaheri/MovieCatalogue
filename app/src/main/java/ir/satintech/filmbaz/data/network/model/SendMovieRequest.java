package ir.satintech.filmbaz.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class SendMovieRequest implements Parcelable {
    private String title;
    private String imdb_id;
    private  String country;
    private  int year;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof SendMovieRequest)) return false;

        SendMovieRequest that = (SendMovieRequest) o;

        return new EqualsBuilder()
                .append(getYear(), that.getYear())
                .append(getTitle(), that.getTitle())
                .append(getImdb_id(), that.getImdb_id())
                .append(getCountry(), that.getCountry())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getTitle())
                .append(getImdb_id())
                .append(getCountry())
                .append(getYear())
                .toHashCode();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.imdb_id);
        dest.writeString(this.country);
        dest.writeInt(this.year);
    }

    public SendMovieRequest() {
    }

    protected SendMovieRequest(Parcel in) {
        this.title = in.readString();
        this.imdb_id = in.readString();
        this.country = in.readString();
        this.year = in.readInt();
    }

    public static final Creator<SendMovieRequest> CREATOR = new Creator<SendMovieRequest>() {
        @Override
        public SendMovieRequest createFromParcel(Parcel source) {
            return new SendMovieRequest(source);
        }

        @Override
        public SendMovieRequest[] newArray(int size) {
            return new SendMovieRequest[size];
        }
    };
}
