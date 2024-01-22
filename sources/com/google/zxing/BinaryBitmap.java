package com.google.zxing;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
/* loaded from: classes11.dex */
public final class BinaryBitmap {

    /* renamed from: a  reason: collision with root package name */
    public final Binarizer f11762a;
    public BitMatrix b;

    public BinaryBitmap(Binarizer binarizer) {
        if (binarizer != null) {
            this.f11762a = binarizer;
            return;
        }
        throw new IllegalArgumentException("Binarizer must be non-null.");
    }

    public BinaryBitmap crop(int i, int i2, int i3, int i4) {
        return new BinaryBitmap(this.f11762a.createBinarizer(this.f11762a.getLuminanceSource().crop(i, i2, i3, i4)));
    }

    public BitMatrix getBlackMatrix() throws NotFoundException {
        if (this.b == null) {
            this.b = this.f11762a.getBlackMatrix();
        }
        return this.b;
    }

    public BitArray getBlackRow(int i, BitArray bitArray) throws NotFoundException {
        return this.f11762a.getBlackRow(i, bitArray);
    }

    public int getHeight() {
        return this.f11762a.getHeight();
    }

    public int getWidth() {
        return this.f11762a.getWidth();
    }

    public boolean isCropSupported() {
        return this.f11762a.getLuminanceSource().isCropSupported();
    }

    public boolean isRotateSupported() {
        return this.f11762a.getLuminanceSource().isRotateSupported();
    }

    public BinaryBitmap rotateCounterClockwise() {
        return new BinaryBitmap(this.f11762a.createBinarizer(this.f11762a.getLuminanceSource().rotateCounterClockwise()));
    }

    public BinaryBitmap rotateCounterClockwise45() {
        return new BinaryBitmap(this.f11762a.createBinarizer(this.f11762a.getLuminanceSource().rotateCounterClockwise45()));
    }

    public String toString() {
        try {
            return getBlackMatrix().toString();
        } catch (NotFoundException unused) {
            return "";
        }
    }
}
