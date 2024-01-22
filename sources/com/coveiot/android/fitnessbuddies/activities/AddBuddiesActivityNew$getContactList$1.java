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
public final class AddBuddiesActivityNew$getContactList$1 implements PermissionUtils.PermissionAskListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AddBuddiesActivityNew f4423a;

    public AddBuddiesActivityNew$getContactList$1(AddBuddiesActivityNew addBuddiesActivityNew) {
        this.f4423a = addBuddiesActivityNew;
    }

    public static final void e(GenericMessageDialog dialog, AddBuddiesActivityNew this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.onBackPressed();
    }

    public static final void f(GenericMessageDialog dialog, AddBuddiesActivityNew this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this$0.getPackageName(), null));
        this$0.startActivity(intent);
    }

    public static final void g(GenericMessageDialog dialog, AddBuddiesActivityNew this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.onBackPressed();
    }

    public static final void h(GenericMessageDialog dialog, AddBuddiesActivityNew this$0, View view) {
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
        AddBuddiesActivityNew addBuddiesActivityNew = this.f4423a;
        i = addBuddiesActivityNew.r;
        ActivityCompat.requestPermissions(addBuddiesActivityNew, new String[]{"android.permission.READ_CONTACTS"}, i);
    }

    @Override // com.coveiot.android.fitnessbuddies.utils.PermissionUtils.PermissionAskListener
    public void onPermissionDisabled() {
        AddBuddiesActivityNew addBuddiesActivityNew = this.f4423a;
        String string = addBuddiesActivityNew.getString(R.string.need_contact_permission);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.need_contact_permission)");
        String string2 = this.f4423a.getString(R.string.need_contact_permission_to_proceed);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.need_…ct_permission_to_proceed)");
        final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(addBuddiesActivityNew, string, string2);
        View findViewById = genericMessageDialog.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(R.id.cancel)");
        View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(R.id.proceed)");
        final AddBuddiesActivityNew addBuddiesActivityNew2 = this.f4423a;
        ((TextView) findViewById).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.activities.b0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddBuddiesActivityNew$getContactList$1.e(GenericMessageDialog.this, addBuddiesActivityNew2, view);
            }
        });
        final AddBuddiesActivityNew addBuddiesActivityNew3 = this.f4423a;
        ((TextView) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.activities.z
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddBuddiesActivityNew$getContactList$1.f(GenericMessageDialog.this, addBuddiesActivityNew3, view);
            }
        });
        genericMessageDialog.show();
    }

    @Override // com.coveiot.android.fitnessbuddies.utils.PermissionUtils.PermissionAskListener
    public void onPermissionGranted() {
        this.f4423a.refreshClicked();
    }

    @Override // com.coveiot.android.fitnessbuddies.utils.PermissionUtils.PermissionAskListener
    public void onPermissionPreviouslyDenied() {
        AddBuddiesActivityNew addBuddiesActivityNew = this.f4423a;
        String string = addBuddiesActivityNew.getString(R.string.need_contact_permission);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.need_contact_permission)");
        String string2 = this.f4423a.getString(R.string.need_contact_permission_to_proceed);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.need_…ct_permission_to_proceed)");
        final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(addBuddiesActivityNew, string, string2);
        View findViewById = genericMessageDialog.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(R.id.cancel)");
        View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(R.id.proceed)");
        final AddBuddiesActivityNew addBuddiesActivityNew2 = this.f4423a;
        ((TextView) findViewById).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.activities.y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddBuddiesActivityNew$getContactList$1.g(GenericMessageDialog.this, addBuddiesActivityNew2, view);
            }
        });
        final AddBuddiesActivityNew addBuddiesActivityNew3 = this.f4423a;
        ((TextView) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.activities.a0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddBuddiesActivityNew$getContactList$1.h(GenericMessageDialog.this, addBuddiesActivityNew3, view);
            }
        });
        genericMessageDialog.show();
    }
}
