package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public final InterfaceC0175b f1635a;
    public final a b = new a();
    public final List<View> c = new ArrayList();

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public long f1636a = 0;
        public a b;

        public void a(int i) {
            if (i >= 64) {
                a aVar = this.b;
                if (aVar != null) {
                    aVar.a(i - 64);
                    return;
                }
                return;
            }
            this.f1636a &= ~(1 << i);
        }

        public int b(int i) {
            a aVar = this.b;
            if (aVar == null) {
                if (i >= 64) {
                    return Long.bitCount(this.f1636a);
                }
                return Long.bitCount(this.f1636a & ((1 << i) - 1));
            } else if (i < 64) {
                return Long.bitCount(this.f1636a & ((1 << i) - 1));
            } else {
                return aVar.b(i - 64) + Long.bitCount(this.f1636a);
            }
        }

        public final void c() {
            if (this.b == null) {
                this.b = new a();
            }
        }

        public boolean d(int i) {
            if (i < 64) {
                return (this.f1636a & (1 << i)) != 0;
            }
            c();
            return this.b.d(i - 64);
        }

        public void e(int i, boolean z) {
            if (i >= 64) {
                c();
                this.b.e(i - 64, z);
                return;
            }
            long j = this.f1636a;
            boolean z2 = (Long.MIN_VALUE & j) != 0;
            long j2 = (1 << i) - 1;
            this.f1636a = ((j & (~j2)) << 1) | (j & j2);
            if (z) {
                h(i);
            } else {
                a(i);
            }
            if (z2 || this.b != null) {
                c();
                this.b.e(0, z2);
            }
        }

        public boolean f(int i) {
            if (i >= 64) {
                c();
                return this.b.f(i - 64);
            }
            long j = 1 << i;
            long j2 = this.f1636a;
            boolean z = (j2 & j) != 0;
            long j3 = j2 & (~j);
            this.f1636a = j3;
            long j4 = j - 1;
            this.f1636a = (j3 & j4) | Long.rotateRight((~j4) & j3, 1);
            a aVar = this.b;
            if (aVar != null) {
                if (aVar.d(0)) {
                    h(63);
                }
                this.b.f(0);
            }
            return z;
        }

        public void g() {
            this.f1636a = 0L;
            a aVar = this.b;
            if (aVar != null) {
                aVar.g();
            }
        }

        public void h(int i) {
            if (i >= 64) {
                c();
                this.b.h(i - 64);
                return;
            }
            this.f1636a |= 1 << i;
        }

        public String toString() {
            if (this.b == null) {
                return Long.toBinaryString(this.f1636a);
            }
            return this.b.toString() + "xx" + Long.toBinaryString(this.f1636a);
        }
    }

    /* renamed from: androidx.recyclerview.widget.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0175b {
        View a(int i);

        void b(View view);

        int c();

        RecyclerView.ViewHolder d(View view);

        void e(int i);

        void f(View view, int i);

        void g();

        int h(View view);

        void i(View view);

        void j(int i);

        void k(View view, int i, ViewGroup.LayoutParams layoutParams);
    }

    public b(InterfaceC0175b interfaceC0175b) {
        this.f1635a = interfaceC0175b;
    }

    public void a(View view, int i, boolean z) {
        int h;
        if (i < 0) {
            h = this.f1635a.c();
        } else {
            h = h(i);
        }
        this.b.e(h, z);
        if (z) {
            l(view);
        }
        this.f1635a.f(view, h);
    }

    public void b(View view, boolean z) {
        a(view, -1, z);
    }

    public void c(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        int h;
        if (i < 0) {
            h = this.f1635a.c();
        } else {
            h = h(i);
        }
        this.b.e(h, z);
        if (z) {
            l(view);
        }
        this.f1635a.k(view, h, layoutParams);
    }

    public void d(int i) {
        int h = h(i);
        this.b.f(h);
        this.f1635a.e(h);
    }

    public View e(int i) {
        int size = this.c.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view = this.c.get(i2);
            RecyclerView.ViewHolder d = this.f1635a.d(view);
            if (d.getLayoutPosition() == i && !d.isInvalid() && !d.isRemoved()) {
                return view;
            }
        }
        return null;
    }

    public View f(int i) {
        return this.f1635a.a(h(i));
    }

    public int g() {
        return this.f1635a.c() - this.c.size();
    }

    public final int h(int i) {
        if (i < 0) {
            return -1;
        }
        int c = this.f1635a.c();
        int i2 = i;
        while (i2 < c) {
            int b = i - (i2 - this.b.b(i2));
            if (b == 0) {
                while (this.b.d(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += b;
        }
        return -1;
    }

    public View i(int i) {
        return this.f1635a.a(i);
    }

    public int j() {
        return this.f1635a.c();
    }

    public void k(View view) {
        int h = this.f1635a.h(view);
        if (h >= 0) {
            this.b.h(h);
            l(view);
            return;
        }
        throw new IllegalArgumentException("view is not a child, cannot hide " + view);
    }

    public final void l(View view) {
        this.c.add(view);
        this.f1635a.b(view);
    }

    public int m(View view) {
        int h = this.f1635a.h(view);
        if (h == -1 || this.b.d(h)) {
            return -1;
        }
        return h - this.b.b(h);
    }

    public boolean n(View view) {
        return this.c.contains(view);
    }

    public void o() {
        this.b.g();
        for (int size = this.c.size() - 1; size >= 0; size--) {
            this.f1635a.i(this.c.get(size));
            this.c.remove(size);
        }
        this.f1635a.g();
    }

    public void p(View view) {
        int h = this.f1635a.h(view);
        if (h < 0) {
            return;
        }
        if (this.b.f(h)) {
            t(view);
        }
        this.f1635a.j(h);
    }

    public void q(int i) {
        int h = h(i);
        View a2 = this.f1635a.a(h);
        if (a2 == null) {
            return;
        }
        if (this.b.f(h)) {
            t(a2);
        }
        this.f1635a.j(h);
    }

    public boolean r(View view) {
        int h = this.f1635a.h(view);
        if (h == -1) {
            t(view);
            return true;
        } else if (this.b.d(h)) {
            this.b.f(h);
            t(view);
            this.f1635a.j(h);
            return true;
        } else {
            return false;
        }
    }

    public void s(View view) {
        int h = this.f1635a.h(view);
        if (h >= 0) {
            if (this.b.d(h)) {
                this.b.a(h);
                t(view);
                return;
            }
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
        throw new IllegalArgumentException("view is not a child, cannot hide " + view);
    }

    public final boolean t(View view) {
        if (this.c.remove(view)) {
            this.f1635a.i(view);
            return true;
        }
        return false;
    }

    public String toString() {
        return this.b.toString() + ", hidden list:" + this.c.size();
    }
}
