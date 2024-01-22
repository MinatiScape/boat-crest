package com.mappls.sdk.services.api.event.nearby.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes4.dex */
public class NearbyReportPagination {
    @SerializedName("totalItems")
    @Expose
    private Integer totalItems;

    public Integer getTotalItems() {
        return this.totalItems;
    }

    public void setTotalItems(Integer num) {
        this.totalItems = num;
    }
}
