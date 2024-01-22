package com.coveiot.android.eastapexsdk.api;

import com.coveiot.android.eastapexsdk.EastApexResponseListener;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0014\u0010\u0015J\b\u0010\u0003\u001a\u00020\u0002H&J\b\u0010\u0004\u001a\u00020\u0002H&J\b\u0010\u0006\u001a\u00020\u0005H&R$\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR$\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/coveiot/android/eastapexsdk/api/EastApexBaseReq;", "", "", "isMultiPacket", "isPriority", "", "getCommandType", "Lcom/coveiot/android/eastapexsdk/EastApexResponseListener;", "responseListener", "Lcom/coveiot/android/eastapexsdk/EastApexResponseListener;", "getResponseListener", "()Lcom/coveiot/android/eastapexsdk/EastApexResponseListener;", "setResponseListener", "(Lcom/coveiot/android/eastapexsdk/EastApexResponseListener;)V", "reqId", "Ljava/lang/String;", "getReqId", "()Ljava/lang/String;", "setReqId", "(Ljava/lang/String;)V", "<init>", "()V", "eastapexSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes4.dex */
public abstract class EastApexBaseReq {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public EastApexResponseListener f4363a;
    @Nullable
    public String b;

    @NotNull
    public abstract String getCommandType();

    @Nullable
    public final String getReqId() {
        return this.b;
    }

    @Nullable
    public final EastApexResponseListener getResponseListener() {
        return this.f4363a;
    }

    public abstract boolean isMultiPacket();

    public abstract boolean isPriority();

    public final void setReqId(@Nullable String str) {
        this.b = str;
    }

    public final void setResponseListener(@Nullable EastApexResponseListener eastApexResponseListener) {
        this.f4363a = eastApexResponseListener;
    }
}
