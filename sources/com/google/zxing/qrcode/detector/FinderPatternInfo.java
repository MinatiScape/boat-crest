package com.google.zxing.qrcode.detector;
/* loaded from: classes11.dex */
public final class FinderPatternInfo {

    /* renamed from: a  reason: collision with root package name */
    public final FinderPattern f11881a;
    public final FinderPattern b;
    public final FinderPattern c;

    public FinderPatternInfo(FinderPattern[] finderPatternArr) {
        this.f11881a = finderPatternArr[0];
        this.b = finderPatternArr[1];
        this.c = finderPatternArr[2];
    }

    public FinderPattern getBottomLeft() {
        return this.f11881a;
    }

    public FinderPattern getTopLeft() {
        return this.b;
    }

    public FinderPattern getTopRight() {
        return this.c;
    }
}
