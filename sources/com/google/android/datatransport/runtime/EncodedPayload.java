package com.google.android.datatransport.runtime;

import androidx.annotation.NonNull;
import com.google.android.datatransport.Encoding;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes6.dex */
public final class EncodedPayload {

    /* renamed from: a  reason: collision with root package name */
    public final Encoding f8077a;
    public final byte[] b;

    public EncodedPayload(@NonNull Encoding encoding, @NonNull byte[] bArr) {
        Objects.requireNonNull(encoding, "encoding is null");
        Objects.requireNonNull(bArr, "bytes is null");
        this.f8077a = encoding;
        this.b = bArr;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EncodedPayload) {
            EncodedPayload encodedPayload = (EncodedPayload) obj;
            if (this.f8077a.equals(encodedPayload.f8077a)) {
                return Arrays.equals(this.b, encodedPayload.b);
            }
            return false;
        }
        return false;
    }

    public byte[] getBytes() {
        return this.b;
    }

    public Encoding getEncoding() {
        return this.f8077a;
    }

    public int hashCode() {
        return ((this.f8077a.hashCode() ^ 1000003) * 1000003) ^ Arrays.hashCode(this.b);
    }

    public String toString() {
        return "EncodedPayload{encoding=" + this.f8077a + ", bytes=[...]}";
    }
}
