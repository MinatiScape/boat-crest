package com.google.android.recaptcha.internal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public abstract class zzbc implements InvocationHandler {
    @Nullable
    private final Object zza;

    public zzbc(@Nullable Object obj) {
        this.zza = obj;
    }

    @Override // java.lang.reflect.InvocationHandler
    @NotNull
    public final Object invoke(@NotNull Object obj, @NotNull Method method, @Nullable Object[] objArr) {
        Object obj2;
        if (Intrinsics.areEqual(method.getName(), "toString") && method.getParameterTypes().length == 0) {
            return "Proxy@".concat(String.valueOf(Integer.toHexString(obj.hashCode())));
        }
        if (Intrinsics.areEqual(method.getName(), "hashCode") && method.getParameterTypes().length == 0) {
            return Integer.valueOf(System.identityHashCode(obj));
        }
        if (Intrinsics.areEqual(method.getName(), "equals") && method.getParameterTypes().length != 0) {
            boolean z = false;
            if (objArr != null && objArr.length != 0 && objArr[0].hashCode() == obj.hashCode()) {
                z = true;
            }
            return Boolean.valueOf(z);
        } else if (zza(obj, method, objArr)) {
            if ((this.zza == null && Intrinsics.areEqual(method.getReturnType(), Void.TYPE)) || ((obj2 = this.zza) != null && Intrinsics.areEqual(zzep.zza(obj2.getClass()), zzep.zza(method.getReturnType())))) {
                Object obj3 = this.zza;
                return obj3 == null ? Unit.INSTANCE : obj3;
            }
            Object obj4 = this.zza;
            Class<?> returnType = method.getReturnType();
            throw new IllegalArgumentException(obj4 + " cannot be returned from method with return type " + returnType);
        } else {
            return Unit.INSTANCE;
        }
    }

    public abstract boolean zza(@NotNull Object obj, @NotNull Method method, @Nullable Object[] objArr);
}
