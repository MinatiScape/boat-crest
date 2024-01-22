package com.coveiot.android.activitymodes.activity1k;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SeperatedProgressbar extends Drawable {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    public final int f2727a;
    public final int b;
    @NotNull
    public Context c;
    @NotNull
    public final Paint d;
    @NotNull
    public final RectF e;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SeperatedProgressbar(int i, int i2, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f2727a = i;
        this.b = i2;
        this.c = context;
        this.d = new Paint();
        this.e = new RectF();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        float level = getLevel() / 10000.0f;
        Rect bounds = getBounds();
        Intrinsics.checkNotNullExpressionValue(bounds, "bounds");
        float f = 3;
        float width = (bounds.width() - (2 * 2.0f)) / f;
        this.e.set(0.0f, 0.0f, width, bounds.height());
        this.d.setColor(this.f2727a);
        int i = 0;
        while (i < 3) {
            float f2 = i / 3.0f;
            int i2 = i + 1;
            float f3 = i2 / 3.0f;
            if (f2 <= level && level <= f3) {
                RectF rectF = this.e;
                float f4 = rectF.left;
                float f5 = f4 + (f * width * (level - f2));
                canvas.drawRect(f4, rectF.top, f5, rectF.bottom, this.d);
                this.d.setColor(this.b);
                RectF rectF2 = this.e;
                canvas.drawRect(f5, rectF2.top, rectF2.right, rectF2.bottom, this.d);
            } else {
                canvas.drawRect(this.e, this.d);
            }
            RectF rectF3 = this.e;
            rectF3.offset(rectF3.width() + 2.0f, 0.0f);
            i = i2;
        }
    }

    @NotNull
    public final Context getContext() {
        return this.c;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i) {
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.c = context;
    }
}
