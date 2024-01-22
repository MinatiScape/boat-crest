package androidx.camera.view;

import android.graphics.Bitmap;
import android.util.Size;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.SurfaceRequest;
import com.google.common.util.concurrent.ListenableFuture;
/* loaded from: classes.dex */
public abstract class p {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Size f822a;
    @NonNull
    public FrameLayout b;
    @NonNull
    public final k c;
    public boolean d = false;

    /* loaded from: classes.dex */
    public interface a {
        void a();
    }

    public p(@NonNull FrameLayout frameLayout, @NonNull k kVar) {
        this.b = frameLayout;
        this.c = kVar;
    }

    @Nullable
    public Bitmap a() {
        Bitmap c = c();
        if (c == null) {
            return null;
        }
        return this.c.a(c, new Size(this.b.getWidth(), this.b.getHeight()), this.b.getLayoutDirection());
    }

    @Nullable
    public abstract View b();

    @Nullable
    public abstract Bitmap c();

    public abstract void d();

    public abstract void e();

    public void f() {
        this.d = true;
        h();
    }

    public abstract void g(@NonNull SurfaceRequest surfaceRequest, @Nullable a aVar);

    public void h() {
        View b = b();
        if (b == null || !this.d) {
            return;
        }
        this.c.q(new Size(this.b.getWidth(), this.b.getHeight()), this.b.getLayoutDirection(), b);
    }

    @NonNull
    public abstract ListenableFuture<Void> i();
}
