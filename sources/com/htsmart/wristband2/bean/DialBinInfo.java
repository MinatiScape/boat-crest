package com.htsmart.wristband2.bean;

import androidx.annotation.Nullable;
import com.htsmart.wristband2.dial.DialDrawer;
import java.util.List;
/* loaded from: classes11.dex */
public class DialBinInfo {

    /* renamed from: a  reason: collision with root package name */
    public int f11955a;
    public int b;
    public int c;
    public int d;
    public int e;
    public String f;
    public List<DialSubBinInfo> g;
    public int h;
    @Nullable
    public DialDrawer.Shape i;

    public int getBinVersion() {
        return this.d;
    }

    public int getDialNum() {
        return this.c;
    }

    public int getDialPosition() {
        return this.h;
    }

    public int getLcd() {
        return this.e;
    }

    @Nullable
    public DialDrawer.Shape getShape() {
        return this.i;
    }

    @Nullable
    public List<DialSubBinInfo> getSubBinList() {
        return this.g;
    }

    public String getToolVersion() {
        return this.f;
    }

    public int getUiNum() {
        return this.f11955a;
    }

    public int getUiSerial() {
        return this.b;
    }

    public void setBinVersion(int i) {
        this.d = i;
    }

    public void setDialNum(int i) {
        this.c = i;
    }

    public void setDialPosition(int i) {
        this.h = i;
    }

    public void setLcd(int i) {
        this.e = i;
    }

    public void setShape(@Nullable DialDrawer.Shape shape) {
        this.i = shape;
    }

    public void setSubBinList(List<DialSubBinInfo> list) {
        this.g = list;
    }

    public void setToolVersion(String str) {
        this.f = str;
    }

    public void setUiNum(int i) {
        this.f11955a = i;
    }

    public void setUiSerial(int i) {
        this.b = i;
    }
}
