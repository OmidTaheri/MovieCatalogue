
package ir.satintech.filmbaz.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class MovieByGenretResponse implements Parcelable
{

    @SerializedName("data")
    @Expose
    private List<Movie> data = null;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
    public final static Creator<MovieByGenretResponse> CREATOR = new Creator<MovieByGenretResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MovieByGenretResponse createFromParcel(Parcel in) {
            return new MovieByGenretResponse(in);
        }

        public MovieByGenretResponse[] newArray(int size) {
            return (new MovieByGenretResponse[size]);
        }

    }
    ;

    protected MovieByGenretResponse(Parcel in) {
        in.readList(this.data, (Movie.class.getClassLoader()));
        this.metadata = ((Metadata) in.readValue((Metadata.class.getClassLoader())));
    }

    public MovieByGenretResponse() {
    }

    public List<Movie> getData() {
        return data;
    }

    public void setData(List<Movie> data) {
        this.data = data;
    }

    public MovieByGenretResponse withData(List<Movie> data) {
        this.data = data;
        return this;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public MovieByGenretResponse withMetadata(Metadata metadata) {
        this.metadata = metadata;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("data", data).append("metadata", metadata).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(data).append(metadata).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MovieByGenretResponse) == false) {
            return false;
        }
        MovieByGenretResponse rhs = ((MovieByGenretResponse) other);
        return new EqualsBuilder().append(data, rhs.data).append(metadata, rhs.metadata).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
        dest.writeValue(metadata);
    }

    public int describeContents() {
        return  0;
    }

}
