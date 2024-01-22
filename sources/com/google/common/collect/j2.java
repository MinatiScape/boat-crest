package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
@GwtCompatible
/* loaded from: classes10.dex */
public final class j2 {

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes10.dex */
    public static abstract class b {
        public static final b NEXT_LOWER = new a("NEXT_LOWER", 0);
        public static final b NEXT_HIGHER = new C0485b("NEXT_HIGHER", 1);
        public static final b INVERTED_INSERTION_INDEX = new c("INVERTED_INSERTION_INDEX", 2);
        private static final /* synthetic */ b[] $VALUES = $values();

        /* loaded from: classes10.dex */
        public enum a extends b {
            public a(String str, int i) {
                super(str, i);
            }

            @Override // com.google.common.collect.j2.b
            public int resultIndex(int i) {
                return i - 1;
            }
        }

        /* renamed from: com.google.common.collect.j2$b$b  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public enum C0485b extends b {
            public C0485b(String str, int i) {
                super(str, i);
            }

            @Override // com.google.common.collect.j2.b
            public int resultIndex(int i) {
                return i;
            }
        }

        /* loaded from: classes10.dex */
        public enum c extends b {
            public c(String str, int i) {
                super(str, i);
            }

            @Override // com.google.common.collect.j2.b
            public int resultIndex(int i) {
                return ~i;
            }
        }

        private static /* synthetic */ b[] $values() {
            return new b[]{NEXT_LOWER, NEXT_HIGHER, INVERTED_INSERTION_INDEX};
        }

        private b(String str, int i) {
        }

        public static b valueOf(String str) {
            return (b) Enum.valueOf(b.class, str);
        }

        public static b[] values() {
            return (b[]) $VALUES.clone();
        }

        public abstract int resultIndex(int i);
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes10.dex */
    public static abstract class c {
        public static final c ANY_PRESENT = new a("ANY_PRESENT", 0);
        public static final c LAST_PRESENT = new b("LAST_PRESENT", 1);
        public static final c FIRST_PRESENT = new C0486c("FIRST_PRESENT", 2);
        public static final c FIRST_AFTER = new d("FIRST_AFTER", 3);
        public static final c LAST_BEFORE = new e("LAST_BEFORE", 4);
        private static final /* synthetic */ c[] $VALUES = $values();

        /* loaded from: classes10.dex */
        public enum a extends c {
            public a(String str, int i) {
                super(str, i);
            }

            @Override // com.google.common.collect.j2.c
            public <E> int resultIndex(Comparator<? super E> comparator, E e, List<? extends E> list, int i) {
                return i;
            }
        }

        /* loaded from: classes10.dex */
        public enum b extends c {
            public b(String str, int i) {
                super(str, i);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.j2.c
            public <E> int resultIndex(Comparator<? super E> comparator, E e, List<? extends E> list, int i) {
                int size = list.size() - 1;
                while (i < size) {
                    int i2 = ((i + size) + 1) >>> 1;
                    if (comparator.compare((E) list.get(i2), e) > 0) {
                        size = i2 - 1;
                    } else {
                        i = i2;
                    }
                }
                return i;
            }
        }

        /* renamed from: com.google.common.collect.j2$c$c  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public enum C0486c extends c {
            public C0486c(String str, int i) {
                super(str, i);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.j2.c
            public <E> int resultIndex(Comparator<? super E> comparator, E e, List<? extends E> list, int i) {
                int i2 = 0;
                while (i2 < i) {
                    int i3 = (i2 + i) >>> 1;
                    if (comparator.compare((E) list.get(i3), e) < 0) {
                        i2 = i3 + 1;
                    } else {
                        i = i3;
                    }
                }
                return i2;
            }
        }

        /* loaded from: classes10.dex */
        public enum d extends c {
            public d(String str, int i) {
                super(str, i);
            }

            @Override // com.google.common.collect.j2.c
            public <E> int resultIndex(Comparator<? super E> comparator, E e, List<? extends E> list, int i) {
                return c.LAST_PRESENT.resultIndex(comparator, e, list, i) + 1;
            }
        }

        /* loaded from: classes10.dex */
        public enum e extends c {
            public e(String str, int i) {
                super(str, i);
            }

            @Override // com.google.common.collect.j2.c
            public <E> int resultIndex(Comparator<? super E> comparator, E e, List<? extends E> list, int i) {
                return c.FIRST_PRESENT.resultIndex(comparator, e, list, i) - 1;
            }
        }

        private static /* synthetic */ c[] $values() {
            return new c[]{ANY_PRESENT, LAST_PRESENT, FIRST_PRESENT, FIRST_AFTER, LAST_BEFORE};
        }

        private c(String str, int i) {
        }

        public static c valueOf(String str) {
            return (c) Enum.valueOf(c.class, str);
        }

        public static c[] values() {
            return (c[]) $VALUES.clone();
        }

        public abstract <E> int resultIndex(Comparator<? super E> comparator, E e2, List<? extends E> list, int i);
    }

    public static <E, K extends Comparable> int a(List<E> list, Function<? super E, K> function, @NullableDecl K k, c cVar, b bVar) {
        return b(list, function, k, Ordering.natural(), cVar, bVar);
    }

    public static <E, K> int b(List<E> list, Function<? super E, K> function, @NullableDecl K k, Comparator<? super K> comparator, c cVar, b bVar) {
        return c(Lists.transform(list, function), k, comparator, cVar, bVar);
    }

    public static <E> int c(List<? extends E> list, @NullableDecl E e, Comparator<? super E> comparator, c cVar, b bVar) {
        Preconditions.checkNotNull(comparator);
        Preconditions.checkNotNull(list);
        Preconditions.checkNotNull(cVar);
        Preconditions.checkNotNull(bVar);
        if (!(list instanceof RandomAccess)) {
            list = Lists.newArrayList(list);
        }
        int i = 0;
        int size = list.size() - 1;
        while (i <= size) {
            int i2 = (i + size) >>> 1;
            int compare = comparator.compare(e, (E) list.get(i2));
            if (compare < 0) {
                size = i2 - 1;
            } else if (compare <= 0) {
                return i + cVar.resultIndex(comparator, e, list.subList(i, size + 1), i2 - i);
            } else {
                i = i2 + 1;
            }
        }
        return bVar.resultIndex(i);
    }
}
