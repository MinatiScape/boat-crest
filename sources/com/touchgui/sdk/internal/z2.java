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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes12.dex */
public final class z2 implements t2 {

    /* renamed from: a  reason: collision with root package name */
    public int f13850a;
    public String h;
    public int i;
    public int b = 0;
    public int c = 0;
    public int d = 0;
    public int e = 0;
    public int f = 0;
    public final ArrayList g = new ArrayList();
    public ByteBuffer j = null;

    @Override // com.touchgui.sdk.internal.t2
    public final void a(Bitmap bitmap) {
        y2 y2Var;
        y2 y2Var2;
        if (bitmap == null) {
            return;
        }
        Iterator it = this.g.iterator();
        while (true) {
            if (!it.hasNext()) {
                y2Var = null;
                break;
            }
            y2Var = (y2) it.next();
            if (y2Var.f13845a == 0) {
                break;
            }
        }
        if (y2Var != null) {
            short s = y2Var.b;
            short s2 = y2Var.c;
            Bitmap createBitmap = Bitmap.createBitmap(s, s2, Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas();
            canvas.setBitmap(createBitmap);
            Paint paint = new Paint();
            paint.setFilterBitmap(true);
            canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new Rect(0, 0, s, s2), paint);
            canvas.setBitmap(null);
            ByteBuffer allocate = ByteBuffer.allocate(createBitmap.getByteCount());
            createBitmap.copyPixelsToBuffer(allocate);
            byte[] array = allocate.array();
            for (int i = 0; i < array.length / 2; i++) {
                int i2 = i * 2;
                byte b = array[i2];
                int i3 = i2 + 1;
                array[i2] = array[i3];
                array[i3] = b;
            }
            this.j.position(y2Var.e);
            this.j.put(array);
        }
        Iterator it2 = this.g.iterator();
        while (true) {
            if (!it2.hasNext()) {
                y2Var2 = null;
                break;
            }
            y2Var2 = (y2) it2.next();
            if (y2Var2.f13845a == 1) {
                break;
            }
        }
        if (y2Var2 != null) {
            short s3 = y2Var2.b;
            short s4 = y2Var2.c;
            Bitmap createBitmap2 = Bitmap.createBitmap(s3, s4, Bitmap.Config.RGB_565);
            Canvas canvas2 = new Canvas();
            canvas2.setBitmap(createBitmap2);
            Paint paint2 = new Paint();
            paint2.setFilterBitmap(true);
            canvas2.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new Rect(0, 0, s3, s4), paint2);
            canvas2.setBitmap(null);
            ByteBuffer allocate2 = ByteBuffer.allocate(createBitmap2.getByteCount());
            createBitmap2.copyPixelsToBuffer(allocate2);
            byte[] array2 = allocate2.array();
            for (int i4 = 0; i4 < array2.length / 2; i4++) {
                int i5 = i4 * 2;
                byte b2 = array2[i5];
                int i6 = i5 + 1;
                array2[i5] = array2[i6];
                array2[i6] = b2;
            }
            this.j.position(y2Var2.e);
            this.j.put(array2);
        }
    }

    @Override // com.touchgui.sdk.internal.t2
    public final void b(int i) {
    }

    @Override // com.touchgui.sdk.internal.t2
    public final boolean b(File file) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            dataOutputStream.write(s.a(this.f13850a));
            this.c = ((this.h.length() + 3) / 4) * 4;
            dataOutputStream.write(s.a(this.b));
            dataOutputStream.write(s.a(this.c));
            this.d = this.b + this.c;
            this.e = 0;
            for (int i = 0; i < this.g.size(); i++) {
                this.e += ((y2) this.g.get(i)).d.length;
            }
            dataOutputStream.write(s.a(this.d));
            dataOutputStream.write(s.a(this.e + 4));
            dataOutputStream.write(s.a(this.d + this.e + 4));
            dataOutputStream.write(s.a(this.f));
            dataOutputStream.write(this.h.getBytes(StandardCharsets.US_ASCII));
            for (int length = this.h.length(); length < this.c; length++) {
                dataOutputStream.writeByte(0);
            }
            dataOutputStream.write(s.a(this.i));
            for (int i2 = 0; i2 < this.g.size(); i2++) {
                dataOutputStream.write(((y2) this.g.get(i2)).d);
            }
            ByteBuffer byteBuffer = this.j;
            if (byteBuffer != null) {
                dataOutputStream.write(byteBuffer.array());
            }
            dataOutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.touchgui.sdk.internal.t2
    public final boolean a(File file) {
        int read;
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            this.f13850a = Integer.reverseBytes(dataInputStream.readInt());
            this.b = Integer.reverseBytes(dataInputStream.readInt());
            this.c = Integer.reverseBytes(dataInputStream.readInt());
            this.d = Integer.reverseBytes(dataInputStream.readInt());
            this.e = Integer.reverseBytes(dataInputStream.readInt());
            Integer.reverseBytes(dataInputStream.readInt());
            this.f = Integer.reverseBytes(dataInputStream.readInt());
            byte[] bArr = new byte[this.c];
            if (dataInputStream.read(bArr) != this.c) {
                TGLogger.w("load error");
            }
            short s = 0;
            for (int i = 0; i < this.c && bArr[i] != 0; i++) {
                s = (short) (s + 1);
            }
            this.h = new String(bArr, 0, (int) s);
            new x2().b(this.h);
            this.i = Integer.reverseBytes(dataInputStream.readInt());
            int i2 = this.e / 16;
            for (int i3 = 0; i3 < i2; i3++) {
                byte[] bArr2 = new byte[16];
                if (dataInputStream.read(bArr2) != 16) {
                    break;
                }
                this.g.add(new y2(bArr2));
            }
            this.j = ByteBuffer.allocate(this.f);
            byte[] bArr3 = new byte[256];
            for (int i4 = 0; i4 < (this.f / 256) + 1 && -1 != (read = dataInputStream.read(bArr3)); i4++) {
                this.j.put(bArr3, 0, read);
            }
            dataInputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
