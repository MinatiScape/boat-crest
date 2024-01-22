package androidx.recyclerview.widget;

import androidx.annotation.NonNull;
import androidx.collection.LongSparseArray;
/* loaded from: classes.dex */
public interface StableIdStorage {

    /* loaded from: classes.dex */
    public static class IsolatedStableIdStorage implements StableIdStorage {

        /* renamed from: a  reason: collision with root package name */
        public long f1618a = 0;

        /* loaded from: classes.dex */
        public class a implements StableIdLookup {

            /* renamed from: a  reason: collision with root package name */
            public final LongSparseArray<Long> f1619a = new LongSparseArray<>();

            public a() {
            }

            @Override // androidx.recyclerview.widget.StableIdStorage.StableIdLookup
            public long localToGlobal(long j) {
                Long l = this.f1619a.get(j);
                if (l == null) {
                    l = Long.valueOf(IsolatedStableIdStorage.this.a());
                    this.f1619a.put(j, l);
                }
                return l.longValue();
            }
        }

        public long a() {
            long j = this.f1618a;
            this.f1618a = 1 + j;
            return j;
        }

        @Override // androidx.recyclerview.widget.StableIdStorage
        @NonNull
        public StableIdLookup createStableIdLookup() {
            return new a();
        }
    }

    /* loaded from: classes.dex */
    public static class NoStableIdStorage implements StableIdStorage {

        /* renamed from: a  reason: collision with root package name */
        public final StableIdLookup f1620a = new a(this);

        /* loaded from: classes.dex */
        public class a implements StableIdLookup {
            public a(NoStableIdStorage noStableIdStorage) {
            }

            @Override // androidx.recyclerview.widget.StableIdStorage.StableIdLookup
            public long localToGlobal(long j) {
                return -1L;
            }
        }

        @Override // androidx.recyclerview.widget.StableIdStorage
        @NonNull
        public StableIdLookup createStableIdLookup() {
            return this.f1620a;
        }
    }

    /* loaded from: classes.dex */
    public static class SharedPoolStableIdStorage implements StableIdStorage {

        /* renamed from: a  reason: collision with root package name */
        public final StableIdLookup f1621a = new a(this);

        /* loaded from: classes.dex */
        public class a implements StableIdLookup {
            public a(SharedPoolStableIdStorage sharedPoolStableIdStorage) {
            }

            @Override // androidx.recyclerview.widget.StableIdStorage.StableIdLookup
            public long localToGlobal(long j) {
                return j;
            }
        }

        @Override // androidx.recyclerview.widget.StableIdStorage
        @NonNull
        public StableIdLookup createStableIdLookup() {
            return this.f1621a;
        }
    }

    /* loaded from: classes.dex */
    public interface StableIdLookup {
        long localToGlobal(long j);
    }

    @NonNull
    StableIdLookup createStableIdLookup();
}
