package com.coveiot.android.jstyle1860Sdk.model;

import com.jstyle.blesdk1860.constant.DeviceKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0010\u0010\u0011R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\"\u0010\n\u001a\u00020\u00018\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/coveiot/android/jstyle1860Sdk/model/JstyleLiveResponse;", "", "", "a", "Ljava/lang/String;", "getDataType", "()Ljava/lang/String;", "setDataType", "(Ljava/lang/String;)V", DeviceKey.DataType, "obj", "Ljava/lang/Object;", "getObj", "()Ljava/lang/Object;", "setObj", "(Ljava/lang/Object;)V", "<init>", "()V", "jstyle1860Sdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class JstyleLiveResponse {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f4610a;
    public Object obj;

    @Nullable
    public final String getDataType() {
        return this.f4610a;
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

    public final void setDataType(@Nullable String str) {
        this.f4610a = str;
    }

    public final void setObj(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.obj = obj;
    }
}
