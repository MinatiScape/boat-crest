package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SourceDebugExtension({"SMAP\nNetworkObserver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NetworkObserver.kt\ncom/pierfrancescosoffritti/androidyoutubeplayer/core/player/utils/NetworkObserver\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,121:1\n1#2:122\n*E\n"})
/* loaded from: classes9.dex */
public final class NetworkObserver {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f13323a;
    @NotNull
    public final List<Listener> b;
    @Nullable
    public com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.a c;
    @Nullable
    public ConnectivityManager.NetworkCallback d;

    /* loaded from: classes9.dex */
    public interface Listener {
        void onNetworkAvailable();

        void onNetworkUnavailable();
    }

    @SourceDebugExtension({"SMAP\nNetworkObserver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NetworkObserver.kt\ncom/pierfrancescosoffritti/androidyoutubeplayer/core/player/utils/NetworkObserver$doObserveNetworkLegacy$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,121:1\n1855#2,2:122\n*S KotlinDebug\n*F\n+ 1 NetworkObserver.kt\ncom/pierfrancescosoffritti/androidyoutubeplayer/core/player/utils/NetworkObserver$doObserveNetworkLegacy$1\n*L\n80#1:122,2\n*E\n"})
    /* loaded from: classes9.dex */
    public static final class a extends Lambda implements Function0<Unit> {
        public a() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
            for (Listener listener : NetworkObserver.this.getListeners()) {
                listener.onNetworkAvailable();
            }
        }
    }

    @SourceDebugExtension({"SMAP\nNetworkObserver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NetworkObserver.kt\ncom/pierfrancescosoffritti/androidyoutubeplayer/core/player/utils/NetworkObserver$doObserveNetworkLegacy$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,121:1\n1855#2,2:122\n*S KotlinDebug\n*F\n+ 1 NetworkObserver.kt\ncom/pierfrancescosoffritti/androidyoutubeplayer/core/player/utils/NetworkObserver$doObserveNetworkLegacy$2\n*L\n81#1:122,2\n*E\n"})
    /* loaded from: classes9.dex */
    public static final class b extends Lambda implements Function0<Unit> {
        public b() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
            for (Listener listener : NetworkObserver.this.getListeners()) {
                listener.onNetworkUnavailable();
            }
        }
    }

    public NetworkObserver(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f13323a = context;
        this.b = new ArrayList();
    }

    @RequiresApi(24)
    public final void a(Context context) {
        NetworkObserver$doObserveNetwork$callback$1 networkObserver$doObserveNetwork$callback$1 = new NetworkObserver$doObserveNetwork$callback$1(this);
        this.d = networkObserver$doObserveNetwork$callback$1;
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ((ConnectivityManager) systemService).registerDefaultNetworkCallback(networkObserver$doObserveNetwork$callback$1);
    }

    public final void b(Context context) {
        com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.a aVar = new com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.a(new a(), new b());
        this.c = aVar;
        context.registerReceiver(aVar, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public final void destroy() {
        if (Build.VERSION.SDK_INT >= 21) {
            ConnectivityManager.NetworkCallback networkCallback = this.d;
            if (networkCallback == null) {
                return;
            }
            Object systemService = this.f13323a.getSystemService("connectivity");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
            ((ConnectivityManager) systemService).unregisterNetworkCallback(networkCallback);
        } else {
            com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.a aVar = this.c;
            if (aVar == null) {
                return;
            }
            try {
                Result.Companion companion = Result.Companion;
                this.f13323a.unregisterReceiver(aVar);
                Result.m123constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                Result.m123constructorimpl(ResultKt.createFailure(th));
            }
        }
        this.b.clear();
        this.d = null;
        this.c = null;
    }

    @NotNull
    public final List<Listener> getListeners() {
        return this.b;
    }

    public final void observeNetwork() {
        if (Build.VERSION.SDK_INT >= 24) {
            a(this.f13323a);
        } else {
            b(this.f13323a);
        }
    }
}
