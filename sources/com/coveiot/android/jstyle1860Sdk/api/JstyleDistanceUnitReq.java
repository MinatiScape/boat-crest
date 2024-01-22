package com.coveiot.android.jstyle1860Sdk.api;

import com.jstyle.blesdk1860.Util.BleSDK;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0005\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016R\"\u0010\u0007\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0007\u0010\t\"\u0004\b\n\u0010\u000bR\u0016\u0010\r\u001a\u00020\f8\u0002@\u0002X\u0082D¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\u00020\f8\u0002@\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u000f\u0010\u000e¨\u0006\u0012"}, d2 = {"Lcom/coveiot/android/jstyle1860Sdk/api/JstyleDistanceUnitReq;", "Lcom/coveiot/android/jstyle1860Sdk/api/JstyleBaseReq;", "", "getCommandBytes", "", "isMultiPacket", "isPriority", "isDistanceUnitInMile", "Z", "()Z", "setDistanceUnitInMile", "(Z)V", "", "DISTANCE_MODE_KM", "B", "DISTANCE_MODE_MILE", "<init>", "()V", "jstyle1860Sdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class JstyleDistanceUnitReq extends JstyleBaseReq {
    public boolean g = true;

    @Override // com.coveiot.android.jstyle1860Sdk.api.JstyleBaseReq
    @Nullable
    public byte[] getCommandBytes() {
        return BleSDK.SetDistanceUnit(this.g);
    }

    public final boolean isDistanceUnitInMile() {
        return this.g;
    }

    @Override // com.coveiot.android.jstyle1860Sdk.api.JstyleBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.jstyle1860Sdk.api.JstyleBaseReq
    public boolean isPriority() {
        return true;
    }

    public final void setDistanceUnitInMile(boolean z) {
        this.g = z;
    }
}
