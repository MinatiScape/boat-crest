package com.github.anastr.speedviewlib.components.note;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0019\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J \u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0014J\u0006\u0010\f\u001a\u00020\bJ\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\bJ\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u000fJ\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0002R\u0013\u0010\u0012\u001a\u00020\u00028F@\u0006¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001c"}, d2 = {"Lcom/github/anastr/speedviewlib/components/note/TextNote;", "Lcom/github/anastr/speedviewlib/components/note/Note;", "", "viewWidth", "", "build", "Landroid/graphics/Canvas;", "canvas", "", "leftX", "topY", "drawContains", "getTextSize", "textSize", "setTextSize", "Landroid/graphics/Typeface;", "typeface", "setTextTypeFace", "textColor", "setTextColor", "getTextColor", "()I", "Landroid/content/Context;", "context", "", "noteText", "<init>", "(Landroid/content/Context;Ljava/lang/CharSequence;)V", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes9.dex */
public final class TextNote extends Note<TextNote> {
    @Nullable
    public final CharSequence q;
    @NotNull
    public final TextPaint r;
    public float s;
    @Nullable
    public StaticLayout t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextNote(@NotNull Context context, @Nullable CharSequence charSequence) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.q = charSequence;
        TextPaint textPaint = new TextPaint(1);
        this.r = textPaint;
        this.s = textPaint.getTextSize();
        if (charSequence != null) {
            textPaint.setTextAlign(Paint.Align.LEFT);
            return;
        }
        throw new IllegalArgumentException("noteText cannot be null.".toString());
    }

    @Override // com.github.anastr.speedviewlib.components.note.Note
    public void build(int i) {
        StaticLayout staticLayout = new StaticLayout(this.q, this.r, i, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
        this.t = staticLayout;
        Intrinsics.checkNotNull(staticLayout);
        int lineCount = staticLayout.getLineCount();
        int i2 = 0;
        if (lineCount > 0) {
            int i3 = 0;
            while (true) {
                int i4 = i2 + 1;
                StaticLayout staticLayout2 = this.t;
                Intrinsics.checkNotNull(staticLayout2);
                i3 = (int) Math.max(i3, staticLayout2.getLineWidth(i2));
                if (i4 >= lineCount) {
                    break;
                }
                i2 = i4;
            }
            i2 = i3;
        }
        StaticLayout staticLayout3 = this.t;
        Intrinsics.checkNotNull(staticLayout3);
        noticeContainsSizeChange(i2, staticLayout3.getHeight());
    }

    @Override // com.github.anastr.speedviewlib.components.note.Note
    public void drawContains(@NotNull Canvas canvas, float f, float f2) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.save();
        canvas.translate(f, f2);
        StaticLayout staticLayout = this.t;
        Intrinsics.checkNotNull(staticLayout);
        staticLayout.draw(canvas);
        canvas.restore();
    }

    public final int getTextColor() {
        return this.r.getColor();
    }

    public final float getTextSize() {
        return this.s;
    }

    @NotNull
    public final TextNote setTextColor(int i) {
        this.r.setColor(i);
        return this;
    }

    @NotNull
    public final TextNote setTextSize(float f) {
        this.s = f;
        this.r.setTextSize(f);
        return this;
    }

    @NotNull
    public final TextNote setTextTypeFace(@NotNull Typeface typeface) {
        Intrinsics.checkNotNullParameter(typeface, "typeface");
        this.r.setTypeface(typeface);
        return this;
    }
}
