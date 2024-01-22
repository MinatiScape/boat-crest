package com.mappls.sdk.navigation.ui.theme;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.widgets.CompassView;
import com.mappls.sdk.navigation.ui.NavigationOptions;
import com.mappls.sdk.navigation.ui.R;
/* loaded from: classes11.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static int f13029a = 1;

    public static int a(Context context, int i, int i2) {
        int i3 = f(context, i).resourceId;
        return i3 != -1 && ((-16777216) & i3) != 0 && (16711680 & i3) != 0 ? i3 : i2;
    }

    public static void a(Context context) {
        f13029a = a() ? 1 : 2;
    }

    public static void a(Context context, MapView mapView) {
        CompassView compassView;
        int i;
        if (mapView == null || mapView.getCompassView() == null) {
            return;
        }
        if (a()) {
            compassView = mapView.getCompassView();
            i = R.drawable.compass_background_dark;
        } else {
            compassView = mapView.getCompassView();
            i = R.drawable.compass_background_light;
        }
        compassView.setBackgroundResource(i);
    }

    public static void a(Context context, NavigationOptions navigationOptions) {
        if (navigationOptions.navigationTheme().intValue() == 0) {
            f13029a = (context.getResources().getConfiguration().uiMode & 48) == 32 ? 2 : 1;
        } else {
            f13029a = navigationOptions.navigationTheme().intValue();
        }
        context.setTheme((a() ? navigationOptions.navigationDarkTheme() : navigationOptions.navigationLightTheme()).intValue());
    }

    public static boolean a() {
        return f13029a == 2;
    }

    public static int b(Context context, int i) {
        TypedValue f = f(context, i);
        int i2 = f.type;
        return (i2 < 28 || i2 > 31) ? ContextCompat.getColor(context, f.resourceId) : f.data;
    }

    public static ColorStateList c(Context context, int i) {
        return ContextCompat.getColorStateList(context, f(context, i).resourceId);
    }

    public static Drawable d(Context context, int i) {
        return AppCompatResources.getDrawable(context, f(context, i).resourceId);
    }

    public static int e(Context context, int i) {
        return f(context, i).resourceId;
    }

    @NonNull
    public static TypedValue f(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue;
    }
}
