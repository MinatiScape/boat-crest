package com.coveiot.android.jstyle1860Sdk.api;

import com.jstyle.blesdk1860.Util.BleSDK;
import com.jstyle.blesdk1860.model.Clock;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u000f\u0010\u0010J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016R*\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/coveiot/android/jstyle1860Sdk/api/JstyleVibrationAlarmReq;", "Lcom/coveiot/android/jstyle1860Sdk/api/JstyleBaseReq;", "", "getCommandBytes", "", "isMultiPacket", "isPriority", "", "Lcom/jstyle/blesdk1860/model/Clock;", "clockList", "Ljava/util/List;", "getClockList", "()Ljava/util/List;", "setClockList", "(Ljava/util/List;)V", "<init>", "()V", "jstyle1860Sdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class JstyleVibrationAlarmReq extends JstyleBaseReq {
    @Nullable
    public List<? extends Clock> g;

    @Nullable
    public final List<Clock> getClockList() {
        return this.g;
    }

    @Override // com.coveiot.android.jstyle1860Sdk.api.JstyleBaseReq
    @Nullable
    public byte[] getCommandBytes() {
        return BleSDK.setClockData(this.g);
    }

    @Override // com.coveiot.android.jstyle1860Sdk.api.JstyleBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.jstyle1860Sdk.api.JstyleBaseReq
    public boolean isPriority() {
        return true;
    }

    public final void setClockList(@Nullable List<? extends Clock> list) {
        this.g = list;
    }
}
