package androidx.viewpager2.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Locale;
/* loaded from: classes.dex */
public final class e extends RecyclerView.OnScrollListener {

    /* renamed from: a  reason: collision with root package name */
    public ViewPager2.OnPageChangeCallback f1767a;
    @NonNull
    public final ViewPager2 b;
    @NonNull
    public final RecyclerView c;
    @NonNull
    public final LinearLayoutManager d;
    public int e;
    public int f;
    public a g;
    public int h;
    public int i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public int f1768a;
        public float b;
        public int c;

        public void a() {
            this.f1768a = -1;
            this.b = 0.0f;
            this.c = 0;
        }
    }

    public e(@NonNull ViewPager2 viewPager2) {
        this.b = viewPager2;
        RecyclerView recyclerView = viewPager2.q;
        this.c = recyclerView;
        this.d = (LinearLayoutManager) recyclerView.getLayoutManager();
        this.g = new a();
        o();
    }

    public final void a(int i, float f, int i2) {
        ViewPager2.OnPageChangeCallback onPageChangeCallback = this.f1767a;
        if (onPageChangeCallback != null) {
            onPageChangeCallback.onPageScrolled(i, f, i2);
        }
    }

    public final void b(int i) {
        ViewPager2.OnPageChangeCallback onPageChangeCallback = this.f1767a;
        if (onPageChangeCallback != null) {
            onPageChangeCallback.onPageSelected(i);
        }
    }

    public final void c(int i) {
        if ((this.e == 3 && this.f == 0) || this.f == i) {
            return;
        }
        this.f = i;
        ViewPager2.OnPageChangeCallback onPageChangeCallback = this.f1767a;
        if (onPageChangeCallback != null) {
            onPageChangeCallback.onPageScrollStateChanged(i);
        }
    }

    public final int d() {
        return this.d.findFirstVisibleItemPosition();
    }

    public double e() {
        r();
        a aVar = this.g;
        return aVar.f1768a + aVar.b;
    }

    public int f() {
        return this.f;
    }

    public boolean g() {
        return this.f == 1;
    }

    public boolean h() {
        return this.m;
    }

    public boolean i() {
        return this.f == 0;
    }

    public final boolean j() {
        int i = this.e;
        return i == 1 || i == 4;
    }

    public void k() {
        this.e = 4;
        q(true);
    }

    public void l() {
        this.l = true;
    }

    public void m() {
        if (!g() || this.m) {
            this.m = false;
            r();
            a aVar = this.g;
            if (aVar.c == 0) {
                int i = aVar.f1768a;
                if (i != this.h) {
                    b(i);
                }
                c(0);
                o();
                return;
            }
            c(2);
        }
    }

    public void n(int i, boolean z) {
        this.e = z ? 2 : 3;
        this.m = false;
        boolean z2 = this.i != i;
        this.i = i;
        c(2);
        if (z2) {
            b(i);
        }
    }

    public final void o() {
        this.e = 0;
        this.f = 0;
        this.g.a();
        this.h = -1;
        this.i = -1;
        this.j = false;
        this.k = false;
        this.m = false;
        this.l = false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
        boolean z = true;
        if ((this.e != 1 || this.f != 1) && i == 1) {
            q(false);
        } else if (j() && i == 2) {
            if (this.k) {
                c(2);
                this.j = true;
            }
        } else {
            if (j() && i == 0) {
                r();
                if (!this.k) {
                    int i2 = this.g.f1768a;
                    if (i2 != -1) {
                        a(i2, 0.0f, 0);
                    }
                } else {
                    a aVar = this.g;
                    if (aVar.c == 0) {
                        int i3 = this.h;
                        int i4 = aVar.f1768a;
                        if (i3 != i4) {
                            b(i4);
                        }
                    } else {
                        z = false;
                    }
                }
                if (z) {
                    c(0);
                    o();
                }
            }
            if (this.e == 2 && i == 0 && this.l) {
                r();
                a aVar2 = this.g;
                if (aVar2.c == 0) {
                    int i5 = this.i;
                    int i6 = aVar2.f1768a;
                    if (i5 != i6) {
                        if (i6 == -1) {
                            i6 = 0;
                        }
                        b(i6);
                    }
                    c(0);
                    o();
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x001d, code lost:
        if ((r5 < 0) == r3.b.c()) goto L36;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0039  */
    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onScrolled(@androidx.annotation.NonNull androidx.recyclerview.widget.RecyclerView r4, int r5, int r6) {
        /*
            r3 = this;
            r4 = 1
            r3.k = r4
            r3.r()
            boolean r0 = r3.j
            r1 = -1
            r2 = 0
            if (r0 == 0) goto L3d
            r3.j = r2
            if (r6 > 0) goto L22
            if (r6 != 0) goto L20
            if (r5 >= 0) goto L16
            r5 = r4
            goto L17
        L16:
            r5 = r2
        L17:
            androidx.viewpager2.widget.ViewPager2 r6 = r3.b
            boolean r6 = r6.c()
            if (r5 != r6) goto L20
            goto L22
        L20:
            r5 = r2
            goto L23
        L22:
            r5 = r4
        L23:
            if (r5 == 0) goto L2f
            androidx.viewpager2.widget.e$a r5 = r3.g
            int r6 = r5.c
            if (r6 == 0) goto L2f
            int r5 = r5.f1768a
            int r5 = r5 + r4
            goto L33
        L2f:
            androidx.viewpager2.widget.e$a r5 = r3.g
            int r5 = r5.f1768a
        L33:
            r3.i = r5
            int r6 = r3.h
            if (r6 == r5) goto L4b
            r3.b(r5)
            goto L4b
        L3d:
            int r5 = r3.e
            if (r5 != 0) goto L4b
            androidx.viewpager2.widget.e$a r5 = r3.g
            int r5 = r5.f1768a
            if (r5 != r1) goto L48
            r5 = r2
        L48:
            r3.b(r5)
        L4b:
            androidx.viewpager2.widget.e$a r5 = r3.g
            int r6 = r5.f1768a
            if (r6 != r1) goto L52
            r6 = r2
        L52:
            float r0 = r5.b
            int r5 = r5.c
            r3.a(r6, r0, r5)
            androidx.viewpager2.widget.e$a r5 = r3.g
            int r6 = r5.f1768a
            int r0 = r3.i
            if (r6 == r0) goto L63
            if (r0 != r1) goto L71
        L63:
            int r5 = r5.c
            if (r5 != 0) goto L71
            int r5 = r3.f
            if (r5 == r4) goto L71
            r3.c(r2)
            r3.o()
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager2.widget.e.onScrolled(androidx.recyclerview.widget.RecyclerView, int, int):void");
    }

    public void p(ViewPager2.OnPageChangeCallback onPageChangeCallback) {
        this.f1767a = onPageChangeCallback;
    }

    public final void q(boolean z) {
        this.m = z;
        this.e = z ? 4 : 1;
        int i = this.i;
        if (i != -1) {
            this.h = i;
            this.i = -1;
        } else if (this.h == -1) {
            this.h = d();
        }
        c(1);
    }

    public final void r() {
        int top;
        a aVar = this.g;
        int findFirstVisibleItemPosition = this.d.findFirstVisibleItemPosition();
        aVar.f1768a = findFirstVisibleItemPosition;
        if (findFirstVisibleItemPosition == -1) {
            aVar.a();
            return;
        }
        View findViewByPosition = this.d.findViewByPosition(findFirstVisibleItemPosition);
        if (findViewByPosition == null) {
            aVar.a();
            return;
        }
        int leftDecorationWidth = this.d.getLeftDecorationWidth(findViewByPosition);
        int rightDecorationWidth = this.d.getRightDecorationWidth(findViewByPosition);
        int topDecorationHeight = this.d.getTopDecorationHeight(findViewByPosition);
        int bottomDecorationHeight = this.d.getBottomDecorationHeight(findViewByPosition);
        ViewGroup.LayoutParams layoutParams = findViewByPosition.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            leftDecorationWidth += marginLayoutParams.leftMargin;
            rightDecorationWidth += marginLayoutParams.rightMargin;
            topDecorationHeight += marginLayoutParams.topMargin;
            bottomDecorationHeight += marginLayoutParams.bottomMargin;
        }
        int height = findViewByPosition.getHeight() + topDecorationHeight + bottomDecorationHeight;
        int width = findViewByPosition.getWidth() + leftDecorationWidth + rightDecorationWidth;
        if (this.d.getOrientation() == 0) {
            top = (findViewByPosition.getLeft() - leftDecorationWidth) - this.c.getPaddingLeft();
            if (this.b.c()) {
                top = -top;
            }
            height = width;
        } else {
            top = (findViewByPosition.getTop() - topDecorationHeight) - this.c.getPaddingTop();
        }
        int i = -top;
        aVar.c = i;
        if (i < 0) {
            if (new androidx.viewpager2.widget.a(this.d).d()) {
                throw new IllegalStateException("Page(s) contain a ViewGroup with a LayoutTransition (or animateLayoutChanges=\"true\"), which interferes with the scrolling animation. Make sure to call getLayoutTransition().setAnimateParentHierarchy(false) on all ViewGroups with a LayoutTransition before an animation is started.");
            }
            throw new IllegalStateException(String.format(Locale.US, "Page can only be offset by a positive amount, not by %d", Integer.valueOf(aVar.c)));
        }
        aVar.b = height == 0 ? 0.0f : i / height;
    }
}
