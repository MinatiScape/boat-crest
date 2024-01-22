package com.example.custom_dial;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes9.dex */
public class DrawTxtUtils {

    /* renamed from: a  reason: collision with root package name */
    public final String f7893a = getClass().getSimpleName();

    public final Bitmap a(String str, int i, Typeface typeface) {
        if (!TextUtils.isEmpty(str)) {
            Bitmap createBitmap = Bitmap.createBitmap(36, 48, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            TextPaint textPaint = new TextPaint();
            textPaint.setAntiAlias(true);
            textPaint.setStyle(Paint.Style.FILL);
            textPaint.setColor(i);
            textPaint.setTextSize(48.0f);
            if (typeface != null) {
                textPaint.setTypeface(typeface);
            }
            float measureText = textPaint.measureText(str);
            float abs = Math.abs(textPaint.ascent() + textPaint.descent());
            canvas.translate(18.0f, 24.0f);
            canvas.drawText(str, (-measureText) / 2.0f, abs / 2.0f, textPaint);
            return createBitmap;
        }
        throw new NullPointerException("文字不存在");
    }

    public final Bitmap b(String str, int i, Typeface typeface) {
        if (!TextUtils.isEmpty(str)) {
            Bitmap createBitmap = Bitmap.createBitmap(28, 38, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            TextPaint textPaint = new TextPaint();
            textPaint.setAntiAlias(true);
            textPaint.setStyle(Paint.Style.FILL);
            textPaint.setColor(i);
            textPaint.setTextSize(38.0f);
            if (typeface != null) {
                textPaint.setTypeface(typeface);
            }
            float measureText = textPaint.measureText(str);
            float abs = Math.abs(textPaint.ascent() + textPaint.descent());
            canvas.translate(14.0f, 19.0f);
            canvas.drawText(str, (-measureText) / 2.0f, abs / 2.0f, textPaint);
            return createBitmap;
        }
        throw new NullPointerException("文字不存在");
    }

    public final Bitmap c(int i, Typeface typeface) {
        Bitmap createBitmap = Bitmap.createBitmap(12, 38, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(i);
        textPaint.setTextSize(38.0f);
        if (typeface != null) {
            textPaint.setTypeface(typeface);
        }
        float measureText = textPaint.measureText(":");
        Rect rect = new Rect();
        textPaint.getTextBounds(":", 0, 1, rect);
        String str = this.f7893a;
        Log.e(str, "测量的文字的宽度:" + measureText + ",文字的高:" + rect.height());
        canvas.translate(6.0f, 19.0f);
        canvas.drawText(":", (-measureText) / 2.0f, (float) (rect.height() / 2), textPaint);
        return createBitmap;
    }

    public final Bitmap d(int i, Typeface typeface) {
        Bitmap createBitmap = Bitmap.createBitmap(12, 48, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(i);
        textPaint.setTextSize(48.0f);
        if (typeface != null) {
            textPaint.setTypeface(typeface);
        }
        float measureText = textPaint.measureText(":");
        Rect rect = new Rect();
        textPaint.getTextBounds(":", 0, 1, rect);
        canvas.translate(6.0f, 24.0f);
        canvas.drawText(":", (-measureText) / 2.0f, rect.height() / 2, textPaint);
        return createBitmap;
    }

    public Bitmap drawColonBitmap(int i, int i2, int i3, Typeface typeface) {
        if (i == 240) {
            if (i3 == 1) {
                return c(i2, typeface);
            }
            return d(i2, typeface);
        } else if (i == 356) {
            return f(i2, typeface);
        } else {
            if (i == 368) {
                return h(i2, typeface);
            }
            if (i == 466 && i3 == 1) {
                return j(i2, typeface);
            }
            return null;
        }
    }

    public Map<Integer, Bitmap> drawNumBitmap(int i, int i2, int i3, Typeface typeface) {
        HashMap hashMap = new HashMap();
        hashMap.clear();
        int i4 = 0;
        if (i == 240) {
            if (i3 == 1) {
                while (i4 < 10) {
                    hashMap.put(Integer.valueOf(i4), b(i4 + "", i2, typeface));
                    i4++;
                }
            } else {
                while (i4 < 10) {
                    hashMap.put(Integer.valueOf(i4), a(i4 + "", i2, typeface));
                    i4++;
                }
            }
        } else if (i == 356) {
            while (i4 < 10) {
                hashMap.put(Integer.valueOf(i4), e(i4 + "", i2, typeface));
                i4++;
            }
        } else if (i == 368) {
            while (i4 < 10) {
                hashMap.put(Integer.valueOf(i4), g(i4 + "", i2, typeface));
                i4++;
            }
        } else if (i == 466 && i3 == 1) {
            while (i4 < 10) {
                hashMap.put(Integer.valueOf(i4), i(i4 + "", i2, typeface));
                i4++;
            }
        }
        return hashMap;
    }

    public final Bitmap e(String str, int i, Typeface typeface) {
        if (!TextUtils.isEmpty(str)) {
            Bitmap createBitmap = Bitmap.createBitmap(40, 66, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            TextPaint textPaint = new TextPaint();
            textPaint.setAntiAlias(true);
            textPaint.setStyle(Paint.Style.FILL);
            textPaint.setColor(i);
            textPaint.setTextSize(66.0f);
            if (typeface != null) {
                textPaint.setTypeface(typeface);
            }
            float measureText = textPaint.measureText(str);
            float abs = Math.abs(textPaint.ascent() + textPaint.descent());
            canvas.translate(20.0f, 33.0f);
            canvas.drawText(str, (-measureText) / 2.0f, abs / 2.0f, textPaint);
            return createBitmap;
        }
        throw new NullPointerException("文字不存在");
    }

    public final Bitmap f(int i, Typeface typeface) {
        Bitmap createBitmap = Bitmap.createBitmap(12, 72, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(i);
        textPaint.setTextSize(72.0f);
        if (typeface != null) {
            textPaint.setTypeface(typeface);
        }
        float measureText = textPaint.measureText(":");
        Rect rect = new Rect();
        textPaint.getTextBounds(":", 0, 1, rect);
        String str = this.f7893a;
        Log.e(str, "测量的文字的宽度:" + measureText + ",文字的高:" + rect.height());
        canvas.translate(6.0f, 36.0f);
        canvas.drawText(":", (-measureText) / 2.0f, (float) (rect.height() / 2), textPaint);
        return createBitmap;
    }

    public final Bitmap g(String str, int i, Typeface typeface) {
        if (!TextUtils.isEmpty(str)) {
            Bitmap createBitmap = Bitmap.createBitmap(40, 66, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            TextPaint textPaint = new TextPaint();
            textPaint.setAntiAlias(true);
            textPaint.setStyle(Paint.Style.FILL);
            textPaint.setColor(i);
            textPaint.setTextSize(66.0f);
            if (typeface != null) {
                textPaint.setTypeface(typeface);
            }
            float measureText = textPaint.measureText(str);
            float abs = Math.abs(textPaint.ascent() + textPaint.descent());
            canvas.translate(20.0f, 33.0f);
            canvas.drawText(str, (-measureText) / 2.0f, abs / 2.0f, textPaint);
            return createBitmap;
        }
        throw new NullPointerException("文字不存在");
    }

    public final Bitmap h(int i, Typeface typeface) {
        Bitmap createBitmap = Bitmap.createBitmap(12, 72, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(i);
        textPaint.setTextSize(72.0f);
        if (typeface != null) {
            textPaint.setTypeface(typeface);
        }
        float measureText = textPaint.measureText(":");
        Rect rect = new Rect();
        textPaint.getTextBounds(":", 0, 1, rect);
        String str = this.f7893a;
        Log.e(str, "测量的文字的宽度:" + measureText + ",文字的高:" + rect.height());
        canvas.translate(6.0f, 36.0f);
        canvas.drawText(":", (-measureText) / 2.0f, (float) (rect.height() / 2), textPaint);
        return createBitmap;
    }

    public final Bitmap i(String str, int i, Typeface typeface) {
        if (!TextUtils.isEmpty(str)) {
            Bitmap createBitmap = Bitmap.createBitmap(48, 88, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            TextPaint textPaint = new TextPaint();
            textPaint.setAntiAlias(true);
            textPaint.setStyle(Paint.Style.FILL);
            textPaint.setColor(i);
            textPaint.setTextSize(88.0f);
            if (typeface != null) {
                textPaint.setTypeface(typeface);
            }
            float measureText = textPaint.measureText(str);
            float abs = Math.abs(textPaint.ascent() + textPaint.descent());
            canvas.translate(24.0f, 44.0f);
            canvas.drawText(str, (-measureText) / 2.0f, abs / 2.0f, textPaint);
            return createBitmap;
        }
        throw new NullPointerException("文字不存在");
    }

    public final Bitmap j(int i, Typeface typeface) {
        Bitmap createBitmap = Bitmap.createBitmap(20, 88, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(i);
        textPaint.setTextSize(88.0f);
        if (typeface != null) {
            textPaint.setTypeface(typeface);
        }
        float measureText = textPaint.measureText(":");
        Rect rect = new Rect();
        textPaint.getTextBounds(":", 0, 1, rect);
        String str = this.f7893a;
        Log.e(str, "测量的文字的宽度:" + measureText + ",文字的高:" + rect.height());
        canvas.translate(10.0f, 44.0f);
        canvas.drawText(":", (-measureText) / 2.0f, (float) (rect.height() / 2), textPaint);
        return createBitmap;
    }
}
