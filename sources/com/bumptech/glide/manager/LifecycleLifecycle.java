package com.bumptech.glide.manager;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.bumptech.glide.util.Util;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes2.dex */
final class LifecycleLifecycle implements Lifecycle, LifecycleObserver {
    @NonNull
    public final Set<LifecycleListener> h = new HashSet();
    @NonNull
    public final androidx.lifecycle.Lifecycle i;

    public LifecycleLifecycle(androidx.lifecycle.Lifecycle lifecycle) {
        this.i = lifecycle;
        lifecycle.addObserver(this);
    }

    @Override // com.bumptech.glide.manager.Lifecycle
    public void addListener(@NonNull LifecycleListener lifecycleListener) {
        this.h.add(lifecycleListener);
        if (this.i.getCurrentState() == Lifecycle.State.DESTROYED) {
            lifecycleListener.onDestroy();
        } else if (this.i.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            lifecycleListener.onStart();
        } else {
            lifecycleListener.onStop();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(@NonNull LifecycleOwner lifecycleOwner) {
        for (LifecycleListener lifecycleListener : Util.getSnapshot(this.h)) {
            lifecycleListener.onDestroy();
        }
        lifecycleOwner.getLifecycle().removeObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(@NonNull LifecycleOwner lifecycleOwner) {
        for (LifecycleListener lifecycleListener : Util.getSnapshot(this.h)) {
            lifecycleListener.onStart();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(@NonNull LifecycleOwner lifecycleOwner) {
        for (LifecycleListener lifecycleListener : Util.getSnapshot(this.h)) {
            lifecycleListener.onStop();
        }
    }

    @Override // com.bumptech.glide.manager.Lifecycle
    public void removeListener(@NonNull LifecycleListener lifecycleListener) {
        this.h.remove(lifecycleListener);
    }
}
