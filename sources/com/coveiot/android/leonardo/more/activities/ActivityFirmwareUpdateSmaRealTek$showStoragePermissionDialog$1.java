package com.coveiot.android.leonardo.more.activities;

import android.view.View;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.utils.utility.AppUtils;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateSmaRealTek$showStoragePermissionDialog$1 implements View.OnClickListener {
    public final /* synthetic */ BottomSheetDialogOneButtonOneTitle h;
    public final /* synthetic */ ActivityFirmwareUpdateSmaRealTek i;

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        this.h.dismiss();
        AppUtils.openAppSettings(this.i, 122);
    }
}
