package com.touchgui.sdk.internal;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.touchgui.sdk.TGLogger;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes12.dex */
public final class g3 implements t2 {

    /* renamed from: a  reason: collision with root package name */
    public int f13764a;
    public e3 b;
    public byte[] c;
    public final ArrayList d = new ArrayList();
    public final ArrayList e = new ArrayList();
    public int f = 0;

    @Override // com.touchgui.sdk.internal.t2
    public final void a(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        f3 f3Var = (f3) this.d.get(0);
        int i = f3Var.b;
        if (i == f3Var.c && this.f == 1) {
            bitmap = v3.a(bitmap, i / 2.0f);
        }
        int i2 = f3Var.b;
        int i3 = f3Var.c;
        Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas();
        canvas.setBitmap(createBitmap);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new Rect(0, 0, i2, i3), paint);
        canvas.setBitmap(null);
        ByteBuffer allocate = ByteBuffer.allocate(createBitmap.getByteCount());
        createBitmap.copyPixelsToBuffer(allocate);
        f3Var.f = allocate.array();
        if (this.d.size() > 1) {
            f3 f3Var2 = (f3) this.d.get(1);
            int i4 = f3Var2.b;
            int i5 = f3Var2.c;
            Bitmap createBitmap2 = Bitmap.createBitmap(i4, i5, Bitmap.Config.RGB_565);
            Canvas canvas2 = new Canvas();
            canvas2.setBitmap(createBitmap2);
            Paint paint2 = new Paint();
            paint2.setFilterBitmap(true);
            canvas2.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new Rect(0, 0, i4, i5), paint2);
            canvas2.setBitmap(null);
            ByteBuffer allocate2 = ByteBuffer.allocate(createBitmap2.getByteCount());
            createBitmap2.copyPixelsToBuffer(allocate2);
            f3Var2.f = allocate2.array();
        }
    }

    @Override // com.touchgui.sdk.internal.t2
    public final void b(int i) {
        byte[] bArr = this.b.k;
        bArr[4] = (byte) (((-16777216) & i) >> 24);
        bArr[5] = (byte) ((16711680 & i) >> 16);
        bArr[6] = (byte) ((65280 & i) >> 8);
        bArr[7] = (byte) (i & 255);
    }

    @Override // com.touchgui.sdk.internal.t2
    public final void c(int i) {
        e3 e3Var = this.b;
        int i2 = ((i & 15) << 3) | (e3Var.d << 28) | (e3Var.c << 7) | e3Var.f13757a;
        byte[] bArr = e3Var.k;
        bArr[0] = (byte) (i2 & 255);
        bArr[1] = (byte) ((i2 >> 8) & 255);
        bArr[2] = (byte) ((i2 >> 16) & 255);
        bArr[3] = (byte) ((i2 >> 24) & 255);
    }

    @Override // com.touchgui.sdk.internal.t2
    public final boolean b(File file) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            dataOutputStream.write(s.a(this.f13764a));
            dataOutputStream.write(this.b.k);
            dataOutputStream.write(this.c);
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                f3 f3Var = (f3) it.next();
                dataOutputStream.write(s.a(f3Var.e));
                dataOutputStream.write(f3Var.f);
            }
            Iterator it2 = this.e.iterator();
            while (it2.hasNext()) {
                d3 d3Var = (d3) it2.next();
                dataOutputStream.write(d3Var.b, 0, d3Var.f13754a);
            }
            dataOutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.touchgui.sdk.internal.t2
    public final int a() {
        return this.b.c;
    }

    @Override // com.touchgui.sdk.internal.t2
    public final boolean a(File file) {
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            this.f13764a = Integer.reverseBytes(dataInputStream.readInt());
            TGLogger.d("head:" + this.f13764a);
            byte[] bArr = new byte[148];
            dataInputStream.read(bArr);
            this.b = new e3(bArr);
            TGLogger.d("info:" + this.b);
            byte[] bArr2 = new byte[(this.b.i[0] - 4) + (-152)];
            this.c = bArr2;
            dataInputStream.read(bArr2);
            int i = this.b.d;
            TGLogger.d("imageSize=" + i);
            for (int i2 = 0; i2 < Math.min(i, 16); i2++) {
                if (this.b.j[i2] > 4) {
                    f3 f3Var = new f3(i2, Integer.reverseBytes(dataInputStream.readInt()));
                    byte[] bArr3 = new byte[this.b.j[i2] - 4];
                    dataInputStream.read(bArr3);
                    f3Var.f = bArr3;
                    TGLogger.d(f3Var.toString());
                    this.d.add(f3Var);
                }
            }
            while (true) {
                byte[] bArr4 = new byte[1024];
                int read = dataInputStream.read(bArr4);
                if (read <= 0) {
                    dataInputStream.close();
                    return true;
                }
                this.e.add(new d3(read, bArr4));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.touchgui.sdk.internal.t2
    public final void a(int i) {
        this.f = i;
    }
}
