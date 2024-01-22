package androidx.recyclerview.widget;

import android.util.SparseArray;
import android.util.SparseIntArray;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public interface ViewTypeStorage {

    /* loaded from: classes.dex */
    public static class IsolatedViewTypeStorage implements ViewTypeStorage {

        /* renamed from: a  reason: collision with root package name */
        public SparseArray<i> f1629a = new SparseArray<>();
        public int b = 0;

        /* loaded from: classes.dex */
        public class a implements ViewTypeLookup {

            /* renamed from: a  reason: collision with root package name */
            public SparseIntArray f1630a = new SparseIntArray(1);
            public SparseIntArray b = new SparseIntArray(1);
            public final i c;

            public a(i iVar) {
                this.c = iVar;
            }

            @Override // androidx.recyclerview.widget.ViewTypeStorage.ViewTypeLookup
            public void dispose() {
                IsolatedViewTypeStorage.this.b(this.c);
            }

            @Override // androidx.recyclerview.widget.ViewTypeStorage.ViewTypeLookup
            public int globalToLocal(int i) {
                int indexOfKey = this.b.indexOfKey(i);
                if (indexOfKey >= 0) {
                    return this.b.valueAt(indexOfKey);
                }
                throw new IllegalStateException("requested global type " + i + " does not belong to the adapter:" + this.c.c);
            }

            @Override // androidx.recyclerview.widget.ViewTypeStorage.ViewTypeLookup
            public int localToGlobal(int i) {
                int indexOfKey = this.f1630a.indexOfKey(i);
                if (indexOfKey > -1) {
                    return this.f1630a.valueAt(indexOfKey);
                }
                int a2 = IsolatedViewTypeStorage.this.a(this.c);
                this.f1630a.put(i, a2);
                this.b.put(a2, i);
                return a2;
            }
        }

        public int a(i iVar) {
            int i = this.b;
            this.b = i + 1;
            this.f1629a.put(i, iVar);
            return i;
        }

        public void b(@NonNull i iVar) {
            for (int size = this.f1629a.size() - 1; size >= 0; size--) {
                if (this.f1629a.valueAt(size) == iVar) {
                    this.f1629a.removeAt(size);
                }
            }
        }

        @Override // androidx.recyclerview.widget.ViewTypeStorage
        @NonNull
        public ViewTypeLookup createViewTypeWrapper(@NonNull i iVar) {
            return new a(iVar);
        }

        @Override // androidx.recyclerview.widget.ViewTypeStorage
        @NonNull
        public i getWrapperForGlobalType(int i) {
            i iVar = this.f1629a.get(i);
            if (iVar != null) {
                return iVar;
            }
            throw new IllegalArgumentException("Cannot find the wrapper for global view type " + i);
        }
    }

    /* loaded from: classes.dex */
    public static class SharedIdRangeViewTypeStorage implements ViewTypeStorage {

        /* renamed from: a  reason: collision with root package name */
        public SparseArray<List<i>> f1631a = new SparseArray<>();

        /* loaded from: classes.dex */
        public class a implements ViewTypeLookup {

            /* renamed from: a  reason: collision with root package name */
            public final i f1632a;

            public a(i iVar) {
                this.f1632a = iVar;
            }

            @Override // androidx.recyclerview.widget.ViewTypeStorage.ViewTypeLookup
            public void dispose() {
                SharedIdRangeViewTypeStorage.this.a(this.f1632a);
            }

            @Override // androidx.recyclerview.widget.ViewTypeStorage.ViewTypeLookup
            public int globalToLocal(int i) {
                return i;
            }

            @Override // androidx.recyclerview.widget.ViewTypeStorage.ViewTypeLookup
            public int localToGlobal(int i) {
                List<i> list = SharedIdRangeViewTypeStorage.this.f1631a.get(i);
                if (list == null) {
                    list = new ArrayList<>();
                    SharedIdRangeViewTypeStorage.this.f1631a.put(i, list);
                }
                if (!list.contains(this.f1632a)) {
                    list.add(this.f1632a);
                }
                return i;
            }
        }

        public void a(@NonNull i iVar) {
            for (int size = this.f1631a.size() - 1; size >= 0; size--) {
                List<i> valueAt = this.f1631a.valueAt(size);
                if (valueAt.remove(iVar) && valueAt.isEmpty()) {
                    this.f1631a.removeAt(size);
                }
            }
        }

        @Override // androidx.recyclerview.widget.ViewTypeStorage
        @NonNull
        public ViewTypeLookup createViewTypeWrapper(@NonNull i iVar) {
            return new a(iVar);
        }

        @Override // androidx.recyclerview.widget.ViewTypeStorage
        @NonNull
        public i getWrapperForGlobalType(int i) {
            List<i> list = this.f1631a.get(i);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
            throw new IllegalArgumentException("Cannot find the wrapper for global view type " + i);
        }
    }

    /* loaded from: classes.dex */
    public interface ViewTypeLookup {
        void dispose();

        int globalToLocal(int i);

        int localToGlobal(int i);
    }

    @NonNull
    ViewTypeLookup createViewTypeWrapper(@NonNull i iVar);

    @NonNull
    i getWrapperForGlobalType(int i);
}
