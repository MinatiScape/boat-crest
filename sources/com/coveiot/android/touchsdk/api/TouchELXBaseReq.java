package com.coveiot.android.touchsdk.api;

import android.graphics.Bitmap;
import com.coveiot.android.touchsdk.TouchELXResponseListener;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public abstract class TouchELXBaseReq {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public TouchELXResponseListener f6106a;
    @Nullable
    public String b;
    @Nullable
    public Bitmap c;
    @Nullable
    public String d;

    @Nullable
    public final Bitmap getBitmap() {
        return this.c;
    }

    @Nullable
    public final String getFilePath() {
        return this.d;
    }

    @Nullable
    public final String getReqId() {
        return this.b;
    }

    @Nullable
    public final TouchELXResponseListener getResponseListener() {
        return this.f6106a;
    }

    public abstract boolean isMultiPacket();

    public abstract boolean isPriority();

    public final void setBitmap(@Nullable Bitmap bitmap) {
        this.c = bitmap;
    }

    public final void setFilePath(@Nullable String str) {
        this.d = str;
    }

    public final void setReqId(@Nullable String str) {
        this.b = str;
    }

    public final void setResponseListener(@Nullable TouchELXResponseListener touchELXResponseListener) {
        this.f6106a = touchELXResponseListener;
    }
}
