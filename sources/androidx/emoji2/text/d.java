package androidx.emoji2.text;

import android.os.Build;
import android.text.TextPaint;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.PaintCompat;
import androidx.emoji2.text.EmojiCompat;
@AnyThread
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class d implements EmojiCompat.GlyphChecker {
    public static final ThreadLocal<StringBuilder> b = new ThreadLocal<>();

    /* renamed from: a  reason: collision with root package name */
    public final TextPaint f1263a;

    public d() {
        TextPaint textPaint = new TextPaint();
        this.f1263a = textPaint;
        textPaint.setTextSize(10.0f);
    }

    public static StringBuilder a() {
        ThreadLocal<StringBuilder> threadLocal = b;
        if (threadLocal.get() == null) {
            threadLocal.set(new StringBuilder());
        }
        return threadLocal.get();
    }

    @Override // androidx.emoji2.text.EmojiCompat.GlyphChecker
    public boolean hasGlyph(@NonNull CharSequence charSequence, int i, int i2, int i3) {
        int i4 = Build.VERSION.SDK_INT;
        if (i4 >= 23 || i3 <= i4) {
            StringBuilder a2 = a();
            a2.setLength(0);
            while (i < i2) {
                a2.append(charSequence.charAt(i));
                i++;
            }
            return PaintCompat.hasGlyph(this.f1263a, a2.toString());
        }
        return false;
    }
}
