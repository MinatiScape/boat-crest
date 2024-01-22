package com.coveiot.android.healthbuddies.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.coveiot.android.healthbuddies.R;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class GenericMessageDialog extends Dialog {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericMessageDialog(@NotNull Context context, @NotNull String titleText, @NotNull String contentText) {
        super(context, R.style.DialogTheme);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(titleText, "titleText");
        Intrinsics.checkNotNullParameter(contentText, "contentText");
        requestWindowFeature(1);
        Window window = getWindow();
        Intrinsics.checkNotNull(window);
        window.setBackgroundDrawable(new ColorDrawable(0));
        setContentView(R.layout.generic_dialog);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        View findViewById = findViewById(R.id.title);
        Intrinsics.checkNotNullExpressionValue(findViewById, "this.findViewById(R.id.title)");
        View findViewById2 = findViewById(R.id.content);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "this.findViewById(R.id.content)");
        View findViewById3 = findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "this.findViewById(R.id.cancel)");
        TextView textView = (TextView) findViewById3;
        View findViewById4 = findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "this.findViewById(R.id.proceed)");
        TextView textView2 = (TextView) findViewById4;
        ((TextView) findViewById).setText(titleText);
        ((TextView) findViewById2).setText(contentText);
    }
}
