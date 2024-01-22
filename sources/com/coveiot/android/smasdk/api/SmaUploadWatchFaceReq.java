package com.coveiot.android.smasdk.api;

import com.szabh.smable3.watchface.Element;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SmaUploadWatchFaceReq extends SmaBaseReq {
    @Nullable
    public Integer d;
    @Nullable
    public String e;
    @Nullable
    public ArrayList<Element> f;

    public SmaUploadWatchFaceReq(@Nullable Integer num, @Nullable String str, @Nullable ArrayList<Element> arrayList) {
        this.d = num;
        this.e = str;
        this.f = arrayList;
    }

    @Nullable
    public final ArrayList<Element> getWatchFaceElementArray() {
        return this.f;
    }

    @Nullable
    public final String getWatchFaceFilePath() {
        return this.e;
    }

    @Nullable
    public final Integer getWatchFaceResource() {
        return this.d;
    }

    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isMultiPacket() {
        return true;
    }

    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isPriority() {
        return false;
    }

    public final void setWatchFaceElementArray(@Nullable ArrayList<Element> arrayList) {
        this.f = arrayList;
    }

    public final void setWatchFaceFilePath(@Nullable String str) {
        this.e = str;
    }

    public final void setWatchFaceResource(@Nullable Integer num) {
        this.d = num;
    }
}
