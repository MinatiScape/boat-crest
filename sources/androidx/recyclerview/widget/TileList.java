package androidx.recyclerview.widget;

import android.util.SparseArray;
import java.lang.reflect.Array;
/* loaded from: classes.dex */
public class TileList<T> {

    /* renamed from: a  reason: collision with root package name */
    public final int f1625a;
    public final SparseArray<Tile<T>> b = new SparseArray<>(10);
    public Tile<T> c;

    /* loaded from: classes.dex */
    public static class Tile<T> {

        /* renamed from: a  reason: collision with root package name */
        public Tile<T> f1626a;
        public int mItemCount;
        public final T[] mItems;
        public int mStartPosition;

        public Tile(Class<T> cls, int i) {
            this.mItems = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, i));
        }

        public boolean a(int i) {
            int i2 = this.mStartPosition;
            return i2 <= i && i < i2 + this.mItemCount;
        }

        public T b(int i) {
            return this.mItems[i - this.mStartPosition];
        }
    }

    public TileList(int i) {
        this.f1625a = i;
    }

    public Tile<T> a(Tile<T> tile) {
        int indexOfKey = this.b.indexOfKey(tile.mStartPosition);
        if (indexOfKey < 0) {
            this.b.put(tile.mStartPosition, tile);
            return null;
        }
        Tile<T> valueAt = this.b.valueAt(indexOfKey);
        this.b.setValueAt(indexOfKey, tile);
        if (this.c == valueAt) {
            this.c = tile;
        }
        return valueAt;
    }

    public void b() {
        this.b.clear();
    }

    public Tile<T> c(int i) {
        if (i < 0 || i >= this.b.size()) {
            return null;
        }
        return this.b.valueAt(i);
    }

    public T d(int i) {
        Tile<T> tile = this.c;
        if (tile == null || !tile.a(i)) {
            int indexOfKey = this.b.indexOfKey(i - (i % this.f1625a));
            if (indexOfKey < 0) {
                return null;
            }
            this.c = this.b.valueAt(indexOfKey);
        }
        return this.c.b(i);
    }

    public Tile<T> e(int i) {
        Tile<T> tile = this.b.get(i);
        if (this.c == tile) {
            this.c = null;
        }
        this.b.delete(i);
        return tile;
    }

    public int f() {
        return this.b.size();
    }
}
