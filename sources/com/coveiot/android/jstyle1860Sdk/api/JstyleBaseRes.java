package com.coveiot.android.jstyle1860Sdk.api;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u000f\u0010\u0010R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\"\u0010\t\u001a\u00020\u00018\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/coveiot/android/jstyle1860Sdk/api/JstyleBaseRes;", "", "Lcom/coveiot/android/jstyle1860Sdk/api/JstyleBaseReq;", "baseReq", "Lcom/coveiot/android/jstyle1860Sdk/api/JstyleBaseReq;", "getBaseReq", "()Lcom/coveiot/android/jstyle1860Sdk/api/JstyleBaseReq;", "setBaseReq", "(Lcom/coveiot/android/jstyle1860Sdk/api/JstyleBaseReq;)V", "obj", "Ljava/lang/Object;", "getObj", "()Ljava/lang/Object;", "setObj", "(Ljava/lang/Object;)V", "<init>", "()V", "jstyle1860Sdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class JstyleBaseRes {
    public JstyleBaseReq baseReq;
    public Object obj;

    @NotNull
    public final JstyleBaseReq getBaseReq() {
        JstyleBaseReq jstyleBaseReq = this.baseReq;
        if (jstyleBaseReq != null) {
            return jstyleBaseReq;
        }
        Intrinsics.throwUninitializedPropertyAccessException("baseReq");
        throw null;
    }

    @NotNull
    public final Object getObj() {
        Object obj = this.obj;
        if (obj != null) {
            return obj;
        }
        Intrinsics.throwUninitializedPropertyAccessException("obj");
        throw null;
    }

    public final void setBaseReq(@NotNull JstyleBaseReq jstyleBaseReq) {
        Intrinsics.checkNotNullParameter(jstyleBaseReq, "<set-?>");
        this.baseReq = jstyleBaseReq;
    }

    public final void setObj(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.obj = obj;
    }
}
