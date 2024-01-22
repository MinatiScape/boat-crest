package androidx.appcompat.widget;

import android.content.res.TypedArray;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.R;
import androidx.emoji2.viewsintegration.EmojiEditTextHelper;
/* loaded from: classes.dex */
public class e {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final EditText f470a;
    @NonNull
    public final EmojiEditTextHelper b;

    public e(@NonNull EditText editText) {
        this.f470a = editText;
        this.b = new EmojiEditTextHelper(editText, false);
    }

    @Nullable
    public KeyListener a(@Nullable KeyListener keyListener) {
        return b(keyListener) ? this.b.getKeyListener(keyListener) : keyListener;
    }

    public boolean b(KeyListener keyListener) {
        return !(keyListener instanceof NumberKeyListener);
    }

    public boolean c() {
        return this.b.isEnabled();
    }

    public void d(@Nullable AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = this.f470a.getContext().obtainStyledAttributes(attributeSet, R.styleable.AppCompatTextView, i, 0);
        try {
            int i2 = R.styleable.AppCompatTextView_emojiCompatEnabled;
            boolean z = obtainStyledAttributes.hasValue(i2) ? obtainStyledAttributes.getBoolean(i2, true) : true;
            obtainStyledAttributes.recycle();
            f(z);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    @Nullable
    public InputConnection e(@Nullable InputConnection inputConnection, @NonNull EditorInfo editorInfo) {
        return this.b.onCreateInputConnection(inputConnection, editorInfo);
    }

    public void f(boolean z) {
        this.b.setEnabled(z);
    }
}
