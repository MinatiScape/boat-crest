package com.coveiot.coveaccess.userdevicesetting.model;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class MapNavigationSettings implements Serializable {
    @SerializedName("alwaysOnDisplay")
    @Expose
    private AodSettings aodSettings;
    @SerializedName("audio")
    @Expose
    private AudioSettings audioSettings;
    @SerializedName("vibration")
    @Expose
    private VibrationSettings vibrationSettings;

    /* loaded from: classes8.dex */
    public static class AodSettings implements Serializable {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
        @Expose
        private boolean active;

        public boolean isActive() {
            return this.active;
        }

        public void setActive(boolean z) {
            this.active = z;
        }
    }

    /* loaded from: classes8.dex */
    public static class AudioSettings implements Serializable {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
        @Expose
        private boolean active;

        public boolean isActive() {
            return this.active;
        }

        public void setActive(boolean z) {
            this.active = z;
        }
    }

    /* loaded from: classes8.dex */
    public static class VibrationSettings implements Serializable {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
        @Expose
        private boolean active;

        public boolean isActive() {
            return this.active;
        }

        public void setActive(boolean z) {
            this.active = z;
        }
    }

    public AodSettings getAodSettings() {
        return this.aodSettings;
    }

    public AudioSettings getAudioSettings() {
        return this.audioSettings;
    }

    public VibrationSettings getVibrationSettings() {
        return this.vibrationSettings;
    }

    public void setAodSettings(AodSettings aodSettings) {
        this.aodSettings = aodSettings;
    }

    public void setAudioSettings(AudioSettings audioSettings) {
        this.audioSettings = audioSettings;
    }

    public void setVibrationSettings(VibrationSettings vibrationSettings) {
        this.vibrationSettings = vibrationSettings;
    }
}
