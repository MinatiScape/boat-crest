package com.mappls.sdk.category.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Parcelable;
import android.util.AttributeSet;
import androidx.annotation.DrawableRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.annotation.VisibleForTesting;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.category.R;
import com.mappls.sdk.category.model.c;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class SearchCategoryUIOption implements Parcelable {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract Builder backIcon(@NonNull @DrawableRes Integer num);

        public abstract Builder backgroundColor(@NonNull Integer num);

        public abstract SearchCategoryUIOption build();

        public abstract Builder hintTextColor(@NonNull Integer num);

        public abstract Builder iconTintColor(@NonNull Integer num);

        public abstract Builder itemTextColor(@NonNull Integer num);

        public abstract Builder nextButtonColor(@NonNull Integer num);

        public abstract Builder nextButtonTextColor(@NonNull Integer num);

        public abstract Builder polylineColor(@NonNull Integer num);

        public abstract Builder polylineWidth(@NonNull Float f);

        public abstract Builder resultCountTextColor(@NonNull Integer num);

        public abstract Builder resultMessageTextColor(@NonNull Integer num);

        public abstract Builder resultPlaceAddressTextColor(@NonNull Integer num);

        public abstract Builder resultPlaceDistanceTextColor(@NonNull Integer num);

        public abstract Builder resultPlaceNameTextColor(@NonNull Integer num);

        public abstract Builder searchTextColor(@NonNull Integer num);
    }

    public static Builder builder() {
        return new c.a().iconTintColor(Integer.valueOf(Color.parseColor("#3A78E7"))).itemTextColor(Integer.valueOf(Color.parseColor("#212121"))).nextButtonTextColor(-1).nextButtonColor(Integer.valueOf(Color.parseColor("#3A78E7"))).searchTextColor(Integer.valueOf(Color.parseColor("#212121"))).backgroundColor(-1).backIcon(Integer.valueOf(R.drawable.mappls_category_ic_baseline_arrow_back)).hintTextColor(Integer.valueOf(Color.parseColor("#797979"))).resultCountTextColor(Integer.valueOf(Color.parseColor("#3D3D3D"))).resultPlaceNameTextColor(Integer.valueOf(Color.parseColor("#212121"))).resultPlaceAddressTextColor(Integer.valueOf(Color.parseColor("#3D3D3D"))).resultPlaceDistanceTextColor(Integer.valueOf(Color.parseColor("#3D3D3D"))).resultMessageTextColor(Integer.valueOf(Color.parseColor("#3D3D3D"))).polylineWidth(Float.valueOf(4.0f)).polylineColor(Integer.valueOf(Color.parseColor("#07b9fc")));
    }

    @NonNull
    public static Builder createFromAttributes(@NonNull Context context) {
        return createFromAttributes(context, (AttributeSet) null);
    }

    @NonNull
    public static Builder createFromAttributes(@NonNull Context context, @Nullable @StyleRes int i) {
        return createFromAttributes(context.obtainStyledAttributes(null, R.styleable.mappls_category, 0, i));
    }

    @NonNull
    public static Builder createFromAttributes(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        return createFromAttributes(context.obtainStyledAttributes(attributeSet, R.styleable.mappls_category, 0, 0));
    }

    @VisibleForTesting
    public static Builder createFromAttributes(@Nullable TypedArray typedArray) {
        Builder builder = builder();
        if (typedArray != null) {
            try {
                builder.iconTintColor(Integer.valueOf(typedArray.getColor(R.styleable.mappls_category_mappls_category_icon_tint_color, Color.parseColor("#3A78E7"))));
                builder.itemTextColor(Integer.valueOf(typedArray.getColor(R.styleable.mappls_category_mappls_category_item_text_color, Color.parseColor("#212121"))));
                builder.nextButtonTextColor(Integer.valueOf(typedArray.getColor(R.styleable.mappls_category_mappls_category_button_next_text_color, -1)));
                builder.nextButtonColor(Integer.valueOf(typedArray.getColor(R.styleable.mappls_category_mappls_category_button_next_background, Color.parseColor("#3A78E7"))));
                builder.searchTextColor(Integer.valueOf(typedArray.getColor(R.styleable.mappls_category_mappls_category_search_text_color, Color.parseColor("#212121"))));
                builder.backgroundColor(Integer.valueOf(typedArray.getColor(R.styleable.mappls_category_mappls_category_background, -1)));
                builder.backIcon(Integer.valueOf(typedArray.getResourceId(R.styleable.mappls_category_mappls_category_back_button_icon, R.drawable.mappls_category_ic_baseline_arrow_back)));
                builder.hintTextColor(Integer.valueOf(typedArray.getColor(R.styleable.mappls_category_mappls_category_search_hint_text_color, Color.parseColor("#797979"))));
                builder.resultCountTextColor(Integer.valueOf(typedArray.getColor(R.styleable.mappls_category_mappls_category_result_count_text_color, Color.parseColor("#3D3D3D"))));
                builder.resultPlaceNameTextColor(Integer.valueOf(typedArray.getColor(R.styleable.mappls_category_mappls_category_result_place_name_text_color, Color.parseColor("#212121"))));
                builder.resultPlaceAddressTextColor(Integer.valueOf(typedArray.getColor(R.styleable.mappls_category_mappls_category_result_place_address_text_color, Color.parseColor("#3D3D3D"))));
                builder.resultPlaceDistanceTextColor(Integer.valueOf(typedArray.getColor(R.styleable.mappls_category_mappls_category_result_place_distance_text_color, Color.parseColor("#3D3D3D"))));
                builder.resultMessageTextColor(Integer.valueOf(typedArray.getColor(R.styleable.mappls_category_mappls_category_result_message_text_color, Color.parseColor("#3D3D3D"))));
                builder.polylineWidth(Float.valueOf(typedArray.getFloat(R.styleable.mappls_category_mappls_category_result_polyline_width, 4.0f)));
                builder.polylineColor(Integer.valueOf(typedArray.getColor(R.styleable.mappls_category_mappls_category_result_polyline_color, Color.parseColor("#07b9fc"))));
            } finally {
                typedArray.recycle();
            }
        }
        return builder;
    }

    @NonNull
    public abstract Integer backIcon();

    @NonNull
    public abstract Integer backgroundColor();

    @NonNull
    public abstract Integer hintTextColor();

    @NonNull
    public abstract Integer iconTintColor();

    @NonNull
    public abstract Integer itemTextColor();

    @NonNull
    public abstract Integer nextButtonColor();

    @NonNull
    public abstract Integer nextButtonTextColor();

    @NonNull
    public abstract Integer polylineColor();

    @NonNull
    public abstract Float polylineWidth();

    @NonNull
    public abstract Integer resultCountTextColor();

    @NonNull
    public abstract Integer resultMessageTextColor();

    @NonNull
    public abstract Integer resultPlaceAddressTextColor();

    @NonNull
    public abstract Integer resultPlaceDistanceTextColor();

    @NonNull
    public abstract Integer resultPlaceNameTextColor();

    @NonNull
    public abstract Integer searchTextColor();
}
