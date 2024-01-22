package com.google.android.material.color;

import androidx.annotation.AttrRes;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import com.google.android.material.R;
/* loaded from: classes10.dex */
public class HarmonizedColorsOptions {
    @NonNull
    @ColorRes

    /* renamed from: a  reason: collision with root package name */
    public final int[] f10257a;
    @Nullable
    public final HarmonizedColorAttributes b;
    @AttrRes
    public final int c;

    /* loaded from: classes10.dex */
    public static class Builder {
        @Nullable
        public HarmonizedColorAttributes b;
        @NonNull
        @ColorRes

        /* renamed from: a  reason: collision with root package name */
        public int[] f10258a = new int[0];
        @AttrRes
        public int c = R.attr.colorPrimary;

        @NonNull
        public HarmonizedColorsOptions build() {
            return new HarmonizedColorsOptions(this);
        }

        @NonNull
        public Builder setColorAttributeToHarmonizeWith(@AttrRes int i) {
            this.c = i;
            return this;
        }

        @NonNull
        public Builder setColorAttributes(@Nullable HarmonizedColorAttributes harmonizedColorAttributes) {
            this.b = harmonizedColorAttributes;
            return this;
        }

        @NonNull
        public Builder setColorResourceIds(@NonNull @ColorRes int[] iArr) {
            this.f10258a = iArr;
            return this;
        }
    }

    @NonNull
    public static HarmonizedColorsOptions createMaterialDefaults() {
        return new Builder().setColorAttributes(HarmonizedColorAttributes.createMaterialDefaults()).build();
    }

    @StyleRes
    public int a(@StyleRes int i) {
        HarmonizedColorAttributes harmonizedColorAttributes = this.b;
        return (harmonizedColorAttributes == null || harmonizedColorAttributes.getThemeOverlay() == 0) ? i : this.b.getThemeOverlay();
    }

    @AttrRes
    public int getColorAttributeToHarmonizeWith() {
        return this.c;
    }

    @Nullable
    public HarmonizedColorAttributes getColorAttributes() {
        return this.b;
    }

    @NonNull
    @ColorRes
    public int[] getColorResourceIds() {
        return this.f10257a;
    }

    public HarmonizedColorsOptions(Builder builder) {
        this.f10257a = builder.f10258a;
        this.b = builder.b;
        this.c = builder.c;
    }
}
