
package ir.satintech.filmbaz.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Metadata implements Parcelable
{

    @SerializedName("current_page")
    @Expose
    private int currentPage;
    @SerializedName("per_page")
    @Expose
    private int perPage;
    @SerializedName("page_count")
    @Expose
    private int pageCount;
    @SerializedName("total_count")
    @Expose
    private int totalCount;
    public final static Creator<Metadata> CREATOR = new Creator<Metadata>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Metadata createFromParcel(Parcel in) {
            return new Metadata(in);
        }

        public Metadata[] newArray(int size) {
            return (new Metadata[size]);
        }

    }
    ;

    protected Metadata(Parcel in) {
        this.currentPage = ((int) in.readValue((int.class.getClassLoader())));
        this.perPage = ((int) in.readValue((int.class.getClassLoader())));
        this.pageCount = ((int) in.readValue((int.class.getClassLoader())));
        this.totalCount = ((int) in.readValue((int.class.getClassLoader())));
    }

    public Metadata() {
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Metadata withCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public Metadata withPerPage(int perPage) {
        this.perPage = perPage;
        return this;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Metadata withPageCount(int pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public Metadata withTotalCount(int totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("currentPage", currentPage).append("perPage", perPage).append("pageCount", pageCount).append("totalCount", totalCount).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(pageCount).append(totalCount).append(perPage).append(currentPage).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Metadata) == false) {
            return false;
        }
        Metadata rhs = ((Metadata) other);
        return new EqualsBuilder().append(pageCount, rhs.pageCount).append(totalCount, rhs.totalCount).append(perPage, rhs.perPage).append(currentPage, rhs.currentPage).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(currentPage);
        dest.writeValue(perPage);
        dest.writeValue(pageCount);
        dest.writeValue(totalCount);
    }

    public int describeContents() {
        return  0;
    }

}
