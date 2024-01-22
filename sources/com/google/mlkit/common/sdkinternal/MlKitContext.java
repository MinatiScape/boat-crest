package com.google.mlkit.common.sdkinternal;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentDiscovery;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.ComponentRuntime;
import com.google.mlkit.common.internal.MlKitComponentDiscoveryService;
import java.util.List;
@KeepForSdk
/* loaded from: classes10.dex */
public class MlKitContext {
    public static final Object b = new Object();
    @Nullable
    public static MlKitContext c;
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public ComponentRuntime f11589a;

    public static Context a(Context context) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext != null ? applicationContext : context;
    }

    @NonNull
    @KeepForSdk
    public static MlKitContext getInstance() {
        MlKitContext mlKitContext;
        synchronized (b) {
            Preconditions.checkState(c != null, "MlKitContext has not been initialized");
            mlKitContext = (MlKitContext) Preconditions.checkNotNull(c);
        }
        return mlKitContext;
    }

    @NonNull
    @KeepForSdk
    public static MlKitContext initialize(@NonNull Context context, @NonNull List<ComponentRegistrar> list) {
        MlKitContext mlKitContext;
        synchronized (b) {
            Preconditions.checkState(c == null, "MlKitContext is already initialized");
            MlKitContext mlKitContext2 = new MlKitContext();
            c = mlKitContext2;
            ComponentRuntime componentRuntime = new ComponentRuntime(TaskExecutors.MAIN_THREAD, list, Component.of(a(context), Context.class, new Class[0]), Component.of(mlKitContext2, MlKitContext.class, new Class[0]));
            mlKitContext2.f11589a = componentRuntime;
            componentRuntime.initializeEagerComponents(true);
            mlKitContext = c;
        }
        return mlKitContext;
    }

    @NonNull
    @KeepForSdk
    public static MlKitContext initializeIfNeeded(@NonNull Context context) {
        MlKitContext mlKitContext;
        synchronized (b) {
            mlKitContext = c;
            if (mlKitContext == null) {
                mlKitContext = zza(context);
            }
        }
        return mlKitContext;
    }

    @NonNull
    public static MlKitContext zza(@NonNull Context context) {
        MlKitContext mlKitContext;
        synchronized (b) {
            Preconditions.checkState(c == null, "MlKitContext is already initialized");
            MlKitContext mlKitContext2 = new MlKitContext();
            c = mlKitContext2;
            Context a2 = a(context);
            ComponentRuntime build = ComponentRuntime.builder(TaskExecutors.MAIN_THREAD).addLazyComponentRegistrars(ComponentDiscovery.forContext(a2, MlKitComponentDiscoveryService.class).discoverLazy()).addComponent(Component.of(a2, Context.class, new Class[0])).addComponent(Component.of(mlKitContext2, MlKitContext.class, new Class[0])).build();
            mlKitContext2.f11589a = build;
            build.initializeEagerComponents(true);
            mlKitContext = c;
        }
        return mlKitContext;
    }

    @NonNull
    @KeepForSdk
    public <T> T get(@NonNull Class<T> cls) {
        Preconditions.checkState(c == this, "MlKitContext has been deleted");
        Preconditions.checkNotNull(this.f11589a);
        return (T) this.f11589a.get(cls);
    }

    @NonNull
    @KeepForSdk
    public Context getApplicationContext() {
        return (Context) get(Context.class);
    }

    @NonNull
    @KeepForSdk
    public static MlKitContext initializeIfNeeded(@NonNull Context context, @NonNull List<ComponentRegistrar> list) {
        MlKitContext mlKitContext;
        synchronized (b) {
            mlKitContext = c;
            if (mlKitContext == null) {
                mlKitContext = initialize(context, list);
            }
        }
        return mlKitContext;
    }
}
