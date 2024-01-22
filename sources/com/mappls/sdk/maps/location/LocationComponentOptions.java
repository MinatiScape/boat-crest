package com.mappls.sdk.maps.location;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.animation.Interpolator;
import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import com.mappls.sdk.maps.R;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes11.dex */
public class LocationComponentOptions implements Parcelable {
    public static final float CIRCLE_PULSING_MAX_RADIUS_DEFAULT = 35.0f;
    @Nullable
    public Integer A;
    public float B;
    public boolean C;
    public long D;
    @Nullable
    public int[] E;
    public float F;
    public float G;
    public boolean H;
    public float I;
    public float J;
    @Nullable
    public RectF K;
    public String L;
    public String M;
    public float N;
    public boolean O;
    public boolean P;
    public Boolean Q;
    public Boolean R;
    public Integer S;
    public float T;
    public float U;
    public float V;
    @Nullable
    public Interpolator W;
    public float h;
    public int i;
    public int j;
    @Nullable
    public String k;
    public int l;
    @Nullable
    public String m;
    public int n;
    public int o;
    @Nullable
    public String p;
    public int q;
    @Nullable
    public String r;
    public int s;
    @Nullable
    public String t;
    public int u;
    @Nullable
    public String v;
    @Nullable
    public Integer w;
    @Nullable
    public Integer x;
    @Nullable
    public Integer y;
    @Nullable
    public Integer z;
    public static final int[] X = {0, 0, 0, 0};
    public static final Parcelable.Creator<LocationComponentOptions> CREATOR = new a();

    /* loaded from: classes11.dex */
    public static class Builder {
        public Boolean A;
        public Float B;
        public Float C;
        public RectF D;
        public String E;
        public String F;
        public Float G;
        public Boolean H;
        public Boolean I;
        public Boolean J;
        public Boolean K;
        public int L;
        public float M;
        public float N;
        public float O;
        @Nullable
        public Interpolator P;

        /* renamed from: a  reason: collision with root package name */
        public Float f12748a;
        public Integer b;
        public Integer c;
        @Nullable
        public String d;
        public Integer e;
        @Nullable
        public String f;
        public Integer g;
        public Integer h;
        @Nullable
        public String i;
        public Integer j;
        @Nullable
        public String k;
        public Integer l;
        @Nullable
        public String m;
        public Integer n;
        @Nullable
        public String o;
        @Nullable
        public Integer p;
        @Nullable
        public Integer q;
        @Nullable
        public Integer r;
        @Nullable
        public Integer s;
        @Nullable
        public Integer t;
        public Float u;
        public Boolean v;
        public Long w;
        @Nullable
        public int[] x;
        public Float y;
        public Float z;

        public /* synthetic */ Builder(LocationComponentOptions locationComponentOptions, a aVar) {
            this(locationComponentOptions);
        }

        @NonNull
        public Builder accuracyAlpha(float f) {
            this.f12748a = Float.valueOf(f);
            return this;
        }

        public Builder accuracyAnimationEnabled(boolean z) {
            this.I = Boolean.valueOf(z);
            return this;
        }

        @NonNull
        public Builder accuracyColor(int i) {
            this.b = Integer.valueOf(i);
            return this;
        }

        @NonNull
        public Builder backgroundDrawable(int i) {
            this.l = Integer.valueOf(i);
            return this;
        }

        @NonNull
        public Builder backgroundDrawableStale(int i) {
            this.c = Integer.valueOf(i);
            return this;
        }

        @NonNull
        public Builder backgroundName(@Nullable String str) {
            this.m = str;
            return this;
        }

        @NonNull
        public Builder backgroundStaleName(@Nullable String str) {
            this.d = str;
            return this;
        }

        @NonNull
        public Builder backgroundStaleTintColor(@Nullable Integer num) {
            this.t = num;
            return this;
        }

        @NonNull
        public Builder backgroundTintColor(@Nullable Integer num) {
            this.r = num;
            return this;
        }

        @NonNull
        public Builder bearingDrawable(int i) {
            this.n = Integer.valueOf(i);
            return this;
        }

        @NonNull
        public Builder bearingName(@Nullable String str) {
            this.o = str;
            return this;
        }

        @NonNull
        public Builder bearingTintColor(@Nullable Integer num) {
            this.p = num;
            return this;
        }

        @NonNull
        public LocationComponentOptions build() {
            LocationComponentOptions h = h();
            if (h.accuracyAlpha() >= 0.0f && h.accuracyAlpha() <= 1.0f) {
                if (h.elevation() >= 0.0f) {
                    if (h.layerAbove() != null && h.layerBelow() != null) {
                        throw new IllegalArgumentException("You cannot set both layerAbove and layerBelow options. Choose one or the other.");
                    }
                    if (h.pulseEnabled() == null) {
                        String str = "";
                        if (h.pulseFadeEnabled() != null) {
                            str = " pulseFadeEnabled";
                        }
                        if (h.pulseColor() != null) {
                            str = str + " pulseColor";
                        }
                        if (h.pulseSingleDuration() > 0.0f) {
                            str = str + " pulseSingleDuration";
                        }
                        if (h.pulseMaxRadius() > 0.0f) {
                            str = str + " pulseMaxRadius";
                        }
                        if (h.pulseAlpha() >= 0.0f && h.pulseAlpha() <= 1.0f) {
                            str = str + " pulseAlpha";
                        }
                        if (h.pulseInterpolator() != null) {
                            str = str + " pulseInterpolator";
                        }
                        if (!str.isEmpty()) {
                            throw new IllegalStateException("You've set up the following pulsing circle options but have not enabled the pulsing circle via the LocationComponentOptions builder:" + str + ". Enable the pulsing circle if you're going to set pulsing options.");
                        }
                    }
                    return h;
                }
                throw new IllegalArgumentException("Invalid shadow size " + h.elevation() + ". Must be >= 0");
            }
            throw new IllegalArgumentException("Accuracy alpha value must be between 0.0 and 1.0.");
        }

        public Builder compassAnimationEnabled(Boolean bool) {
            this.H = bool;
            return this;
        }

        @NonNull
        public Builder elevation(float f) {
            this.u = Float.valueOf(f);
            return this;
        }

        @NonNull
        public Builder enableStaleState(boolean z) {
            this.v = Boolean.valueOf(z);
            return this;
        }

        @NonNull
        public Builder foregroundDrawable(int i) {
            this.j = Integer.valueOf(i);
            return this;
        }

        @NonNull
        public Builder foregroundDrawableStale(int i) {
            this.e = Integer.valueOf(i);
            return this;
        }

        @NonNull
        public Builder foregroundName(@Nullable String str) {
            this.k = str;
            return this;
        }

        @NonNull
        public Builder foregroundStaleName(@Nullable String str) {
            this.f = str;
            return this;
        }

        @NonNull
        public Builder foregroundStaleTintColor(@Nullable Integer num) {
            this.s = num;
            return this;
        }

        @NonNull
        public Builder foregroundTintColor(@Nullable Integer num) {
            this.q = num;
            return this;
        }

        @NonNull
        public Builder gpsDrawable(int i) {
            this.g = Integer.valueOf(i);
            return this;
        }

        @NonNull
        public Builder gpsName(@Nullable String str) {
            this.i = str;
            return this;
        }

        @NonNull
        public Builder gpsStaleDrawable(int i) {
            this.h = Integer.valueOf(i);
            return this;
        }

        @Nullable
        public LocationComponentOptions h() {
            String str = "";
            if (this.f12748a == null) {
                str = " accuracyAlpha";
            }
            if (this.b == null) {
                str = str + " accuracyColor";
            }
            if (this.c == null) {
                str = str + " backgroundDrawableStale";
            }
            if (this.e == null) {
                str = str + " foregroundDrawableStale";
            }
            if (this.g == null) {
                str = str + " gpsDrawable";
            }
            if (this.h == null) {
                str = str + " gpsStaleDrawable";
            }
            if (this.j == null) {
                str = str + " foregroundDrawable";
            }
            if (this.l == null) {
                str = str + " backgroundDrawable";
            }
            if (this.n == null) {
                str = str + " bearingDrawable";
            }
            if (this.u == null) {
                str = str + " elevation";
            }
            if (this.v == null) {
                str = str + " enableStaleState";
            }
            if (this.w == null) {
                str = str + " staleStateTimeout";
            }
            if (this.x == null) {
                str = str + " padding";
            }
            if (this.y == null) {
                str = str + " maxZoomIconScale";
            }
            if (this.z == null) {
                str = str + " minZoomIconScale";
            }
            if (this.A == null) {
                str = str + " trackingGesturesManagement";
            }
            if (this.B == null) {
                str = str + " trackingInitialMoveThreshold";
            }
            if (this.C == null) {
                str = str + " trackingMultiFingerMoveThreshold";
            }
            if (this.G == null) {
                str = str + " trackingAnimationDurationMultiplier";
            }
            if (str.isEmpty()) {
                return new LocationComponentOptions(this.f12748a.floatValue(), this.b.intValue(), this.c.intValue(), this.d, this.e.intValue(), this.f, this.g.intValue(), this.h.intValue(), this.i, this.j.intValue(), this.k, this.l.intValue(), this.m, this.n.intValue(), this.o, this.p, this.q, this.r, this.s, this.t, this.u.floatValue(), this.v.booleanValue(), this.w.longValue(), this.x, this.y.floatValue(), this.z.floatValue(), this.A.booleanValue(), this.B.floatValue(), this.C.floatValue(), this.D, this.E, this.F, this.G.floatValue(), this.H.booleanValue(), this.I.booleanValue(), this.J, this.K, Integer.valueOf(this.L), this.M, this.N, this.O, this.P);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @NonNull
        public Builder layerAbove(String str) {
            this.E = str;
            return this;
        }

        @NonNull
        public Builder layerBelow(String str) {
            this.F = str;
            return this;
        }

        @NonNull
        public Builder maxZoomIconScale(float f) {
            this.y = Float.valueOf(f);
            return this;
        }

        @NonNull
        public Builder minZoomIconScale(float f) {
            this.z = Float.valueOf(f);
            return this;
        }

        @NonNull
        @Deprecated
        public Builder padding(@Nullable int[] iArr) {
            Objects.requireNonNull(iArr, "Null padding");
            this.x = iArr;
            return this;
        }

        public Builder pulseAlpha(float f) {
            this.O = f;
            return this;
        }

        public Builder pulseColor(@ColorInt int i) {
            this.L = i;
            return this;
        }

        public Builder pulseEnabled(boolean z) {
            this.J = Boolean.valueOf(z);
            return this;
        }

        public Builder pulseFadeEnabled(boolean z) {
            this.K = Boolean.valueOf(z);
            return this;
        }

        public Builder pulseInterpolator(Interpolator interpolator) {
            this.P = interpolator;
            return this;
        }

        public Builder pulseMaxRadius(float f) {
            this.N = f;
            return this;
        }

        public Builder pulseSingleDuration(float f) {
            this.M = f;
            return this;
        }

        @NonNull
        public Builder staleStateTimeout(long j) {
            this.w = Long.valueOf(j);
            return this;
        }

        @NonNull
        public Builder trackingAnimationDurationMultiplier(float f) {
            this.G = Float.valueOf(f);
            return this;
        }

        @NonNull
        public Builder trackingGesturesManagement(boolean z) {
            this.A = Boolean.valueOf(z);
            return this;
        }

        @NonNull
        public Builder trackingInitialMoveThreshold(float f) {
            this.B = Float.valueOf(f);
            return this;
        }

        @NonNull
        public Builder trackingMultiFingerMoveThreshold(float f) {
            this.C = Float.valueOf(f);
            return this;
        }

        @NonNull
        public Builder trackingMultiFingerProtectedMoveArea(@Nullable RectF rectF) {
            this.D = rectF;
            return this;
        }

        public Builder() {
        }

        public Builder(LocationComponentOptions locationComponentOptions) {
            this.f12748a = Float.valueOf(locationComponentOptions.accuracyAlpha());
            this.b = Integer.valueOf(locationComponentOptions.accuracyColor());
            this.c = Integer.valueOf(locationComponentOptions.backgroundDrawableStale());
            this.d = locationComponentOptions.backgroundStaleName();
            this.e = Integer.valueOf(locationComponentOptions.foregroundDrawableStale());
            this.f = locationComponentOptions.foregroundStaleName();
            this.g = Integer.valueOf(locationComponentOptions.gpsDrawable());
            this.h = Integer.valueOf(locationComponentOptions.gpsStaleDrawable());
            this.i = locationComponentOptions.gpsName();
            this.j = Integer.valueOf(locationComponentOptions.foregroundDrawable());
            this.k = locationComponentOptions.foregroundName();
            this.l = Integer.valueOf(locationComponentOptions.backgroundDrawable());
            this.m = locationComponentOptions.backgroundName();
            this.n = Integer.valueOf(locationComponentOptions.bearingDrawable());
            this.o = locationComponentOptions.bearingName();
            this.p = locationComponentOptions.bearingTintColor();
            this.q = locationComponentOptions.foregroundTintColor();
            this.r = locationComponentOptions.backgroundTintColor();
            this.s = locationComponentOptions.foregroundStaleTintColor();
            this.t = locationComponentOptions.backgroundStaleTintColor();
            this.u = Float.valueOf(locationComponentOptions.elevation());
            this.v = Boolean.valueOf(locationComponentOptions.enableStaleState());
            this.w = Long.valueOf(locationComponentOptions.staleStateTimeout());
            this.x = locationComponentOptions.padding();
            this.y = Float.valueOf(locationComponentOptions.maxZoomIconScale());
            this.z = Float.valueOf(locationComponentOptions.minZoomIconScale());
            this.A = Boolean.valueOf(locationComponentOptions.trackingGesturesManagement());
            this.B = Float.valueOf(locationComponentOptions.trackingInitialMoveThreshold());
            this.C = Float.valueOf(locationComponentOptions.trackingMultiFingerMoveThreshold());
            this.D = locationComponentOptions.trackingMultiFingerProtectedMoveArea();
            this.E = locationComponentOptions.layerAbove();
            this.F = locationComponentOptions.layerBelow();
            this.G = Float.valueOf(locationComponentOptions.trackingAnimationDurationMultiplier());
            this.H = Boolean.valueOf(locationComponentOptions.compassAnimationEnabled());
            this.I = Boolean.valueOf(locationComponentOptions.accuracyAnimationEnabled());
            this.J = locationComponentOptions.Q;
            this.K = locationComponentOptions.R;
            this.L = locationComponentOptions.S.intValue();
            this.M = locationComponentOptions.T;
            this.N = locationComponentOptions.U;
            this.O = locationComponentOptions.V;
            this.P = locationComponentOptions.W;
        }
    }

    /* loaded from: classes11.dex */
    public class a implements Parcelable.Creator<LocationComponentOptions> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public LocationComponentOptions createFromParcel(Parcel parcel) {
            return new LocationComponentOptions(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public LocationComponentOptions[] newArray(int i) {
            return new LocationComponentOptions[i];
        }
    }

    public LocationComponentOptions(float f, int i, int i2, @Nullable String str, int i3, @Nullable String str2, int i4, int i5, @Nullable String str3, int i6, @Nullable String str4, int i7, @Nullable String str5, int i8, @Nullable String str6, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @Nullable Integer num4, @Nullable Integer num5, float f2, boolean z, long j, @Nullable int[] iArr, float f3, float f4, boolean z2, float f5, float f6, RectF rectF, String str7, String str8, float f7, boolean z3, boolean z4, Boolean bool, Boolean bool2, Integer num6, float f8, float f9, float f10, @Nullable Interpolator interpolator) {
        this.h = f;
        this.i = i;
        this.j = i2;
        this.k = str;
        this.l = i3;
        this.m = str2;
        this.n = i4;
        this.o = i5;
        this.p = str3;
        this.q = i6;
        this.r = str4;
        this.s = i7;
        this.t = str5;
        this.u = i8;
        this.v = str6;
        this.w = num;
        this.x = num2;
        this.y = num3;
        this.z = num4;
        this.A = num5;
        this.B = f2;
        this.C = z;
        this.D = j;
        Objects.requireNonNull(iArr, "Null padding");
        this.E = iArr;
        this.F = f3;
        this.G = f4;
        this.H = z2;
        this.I = f5;
        this.J = f6;
        this.K = rectF;
        this.L = str7;
        this.M = str8;
        this.N = f7;
        this.O = z3;
        this.P = z4;
        this.Q = bool;
        this.R = bool2;
        this.S = num6;
        this.T = f8;
        this.U = f9;
        this.V = f10;
        this.W = interpolator;
    }

    @NonNull
    public static Builder builder(@NonNull Context context) {
        return createFromAttributes(context, R.style.mappls_maps_LocationComponent).toBuilder();
    }

    @NonNull
    public static LocationComponentOptions createFromAttributes(@NonNull Context context, @StyleRes int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, R.styleable.mappls_maps_LocationComponent);
        Builder padding = new Builder().enableStaleState(true).staleStateTimeout(30000L).maxZoomIconScale(1.0f).minZoomIconScale(0.6f).padding(X);
        padding.foregroundDrawable(obtainStyledAttributes.getResourceId(R.styleable.mappls_maps_LocationComponent_mappls_maps_foregroundDrawable, -1));
        int i2 = R.styleable.mappls_maps_LocationComponent_mappls_maps_foregroundTintColor;
        if (obtainStyledAttributes.hasValue(i2)) {
            padding.foregroundTintColor(Integer.valueOf(obtainStyledAttributes.getColor(i2, -1)));
        }
        padding.backgroundDrawable(obtainStyledAttributes.getResourceId(R.styleable.mappls_maps_LocationComponent_mappls_maps_backgroundDrawable, -1));
        int i3 = R.styleable.mappls_maps_LocationComponent_mappls_maps_backgroundTintColor;
        if (obtainStyledAttributes.hasValue(i3)) {
            padding.backgroundTintColor(Integer.valueOf(obtainStyledAttributes.getColor(i3, -1)));
        }
        padding.foregroundDrawableStale(obtainStyledAttributes.getResourceId(R.styleable.mappls_maps_LocationComponent_mappls_maps_foregroundDrawableStale, -1));
        int i4 = R.styleable.mappls_maps_LocationComponent_mappls_maps_foregroundStaleTintColor;
        if (obtainStyledAttributes.hasValue(i4)) {
            padding.foregroundStaleTintColor(Integer.valueOf(obtainStyledAttributes.getColor(i4, -1)));
        }
        padding.backgroundDrawableStale(obtainStyledAttributes.getResourceId(R.styleable.mappls_maps_LocationComponent_mappls_maps_backgroundDrawableStale, -1));
        int i5 = R.styleable.mappls_maps_LocationComponent_mappls_maps_backgroundStaleTintColor;
        if (obtainStyledAttributes.hasValue(i5)) {
            padding.backgroundStaleTintColor(Integer.valueOf(obtainStyledAttributes.getColor(i5, -1)));
        }
        padding.bearingDrawable(obtainStyledAttributes.getResourceId(R.styleable.mappls_maps_LocationComponent_mappls_maps_bearingDrawable, -1));
        int i6 = R.styleable.mappls_maps_LocationComponent_mappls_maps_bearingTintColor;
        if (obtainStyledAttributes.hasValue(i6)) {
            padding.bearingTintColor(Integer.valueOf(obtainStyledAttributes.getColor(i6, -1)));
        }
        int i7 = R.styleable.mappls_maps_LocationComponent_mappls_maps_enableStaleState;
        if (obtainStyledAttributes.hasValue(i7)) {
            padding.enableStaleState(obtainStyledAttributes.getBoolean(i7, true));
        }
        int i8 = R.styleable.mappls_maps_LocationComponent_mappls_maps_staleStateTimeout;
        if (obtainStyledAttributes.hasValue(i8)) {
            padding.staleStateTimeout(obtainStyledAttributes.getInteger(i8, 30000));
        }
        padding.gpsDrawable(obtainStyledAttributes.getResourceId(R.styleable.mappls_maps_LocationComponent_mappls_maps_gpsDrawable, -1));
        padding.gpsStaleDrawable(obtainStyledAttributes.getResourceId(R.styleable.mappls_maps_LocationComponent_mappls_maps_gpsStaleDrawable, -1));
        float dimension = obtainStyledAttributes.getDimension(R.styleable.mappls_maps_LocationComponent_mappls_maps_elevation, 0.0f);
        padding.accuracyColor(obtainStyledAttributes.getColor(R.styleable.mappls_maps_LocationComponent_mappls_maps_accuracyColor, -1));
        padding.accuracyAlpha(obtainStyledAttributes.getFloat(R.styleable.mappls_maps_LocationComponent_mappls_maps_accuracyAlpha, 0.15f));
        padding.elevation(dimension);
        padding.trackingGesturesManagement(obtainStyledAttributes.getBoolean(R.styleable.mappls_maps_LocationComponent_mappls_maps_trackingGesturesManagement, false));
        padding.trackingInitialMoveThreshold(obtainStyledAttributes.getDimension(R.styleable.mappls_maps_LocationComponent_mappls_maps_trackingInitialMoveThreshold, context.getResources().getDimension(R.dimen.mappls_maps_locationComponentTrackingInitialMoveThreshold)));
        padding.trackingMultiFingerMoveThreshold(obtainStyledAttributes.getDimension(R.styleable.mappls_maps_LocationComponent_mappls_maps_trackingMultiFingerMoveThreshold, context.getResources().getDimension(R.dimen.mappls_maps_locationComponentTrackingMultiFingerMoveThreshold)));
        padding.padding(new int[]{obtainStyledAttributes.getInt(R.styleable.mappls_maps_LocationComponent_mappls_maps_iconPaddingLeft, 0), obtainStyledAttributes.getInt(R.styleable.mappls_maps_LocationComponent_mappls_maps_iconPaddingTop, 0), obtainStyledAttributes.getInt(R.styleable.mappls_maps_LocationComponent_mappls_maps_iconPaddingRight, 0), obtainStyledAttributes.getInt(R.styleable.mappls_maps_LocationComponent_mappls_maps_iconPaddingBottom, 0)});
        padding.layerAbove(obtainStyledAttributes.getString(R.styleable.mappls_maps_LocationComponent_mappls_maps_layer_above));
        padding.layerBelow(obtainStyledAttributes.getString(R.styleable.mappls_maps_LocationComponent_mappls_maps_layer_below));
        float f = obtainStyledAttributes.getFloat(R.styleable.mappls_maps_LocationComponent_mappls_maps_minZoomIconScale, 0.6f);
        float f2 = obtainStyledAttributes.getFloat(R.styleable.mappls_maps_LocationComponent_mappls_maps_maxZoomIconScale, 1.0f);
        padding.minZoomIconScale(f);
        padding.maxZoomIconScale(f2);
        padding.trackingAnimationDurationMultiplier(obtainStyledAttributes.getFloat(R.styleable.mappls_maps_LocationComponent_mappls_maps_trackingAnimationDurationMultiplier, 1.1f));
        padding.H = Boolean.valueOf(obtainStyledAttributes.getBoolean(R.styleable.mappls_maps_LocationComponent_mappls_maps_compassAnimationEnabled, true));
        padding.I = Boolean.valueOf(obtainStyledAttributes.getBoolean(R.styleable.mappls_maps_LocationComponent_mappls_maps_accuracyAnimationEnabled, true));
        padding.J = Boolean.valueOf(obtainStyledAttributes.getBoolean(R.styleable.mappls_maps_LocationComponent_mappls_maps_pulsingLocationCircleEnabled, false));
        padding.K = Boolean.valueOf(obtainStyledAttributes.getBoolean(R.styleable.mappls_maps_LocationComponent_mappls_maps_pulsingLocationCircleFadeEnabled, true));
        int i9 = R.styleable.mappls_maps_LocationComponent_mappls_maps_pulsingLocationCircleColor;
        if (obtainStyledAttributes.hasValue(i9)) {
            padding.pulseColor(obtainStyledAttributes.getColor(i9, -1));
        }
        padding.M = obtainStyledAttributes.getFloat(R.styleable.mappls_maps_LocationComponent_mappls_maps_pulsingLocationCircleDuration, 2300.0f);
        padding.N = obtainStyledAttributes.getFloat(R.styleable.mappls_maps_LocationComponent_mappls_maps_pulsingLocationCircleRadius, 35.0f);
        padding.O = obtainStyledAttributes.getFloat(R.styleable.mappls_maps_LocationComponent_mappls_maps_pulsingLocationCircleAlpha, 1.0f);
        obtainStyledAttributes.recycle();
        return padding.build();
    }

    public float accuracyAlpha() {
        return this.h;
    }

    public boolean accuracyAnimationEnabled() {
        return this.P;
    }

    @ColorInt
    public int accuracyColor() {
        return this.i;
    }

    @DrawableRes
    public int backgroundDrawable() {
        return this.s;
    }

    @DrawableRes
    public int backgroundDrawableStale() {
        return this.j;
    }

    @Nullable
    public String backgroundName() {
        return this.t;
    }

    @Nullable
    public String backgroundStaleName() {
        return this.k;
    }

    @Nullable
    @ColorInt
    public Integer backgroundStaleTintColor() {
        return this.A;
    }

    @Nullable
    @ColorInt
    public Integer backgroundTintColor() {
        return this.y;
    }

    @DrawableRes
    public int bearingDrawable() {
        return this.u;
    }

    @Nullable
    public String bearingName() {
        return this.v;
    }

    @Nullable
    @ColorInt
    public Integer bearingTintColor() {
        return this.w;
    }

    public boolean compassAnimationEnabled() {
        return this.O;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Dimension
    public float elevation() {
        return this.B;
    }

    public boolean enableStaleState() {
        return this.C;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LocationComponentOptions locationComponentOptions = (LocationComponentOptions) obj;
        if (Float.compare(locationComponentOptions.h, this.h) == 0 && this.i == locationComponentOptions.i && this.j == locationComponentOptions.j && this.l == locationComponentOptions.l && this.n == locationComponentOptions.n && this.o == locationComponentOptions.o && this.q == locationComponentOptions.q && this.s == locationComponentOptions.s && this.u == locationComponentOptions.u && Float.compare(locationComponentOptions.B, this.B) == 0 && this.C == locationComponentOptions.C && this.D == locationComponentOptions.D && Float.compare(locationComponentOptions.F, this.F) == 0 && Float.compare(locationComponentOptions.G, this.G) == 0 && this.H == locationComponentOptions.H && Float.compare(locationComponentOptions.I, this.I) == 0 && Float.compare(locationComponentOptions.J, this.J) == 0 && Float.compare(locationComponentOptions.N, this.N) == 0) {
            RectF rectF = this.K;
            if (rectF == null ? locationComponentOptions.K == null : rectF.equals(locationComponentOptions.K)) {
                if (this.O == locationComponentOptions.O && this.P == locationComponentOptions.P) {
                    String str = this.k;
                    if (str == null ? locationComponentOptions.k == null : str.equals(locationComponentOptions.k)) {
                        String str2 = this.m;
                        if (str2 == null ? locationComponentOptions.m == null : str2.equals(locationComponentOptions.m)) {
                            String str3 = this.p;
                            if (str3 == null ? locationComponentOptions.p == null : str3.equals(locationComponentOptions.p)) {
                                String str4 = this.r;
                                if (str4 == null ? locationComponentOptions.r == null : str4.equals(locationComponentOptions.r)) {
                                    String str5 = this.t;
                                    if (str5 == null ? locationComponentOptions.t == null : str5.equals(locationComponentOptions.t)) {
                                        String str6 = this.v;
                                        if (str6 == null ? locationComponentOptions.v == null : str6.equals(locationComponentOptions.v)) {
                                            Integer num = this.w;
                                            if (num == null ? locationComponentOptions.w == null : num.equals(locationComponentOptions.w)) {
                                                Integer num2 = this.x;
                                                if (num2 == null ? locationComponentOptions.x == null : num2.equals(locationComponentOptions.x)) {
                                                    Integer num3 = this.y;
                                                    if (num3 == null ? locationComponentOptions.y == null : num3.equals(locationComponentOptions.y)) {
                                                        Integer num4 = this.z;
                                                        if (num4 == null ? locationComponentOptions.z == null : num4.equals(locationComponentOptions.z)) {
                                                            Integer num5 = this.A;
                                                            if (num5 == null ? locationComponentOptions.A == null : num5.equals(locationComponentOptions.A)) {
                                                                if (Arrays.equals(this.E, locationComponentOptions.E)) {
                                                                    String str7 = this.L;
                                                                    if (str7 == null ? locationComponentOptions.L == null : str7.equals(locationComponentOptions.L)) {
                                                                        if (this.Q == locationComponentOptions.Q && this.R == locationComponentOptions.R) {
                                                                            Integer num6 = this.S;
                                                                            if (num6 == null ? locationComponentOptions.pulseColor() == null : num6.equals(locationComponentOptions.S)) {
                                                                                if (Float.compare(locationComponentOptions.T, this.T) == 0 && Float.compare(locationComponentOptions.U, this.U) == 0 && Float.compare(locationComponentOptions.V, this.V) == 0) {
                                                                                    String str8 = this.M;
                                                                                    String str9 = locationComponentOptions.M;
                                                                                    return str8 != null ? str8.equals(str9) : str9 == null;
                                                                                }
                                                                                return false;
                                                                            }
                                                                            return false;
                                                                        }
                                                                        return false;
                                                                    }
                                                                    return false;
                                                                }
                                                                return false;
                                                            }
                                                            return false;
                                                        }
                                                        return false;
                                                    }
                                                    return false;
                                                }
                                                return false;
                                            }
                                            return false;
                                        }
                                        return false;
                                    }
                                    return false;
                                }
                                return false;
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @DrawableRes
    public int foregroundDrawable() {
        return this.q;
    }

    @DrawableRes
    public int foregroundDrawableStale() {
        return this.l;
    }

    @Nullable
    public String foregroundName() {
        return this.r;
    }

    @Nullable
    public String foregroundStaleName() {
        return this.m;
    }

    @Nullable
    @ColorInt
    public Integer foregroundStaleTintColor() {
        return this.z;
    }

    @Nullable
    @ColorInt
    public Integer foregroundTintColor() {
        return this.x;
    }

    @DrawableRes
    public int gpsDrawable() {
        return this.n;
    }

    @Nullable
    public String gpsName() {
        return this.p;
    }

    @DrawableRes
    public int gpsStaleDrawable() {
        return this.o;
    }

    public int hashCode() {
        float f = this.h;
        int floatToIntBits = (((((f != 0.0f ? Float.floatToIntBits(f) : 0) * 31) + this.i) * 31) + this.j) * 31;
        String str = this.k;
        int hashCode = (((floatToIntBits + (str != null ? str.hashCode() : 0)) * 31) + this.l) * 31;
        String str2 = this.m;
        int hashCode2 = (((((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.n) * 31) + this.o) * 31;
        String str3 = this.p;
        int hashCode3 = (((hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.q) * 31;
        String str4 = this.r;
        int hashCode4 = (((hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31) + this.s) * 31;
        String str5 = this.t;
        int hashCode5 = (((hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31) + this.u) * 31;
        String str6 = this.v;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        Integer num = this.w;
        int hashCode7 = (hashCode6 + (num != null ? num.hashCode() : 0)) * 31;
        Integer num2 = this.x;
        int hashCode8 = (hashCode7 + (num2 != null ? num2.hashCode() : 0)) * 31;
        Integer num3 = this.y;
        int hashCode9 = (hashCode8 + (num3 != null ? num3.hashCode() : 0)) * 31;
        Integer num4 = this.z;
        int hashCode10 = (hashCode9 + (num4 != null ? num4.hashCode() : 0)) * 31;
        Integer num5 = this.A;
        int hashCode11 = (hashCode10 + (num5 != null ? num5.hashCode() : 0)) * 31;
        float f2 = this.B;
        int floatToIntBits2 = f2 != 0.0f ? Float.floatToIntBits(f2) : 0;
        long j = this.D;
        int hashCode12 = (((((((hashCode11 + floatToIntBits2) * 31) + (this.C ? 1 : 0)) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + Arrays.hashCode(this.E)) * 31;
        float f3 = this.F;
        int floatToIntBits3 = (hashCode12 + (f3 != 0.0f ? Float.floatToIntBits(f3) : 0)) * 31;
        float f4 = this.G;
        int floatToIntBits4 = (((floatToIntBits3 + (f4 != 0.0f ? Float.floatToIntBits(f4) : 0)) * 31) + (this.H ? 1 : 0)) * 31;
        float f5 = this.I;
        int floatToIntBits5 = (floatToIntBits4 + (f5 != 0.0f ? Float.floatToIntBits(f5) : 0)) * 31;
        float f6 = this.J;
        int floatToIntBits6 = (floatToIntBits5 + (f6 != 0.0f ? Float.floatToIntBits(f6) : 0)) * 31;
        RectF rectF = this.K;
        int hashCode13 = (floatToIntBits6 + (rectF != null ? rectF.hashCode() : 0)) * 31;
        String str7 = this.L;
        int hashCode14 = (hashCode13 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.M;
        int hashCode15 = (hashCode14 + (str8 != null ? str8.hashCode() : 0)) * 31;
        float f7 = this.N;
        int floatToIntBits7 = (((((((((hashCode15 + (f7 != 0.0f ? Float.floatToIntBits(f7) : 0)) * 31) + (this.O ? 1 : 0)) * 31) + (this.P ? 1 : 0)) * 31) + (this.Q.booleanValue() ? 1 : 0)) * 31) + (this.R.booleanValue() ? 1 : 0)) * 31;
        Integer num6 = this.S;
        int hashCode16 = (floatToIntBits7 + (num6 != null ? num6.hashCode() : 0)) * 31;
        float f8 = this.T;
        int floatToIntBits8 = (hashCode16 + (f8 != 0.0f ? Float.floatToIntBits(f8) : 0)) * 31;
        float f9 = this.U;
        int floatToIntBits9 = (floatToIntBits8 + (f9 != 0.0f ? Float.floatToIntBits(f9) : 0)) * 31;
        float f10 = this.V;
        return floatToIntBits9 + (f10 != 0.0f ? Float.floatToIntBits(f10) : 0);
    }

    public String layerAbove() {
        return this.L;
    }

    public String layerBelow() {
        return this.M;
    }

    public float maxZoomIconScale() {
        return this.F;
    }

    public float minZoomIconScale() {
        return this.G;
    }

    @Nullable
    public int[] padding() {
        return this.E;
    }

    public float pulseAlpha() {
        return this.V;
    }

    public Integer pulseColor() {
        return this.S;
    }

    public Boolean pulseEnabled() {
        return this.Q;
    }

    public Boolean pulseFadeEnabled() {
        return this.R;
    }

    @Nullable
    public Interpolator pulseInterpolator() {
        return this.W;
    }

    public float pulseMaxRadius() {
        return this.U;
    }

    public float pulseSingleDuration() {
        return this.T;
    }

    public long staleStateTimeout() {
        return this.D;
    }

    @NonNull
    public Builder toBuilder() {
        return new Builder(this, null);
    }

    @NonNull
    public String toString() {
        return "LocationComponentOptions{accuracyAlpha=" + this.h + ", accuracyColor=" + this.i + ", backgroundDrawableStale=" + this.j + ", backgroundStaleName=" + this.k + ", foregroundDrawableStale=" + this.l + ", foregroundStaleName=" + this.m + ", gpsDrawable=" + this.n + ", gpsStaleDrawable=" + this.o + ", gpsName=" + this.p + ", foregroundDrawable=" + this.q + ", foregroundName=" + this.r + ", backgroundDrawable=" + this.s + ", backgroundName=" + this.t + ", bearingDrawable=" + this.u + ", bearingName=" + this.v + ", bearingTintColor=" + this.w + ", foregroundTintColor=" + this.x + ", backgroundTintColor=" + this.y + ", foregroundStaleTintColor=" + this.z + ", backgroundStaleTintColor=" + this.A + ", elevation=" + this.B + ", enableStaleState=" + this.C + ", staleStateTimeout=" + this.D + ", padding=" + Arrays.toString(this.E) + ", maxZoomIconScale=" + this.F + ", minZoomIconScale=" + this.G + ", trackingGesturesManagement=" + this.H + ", trackingInitialMoveThreshold=" + this.I + ", trackingMultiFingerMoveThreshold=" + this.J + ", trackingMultiFingerProtectedMoveArea=" + this.K + ", layerAbove=" + this.L + "layerBelow=" + this.M + "trackingAnimationDurationMultiplier=" + this.N + "pulseEnabled=" + this.Q + "pulseFadeEnabled=" + this.R + "pulseColor=" + this.S + "pulseSingleDuration=" + this.T + "pulseMaxRadius=" + this.U + "pulseAlpha=" + this.V + "}";
    }

    public float trackingAnimationDurationMultiplier() {
        return this.N;
    }

    public boolean trackingGesturesManagement() {
        return this.H;
    }

    public float trackingInitialMoveThreshold() {
        return this.I;
    }

    public float trackingMultiFingerMoveThreshold() {
        return this.J;
    }

    @Nullable
    public RectF trackingMultiFingerProtectedMoveArea() {
        return this.K;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.h);
        parcel.writeInt(this.i);
        parcel.writeInt(this.j);
        parcel.writeString(this.k);
        parcel.writeInt(this.l);
        parcel.writeString(this.m);
        parcel.writeInt(this.n);
        parcel.writeInt(this.o);
        parcel.writeString(this.p);
        parcel.writeInt(this.q);
        parcel.writeString(this.r);
        parcel.writeInt(this.s);
        parcel.writeString(this.t);
        parcel.writeInt(this.u);
        parcel.writeString(this.v);
        parcel.writeValue(this.w);
        parcel.writeValue(this.x);
        parcel.writeValue(this.y);
        parcel.writeValue(this.z);
        parcel.writeValue(this.A);
        parcel.writeFloat(this.B);
        parcel.writeByte(this.C ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.D);
        parcel.writeIntArray(this.E);
        parcel.writeFloat(this.F);
        parcel.writeFloat(this.G);
        parcel.writeByte(this.H ? (byte) 1 : (byte) 0);
        parcel.writeFloat(this.I);
        parcel.writeFloat(this.J);
        parcel.writeParcelable(this.K, i);
        parcel.writeString(this.L);
        parcel.writeString(this.M);
        parcel.writeFloat(this.N);
        parcel.writeByte(this.O ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.P ? (byte) 1 : (byte) 0);
        parcel.writeValue(this.Q);
        parcel.writeValue(this.R);
        parcel.writeValue(this.S);
        parcel.writeFloat(this.T);
        parcel.writeFloat(this.U);
        parcel.writeFloat(this.V);
    }

    public LocationComponentOptions(Parcel parcel) {
        this.h = parcel.readFloat();
        this.i = parcel.readInt();
        this.j = parcel.readInt();
        this.k = parcel.readString();
        this.l = parcel.readInt();
        this.m = parcel.readString();
        this.n = parcel.readInt();
        this.o = parcel.readInt();
        this.p = parcel.readString();
        this.q = parcel.readInt();
        this.r = parcel.readString();
        this.s = parcel.readInt();
        this.t = parcel.readString();
        this.u = parcel.readInt();
        this.v = parcel.readString();
        this.w = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.x = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.y = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.z = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.A = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.B = parcel.readFloat();
        this.C = parcel.readByte() != 0;
        this.D = parcel.readLong();
        this.E = parcel.createIntArray();
        this.F = parcel.readFloat();
        this.G = parcel.readFloat();
        this.H = parcel.readByte() != 0;
        this.I = parcel.readFloat();
        this.J = parcel.readFloat();
        this.K = (RectF) parcel.readParcelable(RectF.class.getClassLoader());
        this.L = parcel.readString();
        this.M = parcel.readString();
        this.N = parcel.readFloat();
        this.O = parcel.readByte() != 0;
        this.P = parcel.readByte() != 0;
        this.Q = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.R = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.S = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.T = parcel.readFloat();
        this.U = parcel.readFloat();
        this.V = parcel.readFloat();
    }
}
