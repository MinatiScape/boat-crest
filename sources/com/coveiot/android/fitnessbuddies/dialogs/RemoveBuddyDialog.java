package com.coveiot.android.fitnessbuddies.dialogs;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.GetBuddyItems;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.Arrays;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public class RemoveBuddyDialog {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4456a;
    @NotNull
    public final BottomSheetDialog b;
    @NotNull
    public final Button c;
    @NotNull
    public final Button d;
    @NotNull
    public final TextView e;

    public RemoveBuddyDialog(@NotNull Context context, @NotNull GetBuddyItems buddyDetails) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(buddyDetails, "buddyDetails");
        this.f4456a = context;
        Intrinsics.checkNotNull(context);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
        this.b = bottomSheetDialog;
        bottomSheetDialog.requestWindowFeature(1);
        bottomSheetDialog.setContentView(R.layout.remove_buddies_dialog);
        View findViewById = bottomSheetDialog.findViewById(R.id.btn_cancel);
        Intrinsics.checkNotNull(findViewById);
        Button button = (Button) findViewById;
        this.c = button;
        View findViewById2 = bottomSheetDialog.findViewById(R.id.btn_remove);
        Intrinsics.checkNotNull(findViewById2);
        this.d = (Button) findViewById2;
        View findViewById3 = bottomSheetDialog.findViewById(R.id.tv_remove_buddy_msg);
        Intrinsics.checkNotNull(findViewById3);
        TextView textView = (TextView) findViewById3;
        this.e = textView;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String string = context.getString(R.string.remove_buddy_msg);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.remove_buddy_msg)");
        String format = String.format(locale, string, Arrays.copyOf(new Object[]{buddyDetails.getBuddyDetails().name}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        textView.setText(format);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.dialogs.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RemoveBuddyDialog.b(RemoveBuddyDialog.this, view);
            }
        });
    }

    public static final void b(RemoveBuddyDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public final void dismiss() {
        this.b.dismiss();
    }

    @NotNull
    public final BottomSheetDialog getBottomSheetDialog() {
        return this.b;
    }

    @NotNull
    public final Button getBtn_cancel() {
        return this.c;
    }

    @NotNull
    public final Button getBtn_remove() {
        return this.d;
    }

    @NotNull
    public final Context getContext() {
        return this.f4456a;
    }

    @NotNull
    public final TextView getTv_remove_buddy_msg() {
        return this.e;
    }

    public final boolean isShowing() {
        return this.b.isShowing();
    }

    public final void setCancelable(boolean z) {
        this.b.setCancelable(z);
    }

    public final void setCancelableOutside(boolean z) {
        this.b.setCanceledOnTouchOutside(z);
    }

    public final void setPositiveButton(@NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.d.setOnClickListener(listner);
    }

    public final void show() {
        Context context = this.f4456a;
        if (context instanceof Activity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        if (this.b.isShowing()) {
            return;
        }
        this.b.show();
    }
}
