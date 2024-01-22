package androidx.activity.contextaware;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
/* loaded from: classes.dex */
public final class ContextAwareHelper {

    /* renamed from: a  reason: collision with root package name */
    public final Set<OnContextAvailableListener> f347a = new CopyOnWriteArraySet();
    public volatile Context b;

    public void addOnContextAvailableListener(@NonNull OnContextAvailableListener onContextAvailableListener) {
        if (this.b != null) {
            onContextAvailableListener.onContextAvailable(this.b);
        }
        this.f347a.add(onContextAvailableListener);
    }

    public void clearAvailableContext() {
        this.b = null;
    }

    public void dispatchOnContextAvailable(@NonNull Context context) {
        this.b = context;
        for (OnContextAvailableListener onContextAvailableListener : this.f347a) {
            onContextAvailableListener.onContextAvailable(context);
        }
    }

    @Nullable
    public Context peekAvailableContext() {
        return this.b;
    }

    public void removeOnContextAvailableListener(@NonNull OnContextAvailableListener onContextAvailableListener) {
        this.f347a.remove(onContextAvailableListener);
    }
}
