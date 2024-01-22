package com.google.firebase.heartbeatinfo;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Lazy;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes10.dex */
public class DefaultHeartBeatInfo implements HeartBeatInfo {
    public static final ThreadFactory d = new ThreadFactory() { // from class: com.google.firebase.heartbeatinfo.g
        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            Thread i;
            i = DefaultHeartBeatInfo.i(runnable);
            return i;
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public Provider<h> f11278a;
    public final Set<HeartBeatConsumer> b;
    public final Executor c;

    public DefaultHeartBeatInfo(final Context context, Set<HeartBeatConsumer> set) {
        this(new Lazy(new Provider() { // from class: com.google.firebase.heartbeatinfo.d
            @Override // com.google.firebase.inject.Provider
            public final Object get() {
                h c;
                c = h.c(context);
                return c;
            }
        }), set, new ThreadPoolExecutor(0, 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(), d));
    }

    @NonNull
    public static Component<HeartBeatInfo> component() {
        return Component.builder(HeartBeatInfo.class).add(Dependency.required(Context.class)).add(Dependency.setOf(HeartBeatConsumer.class)).factory(new ComponentFactory() { // from class: com.google.firebase.heartbeatinfo.c
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                HeartBeatInfo f;
                f = DefaultHeartBeatInfo.f(componentContainer);
                return f;
            }
        }).build();
    }

    public static /* synthetic */ HeartBeatInfo f(ComponentContainer componentContainer) {
        return new DefaultHeartBeatInfo((Context) componentContainer.get(Context.class), componentContainer.setOf(HeartBeatConsumer.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List g() throws Exception {
        HeartBeatInfo.HeartBeat heartBeat;
        ArrayList arrayList = new ArrayList();
        h hVar = this.f11278a.get();
        List<SdkHeartBeatResult> e = hVar.e(true);
        long d2 = hVar.d();
        for (SdkHeartBeatResult sdkHeartBeatResult : e) {
            boolean f = h.f(d2, sdkHeartBeatResult.getMillis());
            if (f) {
                heartBeat = HeartBeatInfo.HeartBeat.COMBINED;
            } else {
                heartBeat = HeartBeatInfo.HeartBeat.SDK;
            }
            if (f) {
                d2 = sdkHeartBeatResult.getMillis();
            }
            arrayList.add(HeartBeatResult.create(sdkHeartBeatResult.getSdkName(), sdkHeartBeatResult.getMillis(), heartBeat));
        }
        if (d2 > 0) {
            hVar.j(d2);
        }
        return arrayList;
    }

    public static /* synthetic */ Thread i(Runnable runnable) {
        return new Thread(runnable, "heartbeat-information-executor");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Void j(String str) throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.f11278a.get().h(str, currentTimeMillis)) {
            this.f11278a.get().i(str, currentTimeMillis);
            return null;
        }
        return null;
    }

    @Override // com.google.firebase.heartbeatinfo.HeartBeatInfo
    public Task<List<HeartBeatResult>> getAndClearStoredHeartBeatInfo() {
        return Tasks.call(this.c, new Callable() { // from class: com.google.firebase.heartbeatinfo.e
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List g;
                g = DefaultHeartBeatInfo.this.g();
                return g;
            }
        });
    }

    @Override // com.google.firebase.heartbeatinfo.HeartBeatInfo
    @NonNull
    public HeartBeatInfo.HeartBeat getHeartBeatCode(@NonNull String str) {
        long currentTimeMillis = System.currentTimeMillis();
        boolean h = this.f11278a.get().h(str, currentTimeMillis);
        boolean g = this.f11278a.get().g(currentTimeMillis);
        if (h && g) {
            return HeartBeatInfo.HeartBeat.COMBINED;
        }
        if (g) {
            return HeartBeatInfo.HeartBeat.GLOBAL;
        }
        if (h) {
            return HeartBeatInfo.HeartBeat.SDK;
        }
        return HeartBeatInfo.HeartBeat.NONE;
    }

    @Override // com.google.firebase.heartbeatinfo.HeartBeatInfo
    public Task<Void> storeHeartBeatInfo(@NonNull final String str) {
        if (this.b.size() <= 0) {
            return Tasks.forResult(null);
        }
        return Tasks.call(this.c, new Callable() { // from class: com.google.firebase.heartbeatinfo.f
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Void j;
                j = DefaultHeartBeatInfo.this.j(str);
                return j;
            }
        });
    }

    @VisibleForTesting
    public DefaultHeartBeatInfo(Provider<h> provider, Set<HeartBeatConsumer> set, Executor executor) {
        this.f11278a = provider;
        this.b = set;
        this.c = executor;
    }
}
