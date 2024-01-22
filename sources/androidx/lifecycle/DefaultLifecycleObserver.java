package androidx.lifecycle;

import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public interface DefaultLifecycleObserver extends d {
    @Override // androidx.lifecycle.d
    default void onCreate(@NonNull LifecycleOwner lifecycleOwner) {
    }

    @Override // androidx.lifecycle.d
    default void onDestroy(@NonNull LifecycleOwner lifecycleOwner) {
    }

    @Override // androidx.lifecycle.d
    default void onPause(@NonNull LifecycleOwner lifecycleOwner) {
    }

    @Override // androidx.lifecycle.d
    default void onResume(@NonNull LifecycleOwner lifecycleOwner) {
    }

    @Override // androidx.lifecycle.d
    default void onStart(@NonNull LifecycleOwner lifecycleOwner) {
    }

    @Override // androidx.lifecycle.d
    default void onStop(@NonNull LifecycleOwner lifecycleOwner) {
    }
}
