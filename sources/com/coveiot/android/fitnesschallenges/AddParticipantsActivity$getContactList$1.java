package com.coveiot.android.fitnesschallenges;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import com.coveiot.android.fitnessbuddies.dialogs.GenericMessageDialog;
import com.coveiot.android.fitnessbuddies.utils.PermissionUtils;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes2.dex */
public final class AddParticipantsActivity$getContactList$1 implements PermissionUtils.PermissionAskListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AddParticipantsActivity f4486a;

    public AddParticipantsActivity$getContactList$1(AddParticipantsActivity addParticipantsActivity) {
        this.f4486a = addParticipantsActivity;
    }

    public static final void e(GenericMessageDialog dialog, AddParticipantsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.onBackPressed();
    }

    public static final void f(GenericMessageDialog dialog, AddParticipantsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this$0.getPackageName(), null));
        this$0.startActivity(intent);
    }

    public static final void g(GenericMessageDialog dialog, AddParticipantsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.onBackPressed();
    }

    public static final void h(GenericMessageDialog dialog, AddParticipantsActivity this$0, View view) {
        int i;
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        i = this$0.r;
        ActivityCompat.requestPermissions(this$0, new String[]{"android.permission.READ_CONTACTS"}, i);
    }

    @Override // com.coveiot.android.fitnessbuddies.utils.PermissionUtils.PermissionAskListener
    public void onPermissionAsk() {
        int i;
        AddParticipantsActivity addParticipantsActivity = this.f4486a;
        i = addParticipantsActivity.r;
        ActivityCompat.requestPermissions(addParticipantsActivity, new String[]{"android.permission.READ_CONTACTS"}, i);
    }

    @Override // com.coveiot.android.fitnessbuddies.utils.PermissionUtils.PermissionAskListener
    public void onPermissionDisabled() {
        AddParticipantsActivity addParticipantsActivity = this.f4486a;
        String string = addParticipantsActivity.getString(R.string.need_contact_permission);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.need_contact_permission)");
        String string2 = this.f4486a.getString(R.string.need_contact_permission_to_proceed);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.need_…ct_permission_to_proceed)");
        final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(addParticipantsActivity, string, string2);
        View findViewById = genericMessageDialog.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(R.id.cancel)");
        View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(R.id.proceed)");
        final AddParticipantsActivity addParticipantsActivity2 = this.f4486a;
        ((TextView) findViewById).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.a0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddParticipantsActivity$getContactList$1.e(GenericMessageDialog.this, addParticipantsActivity2, view);
            }
        });
        final AddParticipantsActivity addParticipantsActivity3 = this.f4486a;
        ((TextView) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.z
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddParticipantsActivity$getContactList$1.f(GenericMessageDialog.this, addParticipantsActivity3, view);
            }
        });
        genericMessageDialog.show();
    }

    @Override // com.coveiot.android.fitnessbuddies.utils.PermissionUtils.PermissionAskListener
    public void onPermissionGranted() {
        this.f4486a.refreshClicked();
    }

    @Override // com.coveiot.android.fitnessbuddies.utils.PermissionUtils.PermissionAskListener
    public void onPermissionPreviouslyDenied() {
        AddParticipantsActivity addParticipantsActivity = this.f4486a;
        String string = addParticipantsActivity.getString(R.string.need_contact_permission);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.need_contact_permission)");
        String string2 = this.f4486a.getString(R.string.need_contact_permission_to_proceed);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.need_…ct_permission_to_proceed)");
        final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(addParticipantsActivity, string, string2);
        View findViewById = genericMessageDialog.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(R.id.cancel)");
        View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(R.id.proceed)");
        final AddParticipantsActivity addParticipantsActivity2 = this.f4486a;
        ((TextView) findViewById).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.x
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddParticipantsActivity$getContactList$1.g(GenericMessageDialog.this, addParticipantsActivity2, view);
            }
        });
        final AddParticipantsActivity addParticipantsActivity3 = this.f4486a;
        ((TextView) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddParticipantsActivity$getContactList$1.h(GenericMessageDialog.this, addParticipantsActivity3, view);
            }
        });
        genericMessageDialog.show();
    }
}
