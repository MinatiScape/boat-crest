package com.google.crypto.tink;

import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.Keyset;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes10.dex */
public final class BinaryKeysetWriter implements KeysetWriter {

    /* renamed from: a  reason: collision with root package name */
    public final OutputStream f10814a;
    public final boolean b;

    public BinaryKeysetWriter(OutputStream outputStream, boolean z) {
        this.f10814a = outputStream;
        this.b = z;
    }

    public static KeysetWriter withFile(File file) throws IOException {
        return new BinaryKeysetWriter(new FileOutputStream(file), true);
    }

    public static KeysetWriter withOutputStream(OutputStream outputStream) {
        return new BinaryKeysetWriter(outputStream, false);
    }

    @Override // com.google.crypto.tink.KeysetWriter
    public void write(Keyset keyset) throws IOException {
        try {
            keyset.writeTo(this.f10814a);
        } finally {
            if (this.b) {
                this.f10814a.close();
            }
        }
    }

    @Override // com.google.crypto.tink.KeysetWriter
    public void write(EncryptedKeyset encryptedKeyset) throws IOException {
        try {
            encryptedKeyset.writeTo(this.f10814a);
        } finally {
            if (this.b) {
                this.f10814a.close();
            }
        }
    }
}
