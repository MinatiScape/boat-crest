package com.coveiot.android.tappy.customview;

import android.app.Dialog;
import android.content.Context;
import com.coveiot.android.tappy.R;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class CardActionConfirmationDialog extends Dialog {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CardActionConfirmationDialog(@NotNull Context context) {
        super(context, R.style.DialogTheme);
        Intrinsics.checkNotNullParameter(context, "context");
        requestWindowFeature(1);
        setContentView(R.layout.card_action_confirmation_popup);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }
}
