package com.coveiot.coveaccess.fitnessbuddies.model.lookup;

import com.coveiot.coveaccess.model.CoveApiRequestBaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jieli.watchtesttool.util.WatchTestConstant;
import java.util.List;
/* loaded from: classes8.dex */
public class SendFitnessBuddyLookUpRequest extends CoveApiRequestBaseModel {
    @SerializedName(WatchTestConstant.DIR_CONTACTS)
    @Expose
    private List<BuddyContacts> buddyContactsList;

    /* loaded from: classes8.dex */
    public static class BuddyContacts {
        @SerializedName("mobileNumber")
        @Expose
        public String mobileNumber;

        public BuddyContacts(String str) {
            this.mobileNumber = str;
        }

        public String getMobileNumber() {
            return this.mobileNumber;
        }

        public void setMobileNumber(String str) {
            this.mobileNumber = str;
        }
    }

    public SendFitnessBuddyLookUpRequest(List<BuddyContacts> list) {
        this.buddyContactsList = null;
        this.buddyContactsList = list;
    }

    public List<BuddyContacts> getBuddyContactsList() {
        return this.buddyContactsList;
    }

    public void setBuddyContactsList(List<BuddyContacts> list) {
        this.buddyContactsList = list;
    }
}
