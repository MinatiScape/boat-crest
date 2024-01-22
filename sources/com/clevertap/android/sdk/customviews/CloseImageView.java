package com.clevertap.android.sdk.customviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.appcompat.widget.AppCompatImageView;
import com.clevertap.android.sdk.Logger;
/* loaded from: classes2.dex */
public final class CloseImageView extends AppCompatImageView {
    public final int k;

    @SuppressLint({"ResourceType"})
    public CloseImageView(Context context) {
        super(context);
        this.k = a(40);
        setId(199272);
    }

    public final int a(int i) {
        return (int) TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }

    @Override // android.widget.ImageView, android.view.View
    @SuppressLint({"DrawAllocation"})
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try {
            Context context = getContext();
            Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("ct_close", "drawable", context.getPackageName()), null);
            if (decodeResource != null) {
                int i = this.k;
                canvas.drawBitmap(Bitmap.createScaledBitmap(decodeResource, i, i, true), 0.0f, 0.0f, new Paint());
            } else {
                Logger.v("Unable to find inapp notif close button image");
            }
        } catch (Throwable th) {
            Logger.v("Error displaying the inapp notif close button image:", th);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i, int i2) {
        int i3 = this.k;
        setMeasuredDimension(i3, i3);
    }

    @SuppressLint({"ResourceType"})
    public CloseImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = a(40);
        setId(199272);
    }

    @SuppressLint({"ResourceType"})
    public CloseImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = a(40);
        setId(199272);
    }
}
