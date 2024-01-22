package androidx.camera.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
/* loaded from: classes.dex */
public final class f<T> extends MediatorLiveData<T> {
    public LiveData<T> m;

    /* JADX WARN: Multi-variable type inference failed */
    public void f(@NonNull LiveData<T> liveData) {
        LiveData liveData2 = (LiveData<T>) this.m;
        if (liveData2 != null) {
            super.removeSource(liveData2);
        }
        this.m = liveData;
        super.addSource(liveData, new Observer() { // from class: androidx.camera.view.e
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                f.this.setValue(obj);
            }
        });
    }

    @Override // androidx.lifecycle.LiveData
    public T getValue() {
        LiveData<T> liveData = this.m;
        if (liveData == null) {
            return null;
        }
        return liveData.getValue();
    }
}
