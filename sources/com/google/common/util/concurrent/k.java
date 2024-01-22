package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Ordering;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtIncompatible
/* loaded from: classes10.dex */
public final class k {

    /* renamed from: a  reason: collision with root package name */
    public static final Ordering<Constructor<?>> f10809a = Ordering.natural().onResultOf(new a()).reverse();

    /* loaded from: classes10.dex */
    public class a implements Function<Constructor<?>, Boolean> {
        @Override // com.google.common.base.Function
        /* renamed from: a */
        public Boolean apply(Constructor<?> constructor) {
            return Boolean.valueOf(Arrays.asList(constructor.getParameterTypes()).contains(String.class));
        }
    }

    @VisibleForTesting
    /* loaded from: classes10.dex */
    public interface b {
        void validateClass(Class<? extends Exception> cls);
    }

    @VisibleForTesting
    /* loaded from: classes10.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public static final String f10810a = c.class.getName().concat("$ClassValueValidator");
        public static final b b = a();

        /* loaded from: classes10.dex */
        public enum a implements b {
            INSTANCE;
            
            private static final Set<WeakReference<Class<? extends Exception>>> validClasses = new CopyOnWriteArraySet();

            @Override // com.google.common.util.concurrent.k.b
            public void validateClass(Class<? extends Exception> cls) {
                for (WeakReference<Class<? extends Exception>> weakReference : validClasses) {
                    if (cls.equals(weakReference.get())) {
                        return;
                    }
                }
                k.b(cls);
                Set<WeakReference<Class<? extends Exception>>> set = validClasses;
                if (set.size() > 1000) {
                    set.clear();
                }
                set.add(new WeakReference<>(cls));
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static b a() {
            try {
                return (b) Class.forName(f10810a).getEnumConstants()[0];
            } catch (Throwable unused) {
                return k.k();
            }
        }
    }

    public static b a() {
        return c.b;
    }

    @VisibleForTesting
    public static void b(Class<? extends Exception> cls) {
        Preconditions.checkArgument(g(cls), "Futures.getChecked exception type (%s) must not be a RuntimeException", cls);
        Preconditions.checkArgument(f(cls), "Futures.getChecked exception type (%s) must be an accessible class with an accessible constructor whose parameters (if any) must be of type String and/or Throwable", cls);
    }

    @VisibleForTesting
    @CanIgnoreReturnValue
    public static <V, X extends Exception> V c(b bVar, Future<V> future, Class<X> cls) throws Exception {
        bVar.validateClass(cls);
        try {
            return future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw i(cls, e);
        } catch (ExecutionException e2) {
            l(e2.getCause(), cls);
            throw new AssertionError();
        }
    }

    @CanIgnoreReturnValue
    public static <V, X extends Exception> V d(Future<V> future, Class<X> cls) throws Exception {
        return (V) c(a(), future, cls);
    }

    @CanIgnoreReturnValue
    public static <V, X extends Exception> V e(Future<V> future, Class<X> cls, long j, TimeUnit timeUnit) throws Exception {
        a().validateClass(cls);
        try {
            return future.get(j, timeUnit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw i(cls, e);
        } catch (ExecutionException e2) {
            l(e2.getCause(), cls);
            throw new AssertionError();
        } catch (TimeoutException e3) {
            throw i(cls, e3);
        }
    }

    public static boolean f(Class<? extends Exception> cls) {
        try {
            i(cls, new Exception());
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    @VisibleForTesting
    public static boolean g(Class<? extends Exception> cls) {
        return !RuntimeException.class.isAssignableFrom(cls);
    }

    @NullableDecl
    public static <X> X h(Constructor<X> constructor, Throwable th) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object[] objArr = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> cls = parameterTypes[i];
            if (cls.equals(String.class)) {
                objArr[i] = th.toString();
            } else if (!cls.equals(Throwable.class)) {
                return null;
            } else {
                objArr[i] = th;
            }
        }
        try {
            return constructor.newInstance(objArr);
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    public static <X extends Exception> X i(Class<X> cls, Throwable th) {
        for (Constructor constructor : j(Arrays.asList(cls.getConstructors()))) {
            X x = (X) h(constructor, th);
            if (x != null) {
                if (x.getCause() == null) {
                    x.initCause(th);
                }
                return x;
            }
        }
        String valueOf = String.valueOf(cls);
        StringBuilder sb = new StringBuilder(valueOf.length() + 82);
        sb.append("No appropriate constructor for exception of type ");
        sb.append(valueOf);
        sb.append(" in response to chained exception");
        throw new IllegalArgumentException(sb.toString(), th);
    }

    public static <X extends Exception> List<Constructor<X>> j(List<Constructor<X>> list) {
        return (List<Constructor<X>>) f10809a.sortedCopy(list);
    }

    @VisibleForTesting
    public static b k() {
        return c.a.INSTANCE;
    }

    public static <X extends Exception> void l(Throwable th, Class<X> cls) throws Exception {
        if (!(th instanceof Error)) {
            if (th instanceof RuntimeException) {
                throw new UncheckedExecutionException(th);
            }
            throw i(cls, th);
        }
        throw new ExecutionError((Error) th);
    }
}
