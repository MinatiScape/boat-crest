package com.coveiot.android.jstyle1860Sdk.api;

import com.jstyle.blesdk1860.Util.ResolveUtil;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016J\u0010\u0010\t\u001a\u0004\u0018\u00010\u00022\u0006\u0010\b\u001a\u00020\u0007R\"\u0010\n\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/coveiot/android/jstyle1860Sdk/api/JstyleCustomWatchFaceReq;", "Lcom/coveiot/android/jstyle1860Sdk/api/JstyleBaseReq;", "", "getCommandBytes", "", "isMultiPacket", "isPriority", "", "styleMode", "setWatchFaceCustomStyle", "watchFacePosition", "I", "getWatchFacePosition", "()I", "setWatchFacePosition", "(I)V", "<init>", "()V", "jstyle1860Sdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class JstyleCustomWatchFaceReq extends JstyleBaseReq {
    public int g;

    @Override // com.coveiot.android.jstyle1860Sdk.api.JstyleBaseReq
    @Nullable
    public byte[] getCommandBytes() {
        return setWatchFaceCustomStyle(this.g);
    }

    public final int getWatchFacePosition() {
        return this.g;
    }

    @Override // com.coveiot.android.jstyle1860Sdk.api.JstyleBaseReq
    public boolean isMultiPacket() {
        return true;
    }

    @Override // com.coveiot.android.jstyle1860Sdk.api.JstyleBaseReq
    public boolean isPriority() {
        return false;
    }

    @Nullable
    public final byte[] setWatchFaceCustomStyle(int i) {
        byte[] bArr = new byte[16];
        bArr[0] = 3;
        bArr[12] = (byte) (i + 192);
        ResolveUtil.crcValue(bArr);
        return bArr;
    }

    public final void setWatchFacePosition(int i) {
        this.g = i;
    }
}
