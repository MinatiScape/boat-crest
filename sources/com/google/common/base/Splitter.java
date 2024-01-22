package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public final class Splitter {

    /* renamed from: a  reason: collision with root package name */
    public final CharMatcher f10523a;
    public final boolean b;
    public final g c;
    public final int d;

    @Beta
    /* loaded from: classes10.dex */
    public static final class MapSplitter {

        /* renamed from: a  reason: collision with root package name */
        public final Splitter f10524a;
        public final Splitter b;

        public /* synthetic */ MapSplitter(Splitter splitter, Splitter splitter2, a aVar) {
            this(splitter, splitter2);
        }

        public Map<String, String> split(CharSequence charSequence) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (String str : this.f10524a.split(charSequence)) {
                Iterator f = this.b.f(str);
                Preconditions.checkArgument(f.hasNext(), "Chunk [%s] is not a valid entry", str);
                String str2 = (String) f.next();
                Preconditions.checkArgument(!linkedHashMap.containsKey(str2), "Duplicate key [%s] found.", str2);
                Preconditions.checkArgument(f.hasNext(), "Chunk [%s] is not a valid entry", str);
                linkedHashMap.put(str2, (String) f.next());
                Preconditions.checkArgument(!f.hasNext(), "Chunk [%s] is not a valid entry", str);
            }
            return Collections.unmodifiableMap(linkedHashMap);
        }

        public MapSplitter(Splitter splitter, Splitter splitter2) {
            this.f10524a = splitter;
            this.b = (Splitter) Preconditions.checkNotNull(splitter2);
        }
    }

    /* loaded from: classes10.dex */
    public class a implements g {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CharMatcher f10525a;

        /* renamed from: com.google.common.base.Splitter$a$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class C0454a extends f {
            public C0454a(Splitter splitter, CharSequence charSequence) {
                super(splitter, charSequence);
            }

            @Override // com.google.common.base.Splitter.f
            public int e(int i) {
                return i + 1;
            }

            @Override // com.google.common.base.Splitter.f
            public int f(int i) {
                return a.this.f10525a.indexIn(this.j, i);
            }
        }

        public a(CharMatcher charMatcher) {
            this.f10525a = charMatcher;
        }

        @Override // com.google.common.base.Splitter.g
        /* renamed from: b */
        public f a(Splitter splitter, CharSequence charSequence) {
            return new C0454a(splitter, charSequence);
        }
    }

    /* loaded from: classes10.dex */
    public class b implements g {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f10526a;

        /* loaded from: classes10.dex */
        public class a extends f {
            public a(Splitter splitter, CharSequence charSequence) {
                super(splitter, charSequence);
            }

            @Override // com.google.common.base.Splitter.f
            public int e(int i) {
                return i + b.this.f10526a.length();
            }

            /* JADX WARN: Code restructure failed: missing block: B:8:0x0026, code lost:
                r6 = r6 + 1;
             */
            @Override // com.google.common.base.Splitter.f
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public int f(int r6) {
                /*
                    r5 = this;
                    com.google.common.base.Splitter$b r0 = com.google.common.base.Splitter.b.this
                    java.lang.String r0 = r0.f10526a
                    int r0 = r0.length()
                    java.lang.CharSequence r1 = r5.j
                    int r1 = r1.length()
                    int r1 = r1 - r0
                Lf:
                    if (r6 > r1) goto L2d
                    r2 = 0
                L12:
                    if (r2 >= r0) goto L2c
                    java.lang.CharSequence r3 = r5.j
                    int r4 = r2 + r6
                    char r3 = r3.charAt(r4)
                    com.google.common.base.Splitter$b r4 = com.google.common.base.Splitter.b.this
                    java.lang.String r4 = r4.f10526a
                    char r4 = r4.charAt(r2)
                    if (r3 == r4) goto L29
                    int r6 = r6 + 1
                    goto Lf
                L29:
                    int r2 = r2 + 1
                    goto L12
                L2c:
                    return r6
                L2d:
                    r6 = -1
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.base.Splitter.b.a.f(int):int");
            }
        }

        public b(String str) {
            this.f10526a = str;
        }

        @Override // com.google.common.base.Splitter.g
        /* renamed from: b */
        public f a(Splitter splitter, CharSequence charSequence) {
            return new a(splitter, charSequence);
        }
    }

    /* loaded from: classes10.dex */
    public class c implements g {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ com.google.common.base.d f10527a;

        /* loaded from: classes10.dex */
        public class a extends f {
            public final /* synthetic */ com.google.common.base.c o;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(c cVar, Splitter splitter, CharSequence charSequence, com.google.common.base.c cVar2) {
                super(splitter, charSequence);
                this.o = cVar2;
            }

            @Override // com.google.common.base.Splitter.f
            public int e(int i) {
                return this.o.a();
            }

            @Override // com.google.common.base.Splitter.f
            public int f(int i) {
                if (this.o.c(i)) {
                    return this.o.e();
                }
                return -1;
            }
        }

        public c(com.google.common.base.d dVar) {
            this.f10527a = dVar;
        }

        @Override // com.google.common.base.Splitter.g
        /* renamed from: b */
        public f a(Splitter splitter, CharSequence charSequence) {
            return new a(this, splitter, charSequence, this.f10527a.matcher(charSequence));
        }
    }

    /* loaded from: classes10.dex */
    public class d implements g {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ int f10528a;

        /* loaded from: classes10.dex */
        public class a extends f {
            public a(Splitter splitter, CharSequence charSequence) {
                super(splitter, charSequence);
            }

            @Override // com.google.common.base.Splitter.f
            public int e(int i) {
                return i;
            }

            @Override // com.google.common.base.Splitter.f
            public int f(int i) {
                int i2 = i + d.this.f10528a;
                if (i2 < this.j.length()) {
                    return i2;
                }
                return -1;
            }
        }

        public d(int i) {
            this.f10528a = i;
        }

        @Override // com.google.common.base.Splitter.g
        /* renamed from: b */
        public f a(Splitter splitter, CharSequence charSequence) {
            return new a(splitter, charSequence);
        }
    }

    /* loaded from: classes10.dex */
    public class e implements Iterable<String> {
        public final /* synthetic */ CharSequence h;

        public e(CharSequence charSequence) {
            this.h = charSequence;
        }

        @Override // java.lang.Iterable
        public Iterator<String> iterator() {
            return Splitter.this.f(this.h);
        }

        public String toString() {
            Joiner on = Joiner.on(", ");
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            StringBuilder appendTo = on.appendTo(sb, (Iterable<?>) this);
            appendTo.append(']');
            return appendTo.toString();
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class f extends com.google.common.base.b<String> {
        public final CharSequence j;
        public final CharMatcher k;
        public final boolean l;
        public int m = 0;
        public int n;

        public f(Splitter splitter, CharSequence charSequence) {
            this.k = splitter.f10523a;
            this.l = splitter.b;
            this.n = splitter.d;
            this.j = charSequence;
        }

        @Override // com.google.common.base.b
        /* renamed from: d */
        public String a() {
            int f;
            int i = this.m;
            while (true) {
                int i2 = this.m;
                if (i2 != -1) {
                    f = f(i2);
                    if (f == -1) {
                        f = this.j.length();
                        this.m = -1;
                    } else {
                        this.m = e(f);
                    }
                    int i3 = this.m;
                    if (i3 == i) {
                        int i4 = i3 + 1;
                        this.m = i4;
                        if (i4 > this.j.length()) {
                            this.m = -1;
                        }
                    } else {
                        while (i < f && this.k.matches(this.j.charAt(i))) {
                            i++;
                        }
                        while (f > i && this.k.matches(this.j.charAt(f - 1))) {
                            f--;
                        }
                        if (!this.l || i != f) {
                            break;
                        }
                        i = this.m;
                    }
                } else {
                    return b();
                }
            }
            int i5 = this.n;
            if (i5 == 1) {
                f = this.j.length();
                this.m = -1;
                while (f > i && this.k.matches(this.j.charAt(f - 1))) {
                    f--;
                }
            } else {
                this.n = i5 - 1;
            }
            return this.j.subSequence(i, f).toString();
        }

        public abstract int e(int i);

        public abstract int f(int i);
    }

    /* loaded from: classes10.dex */
    public interface g {
        Iterator<String> a(Splitter splitter, CharSequence charSequence);
    }

    public Splitter(g gVar) {
        this(gVar, false, CharMatcher.none(), Integer.MAX_VALUE);
    }

    public static Splitter e(com.google.common.base.d dVar) {
        Preconditions.checkArgument(!dVar.matcher("").d(), "The pattern may not match the empty string: %s", dVar);
        return new Splitter(new c(dVar));
    }

    public static Splitter fixedLength(int i) {
        Preconditions.checkArgument(i > 0, "The length may not be less than 1");
        return new Splitter(new d(i));
    }

    public static Splitter on(char c2) {
        return on(CharMatcher.is(c2));
    }

    @GwtIncompatible
    public static Splitter onPattern(String str) {
        return e(l.a(str));
    }

    public final Iterator<String> f(CharSequence charSequence) {
        return this.c.a(this, charSequence);
    }

    public Splitter limit(int i) {
        Preconditions.checkArgument(i > 0, "must be greater than zero: %s", i);
        return new Splitter(this.c, this.b, this.f10523a, i);
    }

    public Splitter omitEmptyStrings() {
        return new Splitter(this.c, true, this.f10523a, this.d);
    }

    public Iterable<String> split(CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        return new e(charSequence);
    }

    public List<String> splitToList(CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        Iterator<String> f2 = f(charSequence);
        ArrayList arrayList = new ArrayList();
        while (f2.hasNext()) {
            arrayList.add(f2.next());
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Splitter trimResults() {
        return trimResults(CharMatcher.whitespace());
    }

    @Beta
    public MapSplitter withKeyValueSeparator(String str) {
        return withKeyValueSeparator(on(str));
    }

    public Splitter(g gVar, boolean z, CharMatcher charMatcher, int i) {
        this.c = gVar;
        this.b = z;
        this.f10523a = charMatcher;
        this.d = i;
    }

    public static Splitter on(CharMatcher charMatcher) {
        Preconditions.checkNotNull(charMatcher);
        return new Splitter(new a(charMatcher));
    }

    public Splitter trimResults(CharMatcher charMatcher) {
        Preconditions.checkNotNull(charMatcher);
        return new Splitter(this.c, this.b, charMatcher, this.d);
    }

    @Beta
    public MapSplitter withKeyValueSeparator(char c2) {
        return withKeyValueSeparator(on(c2));
    }

    @Beta
    public MapSplitter withKeyValueSeparator(Splitter splitter) {
        return new MapSplitter(this, splitter, null);
    }

    public static Splitter on(String str) {
        Preconditions.checkArgument(str.length() != 0, "The separator may not be the empty string.");
        if (str.length() == 1) {
            return on(str.charAt(0));
        }
        return new Splitter(new b(str));
    }

    @GwtIncompatible
    public static Splitter on(Pattern pattern) {
        return e(new i(pattern));
    }
}
