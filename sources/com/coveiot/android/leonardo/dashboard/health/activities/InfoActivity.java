package com.coveiot.android.leonardo.dashboard.health.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.theme.BaseActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class InfoActivity extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public static final void r(InfoActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
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

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra(AppConstants.SCREEN_NAME.getValue());
        Intrinsics.checkNotNull(stringExtra);
        if (stringExtra.equals(getResources().getString(R.string.hrv_caps))) {
            setContentView(R.layout.info_activity);
        } else if (BleApiManager.getInstance(this).getDeviceType() != DeviceType.smaR9 && BleApiManager.getInstance(this).getDeviceType() != DeviceType.SMA_WAVE_GENESIS_PRO && BleApiManager.getInstance(this).getDeviceType() != DeviceType.SMA_WAVE_ELEVATE_PRO && BleApiManager.getInstance(this).getDeviceType() != DeviceType.SMA_WAVE_GLORY_PRO && BleApiManager.getInstance(this).getDeviceType() != DeviceType.SMA_ULTIMA_VOGUE && BleApiManager.getInstance(this).getDeviceType() != DeviceType.SMA_LUNAR_SEEK && BleApiManager.getInstance(this).getDeviceType() != DeviceType.SMA_LUNAR_COMET && BleApiManager.getInstance(this).getDeviceType() != DeviceType.SMA_LUNAR_VELOCITY) {
            setContentView(R.layout.info_activity);
        } else {
            setContentView(R.layout.info_activity_s12);
        }
        if (stringExtra.equals(getResources().getString(R.string.hrv_caps))) {
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.hrv_layout_info);
            if (linearLayout != null) {
                linearLayout.setVisibility(0);
            }
            LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(R.id.stress_layout_info);
            if (linearLayout2 != null) {
                linearLayout2.setVisibility(8);
            }
            LinearLayout linearLayout3 = (LinearLayout) _$_findCachedViewById(R.id.sleep_layout_info);
            if (linearLayout3 != null) {
                linearLayout3.setVisibility(8);
            }
            TextView textView = (TextView) _$_findCachedViewById(R.id.title_text);
            if (textView != null) {
                textView.setText(getResources().getString(R.string.hrv_info_title));
            }
            LinearLayout linearLayout4 = (LinearLayout) _$_findCachedViewById(R.id.breath_quality_info_layout);
            if (linearLayout4 != null) {
                linearLayout4.setVisibility(8);
            }
        } else if (stringExtra.equals(getResources().getString(R.string.sleep))) {
            LinearLayout linearLayout5 = (LinearLayout) _$_findCachedViewById(R.id.hrv_layout_info);
            if (linearLayout5 != null) {
                linearLayout5.setVisibility(8);
            }
            LinearLayout linearLayout6 = (LinearLayout) _$_findCachedViewById(R.id.stress_layout_info);
            if (linearLayout6 != null) {
                linearLayout6.setVisibility(8);
            }
            if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isSleepScoreSupportsFromBand()) {
                View _$_findCachedViewById = _$_findCachedViewById(R.id.sleep_score_info_ido);
                if (_$_findCachedViewById != null) {
                    _$_findCachedViewById.setVisibility(0);
                }
                LinearLayout linearLayout7 = (LinearLayout) _$_findCachedViewById(R.id.sleep_layout_info);
                if (linearLayout7 != null) {
                    linearLayout7.setVisibility(8);
                }
            } else {
                View _$_findCachedViewById2 = _$_findCachedViewById(R.id.sleep_score_info_ido);
                if (_$_findCachedViewById2 != null) {
                    _$_findCachedViewById2.setVisibility(8);
                }
                LinearLayout linearLayout8 = (LinearLayout) _$_findCachedViewById(R.id.sleep_layout_info);
                if (linearLayout8 != null) {
                    linearLayout8.setVisibility(0);
                }
            }
            TextView textView2 = (TextView) _$_findCachedViewById(R.id.title_text);
            if (textView2 != null) {
                textView2.setText(getResources().getString(R.string.sleep_info_title));
            }
            LinearLayout linearLayout9 = (LinearLayout) _$_findCachedViewById(R.id.breath_quality_info_layout);
            if (linearLayout9 != null) {
                linearLayout9.setVisibility(8);
            }
        } else if (stringExtra.equals(getResources().getString(R.string.breath_quality))) {
            LinearLayout linearLayout10 = (LinearLayout) _$_findCachedViewById(R.id.hrv_layout_info);
            if (linearLayout10 != null) {
                linearLayout10.setVisibility(8);
            }
            LinearLayout linearLayout11 = (LinearLayout) _$_findCachedViewById(R.id.stress_layout_info);
            if (linearLayout11 != null) {
                linearLayout11.setVisibility(8);
            }
            LinearLayout linearLayout12 = (LinearLayout) _$_findCachedViewById(R.id.sleep_layout_info);
            if (linearLayout12 != null) {
                linearLayout12.setVisibility(8);
            }
            TextView textView3 = (TextView) _$_findCachedViewById(R.id.title_text);
            if (textView3 != null) {
                textView3.setText(getResources().getString(R.string.breath_quality));
            }
            LinearLayout linearLayout13 = (LinearLayout) _$_findCachedViewById(R.id.breath_quality_info_layout);
            if (linearLayout13 != null) {
                linearLayout13.setVisibility(0);
            }
        } else {
            LinearLayout linearLayout14 = (LinearLayout) _$_findCachedViewById(R.id.stress_layout_info);
            if (linearLayout14 != null) {
                linearLayout14.setVisibility(0);
            }
            LinearLayout linearLayout15 = (LinearLayout) _$_findCachedViewById(R.id.hrv_layout_info);
            if (linearLayout15 != null) {
                linearLayout15.setVisibility(8);
            }
            LinearLayout linearLayout16 = (LinearLayout) _$_findCachedViewById(R.id.sleep_layout_info);
            if (linearLayout16 != null) {
                linearLayout16.setVisibility(8);
            }
            TextView textView4 = (TextView) _$_findCachedViewById(R.id.title_text);
            if (textView4 != null) {
                textView4.setText(getResources().getString(R.string.stress_info_title));
            }
            LinearLayout linearLayout17 = (LinearLayout) _$_findCachedViewById(R.id.breath_quality_info_layout);
            if (linearLayout17 != null) {
                linearLayout17.setVisibility(8);
            }
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (!companion.isIDODevice(this) && !companion.isTouchELXDevice(this)) {
                if (companion.isCADevice(this) || companion.isCYDevice(this) || companion.isPS1Device(this) || companion.isBESDevice(this)) {
                    int i = R.id.stress_range_tv4;
                    ((TextView) _$_findCachedViewById(i)).setText(getResources().getString(R.string.high));
                    ((TextView) _$_findCachedViewById(i)).setTextColor(getColor(R.color.color_951330));
                    int i2 = R.id.stress_range_tv3;
                    ((TextView) _$_findCachedViewById(i2)).setText(getResources().getString(R.string.moderate));
                    ((TextView) _$_findCachedViewById(i2)).setTextColor(getColor(R.color.color_ac3d1e));
                    int i3 = R.id.stress_range_tv2;
                    ((TextView) _$_findCachedViewById(i3)).setText(getResources().getString(R.string.mild));
                    ((TextView) _$_findCachedViewById(i3)).setTextColor(getColor(R.color.color_d59f27));
                    int i4 = R.id.stress_range_tv1;
                    ((TextView) _$_findCachedViewById(i4)).setText(getResources().getString(R.string.relaxed));
                    ((TextView) _$_findCachedViewById(i4)).setTextColor(getColor(R.color.color_1fbba6));
                    ((TextView) _$_findCachedViewById(R.id.stress_value_tv4)).setText(getResources().getString(R.string.stress_range_ca3_4));
                    ((TextView) _$_findCachedViewById(R.id.stress_value_tv3)).setText(getResources().getString(R.string.stress_range_ca3_3));
                    ((TextView) _$_findCachedViewById(R.id.stress_value_tv2)).setText(getResources().getString(R.string.stress_range_ca3_2));
                    ((TextView) _$_findCachedViewById(R.id.stress_value_tv1)).setText(getResources().getString(R.string.stress_range_ca3_1));
                }
            } else {
                int i5 = R.id.stress_range_tv4;
                ((TextView) _$_findCachedViewById(i5)).setText(getResources().getString(R.string.high));
                ((TextView) _$_findCachedViewById(i5)).setTextColor(getColor(R.color.color_951330));
                int i6 = R.id.stress_range_tv3;
                ((TextView) _$_findCachedViewById(i6)).setText(getResources().getString(R.string.medium));
                ((TextView) _$_findCachedViewById(i6)).setTextColor(getColor(R.color.color_ac3d1e));
                int i7 = R.id.stress_range_tv2;
                ((TextView) _$_findCachedViewById(i7)).setText(getResources().getString(R.string.normal));
                ((TextView) _$_findCachedViewById(i7)).setTextColor(getColor(R.color.color_d59f27));
                int i8 = R.id.stress_range_tv1;
                ((TextView) _$_findCachedViewById(i8)).setText(getResources().getString(R.string.relax));
                ((TextView) _$_findCachedViewById(i8)).setTextColor(getColor(R.color.color_1fbba6));
                ((TextView) _$_findCachedViewById(R.id.stress_value_tv4)).setText(getResources().getString(R.string.stress_range_ido4));
                ((TextView) _$_findCachedViewById(R.id.stress_value_tv3)).setText(getResources().getString(R.string.stress_range_ido3));
                ((TextView) _$_findCachedViewById(R.id.stress_value_tv2)).setText(getResources().getString(R.string.stress_range_ido2));
                ((TextView) _$_findCachedViewById(R.id.stress_value_tv1)).setText(getResources().getString(R.string.stress_range_ido1));
            }
        }
        ImageView imageView = (ImageView) _$_findCachedViewById(R.id.iv_close);
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.activities.k
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    InfoActivity.r(InfoActivity.this, view);
                }
            });
        }
    }
}
