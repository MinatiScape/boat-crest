package com.google.zxing.oned.rss.expanded;

import com.google.zxing.oned.rss.DataCharacter;
import com.google.zxing.oned.rss.FinderPattern;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public final DataCharacter f11835a;
    public final DataCharacter b;
    public final FinderPattern c;

    public b(DataCharacter dataCharacter, DataCharacter dataCharacter2, FinderPattern finderPattern) {
        this.f11835a = dataCharacter;
        this.b = dataCharacter2;
        this.c = finderPattern;
    }

    public FinderPattern a() {
        return this.c;
    }

    public DataCharacter b() {
        return this.f11835a;
    }

    public DataCharacter c() {
        return this.b;
    }

    public boolean d() {
        return this.b == null;
    }

    public boolean equals(Object obj) {
        if (obj instanceof b) {
            b bVar = (b) obj;
            return Objects.equals(this.f11835a, bVar.f11835a) && Objects.equals(this.b, bVar.b) && Objects.equals(this.c, bVar.c);
        }
        return false;
    }

    public int hashCode() {
        return (Objects.hashCode(this.f11835a) ^ Objects.hashCode(this.b)) ^ Objects.hashCode(this.c);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        sb.append(this.f11835a);
        sb.append(" , ");
        sb.append(this.b);
        sb.append(" : ");
        FinderPattern finderPattern = this.c;
        sb.append(finderPattern == null ? "null" : Integer.valueOf(finderPattern.getValue()));
        sb.append(" ]");
        return sb.toString();
    }
}
