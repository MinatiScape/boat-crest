package com.coveiot.android.fitnessbuddies.dialogs;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.utils.model.CoveContact;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.Arrays;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public class InviteBuddyDialog {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4455a;
    @NotNull
    public final BottomSheetDialog b;
    @NotNull
    public final Button c;
    @NotNull
    public final Button d;
    @NotNull
    public final TextView e;
    @NotNull
    public final EditText f;
    @NotNull
    public final TextView g;

    public InviteBuddyDialog(@NotNull Context context, @NotNull CoveContact contact) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contact, "contact");
        this.f4455a = context;
        Intrinsics.checkNotNull(context);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
        this.b = bottomSheetDialog;
        bottomSheetDialog.requestWindowFeature(1);
        bottomSheetDialog.setContentView(R.layout.send_buddy_invite_dialog);
        View findViewById = bottomSheetDialog.findViewById(R.id.btn_cancel);
        Intrinsics.checkNotNull(findViewById);
        Button button = (Button) findViewById;
        this.c = button;
        View findViewById2 = bottomSheetDialog.findViewById(R.id.btn_send_invite);
        Intrinsics.checkNotNull(findViewById2);
        this.d = (Button) findViewById2;
        View findViewById3 = bottomSheetDialog.findViewById(R.id.title);
        Intrinsics.checkNotNull(findViewById3);
        TextView textView = (TextView) findViewById3;
        this.e = textView;
        View findViewById4 = bottomSheetDialog.findViewById(R.id.updateMsgText);
        Intrinsics.checkNotNull(findViewById4);
        EditText editText = (EditText) findViewById4;
        this.f = editText;
        View findViewById5 = bottomSheetDialog.findViewById(R.id.tv_text_num);
        Intrinsics.checkNotNull(findViewById5);
        TextView textView2 = (TextView) findViewById5;
        this.g = textView2;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String string = context.getString(R.string.invitation_message_to_buddy);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…itation_message_to_buddy)");
        String format = String.format(locale, string, Arrays.copyOf(new Object[]{contact.getName()}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        textView.setText(format);
        String string2 = context.getString(R.string.buddy_request_msg);
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.buddy_request_msg)");
        String format2 = String.format(locale, string2, Arrays.copyOf(new Object[]{contact.getName()}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
        editText.setText(format2);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.dialogs.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InviteBuddyDialog.b(InviteBuddyDialog.this, view);
            }
        });
        textView2.setText(editText.getText().length() + " / 180 Characters");
        editText.addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.fitnessbuddies.dialogs.InviteBuddyDialog.2
            @Override // android.text.TextWatcher
            public void afterTextChanged(@NotNull Editable s) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@NotNull CharSequence s, int i, int i2, int i3) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@NotNull CharSequence s, int i, int i2, int i3) {
                Intrinsics.checkNotNullParameter(s, "s");
                if (s.length() > 0) {
                    TextView tv_text_num = InviteBuddyDialog.this.getTv_text_num();
                    tv_text_num.setText(InviteBuddyDialog.this.getUpdateMsgText().length() + " / 180 Characters");
                }
            }
        });
    }

    public static final void b(InviteBuddyDialog this$0, View view) {
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
    public final Button getBtn_send_invite() {
        return this.d;
    }

    @NotNull
    public final Context getContext() {
        return this.f4455a;
    }

    @NotNull
    public final TextView getTitle() {
        return this.e;
    }

    @NotNull
    public final TextView getTv_text_num() {
        return this.g;
    }

    @NotNull
    public final EditText getUpdateMsgText() {
        return this.f;
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
        Context context = this.f4455a;
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
