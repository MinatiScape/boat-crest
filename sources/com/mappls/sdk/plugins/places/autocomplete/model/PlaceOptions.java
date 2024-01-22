package com.mappls.sdk.plugins.places.autocomplete.model;

import android.graphics.Color;
import android.os.Parcelable;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntRange;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.google.android.gms.common.ConnectionResult;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.plugins.places.R;
import com.mappls.sdk.plugins.places.autocomplete.model.b;
import java.util.ArrayList;
import java.util.List;
@AutoValue
@Keep
/* loaded from: classes10.dex */
public abstract class PlaceOptions implements Parcelable {
    public static final int GRAVITY_BOTTOM = 7;
    public static final int GRAVITY_CENTER = 4;
    public static final int GRAVITY_LEFT = 3;
    public static final int GRAVITY_RIGHT = 5;
    public static final int GRAVITY_TOP = 6;
    public static final int MODE_CARDS = 2;
    public static final int MODE_FULLSCREEN = 1;
    public static final int SIZE_LARGE = 10;
    public static final int SIZE_MEDIUM = 9;
    public static final int SIZE_SMALL = 8;

    @Keep
    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        private List<String> countries = new ArrayList();
        private Integer debounceValue = 0;

        public abstract Builder attributionHorizontalAlignment(@NonNull int i);

        public abstract Builder attributionVerticalAlignment(@NonNull int i);

        public abstract PlaceOptions autoBuild();

        public abstract Builder backgroundColor(@ColorInt int i);

        public abstract Builder bridge(@NonNull Boolean bool);

        public PlaceOptions build() {
            return build(1);
        }

        public PlaceOptions build(@IntRange(from = 1, to = 2) int i) {
            int valueOf;
            viewMode(i);
            if (this.debounceValue.intValue() >= 0) {
                if (this.debounceValue.intValue() > 1500) {
                    valueOf = Integer.valueOf((int) ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
                }
                internalDebounce(this.debounceValue);
                return autoBuild();
            }
            valueOf = 0;
            this.debounceValue = valueOf;
            internalDebounce(this.debounceValue);
            return autoBuild();
        }

        public abstract Builder currentLocationIcon(@NonNull @DrawableRes Integer num);

        public abstract Builder currentLocationTextColor(@NonNull Integer num);

        public Builder debounce(@IntRange(from = 0, to = 1500) @NonNull Integer num) {
            this.debounceValue = num;
            return this;
        }

        public abstract Builder enableTextSearch(Boolean bool);

        public abstract Builder favoriteCount(@IntRange(from = 0) @Nullable Integer num);

        public Builder favoritePlaces(List<MapplsFavoritePlace> list) {
            ArrayList arrayList = new ArrayList();
            for (MapplsFavoritePlace mapplsFavoritePlace : list) {
                arrayList.add(new Gson().toJson(mapplsFavoritePlace));
            }
            return injectedPlaces(arrayList);
        }

        public abstract Builder filter(@Nullable String str);

        public abstract Builder hint(@Nullable String str);

        public abstract Builder historyCount(@IntRange(from = 0) @Nullable Integer num);

        public abstract Builder hyperLocal(@NonNull Boolean bool);

        public abstract Builder injectedPlaces(List<String> list);

        public abstract Builder internalDebounce(@IntRange(from = 0, to = 1000) @NonNull Integer num);

        public abstract Builder internalMinCharactersForSearch(@IntRange(from = 2) @NonNull Integer num);

        public abstract Builder isShowCurrentLocation(@NonNull Boolean bool);

        public abstract Builder limit(@IntRange(from = 1, to = 20) int i);

        public abstract Builder location(@Nullable Point point);

        public abstract Builder logoSize(@NonNull int i);

        public Builder minCharactersForSearch(@IntRange(from = 2) @NonNull Integer num) {
            return num.intValue() < 2 ? internalMinCharactersForSearch(3) : internalMinCharactersForSearch(num);
        }

        public abstract Builder pod(@Nullable String str);

        public abstract Builder responseLang(@Nullable String str);

        public abstract Builder saveHistory(Boolean bool);

        public abstract Builder showPoweredByText(@NonNull Boolean bool);

        public abstract Builder statusBarColor(@Nullable @ColorInt Integer num);

        public abstract Builder tokenizeAddress(@Nullable Boolean bool);

        public abstract Builder toolbarColor(@ColorInt int i);

        public abstract Builder toolbarTintColor(@ColorInt int i);

        public abstract Builder userAddedLocationEnable(Boolean bool);

        public abstract Builder viewMode(int i);

        public abstract Builder zoom(@Nullable Double d);
    }

    public static Builder builder() {
        Builder builder = new b.a().backgroundColor(0).toolbarColor(-1);
        Boolean bool = Boolean.FALSE;
        return builder.userAddedLocationEnable(bool).toolbarTintColor(ViewCompat.MEASURED_STATE_MASK).enableTextSearch(bool).debounce(0).saveHistory(bool).showPoweredByText(Boolean.TRUE).favoriteCount(2).logoSize(9).minCharactersForSearch(3).attributionHorizontalAlignment(6).attributionVerticalAlignment(3).isShowCurrentLocation(bool).currentLocationTextColor(Integer.valueOf(Color.parseColor("#263d57"))).currentLocationIcon(Integer.valueOf(R.drawable.mappls_search_my_location)).limit(20);
    }

    @NonNull
    public abstract int attributionHorizontalAlignment();

    @NonNull
    public abstract int attributionVerticalAlignment();

    public abstract int backgroundColor();

    @Nullable
    public abstract Boolean bridge();

    @NonNull
    @DrawableRes
    public abstract Integer currentLocationIcon();

    @NonNull
    public abstract Integer currentLocationTextColor();

    @NonNull
    public abstract Boolean enableTextSearch();

    @Nullable
    public abstract Integer favoriteCount();

    @Nullable
    public abstract String filter();

    @Nullable
    public abstract String hint();

    @Nullable
    public abstract Integer historyCount();

    @Nullable
    public abstract Boolean hyperLocal();

    @Nullable
    public abstract List<String> injectedPlaces();

    @NonNull
    public abstract Integer internalDebounce();

    @NonNull
    public abstract Integer internalMinCharactersForSearch();

    @NonNull
    public abstract Boolean isShowCurrentLocation();

    public abstract int limit();

    @Nullable
    public abstract Point location();

    @NonNull
    public abstract int logoSize();

    @Nullable
    public abstract String pod();

    @Nullable
    public abstract String responseLang();

    @NonNull
    public abstract Boolean saveHistory();

    @NonNull
    public abstract Boolean showPoweredByText();

    @Nullable
    @ColorInt
    public abstract Integer statusBarColor();

    @Nullable
    public abstract Boolean tokenizeAddress();

    public abstract int toolbarColor();

    @ColorInt
    public abstract int toolbarTintColor();

    @NonNull
    public abstract Boolean userAddedLocationEnable();

    public abstract int viewMode();

    @Nullable
    public abstract Double zoom();
}
