package com.bumptech.glide.manager;

import android.content.Context;
import androidx.annotation.NonNull;
import com.bumptech.glide.manager.ConnectivityMonitor;
/* loaded from: classes2.dex */
public final class c implements ConnectivityMonitor {
    public final Context h;
    public final ConnectivityMonitor.ConnectivityListener i;

    public c(@NonNull Context context, @NonNull ConnectivityMonitor.ConnectivityListener connectivityListener) {
        this.h = context.getApplicationContext();
        this.i = connectivityListener;
    }

    public final void a() {
        k.a(this.h).d(this.i);
    }

    public final void b() {
        k.a(this.h).e(this.i);
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onDestroy() {
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStart() {
        a();
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStop() {
        b();
    }
}
