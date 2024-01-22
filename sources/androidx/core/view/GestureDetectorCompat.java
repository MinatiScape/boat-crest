package androidx.core.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public final class GestureDetectorCompat {

    /* renamed from: a  reason: collision with root package name */
    public final a f1135a;

    /* loaded from: classes.dex */
    public interface a {
        void a(GestureDetector.OnDoubleTapListener onDoubleTapListener);

        boolean b(MotionEvent motionEvent);

        void c(boolean z);

        boolean d();
    }

    /* loaded from: classes.dex */
    public static class b implements a {
        public static final int v = ViewConfiguration.getTapTimeout();
        public static final int w = ViewConfiguration.getDoubleTapTimeout();

        /* renamed from: a  reason: collision with root package name */
        public int f1136a;
        public int b;
        public int c;
        public int d;
        public final Handler e;
        public final GestureDetector.OnGestureListener f;
        public GestureDetector.OnDoubleTapListener g;
        public boolean h;
        public boolean i;
        public boolean j;
        public boolean k;
        public boolean l;
        public MotionEvent m;
        public MotionEvent n;
        public boolean o;
        public float p;
        public float q;
        public float r;
        public float s;
        public boolean t;
        public VelocityTracker u;

        public b(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
            if (handler != null) {
                this.e = new a(handler);
            } else {
                this.e = new a();
            }
            this.f = onGestureListener;
            if (onGestureListener instanceof GestureDetector.OnDoubleTapListener) {
                a((GestureDetector.OnDoubleTapListener) onGestureListener);
            }
            h(context);
        }

        @Override // androidx.core.view.GestureDetectorCompat.a
        public void a(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
            this.g = onDoubleTapListener;
        }

        /* JADX WARN: Removed duplicated region for block: B:107:0x0204  */
        /* JADX WARN: Removed duplicated region for block: B:110:0x021b  */
        @Override // androidx.core.view.GestureDetectorCompat.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean b(android.view.MotionEvent r13) {
            /*
                Method dump skipped, instructions count: 589
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.GestureDetectorCompat.b.b(android.view.MotionEvent):boolean");
        }

        @Override // androidx.core.view.GestureDetectorCompat.a
        public void c(boolean z) {
            this.t = z;
        }

        @Override // androidx.core.view.GestureDetectorCompat.a
        public boolean d() {
            return this.t;
        }

        public final void e() {
            this.e.removeMessages(1);
            this.e.removeMessages(2);
            this.e.removeMessages(3);
            this.u.recycle();
            this.u = null;
            this.o = false;
            this.h = false;
            this.k = false;
            this.l = false;
            this.i = false;
            if (this.j) {
                this.j = false;
            }
        }

        public final void f() {
            this.e.removeMessages(1);
            this.e.removeMessages(2);
            this.e.removeMessages(3);
            this.o = false;
            this.k = false;
            this.l = false;
            this.i = false;
            if (this.j) {
                this.j = false;
            }
        }

        public void g() {
            this.e.removeMessages(3);
            this.i = false;
            this.j = true;
            this.f.onLongPress(this.m);
        }

        public final void h(Context context) {
            if (context != null) {
                if (this.f != null) {
                    this.t = true;
                    ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
                    int scaledTouchSlop = viewConfiguration.getScaledTouchSlop();
                    int scaledDoubleTapSlop = viewConfiguration.getScaledDoubleTapSlop();
                    this.c = viewConfiguration.getScaledMinimumFlingVelocity();
                    this.d = viewConfiguration.getScaledMaximumFlingVelocity();
                    this.f1136a = scaledTouchSlop * scaledTouchSlop;
                    this.b = scaledDoubleTapSlop * scaledDoubleTapSlop;
                    return;
                }
                throw new IllegalArgumentException("OnGestureListener must not be null");
            }
            throw new IllegalArgumentException("Context must not be null");
        }

        public final boolean i(MotionEvent motionEvent, MotionEvent motionEvent2, MotionEvent motionEvent3) {
            if (this.l && motionEvent3.getEventTime() - motionEvent2.getEventTime() <= w) {
                int x = ((int) motionEvent.getX()) - ((int) motionEvent3.getX());
                int y = ((int) motionEvent.getY()) - ((int) motionEvent3.getY());
                return (x * x) + (y * y) < this.b;
            }
            return false;
        }

        /* loaded from: classes.dex */
        public class a extends Handler {
            public a() {
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    b bVar = b.this;
                    bVar.f.onShowPress(bVar.m);
                } else if (i == 2) {
                    b.this.g();
                } else if (i == 3) {
                    b bVar2 = b.this;
                    GestureDetector.OnDoubleTapListener onDoubleTapListener = bVar2.g;
                    if (onDoubleTapListener != null) {
                        if (!bVar2.h) {
                            onDoubleTapListener.onSingleTapConfirmed(bVar2.m);
                        } else {
                            bVar2.i = true;
                        }
                    }
                } else {
                    throw new RuntimeException("Unknown message " + message);
                }
            }

            public a(Handler handler) {
                super(handler.getLooper());
            }
        }
    }

    /* loaded from: classes.dex */
    public static class c implements a {

        /* renamed from: a  reason: collision with root package name */
        public final GestureDetector f1138a;

        public c(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
            this.f1138a = new GestureDetector(context, onGestureListener, handler);
        }

        @Override // androidx.core.view.GestureDetectorCompat.a
        public void a(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
            this.f1138a.setOnDoubleTapListener(onDoubleTapListener);
        }

        @Override // androidx.core.view.GestureDetectorCompat.a
        public boolean b(MotionEvent motionEvent) {
            return this.f1138a.onTouchEvent(motionEvent);
        }

        @Override // androidx.core.view.GestureDetectorCompat.a
        public void c(boolean z) {
            this.f1138a.setIsLongpressEnabled(z);
        }

        @Override // androidx.core.view.GestureDetectorCompat.a
        public boolean d() {
            return this.f1138a.isLongpressEnabled();
        }
    }

    public GestureDetectorCompat(@NonNull Context context, @NonNull GestureDetector.OnGestureListener onGestureListener) {
        this(context, onGestureListener, null);
    }

    public boolean isLongpressEnabled() {
        return this.f1135a.d();
    }

    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        return this.f1135a.b(motionEvent);
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public void setIsLongpressEnabled(boolean z) {
        this.f1135a.c(z);
    }

    public void setOnDoubleTapListener(@Nullable GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        this.f1135a.a(onDoubleTapListener);
    }

    public GestureDetectorCompat(@NonNull Context context, @NonNull GestureDetector.OnGestureListener onGestureListener, @Nullable Handler handler) {
        if (Build.VERSION.SDK_INT > 17) {
            this.f1135a = new c(context, onGestureListener, handler);
        } else {
            this.f1135a = new b(context, onGestureListener, handler);
        }
    }
}
