package com.coveiot.coveaccess.timeline.model;

import com.coveiot.coveaccess.mediauplaod.model.MediaListBean;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class TimeLineCheckInData extends TimeLineData {
    public String address;
    public int dwellRadius;
    public int dwellThreshold;
    public double latitude;
    public String locality;
    public double longitude;
    public List<MediaListBean> mediaListBeanList;
    public String placeName;
    public String placeSelectedTime;
    public String placeVicivity;
    public String place_id;
    public PredictedPlaceBean predictedPlaceBean;

    /* loaded from: classes8.dex */
    public static class PredictedPlaceBean {
        @SerializedName("mediaList")
        private List<MediaListBean> mediaListX;
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
        private String name;
        @SerializedName("placeId")
        private String placeId;
        @SerializedName("vicinity")
        private String vicinity;

        public List<MediaListBean> getMediaListX() {
            return this.mediaListX;
        }

        public String getName() {
            return this.name;
        }

        public String getPlaceId() {
            return this.placeId;
        }

        public String getVicinity() {
            return this.vicinity;
        }

        public void setMediaListX(List<MediaListBean> list) {
            this.mediaListX = list;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setPlaceId(String str) {
            this.placeId = str;
        }

        public void setVicinity(String str) {
            this.vicinity = str;
        }

        public String toString() {
            return "PredictedPlaceBean{placeId='" + this.placeId + "', name='" + this.name + "', vicinity='" + this.vicinity + "', mediaListX=" + this.mediaListX + '}';
        }
    }

    public TimeLineCheckInData(TimelineCardType timelineCardType, String str, String str2, double d, double d2, String str3, String str4) {
        super(timelineCardType, str, str2);
        this.latitude = d;
        this.longitude = d2;
        this.locality = str3;
        this.address = str4;
    }

    public String getAddress() {
        return this.address;
    }

    public int getDwellRadius() {
        return this.dwellRadius;
    }

    public int getDwellThreshold() {
        return this.dwellThreshold;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public String getLocality() {
        return this.locality;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public List<MediaListBean> getMediaListBeanList() {
        return this.mediaListBeanList;
    }

    public String getPlaceName() {
        return this.placeName;
    }

    public String getPlaceSelectedTime() {
        return this.placeSelectedTime;
    }

    public String getPlaceVicivity() {
        return this.placeVicivity;
    }

    public String getPlace_id() {
        return this.place_id;
    }

    public PredictedPlaceBean getPredictedPlaceBean() {
        return this.predictedPlaceBean;
    }

    public void setDwellRadius(int i) {
        this.dwellRadius = i;
    }

    public void setDwellThreshold(int i) {
        this.dwellThreshold = i;
    }

    public void setMediaListBeanList(List<MediaListBean> list) {
        this.mediaListBeanList = list;
    }

    public void setPlaceName(String str) {
        this.placeName = str;
    }

    public void setPlaceSelectedTime(String str) {
        this.placeSelectedTime = str;
    }

    public void setPlaceVicivity(String str) {
        this.placeVicivity = str;
    }

    public void setPlace_id(String str) {
        this.place_id = str;
    }

    public void setPredictedPlaceBean(PredictedPlaceBean predictedPlaceBean) {
        this.predictedPlaceBean = predictedPlaceBean;
    }

    @Override // com.coveiot.coveaccess.timeline.model.TimeLineData
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TimeLineCheckInData{latitude=");
        sb.append(this.latitude);
        sb.append(", longitude=");
        sb.append(this.longitude);
        sb.append(", locality='");
        sb.append(this.locality);
        sb.append('\'');
        sb.append(", address='");
        sb.append(this.address);
        sb.append('\'');
        sb.append(", place_id='");
        sb.append(this.place_id);
        sb.append('\'');
        sb.append(", placeName='");
        sb.append(this.placeName);
        sb.append('\'');
        sb.append(", placeVicivity='");
        sb.append(this.placeVicivity);
        sb.append('\'');
        sb.append(", placeSelectedTime='");
        sb.append(this.placeSelectedTime);
        sb.append('\'');
        sb.append(", mediaListBeanList=");
        sb.append(this.mediaListBeanList);
        sb.append(", dwellRadius=");
        sb.append(this.dwellRadius);
        sb.append(", dwellThreshold=");
        sb.append(this.dwellThreshold);
        sb.append(", predictedPlaceBean=");
        PredictedPlaceBean predictedPlaceBean = this.predictedPlaceBean;
        sb.append(predictedPlaceBean == null ? "null" : predictedPlaceBean.toString());
        sb.append(", timelineCardType=");
        sb.append(this.timelineCardType);
        sb.append(", timeStamp='");
        sb.append(this.timeStamp);
        sb.append('\'');
        sb.append(", date='");
        sb.append(this.date);
        sb.append('\'');
        sb.append(", logId='");
        sb.append(this.logId);
        sb.append('\'');
        sb.append('}');
        return sb.toString();
    }
}
