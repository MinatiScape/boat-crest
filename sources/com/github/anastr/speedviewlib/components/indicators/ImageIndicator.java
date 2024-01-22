package com.github.anastr.speedviewlib.components.indicators;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0014¨\u0006\u0010"}, d2 = {"Lcom/github/anastr/speedviewlib/components/indicators/ImageIndicator;", "Lcom/github/anastr/speedviewlib/components/indicators/Indicator;", "Landroid/graphics/Canvas;", "canvas", "", "draw", "updateIndicator", "", "withEffects", "setWithEffects", "Landroid/content/Context;", "context", "Landroid/graphics/drawable/Drawable;", "bitmapIndicator", "<init>", "(Landroid/content/Context;Landroid/graphics/drawable/Drawable;)V", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes9.dex */
public final class ImageIndicator extends Indicator<ImageIndicator> {
    @NotNull
    public final Drawable f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImageIndicator(@NotNull Context context, @NotNull Drawable bitmapIndicator) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bitmapIndicator, "bitmapIndicator");
        this.f = bitmapIndicator;
    }

    @Override // com.github.anastr.speedviewlib.components.indicators.Indicator
    public void draw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        this.f.draw(canvas);
    }

    @Override // com.github.anastr.speedviewlib.components.indicators.Indicator
    public void setWithEffects(boolean z) {
    }

    @Override // com.github.anastr.speedviewlib.components.indicators.Indicator
    public void updateIndicator() {
        this.f.setBounds(0, 0, (int) getViewSize(), (int) getViewSize());
    }
}