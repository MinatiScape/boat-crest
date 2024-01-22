package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.Internal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes10.dex */
public abstract class r {

    /* renamed from: a  reason: collision with root package name */
    public static final r f10991a = new b();
    public static final r b = new c();

    /* loaded from: classes10.dex */
    public static final class b extends r {
        public static final Class<?> c = Collections.unmodifiableList(Collections.emptyList()).getClass();

        public b() {
            super();
        }

        public static <E> List<E> f(Object obj, long j) {
            return (List) u0.F(obj, j);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static <L> List<L> g(Object obj, long j, int i) {
            LazyStringArrayList lazyStringArrayList;
            List<L> arrayList;
            List<L> f = f(obj, j);
            if (f.isEmpty()) {
                if (f instanceof LazyStringList) {
                    arrayList = new LazyStringArrayList(i);
                } else if ((f instanceof h0) && (f instanceof Internal.ProtobufList)) {
                    arrayList = ((Internal.ProtobufList) f).mutableCopyWithCapacity(i);
                } else {
                    arrayList = new ArrayList<>(i);
                }
                u0.V(obj, j, arrayList);
                return arrayList;
            }
            if (c.isAssignableFrom(f.getClass())) {
                ArrayList arrayList2 = new ArrayList(f.size() + i);
                arrayList2.addAll(f);
                u0.V(obj, j, arrayList2);
                lazyStringArrayList = arrayList2;
            } else if (f instanceof UnmodifiableLazyStringList) {
                LazyStringArrayList lazyStringArrayList2 = new LazyStringArrayList(f.size() + i);
                lazyStringArrayList2.addAll((UnmodifiableLazyStringList) f);
                u0.V(obj, j, lazyStringArrayList2);
                lazyStringArrayList = lazyStringArrayList2;
            } else if ((f instanceof h0) && (f instanceof Internal.ProtobufList)) {
                Internal.ProtobufList protobufList = (Internal.ProtobufList) f;
                if (protobufList.isModifiable()) {
                    return f;
                }
                Internal.ProtobufList mutableCopyWithCapacity = protobufList.mutableCopyWithCapacity(f.size() + i);
                u0.V(obj, j, mutableCopyWithCapacity);
                return mutableCopyWithCapacity;
            } else {
                return f;
            }
            return lazyStringArrayList;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.r
        public void c(Object obj, long j) {
            Object unmodifiableList;
            List list = (List) u0.F(obj, j);
            if (list instanceof LazyStringList) {
                unmodifiableList = ((LazyStringList) list).getUnmodifiableView();
            } else if (c.isAssignableFrom(list.getClass())) {
                return;
            } else {
                if ((list instanceof h0) && (list instanceof Internal.ProtobufList)) {
                    Internal.ProtobufList protobufList = (Internal.ProtobufList) list;
                    if (protobufList.isModifiable()) {
                        protobufList.makeImmutable();
                        return;
                    }
                    return;
                }
                unmodifiableList = Collections.unmodifiableList(list);
            }
            u0.V(obj, j, unmodifiableList);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.r
        public <E> void d(Object obj, Object obj2, long j) {
            List f = f(obj2, j);
            List g = g(obj, j, f.size());
            int size = g.size();
            int size2 = f.size();
            if (size > 0 && size2 > 0) {
                g.addAll(f);
            }
            if (size > 0) {
                f = g;
            }
            u0.V(obj, j, f);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.r
        public <L> List<L> e(Object obj, long j) {
            return g(obj, j, 10);
        }
    }

    /* loaded from: classes10.dex */
    public static final class c extends r {
        public c() {
            super();
        }

        public static <E> Internal.ProtobufList<E> f(Object obj, long j) {
            return (Internal.ProtobufList) u0.F(obj, j);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.r
        public void c(Object obj, long j) {
            f(obj, j).makeImmutable();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v2, types: [java.util.List] */
        @Override // com.google.crypto.tink.shaded.protobuf.r
        public <E> void d(Object obj, Object obj2, long j) {
            Internal.ProtobufList<E> f = f(obj, j);
            Internal.ProtobufList<E> f2 = f(obj2, j);
            int size = f.size();
            int size2 = f2.size();
            Internal.ProtobufList<E> protobufList = f;
            protobufList = f;
            if (size > 0 && size2 > 0) {
                boolean isModifiable = f.isModifiable();
                Internal.ProtobufList<E> protobufList2 = f;
                if (!isModifiable) {
                    protobufList2 = f.mutableCopyWithCapacity(size2 + size);
                }
                protobufList2.addAll(f2);
                protobufList = protobufList2;
            }
            if (size > 0) {
                f2 = protobufList;
            }
            u0.V(obj, j, f2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.r
        public <L> List<L> e(Object obj, long j) {
            Internal.ProtobufList f = f(obj, j);
            if (f.isModifiable()) {
                return f;
            }
            int size = f.size();
            Internal.ProtobufList mutableCopyWithCapacity = f.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
            u0.V(obj, j, mutableCopyWithCapacity);
            return mutableCopyWithCapacity;
        }
    }

    public static r a() {
        return f10991a;
    }

    public static r b() {
        return b;
    }

    public abstract void c(Object obj, long j);

    public abstract <L> void d(Object obj, Object obj2, long j);

    public abstract <L> List<L> e(Object obj, long j);

    public r() {
    }
}
