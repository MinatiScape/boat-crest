package com.coveiot.android.leonardo.more.activities;

import android.view.View;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityBandDisplay$saveChangesDialog$2 implements View.OnClickListener {
    public final /* synthetic */ BottomSheetDialogTwoButtons h;
    public final /* synthetic */ ActivityBandDisplay i;

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        this.h.dismiss();
        this.i.finish();
    }
}
