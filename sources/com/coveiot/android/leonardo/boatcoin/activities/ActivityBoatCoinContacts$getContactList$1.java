package com.coveiot.android.leonardo.boatcoin.activities;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import com.coveiot.android.boat.R;
import com.coveiot.android.fitnessbuddies.dialogs.GenericMessageDialog;
import com.coveiot.android.fitnessbuddies.utils.PermissionUtils;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes2.dex */
public final class ActivityBoatCoinContacts$getContactList$1 implements PermissionUtils.PermissionAskListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivityBoatCoinContacts f4647a;

    public ActivityBoatCoinContacts$getContactList$1(ActivityBoatCoinContacts activityBoatCoinContacts) {
        this.f4647a = activityBoatCoinContacts;
    }

    public static final void e(GenericMessageDialog dialog, ActivityBoatCoinContacts this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.onBackPressed();
    }

    public static final void f(GenericMessageDialog dialog, ActivityBoatCoinContacts this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this$0.getPackageName(), null));
        this$0.startActivity(intent);
    }

    public static final void g(GenericMessageDialog dialog, ActivityBoatCoinContacts this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.onBackPressed();
    }

    public static final void h(GenericMessageDialog dialog, ActivityBoatCoinContacts this$0, View view) {
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
        ActivityBoatCoinContacts activityBoatCoinContacts = this.f4647a;
        i = activityBoatCoinContacts.q;
        ActivityCompat.requestPermissions(activityBoatCoinContacts, new String[]{"android.permission.READ_CONTACTS"}, i);
    }

    @Override // com.coveiot.android.fitnessbuddies.utils.PermissionUtils.PermissionAskListener
    public void onPermissionDisabled() {
        ActivityBoatCoinContacts activityBoatCoinContacts = this.f4647a;
        String string = activityBoatCoinContacts.getString(R.string.need_permission);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.need_permission)");
        String string2 = this.f4647a.getString(R.string.permission_to_gift);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.permission_to_gift)");
        final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(activityBoatCoinContacts, string, string2);
        View findViewById = genericMessageDialog.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(com.…tnessbuddies.R.id.cancel)");
        View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(com.…nessbuddies.R.id.proceed)");
        final ActivityBoatCoinContacts activityBoatCoinContacts2 = this.f4647a;
        ((TextView) findViewById).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.boatcoin.activities.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBoatCoinContacts$getContactList$1.e(GenericMessageDialog.this, activityBoatCoinContacts2, view);
            }
        });
        final ActivityBoatCoinContacts activityBoatCoinContacts3 = this.f4647a;
        ((TextView) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.boatcoin.activities.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBoatCoinContacts$getContactList$1.f(GenericMessageDialog.this, activityBoatCoinContacts3, view);
            }
        });
        genericMessageDialog.show();
    }

    @Override // com.coveiot.android.fitnessbuddies.utils.PermissionUtils.PermissionAskListener
    public void onPermissionGranted() {
        this.f4647a.refreshClicked();
    }

    @Override // com.coveiot.android.fitnessbuddies.utils.PermissionUtils.PermissionAskListener
    public void onPermissionPreviouslyDenied() {
        ActivityBoatCoinContacts activityBoatCoinContacts = this.f4647a;
        String string = activityBoatCoinContacts.getString(R.string.need_permission);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.need_permission)");
        String string2 = this.f4647a.getString(R.string.permission_to_gift);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.permission_to_gift)");
        final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(activityBoatCoinContacts, string, string2);
        View findViewById = genericMessageDialog.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(R.id.cancel)");
        View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(R.id.proceed)");
        final ActivityBoatCoinContacts activityBoatCoinContacts2 = this.f4647a;
        ((TextView) findViewById).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.boatcoin.activities.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBoatCoinContacts$getContactList$1.g(GenericMessageDialog.this, activityBoatCoinContacts2, view);
            }
        });
        final ActivityBoatCoinContacts activityBoatCoinContacts3 = this.f4647a;
        ((TextView) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.boatcoin.activities.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBoatCoinContacts$getContactList$1.h(GenericMessageDialog.this, activityBoatCoinContacts3, view);
            }
        });
        genericMessageDialog.show();
    }
}
