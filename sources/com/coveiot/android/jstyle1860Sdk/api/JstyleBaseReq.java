package com.coveiot.android.jstyle1860Sdk.api;

import android.graphics.Bitmap;
import com.coveiot.android.jstyle1860Sdk.JstyleResponseListener;
import com.jstyle.blesdk1860.constant.DeviceKey;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\f\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b%\u0010&J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&J\b\u0010\u0005\u001a\u00020\u0004H&J\b\u0010\u0006\u001a\u00020\u0004H&R$\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR$\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0015\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014R$\u0010\u0018\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0010\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014R$\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R$\u0010\"\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\"\u0010\u0010\u001a\u0004\b#\u0010\u0012\"\u0004\b$\u0010\u0014¨\u0006'"}, d2 = {"Lcom/coveiot/android/jstyle1860Sdk/api/JstyleBaseReq;", "", "", "getCommandBytes", "", "isMultiPacket", "isPriority", "Lcom/coveiot/android/jstyle1860Sdk/JstyleResponseListener;", "responseListener", "Lcom/coveiot/android/jstyle1860Sdk/JstyleResponseListener;", "getResponseListener", "()Lcom/coveiot/android/jstyle1860Sdk/JstyleResponseListener;", "setResponseListener", "(Lcom/coveiot/android/jstyle1860Sdk/JstyleResponseListener;)V", "", "reqId", "Ljava/lang/String;", "getReqId", "()Ljava/lang/String;", "setReqId", "(Ljava/lang/String;)V", DeviceKey.DataType, "getDataType", "setDataType", "filePath", "getFilePath", "setFilePath", "Landroid/graphics/Bitmap;", "bitmap", "Landroid/graphics/Bitmap;", "getBitmap", "()Landroid/graphics/Bitmap;", "setBitmap", "(Landroid/graphics/Bitmap;)V", "watchFaceFilePath", "getWatchFaceFilePath", "setWatchFaceFilePath", "<init>", "()V", "jstyle1860Sdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public abstract class JstyleBaseReq {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public JstyleResponseListener f4604a;
    @Nullable
    public String b;
    @Nullable
    public String c;
    @Nullable
    public String d;
    @Nullable
    public Bitmap e;
    @Nullable
    public String f;

    @Nullable
    public final Bitmap getBitmap() {
        return this.e;
    }

    @Nullable
    public abstract byte[] getCommandBytes();

    @Nullable
    public final String getDataType() {
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
    public final JstyleResponseListener getResponseListener() {
        return this.f4604a;
    }

    @Nullable
    public final String getWatchFaceFilePath() {
        return this.f;
    }

    public abstract boolean isMultiPacket();

    public abstract boolean isPriority();

    public final void setBitmap(@Nullable Bitmap bitmap) {
        this.e = bitmap;
    }

    public final void setDataType(@Nullable String str) {
        this.c = str;
    }

    public final void setFilePath(@Nullable String str) {
        this.d = str;
    }

    public final void setReqId(@Nullable String str) {
        this.b = str;
    }

    public final void setResponseListener(@Nullable JstyleResponseListener jstyleResponseListener) {
        this.f4604a = jstyleResponseListener;
    }

    public final void setWatchFaceFilePath(@Nullable String str) {
        this.f = str;
    }
}
