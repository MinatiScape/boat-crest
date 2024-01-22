package androidx.databinding;

import androidx.annotation.NonNull;
import androidx.databinding.CallbackRegistry;
import androidx.databinding.Observable;
/* loaded from: classes.dex */
public class PropertyChangeRegistry extends CallbackRegistry<Observable.OnPropertyChangedCallback, Observable, Void> {
    public static final CallbackRegistry.NotifierCallback<Observable.OnPropertyChangedCallback, Observable, Void> m = new a();

    /* loaded from: classes.dex */
    public class a extends CallbackRegistry.NotifierCallback<Observable.OnPropertyChangedCallback, Observable, Void> {
        @Override // androidx.databinding.CallbackRegistry.NotifierCallback
        /* renamed from: a */
        public void onNotifyCallback(Observable.OnPropertyChangedCallback onPropertyChangedCallback, Observable observable, int i, Void r4) {
            onPropertyChangedCallback.onPropertyChanged(observable, i);
        }
    }

    public PropertyChangeRegistry() {
        super(m);
    }

    public void notifyChange(@NonNull Observable observable, int i) {
        notifyCallbacks(observable, i, null);
    }
}
