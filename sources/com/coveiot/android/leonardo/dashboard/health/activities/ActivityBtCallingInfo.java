package com.coveiot.android.leonardo.dashboard.health.activities;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ActivityBtCallingInfo extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public static final void s(ActivityBtCallingInfo this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppUtils.openBluetoothSettings(this$0);
    }

    public static final void t(ActivityBtCallingInfo this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        super.onBackPressed();
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
        if (!SessionManager.getInstance(this).getDeviceType().equals(getResources().getString(R.string.cove_ca3_bt))) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (!companion.isCYDevice(this) && !companion.isPS1Device(this) && !companion.isBESDevice(this)) {
                setContentView(R.layout.bt_calling_info_activity);
                int i = R.id.bt_settings;
                ((TextView) _$_findCachedViewById(i)).setText(Html.fromHtml(getString(R.string._3_go_to_u_phone_settings_u_bluetooth_n_bt3_0_connect)));
                ((TextView) _$_findCachedViewById(i)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.activities.a
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityBtCallingInfo.s(ActivityBtCallingInfo.this, view);
                    }
                });
                ((ImageView) _$_findCachedViewById(R.id.iv_close)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.activities.b
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityBtCallingInfo.t(ActivityBtCallingInfo.this, view);
                    }
                });
            }
        }
        setContentView(R.layout.ca3_bt_calling_info_activity);
        ((ImageView) _$_findCachedViewById(R.id.iv_close)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.activities.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBtCallingInfo.t(ActivityBtCallingInfo.this, view);
            }
        });
    }
}
