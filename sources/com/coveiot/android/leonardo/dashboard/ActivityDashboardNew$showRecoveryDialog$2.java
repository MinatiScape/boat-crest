package com.coveiot.android.leonardo.dashboard;

import android.content.Intent;
import android.view.View;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareRestore;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityDashboardNew$showRecoveryDialog$2 implements View.OnClickListener {
    public final /* synthetic */ ActivityDashboardNew h;

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        this.h.startActivity(new Intent(this.h, ActivityFirmwareRestore.class));
        BottomSheetDialogImageTitleMessage recoveryDialog = this.h.getRecoveryDialog();
        if (recoveryDialog != null) {
            recoveryDialog.dismiss();
        }
    }
}
