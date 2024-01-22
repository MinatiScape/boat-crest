package androidx.recyclerview.widget;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.collection.LongSparseArray;
import androidx.collection.SimpleArrayMap;
import androidx.core.util.Pools;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes.dex */
public class l {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    public final SimpleArrayMap<RecyclerView.ViewHolder, a> f1652a = new SimpleArrayMap<>();
    @VisibleForTesting
    public final LongSparseArray<RecyclerView.ViewHolder> b = new LongSparseArray<>();

    /* loaded from: classes.dex */
    public static class a {
        public static Pools.Pool<a> d = new Pools.SimplePool(20);

        /* renamed from: a  reason: collision with root package name */
        public int f1653a;
        @Nullable
        public RecyclerView.ItemAnimator.ItemHolderInfo b;
        @Nullable
        public RecyclerView.ItemAnimator.ItemHolderInfo c;

        public static void a() {
            do {
            } while (d.acquire() != null);
        }

        public static a b() {
            a acquire = d.acquire();
            return acquire == null ? new a() : acquire;
        }

        public static void c(a aVar) {
            aVar.f1653a = 0;
            aVar.b = null;
            aVar.c = null;
            d.release(aVar);
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        void a(RecyclerView.ViewHolder viewHolder, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2);

        void b(RecyclerView.ViewHolder viewHolder);

        void c(RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2);

        void d(RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2);
    }

    public void a(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo) {
        a aVar = this.f1652a.get(viewHolder);
        if (aVar == null) {
            aVar = a.b();
            this.f1652a.put(viewHolder, aVar);
        }
        aVar.f1653a |= 2;
        aVar.b = itemHolderInfo;
    }

    public void b(RecyclerView.ViewHolder viewHolder) {
        a aVar = this.f1652a.get(viewHolder);
        if (aVar == null) {
            aVar = a.b();
            this.f1652a.put(viewHolder, aVar);
        }
        aVar.f1653a |= 1;
    }

    public void c(long j, RecyclerView.ViewHolder viewHolder) {
        this.b.put(j, viewHolder);
    }

    public void d(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo) {
        a aVar = this.f1652a.get(viewHolder);
        if (aVar == null) {
            aVar = a.b();
            this.f1652a.put(viewHolder, aVar);
        }
        aVar.c = itemHolderInfo;
        aVar.f1653a |= 8;
    }

    public void e(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo) {
        a aVar = this.f1652a.get(viewHolder);
        if (aVar == null) {
            aVar = a.b();
            this.f1652a.put(viewHolder, aVar);
        }
        aVar.b = itemHolderInfo;
        aVar.f1653a |= 4;
    }

    public void f() {
        this.f1652a.clear();
        this.b.clear();
    }

    public RecyclerView.ViewHolder g(long j) {
        return this.b.get(j);
    }

    public boolean h(RecyclerView.ViewHolder viewHolder) {
        a aVar = this.f1652a.get(viewHolder);
        return (aVar == null || (aVar.f1653a & 1) == 0) ? false : true;
    }

    public boolean i(RecyclerView.ViewHolder viewHolder) {
        a aVar = this.f1652a.get(viewHolder);
        return (aVar == null || (aVar.f1653a & 4) == 0) ? false : true;
    }

    public void j() {
        a.a();
    }

    public void k(RecyclerView.ViewHolder viewHolder) {
        p(viewHolder);
    }

    public final RecyclerView.ItemAnimator.ItemHolderInfo l(RecyclerView.ViewHolder viewHolder, int i) {
        a valueAt;
        RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo;
        int indexOfKey = this.f1652a.indexOfKey(viewHolder);
        if (indexOfKey >= 0 && (valueAt = this.f1652a.valueAt(indexOfKey)) != null) {
            int i2 = valueAt.f1653a;
            if ((i2 & i) != 0) {
                int i3 = (~i) & i2;
                valueAt.f1653a = i3;
                if (i == 4) {
                    itemHolderInfo = valueAt.b;
                } else if (i == 8) {
                    itemHolderInfo = valueAt.c;
                } else {
                    throw new IllegalArgumentException("Must provide flag PRE or POST");
                }
                if ((i3 & 12) == 0) {
                    this.f1652a.removeAt(indexOfKey);
                    a.c(valueAt);
                }
                return itemHolderInfo;
            }
        }
        return null;
    }

    @Nullable
    public RecyclerView.ItemAnimator.ItemHolderInfo m(RecyclerView.ViewHolder viewHolder) {
        return l(viewHolder, 8);
    }

    @Nullable
    public RecyclerView.ItemAnimator.ItemHolderInfo n(RecyclerView.ViewHolder viewHolder) {
        return l(viewHolder, 4);
    }

    public void o(b bVar) {
        for (int size = this.f1652a.size() - 1; size >= 0; size--) {
            RecyclerView.ViewHolder keyAt = this.f1652a.keyAt(size);
            a removeAt = this.f1652a.removeAt(size);
            int i = removeAt.f1653a;
            if ((i & 3) == 3) {
                bVar.b(keyAt);
            } else if ((i & 1) != 0) {
                RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo = removeAt.b;
                if (itemHolderInfo == null) {
                    bVar.b(keyAt);
                } else {
                    bVar.c(keyAt, itemHolderInfo, removeAt.c);
                }
            } else if ((i & 14) == 14) {
                bVar.a(keyAt, removeAt.b, removeAt.c);
            } else if ((i & 12) == 12) {
                bVar.d(keyAt, removeAt.b, removeAt.c);
            } else if ((i & 4) != 0) {
                bVar.c(keyAt, removeAt.b, null);
            } else if ((i & 8) != 0) {
                bVar.a(keyAt, removeAt.b, removeAt.c);
            }
            a.c(removeAt);
        }
    }

    public void p(RecyclerView.ViewHolder viewHolder) {
        a aVar = this.f1652a.get(viewHolder);
        if (aVar == null) {
            return;
        }
        aVar.f1653a &= -2;
    }

    public void q(RecyclerView.ViewHolder viewHolder) {
        int size = this.b.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            } else if (viewHolder == this.b.valueAt(size)) {
                this.b.removeAt(size);
                break;
            } else {
                size--;
            }
        }
        a remove = this.f1652a.remove(viewHolder);
        if (remove != null) {
            a.c(remove);
        }
    }
}
