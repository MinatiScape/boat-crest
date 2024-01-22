package com.coveiot.android.fitnessbuddies.activities;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.dialogs.GenericMessageDialog;
import com.coveiot.android.fitnessbuddies.utils.PermissionUtils;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes4.dex */
public final class AddBuddiesActivity$getContactList$1 implements PermissionUtils.PermissionAskListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AddBuddiesActivity f4419a;

    public AddBuddiesActivity$getContactList$1(AddBuddiesActivity addBuddiesActivity) {
        this.f4419a = addBuddiesActivity;
    }

    public static final void e(GenericMessageDialog dialog, AddBuddiesActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.onBackPressed();
    }

    public static final void f(GenericMessageDialog dialog, AddBuddiesActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this$0.getPackageName(), null));
        this$0.startActivity(intent);
    }

    public static final void g(GenericMessageDialog dialog, AddBuddiesActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.onBackPressed();
    }

    public static final void h(GenericMessageDialog dialog, AddBuddiesActivity this$0, View view) {
        int i;
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        i = this$0.p;
        ActivityCompat.requestPermissions(this$0, new String[]{"android.permission.READ_CONTACTS"}, i);
    }

    @Override // com.coveiot.android.fitnessbuddies.utils.PermissionUtils.PermissionAskListener
    public void onPermissionAsk() {
        int i;
        AddBuddiesActivity addBuddiesActivity = this.f4419a;
        i = addBuddiesActivity.p;
        ActivityCompat.requestPermissions(addBuddiesActivity, new String[]{"android.permission.READ_CONTACTS"}, i);
    }

    @Override // com.coveiot.android.fitnessbuddies.utils.PermissionUtils.PermissionAskListener
    public void onPermissionDisabled() {
        final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(this.f4419a, "Need contacts permission", "We need contacts permission in order to invite buddies.");
        View findViewById = genericMessageDialog.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(R.id.cancel)");
        View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(R.id.proceed)");
        final AddBuddiesActivity addBuddiesActivity = this.f4419a;
        ((TextView) findViewById).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.activities.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddBuddiesActivity$getContactList$1.e(GenericMessageDialog.this, addBuddiesActivity, view);
            }
        });
        final AddBuddiesActivity addBuddiesActivity2 = this.f4419a;
        ((TextView) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.activities.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddBuddiesActivity$getContactList$1.f(GenericMessageDialog.this, addBuddiesActivity2, view);
            }
        });
        genericMessageDialog.show();
    }

    @Override // com.coveiot.android.fitnessbuddies.utils.PermissionUtils.PermissionAskListener
    public void onPermissionGranted() {
        this.f4419a.refreshClicked();
    }

    @Override // com.coveiot.android.fitnessbuddies.utils.PermissionUtils.PermissionAskListener
    public void onPermissionPreviouslyDenied() {
        final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(this.f4419a, "Need contacts permission", "We need contacts permission in order to invite buddies.");
        View findViewById = genericMessageDialog.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(R.id.cancel)");
        View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(R.id.proceed)");
        final AddBuddiesActivity addBuddiesActivity = this.f4419a;
        ((TextView) findViewById).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.activities.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddBuddiesActivity$getContactList$1.g(GenericMessageDialog.this, addBuddiesActivity, view);
            }
        });
        final AddBuddiesActivity addBuddiesActivity2 = this.f4419a;
        ((TextView) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.activities.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddBuddiesActivity$getContactList$1.h(GenericMessageDialog.this, addBuddiesActivity2, view);
            }
        });
        genericMessageDialog.show();
    }
}
