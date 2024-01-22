package com.coveiot.android.leonardo.dashboard;

import android.app.Dialog;
import android.content.Context;
import com.coveiot.android.boat.R;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class TemperatureDisclaimerDialog extends Dialog {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TemperatureDisclaimerDialog(@NotNull Context context) {
        super(context, R.style.DialogTheme);
        Intrinsics.checkNotNullParameter(context, "context");
        requestWindowFeature(1);
        setContentView(R.layout.dialog_temperature_disclaimer);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }
}
