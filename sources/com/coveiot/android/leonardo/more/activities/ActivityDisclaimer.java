package com.coveiot.android.leonardo.more.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.utils.ThemesUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityDisclaimer extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public static final void r(ActivityDisclaimer this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
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

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.disclaimer));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.s5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDisclaimer.r(ActivityDisclaimer.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_disclaimer);
        initToolbar();
        ImageView iv_powered_by_logo = (ImageView) _$_findCachedViewById(R.id.iv_powered_by_logo);
        Intrinsics.checkNotNullExpressionValue(iv_powered_by_logo, "iv_powered_by_logo");
        ThemesUtils.setPoweredByLogoIcon(this, iv_powered_by_logo, false);
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        if (!themesUtils.isGuestUser(this) && !themesUtils.isPairDeviceLater(this)) {
            if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isTemparatureHistorySupported()) {
                ConstraintLayout cl_temperature_disclaimer = (ConstraintLayout) _$_findCachedViewById(R.id.cl_temperature_disclaimer);
                Intrinsics.checkNotNullExpressionValue(cl_temperature_disclaimer, "cl_temperature_disclaimer");
                visible(cl_temperature_disclaimer);
                return;
            }
            return;
        }
        ConstraintLayout cl_temperature_disclaimer2 = (ConstraintLayout) _$_findCachedViewById(R.id.cl_temperature_disclaimer);
        Intrinsics.checkNotNullExpressionValue(cl_temperature_disclaimer2, "cl_temperature_disclaimer");
        visible(cl_temperature_disclaimer2);
    }
}
