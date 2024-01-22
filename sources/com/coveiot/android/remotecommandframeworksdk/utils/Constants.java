package com.coveiot.android.remotecommandframeworksdk.utils;

import com.jstyle.blesdk1860.constant.BleConst;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lcom/coveiot/android/remotecommandframeworksdk/utils/Constants;", "", "", "value", "Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "CONNECTION_TIMEOUT", "KEEP_ALIVE_INTERVAL", "MQTT_PORT_NUMBER", "MQTT_SSL_PROTOCOL", "MQTT_QOS_1", "CONTENT_TYPE_CBOR", "CONTENT_TYPE_JSON", "remotecommandframeworksdk_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public enum Constants {
    CONNECTION_TIMEOUT(BleConst.EnterActivityMode),
    KEEP_ALIVE_INTERVAL(BleConst.ReadHeartateSensorstatus),
    MQTT_PORT_NUMBER("8883"),
    MQTT_SSL_PROTOCOL("ssl"),
    MQTT_QOS_1("1"),
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
