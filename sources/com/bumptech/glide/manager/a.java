package com.bumptech.glide.manager;

import androidx.annotation.NonNull;
import com.bumptech.glide.util.Util;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
/* loaded from: classes2.dex */
public class a implements Lifecycle {
    public final Set<LifecycleListener> h = Collections.newSetFromMap(new WeakHashMap());
    public boolean i;
    public boolean j;

    public void a() {
        this.j = true;
        for (LifecycleListener lifecycleListener : Util.getSnapshot(this.h)) {
            lifecycleListener.onDestroy();
        }
    }

    @Override // com.bumptech.glide.manager.Lifecycle
    public void addListener(@NonNull LifecycleListener lifecycleListener) {
        this.h.add(lifecycleListener);
        if (this.j) {
            lifecycleListener.onDestroy();
        } else if (this.i) {
            lifecycleListener.onStart();
        } else {
            lifecycleListener.onStop();
        }
    }

    public void b() {
        this.i = true;
        for (LifecycleListener lifecycleListener : Util.getSnapshot(this.h)) {
            lifecycleListener.onStart();
        }
    }

    public void c() {
        this.i = false;
        for (LifecycleListener lifecycleListener : Util.getSnapshot(this.h)) {
            lifecycleListener.onStop();
        }
    }

    @Override // com.bumptech.glide.manager.Lifecycle
    public void removeListener(@NonNull LifecycleListener lifecycleListener) {
        this.h.remove(lifecycleListener);
    }
}
