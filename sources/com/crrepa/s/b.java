package com.crrepa.s;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes9.dex */
public abstract class b extends a {
    public int b;
    public int c;

    public b() {
    }

    public b(int i, int i2) {
        this.b = i;
        this.c = i2;
    }

    public b(ImageView imageView) {
        int width = imageView.getWidth();
        int height = imageView.getHeight();
        if (width <= 0 || height <= 0) {
            throw new RuntimeException("Can not get the width or height of ImageView");
        }
        this.b = width;
        this.c = height;
    }

    public static final byte[] e(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[100];
        while (true) {
            int read = inputStream.read(bArr, 0, 100);
            if (read <= 0) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    @Override // com.crrepa.s.a
    /* renamed from: d */
    public Bitmap b(com.crrepa.r.c cVar) {
        return (this.b == 0 || this.c == 0) ? BitmapFactory.decodeStream(cVar.f7835a) : d(cVar.f7835a);
    }

    public final Bitmap d(InputStream inputStream) {
        byte[] bArr;
        try {
            bArr = e(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            bArr = null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        int floor = (int) Math.floor(i2 / this.b);
        int floor2 = (int) Math.floor(i3 / this.c);
        if (floor > 1 || floor2 > 1) {
            i = Math.max(floor, floor2);
        }
        options.inSampleSize = i;
        options.inJustDecodeBounds = false;
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        if (decodeByteArray != null) {
            return decodeByteArray;
        }
        throw new RuntimeException("Failed to decode stream.");
    }
}
