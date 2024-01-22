package com.mappls.sdk.plugins.places.placepicker.model;

import android.graphics.Bitmap;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.camera.CameraPosition;
import com.mappls.sdk.maps.geometry.LatLngBounds;
import com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions;
import com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions;
import java.util.List;
import java.util.Objects;
/* loaded from: classes11.dex */
public abstract class a extends PlacePickerOptions {
    public final Integer A;
    public final Integer h;
    public final LatLngBounds i;
    public final CameraPosition j;
    public final List<String> k;
    public final String l;
    public final Boolean m;
    public final Double n;
    public final Double o;
    public final Boolean p;
    public final PlaceOptions q;
    public final Bitmap r;
    public final Integer s;
    public final Integer t;
    public final Integer u;
    public final Boolean v;
    public final Integer w;
    public final Integer x;
    public final Integer y;
    public final String z;

    /* renamed from: com.mappls.sdk.plugins.places.placepicker.model.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class C0663a extends PlacePickerOptions.Builder {

        /* renamed from: a  reason: collision with root package name */
        public Integer f13144a;
        public LatLngBounds b;
        public CameraPosition c;
        public List<String> d;
        public String e;
        public Boolean f;
        public Double g;
        public Double h;
        public Boolean i;
        public PlaceOptions j;
        public Bitmap k;
        public Integer l;
        public Integer m;
        public Integer n;
        public Boolean o;
        public Integer p;
        public Integer q;
        public Integer r;
        public String s;
        public Integer t;

        @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions.Builder
        public final PlacePickerOptions.Builder addressTextColor(Integer num) {
            Objects.requireNonNull(num, "Null addressTextColor");
            this.n = num;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions.Builder
        public final PlacePickerOptions build() {
            String a2 = this.f == null ? com.mappls.sdk.plugins.places.autocomplete.model.a.a("", " includeDeviceLocationButton") : "";
            if (this.g == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " mapMaxZoom");
            }
            if (this.h == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " mapMinZoom");
            }
            if (this.i == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " includeSearch");
            }
            if (this.j == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " searchPlaceOption");
            }
            if (this.l == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " toolbarTintColor");
            }
            if (this.m == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " placeNameTextColor");
            }
            if (this.n == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " addressTextColor");
            }
            if (this.o == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " showMarkerShadow");
            }
            if (this.q == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " pickerButtonBackgroundResource");
            }
            if (this.r == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " pickerButtonTextColor");
            }
            if (this.s == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " pickerButtonText");
            }
            if (this.t == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " marker");
            }
            if (a2.isEmpty()) {
                return new b(this.f13144a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t);
            }
            throw new IllegalStateException(com.mappls.sdk.plugins.places.autocomplete.model.a.a("Missing required properties:", a2));
        }

        @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions.Builder
        public final PlacePickerOptions.Builder includeDeviceLocationButton(Boolean bool) {
            Objects.requireNonNull(bool, "Null includeDeviceLocationButton");
            this.f = bool;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions.Builder
        public final PlacePickerOptions.Builder includeSearch(Boolean bool) {
            Objects.requireNonNull(bool, "Null includeSearch");
            this.i = bool;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions.Builder
        public final PlacePickerOptions.Builder mapMaxZoom(Double d) {
            Objects.requireNonNull(d, "Null mapMaxZoom");
            this.g = d;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions.Builder
        public final PlacePickerOptions.Builder mapMinZoom(Double d) {
            Objects.requireNonNull(d, "Null mapMinZoom");
            this.h = d;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions.Builder
        public final PlacePickerOptions.Builder marker(Integer num) {
            Objects.requireNonNull(num, "Null marker");
            this.t = num;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions.Builder
        public final PlacePickerOptions.Builder markerBitmap(@Nullable Bitmap bitmap) {
            this.k = bitmap;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions.Builder
        public final PlacePickerOptions.Builder pickerButtonBackgroundColor(@Nullable Integer num) {
            this.p = num;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions.Builder
        public final PlacePickerOptions.Builder pickerButtonBackgroundResource(Integer num) {
            Objects.requireNonNull(num, "Null pickerButtonBackgroundResource");
            this.q = num;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions.Builder
        public final PlacePickerOptions.Builder pickerButtonText(String str) {
            Objects.requireNonNull(str, "Null pickerButtonText");
            this.s = str;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions.Builder
        public final PlacePickerOptions.Builder pickerButtonTextColor(Integer num) {
            Objects.requireNonNull(num, "Null pickerButtonTextColor");
            this.r = num;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions.Builder
        public final PlacePickerOptions.Builder placeNameTextColor(Integer num) {
            Objects.requireNonNull(num, "Null placeNameTextColor");
            this.m = num;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions.Builder
        public final PlacePickerOptions.Builder searchPlaceOption(PlaceOptions placeOptions) {
            Objects.requireNonNull(placeOptions, "Null searchPlaceOption");
            this.j = placeOptions;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions.Builder
        public final PlacePickerOptions.Builder showMarkerShadow(Boolean bool) {
            Objects.requireNonNull(bool, "Null showMarkerShadow");
            this.o = bool;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions.Builder
        public final PlacePickerOptions.Builder startingBounds(LatLngBounds latLngBounds) {
            this.b = latLngBounds;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions.Builder
        public final PlacePickerOptions.Builder startingMapplsPinBounds(@Nullable List<String> list) {
            this.d = list;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions.Builder
        public final PlacePickerOptions.Builder startingMapplsPinPosition(@Nullable String str) {
            this.e = str;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions.Builder
        public final PlacePickerOptions.Builder statingCameraPosition(CameraPosition cameraPosition) {
            this.c = cameraPosition;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions.Builder
        public final PlacePickerOptions.Builder toolbarColor(Integer num) {
            this.f13144a = num;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions.Builder
        public final PlacePickerOptions.Builder toolbarTintColor(Integer num) {
            Objects.requireNonNull(num, "Null toolbarTintColor");
            this.l = num;
            return this;
        }
    }

    public a(@Nullable Integer num, @Nullable LatLngBounds latLngBounds, @Nullable CameraPosition cameraPosition, @Nullable List<String> list, @Nullable String str, Boolean bool, Double d, Double d2, Boolean bool2, PlaceOptions placeOptions, @Nullable Bitmap bitmap, Integer num2, Integer num3, Integer num4, Boolean bool3, @Nullable Integer num5, Integer num6, Integer num7, String str2, Integer num8) {
        this.h = num;
        this.i = latLngBounds;
        this.j = cameraPosition;
        this.k = list;
        this.l = str;
        Objects.requireNonNull(bool, "Null includeDeviceLocationButton");
        this.m = bool;
        Objects.requireNonNull(d, "Null mapMaxZoom");
        this.n = d;
        Objects.requireNonNull(d2, "Null mapMinZoom");
        this.o = d2;
        Objects.requireNonNull(bool2, "Null includeSearch");
        this.p = bool2;
        Objects.requireNonNull(placeOptions, "Null searchPlaceOption");
        this.q = placeOptions;
        this.r = bitmap;
        Objects.requireNonNull(num2, "Null toolbarTintColor");
        this.s = num2;
        Objects.requireNonNull(num3, "Null placeNameTextColor");
        this.t = num3;
        Objects.requireNonNull(num4, "Null addressTextColor");
        this.u = num4;
        Objects.requireNonNull(bool3, "Null showMarkerShadow");
        this.v = bool3;
        this.w = num5;
        Objects.requireNonNull(num6, "Null pickerButtonBackgroundResource");
        this.x = num6;
        Objects.requireNonNull(num7, "Null pickerButtonTextColor");
        this.y = num7;
        Objects.requireNonNull(str2, "Null pickerButtonText");
        this.z = str2;
        Objects.requireNonNull(num8, "Null marker");
        this.A = num8;
    }

    @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions
    @NonNull
    @ColorInt
    public final Integer addressTextColor() {
        return this.u;
    }

    public final boolean equals(Object obj) {
        Bitmap bitmap;
        Integer num;
        if (obj == this) {
            return true;
        }
        if (obj instanceof PlacePickerOptions) {
            PlacePickerOptions placePickerOptions = (PlacePickerOptions) obj;
            Integer num2 = this.h;
            if (num2 != null ? num2.equals(placePickerOptions.toolbarColor()) : placePickerOptions.toolbarColor() == null) {
                LatLngBounds latLngBounds = this.i;
                if (latLngBounds != null ? latLngBounds.equals(placePickerOptions.startingBounds()) : placePickerOptions.startingBounds() == null) {
                    CameraPosition cameraPosition = this.j;
                    if (cameraPosition != null ? cameraPosition.equals(placePickerOptions.statingCameraPosition()) : placePickerOptions.statingCameraPosition() == null) {
                        List<String> list = this.k;
                        if (list != null ? list.equals(placePickerOptions.startingMapplsPinBounds()) : placePickerOptions.startingMapplsPinBounds() == null) {
                            String str = this.l;
                            if (str != null ? str.equals(placePickerOptions.startingMapplsPinPosition()) : placePickerOptions.startingMapplsPinPosition() == null) {
                                if (this.m.equals(placePickerOptions.includeDeviceLocationButton()) && this.n.equals(placePickerOptions.mapMaxZoom()) && this.o.equals(placePickerOptions.mapMinZoom()) && this.p.equals(placePickerOptions.includeSearch()) && this.q.equals(placePickerOptions.searchPlaceOption()) && ((bitmap = this.r) != null ? bitmap.equals(placePickerOptions.markerBitmap()) : placePickerOptions.markerBitmap() == null) && this.s.equals(placePickerOptions.toolbarTintColor()) && this.t.equals(placePickerOptions.placeNameTextColor()) && this.u.equals(placePickerOptions.addressTextColor()) && this.v.equals(placePickerOptions.showMarkerShadow()) && ((num = this.w) != null ? num.equals(placePickerOptions.pickerButtonBackgroundColor()) : placePickerOptions.pickerButtonBackgroundColor() == null) && this.x.equals(placePickerOptions.pickerButtonBackgroundResource()) && this.y.equals(placePickerOptions.pickerButtonTextColor()) && this.z.equals(placePickerOptions.pickerButtonText()) && this.A.equals(placePickerOptions.marker())) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        Integer num = this.h;
        int hashCode = ((num == null ? 0 : num.hashCode()) ^ 1000003) * 1000003;
        LatLngBounds latLngBounds = this.i;
        int hashCode2 = (hashCode ^ (latLngBounds == null ? 0 : latLngBounds.hashCode())) * 1000003;
        CameraPosition cameraPosition = this.j;
        int hashCode3 = (hashCode2 ^ (cameraPosition == null ? 0 : cameraPosition.hashCode())) * 1000003;
        List<String> list = this.k;
        int hashCode4 = (hashCode3 ^ (list == null ? 0 : list.hashCode())) * 1000003;
        String str = this.l;
        int hashCode5 = (((((((((((hashCode4 ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.m.hashCode()) * 1000003) ^ this.n.hashCode()) * 1000003) ^ this.o.hashCode()) * 1000003) ^ this.p.hashCode()) * 1000003) ^ this.q.hashCode()) * 1000003;
        Bitmap bitmap = this.r;
        int hashCode6 = (((((((((hashCode5 ^ (bitmap == null ? 0 : bitmap.hashCode())) * 1000003) ^ this.s.hashCode()) * 1000003) ^ this.t.hashCode()) * 1000003) ^ this.u.hashCode()) * 1000003) ^ this.v.hashCode()) * 1000003;
        Integer num2 = this.w;
        return ((((((((hashCode6 ^ (num2 != null ? num2.hashCode() : 0)) * 1000003) ^ this.x.hashCode()) * 1000003) ^ this.y.hashCode()) * 1000003) ^ this.z.hashCode()) * 1000003) ^ this.A.hashCode();
    }

    @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions
    @NonNull
    public final Boolean includeDeviceLocationButton() {
        return this.m;
    }

    @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions
    @NonNull
    public final Boolean includeSearch() {
        return this.p;
    }

    @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions
    @NonNull
    public final Double mapMaxZoom() {
        return this.n;
    }

    @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions
    @NonNull
    public final Double mapMinZoom() {
        return this.o;
    }

    @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions
    @NonNull
    @DrawableRes
    public final Integer marker() {
        return this.A;
    }

    @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions
    @Nullable
    public final Bitmap markerBitmap() {
        return this.r;
    }

    @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions
    @Nullable
    @ColorInt
    public final Integer pickerButtonBackgroundColor() {
        return this.w;
    }

    @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions
    @NonNull
    @DrawableRes
    public final Integer pickerButtonBackgroundResource() {
        return this.x;
    }

    @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions
    @NonNull
    public final String pickerButtonText() {
        return this.z;
    }

    @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions
    @NonNull
    @ColorInt
    public final Integer pickerButtonTextColor() {
        return this.y;
    }

    @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions
    @NonNull
    @ColorInt
    public final Integer placeNameTextColor() {
        return this.t;
    }

    @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions
    @NonNull
    public final PlaceOptions searchPlaceOption() {
        return this.q;
    }

    @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions
    @NonNull
    public final Boolean showMarkerShadow() {
        return this.v;
    }

    @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions
    @Nullable
    public final LatLngBounds startingBounds() {
        return this.i;
    }

    @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions
    @Nullable
    public final List<String> startingMapplsPinBounds() {
        return this.k;
    }

    @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions
    @Nullable
    public final String startingMapplsPinPosition() {
        return this.l;
    }

    @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions
    @Nullable
    public final CameraPosition statingCameraPosition() {
        return this.j;
    }

    public final String toString() {
        return "PlacePickerOptions{toolbarColor=" + this.h + ", startingBounds=" + this.i + ", statingCameraPosition=" + this.j + ", startingMapplsPinBounds=" + this.k + ", startingMapplsPinPosition=" + this.l + ", includeDeviceLocationButton=" + this.m + ", mapMaxZoom=" + this.n + ", mapMinZoom=" + this.o + ", includeSearch=" + this.p + ", searchPlaceOption=" + this.q + ", markerBitmap=" + this.r + ", toolbarTintColor=" + this.s + ", placeNameTextColor=" + this.t + ", addressTextColor=" + this.u + ", showMarkerShadow=" + this.v + ", pickerButtonBackgroundColor=" + this.w + ", pickerButtonBackgroundResource=" + this.x + ", pickerButtonTextColor=" + this.y + ", pickerButtonText=" + this.z + ", marker=" + this.A + "}";
    }

    @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions
    @Nullable
    public final Integer toolbarColor() {
        return this.h;
    }

    @Override // com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions
    @NonNull
    @ColorInt
    public final Integer toolbarTintColor() {
        return this.s;
    }
}
