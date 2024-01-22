package com.coveiot.coveaccess.sos.model;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class SosEventReq implements Serializable {
    @SerializedName("events")
    @Expose
    private List<EventItem> eventItems;

    /* loaded from: classes8.dex */
    public static class EventItem implements Serializable {
        @SerializedName("contact")
        @Expose
        private ContactItem contactItems;
        @SerializedName("duration")
        @Expose
        private String duration;
        @SerializedName("eventDate")
        @Expose
        private String eventDate;
        @SerializedName(FirebaseAnalytics.Param.LOCATION)
        @Expose
        private Location location;
        @SerializedName(Constants.KEY_MESSAGE)
        @Expose
        private String message;
        @SerializedName(NotificationCompat.CATEGORY_STATUS)
        @Expose
        private String status;
        @SerializedName("type")
        @Expose
        private String type;

        /* loaded from: classes8.dex */
        public static class ContactItem implements Serializable {
            @SerializedName("mobileNumber")
            @Expose
            private String mobileNumber;
            @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
            @Expose
            private String name;

            public String getMobileNumber() {
                return this.mobileNumber;
            }

            public String getName() {
                return this.name;
            }

            public void setMobileNumber(String str) {
                this.mobileNumber = str;
            }

            public void setName(String str) {
                this.name = str;
            }

            public String toString() {
                return "ContactItem{name='" + this.name + "', mobileNumber=" + this.mobileNumber + '}';
            }
        }

        /* loaded from: classes8.dex */
        public static class Location implements Serializable {
            @SerializedName("coordinates")
            @Expose
            private List<Double> coordinates;
            @SerializedName("type")
            @Expose
            private String type;

            public List<Double> getCoordinates() {
                return this.coordinates;
            }

            public String getType() {
                return this.type;
            }

            public void setCoordinates(List<Double> list) {
                this.coordinates = list;
            }

            public void setType(String str) {
                this.type = str;
            }

            public String toString() {
                return "Location{type='" + this.type + "', coordinates=" + this.coordinates + '}';
            }
        }

        public ContactItem getContactItems() {
            return this.contactItems;
        }

        public String getDuration() {
            return this.duration;
        }

        public String getEventDate() {
            return this.eventDate;
        }

        public Location getLocation() {
            return this.location;
        }

        public String getMessage() {
            return this.message;
        }

        public String getStatus() {
            return this.status;
        }

        public String getType() {
            return this.type;
        }

        public void setContactItems(ContactItem contactItem) {
            this.contactItems = contactItem;
        }

        public void setDuration(String str) {
            this.duration = str;
        }

        public void setEventDate(String str) {
            this.eventDate = str;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public void setMessage(String str) {
            this.message = str;
        }

        public void setStatus(String str) {
            this.status = str;
        }

        public void setType(String str) {
            this.type = str;
        }

        public String toString() {
            return "EventItem{type='" + this.type + "', contactItems=" + this.contactItems + ", status='" + this.status + "', duration='" + this.duration + "', message='" + this.message + "', eventDate='" + this.eventDate + "', location=" + this.location + '}';
        }
    }

    public List<EventItem> getEventItems() {
        return this.eventItems;
    }

    public void setEventItems(List<EventItem> list) {
        this.eventItems = list;
    }

    public String toString() {
        return "SosEventRes{eventItems=" + this.eventItems + '}';
    }
}
