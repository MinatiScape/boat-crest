package com.coveiot.android.respiratoryrate.utils;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jstyle.blesdk1860.constant.BleConst;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public enum Constants {
    EMPTY_STRING(""),
    EMPTY_SPACE(HexStringBuilder.DEFAULT_SEPARATOR),
    EMPTY_RESPIRATORY_RATE("--"),
    SHARE_DATA("share_data"),
    FITNESS_COMPUTED_API_CALL_INTERVAL("2"),
    SUBSCRIPTION_INTERVAL("2"),
    DAY("DAY"),
    WEEK("WEEK"),
    MONTH("MONTH"),
    SECONDS("SECONDS"),
    PERCENTAGE("PERCENTAGE"),
    AIML_ALGO("AIML_ALGO"),
    NOT_AVAILABLE("NOT_AVAILABLE"),
    SLEEP_COMPUTATION("SLEEP_COMPUTATION"),
    ENERGY_COMPUTATION("ENERGY_COMPUTATION"),
    FITNESS_COMPUTATION("FITNESS_COMPUTATION"),
    SYNC_START_TIME(BleConst.GetDeviceBatteryLevel),
    SYNC_END_TIME(BleConst.SetAlarmClockWithAllClock),
    BASE_UNIT_BRPM("BRPM"),
    TIME_OFFSET("+05:30"),
    DEFAULT_AGE("199");
    
    @NotNull
    private String value;

    Constants(String str) {
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
