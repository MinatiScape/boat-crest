package com.mappls.sdk.navigation.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.util.AttributeSet;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.navigation.ui.a;
@AutoValue
@Keep
/* loaded from: classes11.dex */
public abstract class NavigationOptions implements Parcelable {
    public static final int THEME_DAY = 1;
    public static final int THEME_DEFAULT = 0;
    public static final int THEME_NIGHT = 2;

    @Keep
    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract NavigationOptions build();

        public abstract Builder isUsingInternalMap(@NonNull Boolean bool);

        public abstract Builder mapplsMapDarkStyle(@NonNull String str);

        public abstract Builder mapplsMapLightStyle(@NonNull String str);

        public abstract Builder navigationDarkTheme(@NonNull @StyleRes Integer num);

        public abstract Builder navigationLightTheme(@NonNull @StyleRes Integer num);

        public abstract Builder navigationTheme(@NonNull Integer num);

        public abstract Builder showBottomInfoBar(@NonNull Boolean bool);

        public abstract Builder showCurrentSpeed(@NonNull Boolean bool);

        public abstract Builder showDayNightOption(@NonNull Boolean bool);

        public abstract Builder showInstructionBanner(@NonNull Boolean bool);

        public abstract Builder showNavigationSettingsOption(@NonNull Boolean bool);

        public abstract Builder showNextInstructionBanner(@NonNull Boolean bool);

        public abstract Builder showSearchDuringNavigationOption(@NonNull Boolean bool);

        public abstract Builder showSpeedWarning(@NonNull Boolean bool);

        public abstract Builder showTrafficOption(@NonNull Boolean bool);

        public abstract Builder showWarningMessage(@NonNull Boolean bool);
    }

    public static Builder builder() {
        Builder navigationDarkTheme = new a.C0648a().navigationLightTheme(Integer.valueOf(R.style.NavigationViewLight)).navigationDarkTheme(Integer.valueOf(R.style.NavigationViewDark));
        Boolean bool = Boolean.TRUE;
        return navigationDarkTheme.isUsingInternalMap(bool).mapplsMapDarkStyle("standard_night").mapplsMapLightStyle("standard_day").showTrafficOption(bool).showDayNightOption(Boolean.FALSE).showNavigationSettingsOption(bool).showSearchDuringNavigationOption(bool).showNextInstructionBanner(bool).showCurrentSpeed(bool).showSpeedWarning(bool).showBottomInfoBar(bool).showInstructionBanner(bool).showWarningMessage(bool).navigationTheme(1);
    }

    public static NavigationOptions createFromAttributes(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.NavigationView, 0, 0);
        Builder builder = builder();
        builder.navigationLightTheme(Integer.valueOf(obtainStyledAttributes.getResourceId(R.styleable.NavigationView_navigationLightTheme, R.style.NavigationViewLight)));
        builder.navigationDarkTheme(Integer.valueOf(obtainStyledAttributes.getResourceId(R.styleable.NavigationView_navigationDarkTheme, R.style.NavigationViewDark)));
        builder.isUsingInternalMap(Boolean.valueOf(obtainStyledAttributes.getBoolean(R.styleable.NavigationView_isUsingInternalMap, true)));
        int i = R.styleable.NavigationView_mapplsMapLightStyle;
        if (obtainStyledAttributes.hasValue(i) && obtainStyledAttributes.getString(i) != null) {
            builder.mapplsMapLightStyle(obtainStyledAttributes.getString(i));
        }
        int i2 = R.styleable.NavigationView_mapplsMapDarkStyle;
        if (obtainStyledAttributes.hasValue(i2) && obtainStyledAttributes.getString(i2) != null) {
            builder.mapplsMapDarkStyle(obtainStyledAttributes.getString(i2));
        }
        builder.showDayNightOption(Boolean.valueOf(obtainStyledAttributes.getBoolean(R.styleable.NavigationView_showDayNightOption, false)));
        builder.showNavigationSettingsOption(Boolean.valueOf(obtainStyledAttributes.getBoolean(R.styleable.NavigationView_showSettingsOption, true)));
        builder.showTrafficOption(Boolean.valueOf(obtainStyledAttributes.getBoolean(R.styleable.NavigationView_showTrafficOption, true)));
        builder.showSearchDuringNavigationOption(Boolean.valueOf(obtainStyledAttributes.getBoolean(R.styleable.NavigationView_showSearchDuringNavigationOption, true)));
        builder.navigationTheme(Integer.valueOf(obtainStyledAttributes.getInt(R.styleable.NavigationView_navigationTheme, 1)));
        builder.showNextInstructionBanner(Boolean.valueOf(obtainStyledAttributes.getBoolean(R.styleable.NavigationView_showNextInstructionBanner, true)));
        builder.showCurrentSpeed(Boolean.valueOf(obtainStyledAttributes.getBoolean(R.styleable.NavigationView_showCurrentSpeed, true)));
        builder.showSpeedWarning(Boolean.valueOf(obtainStyledAttributes.getBoolean(R.styleable.NavigationView_showSpeedWarning, true)));
        builder.showBottomInfoBar(Boolean.valueOf(obtainStyledAttributes.getBoolean(R.styleable.NavigationView_showBottomInfoBar, true)));
        builder.showInstructionBanner(Boolean.valueOf(obtainStyledAttributes.getBoolean(R.styleable.NavigationView_showInstructionBanner, true)));
        builder.showWarningMessage(Boolean.valueOf(obtainStyledAttributes.getBoolean(R.styleable.NavigationView_showWarningMessage, true)));
        return builder.build();
    }

    @NonNull
    public abstract Boolean isUsingInternalMap();

    @NonNull
    public abstract String mapplsMapDarkStyle();

    @NonNull
    public abstract String mapplsMapLightStyle();

    @NonNull
    @StyleRes
    public abstract Integer navigationDarkTheme();

    @NonNull
    @StyleRes
    public abstract Integer navigationLightTheme();

    @NonNull
    public abstract Integer navigationTheme();

    @NonNull
    public abstract Boolean showBottomInfoBar();

    @NonNull
    public abstract Boolean showCurrentSpeed();

    @NonNull
    public abstract Boolean showDayNightOption();

    @NonNull
    public abstract Boolean showInstructionBanner();

    @NonNull
    public abstract Boolean showNavigationSettingsOption();

    @NonNull
    public abstract Boolean showNextInstructionBanner();

    @NonNull
    public abstract Boolean showSearchDuringNavigationOption();

    @NonNull
    public abstract Boolean showSpeedWarning();

    @NonNull
    public abstract Boolean showTrafficOption();

    @NonNull
    public abstract Boolean showWarningMessage();
}
