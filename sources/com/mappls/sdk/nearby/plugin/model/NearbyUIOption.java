package com.mappls.sdk.nearby.plugin.model;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Parcelable;
import androidx.annotation.DrawableRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.nearby.plugin.R;
import com.mappls.sdk.nearby.plugin.model.c;
@AutoValue
@Keep
/* loaded from: classes10.dex */
public abstract class NearbyUIOption implements Parcelable {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        public abstract Builder backgroundColor(@NonNull Integer num);

        public abstract NearbyUIOption build();

        public abstract Builder categoryBackgroundColor(@NonNull Integer num);

        public abstract Builder categoryFilterBackgroundColor(@NonNull Integer num);

        public abstract Builder categoryTextColor(@NonNull Integer num);

        public abstract Builder categoryTintColor(@NonNull Integer num);

        public abstract Builder changeLocationButtonTextColor(@NonNull Integer num);

        public abstract Builder detailListBackgroundColor(@NonNull Integer num);

        public abstract Builder detailListSeperatorBackgroundColor(@NonNull Integer num);

        public abstract Builder distanceTextColor(@NonNull Integer num);

        public abstract Builder locationDetailFormattedAddressTextColor(@NonNull Integer num);

        public abstract Builder locationDetailInfoTextColor(@NonNull Integer num);

        public abstract Builder locationDetailsBackgroundColor(@NonNull Integer num);

        public abstract Builder locationDetailsInfoLabelText(@NonNull String str);

        public abstract Builder nearbyToolbarBitmap(@Nullable Bitmap bitmap);

        public abstract Builder nearbyToolbarColor(@NonNull Integer num);

        public Builder nearbyToolbarIcon(@Nullable Bitmap bitmap) {
            return nearbyToolbarBitmap(bitmap);
        }

        public abstract Builder nearbyToolbarIcon(@NonNull @DrawableRes Integer num);

        public abstract Builder nearbyToolbarTintColor(@NonNull Integer num);

        public abstract Builder nextButtonBackgroundColor(@NonNull Integer num);

        public abstract Builder paginationBackgroundColor(@NonNull Integer num);

        public abstract Builder placeAddressTextColor(@NonNull Integer num);

        public abstract Builder placeNameTextColor(@NonNull Integer num);

        public abstract Builder prevButtonBackgroundColor(@NonNull Integer num);

        public abstract Builder refLocationBitmap(@Nullable Bitmap bitmap);

        public abstract Builder refLocationCircleAlpha(@NonNull Float f);

        public abstract Builder refLocationCircleColor(@NonNull Integer num);

        public Builder refLocationIcon(@Nullable Bitmap bitmap) {
            return refLocationBitmap(bitmap);
        }

        public abstract Builder refLocationIcon(@NonNull Integer num);

        public abstract Builder selectedCategoryBackgroundColor(@NonNull Integer num);

        public abstract Builder selectedCategoryTextColor(@NonNull Integer num);

        public abstract Builder selectedCategoryTintColor(@NonNull Integer num);

        public abstract Builder selectedTabTextColor(@NonNull Integer num);

        public abstract Builder showDefaultMap(@NonNull Boolean bool);

        public abstract Builder submitButtonColor(@Nullable Integer num);

        public abstract Builder submitButtonResource(@NonNull Integer num);

        public abstract Builder submitButtonText(@NonNull String str);

        public abstract Builder submitButtonTextColor(@NonNull Integer num);

        public abstract Builder tabBackgroundColor(@NonNull Integer num);

        public abstract Builder tabIconTint(@NonNull Integer num);

        public abstract Builder tabIndicatorColor(@NonNull Integer num);

        public abstract Builder tabTextColor(@NonNull Integer num);

        public abstract Builder toolbarColor(@NonNull Integer num);

        public abstract Builder toolbarTintColor(@NonNull Integer num);

        public abstract Builder useCurrentLocationButtonTextColor(@NonNull Integer num);
    }

    public static Builder builder() {
        return new c.a().backgroundColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).detailListBackgroundColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).toolbarColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).nearbyToolbarColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).prevButtonBackgroundColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).nextButtonBackgroundColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).nearbyToolbarIcon(Integer.valueOf(R.drawable.mappls_nearby_marker)).toolbarTintColor(Integer.valueOf(Color.parseColor("#212121"))).nearbyToolbarTintColor(Integer.valueOf(Color.parseColor("#212121"))).detailListSeperatorBackgroundColor(Integer.valueOf(Color.parseColor("#212121"))).locationDetailsBackgroundColor(Integer.valueOf(Color.parseColor("#FAFAFA"))).categoryFilterBackgroundColor(Integer.valueOf(Color.parseColor("#FAFAFA"))).locationDetailInfoTextColor(Integer.valueOf(Color.parseColor("#797979"))).locationDetailsInfoLabelText("Your location is auto-detected as").locationDetailFormattedAddressTextColor(Integer.valueOf(Color.parseColor("#212121"))).changeLocationButtonTextColor(Integer.valueOf(Color.parseColor("#3A78E7"))).useCurrentLocationButtonTextColor(Integer.valueOf(Color.parseColor("#3A78E7"))).submitButtonResource(Integer.valueOf(R.drawable.mappls_nearby_btn_bg)).submitButtonTextColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).selectedCategoryBackgroundColor(Integer.valueOf(Color.parseColor("#3A78E7"))).selectedCategoryTextColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).selectedCategoryTintColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).categoryBackgroundColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).tabBackgroundColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).categoryTextColor(Integer.valueOf(Color.parseColor("#3D3D3D"))).categoryTintColor(Integer.valueOf(Color.parseColor("#3A78E7"))).paginationBackgroundColor(Integer.valueOf(Color.parseColor(Constants.WHITE))).tabTextColor(Integer.valueOf(Color.parseColor("#797979"))).selectedTabTextColor(Integer.valueOf(Color.parseColor("#3A78E7"))).tabIndicatorColor(Integer.valueOf(Color.parseColor("#3A78E7"))).tabIconTint(Integer.valueOf(R.color.mappls_nearby_icon_tab_color)).placeNameTextColor(Integer.valueOf(Color.parseColor("#212121"))).distanceTextColor(Integer.valueOf(Color.parseColor("#99212121"))).placeAddressTextColor(Integer.valueOf(Color.parseColor("#212121"))).refLocationIcon(Integer.valueOf(R.drawable.mappls_nearby_location_marker)).refLocationCircleColor(Integer.valueOf(Color.parseColor("#0099FF"))).submitButtonText("Next").refLocationCircleAlpha(Float.valueOf(0.22f)).showDefaultMap(Boolean.TRUE);
    }

    @NonNull
    public abstract Integer backgroundColor();

    @NonNull
    public abstract Integer categoryBackgroundColor();

    @NonNull
    public abstract Integer categoryFilterBackgroundColor();

    @NonNull
    public abstract Integer categoryTextColor();

    @NonNull
    public abstract Integer categoryTintColor();

    @NonNull
    public abstract Integer changeLocationButtonTextColor();

    @NonNull
    public abstract Integer detailListBackgroundColor();

    @NonNull
    public abstract Integer detailListSeperatorBackgroundColor();

    @NonNull
    public abstract Integer distanceTextColor();

    @NonNull
    public abstract Integer locationDetailFormattedAddressTextColor();

    @NonNull
    public abstract Integer locationDetailInfoTextColor();

    @NonNull
    public abstract Integer locationDetailsBackgroundColor();

    @NonNull
    public abstract String locationDetailsInfoLabelText();

    @Nullable
    public abstract Bitmap nearbyToolbarBitmap();

    @NonNull
    public abstract Integer nearbyToolbarColor();

    @NonNull
    @DrawableRes
    public abstract Integer nearbyToolbarIcon();

    @NonNull
    public abstract Integer nearbyToolbarTintColor();

    @NonNull
    public abstract Integer nextButtonBackgroundColor();

    @NonNull
    public abstract Integer paginationBackgroundColor();

    @NonNull
    public abstract Integer placeAddressTextColor();

    @NonNull
    public abstract Integer placeNameTextColor();

    @NonNull
    public abstract Integer prevButtonBackgroundColor();

    @Nullable
    public abstract Bitmap refLocationBitmap();

    @NonNull
    public abstract Float refLocationCircleAlpha();

    @NonNull
    public abstract Integer refLocationCircleColor();

    @NonNull
    public abstract Integer refLocationIcon();

    @NonNull
    public abstract Integer selectedCategoryBackgroundColor();

    @NonNull
    public abstract Integer selectedCategoryTextColor();

    @NonNull
    public abstract Integer selectedCategoryTintColor();

    @NonNull
    public abstract Integer selectedTabTextColor();

    @NonNull
    public abstract Boolean showDefaultMap();

    @Nullable
    public abstract Integer submitButtonColor();

    @NonNull
    public abstract Integer submitButtonResource();

    @NonNull
    public abstract String submitButtonText();

    @NonNull
    public abstract Integer submitButtonTextColor();

    @NonNull
    public abstract Integer tabBackgroundColor();

    @NonNull
    public abstract Integer tabIconTint();

    @NonNull
    public abstract Integer tabIndicatorColor();

    @NonNull
    public abstract Integer tabTextColor();

    @NonNull
    public abstract Integer toolbarColor();

    @NonNull
    public abstract Integer toolbarTintColor();

    @NonNull
    public abstract Integer useCurrentLocationButtonTextColor();
}
