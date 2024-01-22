package com.mappls.sdk.plugin.directions.view;

import android.graphics.Canvas;
import android.graphics.PointF;
import androidx.core.util.Pair;
import com.mappls.sdk.plugin.directions.view.b;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes11.dex */
public final class b {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final b f13093a = new b();
    @JvmField
    @NotNull
    public static final Map<Pair<String, String>, com.mappls.sdk.plugin.directions.view.a> b = new a();
    @JvmField
    @NotNull
    public static final Set<String> c = new d();
    @JvmField
    @NotNull
    public static final Set<String> d = new c();
    @JvmField
    @NotNull
    public static final Set<String> e = new C0657b();

    /* loaded from: classes11.dex */
    public static final class a extends HashMap<Pair<String, String>, com.mappls.sdk.plugin.directions.view.a> {
        public a() {
            put(new Pair("merge", null), new com.mappls.sdk.plugin.directions.view.a() { // from class: com.mappls.sdk.plugin.directions.view.m
                @Override // com.mappls.sdk.plugin.directions.view.a
                public final void a(Canvas canvas, Integer num, Integer num2, PointF pointF, Float f) {
                    b.a.a(canvas, num, num2, pointF, f);
                }
            });
            put(new Pair("off ramp", null), new com.mappls.sdk.plugin.directions.view.a() { // from class: com.mappls.sdk.plugin.directions.view.o
                @Override // com.mappls.sdk.plugin.directions.view.a
                public final void a(Canvas canvas, Integer num, Integer num2, PointF pointF, Float f) {
                    b.a.b(canvas, num, num2, pointF, f);
                }
            });
            put(new Pair("fork", null), new com.mappls.sdk.plugin.directions.view.a() { // from class: com.mappls.sdk.plugin.directions.view.j
                @Override // com.mappls.sdk.plugin.directions.view.a
                public final void a(Canvas canvas, Integer num, Integer num2, PointF pointF, Float f) {
                    b.a.m(canvas, num, num2, pointF, f);
                }
            });
            put(new Pair("roundabout", null), new com.mappls.sdk.plugin.directions.view.a() { // from class: com.mappls.sdk.plugin.directions.view.i
                @Override // com.mappls.sdk.plugin.directions.view.a
                public final void a(Canvas canvas, Integer num, Integer num2, PointF pointF, Float f) {
                    b.a.n(canvas, num, num2, pointF, f);
                }
            });
            put(new Pair("roundabout turn", null), new com.mappls.sdk.plugin.directions.view.a() { // from class: com.mappls.sdk.plugin.directions.view.n
                @Override // com.mappls.sdk.plugin.directions.view.a
                public final void a(Canvas canvas, Integer num, Integer num2, PointF pointF, Float f) {
                    b.a.o(canvas, num, num2, pointF, f);
                }
            });
            put(new Pair("rotary", null), new com.mappls.sdk.plugin.directions.view.a() { // from class: com.mappls.sdk.plugin.directions.view.s
                @Override // com.mappls.sdk.plugin.directions.view.a
                public final void a(Canvas canvas, Integer num, Integer num2, PointF pointF, Float f) {
                    b.a.p(canvas, num, num2, pointF, f);
                }
            });
            put(new Pair("exit rotary", null), new com.mappls.sdk.plugin.directions.view.a() { // from class: com.mappls.sdk.plugin.directions.view.e
                @Override // com.mappls.sdk.plugin.directions.view.a
                public final void a(Canvas canvas, Integer num, Integer num2, PointF pointF, Float f) {
                    b.a.q(canvas, num, num2, pointF, f);
                }
            });
            put(new Pair("arrive", null), new com.mappls.sdk.plugin.directions.view.a() { // from class: com.mappls.sdk.plugin.directions.view.t
                @Override // com.mappls.sdk.plugin.directions.view.a
                public final void a(Canvas canvas, Integer num, Integer num2, PointF pointF, Float f) {
                    b.a.r(canvas, num, num2, pointF, f);
                }
            });
            put(new Pair("arrive", "right"), new com.mappls.sdk.plugin.directions.view.a() { // from class: com.mappls.sdk.plugin.directions.view.v
                @Override // com.mappls.sdk.plugin.directions.view.a
                public final void a(Canvas canvas, Integer num, Integer num2, PointF pointF, Float f) {
                    b.a.s(canvas, num, num2, pointF, f);
                }
            });
            put(new Pair("arrive", "left"), new com.mappls.sdk.plugin.directions.view.a() { // from class: com.mappls.sdk.plugin.directions.view.f
                @Override // com.mappls.sdk.plugin.directions.view.a
                public final void a(Canvas canvas, Integer num, Integer num2, PointF pointF, Float f) {
                    b.a.t(canvas, num, num2, pointF, f);
                }
            });
            put(new Pair(null, "slight right"), new com.mappls.sdk.plugin.directions.view.a() { // from class: com.mappls.sdk.plugin.directions.view.u
                @Override // com.mappls.sdk.plugin.directions.view.a
                public final void a(Canvas canvas, Integer num, Integer num2, PointF pointF, Float f) {
                    b.a.c(canvas, num, num2, pointF, f);
                }
            });
            put(new Pair(null, "right"), new com.mappls.sdk.plugin.directions.view.a() { // from class: com.mappls.sdk.plugin.directions.view.l
                @Override // com.mappls.sdk.plugin.directions.view.a
                public final void a(Canvas canvas, Integer num, Integer num2, PointF pointF, Float f) {
                    b.a.d(canvas, num, num2, pointF, f);
                }
            });
            put(new Pair(null, "sharp right"), new com.mappls.sdk.plugin.directions.view.a() { // from class: com.mappls.sdk.plugin.directions.view.d
                @Override // com.mappls.sdk.plugin.directions.view.a
                public final void a(Canvas canvas, Integer num, Integer num2, PointF pointF, Float f) {
                    b.a.e(canvas, num, num2, pointF, f);
                }
            });
            put(new Pair(null, "slight left"), new com.mappls.sdk.plugin.directions.view.a() { // from class: com.mappls.sdk.plugin.directions.view.g
                @Override // com.mappls.sdk.plugin.directions.view.a
                public final void a(Canvas canvas, Integer num, Integer num2, PointF pointF, Float f) {
                    b.a.f(canvas, num, num2, pointF, f);
                }
            });
            put(new Pair(null, "left"), new com.mappls.sdk.plugin.directions.view.a() { // from class: com.mappls.sdk.plugin.directions.view.k
                @Override // com.mappls.sdk.plugin.directions.view.a
                public final void a(Canvas canvas, Integer num, Integer num2, PointF pointF, Float f) {
                    b.a.g(canvas, num, num2, pointF, f);
                }
            });
            put(new Pair(null, "sharp left"), new com.mappls.sdk.plugin.directions.view.a() { // from class: com.mappls.sdk.plugin.directions.view.w
                @Override // com.mappls.sdk.plugin.directions.view.a
                public final void a(Canvas canvas, Integer num, Integer num2, PointF pointF, Float f) {
                    b.a.h(canvas, num, num2, pointF, f);
                }
            });
            put(new Pair(null, "uturn"), new com.mappls.sdk.plugin.directions.view.a() { // from class: com.mappls.sdk.plugin.directions.view.p
                @Override // com.mappls.sdk.plugin.directions.view.a
                public final void a(Canvas canvas, Integer num, Integer num2, PointF pointF, Float f) {
                    b.a.i(canvas, num, num2, pointF, f);
                }
            });
            put(new Pair(null, "straight"), new com.mappls.sdk.plugin.directions.view.a() { // from class: com.mappls.sdk.plugin.directions.view.r
                @Override // com.mappls.sdk.plugin.directions.view.a
                public final void a(Canvas canvas, Integer num, Integer num2, PointF pointF, Float f) {
                    b.a.j(canvas, num, num2, pointF, f);
                }
            });
            put(new Pair(null, "straight_21"), new com.mappls.sdk.plugin.directions.view.a() { // from class: com.mappls.sdk.plugin.directions.view.q
                @Override // com.mappls.sdk.plugin.directions.view.a
                public final void a(Canvas canvas, Integer num, Integer num2, PointF pointF, Float f) {
                    b.a.k(canvas, num, num2, pointF, f);
                }
            });
            put(new Pair(null, null), new com.mappls.sdk.plugin.directions.view.a() { // from class: com.mappls.sdk.plugin.directions.view.h
                @Override // com.mappls.sdk.plugin.directions.view.a
                public final void a(Canvas canvas, Integer num, Integer num2, PointF pointF, Float f) {
                    b.a.l(canvas, num, num2, pointF, f);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void a(Canvas canvas, Integer primaryColor, Integer secondaryColor, PointF pointF, Float f) {
            Intrinsics.checkNotNullExpressionValue(primaryColor, "primaryColor");
            int intValue = primaryColor.intValue();
            Intrinsics.checkNotNullExpressionValue(secondaryColor, "secondaryColor");
            com.mappls.sdk.plugin.directions.view.c.b(canvas, intValue, secondaryColor.intValue(), pointF);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void b(Canvas canvas, Integer primaryColor, Integer secondaryColor, PointF pointF, Float f) {
            Intrinsics.checkNotNullExpressionValue(primaryColor, "primaryColor");
            int intValue = primaryColor.intValue();
            Intrinsics.checkNotNullExpressionValue(secondaryColor, "secondaryColor");
            com.mappls.sdk.plugin.directions.view.c.c(canvas, intValue, secondaryColor.intValue(), pointF);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void c(Canvas canvas, Integer primaryColor, Integer num, PointF pointF, Float f) {
            Intrinsics.checkNotNullExpressionValue(primaryColor, "primaryColor");
            com.mappls.sdk.plugin.directions.view.c.f(canvas, primaryColor.intValue(), pointF);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void d(Canvas canvas, Integer primaryColor, Integer num, PointF pointF, Float f) {
            Intrinsics.checkNotNullExpressionValue(primaryColor, "primaryColor");
            com.mappls.sdk.plugin.directions.view.c.d(canvas, primaryColor.intValue(), pointF);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void e(Canvas canvas, Integer primaryColor, Integer num, PointF pointF, Float f) {
            Intrinsics.checkNotNullExpressionValue(primaryColor, "primaryColor");
            com.mappls.sdk.plugin.directions.view.c.e(canvas, primaryColor.intValue(), pointF);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void f(Canvas canvas, Integer primaryColor, Integer num, PointF pointF, Float f) {
            Intrinsics.checkNotNullExpressionValue(primaryColor, "primaryColor");
            com.mappls.sdk.plugin.directions.view.c.f(canvas, primaryColor.intValue(), pointF);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void g(Canvas canvas, Integer primaryColor, Integer num, PointF pointF, Float f) {
            Intrinsics.checkNotNullExpressionValue(primaryColor, "primaryColor");
            com.mappls.sdk.plugin.directions.view.c.d(canvas, primaryColor.intValue(), pointF);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void h(Canvas canvas, Integer primaryColor, Integer num, PointF pointF, Float f) {
            Intrinsics.checkNotNullExpressionValue(primaryColor, "primaryColor");
            com.mappls.sdk.plugin.directions.view.c.e(canvas, primaryColor.intValue(), pointF);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void i(Canvas canvas, Integer primaryColor, Integer num, PointF pointF, Float f) {
            Intrinsics.checkNotNullExpressionValue(primaryColor, "primaryColor");
            com.mappls.sdk.plugin.directions.view.c.c(canvas, primaryColor.intValue(), pointF);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void j(Canvas canvas, Integer primaryColor, Integer num, PointF pointF, Float f) {
            Intrinsics.checkNotNullExpressionValue(primaryColor, "primaryColor");
            com.mappls.sdk.plugin.directions.view.c.g(canvas, primaryColor.intValue(), pointF);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void k(Canvas canvas, Integer primaryColor, Integer num, PointF pointF, Float f) {
            Intrinsics.checkNotNullExpressionValue(primaryColor, "primaryColor");
            com.mappls.sdk.plugin.directions.view.c.h(canvas, primaryColor.intValue(), pointF);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void l(Canvas canvas, Integer primaryColor, Integer num, PointF pointF, Float f) {
            Intrinsics.checkNotNullExpressionValue(primaryColor, "primaryColor");
            com.mappls.sdk.plugin.directions.view.c.g(canvas, primaryColor.intValue(), pointF);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void m(Canvas canvas, Integer primaryColor, Integer secondaryColor, PointF pointF, Float f) {
            Intrinsics.checkNotNullExpressionValue(primaryColor, "primaryColor");
            int intValue = primaryColor.intValue();
            Intrinsics.checkNotNullExpressionValue(secondaryColor, "secondaryColor");
            com.mappls.sdk.plugin.directions.view.c.a(canvas, intValue, secondaryColor.intValue(), pointF);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void n(Canvas canvas, Integer primaryColor, Integer secondaryColor, PointF pointF, Float roundaboutAngle) {
            Intrinsics.checkNotNullExpressionValue(primaryColor, "primaryColor");
            int intValue = primaryColor.intValue();
            Intrinsics.checkNotNullExpressionValue(secondaryColor, "secondaryColor");
            int intValue2 = secondaryColor.intValue();
            Intrinsics.checkNotNullExpressionValue(roundaboutAngle, "roundaboutAngle");
            com.mappls.sdk.plugin.directions.view.c.a(canvas, intValue, intValue2, pointF, roundaboutAngle.floatValue());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void o(Canvas canvas, Integer primaryColor, Integer secondaryColor, PointF pointF, Float roundaboutAngle) {
            Intrinsics.checkNotNullExpressionValue(primaryColor, "primaryColor");
            int intValue = primaryColor.intValue();
            Intrinsics.checkNotNullExpressionValue(secondaryColor, "secondaryColor");
            int intValue2 = secondaryColor.intValue();
            Intrinsics.checkNotNullExpressionValue(roundaboutAngle, "roundaboutAngle");
            com.mappls.sdk.plugin.directions.view.c.a(canvas, intValue, intValue2, pointF, roundaboutAngle.floatValue());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void p(Canvas canvas, Integer primaryColor, Integer num, PointF pointF, Float f) {
            Intrinsics.checkNotNullExpressionValue(primaryColor, "primaryColor");
            com.mappls.sdk.plugin.directions.view.c.i(canvas, primaryColor.intValue(), pointF);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void q(Canvas canvas, Integer primaryColor, Integer secondaryColor, PointF pointF, Float roundaboutAngle) {
            Intrinsics.checkNotNullExpressionValue(primaryColor, "primaryColor");
            int intValue = primaryColor.intValue();
            Intrinsics.checkNotNullExpressionValue(secondaryColor, "secondaryColor");
            int intValue2 = secondaryColor.intValue();
            Intrinsics.checkNotNullExpressionValue(roundaboutAngle, "roundaboutAngle");
            com.mappls.sdk.plugin.directions.view.c.a(canvas, intValue, intValue2, pointF, roundaboutAngle.floatValue());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void r(Canvas canvas, Integer primaryColor, Integer num, PointF pointF, Float f) {
            Intrinsics.checkNotNullExpressionValue(primaryColor, "primaryColor");
            com.mappls.sdk.plugin.directions.view.c.a(canvas, primaryColor.intValue(), pointF);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void s(Canvas canvas, Integer primaryColor, Integer num, PointF pointF, Float f) {
            Intrinsics.checkNotNullExpressionValue(primaryColor, "primaryColor");
            com.mappls.sdk.plugin.directions.view.c.b(canvas, primaryColor.intValue(), pointF);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void t(Canvas canvas, Integer primaryColor, Integer num, PointF pointF, Float f) {
            Intrinsics.checkNotNullExpressionValue(primaryColor, "primaryColor");
            com.mappls.sdk.plugin.directions.view.c.b(canvas, primaryColor.intValue(), pointF);
        }

        public /* bridge */ com.mappls.sdk.plugin.directions.view.a a(Pair<String, String> pair, com.mappls.sdk.plugin.directions.view.a aVar) {
            return (com.mappls.sdk.plugin.directions.view.a) super.getOrDefault(pair, aVar);
        }

        public /* bridge */ Set<Map.Entry<Pair<String, String>, com.mappls.sdk.plugin.directions.view.a>> a() {
            return super.entrySet();
        }

        public /* bridge */ boolean a(Pair<String, String> pair) {
            return super.containsKey(pair);
        }

        public /* bridge */ boolean a(com.mappls.sdk.plugin.directions.view.a aVar) {
            return super.containsValue(aVar);
        }

        public /* bridge */ com.mappls.sdk.plugin.directions.view.a b(Pair<String, String> pair) {
            return (com.mappls.sdk.plugin.directions.view.a) super.get(pair);
        }

        public /* bridge */ Set<Pair<String, String>> b() {
            return super.keySet();
        }

        public /* bridge */ boolean b(Pair<String, String> pair, com.mappls.sdk.plugin.directions.view.a aVar) {
            return super.remove(pair, aVar);
        }

        public /* bridge */ int c() {
            return super.size();
        }

        public /* bridge */ com.mappls.sdk.plugin.directions.view.a c(Pair<String, String> pair) {
            return (com.mappls.sdk.plugin.directions.view.a) super.remove(pair);
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ boolean containsKey(Object obj) {
            if (obj instanceof Pair) {
                return a((Pair) obj);
            }
            return false;
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ boolean containsValue(Object obj) {
            if (obj instanceof com.mappls.sdk.plugin.directions.view.a) {
                return a((com.mappls.sdk.plugin.directions.view.a) obj);
            }
            return false;
        }

        public /* bridge */ Collection<com.mappls.sdk.plugin.directions.view.a> d() {
            return super.values();
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ Set<Map.Entry<Pair<String, String>, com.mappls.sdk.plugin.directions.view.a>> entrySet() {
            return a();
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ /* synthetic */ Object get(Object obj) {
            if (obj instanceof Pair) {
                return b((Pair) obj);
            }
            return null;
        }

        @Override // java.util.HashMap, java.util.Map
        public final /* bridge */ /* synthetic */ Object getOrDefault(Object obj, Object obj2) {
            return !(obj instanceof Pair) ? obj2 : a((Pair) obj, (com.mappls.sdk.plugin.directions.view.a) obj2);
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ Set<Pair<String, String>> keySet() {
            return b();
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ /* synthetic */ Object remove(Object obj) {
            if (obj instanceof Pair) {
                return c((Pair) obj);
            }
            return null;
        }

        @Override // java.util.HashMap, java.util.Map
        public final /* bridge */ boolean remove(Object obj, Object obj2) {
            if ((obj instanceof Pair) && (obj2 instanceof com.mappls.sdk.plugin.directions.view.a)) {
                return b((Pair) obj, (com.mappls.sdk.plugin.directions.view.a) obj2);
            }
            return false;
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ int size() {
            return c();
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ Collection<com.mappls.sdk.plugin.directions.view.a> values() {
            return d();
        }
    }

    /* renamed from: com.mappls.sdk.plugin.directions.view.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static final class C0657b extends HashSet<String> {
        public C0657b() {
            add("off ramp");
            add("fork");
            add("roundabout");
            add("roundabout turn");
            add("rotary");
        }

        public /* bridge */ int a() {
            return super.size();
        }

        public /* bridge */ boolean a(String str) {
            return super.contains(str);
        }

        public /* bridge */ boolean b(String str) {
            return super.remove(str);
        }

        @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final /* bridge */ boolean contains(Object obj) {
            if (obj instanceof String) {
                return a((String) obj);
            }
            return false;
        }

        @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final /* bridge */ boolean remove(Object obj) {
            if (obj instanceof String) {
                return b((String) obj);
            }
            return false;
        }

        @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final /* bridge */ int size() {
            return a();
        }
    }

    /* loaded from: classes11.dex */
    public static final class c extends HashSet<String> {
        public c() {
            add("roundabout");
            add("roundabout turn");
        }

        public /* bridge */ int a() {
            return super.size();
        }

        public /* bridge */ boolean a(String str) {
            return super.contains(str);
        }

        public /* bridge */ boolean b(String str) {
            return super.remove(str);
        }

        @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final /* bridge */ boolean contains(Object obj) {
            if (obj instanceof String) {
                return a((String) obj);
            }
            return false;
        }

        @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final /* bridge */ boolean remove(Object obj) {
            if (obj instanceof String) {
                return b((String) obj);
            }
            return false;
        }

        @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final /* bridge */ int size() {
            return a();
        }
    }

    /* loaded from: classes11.dex */
    public static final class d extends HashSet<String> {
        public d() {
            add("slight left");
            add("left");
            add("sharp left");
            add("uturn");
        }

        public /* bridge */ int a() {
            return super.size();
        }

        public /* bridge */ boolean a(String str) {
            return super.contains(str);
        }

        public /* bridge */ boolean b(String str) {
            return super.remove(str);
        }

        @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final /* bridge */ boolean contains(Object obj) {
            if (obj instanceof String) {
                return a((String) obj);
            }
            return false;
        }

        @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final /* bridge */ boolean remove(Object obj) {
            if (obj instanceof String) {
                return b((String) obj);
            }
            return false;
        }

        @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final /* bridge */ int size() {
            return a();
        }
    }

    @JvmStatic
    public static final float a(float f) {
        if (f < 0.0f) {
            return 0.0f;
        }
        if (f > 360.0f) {
            return 360.0f;
        }
        return f;
    }

    @JvmStatic
    public static final boolean a(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        boolean areEqual = Intrinsics.areEqual("left", str3);
        boolean contains = CollectionsKt___CollectionsKt.contains(d, str);
        boolean z = !(str2 == null || kotlin.text.m.isBlank(str2)) && StringsKt__StringsKt.contains$default((CharSequence) "uturn", (CharSequence) str2, false, 2, (Object) null);
        boolean contains2 = CollectionsKt___CollectionsKt.contains(c, str2);
        if (Intrinsics.areEqual(str, "depart") && str2 == null) {
            return true;
        }
        if (contains) {
            contains2 = areEqual;
        }
        return (areEqual && z) ? !contains2 : contains2;
    }
}
