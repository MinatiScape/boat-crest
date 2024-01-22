package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.vision.zzac;
import com.google.android.gms.internal.vision.zzaj;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public class Line implements Text {

    /* renamed from: a  reason: collision with root package name */
    public zzac f10199a;
    public List<Element> b;

    public Line(zzac zzacVar) {
        this.f10199a = zzacVar;
    }

    public float getAngle() {
        return this.f10199a.zzej.zzeh;
    }

    @Override // com.google.android.gms.vision.text.Text
    public Rect getBoundingBox() {
        return c.a(this);
    }

    @Override // com.google.android.gms.vision.text.Text
    public List<? extends Text> getComponents() {
        if (this.f10199a.zzei.length == 0) {
            return new ArrayList(0);
        }
        if (this.b == null) {
            this.b = new ArrayList(this.f10199a.zzei.length);
            for (zzaj zzajVar : this.f10199a.zzei) {
                this.b.add(new Element(zzajVar));
            }
        }
        return this.b;
    }

    @Override // com.google.android.gms.vision.text.Text
    public Point[] getCornerPoints() {
        return c.b(this.f10199a.zzej);
    }

    @Override // com.google.android.gms.vision.text.Text
    public String getLanguage() {
        return this.f10199a.zzed;
    }

    @Override // com.google.android.gms.vision.text.Text
    public String getValue() {
        return this.f10199a.zzem;
    }

    public boolean isVertical() {
        return this.f10199a.zzeo;
    }
}
