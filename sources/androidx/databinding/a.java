package androidx.databinding;

import androidx.databinding.Observable;
/* loaded from: classes.dex */
public abstract class a extends BaseObservable {

    /* renamed from: androidx.databinding.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0138a extends Observable.OnPropertyChangedCallback {
        public C0138a() {
        }

        @Override // androidx.databinding.Observable.OnPropertyChangedCallback
        public void onPropertyChanged(Observable observable, int i) {
            a.this.notifyChange();
        }
    }

    public a() {
    }

    public a(Observable... observableArr) {
        if (observableArr == null || observableArr.length == 0) {
            return;
        }
        C0138a c0138a = new C0138a();
        for (Observable observable : observableArr) {
            observable.addOnPropertyChangedCallback(c0138a);
        }
    }
}
