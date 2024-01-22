package com.bumptech.glide.manager;

import androidx.annotation.NonNull;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.Util;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
/* loaded from: classes2.dex */
public final class TargetTracker implements LifecycleListener {
    public final Set<Target<?>> h = Collections.newSetFromMap(new WeakHashMap());

    public void clear() {
        this.h.clear();
    }

    @NonNull
    public List<Target<?>> getAll() {
        return Util.getSnapshot(this.h);
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onDestroy() {
        for (Target target : Util.getSnapshot(this.h)) {
            target.onDestroy();
        }
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStart() {
        for (Target target : Util.getSnapshot(this.h)) {
            target.onStart();
        }
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStop() {
        for (Target target : Util.getSnapshot(this.h)) {
            target.onStop();
        }
    }

    public void track(@NonNull Target<?> target) {
        this.h.add(target);
    }

    public void untrack(@NonNull Target<?> target) {
        this.h.remove(target);
    }
}
