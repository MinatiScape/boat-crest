package androidx.appcompat.widget;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.ShowableListMenu;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public abstract class ForwardingListener implements View.OnTouchListener, View.OnAttachStateChangeListener {
    public final float h;
    public final int i;
    public final int j;
    public final View k;
    public Runnable l;
    public Runnable m;
    public boolean n;
    public int o;
    public final int[] p = new int[2];

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewParent parent = ForwardingListener.this.k.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ForwardingListener.this.b();
        }
    }

    public ForwardingListener(View view) {
        this.k = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.h = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        int tapTimeout = ViewConfiguration.getTapTimeout();
        this.i = tapTimeout;
        this.j = (tapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
    }

    public static boolean e(View view, float f, float f2, float f3) {
        float f4 = -f3;
        return f >= f4 && f2 >= f4 && f < ((float) (view.getRight() - view.getLeft())) + f3 && f2 < ((float) (view.getBottom() - view.getTop())) + f3;
    }

    public final void a() {
        Runnable runnable = this.m;
        if (runnable != null) {
            this.k.removeCallbacks(runnable);
        }
        Runnable runnable2 = this.l;
        if (runnable2 != null) {
            this.k.removeCallbacks(runnable2);
        }
    }

    public void b() {
        a();
        View view = this.k;
        if (view.isEnabled() && !view.isLongClickable() && onForwardingStarted()) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            view.onTouchEvent(obtain);
            obtain.recycle();
            this.n = true;
        }
    }

    public final boolean c(MotionEvent motionEvent) {
        o oVar;
        View view = this.k;
        ShowableListMenu popup = getPopup();
        if (popup == null || !popup.isShowing() || (oVar = (o) popup.getListView()) == null || !oVar.isShown()) {
            return false;
        }
        MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
        f(view, obtainNoHistory);
        g(oVar, obtainNoHistory);
        boolean onForwardedEvent = oVar.onForwardedEvent(obtainNoHistory, this.o);
        obtainNoHistory.recycle();
        int actionMasked = motionEvent.getActionMasked();
        return onForwardedEvent && (actionMasked != 1 && actionMasked != 3);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0017, code lost:
        if (r1 != 3) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean d(android.view.MotionEvent r6) {
        /*
            r5 = this;
            android.view.View r0 = r5.k
            boolean r1 = r0.isEnabled()
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            int r1 = r6.getActionMasked()
            if (r1 == 0) goto L41
            r3 = 1
            if (r1 == r3) goto L3d
            r4 = 2
            if (r1 == r4) goto L1a
            r6 = 3
            if (r1 == r6) goto L3d
            goto L6d
        L1a:
            int r1 = r5.o
            int r1 = r6.findPointerIndex(r1)
            if (r1 < 0) goto L6d
            float r4 = r6.getX(r1)
            float r6 = r6.getY(r1)
            float r1 = r5.h
            boolean r6 = e(r0, r4, r6, r1)
            if (r6 != 0) goto L6d
            r5.a()
            android.view.ViewParent r6 = r0.getParent()
            r6.requestDisallowInterceptTouchEvent(r3)
            return r3
        L3d:
            r5.a()
            goto L6d
        L41:
            int r6 = r6.getPointerId(r2)
            r5.o = r6
            java.lang.Runnable r6 = r5.l
            if (r6 != 0) goto L52
            androidx.appcompat.widget.ForwardingListener$a r6 = new androidx.appcompat.widget.ForwardingListener$a
            r6.<init>()
            r5.l = r6
        L52:
            java.lang.Runnable r6 = r5.l
            int r1 = r5.i
            long r3 = (long) r1
            r0.postDelayed(r6, r3)
            java.lang.Runnable r6 = r5.m
            if (r6 != 0) goto L65
            androidx.appcompat.widget.ForwardingListener$b r6 = new androidx.appcompat.widget.ForwardingListener$b
            r6.<init>()
            r5.m = r6
        L65:
            java.lang.Runnable r6 = r5.m
            int r1 = r5.j
            long r3 = (long) r1
            r0.postDelayed(r6, r3)
        L6d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ForwardingListener.d(android.view.MotionEvent):boolean");
    }

    public final boolean f(View view, MotionEvent motionEvent) {
        int[] iArr = this.p;
        view.getLocationOnScreen(iArr);
        motionEvent.offsetLocation(iArr[0], iArr[1]);
        return true;
    }

    public final boolean g(View view, MotionEvent motionEvent) {
        int[] iArr = this.p;
        view.getLocationOnScreen(iArr);
        motionEvent.offsetLocation(-iArr[0], -iArr[1]);
        return true;
    }

    public abstract ShowableListMenu getPopup();

    public boolean onForwardingStarted() {
        ShowableListMenu popup = getPopup();
        if (popup == null || popup.isShowing()) {
            return true;
        }
        popup.show();
        return true;
    }

    public boolean onForwardingStopped() {
        ShowableListMenu popup = getPopup();
        if (popup == null || !popup.isShowing()) {
            return true;
        }
        popup.dismiss();
        return true;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z;
        boolean z2 = this.n;
        if (z2) {
            z = c(motionEvent) || !onForwardingStopped();
        } else {
            z = d(motionEvent) && onForwardingStarted();
            if (z) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                this.k.onTouchEvent(obtain);
                obtain.recycle();
            }
        }
        this.n = z;
        return z || z2;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        this.n = false;
        this.o = -1;
        Runnable runnable = this.l;
        if (runnable != null) {
            this.k.removeCallbacks(runnable);
        }
    }
}
