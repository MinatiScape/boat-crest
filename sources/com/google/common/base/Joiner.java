package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes10.dex */
public class Joiner {

    /* renamed from: a  reason: collision with root package name */
    public final String f10519a;

    /* loaded from: classes10.dex */
    public static final class MapJoiner {

        /* renamed from: a  reason: collision with root package name */
        public final Joiner f10520a;
        public final String b;

        public /* synthetic */ MapJoiner(Joiner joiner, String str, a aVar) {
            this(joiner, str);
        }

        @CanIgnoreReturnValue
        public <A extends Appendable> A appendTo(A a2, Map<?, ?> map) throws IOException {
            return (A) appendTo((MapJoiner) a2, (Iterable<? extends Map.Entry<?, ?>>) map.entrySet());
        }

        public String join(Map<?, ?> map) {
            return join(map.entrySet());
        }

        public MapJoiner useForNull(String str) {
            return new MapJoiner(this.f10520a.useForNull(str), this.b);
        }

        public MapJoiner(Joiner joiner, String str) {
            this.f10520a = joiner;
            this.b = (String) Preconditions.checkNotNull(str);
        }

        @CanIgnoreReturnValue
        public StringBuilder appendTo(StringBuilder sb, Map<?, ?> map) {
            return appendTo(sb, (Iterable<? extends Map.Entry<?, ?>>) map.entrySet());
        }

        @Beta
        public String join(Iterable<? extends Map.Entry<?, ?>> iterable) {
            return join(iterable.iterator());
        }

        @CanIgnoreReturnValue
        @Beta
        public <A extends Appendable> A appendTo(A a2, Iterable<? extends Map.Entry<?, ?>> iterable) throws IOException {
            return (A) appendTo((MapJoiner) a2, iterable.iterator());
        }

        @Beta
        public String join(Iterator<? extends Map.Entry<?, ?>> it) {
            return appendTo(new StringBuilder(), it).toString();
        }

        @CanIgnoreReturnValue
        @Beta
        public <A extends Appendable> A appendTo(A a2, Iterator<? extends Map.Entry<?, ?>> it) throws IOException {
            Preconditions.checkNotNull(a2);
            if (it.hasNext()) {
                Map.Entry<?, ?> next = it.next();
                a2.append(this.f10520a.c(next.getKey()));
                a2.append(this.b);
                a2.append(this.f10520a.c(next.getValue()));
                while (it.hasNext()) {
                    a2.append(this.f10520a.f10519a);
                    Map.Entry<?, ?> next2 = it.next();
                    a2.append(this.f10520a.c(next2.getKey()));
                    a2.append(this.b);
                    a2.append(this.f10520a.c(next2.getValue()));
                }
            }
            return a2;
        }

        @CanIgnoreReturnValue
        @Beta
        public StringBuilder appendTo(StringBuilder sb, Iterable<? extends Map.Entry<?, ?>> iterable) {
            return appendTo(sb, iterable.iterator());
        }

        @CanIgnoreReturnValue
        @Beta
        public StringBuilder appendTo(StringBuilder sb, Iterator<? extends Map.Entry<?, ?>> it) {
            try {
                appendTo((MapJoiner) sb, it);
                return sb;
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }
    }

    /* loaded from: classes10.dex */
    public class a extends Joiner {
        public final /* synthetic */ String b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Joiner joiner, String str) {
            super(joiner, null);
            this.b = str;
        }

        @Override // com.google.common.base.Joiner
        public CharSequence c(@NullableDecl Object obj) {
            return obj == null ? this.b : Joiner.this.c(obj);
        }

        @Override // com.google.common.base.Joiner
        public Joiner skipNulls() {
            throw new UnsupportedOperationException("already specified useForNull");
        }

        @Override // com.google.common.base.Joiner
        public Joiner useForNull(String str) {
            throw new UnsupportedOperationException("already specified useForNull");
        }
    }

    /* loaded from: classes10.dex */
    public class b extends Joiner {
        public b(Joiner joiner) {
            super(joiner, null);
        }

        @Override // com.google.common.base.Joiner
        public <A extends Appendable> A appendTo(A a2, Iterator<?> it) throws IOException {
            Preconditions.checkNotNull(a2, "appendable");
            Preconditions.checkNotNull(it, "parts");
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                if (next != null) {
                    a2.append(Joiner.this.c(next));
                    break;
                }
            }
            while (it.hasNext()) {
                Object next2 = it.next();
                if (next2 != null) {
                    a2.append(Joiner.this.f10519a);
                    a2.append(Joiner.this.c(next2));
                }
            }
            return a2;
        }

        @Override // com.google.common.base.Joiner
        public Joiner useForNull(String str) {
            throw new UnsupportedOperationException("already specified skipNulls");
        }

        @Override // com.google.common.base.Joiner
        public MapJoiner withKeyValueSeparator(String str) {
            throw new UnsupportedOperationException("can't use .skipNulls() with maps");
        }
    }

    /* loaded from: classes10.dex */
    public class c extends AbstractList<Object> {
        public final /* synthetic */ Object[] h;
        public final /* synthetic */ Object i;
        public final /* synthetic */ Object j;

        public c(Object[] objArr, Object obj, Object obj2) {
            this.h = objArr;
            this.i = obj;
            this.j = obj2;
        }

        @Override // java.util.AbstractList, java.util.List
        public Object get(int i) {
            if (i != 0) {
                if (i != 1) {
                    return this.h[i - 2];
                }
                return this.j;
            }
            return this.i;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.h.length + 2;
        }
    }

    public /* synthetic */ Joiner(Joiner joiner, a aVar) {
        this(joiner);
    }

    public static Iterable<Object> b(Object obj, Object obj2, Object[] objArr) {
        Preconditions.checkNotNull(objArr);
        return new c(objArr, obj, obj2);
    }

    public static Joiner on(String str) {
        return new Joiner(str);
    }

    @CanIgnoreReturnValue
    public <A extends Appendable> A appendTo(A a2, Iterable<?> iterable) throws IOException {
        return (A) appendTo((Joiner) a2, iterable.iterator());
    }

    public CharSequence c(Object obj) {
        Preconditions.checkNotNull(obj);
        return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
    }

    public final String join(Iterable<?> iterable) {
        return join(iterable.iterator());
    }

    public Joiner skipNulls() {
        return new b(this);
    }

    public Joiner useForNull(String str) {
        Preconditions.checkNotNull(str);
        return new a(this, str);
    }

    public MapJoiner withKeyValueSeparator(char c2) {
        return withKeyValueSeparator(String.valueOf(c2));
    }

    public Joiner(String str) {
        this.f10519a = (String) Preconditions.checkNotNull(str);
    }

    public static Joiner on(char c2) {
        return new Joiner(String.valueOf(c2));
    }

    @CanIgnoreReturnValue
    public <A extends Appendable> A appendTo(A a2, Iterator<?> it) throws IOException {
        Preconditions.checkNotNull(a2);
        if (it.hasNext()) {
            a2.append(c(it.next()));
            while (it.hasNext()) {
                a2.append(this.f10519a);
                a2.append(c(it.next()));
            }
        }
        return a2;
    }

    public final String join(Iterator<?> it) {
        return appendTo(new StringBuilder(), it).toString();
    }

    public MapJoiner withKeyValueSeparator(String str) {
        return new MapJoiner(this, str, null);
    }

    public final String join(Object[] objArr) {
        return join(Arrays.asList(objArr));
    }

    public Joiner(Joiner joiner) {
        this.f10519a = joiner.f10519a;
    }

    public final String join(@NullableDecl Object obj, @NullableDecl Object obj2, Object... objArr) {
        return join(b(obj, obj2, objArr));
    }

    @CanIgnoreReturnValue
    public final <A extends Appendable> A appendTo(A a2, Object[] objArr) throws IOException {
        return (A) appendTo((Joiner) a2, (Iterable<?>) Arrays.asList(objArr));
    }

    @CanIgnoreReturnValue
    public final <A extends Appendable> A appendTo(A a2, @NullableDecl Object obj, @NullableDecl Object obj2, Object... objArr) throws IOException {
        return (A) appendTo((Joiner) a2, b(obj, obj2, objArr));
    }

    @CanIgnoreReturnValue
    public final StringBuilder appendTo(StringBuilder sb, Iterable<?> iterable) {
        return appendTo(sb, iterable.iterator());
    }

    @CanIgnoreReturnValue
    public final StringBuilder appendTo(StringBuilder sb, Iterator<?> it) {
        try {
            appendTo((Joiner) sb, it);
            return sb;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    @CanIgnoreReturnValue
    public final StringBuilder appendTo(StringBuilder sb, Object[] objArr) {
        return appendTo(sb, (Iterable<?>) Arrays.asList(objArr));
    }

    @CanIgnoreReturnValue
    public final StringBuilder appendTo(StringBuilder sb, @NullableDecl Object obj, @NullableDecl Object obj2, Object... objArr) {
        return appendTo(sb, b(obj, obj2, objArr));
    }
}
