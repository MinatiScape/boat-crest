package androidx.startup;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.tracing.Trace;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class AppInitializer {
    public static volatile AppInitializer d;
    public static final Object e = new Object();
    @NonNull
    public final Context c;
    @NonNull
    public final Set<Class<? extends Initializer<?>>> b = new HashSet();
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final Map<Class<?>, Object> f1688a = new HashMap();

    public AppInitializer(@NonNull Context context) {
        this.c = context.getApplicationContext();
    }

    @NonNull
    public static AppInitializer getInstance(@NonNull Context context) {
        if (d == null) {
            synchronized (e) {
                if (d == null) {
                    d = new AppInitializer(context);
                }
            }
        }
        return d;
    }

    public void a() {
        try {
            try {
                Trace.beginSection("Startup");
                b(this.c.getPackageManager().getProviderInfo(new ComponentName(this.c.getPackageName(), InitializationProvider.class.getName()), 128).metaData);
            } catch (PackageManager.NameNotFoundException e2) {
                throw new StartupException(e2);
            }
        } finally {
            Trace.endSection();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void b(@Nullable Bundle bundle) {
        String string = this.c.getString(R.string.androidx_startup);
        if (bundle != null) {
            try {
                HashSet hashSet = new HashSet();
                for (String str : bundle.keySet()) {
                    if (string.equals(bundle.getString(str, null))) {
                        Class<?> cls = Class.forName(str);
                        if (Initializer.class.isAssignableFrom(cls)) {
                            this.b.add(cls);
                        }
                    }
                }
                for (Class<? extends Initializer<?>> cls2 : this.b) {
                    d(cls2, hashSet);
                }
            } catch (ClassNotFoundException e2) {
                throw new StartupException(e2);
            }
        }
    }

    @NonNull
    public <T> T c(@NonNull Class<? extends Initializer<?>> cls) {
        T t;
        synchronized (e) {
            t = (T) this.f1688a.get(cls);
            if (t == null) {
                t = (T) d(cls, new HashSet());
            }
        }
        return t;
    }

    @NonNull
    public final <T> T d(@NonNull Class<? extends Initializer<?>> cls, @NonNull Set<Class<?>> set) {
        T t;
        if (Trace.isEnabled()) {
            try {
                Trace.beginSection(cls.getSimpleName());
            } finally {
                Trace.endSection();
            }
        }
        if (!set.contains(cls)) {
            if (!this.f1688a.containsKey(cls)) {
                set.add(cls);
                Initializer<?> newInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                List<Class<? extends Initializer<?>>> dependencies = newInstance.dependencies();
                if (!dependencies.isEmpty()) {
                    for (Class<? extends Initializer<?>> cls2 : dependencies) {
                        if (!this.f1688a.containsKey(cls2)) {
                            d(cls2, set);
                        }
                    }
                }
                t = (T) newInstance.create(this.c);
                set.remove(cls);
                this.f1688a.put(cls, t);
            } else {
                t = (T) this.f1688a.get(cls);
            }
            return t;
        }
        throw new IllegalStateException(String.format("Cannot initialize %s. Cycle detected.", cls.getName()));
    }

    @NonNull
    public <T> T initializeComponent(@NonNull Class<? extends Initializer<T>> cls) {
        return (T) c(cls);
    }

    public boolean isEagerlyInitialized(@NonNull Class<? extends Initializer<?>> cls) {
        return this.b.contains(cls);
    }
}
