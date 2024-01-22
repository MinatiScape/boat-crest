package com.coveiot.android.sos;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import com.coveiot.android.fitnessbuddies.dialogs.GenericMessageDialog;
import com.coveiot.android.sos.viewmodel.SOSSettingsViewmodel;
import com.coveiot.covepreferences.sos.SOSData;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.permissions.PermissionUtils;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes7.dex */
public final class ActivitySOS$getContactsPermission$1 implements PermissionUtils.PermissionAskListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivitySOS f5768a;

    public ActivitySOS$getContactsPermission$1(ActivitySOS activitySOS) {
        this.f5768a = activitySOS;
    }

    public static final void e(GenericMessageDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public static final void f(GenericMessageDialog dialog, ActivitySOS this$0, View view) {
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

    public static final void h(GenericMessageDialog dialog, ActivitySOS this$0, View view) {
        int i;
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        i = this$0.w;
        ActivityCompat.requestPermissions(this$0, new String[]{"android.permission.READ_CONTACTS"}, i);
    }

    @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
    public void onPermissionAsk() {
        int i;
        ActivitySOS activitySOS = this.f5768a;
        i = activitySOS.w;
        ActivityCompat.requestPermissions(activitySOS, new String[]{"android.permission.READ_CONTACTS"}, i);
    }

    @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
    public void onPermissionDisabled() {
        ActivitySOS activitySOS = this.f5768a;
        String string = activitySOS.getString(R.string.permission_required_nw);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required_nw)");
        String string2 = this.f5768a.getString(R.string.to_configure_your_emergency_contact_please_allow);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.to_co…ncy_contact_please_allow)");
        final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(activitySOS, string, string2);
        View findViewById = genericMessageDialog.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(R.id.cancel)");
        View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(R.id.proceed)");
        ((TextView) findViewById).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOS$getContactsPermission$1.e(GenericMessageDialog.this, view);
            }
        });
        final ActivitySOS activitySOS2 = this.f5768a;
        ((TextView) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.t
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOS$getContactsPermission$1.f(GenericMessageDialog.this, activitySOS2, view);
            }
        });
        genericMessageDialog.show();
    }

    @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
    public void onPermissionGranted() {
        CoveContact coveContact;
        SOSData sOSData;
        SOSSettingsViewmodel sOSSettingsViewmodel;
        coveContact = this.f5768a.t;
        if (coveContact == null) {
            sOSData = this.f5768a.s;
            SOSSettingsViewmodel sOSSettingsViewmodel2 = null;
            if ((sOSData != null ? sOSData.getContactName() : null) != null) {
                sOSSettingsViewmodel = this.f5768a.q;
                if (sOSSettingsViewmodel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    sOSSettingsViewmodel2 = sOSSettingsViewmodel;
                }
                sOSSettingsViewmodel2.getSOSConfigSettings();
            }
        }
    }

    @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
    public void onPermissionPreviouslyDenied() {
        ActivitySOS activitySOS = this.f5768a;
        String string = activitySOS.getString(R.string.permission_required_nw);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required_nw)");
        String string2 = this.f5768a.getString(R.string.to_configure_your_emergency_contact_please_allow);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.to_co…ncy_contact_please_allow)");
        final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(activitySOS, string, string2);
        View findViewById = genericMessageDialog.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(R.id.cancel)");
        View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(R.id.proceed)");
        ((TextView) findViewById).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOS$getContactsPermission$1.g(GenericMessageDialog.this, view);
            }
        });
        final ActivitySOS activitySOS2 = this.f5768a;
        ((TextView) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOS$getContactsPermission$1.h(GenericMessageDialog.this, activitySOS2, view);
            }
        });
        genericMessageDialog.show();
    }
}
