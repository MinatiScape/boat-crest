package com.coveiot.android.activitymodes.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.theme.BaseActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class HeartRateZoneInfoActivity extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public static final void r(HeartRateZoneInfoActivity this$0, View view) {
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
        setContentView(R.layout.activity_heart_rate_zone_info);
        ((ImageView) _$_findCachedViewById(R.id.iv_close)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.u2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HeartRateZoneInfoActivity.r(HeartRateZoneInfoActivity.this, view);
            }
        });
        if (DeviceUtils.Companion.isEastApexDevice(this)) {
            _$_findCachedViewById(R.id.view_blue).setVisibility(8);
            int i = R.id.tv_blue;
            Resources resources = getResources();
            int i2 = R.color.color_deffffff;
            ((TextView) _$_findCachedViewById(i)).setTextColor(resources.getColor(i2));
            ((TextView) _$_findCachedViewById(i)).setText(R.string.normal_zone_header);
            ((TextView) _$_findCachedViewById(R.id.tv_warm_up_content)).setText(R.string.normal_zone_body);
            _$_findCachedViewById(R.id.view1).setVisibility(8);
            int i3 = R.id.textView8;
            ((TextView) _$_findCachedViewById(i3)).setTextColor(getResources().getColor(i2));
            ((TextView) _$_findCachedViewById(i3)).setText(R.string.warm_up_zone_header);
            ((TextView) _$_findCachedViewById(R.id.tv_fat_content)).setText(R.string.warm_up_zone_body);
            _$_findCachedViewById(R.id.view).setVisibility(8);
            int i4 = R.id.textView9;
            ((TextView) _$_findCachedViewById(i4)).setTextColor(getResources().getColor(i2));
            ((TextView) _$_findCachedViewById(i4)).setText(R.string.fat_consumption_header);
            ((TextView) _$_findCachedViewById(R.id.tv_cardio_content)).setText(R.string.fat_consumption_body);
            _$_findCachedViewById(R.id.view5).setVisibility(8);
            int i5 = R.id.textView12;
            ((TextView) _$_findCachedViewById(i5)).setTextColor(getResources().getColor(i2));
            ((TextView) _$_findCachedViewById(i5)).setText(R.string.aerobic_zone_header);
            ((TextView) _$_findCachedViewById(R.id.tv_threshold_content)).setText(R.string.aerobic_zone_body);
            _$_findCachedViewById(R.id.view2).setVisibility(8);
            int i6 = R.id.textView10;
            ((TextView) _$_findCachedViewById(i6)).setTextColor(getResources().getColor(i2));
            ((TextView) _$_findCachedViewById(i6)).setText(R.string.anaerobic_zone_header);
            ((TextView) _$_findCachedViewById(R.id.tv_peak_content)).setText(R.string.anaerobic_zone_body);
            ((ConstraintLayout) _$_findCachedViewById(R.id.limit_layout)).setVisibility(0);
            _$_findCachedViewById(R.id.limit_view2).setVisibility(8);
            int i7 = R.id.textView11;
            ((TextView) _$_findCachedViewById(i7)).setTextColor(getResources().getColor(i2));
            ((TextView) _$_findCachedViewById(i7)).setText(R.string.limit_zone_header);
            ((TextView) _$_findCachedViewById(R.id.tv_limit_content)).setText(R.string.limit_zone_body);
        }
    }
}
