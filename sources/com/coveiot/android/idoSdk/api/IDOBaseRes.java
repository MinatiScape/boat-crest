package com.coveiot.android.idoSdk.api;

import com.google.android.material.color.c;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u001b\u0010\u001cR\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\u000f\u001a\u0004\u0018\u00010\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u0013\u001a\u0004\u0018\u00010\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\n\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000eR\"\u0010\u0017\u001a\u00020\u00148\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006\u001d"}, d2 = {"Lcom/coveiot/android/idoSdk/api/IDOBaseRes;", "", "Lcom/coveiot/android/idoSdk/api/IDOBaseReq;", "baseReq", "Lcom/coveiot/android/idoSdk/api/IDOBaseReq;", "getBaseReq", "()Lcom/coveiot/android/idoSdk/api/IDOBaseReq;", "setBaseReq", "(Lcom/coveiot/android/idoSdk/api/IDOBaseReq;)V", "a", "Ljava/lang/Object;", "getObj", "()Ljava/lang/Object;", "setObj", "(Ljava/lang/Object;)V", "obj", "b", "getObj2", "setObj2", "obj2", "", c.f10260a, "Z", "isComplete", "()Z", "setComplete", "(Z)V", "<init>", "()V", "idoSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class IDOBaseRes {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Object f4602a;
    @Nullable
    public Object b;
    public IDOBaseReq baseReq;
    public boolean c;

    @NotNull
    public final IDOBaseReq getBaseReq() {
        IDOBaseReq iDOBaseReq = this.baseReq;
        if (iDOBaseReq != null) {
            return iDOBaseReq;
        }
        Intrinsics.throwUninitializedPropertyAccessException("baseReq");
        throw null;
    }

    @Nullable
    public final Object getObj() {
        return this.f4602a;
    }

    @Nullable
    public final Object getObj2() {
        return this.b;
    }

    public final boolean isComplete() {
        return this.c;
    }

    public final void setBaseReq(@NotNull IDOBaseReq iDOBaseReq) {
        Intrinsics.checkNotNullParameter(iDOBaseReq, "<set-?>");
        this.baseReq = iDOBaseReq;
    }

    public final void setComplete(boolean z) {
        this.c = z;
    }

    public final void setObj(@Nullable Object obj) {
        this.f4602a = obj;
    }

    public final void setObj2(@Nullable Object obj) {
        this.b = obj;
    }
}
