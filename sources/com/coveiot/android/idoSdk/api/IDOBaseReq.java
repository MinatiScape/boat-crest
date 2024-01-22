package com.coveiot.android.idoSdk.api;

import com.coveiot.android.idoSdk.IDOBleCommandName;
import com.coveiot.android.idoSdk.IDOResponseListener;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\n\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0019\u0010\u001aJ\b\u0010\u0003\u001a\u00020\u0002H&J\b\u0010\u0004\u001a\u00020\u0002H&J\b\u0010\u0006\u001a\u00020\u0005H&J\b\u0010\b\u001a\u00020\u0007H&R$\u0010\u0010\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0018\u001a\u0004\u0018\u00010\u00118\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u001b"}, d2 = {"Lcom/coveiot/android/idoSdk/api/IDOBaseReq;", "", "", "isMultiPacket", "isPriority", "", "getTimeOut", "Lcom/coveiot/android/idoSdk/IDOBleCommandName;", "getCommandName", "Lcom/coveiot/android/idoSdk/IDOResponseListener;", "a", "Lcom/coveiot/android/idoSdk/IDOResponseListener;", "getResponseListener", "()Lcom/coveiot/android/idoSdk/IDOResponseListener;", "setResponseListener", "(Lcom/coveiot/android/idoSdk/IDOResponseListener;)V", "responseListener", "", "b", "Ljava/lang/String;", "getReqId", "()Ljava/lang/String;", "setReqId", "(Ljava/lang/String;)V", "reqId", "<init>", "()V", "idoSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public abstract class IDOBaseReq {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public IDOResponseListener f4601a;
    @Nullable
    public String b;

    @NotNull
    public abstract IDOBleCommandName getCommandName();

    @Nullable
    public final String getReqId() {
        return this.b;
    }

    @Nullable
    public final IDOResponseListener getResponseListener() {
        return this.f4601a;
    }

    public abstract int getTimeOut();

    public abstract boolean isMultiPacket();

    public abstract boolean isPriority();

    public final void setReqId(@Nullable String str) {
        this.b = str;
    }

    public final void setResponseListener(@Nullable IDOResponseListener iDOResponseListener) {
        this.f4601a = iDOResponseListener;
    }
}
