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
import com.mappls.sdk.nearby.plugin.view.b;
@AutoValue
@Keep
/* loaded from: classes10.dex */
public abstract class NearbyViewOption implements Parcelable {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        public abstract Builder addressBackgroundColor(@NonNull Integer num);

        public abstract Builder addressTextColor(@NonNull Integer num);

        public abstract Builder addressTooltipTextColor(@NonNull Integer num);

        public abstract Builder backgroundColor(@NonNull Integer num);

        public abstract NearbyViewOption build();

        public abstract Builder categoryBackgroundColor(@NonNull Integer num);

        public abstract Builder categoryTextColor(@NonNull Integer num);

        public abstract Builder categoryTintColor(@NonNull Integer num);

        public abstract Builder changeLocationButtonTextColor(@NonNull Integer num);

        public abstract Builder locationInfoLabelText(@NonNull String str);

        public abstract Builder selectedCategoryBackgroundColor(@NonNull Integer num);

        public abstract Builder selectedCategoryTextColor(@NonNull Integer num);

        public abstract Builder selectedCategoryTintColor(@NonNull Integer num);

        public abstract Builder submitButtonColor(@Nullable Integer num);

        public abstract Builder submitButtonResource(@NonNull Integer num);

        public abstract Builder submitButtonText(@NonNull String str);

        public abstract Builder submitButtonTextColor(@NonNull Integer num);

        public abstract Builder toolbarBackgroundColor(@NonNull Integer num);

        public abstract Builder toolbarBitmap(@Nullable Bitmap bitmap);

        public abstract Builder toolbarIcon(@NonNull @DrawableRes Integer num);

        public abstract Builder toolbarText(@NonNull String str);

        public abstract Builder toolbarTextColor(@NonNull Integer num);

        public abstract Builder useCurrentLocationButtonTextColor(@NonNull Integer num);
    }

    public static Builder builder() {
        return new b.a().backgroundColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).toolbarBackgroundColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).toolbarIcon(Integer.valueOf(R.drawable.mappls_nearby_marker)).toolbarTextColor(Integer.valueOf(Color.parseColor("#212121"))).toolbarText("Nearby Search").addressBackgroundColor(Integer.valueOf(Color.parseColor("#FAFAFA"))).addressTooltipTextColor(Integer.valueOf(Color.parseColor("#797979"))).addressTextColor(Integer.valueOf(Color.parseColor("#212121"))).changeLocationButtonTextColor(Integer.valueOf(Color.parseColor("#3A78E7"))).useCurrentLocationButtonTextColor(Integer.valueOf(Color.parseColor("#3A78E7"))).selectedCategoryBackgroundColor(Integer.valueOf(Color.parseColor("#3A78E7"))).selectedCategoryTextColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).selectedCategoryTintColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).categoryBackgroundColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).categoryTextColor(Integer.valueOf(Color.parseColor("#3D3D3D"))).categoryTintColor(Integer.valueOf(Color.parseColor("#3A78E7"))).submitButtonResource(Integer.valueOf(R.drawable.mappls_nearby_btn_bg)).submitButtonText("Next").locationInfoLabelText("Your location is auto-detected as").submitButtonTextColor(Integer.valueOf(Color.parseColor(Constants.WHITE)));
    }

    public static NearbyViewOption createFromAttributes(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.mappls_nearby, 0, 0);
        Builder builder = builder();
        int i = R.styleable.mappls_nearby_mappls_nearby_background;
        int i2 = R.color.mappls_nearby_white;
        builder.backgroundColor(Integer.valueOf(obtainStyledAttributes.getColor(i, ContextCompat.getColor(context, i2))));
        builder.toolbarBackgroundColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_mappls_nearby_toolbar_background, ContextCompat.getColor(context, i2))));
        builder.toolbarIcon(Integer.valueOf(obtainStyledAttributes.getResourceId(R.styleable.mappls_nearby_mappls_nearby_toolbar_icon, R.drawable.mappls_nearby_marker)));
        int i3 = R.styleable.mappls_nearby_mappls_nearby_toolbar_text_color;
        int i4 = R.color.mappls_nearby_primary_color;
        builder.toolbarTextColor(Integer.valueOf(obtainStyledAttributes.getColor(i3, ContextCompat.getColor(context, i4))));
        int i5 = R.styleable.mappls_nearby_mappls_nearby_toolbar_text;
        builder.toolbarText(obtainStyledAttributes.getString(i5) != null ? obtainStyledAttributes.getString(i5) : "Nearby Search");
        builder.addressBackgroundColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_mappls_nearby_address_background, ContextCompat.getColor(context, R.color.mappls_nearby_address_background_color))));
        builder.addressTooltipTextColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_mappls_nearby_address_tooltip_text_color, ContextCompat.getColor(context, R.color.mappls_nearby_address_tooltip_text_color))));
        builder.addressTextColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_mappls_nearby_address_text_color, ContextCompat.getColor(context, i4))));
        int i6 = R.styleable.mappls_nearby_mappls_nearby_change_location_button_text_color;
        int i7 = R.color.mappls_nearby_secondary_color;
        builder.changeLocationButtonTextColor(Integer.valueOf(obtainStyledAttributes.getColor(i6, ContextCompat.getColor(context, i7))));
        builder.useCurrentLocationButtonTextColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_mappls_nearby_use_current_location_button_text_color, ContextCompat.getColor(context, i7))));
        builder.selectedCategoryBackgroundColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_mappls_nearby_selected_category_background_color, ContextCompat.getColor(context, i7))));
        builder.selectedCategoryTextColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_mappls_nearby_selected_category_text_color, ContextCompat.getColor(context, i2))));
        builder.selectedCategoryTintColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_mappls_nearby_selected_category_tint_color, ContextCompat.getColor(context, i2))));
        builder.categoryBackgroundColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_mappls_nearby_category_background_color, ContextCompat.getColor(context, i2))));
        builder.categoryTextColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_mappls_nearby_category_text_color, ContextCompat.getColor(context, R.color.mappls_nearby_text_color))));
        builder.categoryTintColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_mappls_nearby_category_tint_color, ContextCompat.getColor(context, i7))));
        builder.submitButtonResource(Integer.valueOf(obtainStyledAttributes.getResourceId(R.styleable.mappls_nearby_mappls_nearby_submit_button_color, R.drawable.mappls_nearby_btn_bg)));
        builder.submitButtonTextColor(Integer.valueOf(obtainStyledAttributes.getColor(R.styleable.mappls_nearby_mappls_nearby_submit_text_color, ContextCompat.getColor(context, i2))));
        int i8 = R.styleable.mappls_nearby_mappls_nearby_submit_button_text;
        builder.submitButtonText(obtainStyledAttributes.getString(i8) != null ? obtainStyledAttributes.getString(i8) : "Next");
        int i9 = R.styleable.mappls_nearby_mappls_nearby_location_info_label_text;
        builder.locationInfoLabelText(obtainStyledAttributes.getString(i9) != null ? obtainStyledAttributes.getString(i9) : "Your location is auto-detected as");
        obtainStyledAttributes.recycle();
        return builder.build();
    }

    public static NearbyViewOption createFromNearbyOption(NearbyUIOption nearbyUIOption) {
        Builder builder = builder();
        builder.backgroundColor(nearbyUIOption.backgroundColor());
        builder.toolbarBackgroundColor(nearbyUIOption.nearbyToolbarColor());
        builder.toolbarIcon(nearbyUIOption.nearbyToolbarIcon());
        builder.toolbarBitmap(nearbyUIOption.nearbyToolbarBitmap());
        builder.toolbarTextColor(nearbyUIOption.nearbyToolbarTintColor());
        builder.addressBackgroundColor(nearbyUIOption.locationDetailsBackgroundColor());
        builder.addressTextColor(nearbyUIOption.locationDetailFormattedAddressTextColor());
        builder.addressTooltipTextColor(nearbyUIOption.locationDetailInfoTextColor());
        builder.changeLocationButtonTextColor(nearbyUIOption.changeLocationButtonTextColor());
        builder.useCurrentLocationButtonTextColor(nearbyUIOption.useCurrentLocationButtonTextColor());
        builder.submitButtonTextColor(nearbyUIOption.submitButtonTextColor());
        builder.submitButtonResource(nearbyUIOption.submitButtonResource());
        builder.submitButtonColor(nearbyUIOption.submitButtonColor());
        builder.submitButtonText(nearbyUIOption.submitButtonText());
        builder.selectedCategoryBackgroundColor(nearbyUIOption.selectedCategoryBackgroundColor());
        builder.selectedCategoryTextColor(nearbyUIOption.selectedCategoryTextColor());
        builder.selectedCategoryTintColor(nearbyUIOption.selectedCategoryTintColor());
        builder.categoryBackgroundColor(nearbyUIOption.categoryBackgroundColor());
        builder.categoryTextColor(nearbyUIOption.categoryTextColor());
        builder.categoryTintColor(nearbyUIOption.categoryTintColor());
        builder.locationInfoLabelText(nearbyUIOption.locationDetailsInfoLabelText());
        return builder.build();
    }

    @NonNull
    public abstract Integer addressBackgroundColor();

    @NonNull
    public abstract Integer addressTextColor();

    @NonNull
    public abstract Integer addressTooltipTextColor();

    @NonNull
    public abstract Integer backgroundColor();

    @NonNull
    public abstract Integer categoryBackgroundColor();

    @NonNull
    public abstract Integer categoryTextColor();

    @NonNull
    public abstract Integer categoryTintColor();

    @NonNull
    public abstract Integer changeLocationButtonTextColor();

    @NonNull
    public abstract String locationInfoLabelText();

    @NonNull
    public abstract Integer selectedCategoryBackgroundColor();

    @NonNull
    public abstract Integer selectedCategoryTextColor();

    @NonNull
    public abstract Integer selectedCategoryTintColor();

    @Nullable
    public abstract Integer submitButtonColor();

    @NonNull
    public abstract Integer submitButtonResource();

    @NonNull
    public abstract String submitButtonText();

    @NonNull
    public abstract Integer submitButtonTextColor();

    @NonNull
    public abstract Integer toolbarBackgroundColor();

    @Nullable
    public abstract Bitmap toolbarBitmap();

    @NonNull
    @DrawableRes
    public abstract Integer toolbarIcon();

    @NonNull
    public abstract String toolbarText();

    @NonNull
    public abstract Integer toolbarTextColor();

    @NonNull
    public abstract Integer useCurrentLocationButtonTextColor();
}
