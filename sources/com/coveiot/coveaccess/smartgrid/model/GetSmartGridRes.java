package com.coveiot.coveaccess.smartgrid.model;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import org.jose4j.jwx.HeaderParameterNames;
/* loaded from: classes8.dex */
public class GetSmartGridRes implements Serializable {
    @SerializedName(FirebaseAnalytics.Param.ITEMS)
    @Expose
    private List<GridItem> gridItems;

    /* loaded from: classes8.dex */
    public static class GridItem {
        @SerializedName("title")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private String f6732a;
        @SerializedName("featureId")
        @Expose
        private String b;
        @SerializedName("iconUrl")
        @Expose
        private String c;
        @SerializedName("ctaType")
        @Expose
        private String d;
        @SerializedName("ctaLink")
        @Expose
        private String e;
        @SerializedName(HeaderParameterNames.AUTHENTICATION_TAG)
        @Expose
        private String f;

        public GridItem(String str, String str2, String str3, String str4, String str5, String str6) {
            this.f6732a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = str5;
            this.f = str6;
        }

        public String getCtaLink() {
            return this.e;
        }

        public String getCtaType() {
            return this.d;
        }

        public String getFeatureId() {
            return this.b;
        }

        public String getIconUrl() {
            return this.c;
        }

        public String getTag() {
            return this.f;
        }

        public String getTitle() {
            return this.f6732a;
        }

        public void setCtaLink(String str) {
            this.e = str;
        }

        public void setCtaType(String str) {
            this.d = str;
        }

        public void setFeatureId(String str) {
            this.b = str;
        }

        public void setIconUrl(String str) {
            this.c = str;
        }

        public void setTag(String str) {
            this.f = str;
        }

        public void setTitle(String str) {
            this.f6732a = str;
        }
    }

    public List<GridItem> getGridItems() {
        return this.gridItems;
    }

    public void setGridItems(List<GridItem> list) {
        this.gridItems = list;
    }
}
