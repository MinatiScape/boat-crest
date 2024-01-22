package com.coveiot.android.matrixsdk.api;

import android.graphics.Bitmap;
import com.coveiot.android.matrixsdk.MatrixResponseListener;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public abstract class MatrixBaseReq {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public MatrixResponseListener f5467a;
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
    public final MatrixResponseListener getResponseListener() {
        return this.f5467a;
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

    public final void setResponseListener(@Nullable MatrixResponseListener matrixResponseListener) {
        this.f5467a = matrixResponseListener;
    }
}
