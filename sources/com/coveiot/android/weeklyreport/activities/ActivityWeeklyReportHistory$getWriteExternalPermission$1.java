package com.coveiot.android.weeklyreport.activities;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import com.coveiot.android.theme.GenericMessageDialog;
import com.coveiot.android.weeklyreport.R;
import com.coveiot.utils.permissions.PermissionUtils;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes8.dex */
public final class ActivityWeeklyReportHistory$getWriteExternalPermission$1 implements PermissionUtils.PermissionAskListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivityWeeklyReportHistory f6240a;

    public ActivityWeeklyReportHistory$getWriteExternalPermission$1(ActivityWeeklyReportHistory activityWeeklyReportHistory) {
        this.f6240a = activityWeeklyReportHistory;
    }

    public static final void e(GenericMessageDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public static final void f(GenericMessageDialog dialog, ActivityWeeklyReportHistory this$0, View view) {
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

    public static final void h(GenericMessageDialog dialog, ActivityWeeklyReportHistory this$0, View view) {
        int i;
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        i = this$0.s;
        ActivityCompat.requestPermissions(this$0, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, i);
    }

    @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
    public void onPermissionAsk() {
        int i;
        ActivityWeeklyReportHistory activityWeeklyReportHistory = this.f6240a;
        i = activityWeeklyReportHistory.s;
        ActivityCompat.requestPermissions(activityWeeklyReportHistory, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, i);
    }

    @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
    public void onPermissionDisabled() {
        ActivityWeeklyReportHistory activityWeeklyReportHistory = this.f6240a;
        String string = activityWeeklyReportHistory.getString(R.string.permission_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required)");
        String string2 = this.f6240a.getString(R.string.permission_required_nw);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.permission_required_nw)");
        final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(activityWeeklyReportHistory, string, string2);
        View findViewById = genericMessageDialog.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(R.id.cancel)");
        View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(R.id.proceed)");
        ((TextView) findViewById).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.activities.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWeeklyReportHistory$getWriteExternalPermission$1.e(GenericMessageDialog.this, view);
            }
        });
        final ActivityWeeklyReportHistory activityWeeklyReportHistory2 = this.f6240a;
        ((TextView) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.activities.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWeeklyReportHistory$getWriteExternalPermission$1.f(GenericMessageDialog.this, activityWeeklyReportHistory2, view);
            }
        });
        genericMessageDialog.show();
    }

    @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
    public void onPermissionGranted() {
    }

    @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
    public void onPermissionPreviouslyDenied() {
        ActivityWeeklyReportHistory activityWeeklyReportHistory = this.f6240a;
        String string = activityWeeklyReportHistory.getString(R.string.permission_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required)");
        String string2 = this.f6240a.getString(R.string.permission_required_nw);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.permission_required_nw)");
        final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(activityWeeklyReportHistory, string, string2);
        View findViewById = genericMessageDialog.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(R.id.cancel)");
        View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(R.id.proceed)");
        ((TextView) findViewById).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.activities.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWeeklyReportHistory$getWriteExternalPermission$1.g(GenericMessageDialog.this, view);
            }
        });
        final ActivityWeeklyReportHistory activityWeeklyReportHistory2 = this.f6240a;
        ((TextView) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.activities.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWeeklyReportHistory$getWriteExternalPermission$1.h(GenericMessageDialog.this, activityWeeklyReportHistory2, view);
            }
        });
        genericMessageDialog.show();
    }
}
