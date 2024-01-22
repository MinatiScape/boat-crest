package androidx.emoji2.viewsintegration;

import android.os.Build;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
/* loaded from: classes.dex */
public final class EmojiEditTextHelper {

    /* renamed from: a  reason: collision with root package name */
    public final b f1283a;
    public int b;
    public int c;

    @RequiresApi(19)
    /* loaded from: classes.dex */
    public static class a extends b {

        /* renamed from: a  reason: collision with root package name */
        public final EditText f1284a;
        public final c b;

        public a(@NonNull EditText editText, boolean z) {
            this.f1284a = editText;
            c cVar = new c(editText, z);
            this.b = cVar;
            editText.addTextChangedListener(cVar);
            editText.setEditableFactory(androidx.emoji2.viewsintegration.a.getInstance());
        }

        @Override // androidx.emoji2.viewsintegration.EmojiEditTextHelper.b
        public KeyListener a(@Nullable KeyListener keyListener) {
            if (keyListener instanceof EmojiKeyListener) {
                return keyListener;
            }
            if (keyListener == null) {
                return null;
            }
            return keyListener instanceof NumberKeyListener ? keyListener : new EmojiKeyListener(keyListener);
        }

        @Override // androidx.emoji2.viewsintegration.EmojiEditTextHelper.b
        public boolean b() {
            return this.b.b();
        }

        @Override // androidx.emoji2.viewsintegration.EmojiEditTextHelper.b
        public InputConnection c(@NonNull InputConnection inputConnection, @NonNull EditorInfo editorInfo) {
            return inputConnection instanceof EmojiInputConnection ? inputConnection : new EmojiInputConnection(this.f1284a, inputConnection, editorInfo);
        }

        @Override // androidx.emoji2.viewsintegration.EmojiEditTextHelper.b
        public void d(int i) {
            this.b.d(i);
        }

        @Override // androidx.emoji2.viewsintegration.EmojiEditTextHelper.b
        public void e(boolean z) {
            this.b.e(z);
        }

        @Override // androidx.emoji2.viewsintegration.EmojiEditTextHelper.b
        public void f(int i) {
            this.b.f(i);
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        @Nullable
        public KeyListener a(@Nullable KeyListener keyListener) {
            return keyListener;
        }

        public boolean b() {
            return false;
        }

        public InputConnection c(@NonNull InputConnection inputConnection, @NonNull EditorInfo editorInfo) {
            return inputConnection;
        }

        public void d(int i) {
        }

        public void e(boolean z) {
        }

        public void f(int i) {
        }
    }

    public EmojiEditTextHelper(@NonNull EditText editText) {
        this(editText, true);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getEmojiReplaceStrategy() {
        return this.c;
    }

    @Nullable
    public KeyListener getKeyListener(@Nullable KeyListener keyListener) {
        return this.f1283a.a(keyListener);
    }

    public int getMaxEmojiCount() {
        return this.b;
    }

    public boolean isEnabled() {
        return this.f1283a.b();
    }

    @Nullable
    public InputConnection onCreateInputConnection(@Nullable InputConnection inputConnection, @NonNull EditorInfo editorInfo) {
        if (inputConnection == null) {
            return null;
        }
        return this.f1283a.c(inputConnection, editorInfo);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setEmojiReplaceStrategy(int i) {
        this.c = i;
        this.f1283a.d(i);
    }

    public void setEnabled(boolean z) {
        this.f1283a.e(z);
    }

    public void setMaxEmojiCount(@IntRange(from = 0) int i) {
        Preconditions.checkArgumentNonnegative(i, "maxEmojiCount should be greater than 0");
        this.b = i;
        this.f1283a.f(i);
    }

    public EmojiEditTextHelper(@NonNull EditText editText, boolean z) {
        this.b = Integer.MAX_VALUE;
        this.c = 0;
        Preconditions.checkNotNull(editText, "editText cannot be null");
        if (Build.VERSION.SDK_INT < 19) {
            this.f1283a = new b();
        } else {
            this.f1283a = new a(editText, z);
        }
    }
}
