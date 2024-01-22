package com.coveiot.android.theme;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class LocationPermissionActivity extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int p = 1001;
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage q;

    public static final void t(LocationPermissionActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void u(LocationPermissionActivity this$0, View view) {
        String[] unGrantedPermissions;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = Build.VERSION.SDK_INT;
        if (i < 29) {
            unGrantedPermissions = PermissionUtils.checkPermissionsHasGranted(this$0, new String[]{"android.permission.ACCESS_FINE_LOCATION"});
        } else {
            unGrantedPermissions = PermissionUtils.checkPermissionsHasGranted(this$0, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_BACKGROUND_LOCATION"});
        }
        Intrinsics.checkNotNullExpressionValue(unGrantedPermissions, "unGrantedPermissions");
        if (!(unGrantedPermissions.length == 0)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this$0, "android.permission.ACCESS_BACKGROUND_LOCATION")) {
                String string = this$0.getResources().getString(R.string.need_location_permission);
                Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…need_location_permission)");
                this$0.v(string);
                return;
            } else if (i < 29) {
                ActivityCompat.requestPermissions(this$0, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, this$0.p);
                return;
            } else {
                ActivityCompat.requestPermissions(this$0, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_BACKGROUND_LOCATION"}, this$0.p);
                return;
            }
        }
        AppUtils.openAppSettings(this$0, 101);
    }

    public static final void w(LocationPermissionActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppUtils.openAppSettings(this$0, 101);
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = this$0.q;
        if (bottomSheetDialogOneButtonTitleMessage != null) {
            bottomSheetDialogOneButtonTitleMessage.dismiss();
        }
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

    @Nullable
    public final BottomSheetDialogOneButtonTitleMessage getBottomSheetDialogOneButtonTitleMessage() {
        return this.q;
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.location_permission_title));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.u
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LocationPermissionActivity.t(LocationPermissionActivity.this, view);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        setResult(-1);
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_location_permission);
        initToolbar();
        ((TextView) _$_findCachedViewById(R.id.textview15)).setText(Html.fromHtml(getString(R.string.background_location_access_to_weather)));
        ((Button) _$_findCachedViewById(R.id.positive_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LocationPermissionActivity.u(LocationPermissionActivity.this, view);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        setResult(-1);
        finish();
    }

    public final void setBottomSheetDialogOneButtonTitleMessage(@Nullable BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage) {
        this.q = bottomSheetDialogOneButtonTitleMessage;
    }

    public final void v(String str) {
        String string = getString(R.string.permission_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required)");
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, str);
        this.q = bottomSheetDialogOneButtonTitleMessage;
        bottomSheetDialogOneButtonTitleMessage.setCancelable(true);
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = this.q;
        if (bottomSheetDialogOneButtonTitleMessage2 != null) {
            bottomSheetDialogOneButtonTitleMessage2.setCancelableOutside(true);
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage3 = this.q;
        if (bottomSheetDialogOneButtonTitleMessage3 != null) {
            String string2 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
            bottomSheetDialogOneButtonTitleMessage3.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.theme.v
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LocationPermissionActivity.w(LocationPermissionActivity.this, view);
                }
            });
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage4 = this.q;
        if (bottomSheetDialogOneButtonTitleMessage4 != null) {
            bottomSheetDialogOneButtonTitleMessage4.show();
        }
    }
}
