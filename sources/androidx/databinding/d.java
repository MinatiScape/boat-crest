package androidx.databinding;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.lifecycle.LifecycleOwner;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class d<T> extends WeakReference<ViewDataBinding> {

    /* renamed from: a  reason: collision with root package name */
    public final c<T> f1233a;
    public final int b;
    public T c;

    public d(ViewDataBinding viewDataBinding, int i, c<T> cVar, ReferenceQueue<ViewDataBinding> referenceQueue) {
        super(viewDataBinding, referenceQueue);
        this.b = i;
        this.f1233a = cVar;
    }

    @Nullable
    public ViewDataBinding a() {
        ViewDataBinding viewDataBinding = (ViewDataBinding) get();
        if (viewDataBinding == null) {
            e();
        }
        return viewDataBinding;
    }

    public T b() {
        return this.c;
    }

    public void c(LifecycleOwner lifecycleOwner) {
        this.f1233a.setLifecycleOwner(lifecycleOwner);
    }

    public void d(T t) {
        e();
        this.c = t;
        if (t != null) {
            this.f1233a.addListener(t);
        }
    }

    public boolean e() {
        boolean z;
        T t = this.c;
        if (t != null) {
            this.f1233a.removeListener(t);
            z = true;
        } else {
            z = false;
        }
        this.c = null;
        return z;
    }
}
