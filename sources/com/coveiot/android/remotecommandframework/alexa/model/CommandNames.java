package com.coveiot.android.remotecommandframework.alexa.model;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public enum CommandNames {
    SET_ALARM("SetAlarm"),
    GET_ALARM("GetAlarm"),
    GET_BATTERY("GetBattery"),
    GET_USER_DAY_SUMMARY("GetUserDaySummary"),
    SET_DND("SetDnd"),
    SET_LIFT_WRIST_TO_VIEW("SetLiftWristToView"),
    SET_TIME_FORMAT("SetClockFormat"),
    SET_SEDENTARY_REMINDER("SetSedentaryReminder"),
    SET_AUTO_HR("SetAutoHr"),
    SET_STEP_TARGET("SetStepTarget"),
    SET_SLEEP_TARGET("SetSleepTarget"),
    SET_FITNESS_CONFIG_INFO("SetFitnessConfigInfo"),
    SET_MEASUREMENT_UNIT("SetMeasurementUnit"),
    SET_NOTIFICATION("SetNotification");
    
    @NotNull
    private String value;

    CommandNames(String str) {
        this.value = str;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }

    public final void setValue(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.value = str;
    }
}
