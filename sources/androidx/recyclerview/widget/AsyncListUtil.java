package androidx.recyclerview.widget;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;
import androidx.recyclerview.widget.ThreadUtil;
import androidx.recyclerview.widget.TileList;
/* loaded from: classes.dex */
public class AsyncListUtil<T> {

    /* renamed from: a  reason: collision with root package name */
    public final Class<T> f1577a;
    public final int b;
    public final DataCallback<T> c;
    public final ViewCallback d;
    public final TileList<T> e;
    public final ThreadUtil.MainThreadCallback<T> f;
    public final ThreadUtil.BackgroundCallback<T> g;
    public boolean k;
    public final ThreadUtil.MainThreadCallback<T> q;
    public final ThreadUtil.BackgroundCallback<T> r;
    public final int[] h = new int[2];
    public final int[] i = new int[2];
    public final int[] j = new int[2];
    public int l = 0;
    public int m = 0;
    public int n = 0;
    public int o = 0;
    public final SparseIntArray p = new SparseIntArray();

    /* loaded from: classes.dex */
    public static abstract class DataCallback<T> {
        @WorkerThread
        public abstract void fillData(@NonNull T[] tArr, int i, int i2);

        @WorkerThread
        public int getMaxCachedTiles() {
            return 10;
        }

        @WorkerThread
        public void recycleData(@NonNull T[] tArr, int i) {
        }

        @WorkerThread
        public abstract int refreshData();
    }

    /* loaded from: classes.dex */
    public static abstract class ViewCallback {
        public static final int HINT_SCROLL_ASC = 2;
        public static final int HINT_SCROLL_DESC = 1;
        public static final int HINT_SCROLL_NONE = 0;

        @UiThread
        public void extendRangeInto(@NonNull int[] iArr, @NonNull int[] iArr2, int i) {
            int i2 = (iArr[1] - iArr[0]) + 1;
            int i3 = i2 / 2;
            iArr2[0] = iArr[0] - (i == 1 ? i2 : i3);
            int i4 = iArr[1];
            if (i != 2) {
                i2 = i3;
            }
            iArr2[1] = i4 + i2;
        }

        @UiThread
        public abstract void getItemRangeInto(@NonNull int[] iArr);

        @UiThread
        public abstract void onDataRefresh();

        @UiThread
        public abstract void onItemLoaded(int i);
    }

    /* loaded from: classes.dex */
    public class a implements ThreadUtil.MainThreadCallback<Object> {
        public a() {
        }

        public final boolean a(int i) {
            return i == AsyncListUtil.this.o;
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
        public void addTile(int i, TileList.Tile<Object> tile) {
            if (!a(i)) {
                AsyncListUtil.this.g.recycleTile(tile);
                return;
            }
            TileList.Tile<T> a2 = AsyncListUtil.this.e.a(tile);
            if (a2 != null) {
                Log.e("AsyncListUtil", "duplicate tile @" + a2.mStartPosition);
                AsyncListUtil.this.g.recycleTile(a2);
            }
            int i2 = tile.mStartPosition + tile.mItemCount;
            int i3 = 0;
            while (i3 < AsyncListUtil.this.p.size()) {
                int keyAt = AsyncListUtil.this.p.keyAt(i3);
                if (tile.mStartPosition > keyAt || keyAt >= i2) {
                    i3++;
                } else {
                    AsyncListUtil.this.p.removeAt(i3);
                    AsyncListUtil.this.d.onItemLoaded(keyAt);
                }
            }
        }

        public final void b() {
            for (int i = 0; i < AsyncListUtil.this.e.f(); i++) {
                AsyncListUtil asyncListUtil = AsyncListUtil.this;
                asyncListUtil.g.recycleTile(asyncListUtil.e.c(i));
            }
            AsyncListUtil.this.e.b();
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
        public void removeTile(int i, int i2) {
            if (a(i)) {
                TileList.Tile<T> e = AsyncListUtil.this.e.e(i2);
                if (e == null) {
                    Log.e("AsyncListUtil", "tile not found @" + i2);
                    return;
                }
                AsyncListUtil.this.g.recycleTile(e);
            }
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
        public void updateItemCount(int i, int i2) {
            if (a(i)) {
                AsyncListUtil asyncListUtil = AsyncListUtil.this;
                asyncListUtil.m = i2;
                asyncListUtil.d.onDataRefresh();
                AsyncListUtil asyncListUtil2 = AsyncListUtil.this;
                asyncListUtil2.n = asyncListUtil2.o;
                b();
                AsyncListUtil asyncListUtil3 = AsyncListUtil.this;
                asyncListUtil3.k = false;
                asyncListUtil3.b();
            }
        }
    }

    /* loaded from: classes.dex */
    public class b implements ThreadUtil.BackgroundCallback<Object> {

        /* renamed from: a  reason: collision with root package name */
        public TileList.Tile<Object> f1579a;
        public final SparseBooleanArray b = new SparseBooleanArray();
        public int c;
        public int d;
        public int e;
        public int f;

        public b() {
        }

        public final TileList.Tile<Object> a() {
            TileList.Tile<Object> tile = this.f1579a;
            if (tile != null) {
                this.f1579a = tile.f1626a;
                return tile;
            }
            AsyncListUtil asyncListUtil = AsyncListUtil.this;
            return new TileList.Tile<>(asyncListUtil.f1577a, asyncListUtil.b);
        }

        public final void b(TileList.Tile<Object> tile) {
            this.b.put(tile.mStartPosition, true);
            AsyncListUtil.this.f.addTile(this.c, tile);
        }

        public final void c(int i) {
            int maxCachedTiles = AsyncListUtil.this.c.getMaxCachedTiles();
            while (this.b.size() >= maxCachedTiles) {
                int keyAt = this.b.keyAt(0);
                SparseBooleanArray sparseBooleanArray = this.b;
                int keyAt2 = sparseBooleanArray.keyAt(sparseBooleanArray.size() - 1);
                int i2 = this.e - keyAt;
                int i3 = keyAt2 - this.f;
                if (i2 > 0 && (i2 >= i3 || i == 2)) {
                    f(keyAt);
                } else if (i3 <= 0) {
                    return;
                } else {
                    if (i2 >= i3 && i != 1) {
                        return;
                    }
                    f(keyAt2);
                }
            }
        }

        public final int d(int i) {
            return i - (i % AsyncListUtil.this.b);
        }

        public final boolean e(int i) {
            return this.b.get(i);
        }

        public final void f(int i) {
            this.b.delete(i);
            AsyncListUtil.this.f.removeTile(this.c, i);
        }

        public final void g(int i, int i2, int i3, boolean z) {
            int i4 = i;
            while (i4 <= i2) {
                AsyncListUtil.this.g.loadTile(z ? (i2 + i) - i4 : i4, i3);
                i4 += AsyncListUtil.this.b;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void loadTile(int i, int i2) {
            if (e(i)) {
                return;
            }
            TileList.Tile<Object> a2 = a();
            a2.mStartPosition = i;
            int min = Math.min(AsyncListUtil.this.b, this.d - i);
            a2.mItemCount = min;
            AsyncListUtil.this.c.fillData(a2.mItems, a2.mStartPosition, min);
            c(i2);
            b(a2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void recycleTile(TileList.Tile<Object> tile) {
            AsyncListUtil.this.c.recycleData(tile.mItems, tile.mItemCount);
            tile.f1626a = (TileList.Tile<T>) this.f1579a;
            this.f1579a = tile;
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void refresh(int i) {
            this.c = i;
            this.b.clear();
            int refreshData = AsyncListUtil.this.c.refreshData();
            this.d = refreshData;
            AsyncListUtil.this.f.updateItemCount(this.c, refreshData);
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void updateRange(int i, int i2, int i3, int i4, int i5) {
            if (i > i2) {
                return;
            }
            int d = d(i);
            int d2 = d(i2);
            this.e = d(i3);
            int d3 = d(i4);
            this.f = d3;
            if (i5 == 1) {
                g(this.e, d2, i5, true);
                g(d2 + AsyncListUtil.this.b, this.f, i5, false);
                return;
            }
            g(d, d3, i5, false);
            g(this.e, d - AsyncListUtil.this.b, i5, true);
        }
    }

    public AsyncListUtil(@NonNull Class<T> cls, int i, @NonNull DataCallback<T> dataCallback, @NonNull ViewCallback viewCallback) {
        a aVar = new a();
        this.q = aVar;
        b bVar = new b();
        this.r = bVar;
        this.f1577a = cls;
        this.b = i;
        this.c = dataCallback;
        this.d = viewCallback;
        this.e = new TileList<>(i);
        h hVar = new h();
        this.f = hVar.b(aVar);
        this.g = hVar.a(bVar);
        refresh();
    }

    public final boolean a() {
        return this.o != this.n;
    }

    public void b() {
        this.d.getItemRangeInto(this.h);
        int[] iArr = this.h;
        if (iArr[0] > iArr[1] || iArr[0] < 0 || iArr[1] >= this.m) {
            return;
        }
        if (!this.k) {
            this.l = 0;
        } else {
            int i = iArr[0];
            int[] iArr2 = this.i;
            if (i <= iArr2[1] && iArr2[0] <= iArr[1]) {
                if (iArr[0] < iArr2[0]) {
                    this.l = 1;
                } else if (iArr[0] > iArr2[0]) {
                    this.l = 2;
                }
            } else {
                this.l = 0;
            }
        }
        int[] iArr3 = this.i;
        iArr3[0] = iArr[0];
        iArr3[1] = iArr[1];
        this.d.extendRangeInto(iArr, this.j, this.l);
        int[] iArr4 = this.j;
        iArr4[0] = Math.min(this.h[0], Math.max(iArr4[0], 0));
        int[] iArr5 = this.j;
        iArr5[1] = Math.max(this.h[1], Math.min(iArr5[1], this.m - 1));
        ThreadUtil.BackgroundCallback<T> backgroundCallback = this.g;
        int[] iArr6 = this.h;
        int i2 = iArr6[0];
        int i3 = iArr6[1];
        int[] iArr7 = this.j;
        backgroundCallback.updateRange(i2, i3, iArr7[0], iArr7[1], this.l);
    }

    @Nullable
    public T getItem(int i) {
        if (i >= 0 && i < this.m) {
            T d = this.e.d(i);
            if (d == null && !a()) {
                this.p.put(i, 0);
            }
            return d;
        }
        throw new IndexOutOfBoundsException(i + " is not within 0 and " + this.m);
    }

    public int getItemCount() {
        return this.m;
    }

    public void onRangeChanged() {
        if (a()) {
            return;
        }
        b();
        this.k = true;
    }

    public void refresh() {
        this.p.clear();
        ThreadUtil.BackgroundCallback<T> backgroundCallback = this.g;
        int i = this.o + 1;
        this.o = i;
        backgroundCallback.refresh(i);
    }
}
