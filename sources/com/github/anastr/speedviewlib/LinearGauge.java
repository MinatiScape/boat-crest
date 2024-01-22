package com.github.anastr.speedviewlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001:\u0001\u001eB'\b\u0007\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0019\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0004¢\u0006\u0004\b\u001c\u0010\u001dJ\b\u0010\u0003\u001a\u00020\u0002H$J(\u0010\t\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0014J\b\u0010\n\u001a\u00020\u0002H\u0014J\b\u0010\f\u001a\u00020\u000bH\u0004J\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000bH\u0014R*\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u001f"}, d2 = {"Lcom/github/anastr/speedviewlib/LinearGauge;", "Lcom/github/anastr/speedviewlib/Gauge;", "", "updateFrontAndBackBitmaps", "", Constants.INAPP_WINDOW, "h", "oldW", "oldH", "onSizeChanged", "updateBackgroundBitmap", "Landroid/graphics/Canvas;", "createForegroundBitmapCanvas", "canvas", "onDraw", "Lcom/github/anastr/speedviewlib/LinearGauge$Orientation;", Constants.KEY_ORIENTATION, "e0", "Lcom/github/anastr/speedviewlib/LinearGauge$Orientation;", "getOrientation", "()Lcom/github/anastr/speedviewlib/LinearGauge$Orientation;", "setOrientation", "(Lcom/github/anastr/speedviewlib/LinearGauge$Orientation;)V", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", ExifInterface.TAG_ORIENTATION, "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes9.dex */
public abstract class LinearGauge extends Gauge {
    @NotNull
    public final Paint b0;
    @NotNull
    public final Rect c0;
    @NotNull
    public Bitmap d0;
    @NotNull
    public Orientation e0;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/github/anastr/speedviewlib/LinearGauge$Orientation;", "", "<init>", "(Ljava/lang/String;I)V", "HORIZONTAL", "VERTICAL", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes9.dex */
    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public LinearGauge(@NotNull Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public LinearGauge(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ LinearGauge(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    private final void l(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.LinearGauge, 0, 0);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.theme.obtainStyl…leable.LinearGauge, 0, 0)");
        int i = obtainStyledAttributes.getInt(R.styleable.LinearGauge_sv_orientation, -1);
        if (i != -1) {
            setOrientation(Orientation.values()[i]);
        }
        obtainStyledAttributes.recycle();
    }

    @NotNull
    public final Canvas createForegroundBitmapCanvas() {
        if (getWidthPa() != 0 && getHeightPa() != 0) {
            Bitmap createBitmap = Bitmap.createBitmap(getWidthPa(), getHeightPa(), Bitmap.Config.ARGB_8888);
            Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(widthPa, he… Bitmap.Config.ARGB_8888)");
            this.d0 = createBitmap;
            return new Canvas(this.d0);
        }
        return new Canvas();
    }

    @NotNull
    public final Orientation getOrientation() {
        return this.e0;
    }

    @Override // com.github.anastr.speedviewlib.Gauge, android.view.View
    public void onDraw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        if (this.e0 == Orientation.HORIZONTAL) {
            this.c0.set(0, 0, (int) (getWidthPa() * getOffsetSpeed()), getHeightPa());
        } else {
            this.c0.set(0, getHeightPa() - ((int) (getHeightPa() * getOffsetSpeed())), getWidthPa(), getHeightPa());
        }
        canvas.translate(getPadding(), getPadding());
        Bitmap bitmap = this.d0;
        Rect rect = this.c0;
        canvas.drawBitmap(bitmap, rect, rect, this.b0);
        canvas.translate(-getPadding(), -getPadding());
        drawSpeedUnitText(canvas);
    }

    @Override // com.github.anastr.speedviewlib.Gauge, android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        updateBackgroundBitmap();
    }

    public final void setOrientation(@NotNull Orientation orientation) {
        Intrinsics.checkNotNullParameter(orientation, "orientation");
        this.e0 = orientation;
        if (isAttachedToWindow()) {
            requestLayout();
            invalidateGauge();
        }
    }

    @Override // com.github.anastr.speedviewlib.Gauge
    public void updateBackgroundBitmap() {
        updateFrontAndBackBitmaps();
    }

    public abstract void updateFrontAndBackBitmaps();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public LinearGauge(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.b0 = new Paint(1);
        this.c0 = new Rect();
        Bitmap createBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(1, 1, Bitmap.Config.ARGB_8888)");
        this.d0 = createBitmap;
        this.e0 = Orientation.HORIZONTAL;
        l(context, attributeSet);
    }
}
