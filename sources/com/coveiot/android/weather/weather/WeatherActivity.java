package com.coveiot.android.weather.weather;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.utils.ThemeConstants;
import com.coveiot.android.weather.R;
import com.coveiot.android.weather.weather.WeatherAppPreferenceManager;
import com.coveiot.android.weather.weather.fragments.FragmentWeatherDetails;
import com.coveiot.android.weather.weather.fragments.FragmentWeatherDisabled;
import com.coveiot.android.weather.weather.fragments.FragmentWeatherSettings;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class WeatherActivity extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ImageView p;
    public Context q;
    public boolean r;

    public static final void s(WeatherActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void t(WeatherActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.replaceFragment(FragmentWeatherSettings.Companion.newInstance());
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    public final boolean isFromSettingScreen() {
        return this.r;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
            Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.container);
            Intrinsics.checkNotNull(findFragmentById);
            if (findFragmentById instanceof FragmentWeatherSettings) {
                WeatherAppPreferenceManager.Companion companion = WeatherAppPreferenceManager.Companion;
                Context context = this.q;
                if (context == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    context = null;
                }
                WeatherAppPreferenceManager companion2 = companion.getInstance(context);
                Intrinsics.checkNotNull(companion2);
                if (Intrinsics.areEqual(companion2.isWeatherEnabled(), Boolean.FALSE)) {
                    if (this.r) {
                        finish();
                        return;
                    } else {
                        replaceFragment(FragmentWeatherDisabled.Companion.newInstance());
                        return;
                    }
                }
                replaceFragment(FragmentWeatherDetails.Companion.newInstance());
                return;
            }
            finish();
            return;
        }
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_weather);
        this.q = this;
        View findViewById = findViewById(R.id.iv_setting);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<ImageView>(R.id.iv_setting)");
        this.p = (ImageView) findViewById;
        View findViewById2 = findViewById(R.id.tvSaveWeather);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(R.id.tvSaveWeather)");
        TextView textView = (TextView) findViewById2;
        ViewModel viewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this).get(WeatherViewModel::class.java)");
        WeatherViewModel weatherViewModel = (WeatherViewModel) viewModel;
        if (getIntent() != null) {
            this.r = getIntent().getBooleanExtra(ThemeConstants.IS_WEATHER_FROM_SETTING_SCREEN.getValue(), false);
        }
        if (this.r) {
            replaceFragment(FragmentWeatherSettings.Companion.newInstance());
        } else {
            WeatherAppPreferenceManager companion = WeatherAppPreferenceManager.Companion.getInstance(this);
            Intrinsics.checkNotNull(companion);
            Boolean isWeatherEnabled = companion.isWeatherEnabled();
            Intrinsics.checkNotNull(isWeatherEnabled);
            if (isWeatherEnabled.booleanValue()) {
                replaceFragment(FragmentWeatherDetails.Companion.newInstance());
            } else {
                replaceFragment(FragmentWeatherDisabled.Companion.newInstance());
            }
        }
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weather.weather.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WeatherActivity.s(WeatherActivity.this, view);
            }
        });
        ImageView imageView = this.p;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_setting");
            imageView = null;
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weather.weather.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WeatherActivity.t(WeatherActivity.this, view);
            }
        });
    }

    public final void replaceFragment(@NotNull Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        setupToolbar(fragment);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
    }

    public final void setFromSettingScreen(boolean z) {
        this.r = z;
    }

    public final void setupToolbar(@NotNull Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        ImageView imageView = null;
        if (fragment instanceof FragmentWeatherSettings) {
            ImageView imageView2 = this.p;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_setting");
            } else {
                imageView = imageView2;
            }
            imageView.setVisibility(8);
            ((Button) _$_findCachedViewById(R.id.btn_ok)).setVisibility(8);
            ((TextView) findViewById(R.id.toolbar_title)).setText(R.string.weather_setting);
            return;
        }
        ImageView imageView3 = this.p;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_setting");
        } else {
            imageView = imageView3;
        }
        imageView.setVisibility(0);
        ((Button) _$_findCachedViewById(R.id.btn_ok)).setVisibility(8);
        ((TextView) findViewById(R.id.toolbar_title)).setText(R.string.weather_details);
    }
}
