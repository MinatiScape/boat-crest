package com.coveiot.coveaccess.healthbuddies;

import com.coveiot.coveaccess.model.CoveApiRequestBaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class HealthBuddyRequest extends CoveApiRequestBaseModel {
    @SerializedName("requests")
    @Expose
    private List<HBuddy> buddies;

    public HealthBuddyRequest(List<HBuddy> list) {
        this.buddies = null;
        this.buddies = list;
    }

    public List<HBuddy> getBuddies() {
        return this.buddies;
    }

    /* loaded from: classes8.dex */
    public static class HBuddy {
        @SerializedName("toUserMobileNumber")
        @Expose
        public String mobileNumber;
        @SerializedName("toContactName")
        @Expose
        public String name;
        @SerializedName("relType")
        @Expose
        public String relType;

        public HBuddy(String str, String str2) {
            this.name = str;
            this.mobileNumber = str2;
        }

        public HBuddy(String str, String str2, String str3) {
            this.name = str;
            this.mobileNumber = str2;
            this.relType = str3;
        }
    }
}
