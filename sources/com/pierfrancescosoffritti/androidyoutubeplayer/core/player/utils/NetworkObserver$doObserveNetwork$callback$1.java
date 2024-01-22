package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils;

import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Handler;
import android.os.Looper;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.NetworkObserver;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
@SourceDebugExtension({"SMAP\nNetworkObserver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NetworkObserver.kt\ncom/pierfrancescosoffritti/androidyoutubeplayer/core/player/utils/NetworkObserver$doObserveNetwork$callback$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,121:1\n1855#2,2:122\n1855#2,2:124\n*S KotlinDebug\n*F\n+ 1 NetworkObserver.kt\ncom/pierfrancescosoffritti/androidyoutubeplayer/core/player/utils/NetworkObserver$doObserveNetwork$callback$1\n*L\n61#1:122,2\n68#1:124,2\n*E\n"})
/* loaded from: classes9.dex */
public final class NetworkObserver$doObserveNetwork$callback$1 extends ConnectivityManager.NetworkCallback {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Handler f13324a = new Handler(Looper.getMainLooper());
    public final /* synthetic */ NetworkObserver b;

    public NetworkObserver$doObserveNetwork$callback$1(NetworkObserver networkObserver) {
        this.b = networkObserver;
    }

    public static final void c(NetworkObserver this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        for (NetworkObserver.Listener listener : this$0.getListeners()) {
            listener.onNetworkAvailable();
        }
    }

    public static final void d(NetworkObserver this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        for (NetworkObserver.Listener listener : this$0.getListeners()) {
            listener.onNetworkUnavailable();
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onAvailable(@NotNull Network network) {
        Intrinsics.checkNotNullParameter(network, "network");
        Handler handler = this.f13324a;
        final NetworkObserver networkObserver = this.b;
        handler.post(new Runnable() { // from class: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.b
            @Override // java.lang.Runnable
            public final void run() {
                NetworkObserver$doObserveNetwork$callback$1.c(NetworkObserver.this);
            }
        });
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onLost(@NotNull Network network) {
        Intrinsics.checkNotNullParameter(network, "network");
        Handler handler = this.f13324a;
        final NetworkObserver networkObserver = this.b;
        handler.post(new Runnable() { // from class: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.c
            @Override // java.lang.Runnable
            public final void run() {
                NetworkObserver$doObserveNetwork$callback$1.d(NetworkObserver.this);
            }
        });
    }
}
