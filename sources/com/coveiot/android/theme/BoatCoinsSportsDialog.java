package com.coveiot.android.theme;

import android.app.Dialog;
import android.content.Context;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class BoatCoinsSportsDialog extends Dialog {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BoatCoinsSportsDialog(@NotNull Context context) {
        super(context, R.style.DialogTheme);
        Intrinsics.checkNotNullParameter(context, "context");
        requestWindowFeature(1);
        setContentView(R.layout.boat_coins_pop_up);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }
}
