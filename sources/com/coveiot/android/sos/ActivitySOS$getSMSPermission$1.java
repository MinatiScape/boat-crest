package com.coveiot.android.sos;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import com.coveiot.android.theme.GenericMessageDialog;
import com.coveiot.covepreferences.sos.SOSData;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.permissions.PermissionUtils;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes7.dex */
public final class ActivitySOS$getSMSPermission$1 implements PermissionUtils.PermissionAskListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivitySOS f5769a;

    public ActivitySOS$getSMSPermission$1(ActivitySOS activitySOS) {
        this.f5769a = activitySOS;
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
        i = this$0.v;
        ActivityCompat.requestPermissions(this$0, new String[]{"android.permission.SEND_SMS"}, i);
    }

    @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
    public void onPermissionAsk() {
        int i;
        ActivitySOS activitySOS = this.f5769a;
        i = activitySOS.v;
        ActivityCompat.requestPermissions(activitySOS, new String[]{"android.permission.SEND_SMS"}, i);
    }

    @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
    public void onPermissionDisabled() {
        ActivitySOS activitySOS = this.f5769a;
        String string = activitySOS.getString(R.string.sms_permission_needed);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.sms_permission_needed)");
        String string2 = this.f5769a.getString(R.string.sos_sms_permission);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.sos_sms_permission)");
        final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(activitySOS, string, string2);
        View findViewById = genericMessageDialog.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(R.id.cancel)");
        View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(R.id.proceed)");
        ((TextView) findViewById).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.u
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOS$getSMSPermission$1.e(GenericMessageDialog.this, view);
            }
        });
        final ActivitySOS activitySOS2 = this.f5769a;
        ((TextView) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOS$getSMSPermission$1.f(GenericMessageDialog.this, activitySOS2, view);
            }
        });
        genericMessageDialog.show();
    }

    @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
    public void onPermissionGranted() {
        CoveContact coveContact;
        SOSData sOSData;
        coveContact = this.f5769a.t;
        if (coveContact == null) {
            sOSData = this.f5769a.s;
            if ((sOSData != null ? sOSData.getContactName() : null) != null) {
                this.f5769a.F();
            }
        }
    }

    @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
    public void onPermissionPreviouslyDenied() {
        ActivitySOS activitySOS = this.f5769a;
        String string = activitySOS.getString(R.string.sms_permission_needed);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.sms_permission_needed)");
        String string2 = this.f5769a.getString(R.string.sos_sms_permission);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.sos_sms_permission)");
        final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(activitySOS, string, string2);
        View findViewById = genericMessageDialog.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(R.id.cancel)");
        View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(R.id.proceed)");
        ((TextView) findViewById).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.v
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOS$getSMSPermission$1.g(GenericMessageDialog.this, view);
            }
        });
        final ActivitySOS activitySOS2 = this.f5769a;
        ((TextView) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.x
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOS$getSMSPermission$1.h(GenericMessageDialog.this, activitySOS2, view);
            }
        });
        genericMessageDialog.show();
    }
}
