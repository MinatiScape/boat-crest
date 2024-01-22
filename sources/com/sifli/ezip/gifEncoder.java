package com.sifli.ezip;

import android.graphics.Bitmap;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes12.dex */
public class gifEncoder {
    public byte[] colorTab;
    public int height;
    public byte[] indexedPixels;
    public OutputStream out;
    public byte[] pixels;
    public int width;
    public int delay = 0;
    public boolean[] usedEntry = new boolean[256];

    public boolean addFrame(Bitmap bitmap) {
        try {
            getImagePixels(bitmap);
            analyzePixels();
            writeGraphicCtrlExt();
            writeImageDesc();
            writePalette();
            writePixels();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public void analyzePixels() {
        byte[] bArr = this.pixels;
        int length = bArr.length;
        int i = length / 3;
        this.indexedPixels = new byte[i];
        NeuQuant neuQuant = new NeuQuant(bArr, length, 10);
        this.colorTab = neuQuant.process();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr2 = this.colorTab;
            if (i3 >= bArr2.length) {
                break;
            }
            byte b = bArr2[i3];
            int i4 = i3 + 2;
            bArr2[i3] = bArr2[i4];
            bArr2[i4] = b;
            this.usedEntry[i3 / 3] = false;
            i3 += 3;
        }
        int i5 = 0;
        while (i2 < i) {
            byte[] bArr3 = this.pixels;
            int i6 = i5 + 1;
            int i7 = i6 + 1;
            int map = neuQuant.map(bArr3[i5] & 255, bArr3[i6] & 255, bArr3[i7] & 255);
            this.usedEntry[map] = true;
            this.indexedPixels[i2] = (byte) map;
            i2++;
            i5 = i7 + 1;
        }
        this.pixels = null;
    }

    public boolean finish() {
        try {
            this.out.write(59);
            this.out.flush();
            this.out.close();
            this.out = null;
            this.pixels = null;
            this.indexedPixels = null;
            this.colorTab = null;
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public void getImagePixels(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        this.pixels = new byte[width * height * 3];
        for (int i = 0; i < height; i++) {
            int i2 = width * 3 * i;
            for (int i3 = 0; i3 < width; i3++) {
                int pixel = bitmap.getPixel(i3, i);
                int i4 = (i3 * 3) + i2;
                byte[] bArr = this.pixels;
                bArr[i4 + 0] = (byte) ((pixel & 255) >> 0);
                bArr[i4 + 1] = (byte) ((65280 & pixel) >> 8);
                bArr[i4 + 2] = (byte) ((pixel & 16711680) >> 16);
            }
        }
    }

    public void init(Bitmap bitmap) {
        this.width = bitmap.getWidth();
        this.height = bitmap.getHeight();
        getImagePixels(bitmap);
        analyzePixels();
    }

    public void setFrameRate(float f) {
        if (f != 0.0f) {
            this.delay = Math.round(100.0f / f);
        }
    }

    public boolean start(OutputStream outputStream) {
        if (outputStream == null) {
            return false;
        }
        this.out = outputStream;
        try {
            writeHeader();
            writeLSD();
            writePalette();
            writeGraphicCtrlExt();
            writeImageDesc();
            writePalette();
            writePixels();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public void writeGraphicCtrlExt() throws IOException {
        this.out.write(33);
        this.out.write(249);
        this.out.write(4);
        this.out.write(0);
        writeShort(this.delay);
        this.out.write(0);
        this.out.write(0);
    }

    public void writeHeader() throws IOException {
        writeString("GIF89a");
    }

    public void writeImageDesc() throws IOException {
        this.out.write(44);
        writeShort(0);
        writeShort(0);
        writeShort(this.width);
        writeShort(this.height);
        this.out.write(135);
    }

    public void writeLSD() throws IOException {
        writeShort(this.width);
        writeShort(this.height);
        this.out.write(247);
        this.out.write(0);
        this.out.write(0);
    }

    public void writePalette() throws IOException {
        OutputStream outputStream = this.out;
        byte[] bArr = this.colorTab;
        outputStream.write(bArr, 0, bArr.length);
        int length = 768 - this.colorTab.length;
        for (int i = 0; i < length; i++) {
            this.out.write(0);
        }
    }

    public void writePixels() throws IOException {
        new LZWEncoder(this.width, this.height, this.indexedPixels, 8).f(this.out);
    }

    public void writeShort(int i) throws IOException {
        this.out.write(i & 255);
        this.out.write((i >> 8) & 255);
    }

    public void writeString(String str) throws IOException {
        for (int i = 0; i < str.length(); i++) {
            this.out.write((byte) str.charAt(i));
        }
    }

    public boolean start(String str) {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str));
            this.out = bufferedOutputStream;
            return start(bufferedOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
