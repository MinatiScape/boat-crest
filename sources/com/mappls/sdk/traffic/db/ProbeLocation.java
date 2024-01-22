package com.mappls.sdk.traffic.db;

import android.location.Location;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
@Entity(tableName = "locations")
@Keep
/* loaded from: classes8.dex */
public class ProbeLocation {
    @SerializedName(SavingTrackHelper.TRACK_COL_ALTITUDE)
    @Expose
    private double altitude;
    @SerializedName("devicetype")
    @Expose
    private String deviceType;
    @SerializedName("enginestate")
    @Expose
    private int engineState;
    @SerializedName("heading")
    @Expose
    private float heading;
    @SerializedName("lat")
    @Expose
    private double latitude;
    @SerializedName("lon")
    @Expose
    private double longitude;
    @SerializedName("speed")
    @Expose
    private float speed;
    @NonNull
    @SerializedName(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP)
    @PrimaryKey
    @Expose
    private String time;

    public ProbeLocation() {
    }

    public ProbeLocation(Location location) {
        this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(location.getTime()));
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        this.speed = location.getSpeed();
        this.heading = location.getBearing();
        this.deviceType = "bike";
        this.engineState = 0;
        this.altitude = location.getAltitude();
    }

    public double getAltitude() {
        return this.altitude;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public int getEngineState() {
        return this.engineState;
    }

    public float getHeading() {
        return this.heading;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public float getSpeed() {
        return this.speed;
    }

    public String getTime() {
        return this.time;
    }

    public void setAltitude(double d) {
        this.altitude = d;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public void setEngineState(int i) {
        this.engineState = i;
    }

    public void setHeading(float f) {
        this.heading = f;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public void setSpeed(float f) {
        this.speed = f;
    }

    public void setTime(String str) {
        this.time = str;
    }
}
