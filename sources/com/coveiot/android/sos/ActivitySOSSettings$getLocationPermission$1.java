package com.coveiot.android.sos;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import com.coveiot.android.theme.GenericMessageDialog;
import com.coveiot.utils.permissions.PermissionUtils;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes7.dex */
public final class ActivitySOSSettings$getLocationPermission$1 implements PermissionUtils.PermissionAskListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivitySOSSettings f5772a;

    public ActivitySOSSettings$getLocationPermission$1(ActivitySOSSettings activitySOSSettings) {
        this.f5772a = activitySOSSettings;
    }

    public static final void e(GenericMessageDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public static final void f(GenericMessageDialog dialog, ActivitySOSSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this$0.getPackageName(), null));
        this$0.startActivity(intent);
    }

    public static final void g(GenericMessageDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public static final void h(GenericMessageDialog dialog, ActivitySOSSettings this$0, View view) {
        int i;
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        i = this$0.y;
        ActivityCompat.requestPermissions(this$0, new String[]{"android.permission.SEND_SMS"}, i);
    }

    @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
    public void onPermissionAsk() {
        int i;
        ActivitySOSSettings activitySOSSettings = this.f5772a;
        i = activitySOSSettings.y;
        ActivityCompat.requestPermissions(activitySOSSettings, new String[]{"android.permission.SEND_SMS"}, i);
    }

    @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
    public void onPermissionDisabled() {
        ActivitySOSSettings activitySOSSettings = this.f5772a;
        String string = activitySOSSettings.getString(R.string.location_permission_title);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.location_permission_title)");
        String string2 = this.f5772a.getString(R.string.location_permission_required);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.location_permission_required)");
        final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(activitySOSSettings, string, string2);
        View findViewById = genericMessageDialog.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(R.id.cancel)");
        View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(R.id.proceed)");
        ((TextView) findViewById).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.k0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOSSettings$getLocationPermission$1.e(GenericMessageDialog.this, view);
            }
        });
        final ActivitySOSSettings activitySOSSettings2 = this.f5772a;
        ((TextView) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.m0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOSSettings$getLocationPermission$1.f(GenericMessageDialog.this, activitySOSSettings2, view);
            }
        });
        genericMessageDialog.show();
    }

    @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
    public void onPermissionGranted() {
    }

    @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
    public void onPermissionPreviouslyDenied() {
        ActivitySOSSettings activitySOSSettings = this.f5772a;
        String string = activitySOSSettings.getString(R.string.location_permission_title);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.location_permission_title)");
        String string2 = this.f5772a.getString(R.string.location_permission_required);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.location_permission_required)");
        final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(activitySOSSettings, string, string2);
        View findViewById = genericMessageDialog.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(R.id.cancel)");
        View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(R.id.proceed)");
        ((TextView) findViewById).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.j0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOSSettings$getLocationPermission$1.g(GenericMessageDialog.this, view);
            }
        });
        final ActivitySOSSettings activitySOSSettings2 = this.f5772a;
        ((TextView) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.l0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOSSettings$getLocationPermission$1.h(GenericMessageDialog.this, activitySOSSettings2, view);
            }
        });
        genericMessageDialog.show();
    }
}
