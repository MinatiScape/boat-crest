package com.mappls.sdk.nearby.plugin.view;

import android.graphics.Bitmap;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.nearby.plugin.view.NearbyViewOption;
import java.util.Objects;
/* loaded from: classes10.dex */
public abstract class b extends NearbyViewOption {
    public final Integer A;
    public final String B;
    public final String C;
    public final Integer h;
    public final Integer i;
    public final Integer j;
    public final Bitmap k;
    public final Integer l;
    public final String m;
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

    /* loaded from: classes10.dex */
    public static class a extends NearbyViewOption.Builder {

        /* renamed from: a  reason: collision with root package name */
        public Integer f13072a;
        public Integer b;
        public Integer c;
        public Bitmap d;
        public Integer e;
        public String f;
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
        public Integer t;
        public String u;
        public String v;

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption.Builder
        public final NearbyViewOption.Builder addressBackgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null addressBackgroundColor");
            this.g = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption.Builder
        public final NearbyViewOption.Builder addressTextColor(Integer num) {
            Objects.requireNonNull(num, "Null addressTextColor");
            this.i = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption.Builder
        public final NearbyViewOption.Builder addressTooltipTextColor(Integer num) {
            Objects.requireNonNull(num, "Null addressTooltipTextColor");
            this.h = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption.Builder
        public final NearbyViewOption.Builder backgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null backgroundColor");
            this.f13072a = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption.Builder
        public final NearbyViewOption build() {
            String a2 = this.f13072a == null ? com.mappls.sdk.nearby.plugin.model.a.a("", " backgroundColor") : "";
            if (this.b == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " toolbarBackgroundColor");
            }
            if (this.c == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " toolbarIcon");
            }
            if (this.e == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " toolbarTextColor");
            }
            if (this.f == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " toolbarText");
            }
            if (this.g == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " addressBackgroundColor");
            }
            if (this.h == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " addressTooltipTextColor");
            }
            if (this.i == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " addressTextColor");
            }
            if (this.j == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " changeLocationButtonTextColor");
            }
            if (this.k == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " useCurrentLocationButtonTextColor");
            }
            if (this.l == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " selectedCategoryBackgroundColor");
            }
            if (this.m == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " selectedCategoryTextColor");
            }
            if (this.n == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " selectedCategoryTintColor");
            }
            if (this.o == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " categoryBackgroundColor");
            }
            if (this.p == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " categoryTextColor");
            }
            if (this.q == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " categoryTintColor");
            }
            if (this.r == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " submitButtonResource");
            }
            if (this.t == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " submitButtonTextColor");
            }
            if (this.u == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " submitButtonText");
            }
            if (this.v == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " locationInfoLabelText");
            }
            if (a2.isEmpty()) {
                return new d(this.f13072a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.v);
            }
            throw new IllegalStateException(com.mappls.sdk.nearby.plugin.model.a.a("Missing required properties:", a2));
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption.Builder
        public final NearbyViewOption.Builder categoryBackgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null categoryBackgroundColor");
            this.o = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption.Builder
        public final NearbyViewOption.Builder categoryTextColor(Integer num) {
            Objects.requireNonNull(num, "Null categoryTextColor");
            this.p = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption.Builder
        public final NearbyViewOption.Builder categoryTintColor(Integer num) {
            Objects.requireNonNull(num, "Null categoryTintColor");
            this.q = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption.Builder
        public final NearbyViewOption.Builder changeLocationButtonTextColor(Integer num) {
            Objects.requireNonNull(num, "Null changeLocationButtonTextColor");
            this.j = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption.Builder
        public final NearbyViewOption.Builder locationInfoLabelText(String str) {
            Objects.requireNonNull(str, "Null locationInfoLabelText");
            this.v = str;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption.Builder
        public final NearbyViewOption.Builder selectedCategoryBackgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null selectedCategoryBackgroundColor");
            this.l = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption.Builder
        public final NearbyViewOption.Builder selectedCategoryTextColor(Integer num) {
            Objects.requireNonNull(num, "Null selectedCategoryTextColor");
            this.m = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption.Builder
        public final NearbyViewOption.Builder selectedCategoryTintColor(Integer num) {
            Objects.requireNonNull(num, "Null selectedCategoryTintColor");
            this.n = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption.Builder
        public final NearbyViewOption.Builder submitButtonColor(@Nullable Integer num) {
            this.s = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption.Builder
        public final NearbyViewOption.Builder submitButtonResource(Integer num) {
            Objects.requireNonNull(num, "Null submitButtonResource");
            this.r = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption.Builder
        public final NearbyViewOption.Builder submitButtonText(String str) {
            Objects.requireNonNull(str, "Null submitButtonText");
            this.u = str;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption.Builder
        public final NearbyViewOption.Builder submitButtonTextColor(Integer num) {
            Objects.requireNonNull(num, "Null submitButtonTextColor");
            this.t = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption.Builder
        public final NearbyViewOption.Builder toolbarBackgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null toolbarBackgroundColor");
            this.b = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption.Builder
        public final NearbyViewOption.Builder toolbarBitmap(@Nullable Bitmap bitmap) {
            this.d = bitmap;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption.Builder
        public final NearbyViewOption.Builder toolbarIcon(Integer num) {
            Objects.requireNonNull(num, "Null toolbarIcon");
            this.c = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption.Builder
        public final NearbyViewOption.Builder toolbarText(String str) {
            Objects.requireNonNull(str, "Null toolbarText");
            this.f = str;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption.Builder
        public final NearbyViewOption.Builder toolbarTextColor(Integer num) {
            Objects.requireNonNull(num, "Null toolbarTextColor");
            this.e = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption.Builder
        public final NearbyViewOption.Builder useCurrentLocationButtonTextColor(Integer num) {
            Objects.requireNonNull(num, "Null useCurrentLocationButtonTextColor");
            this.k = num;
            return this;
        }
    }

    public b(Integer num, Integer num2, Integer num3, @Nullable Bitmap bitmap, Integer num4, String str, Integer num5, Integer num6, Integer num7, Integer num8, Integer num9, Integer num10, Integer num11, Integer num12, Integer num13, Integer num14, Integer num15, Integer num16, @Nullable Integer num17, Integer num18, String str2, String str3) {
        Objects.requireNonNull(num, "Null backgroundColor");
        this.h = num;
        Objects.requireNonNull(num2, "Null toolbarBackgroundColor");
        this.i = num2;
        Objects.requireNonNull(num3, "Null toolbarIcon");
        this.j = num3;
        this.k = bitmap;
        Objects.requireNonNull(num4, "Null toolbarTextColor");
        this.l = num4;
        Objects.requireNonNull(str, "Null toolbarText");
        this.m = str;
        Objects.requireNonNull(num5, "Null addressBackgroundColor");
        this.n = num5;
        Objects.requireNonNull(num6, "Null addressTooltipTextColor");
        this.o = num6;
        Objects.requireNonNull(num7, "Null addressTextColor");
        this.p = num7;
        Objects.requireNonNull(num8, "Null changeLocationButtonTextColor");
        this.q = num8;
        Objects.requireNonNull(num9, "Null useCurrentLocationButtonTextColor");
        this.r = num9;
        Objects.requireNonNull(num10, "Null selectedCategoryBackgroundColor");
        this.s = num10;
        Objects.requireNonNull(num11, "Null selectedCategoryTextColor");
        this.t = num11;
        Objects.requireNonNull(num12, "Null selectedCategoryTintColor");
        this.u = num12;
        Objects.requireNonNull(num13, "Null categoryBackgroundColor");
        this.v = num13;
        Objects.requireNonNull(num14, "Null categoryTextColor");
        this.w = num14;
        Objects.requireNonNull(num15, "Null categoryTintColor");
        this.x = num15;
        Objects.requireNonNull(num16, "Null submitButtonResource");
        this.y = num16;
        this.z = num17;
        Objects.requireNonNull(num18, "Null submitButtonTextColor");
        this.A = num18;
        Objects.requireNonNull(str2, "Null submitButtonText");
        this.B = str2;
        Objects.requireNonNull(str3, "Null locationInfoLabelText");
        this.C = str3;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption
    @NonNull
    public final Integer addressBackgroundColor() {
        return this.n;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption
    @NonNull
    public final Integer addressTextColor() {
        return this.p;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption
    @NonNull
    public final Integer addressTooltipTextColor() {
        return this.o;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption
    @NonNull
    public final Integer backgroundColor() {
        return this.h;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption
    @NonNull
    public final Integer categoryBackgroundColor() {
        return this.v;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption
    @NonNull
    public final Integer categoryTextColor() {
        return this.w;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption
    @NonNull
    public final Integer categoryTintColor() {
        return this.x;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption
    @NonNull
    public final Integer changeLocationButtonTextColor() {
        return this.q;
    }

    public final boolean equals(Object obj) {
        Bitmap bitmap;
        Integer num;
        if (obj == this) {
            return true;
        }
        if (obj instanceof NearbyViewOption) {
            NearbyViewOption nearbyViewOption = (NearbyViewOption) obj;
            return this.h.equals(nearbyViewOption.backgroundColor()) && this.i.equals(nearbyViewOption.toolbarBackgroundColor()) && this.j.equals(nearbyViewOption.toolbarIcon()) && ((bitmap = this.k) != null ? bitmap.equals(nearbyViewOption.toolbarBitmap()) : nearbyViewOption.toolbarBitmap() == null) && this.l.equals(nearbyViewOption.toolbarTextColor()) && this.m.equals(nearbyViewOption.toolbarText()) && this.n.equals(nearbyViewOption.addressBackgroundColor()) && this.o.equals(nearbyViewOption.addressTooltipTextColor()) && this.p.equals(nearbyViewOption.addressTextColor()) && this.q.equals(nearbyViewOption.changeLocationButtonTextColor()) && this.r.equals(nearbyViewOption.useCurrentLocationButtonTextColor()) && this.s.equals(nearbyViewOption.selectedCategoryBackgroundColor()) && this.t.equals(nearbyViewOption.selectedCategoryTextColor()) && this.u.equals(nearbyViewOption.selectedCategoryTintColor()) && this.v.equals(nearbyViewOption.categoryBackgroundColor()) && this.w.equals(nearbyViewOption.categoryTextColor()) && this.x.equals(nearbyViewOption.categoryTintColor()) && this.y.equals(nearbyViewOption.submitButtonResource()) && ((num = this.z) != null ? num.equals(nearbyViewOption.submitButtonColor()) : nearbyViewOption.submitButtonColor() == null) && this.A.equals(nearbyViewOption.submitButtonTextColor()) && this.B.equals(nearbyViewOption.submitButtonText()) && this.C.equals(nearbyViewOption.locationInfoLabelText());
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (((((this.h.hashCode() ^ 1000003) * 1000003) ^ this.i.hashCode()) * 1000003) ^ this.j.hashCode()) * 1000003;
        Bitmap bitmap = this.k;
        int hashCode2 = (((((((((((((((((((((((((((((hashCode ^ (bitmap == null ? 0 : bitmap.hashCode())) * 1000003) ^ this.l.hashCode()) * 1000003) ^ this.m.hashCode()) * 1000003) ^ this.n.hashCode()) * 1000003) ^ this.o.hashCode()) * 1000003) ^ this.p.hashCode()) * 1000003) ^ this.q.hashCode()) * 1000003) ^ this.r.hashCode()) * 1000003) ^ this.s.hashCode()) * 1000003) ^ this.t.hashCode()) * 1000003) ^ this.u.hashCode()) * 1000003) ^ this.v.hashCode()) * 1000003) ^ this.w.hashCode()) * 1000003) ^ this.x.hashCode()) * 1000003) ^ this.y.hashCode()) * 1000003;
        Integer num = this.z;
        return ((((((hashCode2 ^ (num != null ? num.hashCode() : 0)) * 1000003) ^ this.A.hashCode()) * 1000003) ^ this.B.hashCode()) * 1000003) ^ this.C.hashCode();
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption
    @NonNull
    public final String locationInfoLabelText() {
        return this.C;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption
    @NonNull
    public final Integer selectedCategoryBackgroundColor() {
        return this.s;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption
    @NonNull
    public final Integer selectedCategoryTextColor() {
        return this.t;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption
    @NonNull
    public final Integer selectedCategoryTintColor() {
        return this.u;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption
    @Nullable
    public final Integer submitButtonColor() {
        return this.z;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption
    @NonNull
    public final Integer submitButtonResource() {
        return this.y;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption
    @NonNull
    public final String submitButtonText() {
        return this.B;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption
    @NonNull
    public final Integer submitButtonTextColor() {
        return this.A;
    }

    public final String toString() {
        return "NearbyViewOption{backgroundColor=" + this.h + ", toolbarBackgroundColor=" + this.i + ", toolbarIcon=" + this.j + ", toolbarBitmap=" + this.k + ", toolbarTextColor=" + this.l + ", toolbarText=" + this.m + ", addressBackgroundColor=" + this.n + ", addressTooltipTextColor=" + this.o + ", addressTextColor=" + this.p + ", changeLocationButtonTextColor=" + this.q + ", useCurrentLocationButtonTextColor=" + this.r + ", selectedCategoryBackgroundColor=" + this.s + ", selectedCategoryTextColor=" + this.t + ", selectedCategoryTintColor=" + this.u + ", categoryBackgroundColor=" + this.v + ", categoryTextColor=" + this.w + ", categoryTintColor=" + this.x + ", submitButtonResource=" + this.y + ", submitButtonColor=" + this.z + ", submitButtonTextColor=" + this.A + ", submitButtonText=" + this.B + ", locationInfoLabelText=" + this.C + "}";
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption
    @NonNull
    public final Integer toolbarBackgroundColor() {
        return this.i;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption
    @Nullable
    public final Bitmap toolbarBitmap() {
        return this.k;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption
    @NonNull
    @DrawableRes
    public final Integer toolbarIcon() {
        return this.j;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption
    @NonNull
    public final String toolbarText() {
        return this.m;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption
    @NonNull
    public final Integer toolbarTextColor() {
        return this.l;
    }

    @Override // com.mappls.sdk.nearby.plugin.view.NearbyViewOption
    @NonNull
    public final Integer useCurrentLocationButtonTextColor() {
        return this.r;
    }
}
