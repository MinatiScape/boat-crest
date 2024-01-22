package com.coveiot.android.crpsdk.api;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u000f\u0010\u0010R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\t\u001a\u0004\u0018\u00010\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/coveiot/android/crpsdk/api/CRPBaseRes;", "", "Lcom/coveiot/android/crpsdk/api/CRPBaseReq;", "baseReq", "Lcom/coveiot/android/crpsdk/api/CRPBaseReq;", "getBaseReq", "()Lcom/coveiot/android/crpsdk/api/CRPBaseReq;", "setBaseReq", "(Lcom/coveiot/android/crpsdk/api/CRPBaseReq;)V", "obj", "Ljava/lang/Object;", "getObj", "()Ljava/lang/Object;", "setObj", "(Ljava/lang/Object;)V", "<init>", "()V", "crpSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class CRPBaseRes {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Object f4113a;
    public CRPBaseReq baseReq;

    @NotNull
    public final CRPBaseReq getBaseReq() {
        CRPBaseReq cRPBaseReq = this.baseReq;
        if (cRPBaseReq != null) {
            return cRPBaseReq;
        }
        Intrinsics.throwUninitializedPropertyAccessException("baseReq");
        throw null;
    }

    @Nullable
    public final Object getObj() {
        return this.f4113a;
    }

    public final void setBaseReq(@NotNull CRPBaseReq cRPBaseReq) {
        Intrinsics.checkNotNullParameter(cRPBaseReq, "<set-?>");
        this.baseReq = cRPBaseReq;
    }

    public final void setObj(@Nullable Object obj) {
        this.f4113a = obj;
    }
}
