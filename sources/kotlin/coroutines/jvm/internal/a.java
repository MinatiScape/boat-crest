package kotlin.coroutines.jvm.internal;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.lang.reflect.Method;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class a {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final a f14061a = new a();
    @NotNull
    public static final C0871a b = new C0871a(null, null, null);
    @Nullable
    public static C0871a c;

    /* renamed from: kotlin.coroutines.jvm.internal.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public static final class C0871a {
        @JvmField
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public final Method f14062a;
        @JvmField
        @Nullable
        public final Method b;
        @JvmField
        @Nullable
        public final Method c;

        public C0871a(@Nullable Method method, @Nullable Method method2, @Nullable Method method3) {
            this.f14062a = method;
            this.b = method2;
            this.c = method3;
        }
    }

    public final C0871a a(BaseContinuationImpl baseContinuationImpl) {
        try {
            C0871a c0871a = new C0871a(Class.class.getDeclaredMethod("getModule", new Class[0]), baseContinuationImpl.getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", new Class[0]), baseContinuationImpl.getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod(AppMeasurementSdk.ConditionalUserProperty.NAME, new Class[0]));
            c = c0871a;
            return c0871a;
        } catch (Exception unused) {
            C0871a c0871a2 = b;
            c = c0871a2;
            return c0871a2;
        }
    }

    @Nullable
    public final String b(@NotNull BaseContinuationImpl continuation) {
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        C0871a c0871a = c;
        if (c0871a == null) {
            c0871a = a(continuation);
        }
        if (c0871a == b) {
            return null;
        }
        Method method = c0871a.f14062a;
        Object invoke = method != null ? method.invoke(continuation.getClass(), new Object[0]) : null;
        if (invoke == null) {
            return null;
        }
        Method method2 = c0871a.b;
        Object invoke2 = method2 != null ? method2.invoke(invoke, new Object[0]) : null;
        if (invoke2 == null) {
            return null;
        }
        Method method3 = c0871a.c;
        Object invoke3 = method3 != null ? method3.invoke(invoke2, new Object[0]) : null;
        if (invoke3 instanceof String) {
            return (String) invoke3;
        }
        return null;
    }
}
