package com.coveiot.android.sos;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import com.coveiot.android.sos.utils.SOSCleverTapConstants;
import com.coveiot.android.theme.GenericMessageDialog;
import com.coveiot.utils.permissions.PermissionUtils;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes7.dex */
public final class ActivitySOSSettings$getContactList$1 implements PermissionUtils.PermissionAskListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivitySOSSettings f5771a;

    public ActivitySOSSettings$getContactList$1(ActivitySOSSettings activitySOSSettings) {
        this.f5771a = activitySOSSettings;
    }

    public static final void e(GenericMessageDialog dialog, ActivitySOSSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.onBackPressed();
    }

    public static final void f(GenericMessageDialog dialog, ActivitySOSSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.C(SOSCleverTapConstants.BC_SOS_CONTACTIMPORT_PROCEED_TAPPED.getValue());
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this$0.getPackageName(), null));
        this$0.startActivity(intent);
    }

    public static final void g(GenericMessageDialog dialog, ActivitySOSSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.onBackPressed();
    }

    public static final void h(GenericMessageDialog dialog, ActivitySOSSettings this$0, View view) {
        int i;
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.C(SOSCleverTapConstants.BC_SOS_CONTACTIMPORT_PROCEED_TAPPED.getValue());
        i = this$0.q;
        ActivityCompat.requestPermissions(this$0, new String[]{"android.permission.READ_CONTACTS"}, i);
    }

    @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
    public void onPermissionAsk() {
        int i;
        this.f5771a.C(SOSCleverTapConstants.BC_SOS_CONTACTIMPORT_PROCEED_TAPPED.getValue());
        ActivitySOSSettings activitySOSSettings = this.f5771a;
        i = activitySOSSettings.q;
        ActivityCompat.requestPermissions(activitySOSSettings, new String[]{"android.permission.READ_CONTACTS"}, i);
    }

    @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
    public void onPermissionDisabled() {
        ActivitySOSSettings activitySOSSettings = this.f5771a;
        String string = activitySOSSettings.getString(R.string.permission_required_nw);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required_nw)");
        String string2 = this.f5771a.getString(R.string.to_configure_your_emergency_contact_please_allow);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.to_co…ncy_contact_please_allow)");
        final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(activitySOSSettings, string, string2);
        View findViewById = genericMessageDialog.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(R.id.cancel)");
        View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(R.id.proceed)");
        final ActivitySOSSettings activitySOSSettings2 = this.f5771a;
        ((TextView) findViewById).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.g0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOSSettings$getContactList$1.e(GenericMessageDialog.this, activitySOSSettings2, view);
            }
        });
        final ActivitySOSSettings activitySOSSettings3 = this.f5771a;
        ((TextView) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.h0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOSSettings$getContactList$1.f(GenericMessageDialog.this, activitySOSSettings3, view);
            }
        });
        genericMessageDialog.show();
    }

    @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
    public void onPermissionGranted() {
        this.f5771a.refreshClicked();
    }

    @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
    public void onPermissionPreviouslyDenied() {
        ActivitySOSSettings activitySOSSettings = this.f5771a;
        String string = activitySOSSettings.getString(R.string.permission_required_nw);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required_nw)");
        String string2 = this.f5771a.getString(R.string.to_configure_your_emergency_contact_please_allow);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.to_co…ncy_contact_please_allow)");
        final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(activitySOSSettings, string, string2);
        View findViewById = genericMessageDialog.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(R.id.cancel)");
        View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(R.id.proceed)");
        final ActivitySOSSettings activitySOSSettings2 = this.f5771a;
        ((TextView) findViewById).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.i0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOSSettings$getContactList$1.g(GenericMessageDialog.this, activitySOSSettings2, view);
            }
        });
        final ActivitySOSSettings activitySOSSettings3 = this.f5771a;
        ((TextView) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.f0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOSSettings$getContactList$1.h(GenericMessageDialog.this, activitySOSSettings3, view);
            }
        });
        genericMessageDialog.show();
    }
}
