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
public final class w2 implements t2 {

    /* renamed from: a  reason: collision with root package name */
    public int f13837a;
    public short b;
    public final ArrayList c = new ArrayList();
    public String d;

    @Override // com.touchgui.sdk.internal.t2
    public final void a(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        v2 v2Var = (v2) this.c.get(0);
        short s = v2Var.f13833a;
        short s2 = v2Var.b;
        Bitmap createBitmap = Bitmap.createBitmap(s, s2, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas();
        canvas.setBitmap(createBitmap);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new Rect(0, 0, s, s2), paint);
        canvas.setBitmap(null);
        ByteBuffer allocate = ByteBuffer.allocate(createBitmap.getByteCount());
        createBitmap.copyPixelsToBuffer(allocate);
        v2Var.e = allocate.array();
        v2 v2Var2 = (v2) this.c.get(1);
        short s3 = v2Var2.f13833a;
        short s4 = v2Var2.b;
        Bitmap createBitmap2 = Bitmap.createBitmap(s3, s4, Bitmap.Config.RGB_565);
        Canvas canvas2 = new Canvas();
        canvas2.setBitmap(createBitmap2);
        Paint paint2 = new Paint();
        paint2.setFilterBitmap(true);
        canvas2.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new Rect(0, 0, s3, s4), paint2);
        canvas2.setBitmap(null);
        ByteBuffer allocate2 = ByteBuffer.allocate(createBitmap2.getByteCount());
        createBitmap2.copyPixelsToBuffer(allocate2);
        v2Var2.e = allocate2.array();
    }

    @Override // com.touchgui.sdk.internal.t2
    public final void b(int i) {
    }

    @Override // com.touchgui.sdk.internal.t2
    public final boolean b(File file) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            dataOutputStream.write(s.a(this.f13837a));
            short s = this.b;
            dataOutputStream.write(new byte[]{(byte) (s & 255), (byte) ((s >> 8) & 255)});
            Iterator it = this.c.iterator();
            int i = 0;
            while (true) {
                int i2 = 4;
                if (!it.hasNext()) {
                    break;
                }
                v2 v2Var = (v2) it.next();
                dataOutputStream.write(v2Var.d);
                int i3 = v2Var.f13833a * v2Var.b;
                if (v2Var.c == 0) {
                    i2 = 2;
                }
                i += i3 * i2;
            }
            int length = ((this.d.length() + 3) / 4) * 4;
            short s2 = (short) length;
            dataOutputStream.write(new byte[]{(byte) (s2 & 255), (byte) ((s2 >> 8) & 255)});
            dataOutputStream.write(this.d.getBytes(StandardCharsets.US_ASCII));
            for (int length2 = this.d.length(); length2 < length; length2++) {
                dataOutputStream.writeByte(0);
            }
            dataOutputStream.write(s.a(i));
            Iterator it2 = this.c.iterator();
            while (it2.hasNext()) {
                dataOutputStream.write(((v2) it2.next()).e);
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
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            this.f13837a = Integer.reverseBytes(dataInputStream.readInt());
            this.b = Short.reverseBytes(dataInputStream.readShort());
            for (int i = 0; i < this.b; i++) {
                byte[] bArr = new byte[24];
                if (dataInputStream.read(bArr) != 24) {
                    break;
                }
                this.c.add(new v2(bArr));
            }
            int reverseBytes = Short.reverseBytes(dataInputStream.readShort());
            byte[] bArr2 = new byte[reverseBytes];
            if (dataInputStream.read(bArr2) != reverseBytes) {
                TGLogger.w("load error");
            }
            short s = 0;
            for (int i2 = 0; i2 < reverseBytes && bArr2[i2] != 0; i2++) {
                s = (short) (s + 1);
            }
            this.d = new String(bArr2, 0, (int) s);
            new u2().b(this.d);
            Integer.reverseBytes(dataInputStream.readInt());
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                v2 v2Var = (v2) it.next();
                int i3 = v2Var.f13833a * v2Var.b * (v2Var.c == 0 ? 2 : 4);
                byte[] bArr3 = new byte[i3];
                if (dataInputStream.read(bArr3) != i3) {
                    TGLogger.w("parser image error");
                }
                v2Var.e = bArr3;
            }
            dataInputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
