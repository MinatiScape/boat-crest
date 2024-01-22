package com.coveiot.android.remotecommandframework.alexa.model;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.messaging.Constants;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SAlarmInfo extends SCommandInfo {
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
    private boolean active;
    @SerializedName(DeviceKey.KAlarmId)
    private int alarmId;
    @SerializedName(Constants.ScionAnalytics.PARAM_LABEL)
    @Nullable
    private String label;
    @SerializedName("repeat")
    private boolean repeat;
    @SerializedName(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP)
    @Nullable
    private String time;
    @SerializedName("type")
    @Nullable
    private String type;
    @SerializedName("weekDays")
    @Nullable
    private String weekDays;

    public final boolean getActive() {
        return this.active;
    }

    public final int getAlarmId() {
        return this.alarmId;
    }

    @Nullable
    public final String getLabel() {
        return this.label;
    }

    public final boolean getRepeat() {
        return this.repeat;
    }

    @Nullable
    public final String getTime() {
        return this.time;
    }

    @Nullable
    public final String getType() {
        return this.type;
    }

    @Nullable
    public final String getWeekDays() {
        return this.weekDays;
    }

    public final void setActive(boolean z) {
        this.active = z;
    }

    public final void setAlarmId(int i) {
        this.alarmId = i;
    }

    public final void setLabel(@Nullable String str) {
        this.label = str;
    }

    public final void setRepeat(boolean z) {
        this.repeat = z;
    }

    public final void setTime(@Nullable String str) {
        this.time = str;
    }

    public final void setType(@Nullable String str) {
        this.type = str;
    }

    public final void setWeekDays(@Nullable String str) {
        this.weekDays = str;
    }
}
