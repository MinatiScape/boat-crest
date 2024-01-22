package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.DoNotMock;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@DoNotMock("Use Optional.of(value) or Optional.absent()")
@GwtCompatible(serializable = true)
/* loaded from: classes10.dex */
public abstract class Optional<T> implements Serializable {
    private static final long serialVersionUID = 0;

    /* loaded from: classes10.dex */
    public class a implements Iterable<T> {
        public final /* synthetic */ Iterable h;

        /* renamed from: com.google.common.base.Optional$a$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class C0453a extends b<T> {
            public final Iterator<? extends Optional<? extends T>> j;

            public C0453a() {
                this.j = (Iterator) Preconditions.checkNotNull(a.this.h.iterator());
            }

            @Override // com.google.common.base.b
            public T a() {
                while (this.j.hasNext()) {
                    Optional<? extends T> next = this.j.next();
                    if (next.isPresent()) {
                        return next.get();
                    }
                }
                return b();
            }
        }

        public a(Iterable iterable) {
            this.h = iterable;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return new C0453a();
        }
    }

    public static <T> Optional<T> absent() {
        return com.google.common.base.a.withType();
    }

    public static <T> Optional<T> fromNullable(@NullableDecl T t) {
        return t == null ? absent() : new m(t);
    }

    public static <T> Optional<T> of(T t) {
        return new m(Preconditions.checkNotNull(t));
    }

    @Beta
    public static <T> Iterable<T> presentInstances(Iterable<? extends Optional<? extends T>> iterable) {
        Preconditions.checkNotNull(iterable);
        return new a(iterable);
    }

    public abstract Set<T> asSet();

    public abstract boolean equals(@NullableDecl Object obj);

    public abstract T get();

    public abstract int hashCode();

    public abstract boolean isPresent();

    public abstract Optional<T> or(Optional<? extends T> optional);

    @Beta
    public abstract T or(Supplier<? extends T> supplier);

    public abstract T or(T t);

    @NullableDecl
    public abstract T orNull();

    public abstract String toString();

    public abstract <V> Optional<V> transform(Function<? super T, V> function);
}
