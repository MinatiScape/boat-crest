package androidx.lifecycle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class SingleGeneratedAdapterObserver implements LifecycleEventObserver {
    public final GeneratedAdapter h;

    public SingleGeneratedAdapterObserver(GeneratedAdapter generatedAdapter) {
        this.h = generatedAdapter;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
        this.h.callMethods(lifecycleOwner, event, false, null);
        this.h.callMethods(lifecycleOwner, event, true, null);
    }
}
