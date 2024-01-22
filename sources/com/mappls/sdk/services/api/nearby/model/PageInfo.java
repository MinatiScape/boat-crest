package com.mappls.sdk.services.api.nearby.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes6.dex */
public class PageInfo {
    @SerializedName("pageCount")
    @Expose
    public Integer pageCount;
    @SerializedName("pageSize")
    @Expose
    public Integer pageSize;
    @SerializedName("totalHits")
    @Expose
    public Integer totalHits;
    @SerializedName("totalPages")
    @Expose
    public Integer totalPages;

    public Integer getPageCount() {
        return this.pageCount;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Integer getTotalHits() {
        return this.totalHits;
    }

    public Integer getTotalPages() {
        return this.totalPages;
    }

    public void setPageCount(Integer num) {
        this.pageCount = num;
    }

    public void setPageSize(Integer num) {
        this.pageSize = num;
    }

    public void setTotalHits(Integer num) {
        this.totalHits = num;
    }

    public void setTotalPages(Integer num) {
        this.totalPages = num;
    }
}
