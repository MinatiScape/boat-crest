package androidx.appcompat.widget;

import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class x implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {
    public static x r;
    public static x s;
    public final View h;
    public final CharSequence i;
    public final int j;
    public final Runnable k = new Runnable() { // from class: androidx.appcompat.widget.w
        @Override // java.lang.Runnable
        public final void run() {
            x.this.e();
        }
    };
    public final Runnable l = new Runnable() { // from class: androidx.appcompat.widget.v
        @Override // java.lang.Runnable
        public final void run() {
            x.this.d();
        }
    };
    public int m;
    public int n;
    public y o;
    public boolean p;
    public boolean q;

    public x(View view, CharSequence charSequence) {
        this.h = view;
        this.i = charSequence;
        this.j = ViewConfigurationCompat.getScaledHoverSlop(ViewConfiguration.get(view.getContext()));
        c();
        view.setOnLongClickListener(this);
        view.setOnHoverListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e() {
        i(false);
    }

    public static void g(x xVar) {
        x xVar2 = r;
        if (xVar2 != null) {
            xVar2.b();
        }
        r = xVar;
        if (xVar != null) {
            xVar.f();
        }
    }

    public static void h(View view, CharSequence charSequence) {
        x xVar = r;
        if (xVar != null && xVar.h == view) {
            g(null);
        }
        if (TextUtils.isEmpty(charSequence)) {
            x xVar2 = s;
            if (xVar2 != null && xVar2.h == view) {
                xVar2.d();
            }
            view.setOnLongClickListener(null);
            view.setLongClickable(false);
            view.setOnHoverListener(null);
            return;
        }
        new x(view, charSequence);
    }

    public final void b() {
        this.h.removeCallbacks(this.k);
    }

    public final void c() {
        this.q = true;
    }

    public void d() {
        if (s == this) {
            s = null;
            y yVar = this.o;
            if (yVar != null) {
                yVar.c();
                this.o = null;
                c();
                this.h.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (r == this) {
            g(null);
        }
        this.h.removeCallbacks(this.l);
    }

    public final void f() {
        this.h.postDelayed(this.k, ViewConfiguration.getLongPressTimeout());
    }

    public void i(boolean z) {
        long j;
        int longPressTimeout;
        long j2;
        if (ViewCompat.isAttachedToWindow(this.h)) {
            g(null);
            x xVar = s;
            if (xVar != null) {
                xVar.d();
            }
            s = this;
            this.p = z;
            y yVar = new y(this.h.getContext());
            this.o = yVar;
            yVar.e(this.h, this.m, this.n, this.p, this.i);
            this.h.addOnAttachStateChangeListener(this);
            if (this.p) {
                j2 = 2500;
            } else {
                if ((ViewCompat.getWindowSystemUiVisibility(this.h) & 1) == 1) {
                    j = 3000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                } else {
                    j = 15000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                }
                j2 = j - longPressTimeout;
            }
            this.h.removeCallbacks(this.l);
            this.h.postDelayed(this.l, j2);
        }
    }

    public final boolean j(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (this.q || Math.abs(x - this.m) > this.j || Math.abs(y - this.n) > this.j) {
            this.m = x;
            this.n = y;
            this.q = false;
            return true;
        }
        return false;
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.o == null || !this.p) {
            AccessibilityManager accessibilityManager = (AccessibilityManager) this.h.getContext().getSystemService("accessibility");
            if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
                return false;
            }
            int action = motionEvent.getAction();
            if (action != 7) {
                if (action == 10) {
                    c();
                    d();
                }
            } else if (this.h.isEnabled() && this.o == null && j(motionEvent)) {
                g(this);
            }
            return false;
        }
        return false;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        this.m = view.getWidth() / 2;
        this.n = view.getHeight() / 2;
        i(true);
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        d();
    }
}
