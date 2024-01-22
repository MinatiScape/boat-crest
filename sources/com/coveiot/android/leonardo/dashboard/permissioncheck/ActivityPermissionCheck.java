package com.coveiot.android.leonardo.dashboard.permissioncheck;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivityCheckPermissionsBinding;
import com.coveiot.android.leonardo.dashboard.model.PermissionsRequiredData;
import com.coveiot.android.leonardo.dashboard.permissioncheck.adapters.PermissionsRequiredAdapter;
import com.coveiot.android.leonardo.onboarding.splash.viewmodel.ActivitySplashViewModel;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.utils.RecyclerviewMarginItemDecoration;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ActivityPermissionCheck extends BaseActivity {
    public ActivityCheckPermissionsBinding p;
    public boolean q;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public ArrayList<PermissionsRequiredData> r = new ArrayList<>();

    public static final void s(ActivityPermissionCheck this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.checkIfLocationPermissionExists(this$0)) {
            PayUtils payUtils = PayUtils.INSTANCE;
            if (payUtils.checkIfBluetoothPermissionExists(this$0) && payUtils.checkIfNotificationPermissionExists(this$0)) {
                this$0.r();
                return;
            }
        }
        AppUtils.openAppSettings(this$0, 121);
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

    @NotNull
    public final ArrayList<PermissionsRequiredData> getPendingPermissions() {
        return this.r;
    }

    public final boolean isFromAppSettings() {
        return this.q;
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @RequiresApi(33)
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityCheckPermissionsBinding inflate = ActivityCheckPermissionsBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ActivityCheckPermissionsBinding activityCheckPermissionsBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        ActivitySplashViewModel activitySplashViewModel = (ActivitySplashViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivitySplashViewModel.class);
        ActivityCheckPermissionsBinding activityCheckPermissionsBinding2 = this.p;
        if (activityCheckPermissionsBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCheckPermissionsBinding = activityCheckPermissionsBinding2;
        }
        activityCheckPermissionsBinding.btnGrantpermissions.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.permissioncheck.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityPermissionCheck.s(ActivityPermissionCheck.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (AppUtils.checkIfLocationPermissionExists(this)) {
            PayUtils payUtils = PayUtils.INSTANCE;
            if (payUtils.checkIfBluetoothPermissionExists(this) && payUtils.checkIfNotificationPermissionExists(this)) {
                r();
                return;
            }
        }
        this.r.clear();
        PayUtils payUtils2 = PayUtils.INSTANCE;
        if (!payUtils2.checkIfBluetoothPermissionExists(this)) {
            ArrayList<PermissionsRequiredData> arrayList = this.r;
            String string = getResources().getString(R.string.bluetooth_scan_permission_check_screen_title);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…ssion_check_screen_title)");
            String string2 = getResources().getString(R.string.bluetooth_scan_permission_check_screen_descriptions);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…heck_screen_descriptions)");
            arrayList.add(new PermissionsRequiredData(string, string2));
        }
        if (!AppUtils.checkIfLocationPermissionExists(this)) {
            ArrayList<PermissionsRequiredData> arrayList2 = this.r;
            String string3 = getResources().getString(R.string.location_permission_check_screen_title);
            Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(R.st…ssion_check_screen_title)");
            String string4 = getResources().getString(R.string.location_permission_check_screen_title_descriptions);
            Intrinsics.checkNotNullExpressionValue(string4, "resources.getString(R.st…creen_title_descriptions)");
            arrayList2.add(new PermissionsRequiredData(string3, string4));
        }
        if (!payUtils2.checkIfNotificationPermissionExists(this) && Build.VERSION.SDK_INT >= 33) {
            ArrayList<PermissionsRequiredData> arrayList3 = this.r;
            String string5 = getResources().getString(R.string.push_notification_permission_check_screen_title);
            Intrinsics.checkNotNullExpressionValue(string5, "resources.getString(R.st…ssion_check_screen_title)");
            String string6 = getResources().getString(R.string.push_notification_permission_check_screen_descriptions);
            Intrinsics.checkNotNullExpressionValue(string6, "resources.getString(R.st…heck_screen_descriptions)");
            arrayList3.add(new PermissionsRequiredData(string5, string6));
        }
        t(this.r);
    }

    public final void r() {
        if (Build.VERSION.SDK_INT >= 31) {
            BleApiManager.getInstance(this).getBleApi().restartService();
        }
        ActivityCheckPermissionsBinding activityCheckPermissionsBinding = this.p;
        ActivityCheckPermissionsBinding activityCheckPermissionsBinding2 = null;
        if (activityCheckPermissionsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCheckPermissionsBinding = null;
        }
        activityCheckPermissionsBinding.recyclerViewPermissionsRequired.setVisibility(8);
        ActivityCheckPermissionsBinding activityCheckPermissionsBinding3 = this.p;
        if (activityCheckPermissionsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCheckPermissionsBinding3 = null;
        }
        activityCheckPermissionsBinding3.imageViewHasAllPermissions.setVisibility(0);
        ActivityCheckPermissionsBinding activityCheckPermissionsBinding4 = this.p;
        if (activityCheckPermissionsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCheckPermissionsBinding2 = activityCheckPermissionsBinding4;
        }
        activityCheckPermissionsBinding2.btnGrantpermissions.setVisibility(8);
        AppNavigator.Companion.navigateToDashBoard(this, false);
    }

    public final void setFromAppSettings(boolean z) {
        this.q = z;
    }

    public final void setPendingPermissions(@NotNull ArrayList<PermissionsRequiredData> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.r = arrayList;
    }

    public final void t(ArrayList<PermissionsRequiredData> arrayList) {
        ActivityCheckPermissionsBinding activityCheckPermissionsBinding = this.p;
        ActivityCheckPermissionsBinding activityCheckPermissionsBinding2 = null;
        if (activityCheckPermissionsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCheckPermissionsBinding = null;
        }
        activityCheckPermissionsBinding.recyclerViewPermissionsRequired.setVisibility(0);
        ActivityCheckPermissionsBinding activityCheckPermissionsBinding3 = this.p;
        if (activityCheckPermissionsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCheckPermissionsBinding3 = null;
        }
        activityCheckPermissionsBinding3.imageViewHasAllPermissions.setVisibility(8);
        ActivityCheckPermissionsBinding activityCheckPermissionsBinding4 = this.p;
        if (activityCheckPermissionsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCheckPermissionsBinding4 = null;
        }
        activityCheckPermissionsBinding4.btnGrantpermissions.setVisibility(0);
        ActivityCheckPermissionsBinding activityCheckPermissionsBinding5 = this.p;
        if (activityCheckPermissionsBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCheckPermissionsBinding2 = activityCheckPermissionsBinding5;
        }
        RecyclerView recyclerView = activityCheckPermissionsBinding2.recyclerViewPermissionsRequired;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PermissionsRequiredAdapter(arrayList));
        recyclerView.addItemDecoration(new RecyclerviewMarginItemDecoration(recyclerView.getResources().getDimensionPixelSize(R.dimen.margin_18dp)));
    }
}
