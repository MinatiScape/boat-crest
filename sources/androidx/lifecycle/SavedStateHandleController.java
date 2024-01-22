package androidx.lifecycle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.savedstate.SavedStateRegistry;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class SavedStateHandleController implements LifecycleEventObserver {
    public final String h;
    public boolean i = false;
    public final SavedStateHandle j;

    public SavedStateHandleController(String str, SavedStateHandle savedStateHandle) {
        this.h = str;
        this.j = savedStateHandle;
    }

    public void a(SavedStateRegistry savedStateRegistry, Lifecycle lifecycle) {
        if (!this.i) {
            this.i = true;
            lifecycle.addObserver(this);
            savedStateRegistry.registerSavedStateProvider(this.h, this.j.savedStateProvider());
            return;
        }
        throw new IllegalStateException("Already attached to lifecycleOwner");
    }

    public SavedStateHandle b() {
        return this.j;
    }

    public boolean c() {
        return this.i;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            this.i = false;
            lifecycleOwner.getLifecycle().removeObserver(this);
        }
    }
}
