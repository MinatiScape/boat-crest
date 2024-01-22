package com.coveiot.android.leonardo.more.fragments;

import android.content.Context;
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
public final class SyncedContactsFragment$getContactsPermission$1 implements PermissionUtils.PermissionAskListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SyncedContactsFragment f5114a;

    public SyncedContactsFragment$getContactsPermission$1(SyncedContactsFragment syncedContactsFragment) {
        this.f5114a = syncedContactsFragment;
    }

    public static final void e(GenericMessageDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public static final void f(GenericMessageDialog dialog, SyncedContactsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this$0.requireContext().getPackageName(), null));
        this$0.startActivity(intent);
    }

    public static final void g(GenericMessageDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public static final void h(GenericMessageDialog dialog, SyncedContactsFragment this$0, View view) {
        int i;
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        i = this$0.t;
        ActivityCompat.requestPermissions(this$0.requireActivity(), new String[]{"android.permission.READ_CONTACTS"}, i);
    }

    @Override // com.coveiot.android.fitnessbuddies.utils.PermissionUtils.PermissionAskListener
    public void onPermissionAsk() {
        int i;
        i = this.f5114a.t;
        ActivityCompat.requestPermissions(this.f5114a.requireActivity(), new String[]{"android.permission.READ_CONTACTS"}, i);
    }

    @Override // com.coveiot.android.fitnessbuddies.utils.PermissionUtils.PermissionAskListener
    public void onPermissionDisabled() {
        Context requireContext = this.f5114a.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = this.f5114a.requireContext().getString(R.string.contact_permission);
        Intrinsics.checkNotNullExpressionValue(string, "requireContext().getStri…tring.contact_permission)");
        String string2 = this.f5114a.requireContext().getString(R.string.contact_permission_required);
        Intrinsics.checkNotNullExpressionValue(string2, "requireContext().getStri…tact_permission_required)");
        final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(requireContext, string, string2);
        View findViewById = genericMessageDialog.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(R.id.cancel)");
        View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(R.id.proceed)");
        ((TextView) findViewById).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.a2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SyncedContactsFragment$getContactsPermission$1.e(GenericMessageDialog.this, view);
            }
        });
        final SyncedContactsFragment syncedContactsFragment = this.f5114a;
        ((TextView) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.c2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SyncedContactsFragment$getContactsPermission$1.f(GenericMessageDialog.this, syncedContactsFragment, view);
            }
        });
        genericMessageDialog.show();
    }

    @Override // com.coveiot.android.fitnessbuddies.utils.PermissionUtils.PermissionAskListener
    public void onPermissionGranted() {
        this.f5114a.q();
    }

    @Override // com.coveiot.android.fitnessbuddies.utils.PermissionUtils.PermissionAskListener
    public void onPermissionPreviouslyDenied() {
        Context requireContext = this.f5114a.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = this.f5114a.requireContext().getString(R.string.contact_permission);
        Intrinsics.checkNotNullExpressionValue(string, "requireContext().getStri…tring.contact_permission)");
        String string2 = this.f5114a.requireContext().getString(R.string.contact_permission_required);
        Intrinsics.checkNotNullExpressionValue(string2, "requireContext().getStri…tact_permission_required)");
        final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(requireContext, string, string2);
        View findViewById = genericMessageDialog.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(R.id.cancel)");
        View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(R.id.proceed)");
        ((TextView) findViewById).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.z1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SyncedContactsFragment$getContactsPermission$1.g(GenericMessageDialog.this, view);
            }
        });
        final SyncedContactsFragment syncedContactsFragment = this.f5114a;
        ((TextView) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.b2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SyncedContactsFragment$getContactsPermission$1.h(GenericMessageDialog.this, syncedContactsFragment, view);
            }
        });
        genericMessageDialog.show();
    }
}
