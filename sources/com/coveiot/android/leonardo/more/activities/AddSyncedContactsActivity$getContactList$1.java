package com.coveiot.android.leonardo.more.activities;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import com.coveiot.android.boat.R;
import com.coveiot.android.fitnessbuddies.dialogs.GenericMessageDialog;
import com.coveiot.android.fitnessbuddies.utils.PermissionUtils;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes5.dex */
public final class AddSyncedContactsActivity$getContactList$1 implements PermissionUtils.PermissionAskListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AddSyncedContactsActivity f4982a;

    public AddSyncedContactsActivity$getContactList$1(AddSyncedContactsActivity addSyncedContactsActivity) {
        this.f4982a = addSyncedContactsActivity;
    }

    public static final void e(GenericMessageDialog dialog, AddSyncedContactsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.onBackPressed();
    }

    public static final void f(GenericMessageDialog dialog, AddSyncedContactsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this$0.getPackageName(), null));
        this$0.startActivity(intent);
    }

    public static final void g(GenericMessageDialog dialog, AddSyncedContactsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.onBackPressed();
    }

    public static final void h(GenericMessageDialog dialog, AddSyncedContactsActivity this$0, View view) {
        int i;
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        i = this$0.q;
        ActivityCompat.requestPermissions(this$0, new String[]{"android.permission.READ_CONTACTS"}, i);
    }

    @Override // com.coveiot.android.fitnessbuddies.utils.PermissionUtils.PermissionAskListener
    public void onPermissionAsk() {
        int i;
        AddSyncedContactsActivity addSyncedContactsActivity = this.f4982a;
        i = addSyncedContactsActivity.q;
        ActivityCompat.requestPermissions(addSyncedContactsActivity, new String[]{"android.permission.READ_CONTACTS"}, i);
    }

    @Override // com.coveiot.android.fitnessbuddies.utils.PermissionUtils.PermissionAskListener
    public void onPermissionDisabled() {
        final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(this.f4982a, "NEED CONTACTS PERMISSION", "We need contacts permission in order to invite buddies.");
        View findViewById = genericMessageDialog.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(com.…tnessbuddies.R.id.cancel)");
        View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(com.…nessbuddies.R.id.proceed)");
        final AddSyncedContactsActivity addSyncedContactsActivity = this.f4982a;
        ((TextView) findViewById).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.jk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddSyncedContactsActivity$getContactList$1.e(GenericMessageDialog.this, addSyncedContactsActivity, view);
            }
        });
        final AddSyncedContactsActivity addSyncedContactsActivity2 = this.f4982a;
        ((TextView) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.lk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddSyncedContactsActivity$getContactList$1.f(GenericMessageDialog.this, addSyncedContactsActivity2, view);
            }
        });
        genericMessageDialog.show();
    }

    @Override // com.coveiot.android.fitnessbuddies.utils.PermissionUtils.PermissionAskListener
    public void onPermissionGranted() {
        this.f4982a.refreshClicked();
    }

    @Override // com.coveiot.android.fitnessbuddies.utils.PermissionUtils.PermissionAskListener
    public void onPermissionPreviouslyDenied() {
        final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(this.f4982a, "NEED CONTACTS PERMISSION", "We need contacts permission in order to invite buddies.");
        View findViewById = genericMessageDialog.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(R.id.cancel)");
        View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(R.id.proceed)");
        final AddSyncedContactsActivity addSyncedContactsActivity = this.f4982a;
        ((TextView) findViewById).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.mk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddSyncedContactsActivity$getContactList$1.g(GenericMessageDialog.this, addSyncedContactsActivity, view);
            }
        });
        final AddSyncedContactsActivity addSyncedContactsActivity2 = this.f4982a;
        ((TextView) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.kk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddSyncedContactsActivity$getContactList$1.h(GenericMessageDialog.this, addSyncedContactsActivity2, view);
            }
        });
        genericMessageDialog.show();
    }
}
