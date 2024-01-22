package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.vision.zzaj;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public class Element implements Text {

    /* renamed from: a  reason: collision with root package name */
    public zzaj f10198a;

    public Element(zzaj zzajVar) {
        this.f10198a = zzajVar;
    }

    @Override // com.google.android.gms.vision.text.Text
    public Rect getBoundingBox() {
        return c.a(this);
    }

    @Override // com.google.android.gms.vision.text.Text
    public List<? extends Text> getComponents() {
        return new ArrayList();
    }

    @Override // com.google.android.gms.vision.text.Text
    public Point[] getCornerPoints() {
        return c.b(this.f10198a.zzej);
    }

    @Override // com.google.android.gms.vision.text.Text
    public String getLanguage() {
        return this.f10198a.zzed;
    }

    @Override // com.google.android.gms.vision.text.Text
    public String getValue() {
        return this.f10198a.zzem;
    }
}
