package androidx.emoji2.viewsintegration;

import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.text.Spanned;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.emoji2.text.EmojiCompat;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
@RequiresApi(19)
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class b implements InputFilter {
    public final TextView h;
    public EmojiCompat.InitCallback i;

    @RequiresApi(19)
    /* loaded from: classes.dex */
    public static class a extends EmojiCompat.InitCallback {

        /* renamed from: a  reason: collision with root package name */
        public final Reference<TextView> f1290a;
        public final Reference<b> b;

        public a(TextView textView, b bVar) {
            this.f1290a = new WeakReference(textView);
            this.b = new WeakReference(bVar);
        }

        public final boolean a(@Nullable TextView textView, @Nullable InputFilter inputFilter) {
            InputFilter[] filters;
            if (inputFilter == null || textView == null || (filters = textView.getFilters()) == null) {
                return false;
            }
            for (InputFilter inputFilter2 : filters) {
                if (inputFilter2 == inputFilter) {
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.emoji2.text.EmojiCompat.InitCallback
        public void onInitialized() {
            CharSequence text;
            CharSequence process;
            super.onInitialized();
            TextView textView = this.f1290a.get();
            if (a(textView, this.b.get()) && textView.isAttachedToWindow() && text != (process = EmojiCompat.get().process((text = textView.getText())))) {
                int selectionStart = Selection.getSelectionStart(process);
                int selectionEnd = Selection.getSelectionEnd(process);
                textView.setText(process);
                if (process instanceof Spannable) {
                    b.b((Spannable) process, selectionStart, selectionEnd);
                }
            }
        }
    }

    public b(@NonNull TextView textView) {
        this.h = textView;
    }

    public static void b(Spannable spannable, int i, int i2) {
        if (i >= 0 && i2 >= 0) {
            Selection.setSelection(spannable, i, i2);
        } else if (i >= 0) {
            Selection.setSelection(spannable, i);
        } else if (i2 >= 0) {
            Selection.setSelection(spannable, i2);
        }
    }

    public final EmojiCompat.InitCallback a() {
        if (this.i == null) {
            this.i = new a(this.h, this);
        }
        return this.i;
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        if (this.h.isInEditMode()) {
            return charSequence;
        }
        int loadState = EmojiCompat.get().getLoadState();
        if (loadState != 0) {
            boolean z = true;
            if (loadState == 1) {
                if (i4 == 0 && i3 == 0 && spanned.length() == 0 && charSequence == this.h.getText()) {
                    z = false;
                }
                if (!z || charSequence == null) {
                    return charSequence;
                }
                if (i != 0 || i2 != charSequence.length()) {
                    charSequence = charSequence.subSequence(i, i2);
                }
                return EmojiCompat.get().process(charSequence, 0, charSequence.length());
            } else if (loadState != 3) {
                return charSequence;
            }
        }
        EmojiCompat.get().registerInitCallback(a());
        return charSequence;
    }
}
