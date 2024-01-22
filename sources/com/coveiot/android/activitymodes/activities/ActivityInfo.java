package com.coveiot.android.activitymodes.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.theme.BaseActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityInfo extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public static final void r(ActivityInfo this$0, View view) {
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
        setContentView(R.layout.activity_info);
        ((TextView) _$_findCachedViewById(R.id.done_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.e1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityInfo.r(ActivityInfo.this, view);
            }
        });
        DeviceType deviceType = BleApiManager.getInstance(this).getDeviceType();
        if (deviceType.equals(DeviceType.smaF2)) {
            ((ImageView) _$_findCachedViewById(R.id.info_image)).setBackgroundResource(R.drawable.band_smaf2_image);
        } else if (deviceType.equals(DeviceType.smaR9)) {
            ((ImageView) _$_findCachedViewById(R.id.info_image)).setBackgroundResource(R.drawable.band_smas12_image);
        } else if (deviceType.equals(DeviceType.kh17)) {
            ((ImageView) _$_findCachedViewById(R.id.info_image)).setBackgroundResource(R.drawable.band_moyang_y20_image);
        } else if (deviceType.equals(DeviceType.crpGPF5)) {
            ((ImageView) _$_findCachedViewById(R.id.info_image)).setBackgroundResource(R.drawable.band_moyang_wavefit_image);
        } else if (deviceType.equals(DeviceType.matrix)) {
            ((ImageView) _$_findCachedViewById(R.id.info_image)).setBackgroundResource(R.drawable.band_matrix_image);
        } else if (deviceType.equals(DeviceType.SMA_WAVE_GENESIS_PRO)) {
            ((ImageView) _$_findCachedViewById(R.id.info_image)).setBackgroundResource(R.drawable.band_smaf2_image);
        } else if (deviceType.equals(DeviceType.SMA_WAVE_ELEVATE_PRO)) {
            ((ImageView) _$_findCachedViewById(R.id.info_image)).setBackgroundResource(R.drawable.band_smaf2_image);
        } else if (deviceType.equals(DeviceType.SMA_WAVE_GLORY_PRO)) {
            ((ImageView) _$_findCachedViewById(R.id.info_image)).setBackgroundResource(R.drawable.band_smaf2_image);
        } else if (deviceType.equals(DeviceType.SMA_ULTIMA_VOGUE)) {
            ((ImageView) _$_findCachedViewById(R.id.info_image)).setBackgroundResource(R.drawable.band_smaf2_image);
        } else if (deviceType.equals(DeviceType.SMA_LUNAR_SEEK)) {
            ((ImageView) _$_findCachedViewById(R.id.info_image)).setBackgroundResource(R.drawable.band_smaf2_image);
        } else if (deviceType.equals(DeviceType.SMA_LUNAR_COMET)) {
            ((ImageView) _$_findCachedViewById(R.id.info_image)).setBackgroundResource(R.drawable.band_smaf2_image);
        } else if (deviceType.equals(DeviceType.SMA_LUNAR_VELOCITY)) {
            ((ImageView) _$_findCachedViewById(R.id.info_image)).setBackgroundResource(R.drawable.band_smaf2_image);
        } else {
            ((ImageView) _$_findCachedViewById(R.id.info_image)).setBackgroundResource(R.drawable.ic_active_map_info_boat);
        }
    }
}
