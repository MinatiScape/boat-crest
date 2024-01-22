package androidx.databinding;

import android.view.View;
import android.view.ViewStub;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public class ViewStubProxy {

    /* renamed from: a  reason: collision with root package name */
    public ViewStub f1217a;
    public ViewDataBinding b;
    public View c;
    public ViewStub.OnInflateListener d;
    public ViewDataBinding e;
    public ViewStub.OnInflateListener f;

    /* loaded from: classes.dex */
    public class a implements ViewStub.OnInflateListener {
        public a() {
        }

        @Override // android.view.ViewStub.OnInflateListener
        public void onInflate(ViewStub viewStub, View view) {
            ViewStubProxy.this.c = view;
            ViewStubProxy viewStubProxy = ViewStubProxy.this;
            viewStubProxy.b = DataBindingUtil.a(viewStubProxy.e.mBindingComponent, view, viewStub.getLayoutResource());
            ViewStubProxy.this.f1217a = null;
            if (ViewStubProxy.this.d != null) {
                ViewStubProxy.this.d.onInflate(viewStub, view);
                ViewStubProxy.this.d = null;
            }
            ViewStubProxy.this.e.invalidateAll();
            ViewStubProxy.this.e.forceExecuteBindings();
        }
    }

    public ViewStubProxy(@NonNull ViewStub viewStub) {
        a aVar = new a();
        this.f = aVar;
        this.f1217a = viewStub;
        viewStub.setOnInflateListener(aVar);
    }

    @Nullable
    public ViewDataBinding getBinding() {
        return this.b;
    }

    public View getRoot() {
        return this.c;
    }

    @Nullable
    public ViewStub getViewStub() {
        return this.f1217a;
    }

    public boolean isInflated() {
        return this.c != null;
    }

    public void setContainingBinding(@NonNull ViewDataBinding viewDataBinding) {
        this.e = viewDataBinding;
    }

    public void setOnInflateListener(@Nullable ViewStub.OnInflateListener onInflateListener) {
        if (this.f1217a != null) {
            this.d = onInflateListener;
        }
    }
}
