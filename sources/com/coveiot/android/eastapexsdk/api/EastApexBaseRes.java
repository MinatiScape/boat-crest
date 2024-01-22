package com.coveiot.android.eastapexsdk.api;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0018\u0010\u0019R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\t\u001a\u0004\u0018\u00010\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u000f\u001a\u0004\u0018\u00010\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\"\u0010\u0013\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0013\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u001a"}, d2 = {"Lcom/coveiot/android/eastapexsdk/api/EastApexBaseRes;", "", "Lcom/coveiot/android/eastapexsdk/api/EastApexBaseReq;", "baseReq", "Lcom/coveiot/android/eastapexsdk/api/EastApexBaseReq;", "getBaseReq", "()Lcom/coveiot/android/eastapexsdk/api/EastApexBaseReq;", "setBaseReq", "(Lcom/coveiot/android/eastapexsdk/api/EastApexBaseReq;)V", "obj", "Ljava/lang/Object;", "getObj", "()Ljava/lang/Object;", "setObj", "(Ljava/lang/Object;)V", "obj2", "getObj2", "setObj2", "", "isComplete", "Z", "()Z", "setComplete", "(Z)V", "<init>", "()V", "eastapexSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes4.dex */
public final class EastApexBaseRes {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Object f4364a;
    @Nullable
    public Object b;
    public EastApexBaseReq baseReq;
    public boolean c;

    @NotNull
    public final EastApexBaseReq getBaseReq() {
        EastApexBaseReq eastApexBaseReq = this.baseReq;
        if (eastApexBaseReq != null) {
            return eastApexBaseReq;
        }
        Intrinsics.throwUninitializedPropertyAccessException("baseReq");
        throw null;
    }

    @Nullable
    public final Object getObj() {
        return this.f4364a;
    }

    @Nullable
    public final Object getObj2() {
        return this.b;
    }

    public final boolean isComplete() {
        return this.c;
    }

    public final void setBaseReq(@NotNull EastApexBaseReq eastApexBaseReq) {
        Intrinsics.checkNotNullParameter(eastApexBaseReq, "<set-?>");
        this.baseReq = eastApexBaseReq;
    }

    public final void setComplete(boolean z) {
        this.c = z;
    }

    public final void setObj(@Nullable Object obj) {
        this.f4364a = obj;
    }

    public final void setObj2(@Nullable Object obj) {
        this.b = obj;
    }
}
