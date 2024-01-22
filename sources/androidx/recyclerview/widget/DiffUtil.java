package androidx.recyclerview.widget;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public class DiffUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final Comparator<c> f1584a = new a();

    /* loaded from: classes.dex */
    public static abstract class Callback {
        public abstract boolean areContentsTheSame(int i, int i2);

        public abstract boolean areItemsTheSame(int i, int i2);

        @Nullable
        public Object getChangePayload(int i, int i2) {
            return null;
        }

        public abstract int getNewListSize();

        public abstract int getOldListSize();
    }

    /* loaded from: classes.dex */
    public static class DiffResult {
        public static final int NO_POSITION = -1;

        /* renamed from: a  reason: collision with root package name */
        public final List<c> f1585a;
        public final int[] b;
        public final int[] c;
        public final Callback d;
        public final int e;
        public final int f;
        public final boolean g;

        public DiffResult(Callback callback, List<c> list, int[] iArr, int[] iArr2, boolean z) {
            this.f1585a = list;
            this.b = iArr;
            this.c = iArr2;
            Arrays.fill(iArr, 0);
            Arrays.fill(iArr2, 0);
            this.d = callback;
            this.e = callback.getOldListSize();
            this.f = callback.getNewListSize();
            this.g = z;
            a();
            c();
        }

        @Nullable
        public static d e(Collection<d> collection, int i, boolean z) {
            d dVar;
            Iterator<d> it = collection.iterator();
            while (true) {
                if (!it.hasNext()) {
                    dVar = null;
                    break;
                }
                dVar = it.next();
                if (dVar.f1588a == i && dVar.c == z) {
                    it.remove();
                    break;
                }
            }
            while (it.hasNext()) {
                d next = it.next();
                if (z) {
                    next.b--;
                } else {
                    next.b++;
                }
            }
            return dVar;
        }

        public final void a() {
            c cVar = this.f1585a.isEmpty() ? null : this.f1585a.get(0);
            if (cVar == null || cVar.f1587a != 0 || cVar.b != 0) {
                this.f1585a.add(0, new c(0, 0, 0));
            }
            this.f1585a.add(new c(this.e, this.f, 0));
        }

        public final void b(int i) {
            int size = this.f1585a.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                c cVar = this.f1585a.get(i3);
                while (i2 < cVar.b) {
                    if (this.c[i2] == 0 && this.d.areItemsTheSame(i, i2)) {
                        int i4 = this.d.areContentsTheSame(i, i2) ? 8 : 4;
                        this.b[i] = (i2 << 4) | i4;
                        this.c[i2] = (i << 4) | i4;
                        return;
                    }
                    i2++;
                }
                i2 = cVar.b();
            }
        }

        public final void c() {
            for (c cVar : this.f1585a) {
                for (int i = 0; i < cVar.c; i++) {
                    int i2 = cVar.f1587a + i;
                    int i3 = cVar.b + i;
                    int i4 = this.d.areContentsTheSame(i2, i3) ? 1 : 2;
                    this.b[i2] = (i3 << 4) | i4;
                    this.c[i3] = (i2 << 4) | i4;
                }
            }
            if (this.g) {
                d();
            }
        }

        public int convertNewPositionToOld(@IntRange(from = 0) int i) {
            if (i >= 0 && i < this.f) {
                int i2 = this.c[i];
                if ((i2 & 15) == 0) {
                    return -1;
                }
                return i2 >> 4;
            }
            throw new IndexOutOfBoundsException("Index out of bounds - passed position = " + i + ", new list size = " + this.f);
        }

        public int convertOldPositionToNew(@IntRange(from = 0) int i) {
            if (i >= 0 && i < this.e) {
                int i2 = this.b[i];
                if ((i2 & 15) == 0) {
                    return -1;
                }
                return i2 >> 4;
            }
            throw new IndexOutOfBoundsException("Index out of bounds - passed position = " + i + ", old list size = " + this.e);
        }

        public final void d() {
            int i = 0;
            for (c cVar : this.f1585a) {
                while (i < cVar.f1587a) {
                    if (this.b[i] == 0) {
                        b(i);
                    }
                    i++;
                }
                i = cVar.a();
            }
        }

        public void dispatchUpdatesTo(@NonNull RecyclerView.Adapter adapter) {
            dispatchUpdatesTo(new AdapterListUpdateCallback(adapter));
        }

        public void dispatchUpdatesTo(@NonNull ListUpdateCallback listUpdateCallback) {
            BatchingListUpdateCallback batchingListUpdateCallback;
            int i;
            if (listUpdateCallback instanceof BatchingListUpdateCallback) {
                batchingListUpdateCallback = (BatchingListUpdateCallback) listUpdateCallback;
            } else {
                batchingListUpdateCallback = new BatchingListUpdateCallback(listUpdateCallback);
            }
            int i2 = this.e;
            ArrayDeque arrayDeque = new ArrayDeque();
            int i3 = this.e;
            int i4 = this.f;
            for (int size = this.f1585a.size() - 1; size >= 0; size--) {
                c cVar = this.f1585a.get(size);
                int a2 = cVar.a();
                int b = cVar.b();
                while (true) {
                    if (i3 <= a2) {
                        break;
                    }
                    i3--;
                    int i5 = this.b[i3];
                    if ((i5 & 12) != 0) {
                        int i6 = i5 >> 4;
                        d e = e(arrayDeque, i6, false);
                        if (e != null) {
                            int i7 = (i2 - e.b) - 1;
                            batchingListUpdateCallback.onMoved(i3, i7);
                            if ((i5 & 4) != 0) {
                                batchingListUpdateCallback.onChanged(i7, 1, this.d.getChangePayload(i3, i6));
                            }
                        } else {
                            arrayDeque.add(new d(i3, (i2 - i3) - 1, true));
                        }
                    } else {
                        batchingListUpdateCallback.onRemoved(i3, 1);
                        i2--;
                    }
                }
                while (i4 > b) {
                    i4--;
                    int i8 = this.c[i4];
                    if ((i8 & 12) != 0) {
                        int i9 = i8 >> 4;
                        d e2 = e(arrayDeque, i9, true);
                        if (e2 == null) {
                            arrayDeque.add(new d(i4, i2 - i3, false));
                        } else {
                            batchingListUpdateCallback.onMoved((i2 - e2.b) - 1, i3);
                            if ((i8 & 4) != 0) {
                                batchingListUpdateCallback.onChanged(i3, 1, this.d.getChangePayload(i9, i4));
                            }
                        }
                    } else {
                        batchingListUpdateCallback.onInserted(i3, 1);
                        i2++;
                    }
                }
                int i10 = cVar.f1587a;
                int i11 = cVar.b;
                for (i = 0; i < cVar.c; i++) {
                    if ((this.b[i10] & 15) == 2) {
                        batchingListUpdateCallback.onChanged(i10, 1, this.d.getChangePayload(i10, i11));
                    }
                    i10++;
                    i11++;
                }
                i3 = cVar.f1587a;
                i4 = cVar.b;
            }
            batchingListUpdateCallback.dispatchLastEvent();
        }
    }

    /* loaded from: classes.dex */
    public static abstract class ItemCallback<T> {
        public abstract boolean areContentsTheSame(@NonNull T t, @NonNull T t2);

        public abstract boolean areItemsTheSame(@NonNull T t, @NonNull T t2);

        @Nullable
        public Object getChangePayload(@NonNull T t, @NonNull T t2) {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public class a implements Comparator<c> {
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(c cVar, c cVar2) {
            return cVar.f1587a - cVar2.f1587a;
        }
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final int[] f1586a;
        public final int b;

        public b(int i) {
            int[] iArr = new int[i];
            this.f1586a = iArr;
            this.b = iArr.length / 2;
        }

        public int[] a() {
            return this.f1586a;
        }

        public int b(int i) {
            return this.f1586a[i + this.b];
        }

        public void c(int i, int i2) {
            this.f1586a[i + this.b] = i2;
        }
    }

    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public final int f1587a;
        public final int b;
        public final int c;

        public c(int i, int i2, int i3) {
            this.f1587a = i;
            this.b = i2;
            this.c = i3;
        }

        public int a() {
            return this.f1587a + this.c;
        }

        public int b() {
            return this.b + this.c;
        }
    }

    /* loaded from: classes.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        public int f1588a;
        public int b;
        public boolean c;

        public d(int i, int i2, boolean z) {
            this.f1588a = i;
            this.b = i2;
            this.c = z;
        }
    }

    /* loaded from: classes.dex */
    public static class e {

        /* renamed from: a  reason: collision with root package name */
        public int f1589a;
        public int b;
        public int c;
        public int d;

        public e() {
        }

        public int a() {
            return this.d - this.c;
        }

        public int b() {
            return this.b - this.f1589a;
        }

        public e(int i, int i2, int i3, int i4) {
            this.f1589a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
        }
    }

    /* loaded from: classes.dex */
    public static class f {

        /* renamed from: a  reason: collision with root package name */
        public int f1590a;
        public int b;
        public int c;
        public int d;
        public boolean e;

        public int a() {
            return Math.min(this.c - this.f1590a, this.d - this.b);
        }

        public boolean b() {
            return this.d - this.b != this.c - this.f1590a;
        }

        public boolean c() {
            return this.d - this.b > this.c - this.f1590a;
        }

        @NonNull
        public c d() {
            if (b()) {
                if (this.e) {
                    return new c(this.f1590a, this.b, a());
                }
                if (c()) {
                    return new c(this.f1590a, this.b + 1, a());
                }
                return new c(this.f1590a + 1, this.b, a());
            }
            int i = this.f1590a;
            return new c(i, this.b, this.c - i);
        }
    }

    @Nullable
    public static f a(e eVar, Callback callback, b bVar, b bVar2, int i) {
        int b2;
        int i2;
        int i3;
        boolean z = (eVar.b() - eVar.a()) % 2 == 0;
        int b3 = eVar.b() - eVar.a();
        int i4 = -i;
        for (int i5 = i4; i5 <= i; i5 += 2) {
            if (i5 != i4 && (i5 == i || bVar2.b(i5 + 1) >= bVar2.b(i5 - 1))) {
                b2 = bVar2.b(i5 - 1);
                i2 = b2 - 1;
            } else {
                b2 = bVar2.b(i5 + 1);
                i2 = b2;
            }
            int i6 = eVar.d - ((eVar.b - i2) - i5);
            int i7 = (i == 0 || i2 != b2) ? i6 : i6 + 1;
            while (i2 > eVar.f1589a && i6 > eVar.c && callback.areItemsTheSame(i2 - 1, i6 - 1)) {
                i2--;
                i6--;
            }
            bVar2.c(i5, i2);
            if (z && (i3 = b3 - i5) >= i4 && i3 <= i && bVar.b(i3) >= i2) {
                f fVar = new f();
                fVar.f1590a = i2;
                fVar.b = i6;
                fVar.c = b2;
                fVar.d = i7;
                fVar.e = true;
                return fVar;
            }
        }
        return null;
    }

    @Nullable
    public static f b(e eVar, Callback callback, b bVar, b bVar2, int i) {
        int b2;
        int i2;
        int i3;
        boolean z = Math.abs(eVar.b() - eVar.a()) % 2 == 1;
        int b3 = eVar.b() - eVar.a();
        int i4 = -i;
        for (int i5 = i4; i5 <= i; i5 += 2) {
            if (i5 != i4 && (i5 == i || bVar.b(i5 + 1) <= bVar.b(i5 - 1))) {
                b2 = bVar.b(i5 - 1);
                i2 = b2 + 1;
            } else {
                b2 = bVar.b(i5 + 1);
                i2 = b2;
            }
            int i6 = (eVar.c + (i2 - eVar.f1589a)) - i5;
            int i7 = (i == 0 || i2 != b2) ? i6 : i6 - 1;
            while (i2 < eVar.b && i6 < eVar.d && callback.areItemsTheSame(i2, i6)) {
                i2++;
                i6++;
            }
            bVar.c(i5, i2);
            if (z && (i3 = b3 - i5) >= i4 + 1 && i3 <= i - 1 && bVar2.b(i3) <= i2) {
                f fVar = new f();
                fVar.f1590a = b2;
                fVar.b = i7;
                fVar.c = i2;
                fVar.d = i6;
                fVar.e = false;
                return fVar;
            }
        }
        return null;
    }

    @Nullable
    public static f c(e eVar, Callback callback, b bVar, b bVar2) {
        if (eVar.b() >= 1 && eVar.a() >= 1) {
            int b2 = ((eVar.b() + eVar.a()) + 1) / 2;
            bVar.c(1, eVar.f1589a);
            bVar2.c(1, eVar.b);
            for (int i = 0; i < b2; i++) {
                f b3 = b(eVar, callback, bVar, bVar2, i);
                if (b3 != null) {
                    return b3;
                }
                f a2 = a(eVar, callback, bVar, bVar2, i);
                if (a2 != null) {
                    return a2;
                }
            }
        }
        return null;
    }

    @NonNull
    public static DiffResult calculateDiff(@NonNull Callback callback) {
        return calculateDiff(callback, true);
    }

    @NonNull
    public static DiffResult calculateDiff(@NonNull Callback callback, boolean z) {
        int oldListSize = callback.getOldListSize();
        int newListSize = callback.getNewListSize();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new e(0, oldListSize, 0, newListSize));
        int i = ((((oldListSize + newListSize) + 1) / 2) * 2) + 1;
        b bVar = new b(i);
        b bVar2 = new b(i);
        ArrayList arrayList3 = new ArrayList();
        while (!arrayList2.isEmpty()) {
            e eVar = (e) arrayList2.remove(arrayList2.size() - 1);
            f c2 = c(eVar, callback, bVar, bVar2);
            if (c2 != null) {
                if (c2.a() > 0) {
                    arrayList.add(c2.d());
                }
                e eVar2 = arrayList3.isEmpty() ? new e() : (e) arrayList3.remove(arrayList3.size() - 1);
                eVar2.f1589a = eVar.f1589a;
                eVar2.c = eVar.c;
                eVar2.b = c2.f1590a;
                eVar2.d = c2.b;
                arrayList2.add(eVar2);
                eVar.b = eVar.b;
                eVar.d = eVar.d;
                eVar.f1589a = c2.c;
                eVar.c = c2.d;
                arrayList2.add(eVar);
            } else {
                arrayList3.add(eVar);
            }
        }
        Collections.sort(arrayList, f1584a);
        return new DiffResult(callback, arrayList, bVar.a(), bVar2.a(), z);
    }
}
