package androidx.emoji2.viewsintegration;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.emoji2.text.EmojiCompat;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
@RequiresApi(19)
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class c implements TextWatcher {
    public final EditText h;
    public final boolean i;
    public EmojiCompat.InitCallback j;
    public int k = Integer.MAX_VALUE;
    public int l = 0;
    public boolean m = true;

    @RequiresApi(19)
    /* loaded from: classes.dex */
    public static class a extends EmojiCompat.InitCallback {

        /* renamed from: a  reason: collision with root package name */
        public final Reference<EditText> f1291a;

        public a(EditText editText) {
            this.f1291a = new WeakReference(editText);
        }

        @Override // androidx.emoji2.text.EmojiCompat.InitCallback
        public void onInitialized() {
            super.onInitialized();
            c.c(this.f1291a.get(), 1);
        }
    }

    public c(EditText editText, boolean z) {
        this.h = editText;
        this.i = z;
    }

    public static void c(@Nullable EditText editText, int i) {
        if (i == 1 && editText != null && editText.isAttachedToWindow()) {
            Editable editableText = editText.getEditableText();
            int selectionStart = Selection.getSelectionStart(editableText);
            int selectionEnd = Selection.getSelectionEnd(editableText);
            EmojiCompat.get().process(editableText);
            b.b(editableText, selectionStart, selectionEnd);
        }
    }

    public final EmojiCompat.InitCallback a() {
        if (this.j == null) {
            this.j = new a(this.h);
        }
        return this.j;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    public boolean b() {
        return this.m;
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void d(int i) {
        this.l = i;
    }

    public void e(boolean z) {
        if (this.m != z) {
            if (this.j != null) {
                EmojiCompat.get().unregisterInitCallback(this.j);
            }
            this.m = z;
            if (z) {
                c(this.h, EmojiCompat.get().getLoadState());
            }
        }
    }

    public void f(int i) {
        this.k = i;
    }

    public final boolean g() {
        return (this.m && (this.i || EmojiCompat.isConfigured())) ? false : true;
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.h.isInEditMode() || g() || i2 > i3 || !(charSequence instanceof Spannable)) {
            return;
        }
        int loadState = EmojiCompat.get().getLoadState();
        if (loadState != 0) {
            if (loadState == 1) {
                EmojiCompat.get().process((Spannable) charSequence, i, i + i3, this.k, this.l);
                return;
            } else if (loadState != 3) {
                return;
            }
        }
        EmojiCompat.get().registerInitCallback(a());
    }
}
