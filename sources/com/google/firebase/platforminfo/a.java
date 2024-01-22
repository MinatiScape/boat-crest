package com.google.firebase.platforminfo;

import java.util.Objects;
import javax.annotation.Nonnull;
/* loaded from: classes10.dex */
public final class a extends c {

    /* renamed from: a  reason: collision with root package name */
    public final String f11475a;
    public final String b;

    public a(String str, String str2) {
        Objects.requireNonNull(str, "Null libraryName");
        this.f11475a = str;
        Objects.requireNonNull(str2, "Null version");
        this.b = str2;
    }

    @Override // com.google.firebase.platforminfo.c
    @Nonnull
    public String b() {
        return this.f11475a;
    }

    @Override // com.google.firebase.platforminfo.c
    @Nonnull
    public String c() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof c) {
            c cVar = (c) obj;
            return this.f11475a.equals(cVar.b()) && this.b.equals(cVar.c());
        }
        return false;
    }

    public int hashCode() {
        return ((this.f11475a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode();
    }

    public String toString() {
        return "LibraryVersion{libraryName=" + this.f11475a + ", version=" + this.b + "}";
    }
}
