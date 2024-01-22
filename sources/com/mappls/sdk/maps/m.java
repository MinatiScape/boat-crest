package com.mappls.sdk.maps;

import android.graphics.PointF;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes11.dex */
public final class m {

    /* renamed from: a  reason: collision with root package name */
    public final Transform f12786a;
    public final UiSettings b;
    public final l c;
    @Nullable
    public a d;

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public boolean h = false;

        public a() {
        }

        public void a() {
            this.h = true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.h) {
                return;
            }
            m.this.c.n0(new PointF(m.this.b.getWidth() / 2.0f, m.this.b.getHeight() / 2.0f), true);
            m.this.d = null;
        }
    }

    public m(Transform transform, UiSettings uiSettings, l lVar) {
        this.f12786a = transform;
        this.b = uiSettings;
        this.c = lVar;
    }

    public boolean d(int i, @NonNull KeyEvent keyEvent) {
        double d = keyEvent.getRepeatCount() >= 5 ? 50.0d : 10.0d;
        if (i != 66) {
            switch (i) {
                case 19:
                    UiSettings uiSettings = this.b;
                    if (uiSettings == null || uiSettings.isScrollGesturesEnabled()) {
                        this.f12786a.f();
                        this.f12786a.u(0.0d, d, 0L);
                        return true;
                    }
                    return false;
                case 20:
                    UiSettings uiSettings2 = this.b;
                    if (uiSettings2 == null || uiSettings2.isScrollGesturesEnabled()) {
                        this.f12786a.f();
                        this.f12786a.u(0.0d, -d, 0L);
                        return true;
                    }
                    return false;
                case 21:
                    UiSettings uiSettings3 = this.b;
                    if (uiSettings3 == null || uiSettings3.isScrollGesturesEnabled()) {
                        this.f12786a.f();
                        this.f12786a.u(d, 0.0d, 0L);
                        return true;
                    }
                    return false;
                case 22:
                    UiSettings uiSettings4 = this.b;
                    if (uiSettings4 == null || uiSettings4.isScrollGesturesEnabled()) {
                        this.f12786a.f();
                        this.f12786a.u(-d, 0.0d, 0L);
                        return true;
                    }
                    return false;
                case 23:
                    break;
                default:
                    return false;
            }
        }
        keyEvent.startTracking();
        return true;
    }

    public boolean e(int i, KeyEvent keyEvent) {
        if (i == 23 || i == 66) {
            UiSettings uiSettings = this.b;
            if (uiSettings == null || uiSettings.isZoomGesturesEnabled()) {
                this.c.n0(new PointF(this.b.getWidth() / 2.0f, this.b.getHeight() / 2.0f), true);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean f(int i, KeyEvent keyEvent) {
        if (keyEvent.isCanceled()) {
            return false;
        }
        if ((i == 23 || i == 66) && this.b.isZoomGesturesEnabled()) {
            this.c.m0(new PointF(this.b.getWidth() / 2.0f, this.b.getHeight() / 2.0f), true);
            return true;
        }
        return false;
    }

    public boolean g(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            a aVar = this.d;
            if (aVar != null) {
                aVar.a();
                this.d = null;
            }
            this.d = new a();
            new Handler(Looper.getMainLooper()).postDelayed(this.d, ViewConfiguration.getLongPressTimeout());
            return true;
        } else if (actionMasked == 1) {
            if (this.b.isZoomGesturesEnabled()) {
                if (this.d != null) {
                    this.c.m0(new PointF(this.b.getWidth() / 2.0f, this.b.getHeight() / 2.0f), true);
                }
                return true;
            }
            return false;
        } else if (actionMasked == 2) {
            if (this.b.isScrollGesturesEnabled()) {
                this.f12786a.f();
                this.f12786a.u(motionEvent.getX() * (-10.0d), motionEvent.getY() * (-10.0d), 0L);
                return true;
            }
            return false;
        } else if (actionMasked != 3) {
            return false;
        } else {
            a aVar2 = this.d;
            if (aVar2 != null) {
                aVar2.a();
                this.d = null;
            }
            return true;
        }
    }
}
