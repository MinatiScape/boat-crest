package com.coveiot.android.leonardo.more.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard;
import com.coveiot.android.leonardo.more.fragments.WatchFeaturesFragment;
import com.coveiot.android.remotecommandframework.ViewModelFactory;
import com.coveiot.android.remotecommandframework.alexa.viewmodel.AlexaApiCallHandlerViewModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.DeviceModelBean;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityMoreDeviceFeaturesNew extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public AlexaApiCallHandlerViewModel p;

    public static final void r(ActivityMoreDeviceFeaturesNew this$0, View view) {
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
        DeviceModelBean deviceModelBean = SessionManager.getInstance(this).getDeviceModelBean();
        if ((deviceModelBean != null ? deviceModelBean.getName() : null) != null) {
            DeviceModelBean deviceModelBean2 = SessionManager.getInstance(this).getDeviceModelBean();
            if (deviceModelBean2 != null) {
                deviceModelBean2.getName();
            }
        } else {
            DeviceUtils.Companion.getModelNumber(this);
        }
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.watch_features));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.bf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityMoreDeviceFeaturesNew.r(ActivityMoreDeviceFeaturesNew.this, view);
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type com.coveiot.android.leonardo.more.fragments.WatchFeaturesFragment");
        ((WatchFeaturesFragment) findFragmentById).onBackPressed();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_more_device_features_new);
        ViewModelActivityDashboard viewModelActivityDashboard = (ViewModelActivityDashboard) ViewModelProviders.of(this).get(ViewModelActivityDashboard.class);
        AlexaApiCallHandlerViewModel alexaApiCallHandlerViewModel = (AlexaApiCallHandlerViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(AlexaApiCallHandlerViewModel.class);
        this.p = alexaApiCallHandlerViewModel;
        if (alexaApiCallHandlerViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewmodelAlexaApiCallHandler");
        }
        initToolbar();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, WatchFeaturesFragment.Companion.newInstance()).commitAllowingStateLoss();
    }
}
