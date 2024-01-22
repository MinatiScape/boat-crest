package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zzac;
import com.google.android.gms.internal.vision.zzw;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public class TextBlock implements Text {

    /* renamed from: a  reason: collision with root package name */
    public zzac[] f10200a;
    public Point[] b;
    public List<Line> c;
    public String d;
    public Rect e;

    public TextBlock(SparseArray<zzac> sparseArray) {
        this.f10200a = new zzac[sparseArray.size()];
        int i = 0;
        while (true) {
            zzac[] zzacVarArr = this.f10200a;
            if (i >= zzacVarArr.length) {
                return;
            }
            zzacVarArr[i] = sparseArray.valueAt(i);
            i++;
        }
    }

    @Override // com.google.android.gms.vision.text.Text
    public Rect getBoundingBox() {
        if (this.e == null) {
            this.e = c.a(this);
        }
        return this.e;
    }

    @Override // com.google.android.gms.vision.text.Text
    public List<? extends Text> getComponents() {
        if (this.f10200a.length == 0) {
            return new ArrayList(0);
        }
        if (this.c == null) {
            this.c = new ArrayList(this.f10200a.length);
            for (zzac zzacVar : this.f10200a) {
                this.c.add(new Line(zzacVar));
            }
        }
        return this.c;
    }

    @Override // com.google.android.gms.vision.text.Text
    public Point[] getCornerPoints() {
        TextBlock textBlock;
        zzac[] zzacVarArr;
        TextBlock textBlock2 = this;
        if (textBlock2.b == null) {
            char c = 0;
            if (textBlock2.f10200a.length == 0) {
                textBlock2.b = new Point[0];
            } else {
                int i = Integer.MIN_VALUE;
                int i2 = 0;
                int i3 = Integer.MAX_VALUE;
                int i4 = Integer.MAX_VALUE;
                int i5 = Integer.MIN_VALUE;
                while (true) {
                    zzacVarArr = textBlock2.f10200a;
                    if (i2 >= zzacVarArr.length) {
                        break;
                    }
                    zzw zzwVar = zzacVarArr[i2].zzej;
                    zzw zzwVar2 = zzacVarArr[c].zzej;
                    double sin = Math.sin(Math.toRadians(zzwVar2.zzeh));
                    double cos = Math.cos(Math.toRadians(zzwVar2.zzeh));
                    Point[] pointArr = new Point[4];
                    pointArr[c] = new Point(zzwVar.left, zzwVar.top);
                    pointArr[c].offset(-zzwVar2.left, -zzwVar2.top);
                    int i6 = i5;
                    int i7 = (int) ((pointArr[c].x * cos) + (pointArr[c].y * sin));
                    int i8 = (int) (((-pointArr[0].x) * sin) + (pointArr[0].y * cos));
                    pointArr[0].x = i7;
                    pointArr[0].y = i8;
                    pointArr[1] = new Point(zzwVar.width + i7, i8);
                    pointArr[2] = new Point(zzwVar.width + i7, zzwVar.height + i8);
                    pointArr[3] = new Point(i7, i8 + zzwVar.height);
                    i5 = i6;
                    for (int i9 = 0; i9 < 4; i9++) {
                        Point point = pointArr[i9];
                        i3 = Math.min(i3, point.x);
                        i = Math.max(i, point.x);
                        i4 = Math.min(i4, point.y);
                        i5 = Math.max(i5, point.y);
                    }
                    i2++;
                    c = 0;
                    textBlock2 = this;
                }
                int i10 = i5;
                zzw zzwVar3 = zzacVarArr[c].zzej;
                int i11 = zzwVar3.left;
                int i12 = zzwVar3.top;
                double sin2 = Math.sin(Math.toRadians(zzwVar3.zzeh));
                double cos2 = Math.cos(Math.toRadians(zzwVar3.zzeh));
                Point[] pointArr2 = {new Point(i3, i4), new Point(i, i4), new Point(i, i10), new Point(i3, i10)};
                for (int i13 = 0; i13 < 4; i13++) {
                    pointArr2[i13].x = (int) ((pointArr2[i13].x * cos2) - (pointArr2[i13].y * sin2));
                    pointArr2[i13].y = (int) ((pointArr2[i13].x * sin2) + (pointArr2[i13].y * cos2));
                    pointArr2[i13].offset(i11, i12);
                }
                textBlock = this;
                textBlock.b = pointArr2;
                return textBlock.b;
            }
        }
        textBlock = textBlock2;
        return textBlock.b;
    }

    @Override // com.google.android.gms.vision.text.Text
    public String getLanguage() {
        zzac[] zzacVarArr;
        String str = this.d;
        if (str != null) {
            return str;
        }
        HashMap hashMap = new HashMap();
        for (zzac zzacVar : this.f10200a) {
            hashMap.put(zzacVar.zzed, Integer.valueOf((hashMap.containsKey(zzacVar.zzed) ? ((Integer) hashMap.get(zzacVar.zzed)).intValue() : 0) + 1));
        }
        String str2 = (String) ((Map.Entry) Collections.max(hashMap.entrySet(), new a(this))).getKey();
        this.d = str2;
        if (str2 == null || str2.isEmpty()) {
            this.d = "und";
        }
        return this.d;
    }

    @Override // com.google.android.gms.vision.text.Text
    public String getValue() {
        zzac[] zzacVarArr = this.f10200a;
        if (zzacVarArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(zzacVarArr[0].zzem);
        for (int i = 1; i < this.f10200a.length; i++) {
            sb.append("\n");
            sb.append(this.f10200a[i].zzem);
        }
        return sb.toString();
    }
}
