package com.google.android.material.timepicker;

import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;
/* loaded from: classes10.dex */
public class f implements TextView.OnEditorActionListener, View.OnKeyListener {
    public final ChipTextInputComboView h;
    public final ChipTextInputComboView i;
    public final TimeModel j;
    public boolean k = false;

    public f(ChipTextInputComboView chipTextInputComboView, ChipTextInputComboView chipTextInputComboView2, TimeModel timeModel) {
        this.h = chipTextInputComboView;
        this.i = chipTextInputComboView2;
        this.j = timeModel;
    }

    public void a() {
        TextInputLayout e = this.h.e();
        TextInputLayout e2 = this.i.e();
        EditText editText = e.getEditText();
        EditText editText2 = e2.getEditText();
        editText.setImeOptions(268435461);
        editText2.setImeOptions(268435462);
        editText.setOnEditorActionListener(this);
        editText.setOnKeyListener(this);
        editText2.setOnKeyListener(this);
    }

    public final void b(int i) {
        this.i.setChecked(i == 12);
        this.h.setChecked(i == 10);
        this.j.m = i;
    }

    public final boolean c(int i, KeyEvent keyEvent, EditText editText) {
        Editable text = editText.getText();
        if (text == null) {
            return false;
        }
        if (i >= 7 && i <= 16 && keyEvent.getAction() == 1 && editText.getSelectionStart() == 2 && text.length() == 2) {
            b(12);
            return true;
        }
        return false;
    }

    public final boolean d(int i, KeyEvent keyEvent, EditText editText) {
        if (i == 67 && keyEvent.getAction() == 0 && TextUtils.isEmpty(editText.getText())) {
            b(10);
            return true;
        }
        return false;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        boolean z = i == 5;
        if (z) {
            b(12);
        }
        return z;
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        boolean c;
        if (this.k) {
            return false;
        }
        this.k = true;
        EditText editText = (EditText) view;
        if (this.j.m == 12) {
            c = d(i, keyEvent, editText);
        } else {
            c = c(i, keyEvent, editText);
        }
        this.k = false;
        return c;
    }
}
