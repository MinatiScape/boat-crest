package com.coveiot.coveaccess.watchface.model;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class WatchFaceCategoriesResponse {
    @SerializedName("data")

    /* renamed from: a  reason: collision with root package name */
    private DataBean f6904a;
    @SerializedName(Constants.KEY_MESSAGE)
    private String b;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String c;

    /* loaded from: classes8.dex */
    public static class DataBean {
        @SerializedName(FirebaseAnalytics.Param.ITEMS)
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private List<ItemsList> f6905a;

        /* loaded from: classes8.dex */
        public static class ItemsList {
            @SerializedName("categoryId")
            @Expose

            /* renamed from: a  reason: collision with root package name */
            private String f6906a;
            @SerializedName("title")
            @Expose
            private String b;
            @SerializedName("newCategory")
            @Expose
            private Boolean c;

            public String getCategoryId() {
                return this.f6906a;
            }

            public Boolean getNewCategory() {
                return this.c;
            }

            public String getTitle() {
                return this.b;
            }

            public void setCategoryId(String str) {
                this.f6906a = str;
            }

            public void setNewCategory(Boolean bool) {
                this.c = bool;
            }

            public void setTitle(String str) {
                this.b = str;
            }
        }

        public List<ItemsList> getItemsLists() {
            return this.f6905a;
        }

        public void setItemsLists(List<ItemsList> list) {
            this.f6905a = list;
        }
    }

    public DataBean getData() {
        return this.f6904a;
    }

    public String getMessage() {
        return this.b;
    }

    public String getStatus() {
        return this.c;
    }

    public void setData(DataBean dataBean) {
        this.f6904a = dataBean;
    }

    public void setMessage(String str) {
        this.b = str;
    }

    public void setStatus(String str) {
        this.c = str;
    }
}
