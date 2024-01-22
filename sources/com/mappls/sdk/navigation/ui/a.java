package com.mappls.sdk.navigation.ui;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import com.mappls.sdk.navigation.ui.NavigationOptions;
import java.util.Objects;
/* loaded from: classes11.dex */
public abstract class a extends NavigationOptions {
    public final Integer h;
    public final Integer i;
    public final String j;
    public final String k;
    public final Boolean l;
    public final Boolean m;
    public final Boolean n;
    public final Boolean o;
    public final Boolean p;
    public final Boolean q;
    public final Boolean r;
    public final Boolean s;
    public final Boolean t;
    public final Boolean u;
    public final Boolean v;
    public final Integer w;

    /* renamed from: com.mappls.sdk.navigation.ui.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class C0648a extends NavigationOptions.Builder {

        /* renamed from: a  reason: collision with root package name */
        public Integer f12968a;
        public Integer b;
        public String c;
        public String d;
        public Boolean e;
        public Boolean f;
        public Boolean g;
        public Boolean h;
        public Boolean i;
        public Boolean j;
        public Boolean k;
        public Boolean l;
        public Boolean m;
        public Boolean n;
        public Boolean o;
        public Integer p;

        @Override // com.mappls.sdk.navigation.ui.NavigationOptions.Builder
        public NavigationOptions build() {
            String str = "";
            if (this.f12968a == null) {
                str = " navigationLightTheme";
            }
            if (this.b == null) {
                str = str + " navigationDarkTheme";
            }
            if (this.c == null) {
                str = str + " mapplsMapLightStyle";
            }
            if (this.d == null) {
                str = str + " mapplsMapDarkStyle";
            }
            if (this.e == null) {
                str = str + " isUsingInternalMap";
            }
            if (this.f == null) {
                str = str + " showDayNightOption";
            }
            if (this.g == null) {
                str = str + " showTrafficOption";
            }
            if (this.h == null) {
                str = str + " showNavigationSettingsOption";
            }
            if (this.i == null) {
                str = str + " showSearchDuringNavigationOption";
            }
            if (this.j == null) {
                str = str + " showNextInstructionBanner";
            }
            if (this.k == null) {
                str = str + " showCurrentSpeed";
            }
            if (this.l == null) {
                str = str + " showSpeedWarning";
            }
            if (this.m == null) {
                str = str + " showBottomInfoBar";
            }
            if (this.n == null) {
                str = str + " showInstructionBanner";
            }
            if (this.o == null) {
                str = str + " showWarningMessage";
            }
            if (this.p == null) {
                str = str + " navigationTheme";
            }
            if (str.isEmpty()) {
                return new b(this.f12968a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.navigation.ui.NavigationOptions.Builder
        public NavigationOptions.Builder isUsingInternalMap(Boolean bool) {
            Objects.requireNonNull(bool, "Null isUsingInternalMap");
            this.e = bool;
            return this;
        }

        @Override // com.mappls.sdk.navigation.ui.NavigationOptions.Builder
        public NavigationOptions.Builder mapplsMapDarkStyle(String str) {
            Objects.requireNonNull(str, "Null mapplsMapDarkStyle");
            this.d = str;
            return this;
        }

        @Override // com.mappls.sdk.navigation.ui.NavigationOptions.Builder
        public NavigationOptions.Builder mapplsMapLightStyle(String str) {
            Objects.requireNonNull(str, "Null mapplsMapLightStyle");
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.navigation.ui.NavigationOptions.Builder
        public NavigationOptions.Builder navigationDarkTheme(Integer num) {
            Objects.requireNonNull(num, "Null navigationDarkTheme");
            this.b = num;
            return this;
        }

        @Override // com.mappls.sdk.navigation.ui.NavigationOptions.Builder
        public NavigationOptions.Builder navigationLightTheme(Integer num) {
            Objects.requireNonNull(num, "Null navigationLightTheme");
            this.f12968a = num;
            return this;
        }

        @Override // com.mappls.sdk.navigation.ui.NavigationOptions.Builder
        public NavigationOptions.Builder navigationTheme(Integer num) {
            Objects.requireNonNull(num, "Null navigationTheme");
            this.p = num;
            return this;
        }

        @Override // com.mappls.sdk.navigation.ui.NavigationOptions.Builder
        public NavigationOptions.Builder showBottomInfoBar(Boolean bool) {
            Objects.requireNonNull(bool, "Null showBottomInfoBar");
            this.m = bool;
            return this;
        }

        @Override // com.mappls.sdk.navigation.ui.NavigationOptions.Builder
        public NavigationOptions.Builder showCurrentSpeed(Boolean bool) {
            Objects.requireNonNull(bool, "Null showCurrentSpeed");
            this.k = bool;
            return this;
        }

        @Override // com.mappls.sdk.navigation.ui.NavigationOptions.Builder
        public NavigationOptions.Builder showDayNightOption(Boolean bool) {
            Objects.requireNonNull(bool, "Null showDayNightOption");
            this.f = bool;
            return this;
        }

        @Override // com.mappls.sdk.navigation.ui.NavigationOptions.Builder
        public NavigationOptions.Builder showInstructionBanner(Boolean bool) {
            Objects.requireNonNull(bool, "Null showInstructionBanner");
            this.n = bool;
            return this;
        }

        @Override // com.mappls.sdk.navigation.ui.NavigationOptions.Builder
        public NavigationOptions.Builder showNavigationSettingsOption(Boolean bool) {
            Objects.requireNonNull(bool, "Null showNavigationSettingsOption");
            this.h = bool;
            return this;
        }

        @Override // com.mappls.sdk.navigation.ui.NavigationOptions.Builder
        public NavigationOptions.Builder showNextInstructionBanner(Boolean bool) {
            Objects.requireNonNull(bool, "Null showNextInstructionBanner");
            this.j = bool;
            return this;
        }

        @Override // com.mappls.sdk.navigation.ui.NavigationOptions.Builder
        public NavigationOptions.Builder showSearchDuringNavigationOption(Boolean bool) {
            Objects.requireNonNull(bool, "Null showSearchDuringNavigationOption");
            this.i = bool;
            return this;
        }

        @Override // com.mappls.sdk.navigation.ui.NavigationOptions.Builder
        public NavigationOptions.Builder showSpeedWarning(Boolean bool) {
            Objects.requireNonNull(bool, "Null showSpeedWarning");
            this.l = bool;
            return this;
        }

        @Override // com.mappls.sdk.navigation.ui.NavigationOptions.Builder
        public NavigationOptions.Builder showTrafficOption(Boolean bool) {
            Objects.requireNonNull(bool, "Null showTrafficOption");
            this.g = bool;
            return this;
        }

        @Override // com.mappls.sdk.navigation.ui.NavigationOptions.Builder
        public NavigationOptions.Builder showWarningMessage(Boolean bool) {
            Objects.requireNonNull(bool, "Null showWarningMessage");
            this.o = bool;
            return this;
        }
    }

    public a(Integer num, Integer num2, String str, String str2, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, Boolean bool6, Boolean bool7, Boolean bool8, Boolean bool9, Boolean bool10, Boolean bool11, Integer num3) {
        Objects.requireNonNull(num, "Null navigationLightTheme");
        this.h = num;
        Objects.requireNonNull(num2, "Null navigationDarkTheme");
        this.i = num2;
        Objects.requireNonNull(str, "Null mapplsMapLightStyle");
        this.j = str;
        Objects.requireNonNull(str2, "Null mapplsMapDarkStyle");
        this.k = str2;
        Objects.requireNonNull(bool, "Null isUsingInternalMap");
        this.l = bool;
        Objects.requireNonNull(bool2, "Null showDayNightOption");
        this.m = bool2;
        Objects.requireNonNull(bool3, "Null showTrafficOption");
        this.n = bool3;
        Objects.requireNonNull(bool4, "Null showNavigationSettingsOption");
        this.o = bool4;
        Objects.requireNonNull(bool5, "Null showSearchDuringNavigationOption");
        this.p = bool5;
        Objects.requireNonNull(bool6, "Null showNextInstructionBanner");
        this.q = bool6;
        Objects.requireNonNull(bool7, "Null showCurrentSpeed");
        this.r = bool7;
        Objects.requireNonNull(bool8, "Null showSpeedWarning");
        this.s = bool8;
        Objects.requireNonNull(bool9, "Null showBottomInfoBar");
        this.t = bool9;
        Objects.requireNonNull(bool10, "Null showInstructionBanner");
        this.u = bool10;
        Objects.requireNonNull(bool11, "Null showWarningMessage");
        this.v = bool11;
        Objects.requireNonNull(num3, "Null navigationTheme");
        this.w = num3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof NavigationOptions) {
            NavigationOptions navigationOptions = (NavigationOptions) obj;
            return this.h.equals(navigationOptions.navigationLightTheme()) && this.i.equals(navigationOptions.navigationDarkTheme()) && this.j.equals(navigationOptions.mapplsMapLightStyle()) && this.k.equals(navigationOptions.mapplsMapDarkStyle()) && this.l.equals(navigationOptions.isUsingInternalMap()) && this.m.equals(navigationOptions.showDayNightOption()) && this.n.equals(navigationOptions.showTrafficOption()) && this.o.equals(navigationOptions.showNavigationSettingsOption()) && this.p.equals(navigationOptions.showSearchDuringNavigationOption()) && this.q.equals(navigationOptions.showNextInstructionBanner()) && this.r.equals(navigationOptions.showCurrentSpeed()) && this.s.equals(navigationOptions.showSpeedWarning()) && this.t.equals(navigationOptions.showBottomInfoBar()) && this.u.equals(navigationOptions.showInstructionBanner()) && this.v.equals(navigationOptions.showWarningMessage()) && this.w.equals(navigationOptions.navigationTheme());
        }
        return false;
    }

    public int hashCode() {
        return ((((((((((((((((((((((((((((((this.h.hashCode() ^ 1000003) * 1000003) ^ this.i.hashCode()) * 1000003) ^ this.j.hashCode()) * 1000003) ^ this.k.hashCode()) * 1000003) ^ this.l.hashCode()) * 1000003) ^ this.m.hashCode()) * 1000003) ^ this.n.hashCode()) * 1000003) ^ this.o.hashCode()) * 1000003) ^ this.p.hashCode()) * 1000003) ^ this.q.hashCode()) * 1000003) ^ this.r.hashCode()) * 1000003) ^ this.s.hashCode()) * 1000003) ^ this.t.hashCode()) * 1000003) ^ this.u.hashCode()) * 1000003) ^ this.v.hashCode()) * 1000003) ^ this.w.hashCode();
    }

    @Override // com.mappls.sdk.navigation.ui.NavigationOptions
    @NonNull
    public Boolean isUsingInternalMap() {
        return this.l;
    }

    @Override // com.mappls.sdk.navigation.ui.NavigationOptions
    @NonNull
    public String mapplsMapDarkStyle() {
        return this.k;
    }

    @Override // com.mappls.sdk.navigation.ui.NavigationOptions
    @NonNull
    public String mapplsMapLightStyle() {
        return this.j;
    }

    @Override // com.mappls.sdk.navigation.ui.NavigationOptions
    @NonNull
    @StyleRes
    public Integer navigationDarkTheme() {
        return this.i;
    }

    @Override // com.mappls.sdk.navigation.ui.NavigationOptions
    @NonNull
    @StyleRes
    public Integer navigationLightTheme() {
        return this.h;
    }

    @Override // com.mappls.sdk.navigation.ui.NavigationOptions
    @NonNull
    public Integer navigationTheme() {
        return this.w;
    }

    @Override // com.mappls.sdk.navigation.ui.NavigationOptions
    @NonNull
    public Boolean showBottomInfoBar() {
        return this.t;
    }

    @Override // com.mappls.sdk.navigation.ui.NavigationOptions
    @NonNull
    public Boolean showCurrentSpeed() {
        return this.r;
    }

    @Override // com.mappls.sdk.navigation.ui.NavigationOptions
    @NonNull
    public Boolean showDayNightOption() {
        return this.m;
    }

    @Override // com.mappls.sdk.navigation.ui.NavigationOptions
    @NonNull
    public Boolean showInstructionBanner() {
        return this.u;
    }

    @Override // com.mappls.sdk.navigation.ui.NavigationOptions
    @NonNull
    public Boolean showNavigationSettingsOption() {
        return this.o;
    }

    @Override // com.mappls.sdk.navigation.ui.NavigationOptions
    @NonNull
    public Boolean showNextInstructionBanner() {
        return this.q;
    }

    @Override // com.mappls.sdk.navigation.ui.NavigationOptions
    @NonNull
    public Boolean showSearchDuringNavigationOption() {
        return this.p;
    }

    @Override // com.mappls.sdk.navigation.ui.NavigationOptions
    @NonNull
    public Boolean showSpeedWarning() {
        return this.s;
    }

    @Override // com.mappls.sdk.navigation.ui.NavigationOptions
    @NonNull
    public Boolean showTrafficOption() {
        return this.n;
    }

    @Override // com.mappls.sdk.navigation.ui.NavigationOptions
    @NonNull
    public Boolean showWarningMessage() {
        return this.v;
    }

    public String toString() {
        return "NavigationOptions{navigationLightTheme=" + this.h + ", navigationDarkTheme=" + this.i + ", mapplsMapLightStyle=" + this.j + ", mapplsMapDarkStyle=" + this.k + ", isUsingInternalMap=" + this.l + ", showDayNightOption=" + this.m + ", showTrafficOption=" + this.n + ", showNavigationSettingsOption=" + this.o + ", showSearchDuringNavigationOption=" + this.p + ", showNextInstructionBanner=" + this.q + ", showCurrentSpeed=" + this.r + ", showSpeedWarning=" + this.s + ", showBottomInfoBar=" + this.t + ", showInstructionBanner=" + this.u + ", showWarningMessage=" + this.v + ", navigationTheme=" + this.w + "}";
    }
}
