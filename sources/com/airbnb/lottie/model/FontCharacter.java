package com.airbnb.lottie.model;

import androidx.annotation.RestrictTo;
import com.airbnb.lottie.model.content.ShapeGroup;
import java.util.List;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class FontCharacter {

    /* renamed from: a  reason: collision with root package name */
    public final List<ShapeGroup> f2025a;
    public final char b;
    public final double c;
    public final String d;
    public final String e;

    public FontCharacter(List<ShapeGroup> list, char c, double d, double d2, String str, String str2) {
        this.f2025a = list;
        this.b = c;
        this.c = d2;
        this.d = str;
        this.e = str2;
    }

    public static int hashFor(char c, String str, String str2) {
        return (((c * 31) + str.hashCode()) * 31) + str2.hashCode();
    }

    public List<ShapeGroup> getShapes() {
        return this.f2025a;
    }

    public double getWidth() {
        return this.c;
    }

    public int hashCode() {
        return hashFor(this.b, this.e, this.d);
    }
}
