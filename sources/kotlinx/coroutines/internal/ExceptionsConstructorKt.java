package kotlinx.coroutines.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CopyableThrowable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class ExceptionsConstructorKt {

    /* renamed from: a */
    public static final int f14174a = e(Throwable.class, -1);
    @NotNull
    public static final CtorCache b;

    /* loaded from: classes12.dex */
    public static final class a extends Lambda implements Function1 {
        public static final a INSTANCE = new a();

        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        @Nullable
        public final Void invoke(@NotNull Throwable th) {
            return null;
        }
    }

    static {
        CtorCache ctorCache;
        try {
            ctorCache = FastServiceLoaderKt.getANDROID_DETECTED() ? f.f14191a : ClassValueCtorCache.f14169a;
        } catch (Throwable unused) {
            ctorCache = f.f14191a;
        }
        b = ctorCache;
    }

    public static final <E extends Throwable> Function1<Throwable, Throwable> a(Class<E> cls) {
        a aVar = a.INSTANCE;
        if (f14174a != e(cls, 0)) {
            return aVar;
        }
        for (Constructor constructor : ArraysKt___ArraysKt.sortedWith(cls.getConstructors(), new Comparator() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createConstructor$$inlined$sortedByDescending$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return kotlin.comparisons.f.compareValues(Integer.valueOf(((Constructor) t2).getParameterTypes().length), Integer.valueOf(((Constructor) t).getParameterTypes().length));
            }
        })) {
            Function1<Throwable, Throwable> b2 = b(constructor);
            if (b2 != null) {
                return b2;
            }
        }
        return aVar;
    }

    public static final /* synthetic */ Function1 access$createConstructor(Class cls) {
        return a(cls);
    }

    public static final Function1<Throwable, Throwable> b(final Constructor<?> constructor) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        int length = parameterTypes.length;
        if (length != 0) {
            if (length != 1) {
                if (length == 2 && Intrinsics.areEqual(parameterTypes[0], String.class) && Intrinsics.areEqual(parameterTypes[1], Throwable.class)) {
                    return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createSafeConstructor$$inlined$safeCtor$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        @Nullable
                        public final Throwable invoke(@NotNull Throwable th) {
                            Object m123constructorimpl;
                            Object newInstance;
                            try {
                                Result.Companion companion = Result.Companion;
                                newInstance = constructor.newInstance(th.getMessage(), th);
                            } catch (Throwable th2) {
                                Result.Companion companion2 = Result.Companion;
                                m123constructorimpl = Result.m123constructorimpl(ResultKt.createFailure(th2));
                            }
                            if (newInstance != null) {
                                m123constructorimpl = Result.m123constructorimpl((Throwable) newInstance);
                                if (Result.m129isFailureimpl(m123constructorimpl)) {
                                    m123constructorimpl = null;
                                }
                                return (Throwable) m123constructorimpl;
                            }
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
                        }
                    };
                }
                return null;
            }
            Class<?> cls = parameterTypes[0];
            if (Intrinsics.areEqual(cls, Throwable.class)) {
                return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createSafeConstructor$$inlined$safeCtor$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    @Nullable
                    public final Throwable invoke(@NotNull Throwable th) {
                        Object m123constructorimpl;
                        Object newInstance;
                        try {
                            Result.Companion companion = Result.Companion;
                            newInstance = constructor.newInstance(th);
                        } catch (Throwable th2) {
                            Result.Companion companion2 = Result.Companion;
                            m123constructorimpl = Result.m123constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (newInstance != null) {
                            m123constructorimpl = Result.m123constructorimpl((Throwable) newInstance);
                            if (Result.m129isFailureimpl(m123constructorimpl)) {
                                m123constructorimpl = null;
                            }
                            return (Throwable) m123constructorimpl;
                        }
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
                    }
                };
            }
            if (Intrinsics.areEqual(cls, String.class)) {
                return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createSafeConstructor$$inlined$safeCtor$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    @Nullable
                    public final Throwable invoke(@NotNull Throwable th) {
                        Object m123constructorimpl;
                        Object newInstance;
                        try {
                            Result.Companion companion = Result.Companion;
                            newInstance = constructor.newInstance(th.getMessage());
                        } catch (Throwable th2) {
                            Result.Companion companion2 = Result.Companion;
                            m123constructorimpl = Result.m123constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (newInstance != null) {
                            Throwable th3 = (Throwable) newInstance;
                            th3.initCause(th);
                            m123constructorimpl = Result.m123constructorimpl(th3);
                            if (Result.m129isFailureimpl(m123constructorimpl)) {
                                m123constructorimpl = null;
                            }
                            return (Throwable) m123constructorimpl;
                        }
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
                    }
                };
            }
            return null;
        }
        return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createSafeConstructor$$inlined$safeCtor$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @Nullable
            public final Throwable invoke(@NotNull Throwable th) {
                Object m123constructorimpl;
                Object newInstance;
                try {
                    Result.Companion companion = Result.Companion;
                    newInstance = constructor.newInstance(new Object[0]);
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.Companion;
                    m123constructorimpl = Result.m123constructorimpl(ResultKt.createFailure(th2));
                }
                if (newInstance != null) {
                    Throwable th3 = (Throwable) newInstance;
                    th3.initCause(th);
                    m123constructorimpl = Result.m123constructorimpl(th3);
                    if (Result.m129isFailureimpl(m123constructorimpl)) {
                        m123constructorimpl = null;
                    }
                    return (Throwable) m123constructorimpl;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
            }
        };
    }

    public static final int c(Class<?> cls, int i) {
        do {
            Field[] declaredFields = cls.getDeclaredFields();
            int length = declaredFields.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                Field field = declaredFields[i2];
                i2++;
                if (!Modifier.isStatic(field.getModifiers())) {
                    i3++;
                }
            }
            i += i3;
            cls = cls.getSuperclass();
        } while (cls != null);
        return i;
    }

    public static /* synthetic */ int d(Class cls, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return c(cls, i);
    }

    public static final int e(Class<?> cls, int i) {
        Integer m123constructorimpl;
        JvmClassMappingKt.getKotlinClass(cls);
        try {
            Result.Companion companion = Result.Companion;
            m123constructorimpl = Result.m123constructorimpl(Integer.valueOf(d(cls, 0, 1, null)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            m123constructorimpl = Result.m123constructorimpl(ResultKt.createFailure(th));
        }
        Integer valueOf = Integer.valueOf(i);
        if (Result.m129isFailureimpl(m123constructorimpl)) {
            m123constructorimpl = valueOf;
        }
        return ((Number) m123constructorimpl).intValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public static final <E extends Throwable> E tryCopyException(@NotNull E e) {
        Object m123constructorimpl;
        if (e instanceof CopyableThrowable) {
            try {
                Result.Companion companion = Result.Companion;
                m123constructorimpl = Result.m123constructorimpl(((CopyableThrowable) e).createCopy());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                m123constructorimpl = Result.m123constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m129isFailureimpl(m123constructorimpl)) {
                m123constructorimpl = null;
            }
            return (E) m123constructorimpl;
        }
        return (E) b.get(e.getClass()).invoke(e);
    }
}
