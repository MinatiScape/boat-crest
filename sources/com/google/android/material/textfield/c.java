package com.google.android.material.textfield;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
/* loaded from: classes10.dex */
public class c extends MaterialShapeDrawable {
    @NonNull
    public final Paint G;
    @NonNull
    public final RectF H;

    public c() {
        this(null);
    }

    public void A(float f, float f2, float f3, float f4) {
        RectF rectF = this.H;
        if (f == rectF.left && f2 == rectF.top && f3 == rectF.right && f4 == rectF.bottom) {
            return;
        }
        rectF.set(f, f2, f3, f4);
        invalidateSelf();
    }

    public void B(@NonNull RectF rectF) {
        A(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }

    public final void C() {
        this.G.setStyle(Paint.Style.FILL_AND_STROKE);
        this.G.setColor(-1);
        this.G.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable
    public void drawStrokeShape(@NonNull Canvas canvas) {
        if (this.H.isEmpty()) {
            super.drawStrokeShape(canvas);
            return;
        }
        canvas.save();
        if (Build.VERSION.SDK_INT >= 26) {
            canvas.clipOutRect(this.H);
        } else {
            canvas.clipRect(this.H, Region.Op.DIFFERENCE);
        }
        super.drawStrokeShape(canvas);
        canvas.restore();
    }

    public boolean y() {
        return !this.H.isEmpty();
    }

    public void z() {
        A(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public c(@Nullable ShapeAppearanceModel shapeAppearanceModel) {
        super(shapeAppearanceModel == null ? new ShapeAppearanceModel() : shapeAppearanceModel);
        this.G = new Paint(1);
        C();
        this.H = new RectF();
    }
}
