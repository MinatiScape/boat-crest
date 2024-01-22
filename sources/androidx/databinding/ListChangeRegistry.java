package androidx.databinding;

import androidx.annotation.NonNull;
import androidx.core.util.Pools;
import androidx.databinding.CallbackRegistry;
import androidx.databinding.ObservableList;
/* loaded from: classes.dex */
public class ListChangeRegistry extends CallbackRegistry<ObservableList.OnListChangedCallback, ObservableList, b> {
    public static final Pools.SynchronizedPool<b> m = new Pools.SynchronizedPool<>(10);
    public static final CallbackRegistry.NotifierCallback<ObservableList.OnListChangedCallback, ObservableList, b> n = new a();

    /* loaded from: classes.dex */
    public class a extends CallbackRegistry.NotifierCallback<ObservableList.OnListChangedCallback, ObservableList, b> {
        @Override // androidx.databinding.CallbackRegistry.NotifierCallback
        /* renamed from: a */
        public void onNotifyCallback(ObservableList.OnListChangedCallback onListChangedCallback, ObservableList observableList, int i, b bVar) {
            if (i == 1) {
                onListChangedCallback.onItemRangeChanged(observableList, bVar.f1214a, bVar.b);
            } else if (i == 2) {
                onListChangedCallback.onItemRangeInserted(observableList, bVar.f1214a, bVar.b);
            } else if (i == 3) {
                onListChangedCallback.onItemRangeMoved(observableList, bVar.f1214a, bVar.c, bVar.b);
            } else if (i != 4) {
                onListChangedCallback.onChanged(observableList);
            } else {
                onListChangedCallback.onItemRangeRemoved(observableList, bVar.f1214a, bVar.b);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public int f1214a;
        public int b;
        public int c;
    }

    public ListChangeRegistry() {
        super(n);
    }

    public static b h(int i, int i2, int i3) {
        b acquire = m.acquire();
        if (acquire == null) {
            acquire = new b();
        }
        acquire.f1214a = i;
        acquire.c = i2;
        acquire.b = i3;
        return acquire;
    }

    public void notifyChanged(@NonNull ObservableList observableList) {
        notifyCallbacks(observableList, 0, (b) null);
    }

    public void notifyInserted(@NonNull ObservableList observableList, int i, int i2) {
        notifyCallbacks(observableList, 2, h(i, 0, i2));
    }

    public void notifyMoved(@NonNull ObservableList observableList, int i, int i2, int i3) {
        notifyCallbacks(observableList, 3, h(i, i2, i3));
    }

    public void notifyRemoved(@NonNull ObservableList observableList, int i, int i2) {
        notifyCallbacks(observableList, 4, h(i, 0, i2));
    }

    @Override // androidx.databinding.CallbackRegistry
    public synchronized void notifyCallbacks(@NonNull ObservableList observableList, int i, b bVar) {
        super.notifyCallbacks((ListChangeRegistry) observableList, i, (int) bVar);
        if (bVar != null) {
            m.release(bVar);
        }
    }

    public void notifyChanged(@NonNull ObservableList observableList, int i, int i2) {
        notifyCallbacks(observableList, 1, h(i, 0, i2));
    }
}
