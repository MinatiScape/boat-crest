package com.google.android.material.textfield;

import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import com.google.android.material.R;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.textfield.TextInputLayout;
/* loaded from: classes10.dex */
public class i extends e {
    public final TextWatcher e;
    public final TextInputLayout.OnEditTextAttachedListener f;
    public final TextInputLayout.OnEndIconChangedListener g;

    /* loaded from: classes10.dex */
    public class a extends TextWatcherAdapter {
        public a() {
        }

        @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            i iVar = i.this;
            iVar.c.setChecked(!iVar.g());
        }
    }

    /* loaded from: classes10.dex */
    public class b implements TextInputLayout.OnEditTextAttachedListener {
        public b() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.OnEditTextAttachedListener
        public void onEditTextAttached(@NonNull TextInputLayout textInputLayout) {
            EditText editText = textInputLayout.getEditText();
            i iVar = i.this;
            iVar.c.setChecked(!iVar.g());
            editText.removeTextChangedListener(i.this.e);
            editText.addTextChangedListener(i.this.e);
        }
    }

    /* loaded from: classes10.dex */
    public class c implements TextInputLayout.OnEndIconChangedListener {

        /* loaded from: classes10.dex */
        public class a implements Runnable {
            public final /* synthetic */ EditText h;

            public a(EditText editText) {
                this.h = editText;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.h.removeTextChangedListener(i.this.e);
            }
        }

        public c() {
        }

        @Override // com.google.android.material.textfield.TextInputLayout.OnEndIconChangedListener
        public void onEndIconChanged(@NonNull TextInputLayout textInputLayout, int i) {
            EditText editText = textInputLayout.getEditText();
            if (editText == null || i != 1) {
                return;
            }
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            editText.post(new a(editText));
        }
    }

    /* loaded from: classes10.dex */
    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EditText editText = i.this.f10389a.getEditText();
            if (editText == null) {
                return;
            }
            int selectionEnd = editText.getSelectionEnd();
            if (i.this.g()) {
                editText.setTransformationMethod(null);
            } else {
                editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            if (selectionEnd >= 0) {
                editText.setSelection(selectionEnd);
            }
            i.this.f10389a.refreshEndIconDrawableState();
        }
    }

    public i(@NonNull TextInputLayout textInputLayout, @DrawableRes int i) {
        super(textInputLayout, i);
        this.e = new a();
        this.f = new b();
        this.g = new c();
    }

    public static boolean h(EditText editText) {
        return editText != null && (editText.getInputType() == 16 || editText.getInputType() == 128 || editText.getInputType() == 144 || editText.getInputType() == 224);
    }

    @Override // com.google.android.material.textfield.e
    public void a() {
        TextInputLayout textInputLayout = this.f10389a;
        int i = this.d;
        if (i == 0) {
            i = R.drawable.design_password_eye;
        }
        textInputLayout.setEndIconDrawable(i);
        TextInputLayout textInputLayout2 = this.f10389a;
        textInputLayout2.setEndIconContentDescription(textInputLayout2.getResources().getText(R.string.password_toggle_content_description));
        this.f10389a.setEndIconVisible(true);
        this.f10389a.setEndIconCheckable(true);
        this.f10389a.setEndIconOnClickListener(new d());
        this.f10389a.addOnEditTextAttachedListener(this.f);
        this.f10389a.addOnEndIconChangedListener(this.g);
        EditText editText = this.f10389a.getEditText();
        if (h(editText)) {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    public final boolean g() {
        EditText editText = this.f10389a.getEditText();
        return editText != null && (editText.getTransformationMethod() instanceof PasswordTransformationMethod);
    }
}
