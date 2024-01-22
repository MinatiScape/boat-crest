package com.mappls.sdk.maps.text;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
@Keep
/* loaded from: classes11.dex */
public class LocalGlyphRasterizer {
    private final Bitmap bitmap;
    @NonNull
    private final Canvas canvas;
    @NonNull
    private final Paint paint;

    public LocalGlyphRasterizer() {
        Bitmap createBitmap = Bitmap.createBitmap(35, 35, Bitmap.Config.ARGB_8888);
        this.bitmap = createBitmap;
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        paint.setTextSize(24.0f);
        Canvas canvas = new Canvas();
        this.canvas = canvas;
        canvas.setBitmap(createBitmap);
    }

    @WorkerThread
    public Bitmap drawGlyphBitmap(String str, boolean z, char c) {
        this.paint.setTypeface(Typeface.create(str, z ? 1 : 0));
        this.canvas.drawColor(-1);
        this.canvas.drawText(String.valueOf(c), 5.0f, 25.0f, this.paint);
        return this.bitmap;
    }
}
