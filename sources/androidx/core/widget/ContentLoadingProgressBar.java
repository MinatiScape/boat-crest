package androidx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
/* loaded from: classes.dex */
public class ContentLoadingProgressBar extends ProgressBar {
    public long h;
    public boolean i;
    public boolean j;
    public boolean k;
    public final Runnable l;
    public final Runnable m;

    public ContentLoadingProgressBar(@NonNull Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f() {
        this.i = false;
        this.h = -1L;
        setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g() {
        this.j = false;
        if (this.k) {
            return;
        }
        this.h = System.currentTimeMillis();
        setVisibility(0);
    }

    @UiThread
    public final void e() {
        this.k = true;
        removeCallbacks(this.m);
        this.j = false;
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.h;
        long j2 = currentTimeMillis - j;
        if (j2 < 500 && j != -1) {
            if (this.i) {
                return;
            }
            postDelayed(this.l, 500 - j2);
            this.i = true;
            return;
        }
        setVisibility(8);
    }

    public final void h() {
        removeCallbacks(this.l);
        removeCallbacks(this.m);
    }

    public void hide() {
        post(new Runnable() { // from class: androidx.core.widget.b
            @Override // java.lang.Runnable
            public final void run() {
                ContentLoadingProgressBar.this.e();
            }
        });
    }

    @UiThread
    public final void i() {
        this.h = -1L;
        this.k = false;
        removeCallbacks(this.l);
        this.i = false;
        if (this.j) {
            return;
        }
        postDelayed(this.m, 500L);
        this.j = true;
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        h();
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        h();
    }

    public void show() {
        post(new Runnable() { // from class: androidx.core.widget.d
            @Override // java.lang.Runnable
            public final void run() {
                ContentLoadingProgressBar.this.i();
            }
        });
    }

    public ContentLoadingProgressBar(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.h = -1L;
        this.i = false;
        this.j = false;
        this.k = false;
        this.l = new Runnable() { // from class: androidx.core.widget.a
            @Override // java.lang.Runnable
            public final void run() {
                ContentLoadingProgressBar.this.f();
            }
        };
        this.m = new Runnable() { // from class: androidx.core.widget.c
            @Override // java.lang.Runnable
            public final void run() {
                ContentLoadingProgressBar.this.g();
            }
        };
    }
}
