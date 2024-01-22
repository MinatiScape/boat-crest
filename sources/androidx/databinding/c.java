package androidx.databinding;

import androidx.annotation.RestrictTo;
import androidx.lifecycle.LifecycleOwner;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public interface c<T> {
    void addListener(T t);

    void removeListener(T t);

    void setLifecycleOwner(LifecycleOwner lifecycleOwner);
}
