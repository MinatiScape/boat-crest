package com.coveiot.android.idoSdk;

import java.io.Serializable;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0005\u001a\u00020\u00028\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0004R\u0016\u0010\u0006\u001a\u00020\u00028\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0006\u0010\u0004R\u0016\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\u00020\u00078\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\n\u0010\tR\u0016\u0010\u000b\u001a\u00020\u00078\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u000b\u0010\tR\u0016\u0010\f\u001a\u00020\u00028\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\f\u0010\u0004R\u0016\u0010\r\u001a\u00020\u00028\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\r\u0010\u0004R\u0016\u0010\u000e\u001a\u00020\u00028\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u000e\u0010\u0004R\u0016\u0010\u000f\u001a\u00020\u00028\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u000f\u0010\u0004¨\u0006\u0012"}, d2 = {"Lcom/coveiot/android/idoSdk/IDOConstants;", "Ljava/io/Serializable;", "", "DATA_TYPE_ACTIVITY", "I", "FEMALE", "MALE", "", "DATA_TYPE_SPORT", "Ljava/lang/String;", "DATA_TYPE_HEART_RATE", "DATA_TYPE_SLEEP", "MULTI_PACKET_WATCHFACE_CMD_MS_TIMER", "MULTI_PACKET_FIRMWARE_UPGRADE_CMD_MS_TIMER", "MULTI_PACKET_CMD_MS_TIMER", "NORMAL_CMD_MS_TIMER", "<init>", "()V", "idoSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class IDOConstants implements Serializable {
    public static final int DATA_TYPE_ACTIVITY = 1;
    @NotNull
    public static final String DATA_TYPE_HEART_RATE = "HEART_RATE";
    @NotNull
    public static final String DATA_TYPE_SLEEP = "SLEEP";
    @NotNull
    public static final String DATA_TYPE_SPORT = "WALK";
    public static final int FEMALE = 1;
    @NotNull
    public static final IDOConstants INSTANCE = new IDOConstants();
    public static final int MALE = 0;
    public static final int MULTI_PACKET_CMD_MS_TIMER = 120000;
    public static final int MULTI_PACKET_FIRMWARE_UPGRADE_CMD_MS_TIMER = 600000;
    public static final int MULTI_PACKET_WATCHFACE_CMD_MS_TIMER = 180000;
    public static final int NORMAL_CMD_MS_TIMER = 30000;

    private IDOConstants() {
    }
}
