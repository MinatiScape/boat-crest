package com.mappls.sdk.nearby.plugin.model;

import android.graphics.Bitmap;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.nearby.plugin.model.NearbyUIOption;
import java.util.Objects;
/* loaded from: classes10.dex */
public abstract class c extends NearbyUIOption {
    public final Integer A;
    public final Integer B;
    public final String C;
    public final Integer D;
    public final Integer E;
    public final Integer F;
    public final Integer G;
    public final Integer H;
    public final Integer I;
    public final Integer J;
    public final Integer K;
    public final Integer L;
    public final Integer M;
    public final Integer N;
    public final Integer O;
    public final Integer P;
    public final Integer Q;
    public final Integer R;
    public final Integer S;
    public final Bitmap T;
    public final Integer U;
    public final Float V;
    public final Boolean W;
    public final Integer h;
    public final Integer i;
    public final Integer j;
    public final Integer k;
    public final Bitmap l;
    public final Integer m;
    public final Integer n;
    public final Integer o;
    public final Integer p;
    public final Integer q;
    public final Integer r;
    public final Integer s;
    public final String t;
    public final Integer u;
    public final Integer v;
    public final Integer w;
    public final Integer x;
    public final Integer y;
    public final Integer z;

    /* loaded from: classes10.dex */
    public static class a extends NearbyUIOption.Builder {
        public Integer A;
        public Integer B;
        public Integer C;
        public Integer D;
        public Integer E;
        public Integer F;
        public Integer G;
        public Integer H;
        public Integer I;
        public Integer J;
        public Integer K;
        public Integer L;
        public Bitmap M;
        public Integer N;
        public Float O;
        public Boolean P;

        /* renamed from: a  reason: collision with root package name */
        public Integer f13066a;
        public Integer b;
        public Integer c;
        public Integer d;
        public Bitmap e;
        public Integer f;
        public Integer g;
        public Integer h;
        public Integer i;
        public Integer j;
        public Integer k;
        public Integer l;
        public String m;
        public Integer n;
        public Integer o;
        public Integer p;
        public Integer q;
        public Integer r;
        public Integer s;
        public Integer t;
        public Integer u;
        public String v;
        public Integer w;
        public Integer x;
        public Integer y;
        public Integer z;

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder backgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null backgroundColor");
            this.f13066a = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption build() {
            String a2 = this.f13066a == null ? com.mappls.sdk.nearby.plugin.model.a.a("", " backgroundColor") : "";
            if (this.b == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " toolbarColor");
            }
            if (this.c == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " nearbyToolbarColor");
            }
            if (this.d == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " nearbyToolbarIcon");
            }
            if (this.f == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " toolbarTintColor");
            }
            if (this.g == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " nearbyToolbarTintColor");
            }
            if (this.h == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " locationDetailsBackgroundColor");
            }
            if (this.i == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " detailListBackgroundColor");
            }
            if (this.j == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " categoryFilterBackgroundColor");
            }
            if (this.k == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " detailListSeperatorBackgroundColor");
            }
            if (this.l == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " locationDetailInfoTextColor");
            }
            if (this.m == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " locationDetailsInfoLabelText");
            }
            if (this.n == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " locationDetailFormattedAddressTextColor");
            }
            if (this.o == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " changeLocationButtonTextColor");
            }
            if (this.p == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " prevButtonBackgroundColor");
            }
            if (this.q == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " nextButtonBackgroundColor");
            }
            if (this.r == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " useCurrentLocationButtonTextColor");
            }
            if (this.t == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " submitButtonResource");
            }
            if (this.u == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " submitButtonTextColor");
            }
            if (this.v == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " submitButtonText");
            }
            if (this.w == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " selectedCategoryBackgroundColor");
            }
            if (this.x == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " selectedCategoryTextColor");
            }
            if (this.y == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " selectedCategoryTintColor");
            }
            if (this.z == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " categoryBackgroundColor");
            }
            if (this.A == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " categoryTextColor");
            }
            if (this.B == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " categoryTintColor");
            }
            if (this.C == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " paginationBackgroundColor");
            }
            if (this.D == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " tabTextColor");
            }
            if (this.E == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " selectedTabTextColor");
            }
            if (this.F == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " tabIndicatorColor");
            }
            if (this.G == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " tabBackgroundColor");
            }
            if (this.H == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " tabIconTint");
            }
            if (this.I == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " placeNameTextColor");
            }
            if (this.J == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " distanceTextColor");
            }
            if (this.K == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " placeAddressTextColor");
            }
            if (this.L == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " refLocationIcon");
            }
            if (this.N == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " refLocationCircleColor");
            }
            if (this.O == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " refLocationCircleAlpha");
            }
            if (this.P == null) {
                a2 = com.mappls.sdk.nearby.plugin.model.a.a(a2, " showDefaultMap");
            }
            if (a2.isEmpty()) {
                return new e(this.f13066a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.z, this.A, this.B, this.C, this.D, this.E, this.F, this.G, this.H, this.I, this.J, this.K, this.L, this.M, this.N, this.O, this.P);
            }
            throw new IllegalStateException(com.mappls.sdk.nearby.plugin.model.a.a("Missing required properties:", a2));
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder categoryBackgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null categoryBackgroundColor");
            this.z = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder categoryFilterBackgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null categoryFilterBackgroundColor");
            this.j = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder categoryTextColor(Integer num) {
            Objects.requireNonNull(num, "Null categoryTextColor");
            this.A = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder categoryTintColor(Integer num) {
            Objects.requireNonNull(num, "Null categoryTintColor");
            this.B = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder changeLocationButtonTextColor(Integer num) {
            Objects.requireNonNull(num, "Null changeLocationButtonTextColor");
            this.o = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder detailListBackgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null detailListBackgroundColor");
            this.i = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder detailListSeperatorBackgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null detailListSeperatorBackgroundColor");
            this.k = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder distanceTextColor(Integer num) {
            Objects.requireNonNull(num, "Null distanceTextColor");
            this.J = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder locationDetailFormattedAddressTextColor(Integer num) {
            Objects.requireNonNull(num, "Null locationDetailFormattedAddressTextColor");
            this.n = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder locationDetailInfoTextColor(Integer num) {
            Objects.requireNonNull(num, "Null locationDetailInfoTextColor");
            this.l = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder locationDetailsBackgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null locationDetailsBackgroundColor");
            this.h = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder locationDetailsInfoLabelText(String str) {
            Objects.requireNonNull(str, "Null locationDetailsInfoLabelText");
            this.m = str;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder nearbyToolbarBitmap(@Nullable Bitmap bitmap) {
            this.e = bitmap;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder nearbyToolbarColor(Integer num) {
            Objects.requireNonNull(num, "Null nearbyToolbarColor");
            this.c = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder nearbyToolbarIcon(Integer num) {
            Objects.requireNonNull(num, "Null nearbyToolbarIcon");
            this.d = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder nearbyToolbarTintColor(Integer num) {
            Objects.requireNonNull(num, "Null nearbyToolbarTintColor");
            this.g = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder nextButtonBackgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null nextButtonBackgroundColor");
            this.q = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder paginationBackgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null paginationBackgroundColor");
            this.C = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder placeAddressTextColor(Integer num) {
            Objects.requireNonNull(num, "Null placeAddressTextColor");
            this.K = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder placeNameTextColor(Integer num) {
            Objects.requireNonNull(num, "Null placeNameTextColor");
            this.I = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder prevButtonBackgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null prevButtonBackgroundColor");
            this.p = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder refLocationBitmap(@Nullable Bitmap bitmap) {
            this.M = bitmap;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder refLocationCircleAlpha(Float f) {
            Objects.requireNonNull(f, "Null refLocationCircleAlpha");
            this.O = f;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder refLocationCircleColor(Integer num) {
            Objects.requireNonNull(num, "Null refLocationCircleColor");
            this.N = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder refLocationIcon(Integer num) {
            Objects.requireNonNull(num, "Null refLocationIcon");
            this.L = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder selectedCategoryBackgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null selectedCategoryBackgroundColor");
            this.w = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder selectedCategoryTextColor(Integer num) {
            Objects.requireNonNull(num, "Null selectedCategoryTextColor");
            this.x = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder selectedCategoryTintColor(Integer num) {
            Objects.requireNonNull(num, "Null selectedCategoryTintColor");
            this.y = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder selectedTabTextColor(Integer num) {
            Objects.requireNonNull(num, "Null selectedTabTextColor");
            this.E = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder showDefaultMap(Boolean bool) {
            Objects.requireNonNull(bool, "Null showDefaultMap");
            this.P = bool;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder submitButtonColor(@Nullable Integer num) {
            this.s = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder submitButtonResource(Integer num) {
            Objects.requireNonNull(num, "Null submitButtonResource");
            this.t = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder submitButtonText(String str) {
            Objects.requireNonNull(str, "Null submitButtonText");
            this.v = str;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder submitButtonTextColor(Integer num) {
            Objects.requireNonNull(num, "Null submitButtonTextColor");
            this.u = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder tabBackgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null tabBackgroundColor");
            this.G = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder tabIconTint(Integer num) {
            Objects.requireNonNull(num, "Null tabIconTint");
            this.H = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder tabIndicatorColor(Integer num) {
            Objects.requireNonNull(num, "Null tabIndicatorColor");
            this.F = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder tabTextColor(Integer num) {
            Objects.requireNonNull(num, "Null tabTextColor");
            this.D = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder toolbarColor(Integer num) {
            Objects.requireNonNull(num, "Null toolbarColor");
            this.b = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder toolbarTintColor(Integer num) {
            Objects.requireNonNull(num, "Null toolbarTintColor");
            this.f = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption.Builder
        public final NearbyUIOption.Builder useCurrentLocationButtonTextColor(Integer num) {
            Objects.requireNonNull(num, "Null useCurrentLocationButtonTextColor");
            this.r = num;
            return this;
        }
    }

    public c(Integer num, Integer num2, Integer num3, Integer num4, @Nullable Bitmap bitmap, Integer num5, Integer num6, Integer num7, Integer num8, Integer num9, Integer num10, Integer num11, String str, Integer num12, Integer num13, Integer num14, Integer num15, Integer num16, @Nullable Integer num17, Integer num18, Integer num19, String str2, Integer num20, Integer num21, Integer num22, Integer num23, Integer num24, Integer num25, Integer num26, Integer num27, Integer num28, Integer num29, Integer num30, Integer num31, Integer num32, Integer num33, Integer num34, Integer num35, @Nullable Bitmap bitmap2, Integer num36, Float f, Boolean bool) {
        Objects.requireNonNull(num, "Null backgroundColor");
        this.h = num;
        Objects.requireNonNull(num2, "Null toolbarColor");
        this.i = num2;
        Objects.requireNonNull(num3, "Null nearbyToolbarColor");
        this.j = num3;
        Objects.requireNonNull(num4, "Null nearbyToolbarIcon");
        this.k = num4;
        this.l = bitmap;
        Objects.requireNonNull(num5, "Null toolbarTintColor");
        this.m = num5;
        Objects.requireNonNull(num6, "Null nearbyToolbarTintColor");
        this.n = num6;
        Objects.requireNonNull(num7, "Null locationDetailsBackgroundColor");
        this.o = num7;
        Objects.requireNonNull(num8, "Null detailListBackgroundColor");
        this.p = num8;
        Objects.requireNonNull(num9, "Null categoryFilterBackgroundColor");
        this.q = num9;
        Objects.requireNonNull(num10, "Null detailListSeperatorBackgroundColor");
        this.r = num10;
        Objects.requireNonNull(num11, "Null locationDetailInfoTextColor");
        this.s = num11;
        Objects.requireNonNull(str, "Null locationDetailsInfoLabelText");
        this.t = str;
        Objects.requireNonNull(num12, "Null locationDetailFormattedAddressTextColor");
        this.u = num12;
        Objects.requireNonNull(num13, "Null changeLocationButtonTextColor");
        this.v = num13;
        Objects.requireNonNull(num14, "Null prevButtonBackgroundColor");
        this.w = num14;
        Objects.requireNonNull(num15, "Null nextButtonBackgroundColor");
        this.x = num15;
        Objects.requireNonNull(num16, "Null useCurrentLocationButtonTextColor");
        this.y = num16;
        this.z = num17;
        Objects.requireNonNull(num18, "Null submitButtonResource");
        this.A = num18;
        Objects.requireNonNull(num19, "Null submitButtonTextColor");
        this.B = num19;
        Objects.requireNonNull(str2, "Null submitButtonText");
        this.C = str2;
        Objects.requireNonNull(num20, "Null selectedCategoryBackgroundColor");
        this.D = num20;
        Objects.requireNonNull(num21, "Null selectedCategoryTextColor");
        this.E = num21;
        Objects.requireNonNull(num22, "Null selectedCategoryTintColor");
        this.F = num22;
        Objects.requireNonNull(num23, "Null categoryBackgroundColor");
        this.G = num23;
        Objects.requireNonNull(num24, "Null categoryTextColor");
        this.H = num24;
        Objects.requireNonNull(num25, "Null categoryTintColor");
        this.I = num25;
        Objects.requireNonNull(num26, "Null paginationBackgroundColor");
        this.J = num26;
        Objects.requireNonNull(num27, "Null tabTextColor");
        this.K = num27;
        Objects.requireNonNull(num28, "Null selectedTabTextColor");
        this.L = num28;
        Objects.requireNonNull(num29, "Null tabIndicatorColor");
        this.M = num29;
        Objects.requireNonNull(num30, "Null tabBackgroundColor");
        this.N = num30;
        Objects.requireNonNull(num31, "Null tabIconTint");
        this.O = num31;
        Objects.requireNonNull(num32, "Null placeNameTextColor");
        this.P = num32;
        Objects.requireNonNull(num33, "Null distanceTextColor");
        this.Q = num33;
        Objects.requireNonNull(num34, "Null placeAddressTextColor");
        this.R = num34;
        Objects.requireNonNull(num35, "Null refLocationIcon");
        this.S = num35;
        this.T = bitmap2;
        Objects.requireNonNull(num36, "Null refLocationCircleColor");
        this.U = num36;
        Objects.requireNonNull(f, "Null refLocationCircleAlpha");
        this.V = f;
        Objects.requireNonNull(bool, "Null showDefaultMap");
        this.W = bool;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer backgroundColor() {
        return this.h;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer categoryBackgroundColor() {
        return this.G;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer categoryFilterBackgroundColor() {
        return this.q;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer categoryTextColor() {
        return this.H;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer categoryTintColor() {
        return this.I;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer changeLocationButtonTextColor() {
        return this.v;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer detailListBackgroundColor() {
        return this.p;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer detailListSeperatorBackgroundColor() {
        return this.r;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer distanceTextColor() {
        return this.Q;
    }

    public final boolean equals(Object obj) {
        Bitmap bitmap;
        Integer num;
        Bitmap bitmap2;
        if (obj == this) {
            return true;
        }
        if (obj instanceof NearbyUIOption) {
            NearbyUIOption nearbyUIOption = (NearbyUIOption) obj;
            return this.h.equals(nearbyUIOption.backgroundColor()) && this.i.equals(nearbyUIOption.toolbarColor()) && this.j.equals(nearbyUIOption.nearbyToolbarColor()) && this.k.equals(nearbyUIOption.nearbyToolbarIcon()) && ((bitmap = this.l) != null ? bitmap.equals(nearbyUIOption.nearbyToolbarBitmap()) : nearbyUIOption.nearbyToolbarBitmap() == null) && this.m.equals(nearbyUIOption.toolbarTintColor()) && this.n.equals(nearbyUIOption.nearbyToolbarTintColor()) && this.o.equals(nearbyUIOption.locationDetailsBackgroundColor()) && this.p.equals(nearbyUIOption.detailListBackgroundColor()) && this.q.equals(nearbyUIOption.categoryFilterBackgroundColor()) && this.r.equals(nearbyUIOption.detailListSeperatorBackgroundColor()) && this.s.equals(nearbyUIOption.locationDetailInfoTextColor()) && this.t.equals(nearbyUIOption.locationDetailsInfoLabelText()) && this.u.equals(nearbyUIOption.locationDetailFormattedAddressTextColor()) && this.v.equals(nearbyUIOption.changeLocationButtonTextColor()) && this.w.equals(nearbyUIOption.prevButtonBackgroundColor()) && this.x.equals(nearbyUIOption.nextButtonBackgroundColor()) && this.y.equals(nearbyUIOption.useCurrentLocationButtonTextColor()) && ((num = this.z) != null ? num.equals(nearbyUIOption.submitButtonColor()) : nearbyUIOption.submitButtonColor() == null) && this.A.equals(nearbyUIOption.submitButtonResource()) && this.B.equals(nearbyUIOption.submitButtonTextColor()) && this.C.equals(nearbyUIOption.submitButtonText()) && this.D.equals(nearbyUIOption.selectedCategoryBackgroundColor()) && this.E.equals(nearbyUIOption.selectedCategoryTextColor()) && this.F.equals(nearbyUIOption.selectedCategoryTintColor()) && this.G.equals(nearbyUIOption.categoryBackgroundColor()) && this.H.equals(nearbyUIOption.categoryTextColor()) && this.I.equals(nearbyUIOption.categoryTintColor()) && this.J.equals(nearbyUIOption.paginationBackgroundColor()) && this.K.equals(nearbyUIOption.tabTextColor()) && this.L.equals(nearbyUIOption.selectedTabTextColor()) && this.M.equals(nearbyUIOption.tabIndicatorColor()) && this.N.equals(nearbyUIOption.tabBackgroundColor()) && this.O.equals(nearbyUIOption.tabIconTint()) && this.P.equals(nearbyUIOption.placeNameTextColor()) && this.Q.equals(nearbyUIOption.distanceTextColor()) && this.R.equals(nearbyUIOption.placeAddressTextColor()) && this.S.equals(nearbyUIOption.refLocationIcon()) && ((bitmap2 = this.T) != null ? bitmap2.equals(nearbyUIOption.refLocationBitmap()) : nearbyUIOption.refLocationBitmap() == null) && this.U.equals(nearbyUIOption.refLocationCircleColor()) && this.V.equals(nearbyUIOption.refLocationCircleAlpha()) && this.W.equals(nearbyUIOption.showDefaultMap());
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (((((((this.h.hashCode() ^ 1000003) * 1000003) ^ this.i.hashCode()) * 1000003) ^ this.j.hashCode()) * 1000003) ^ this.k.hashCode()) * 1000003;
        Bitmap bitmap = this.l;
        int hashCode2 = (((((((((((((((((((((((((((hashCode ^ (bitmap == null ? 0 : bitmap.hashCode())) * 1000003) ^ this.m.hashCode()) * 1000003) ^ this.n.hashCode()) * 1000003) ^ this.o.hashCode()) * 1000003) ^ this.p.hashCode()) * 1000003) ^ this.q.hashCode()) * 1000003) ^ this.r.hashCode()) * 1000003) ^ this.s.hashCode()) * 1000003) ^ this.t.hashCode()) * 1000003) ^ this.u.hashCode()) * 1000003) ^ this.v.hashCode()) * 1000003) ^ this.w.hashCode()) * 1000003) ^ this.x.hashCode()) * 1000003) ^ this.y.hashCode()) * 1000003;
        Integer num = this.z;
        int hashCode3 = (((((((((((((((((((((((((((((((((((((((hashCode2 ^ (num == null ? 0 : num.hashCode())) * 1000003) ^ this.A.hashCode()) * 1000003) ^ this.B.hashCode()) * 1000003) ^ this.C.hashCode()) * 1000003) ^ this.D.hashCode()) * 1000003) ^ this.E.hashCode()) * 1000003) ^ this.F.hashCode()) * 1000003) ^ this.G.hashCode()) * 1000003) ^ this.H.hashCode()) * 1000003) ^ this.I.hashCode()) * 1000003) ^ this.J.hashCode()) * 1000003) ^ this.K.hashCode()) * 1000003) ^ this.L.hashCode()) * 1000003) ^ this.M.hashCode()) * 1000003) ^ this.N.hashCode()) * 1000003) ^ this.O.hashCode()) * 1000003) ^ this.P.hashCode()) * 1000003) ^ this.Q.hashCode()) * 1000003) ^ this.R.hashCode()) * 1000003) ^ this.S.hashCode()) * 1000003;
        Bitmap bitmap2 = this.T;
        return ((((((hashCode3 ^ (bitmap2 != null ? bitmap2.hashCode() : 0)) * 1000003) ^ this.U.hashCode()) * 1000003) ^ this.V.hashCode()) * 1000003) ^ this.W.hashCode();
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer locationDetailFormattedAddressTextColor() {
        return this.u;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer locationDetailInfoTextColor() {
        return this.s;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer locationDetailsBackgroundColor() {
        return this.o;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final String locationDetailsInfoLabelText() {
        return this.t;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @Nullable
    public final Bitmap nearbyToolbarBitmap() {
        return this.l;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer nearbyToolbarColor() {
        return this.j;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    @DrawableRes
    public final Integer nearbyToolbarIcon() {
        return this.k;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer nearbyToolbarTintColor() {
        return this.n;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer nextButtonBackgroundColor() {
        return this.x;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer paginationBackgroundColor() {
        return this.J;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer placeAddressTextColor() {
        return this.R;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer placeNameTextColor() {
        return this.P;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer prevButtonBackgroundColor() {
        return this.w;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @Nullable
    public final Bitmap refLocationBitmap() {
        return this.T;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Float refLocationCircleAlpha() {
        return this.V;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer refLocationCircleColor() {
        return this.U;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer refLocationIcon() {
        return this.S;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer selectedCategoryBackgroundColor() {
        return this.D;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer selectedCategoryTextColor() {
        return this.E;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer selectedCategoryTintColor() {
        return this.F;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer selectedTabTextColor() {
        return this.L;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Boolean showDefaultMap() {
        return this.W;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @Nullable
    public final Integer submitButtonColor() {
        return this.z;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer submitButtonResource() {
        return this.A;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final String submitButtonText() {
        return this.C;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer submitButtonTextColor() {
        return this.B;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer tabBackgroundColor() {
        return this.N;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer tabIconTint() {
        return this.O;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer tabIndicatorColor() {
        return this.M;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer tabTextColor() {
        return this.K;
    }

    public final String toString() {
        return "NearbyUIOption{backgroundColor=" + this.h + ", toolbarColor=" + this.i + ", nearbyToolbarColor=" + this.j + ", nearbyToolbarIcon=" + this.k + ", nearbyToolbarBitmap=" + this.l + ", toolbarTintColor=" + this.m + ", nearbyToolbarTintColor=" + this.n + ", locationDetailsBackgroundColor=" + this.o + ", detailListBackgroundColor=" + this.p + ", categoryFilterBackgroundColor=" + this.q + ", detailListSeperatorBackgroundColor=" + this.r + ", locationDetailInfoTextColor=" + this.s + ", locationDetailsInfoLabelText=" + this.t + ", locationDetailFormattedAddressTextColor=" + this.u + ", changeLocationButtonTextColor=" + this.v + ", prevButtonBackgroundColor=" + this.w + ", nextButtonBackgroundColor=" + this.x + ", useCurrentLocationButtonTextColor=" + this.y + ", submitButtonColor=" + this.z + ", submitButtonResource=" + this.A + ", submitButtonTextColor=" + this.B + ", submitButtonText=" + this.C + ", selectedCategoryBackgroundColor=" + this.D + ", selectedCategoryTextColor=" + this.E + ", selectedCategoryTintColor=" + this.F + ", categoryBackgroundColor=" + this.G + ", categoryTextColor=" + this.H + ", categoryTintColor=" + this.I + ", paginationBackgroundColor=" + this.J + ", tabTextColor=" + this.K + ", selectedTabTextColor=" + this.L + ", tabIndicatorColor=" + this.M + ", tabBackgroundColor=" + this.N + ", tabIconTint=" + this.O + ", placeNameTextColor=" + this.P + ", distanceTextColor=" + this.Q + ", placeAddressTextColor=" + this.R + ", refLocationIcon=" + this.S + ", refLocationBitmap=" + this.T + ", refLocationCircleColor=" + this.U + ", refLocationCircleAlpha=" + this.V + ", showDefaultMap=" + this.W + "}";
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer toolbarColor() {
        return this.i;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer toolbarTintColor() {
        return this.m;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyUIOption
    @NonNull
    public final Integer useCurrentLocationButtonTextColor() {
        return this.y;
    }
}
