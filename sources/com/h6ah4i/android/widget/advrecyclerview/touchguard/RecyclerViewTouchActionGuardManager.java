package com.h6ah4i.android.widget.advrecyclerview.touchguard;

import android.view.MotionEvent;
import android.view.ViewConfiguration;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes11.dex */
public class RecyclerViewTouchActionGuardManager {

    /* renamed from: a  reason: collision with root package name */
    public RecyclerView.OnItemTouchListener f11929a = new a();
    public RecyclerView b;
    public boolean c;
    public int d;
    public int e;
    public int f;
    public boolean g;
    public boolean h;

    /* loaded from: classes11.dex */
    public class a implements RecyclerView.OnItemTouchListener {
        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
            return RecyclerViewTouchActionGuardManager.this.d(recyclerView, motionEvent);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
            RecyclerViewTouchActionGuardManager.this.e(recyclerView, motionEvent);
        }
    }

    public final void a(MotionEvent motionEvent) {
        int y = (int) (motionEvent.getY() + 0.5f);
        this.e = y;
        this.d = y;
        this.c = false;
    }

    public void attachRecyclerView(@NonNull RecyclerView recyclerView) {
        if (!isReleased()) {
            if (this.b == null) {
                this.b = recyclerView;
                recyclerView.addOnItemTouchListener(this.f11929a);
                this.f = ViewConfiguration.get(recyclerView.getContext()).getScaledTouchSlop();
                return;
            }
            throw new IllegalStateException("RecyclerView instance has already been set");
        }
        throw new IllegalStateException("Accessing released object");
    }

    public final boolean b(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (!this.c) {
            int y = (int) (motionEvent.getY() + 0.5f);
            this.e = y;
            int i = y - this.d;
            if (this.h && Math.abs(i) > this.f && recyclerView.isAnimating()) {
                this.c = true;
            }
        }
        return this.c;
    }

    public final void c() {
        this.c = false;
        this.d = 0;
        this.e = 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0013, code lost:
        if (r0 != 3) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean d(androidx.recyclerview.widget.RecyclerView r5, android.view.MotionEvent r6) {
        /*
            r4 = this;
            boolean r0 = r4.g
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            int r0 = r6.getActionMasked()
            if (r0 == 0) goto L21
            r2 = 1
            if (r0 == r2) goto L1d
            r3 = 2
            if (r0 == r3) goto L16
            r5 = 3
            if (r0 == r5) goto L1d
            goto L24
        L16:
            boolean r5 = r4.b(r5, r6)
            if (r5 == 0) goto L24
            return r2
        L1d:
            r4.c()
            goto L24
        L21:
            r4.a(r6)
        L24:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.h6ah4i.android.widget.advrecyclerview.touchguard.RecyclerViewTouchActionGuardManager.d(androidx.recyclerview.widget.RecyclerView, android.view.MotionEvent):boolean");
    }

    public void e(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.g) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 1 || actionMasked == 3) {
                c();
            }
        }
    }

    public boolean isEnabled() {
        return this.g;
    }

    public boolean isInterceptScrollingWhileAnimationRunning() {
        return this.h;
    }

    public boolean isReleased() {
        return this.f11929a == null;
    }

    public void release() {
        RecyclerView.OnItemTouchListener onItemTouchListener;
        RecyclerView recyclerView = this.b;
        if (recyclerView != null && (onItemTouchListener = this.f11929a) != null) {
            recyclerView.removeOnItemTouchListener(onItemTouchListener);
        }
        this.f11929a = null;
        this.b = null;
    }

    public void setEnabled(boolean z) {
        if (this.g == z) {
            return;
        }
        this.g = z;
        if (z) {
            return;
        }
        c();
    }

    public void setInterceptVerticalScrollingWhileAnimationRunning(boolean z) {
        this.h = z;
    }
}
