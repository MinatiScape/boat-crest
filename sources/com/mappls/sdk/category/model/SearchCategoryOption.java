package com.mappls.sdk.category.model;

import android.os.Parcelable;
import androidx.annotation.IntRange;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.category.model.b;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class SearchCategoryOption implements Parcelable {
    public static final int NEARBY_SEARCH = 0;
    public static final int POI_ALONG_ROUTE_SEARCH = 1;

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract Builder bounds(String str);

        public abstract Builder buffer(@IntRange(from = 25, to = 1000) @Nullable Integer num);

        public abstract SearchCategoryOption build();

        public abstract Builder explain(Boolean bool);

        public abstract Builder filter(String str);

        public abstract Builder geometries(@Nullable String str);

        public abstract Builder isSort(@Nullable Boolean bool);

        public abstract Builder isUsingInternalMap(@NonNull Boolean bool);

        public Builder location(double d, double d2) {
            return location(d + Constants.SEPARATOR_COMMA + d2);
        }

        public abstract Builder location(@Nullable String str);

        public abstract Builder maxSelectionCount(@NonNull Integer num);

        public abstract Builder page(@Nullable Integer num);

        public abstract Builder path(@Nullable String str);

        public abstract Builder pod(String str);

        public abstract Builder radius(@NonNull Integer num);

        public abstract Builder richData(Boolean bool);

        public abstract Builder searchBy(String str);

        public abstract Builder searchType(@NonNull Integer num);

        public abstract Builder showRequestOnMap(@NonNull Boolean bool);

        public abstract Builder sortBy(String str);

        public abstract Builder userName(String str);
    }

    public static Builder builder() {
        Builder searchType = new b.a().maxSelectionCount(1).radius(1000).geometries("polyline6").searchType(1);
        Boolean bool = Boolean.TRUE;
        return searchType.isUsingInternalMap(bool).showRequestOnMap(bool);
    }

    @Nullable
    public abstract String bounds();

    @Nullable
    public abstract Integer buffer();

    @Nullable
    public abstract Boolean explain();

    @Nullable
    public abstract String filter();

    @Nullable
    public abstract String geometries();

    @Nullable
    public abstract Boolean isSort();

    @NonNull
    public abstract Boolean isUsingInternalMap();

    @Nullable
    public abstract String location();

    @NonNull
    public abstract Integer maxSelectionCount();

    @Nullable
    public abstract Integer page();

    @Nullable
    public abstract String path();

    @Nullable
    public abstract String pod();

    @NonNull
    public abstract Integer radius();

    @Nullable
    public abstract Boolean richData();

    @Nullable
    public abstract String searchBy();

    @NonNull
    public abstract Integer searchType();

    @NonNull
    public abstract Boolean showRequestOnMap();

    @Nullable
    public abstract String sortBy();

    @Nullable
    public abstract String userName();
}
