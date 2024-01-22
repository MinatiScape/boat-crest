package com.mappls.sdk.direction.ui.model;

import android.graphics.Color;
import android.os.Parcelable;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import com.clevertap.android.sdk.Constants;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.direction.ui.R;
import com.mappls.sdk.direction.ui.model.b;
import com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions;
import com.mappls.sdk.services.api.directions.DirectionsCriteria;
import java.util.List;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class DirectionOptions implements Parcelable {
    public static final int THEME_DAY = 1;
    public static final int THEME_DEFAULT = 0;
    public static final int THEME_NIGHT = 2;

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract Builder alongRouteBuffer(@Nullable Integer num);

        public abstract Builder alongRouteDarkTheme(@NonNull Integer num);

        public abstract Builder alongRouteDayTheme(@NonNull Integer num);

        public abstract Builder alternateCasingRouteColor(@NonNull Integer num);

        public abstract Builder alternateRouteColor(@NonNull Integer num);

        public abstract Builder annotation(@Nullable List<String> list);

        public abstract DirectionOptions build();

        public abstract Builder destination(@Nullable DirectionPoint directionPoint);

        public abstract Builder destinationMarker(@NonNull Integer num);

        public abstract Builder directionDarkTheme(@NonNull @StyleRes Integer num);

        public abstract Builder directionDayTheme(@NonNull @StyleRes Integer num);

        public abstract Builder excludes(@Nullable List<String> list);

        public abstract Builder firstWayPointMarker(@NonNull Integer num);

        public abstract Builder geometries(@NonNull String str);

        public abstract Builder instructions(@Nullable Boolean bool);

        public abstract Builder isSort(@Nullable Boolean bool);

        public abstract Builder lessVerbose(@Nullable Boolean bool);

        public abstract Builder origin(@Nullable DirectionPoint directionPoint);

        public abstract Builder overview(@NonNull String str);

        public abstract Builder profile(@NonNull String str);

        public abstract Builder resource(@NonNull String str);

        public abstract Builder routeType(@Nullable Integer num);

        public abstract Builder searchAlongRoute(@NonNull Boolean bool);

        public abstract Builder searchPlaceOption(@NonNull PlaceOptions placeOptions);

        public abstract Builder secondWayPointMarker(@NonNull Integer num);

        public abstract Builder selectedCasingRouteColor(@NonNull Integer num);

        public abstract Builder selectedRouteColor(@NonNull Integer num);

        public abstract Builder showAddWaypointOption(@NonNull Boolean bool);

        public abstract Builder showAlternative(@NonNull Boolean bool);

        public abstract Builder showDefaultMap(@NonNull Boolean bool);

        public abstract Builder showProfileOption(@NonNull Boolean bool);

        public abstract Builder showRouteReportSummary(@NonNull Boolean bool);

        public abstract Builder showRouteReportSummaryOnMap(@NonNull Boolean bool);

        public abstract Builder showStartNavigation(@NonNull Boolean bool);

        public abstract Builder showTripCostSummary(@NonNull Boolean bool);

        public abstract Builder sourceMarker(@NonNull Integer num);

        public abstract Builder steps(@NonNull Boolean bool);

        public abstract Builder theme(@NonNull Integer num);

        public abstract Builder thirdWayPointMarker(@NonNull Integer num);

        public Builder viaPointMarker(@NonNull Integer num, @NonNull Integer num2, @NonNull Integer num3) {
            return firstWayPointMarker(num).secondWayPointMarker(num2).thirdWayPointMarker(num3);
        }
    }

    public static Builder builder() {
        PlaceOptions.Builder backgroundColor = PlaceOptions.builder().backgroundColor(-1);
        Boolean bool = Boolean.TRUE;
        PlaceOptions build = backgroundColor.isShowCurrentLocation(bool).build(2);
        Builder resource = new b.a().resource(DirectionsCriteria.RESOURCE_ROUTE);
        Boolean bool2 = Boolean.FALSE;
        return resource.showAlternative(bool2).showProfileOption(bool2).searchAlongRoute(bool2).geometries("polyline6").theme(1).showDefaultMap(bool).selectedRouteColor(Integer.valueOf(Color.parseColor("#07b9fc"))).selectedCasingRouteColor(Integer.valueOf(Color.parseColor(Constants.BLACK))).alternateRouteColor(Integer.valueOf(Color.parseColor("#a1bbd2"))).alternateCasingRouteColor(Integer.valueOf(Color.parseColor(Constants.BLACK))).steps(bool).showAddWaypointOption(bool).searchPlaceOption(build).overview("full").profile("driving").showStartNavigation(bool).destinationMarker(Integer.valueOf(R.drawable.mappls_direction_destination_marker)).sourceMarker(Integer.valueOf(R.drawable.mappls_direction_marker_source)).firstWayPointMarker(Integer.valueOf(R.drawable.mappls_direction_marker_via_1)).secondWayPointMarker(Integer.valueOf(R.drawable.mappls_direction_marker_via_2)).thirdWayPointMarker(Integer.valueOf(R.drawable.mappls_direction_marker_via_3)).directionDayTheme(Integer.valueOf(R.style.MapplsDirectionDayTheme)).directionDarkTheme(Integer.valueOf(R.style.MapplsDirectionNightTheme)).alongRouteDayTheme(Integer.valueOf(R.style.MapplsCategoryDayTheme)).alongRouteDarkTheme(Integer.valueOf(R.style.MapplsCategoryDarkTheme)).showRouteReportSummary(bool2).showRouteReportSummaryOnMap(bool2).showTripCostSummary(bool2);
    }

    @Nullable
    public abstract Integer alongRouteBuffer();

    @NonNull
    public abstract Integer alongRouteDarkTheme();

    @NonNull
    public abstract Integer alongRouteDayTheme();

    @NonNull
    public abstract Integer alternateCasingRouteColor();

    @NonNull
    public abstract Integer alternateRouteColor();

    @Nullable
    public abstract List<String> annotation();

    @Nullable
    public abstract DirectionPoint destination();

    @NonNull
    public abstract Integer destinationMarker();

    @NonNull
    @StyleRes
    public abstract Integer directionDarkTheme();

    @NonNull
    @StyleRes
    public abstract Integer directionDayTheme();

    @Nullable
    public abstract List<String> excludes();

    @NonNull
    public abstract Integer firstWayPointMarker();

    @NonNull
    public abstract String geometries();

    @Nullable
    public abstract Boolean instructions();

    @Nullable
    public abstract Boolean isSort();

    @Nullable
    public abstract Boolean lessVerbose();

    @Nullable
    public abstract DirectionPoint origin();

    @NonNull
    public abstract String overview();

    @NonNull
    public abstract String profile();

    @NonNull
    public abstract String resource();

    @Nullable
    public abstract Integer routeType();

    @NonNull
    public abstract Boolean searchAlongRoute();

    @NonNull
    public abstract PlaceOptions searchPlaceOption();

    @NonNull
    public abstract Integer secondWayPointMarker();

    @NonNull
    public abstract Integer selectedCasingRouteColor();

    @NonNull
    public abstract Integer selectedRouteColor();

    @NonNull
    public abstract Boolean showAddWaypointOption();

    @NonNull
    public abstract Boolean showAlternative();

    @NonNull
    public abstract Boolean showDefaultMap();

    @NonNull
    public abstract Boolean showProfileOption();

    @NonNull
    public abstract Boolean showRouteReportSummary();

    @NonNull
    public abstract Boolean showRouteReportSummaryOnMap();

    @NonNull
    public abstract Boolean showStartNavigation();

    @NonNull
    public abstract Boolean showTripCostSummary();

    @NonNull
    public abstract Integer sourceMarker();

    @NonNull
    public abstract Boolean steps();

    @NonNull
    public abstract Integer theme();

    @NonNull
    public abstract Integer thirdWayPointMarker();
}
