package androidx.emoji2.text;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
@RequiresApi(19)
/* loaded from: classes.dex */
public abstract class EmojiSpan extends ReplacementSpan {
    @NonNull
    public final EmojiMetadata i;
    public final Paint.FontMetricsInt h = new Paint.FontMetricsInt();
    public short j = -1;
    public short k = -1;
    public float l = 1.0f;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public EmojiSpan(@NonNull EmojiMetadata emojiMetadata) {
        Preconditions.checkNotNull(emojiMetadata, "metadata cannot be null");
        this.i = emojiMetadata;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final int a() {
        return this.j;
    }

    @RestrictTo({RestrictTo.Scope.TESTS})
    public final int getHeight() {
        return this.k;
    }

    @RestrictTo({RestrictTo.Scope.TESTS})
    public final int getId() {
        return getMetadata().getId();
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final EmojiMetadata getMetadata() {
        return this.i;
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(@NonNull Paint paint, @SuppressLint({"UnknownNullness"}) CharSequence charSequence, int i, int i2, @Nullable Paint.FontMetricsInt fontMetricsInt) {
        paint.getFontMetricsInt(this.h);
        Paint.FontMetricsInt fontMetricsInt2 = this.h;
        this.l = (Math.abs(fontMetricsInt2.descent - fontMetricsInt2.ascent) * 1.0f) / this.i.getHeight();
        this.k = (short) (this.i.getHeight() * this.l);
        short width = (short) (this.i.getWidth() * this.l);
        this.j = width;
        if (fontMetricsInt != null) {
            Paint.FontMetricsInt fontMetricsInt3 = this.h;
            fontMetricsInt.ascent = fontMetricsInt3.ascent;
            fontMetricsInt.descent = fontMetricsInt3.descent;
            fontMetricsInt.top = fontMetricsInt3.top;
            fontMetricsInt.bottom = fontMetricsInt3.bottom;
        }
        return width;
    }
}
