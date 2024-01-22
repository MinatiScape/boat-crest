package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import androidx.annotation.Nullable;
import androidx.core.os.TraceCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public final class e implements Runnable {
    public static final ThreadLocal<e> l = new ThreadLocal<>();
    public static Comparator<c> m = new a();
    public long i;
    public long j;
    public ArrayList<RecyclerView> h = new ArrayList<>();
    public ArrayList<c> k = new ArrayList<>();

    /* loaded from: classes.dex */
    public class a implements Comparator<c> {
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(c cVar, c cVar2) {
            RecyclerView recyclerView = cVar.d;
            if ((recyclerView == null) != (cVar2.d == null)) {
                return recyclerView == null ? 1 : -1;
            }
            boolean z = cVar.f1642a;
            if (z != cVar2.f1642a) {
                return z ? -1 : 1;
            }
            int i = cVar2.b - cVar.b;
            if (i != 0) {
                return i;
            }
            int i2 = cVar.c - cVar2.c;
            if (i2 != 0) {
                return i2;
            }
            return 0;
        }
    }

    @SuppressLint({"VisibleForTests"})
    /* loaded from: classes.dex */
    public static class b implements RecyclerView.LayoutManager.LayoutPrefetchRegistry {

        /* renamed from: a  reason: collision with root package name */
        public int f1641a;
        public int b;
        public int[] c;
        public int d;

        public void a() {
            int[] iArr = this.c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.d = 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager.LayoutPrefetchRegistry
        public void addPosition(int i, int i2) {
            if (i < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            }
            if (i2 >= 0) {
                int i3 = this.d * 2;
                int[] iArr = this.c;
                if (iArr == null) {
                    int[] iArr2 = new int[4];
                    this.c = iArr2;
                    Arrays.fill(iArr2, -1);
                } else if (i3 >= iArr.length) {
                    int[] iArr3 = new int[i3 * 2];
                    this.c = iArr3;
                    System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                }
                int[] iArr4 = this.c;
                iArr4[i3] = i;
                iArr4[i3 + 1] = i2;
                this.d++;
                return;
            }
            throw new IllegalArgumentException("Pixel distance must be non-negative");
        }

        public void b(RecyclerView recyclerView, boolean z) {
            this.d = 0;
            int[] iArr = this.c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            RecyclerView.LayoutManager layoutManager = recyclerView.t;
            if (recyclerView.s == null || layoutManager == null || !layoutManager.isItemPrefetchEnabled()) {
                return;
            }
            if (z) {
                if (!recyclerView.k.p()) {
                    layoutManager.collectInitialPrefetchPositions(recyclerView.s.getItemCount(), this);
                }
            } else if (!recyclerView.hasPendingAdapterUpdates()) {
                layoutManager.collectAdjacentPrefetchPositions(this.f1641a, this.b, recyclerView.p0, this);
            }
            int i = this.d;
            if (i > layoutManager.t) {
                layoutManager.t = i;
                layoutManager.u = z;
                recyclerView.i.E();
            }
        }

        public boolean c(int i) {
            if (this.c != null) {
                int i2 = this.d * 2;
                for (int i3 = 0; i3 < i2; i3 += 2) {
                    if (this.c[i3] == i) {
                        return true;
                    }
                }
            }
            return false;
        }

        public void d(int i, int i2) {
            this.f1641a = i;
            this.b = i2;
        }
    }

    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public boolean f1642a;
        public int b;
        public int c;
        public RecyclerView d;
        public int e;

        public void a() {
            this.f1642a = false;
            this.b = 0;
            this.c = 0;
            this.d = null;
            this.e = 0;
        }
    }

    public static boolean e(RecyclerView recyclerView, int i) {
        int j = recyclerView.l.j();
        for (int i2 = 0; i2 < j; i2++) {
            RecyclerView.ViewHolder S = RecyclerView.S(recyclerView.l.i(i2));
            if (S.mPosition == i && !S.isInvalid()) {
                return true;
            }
        }
        return false;
    }

    public void a(RecyclerView recyclerView) {
        this.h.add(recyclerView);
    }

    public final void b() {
        c cVar;
        int size = this.h.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            RecyclerView recyclerView = this.h.get(i2);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.o0.b(recyclerView, false);
                i += recyclerView.o0.d;
            }
        }
        this.k.ensureCapacity(i);
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            RecyclerView recyclerView2 = this.h.get(i4);
            if (recyclerView2.getWindowVisibility() == 0) {
                b bVar = recyclerView2.o0;
                int abs = Math.abs(bVar.f1641a) + Math.abs(bVar.b);
                for (int i5 = 0; i5 < bVar.d * 2; i5 += 2) {
                    if (i3 >= this.k.size()) {
                        cVar = new c();
                        this.k.add(cVar);
                    } else {
                        cVar = this.k.get(i3);
                    }
                    int[] iArr = bVar.c;
                    int i6 = iArr[i5 + 1];
                    cVar.f1642a = i6 <= abs;
                    cVar.b = abs;
                    cVar.c = i6;
                    cVar.d = recyclerView2;
                    cVar.e = iArr[i5];
                    i3++;
                }
            }
        }
        Collections.sort(this.k, m);
    }

    public final void c(c cVar, long j) {
        RecyclerView.ViewHolder i = i(cVar.d, cVar.e, cVar.f1642a ? Long.MAX_VALUE : j);
        if (i == null || i.mNestedRecyclerView == null || !i.isBound() || i.isInvalid()) {
            return;
        }
        h(i.mNestedRecyclerView.get(), j);
    }

    public final void d(long j) {
        for (int i = 0; i < this.k.size(); i++) {
            c cVar = this.k.get(i);
            if (cVar.d == null) {
                return;
            }
            c(cVar, j);
            cVar.a();
        }
    }

    public void f(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView.isAttachedToWindow() && this.i == 0) {
            this.i = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        recyclerView.o0.d(i, i2);
    }

    public void g(long j) {
        b();
        d(j);
    }

    public final void h(@Nullable RecyclerView recyclerView, long j) {
        if (recyclerView == null) {
            return;
        }
        if (recyclerView.L && recyclerView.l.j() != 0) {
            recyclerView.z0();
        }
        b bVar = recyclerView.o0;
        bVar.b(recyclerView, true);
        if (bVar.d != 0) {
            try {
                TraceCompat.beginSection("RV Nested Prefetch");
                recyclerView.p0.b(recyclerView.s);
                for (int i = 0; i < bVar.d * 2; i += 2) {
                    i(recyclerView, bVar.c[i], j);
                }
            } finally {
                TraceCompat.endSection();
            }
        }
    }

    public final RecyclerView.ViewHolder i(RecyclerView recyclerView, int i, long j) {
        if (e(recyclerView, i)) {
            return null;
        }
        RecyclerView.Recycler recycler = recyclerView.i;
        try {
            recyclerView.n0();
            RecyclerView.ViewHolder C = recycler.C(i, false, j);
            if (C != null) {
                if (C.isBound() && !C.isInvalid()) {
                    recycler.recycleView(C.itemView);
                } else {
                    recycler.a(C, false);
                }
            }
            return C;
        } finally {
            recyclerView.p0(false);
        }
    }

    public void j(RecyclerView recyclerView) {
        this.h.remove(recyclerView);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            TraceCompat.beginSection("RV Prefetch");
            if (!this.h.isEmpty()) {
                int size = this.h.size();
                long j = 0;
                for (int i = 0; i < size; i++) {
                    RecyclerView recyclerView = this.h.get(i);
                    if (recyclerView.getWindowVisibility() == 0) {
                        j = Math.max(recyclerView.getDrawingTime(), j);
                    }
                }
                if (j != 0) {
                    g(TimeUnit.MILLISECONDS.toNanos(j) + this.j);
                }
            }
        } finally {
            this.i = 0L;
            TraceCompat.endSection();
        }
    }
}
