package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Equivalence;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.l1;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public final class MapMaker {

    /* renamed from: a  reason: collision with root package name */
    public boolean f10564a;
    public int b = -1;
    public int c = -1;
    @NullableDecl
    public l1.p d;
    @NullableDecl
    public l1.p e;
    @NullableDecl
    public Equivalence<Object> f;

    /* loaded from: classes10.dex */
    public enum a {
        VALUE
    }

    public int a() {
        int i = this.c;
        if (i == -1) {
            return 4;
        }
        return i;
    }

    public int b() {
        int i = this.b;
        if (i == -1) {
            return 16;
        }
        return i;
    }

    public Equivalence<Object> c() {
        return (Equivalence) MoreObjects.firstNonNull(this.f, d().defaultEquivalence());
    }

    @CanIgnoreReturnValue
    public MapMaker concurrencyLevel(int i) {
        int i2 = this.c;
        Preconditions.checkState(i2 == -1, "concurrency level was already set to %s", i2);
        Preconditions.checkArgument(i > 0);
        this.c = i;
        return this;
    }

    public l1.p d() {
        return (l1.p) MoreObjects.firstNonNull(this.d, l1.p.STRONG);
    }

    public l1.p e() {
        return (l1.p) MoreObjects.firstNonNull(this.e, l1.p.STRONG);
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    public MapMaker f(Equivalence<Object> equivalence) {
        Equivalence<Object> equivalence2 = this.f;
        Preconditions.checkState(equivalence2 == null, "key equivalence was already set to %s", equivalence2);
        this.f = (Equivalence) Preconditions.checkNotNull(equivalence);
        this.f10564a = true;
        return this;
    }

    public MapMaker g(l1.p pVar) {
        l1.p pVar2 = this.d;
        Preconditions.checkState(pVar2 == null, "Key strength was already set to %s", pVar2);
        this.d = (l1.p) Preconditions.checkNotNull(pVar);
        if (pVar != l1.p.STRONG) {
            this.f10564a = true;
        }
        return this;
    }

    public MapMaker h(l1.p pVar) {
        l1.p pVar2 = this.e;
        Preconditions.checkState(pVar2 == null, "Value strength was already set to %s", pVar2);
        this.e = (l1.p) Preconditions.checkNotNull(pVar);
        if (pVar != l1.p.STRONG) {
            this.f10564a = true;
        }
        return this;
    }

    @CanIgnoreReturnValue
    public MapMaker initialCapacity(int i) {
        int i2 = this.b;
        Preconditions.checkState(i2 == -1, "initial capacity was already set to %s", i2);
        Preconditions.checkArgument(i >= 0);
        this.b = i;
        return this;
    }

    public <K, V> ConcurrentMap<K, V> makeMap() {
        if (!this.f10564a) {
            return new ConcurrentHashMap(b(), 0.75f, a());
        }
        return l1.create(this);
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(this);
        int i = this.b;
        if (i != -1) {
            stringHelper.add("initialCapacity", i);
        }
        int i2 = this.c;
        if (i2 != -1) {
            stringHelper.add("concurrencyLevel", i2);
        }
        l1.p pVar = this.d;
        if (pVar != null) {
            stringHelper.add("keyStrength", Ascii.toLowerCase(pVar.toString()));
        }
        l1.p pVar2 = this.e;
        if (pVar2 != null) {
            stringHelper.add("valueStrength", Ascii.toLowerCase(pVar2.toString()));
        }
        if (this.f != null) {
            stringHelper.addValue("keyEquivalence");
        }
        return stringHelper.toString();
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    public MapMaker weakKeys() {
        return g(l1.p.WEAK);
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    public MapMaker weakValues() {
        return h(l1.p.WEAK);
    }
}
