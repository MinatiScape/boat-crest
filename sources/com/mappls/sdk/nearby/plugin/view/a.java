package com.mappls.sdk.nearby.plugin.view;

import android.graphics.Bitmap;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption;
import java.util.Objects;
/* loaded from: classes10.dex */
public abstract class a extends NearbyResultViewOption {
    public final Bitmap A;
    public final Integer B;
    public final Float C;
    public final Boolean D;
    public final Integer h;
    public final Integer i;
    public final Integer j;
    public final Integer k;
    public final String l;
    public final Integer m;
    public final Integer n;
    public final Integer o;
    public final Integer p;
    public final Integer q;
    public final Integer r;
    public final Integer s;
    public final Integer t;
    public final Integer u;
    public final Integer v;
    public final Integer w;
    public final Integer x;
    public final Integer y;
    public final Integer z;

    /* renamed from: com.mappls.sdk.nearby.plugin.view.a$a  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public static class C0654a extends NearbyResultViewOption.Builder {

        /* renamed from: a  reason: collision with root package name */
        public Integer f13071a;
        public Integer b;
        public Integer c;
        public Integer d;
        public String e;
        public Integer f;
        public Integer g;
        public Integer h;
        public Integer i;
        public Integer j;
        public Integer k;
        public Integer l;
        public Integer m;
        public Integer n;
        public Integer o;
        public Integer p;
        public Integer q;
        public Integer r;
        public Integer s;
        public Bitmap t;
        public Integer u;
        public Float v;
        public Boolean w;

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.Builder
        public final NearbyResultViewOption.Builder addressTextColor(Integer num) {
            Objects.requireNonNull(num, "Null addressTextColor");
            this.r = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.Builder
        public final NearbyResultViewOption.Builder backgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null backgroundColor");
            this.f13071a = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.Builder
        public final NearbyResultViewOption build() {
            String a2 = this.f13071a == null ? com.mappls.sdk.nearby.plugin.model.a.a("", " backgroundColor") : "";
            if (this.b == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " paginationBackgroundColor");
            }
            if (this.c == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " toolbarBackgroundColor");
            }
            if (this.d == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " toolbarTintColor");
            }
            if (this.e == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " toolbarText");
            }
            if (this.f == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " tabTextColor");
            }
            if (this.g == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " tabSelectedTextColor");
            }
            if (this.h == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " categoryFilterBackgroundColor");
            }
            if (this.i == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " tabIndicatorColor");
            }
            if (this.j == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " tabBackgroundColor");
            }
            if (this.k == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " tabIconTint");
            }
            if (this.l == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " placeNameTextColor");
            }
            if (this.m == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " distanceTextColor");
            }
            if (this.n == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " listBackgroundColor");
            }
            if (this.o == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " prevButtonBackgroundColor");
            }
            if (this.p == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " nextButtonBackgroundColor");
            }
            if (this.q == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " listSeperatorColor");
            }
            if (this.r == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " addressTextColor");
            }
            if (this.s == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " locationMarkerIcon");
            }
            if (this.u == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " locationCircleColor");
            }
            if (this.v == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " locationCircleAlpha");
            }
            if (this.w == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " showDefaultMap");
            }
            if (a2.isEmpty()) {
                return new c(this.f13071a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.v, this.w);
            }
            throw new IllegalStateException(com.mappls.sdk.nearby.plugin.model.a.a("Missing required properties:", a2));
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.Builder
        public final NearbyResultViewOption.Builder categoryFilterBackgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null categoryFilterBackgroundColor");
            this.h = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.Builder
        public final NearbyResultViewOption.Builder distanceTextColor(Integer num) {
            Objects.requireNonNull(num, "Null distanceTextColor");
            this.m = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.Builder
        public final NearbyResultViewOption.Builder listBackgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null listBackgroundColor");
            this.n = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.Builder
        public final NearbyResultViewOption.Builder listSeperatorColor(Integer num) {
            Objects.requireNonNull(num, "Null listSeperatorColor");
            this.q = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.Builder
        public final NearbyResultViewOption.Builder locationCircleAlpha(Float f) {
            Objects.requireNonNull(f, "Null locationCircleAlpha");
            this.v = f;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.Builder
        public final NearbyResultViewOption.Builder locationCircleColor(Integer num) {
            Objects.requireNonNull(num, "Null locationCircleColor");
            this.u = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.Builder
        public final NearbyResultViewOption.Builder locationMarkerBitmap(@Nullable Bitmap bitmap) {
            this.t = bitmap;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.Builder
        public final NearbyResultViewOption.Builder locationMarkerIcon(Integer num) {
            Objects.requireNonNull(num, "Null locationMarkerIcon");
            this.s = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.Builder
        public final NearbyResultViewOption.Builder nextButtonBackgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null nextButtonBackgroundColor");
            this.p = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.Builder
        public final NearbyResultViewOption.Builder paginationBackgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null paginationBackgroundColor");
            this.b = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.Builder
        public final NearbyResultViewOption.Builder placeNameTextColor(Integer num) {
            Objects.requireNonNull(num, "Null placeNameTextColor");
            this.l = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.Builder
        public final NearbyResultViewOption.Builder prevButtonBackgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null prevButtonBackgroundColor");
            this.o = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.Builder
        public final NearbyResultViewOption.Builder showDefaultMap(Boolean bool) {
            Objects.requireNonNull(bool, "Null showDefaultMap");
            this.w = bool;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.Builder
        public final NearbyResultViewOption.Builder tabBackgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null tabBackgroundColor");
            this.j = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.Builder
        public final NearbyResultViewOption.Builder tabIconTint(Integer num) {
            Objects.requireNonNull(num, "Null tabIconTint");
            this.k = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.Builder
        public final NearbyResultViewOption.Builder tabIndicatorColor(Integer num) {
            Objects.requireNonNull(num, "Null tabIndicatorColor");
            this.i = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.Builder
        public final NearbyResultViewOption.Builder tabSelectedTextColor(Integer num) {
            Objects.requireNonNull(num, "Null tabSelectedTextColor");
            this.g = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.Builder
        public final NearbyResultViewOption.Builder tabTextColor(Integer num) {
            Objects.requireNonNull(num, "Null tabTextColor");
            this.f = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.Builder
        public final NearbyResultViewOption.Builder toolbarBackgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null toolbarBackgroundColor");
            this.c = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.Builder
        public final NearbyResultViewOption.Builder toolbarText(String str) {
            Objects.requireNonNull(str, "Null toolbarText");
            this.e = str;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption.Builder
        public final NearbyResultViewOption.Builder toolbarTintColor(Integer num) {
            Objects.requireNonNull(num, "Null toolbarTintColor");
            this.d = num;
            return this;
        }
    }

    public a(Integer num, Integer num2, Integer num3, Integer num4, String str, Integer num5, Integer num6, Integer num7, Integer num8, Integer num9, Integer num10, Integer num11, Integer num12, Integer num13, Integer num14, Integer num15, Integer num16, Integer num17, Integer num18, @Nullable Bitmap bitmap, Integer num19, Float f, Boolean bool) {
        Objects.requireNonNull(num, "Null backgroundColor");
        this.h = num;
        Objects.requireNonNull(num2, "Null paginationBackgroundColor");
        this.i = num2;
        Objects.requireNonNull(num3, "Null toolbarBackgroundColor");
        this.j = num3;
        Objects.requireNonNull(num4, "Null toolbarTintColor");
        this.k = num4;
        Objects.requireNonNull(str, "Null toolbarText");
        this.l = str;
        Objects.requireNonNull(num5, "Null tabTextColor");
        this.m = num5;
        Objects.requireNonNull(num6, "Null tabSelectedTextColor");
        this.n = num6;
        Objects.requireNonNull(num7, "Null categoryFilterBackgroundColor");
        this.o = num7;
        Objects.requireNonNull(num8, "Null tabIndicatorColor");
        this.p = num8;
        Objects.requireNonNull(num9, "Null tabBackgroundColor");
        this.q = num9;
        Objects.requireNonNull(num10, "Null tabIconTint");
        this.r = num10;
        Objects.requireNonNull(num11, "Null placeNameTextColor");
        this.s = num11;
        Objects.requireNonNull(num12, "Null distanceTextColor");
        this.t = num12;
        Objects.requireNonNull(num13, "Null listBackgroundColor");
        this.u = num13;
        Objects.requireNonNull(num14, "Null prevButtonBackgroundColor");
        this.v = num14;
        Objects.requireNonNull(num15, "Null nextButtonBackgroundColor");
        this.w = num15;
        Objects.requireNonNull(num16, "Null listSeperatorColor");
        this.x = num16;
        Objects.requireNonNull(num17, "Null addressTextColor");
        this.y = num17;
        Objects.requireNonNull(num18, "Null locationMarkerIcon");
        this.z = num18;
        this.A = bitmap;
        Objects.requireNonNull(num19, "Null locationCircleColor");
        this.B = num19;
        Objects.requireNonNull(f, "Null locationCircleAlpha");
        this.C = f;
        Objects.requireNonNull(bool, "Null showDefaultMap");
        this.D = bool;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption
    @NonNull
    public final Integer addressTextColor() {
        return this.y;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption
    @NonNull
    public final Integer backgroundColor() {
        return this.h;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption
    @NonNull
    public final Integer categoryFilterBackgroundColor() {
        return this.o;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption
    @NonNull
    public final Integer distanceTextColor() {
        return this.t;
    }

    public final boolean equals(Object obj) {
        Bitmap bitmap;
        if (obj == this) {
            return true;
        }
        if (obj instanceof NearbyResultViewOption) {
            NearbyResultViewOption nearbyResultViewOption = (NearbyResultViewOption) obj;
            return this.h.equals(nearbyResultViewOption.backgroundColor()) && this.i.equals(nearbyResultViewOption.paginationBackgroundColor()) && this.j.equals(nearbyResultViewOption.toolbarBackgroundColor()) && this.k.equals(nearbyResultViewOption.toolbarTintColor()) && this.l.equals(nearbyResultViewOption.toolbarText()) && this.m.equals(nearbyResultViewOption.tabTextColor()) && this.n.equals(nearbyResultViewOption.tabSelectedTextColor()) && this.o.equals(nearbyResultViewOption.categoryFilterBackgroundColor()) && this.p.equals(nearbyResultViewOption.tabIndicatorColor()) && this.q.equals(nearbyResultViewOption.tabBackgroundColor()) && this.r.equals(nearbyResultViewOption.tabIconTint()) && this.s.equals(nearbyResultViewOption.placeNameTextColor()) && this.t.equals(nearbyResultViewOption.distanceTextColor()) && this.u.equals(nearbyResultViewOption.listBackgroundColor()) && this.v.equals(nearbyResultViewOption.prevButtonBackgroundColor()) && this.w.equals(nearbyResultViewOption.nextButtonBackgroundColor()) && this.x.equals(nearbyResultViewOption.listSeperatorColor()) && this.y.equals(nearbyResultViewOption.addressTextColor()) && this.z.equals(nearbyResultViewOption.locationMarkerIcon()) && ((bitmap = this.A) != null ? bitmap.equals(nearbyResultViewOption.locationMarkerBitmap()) : nearbyResultViewOption.locationMarkerBitmap() == null) && this.B.equals(nearbyResultViewOption.locationCircleColor()) && this.C.equals(nearbyResultViewOption.locationCircleAlpha()) && this.D.equals(nearbyResultViewOption.showDefaultMap());
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (((((((((((((((((((((((((((((((((((((this.h.hashCode() ^ 1000003) * 1000003) ^ this.i.hashCode()) * 1000003) ^ this.j.hashCode()) * 1000003) ^ this.k.hashCode()) * 1000003) ^ this.l.hashCode()) * 1000003) ^ this.m.hashCode()) * 1000003) ^ this.n.hashCode()) * 1000003) ^ this.o.hashCode()) * 1000003) ^ this.p.hashCode()) * 1000003) ^ this.q.hashCode()) * 1000003) ^ this.r.hashCode()) * 1000003) ^ this.s.hashCode()) * 1000003) ^ this.t.hashCode()) * 1000003) ^ this.u.hashCode()) * 1000003) ^ this.v.hashCode()) * 1000003) ^ this.w.hashCode()) * 1000003) ^ this.x.hashCode()) * 1000003) ^ this.y.hashCode()) * 1000003) ^ this.z.hashCode()) * 1000003;
        Bitmap bitmap = this.A;
        return ((((((hashCode ^ (bitmap == null ? 0 : bitmap.hashCode())) * 1000003) ^ this.B.hashCode()) * 1000003) ^ this.C.hashCode()) * 1000003) ^ this.D.hashCode();
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption
    @NonNull
    public final Integer listBackgroundColor() {
        return this.u;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption
    @NonNull
    public final Integer listSeperatorColor() {
        return this.x;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption
    @NonNull
    public final Float locationCircleAlpha() {
        return this.C;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption
    @NonNull
    public final Integer locationCircleColor() {
        return this.B;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption
    @Nullable
    public final Bitmap locationMarkerBitmap() {
        return this.A;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption
    @NonNull
    @DrawableRes
    public final Integer locationMarkerIcon() {
        return this.z;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption
    @NonNull
    public final Integer nextButtonBackgroundColor() {
        return this.w;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption
    @NonNull
    public final Integer paginationBackgroundColor() {
        return this.i;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption
    @NonNull
    public final Integer placeNameTextColor() {
        return this.s;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption
    @NonNull
    public final Integer prevButtonBackgroundColor() {
        return this.v;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption
    @NonNull
    public final Boolean showDefaultMap() {
        return this.D;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption
    @NonNull
    public final Integer tabBackgroundColor() {
        return this.q;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption
    @NonNull
    public final Integer tabIconTint() {
        return this.r;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption
    @NonNull
    public final Integer tabIndicatorColor() {
        return this.p;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption
    @NonNull
    public final Integer tabSelectedTextColor() {
        return this.n;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption
    @NonNull
    public final Integer tabTextColor() {
        return this.m;
    }

    public final String toString() {
        return "NearbyResultViewOption{backgroundColor=" + this.h + ", paginationBackgroundColor=" + this.i + ", toolbarBackgroundColor=" + this.j + ", toolbarTintColor=" + this.k + ", toolbarText=" + this.l + ", tabTextColor=" + this.m + ", tabSelectedTextColor=" + this.n + ", categoryFilterBackgroundColor=" + this.o + ", tabIndicatorColor=" + this.p + ", tabBackgroundColor=" + this.q + ", tabIconTint=" + this.r + ", placeNameTextColor=" + this.s + ", distanceTextColor=" + this.t + ", listBackgroundColor=" + this.u + ", prevButtonBackgroundColor=" + this.v + ", nextButtonBackgroundColor=" + this.w + ", listSeperatorColor=" + this.x + ", addressTextColor=" + this.y + ", locationMarkerIcon=" + this.z + ", locationMarkerBitmap=" + this.A + ", locationCircleColor=" + this.B + ", locationCircleAlpha=" + this.C + ", showDefaultMap=" + this.D + "}";
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption
    @NonNull
    public final Integer toolbarBackgroundColor() {
        return this.j;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption
    @NonNull
    public final String toolbarText() {
        return this.l;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption
    @NonNull
    public final Integer toolbarTintColor() {
        return this.k;
    }
}
