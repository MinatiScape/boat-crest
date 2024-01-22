package com.coveiot.android.remotecommandframework.alexa.utils;

import com.coveiot.android.watchfaceui.constants.WatchfaceConstants;
import com.jstyle.blesdk1860.constant.BleConst;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public enum Constants {
    MIN_FILTER_VALUE("34.9"),
    MAX_FILTER_VALUE("41.1"),
    DEFAULT_STEPS_TARGET("10000"),
    DEFAULT_SLEEP_TARGET(BleConst.SetStepGoal),
    HEIGHT_DEFAULT("165"),
    WEIGHT_DEFAULT(BleConst.GetMusicControl),
    MAX_AVG_ALARM_COUNT(BleConst.SetDeviceInfo),
    TIME_FORMAT_12H(WatchfaceConstants.H12),
    TIME_FORMAT_24H(WatchfaceConstants.H24),
    CONNECTION_TIMEOUT(BleConst.EnterActivityMode),
    KEEP_ALIVE_INTERVAL(BleConst.ReadHeartateSensorstatus),
    MQTT_PORT_NUMBER("8883"),
    MQTT_SSL_PROTOCOL("ssl"),
    MQTT_QOS_1("1"),
    DEFAULT_REQUEST_PRIORITY("100"),
    ENABLE_NOTIFICATION_LISTENERS("enabled_notification_listeners"),
    CONTENT_TYPE_CBOR("cbor"),
    CONTENT_TYPE_JSON("json");
    
    @NotNull
    private final String value;

    Constants(String str) {
        this.value = str;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }
}
