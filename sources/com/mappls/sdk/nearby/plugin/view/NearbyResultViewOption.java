package com.mappls.sdk.nearby.plugin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Parcelable;
import android.util.AttributeSet;
import androidx.annotation.DrawableRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.clevertap.android.sdk.Constants;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.nearby.plugin.R;
import com.mappls.sdk.nearby.plugin.model.NearbyUIOption;
import com.mappls.sdk.nearby.plugin.view.a;
@AutoValue
@Keep
/* loaded from: classes10.dex */
public abstract class NearbyResultViewOption implements Parcelable {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        public abstract Builder addressTextColor(@NonNull Integer num);

        public abstract Builder backgroundColor(@NonNull Integer num);

        public abstract NearbyResultViewOption build();

        public abstract Builder categoryFilterBackgroundColor(@NonNull Integer num);

        public abstract Builder distanceTextColor(@NonNull Integer num);

        public abstract Builder listBackgroundColor(@NonNull Integer num);

        public abstract Builder listSeperatorColor(@NonNull Integer num);

        public abstract Builder locationCircleAlpha(@NonNull Float f);

        public abstract Builder locationCircleColor(@NonNull Integer num);

        public abstract Builder locationMarkerBitmap(@Nullable Bitmap bitmap);

        public abstract Builder locationMarkerIcon(@NonNull @DrawableRes Integer num);

        public abstract Builder nextButtonBackgroundColor(@NonNull Integer num);

        public abstract Builder paginationBackgroundColor(@NonNull Integer num);

        public abstract Builder placeNameTextColor(@NonNull Integer num);

        public abstract Builder prevButtonBackgroundColor(@NonNull Integer num);

        public abstract Builder showDefaultMap(@NonNull Boolean bool);

        public abstract Builder tabBackgroundColor(@NonNull Integer num);

        public abstract Builder tabIconTint(@NonNull Integer num);

        public abstract Builder tabIndicatorColor(@NonNull Integer num);

        public abstract Builder tabSelectedTextColor(@NonNull Integer num);

        public abstract Builder tabTextColor(@NonNull Integer num);

        public abstract Builder toolbarBackgroundColor(@NonNull Integer num);

        public abstract Builder toolbarText(@NonNull String str);

        public abstract Builder toolbarTintColor(@NonNull Integer num);
    }

    public static Builder builder() {
        return new a.C0654a().backgroundColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).listBackgroundColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).paginationBackgroundColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).toolbarBackgroundColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).prevButtonBackgroundColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).nextButtonBackgroundColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).categoryFilterBackgroundColor(Integer.valueOf(Color.parseColor("#FAFAFA"))).toolbarTintColor(Integer.valueOf(Color.parseColor("#212121"))).listSeperatorColor(Integer.valueOf(Color.parseColor("#212121"))).toolbarText("Nearby Search").tabTextColor(Integer.valueOf(Color.parseColor("#797979"))).tabSelectedTextColor(Integer.valueOf(Color.parseColor("#3A78E7"))).tabBackgroundColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).tabIndicatorColor(Integer.valueOf(Color.parseColor("#3A78E7"))).tabIconTint(Integer.valueOf(R.color.mappls_nearby_icon_tab_color)).placeNameTextColor(Integer.valueOf(Color.parseColor("#212121"))).addressTextColor(Integer.valueOf(Color.parseColor("#212121"))).distanceTextColor(Integer.valueOf(Color.parseColor("#99212121"))).locationCircleColor(Integer.valueOf(Color.parseColor("#0099FF"))).locationMarkerIcon(Integer.valueOf(R.drawable.mappls_nearby_location_marker)).locationCircleAlpha(Float.valueOf(0.22f)).showDefaultMap(Boolean.TRUE);
    }

    public static NearbyResultViewOption createFromAttributes(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.mappls_nearby_result, 0, 0);
        Builder builder = builder();
        int i = R.styleable.mappls_nearby_result_mappls_nearby_result_background;
        int i2 = R.color.mappls_nearby_white;
        builder.backgroundColor(Integer.valueOf(obtainStyledAttributes.getColor(i, ContextCompat.getColor(context, i2))));
        builder.listBackgroundColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_result_mappls_nearby_result_list_background, ContextCompat.getColor(context, i2))));
        builder.prevButtonBackgroundColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_result_mappls_nearby_result_prev_button_color, ContextCompat.getColor(context, i2))));
        builder.nextButtonBackgroundColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_result_mappls_nearby_result_next_button_color, ContextCompat.getColor(context, i2))));
        builder.listSeperatorColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_result_mappls_nearby_result_list_seperator, ContextCompat.getColor(context, i2))));
        builder.categoryFilterBackgroundColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_result_mappls_nearby_result_category_filter_background_color, ContextCompat.getColor(context, R.color.mappls_nearby_address_background_color))));
        builder.paginationBackgroundColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_result_mappls_nearby_result_pagination_background, ContextCompat.getColor(context, i2))));
        builder.toolbarBackgroundColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_result_mappls_nearby_result_toolbar_background, ContextCompat.getColor(context, i2))));
        int i3 = R.styleable.mappls_nearby_result_mappls_nearby_result_toolbar_tint_color;
        int i4 = R.color.mappls_nearby_primary_color;
        builder.toolbarTintColor(Integer.valueOf(obtainStyledAttributes.getColor(i3, ContextCompat.getColor(context, i4))));
        int i5 = R.styleable.mappls_nearby_result_mappls_nearby_result_toolbar_text;
        builder.toolbarText(obtainStyledAttributes.getString(i5) != null ? obtainStyledAttributes.getString(i5) : "Nearby Search");
        builder.tabTextColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_result_mappls_nearby_result_tab_text_color, ContextCompat.getColor(context, R.color.mappls_nearby_address_tooltip_text_color))));
        int i6 = R.styleable.mappls_nearby_result_mappls_nearby_result_tab_selected_text_color;
        int i7 = R.color.mappls_nearby_secondary_color;
        builder.tabSelectedTextColor(Integer.valueOf(obtainStyledAttributes.getColor(i6, ContextCompat.getColor(context, i7))));
        builder.tabIndicatorColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_result_mappls_nearby_result_tab_indicator_color, ContextCompat.getColor(context, i7))));
        builder.tabBackgroundColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_result_mappls_nearby_result_tab_background_color, ContextCompat.getColor(context, i2))));
        builder.tabIconTint(Integer.valueOf(obtainStyledAttributes.getResourceId(R.styleable.mappls_nearby_result_mappls_nearby_result_tab_icon_tint, R.color.mappls_nearby_icon_tab_color)));
        builder.placeNameTextColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_result_mappls_nearby_result_place_name_text_color, ContextCompat.getColor(context, i4))));
        builder.distanceTextColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_result_mappls_nearby_result_distance_text_color, ContextCompat.getColor(context, R.color.mappls_nearby_primary_color_60))));
        builder.addressTextColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_result_mappls_nearby_result_address_text_color, ContextCompat.getColor(context, i4))));
        builder.locationMarkerIcon(Integer.valueOf(obtainStyledAttributes.getResourceId(R.styleable.mappls_nearby_result_mappls_nearby_result_location_marker_icon, R.drawable.mappls_nearby_location_marker)));
        builder.locationCircleColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_result_mappls_nearby_result_location_circle_color, ContextCompat.getColor(context, R.color.mappls_nearby_circle_color))));
        builder.locationCircleAlpha(Float.valueOf(obtainStyledAttributes.getFloat(R.styleable.mappls_nearby_result_mappls_nearby_result_location_circle_alpha, 0.22f)));
        builder.showDefaultMap(Boolean.valueOf(obtainStyledAttributes.getBoolean(R.styleable.mappls_nearby_result_mappls_nearby_result_show_default_map, true)));
        obtainStyledAttributes.recycle();
        return builder.build();
    }

    public static NearbyResultViewOption createFromNearbyOption(NearbyUIOption nearbyUIOption) {
        Builder builder = builder();
        builder.backgroundColor(nearbyUIOption.backgroundColor());
        builder.paginationBackgroundColor(nearbyUIOption.paginationBackgroundColor());
        builder.toolbarBackgroundColor(nearbyUIOption.toolbarColor());
        builder.toolbarTintColor(nearbyUIOption.toolbarTintColor());
        builder.tabTextColor(nearbyUIOption.tabTextColor());
        builder.tabSelectedTextColor(nearbyUIOption.selectedTabTextColor());
        builder.tabIndicatorColor(nearbyUIOption.tabIndicatorColor());
        builder.tabBackgroundColor(nearbyUIOption.tabBackgroundColor());
        builder.tabIconTint(nearbyUIOption.tabIconTint());
        builder.prevButtonBackgroundColor(nearbyUIOption.prevButtonBackgroundColor());
        builder.categoryFilterBackgroundColor(nearbyUIOption.categoryFilterBackgroundColor());
        builder.nextButtonBackgroundColor(nearbyUIOption.nextButtonBackgroundColor());
        builder.placeNameTextColor(nearbyUIOption.placeNameTextColor());
        builder.distanceTextColor(nearbyUIOption.distanceTextColor());
        builder.addressTextColor(nearbyUIOption.placeAddressTextColor());
        builder.locationMarkerIcon(nearbyUIOption.refLocationIcon());
        builder.locationMarkerBitmap(nearbyUIOption.refLocationBitmap());
        builder.listBackgroundColor(nearbyUIOption.detailListBackgroundColor());
        builder.listSeperatorColor(nearbyUIOption.detailListSeperatorBackgroundColor());
        builder.locationCircleColor(nearbyUIOption.refLocationCircleColor());
        builder.locationCircleAlpha(nearbyUIOption.refLocationCircleAlpha());
        builder.showDefaultMap(nearbyUIOption.showDefaultMap());
        return builder.build();
    }

    @NonNull
    public abstract Integer addressTextColor();

    @NonNull
    public abstract Integer backgroundColor();

    @NonNull
    public abstract Integer categoryFilterBackgroundColor();

    @NonNull
    public abstract Integer distanceTextColor();

    @NonNull
    public abstract Integer listBackgroundColor();

    @NonNull
    public abstract Integer listSeperatorColor();

    @NonNull
    public abstract Float locationCircleAlpha();

    @NonNull
    public abstract Integer locationCircleColor();

    @Nullable
    public abstract Bitmap locationMarkerBitmap();

    @NonNull
    @DrawableRes
    public abstract Integer locationMarkerIcon();

    @NonNull
    public abstract Integer nextButtonBackgroundColor();

    @NonNull
    public abstract Integer paginationBackgroundColor();

    @NonNull
    public abstract Integer placeNameTextColor();

    @NonNull
    public abstract Integer prevButtonBackgroundColor();

    @NonNull
    public abstract Boolean showDefaultMap();

    @NonNull
    public abstract Integer tabBackgroundColor();

    @NonNull
    public abstract Integer tabIconTint();

    @NonNull
    public abstract Integer tabIndicatorColor();

    @NonNull
    public abstract Integer tabSelectedTextColor();

    @NonNull
    public abstract Integer tabTextColor();

    @NonNull
    public abstract Integer toolbarBackgroundColor();

    @NonNull
    public abstract String toolbarText();

    @NonNull
    public abstract Integer toolbarTintColor();
}
