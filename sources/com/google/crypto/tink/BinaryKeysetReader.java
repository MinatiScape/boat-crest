package com.google.crypto.tink;

import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes10.dex */
public final class BinaryKeysetReader implements KeysetReader {

    /* renamed from: a  reason: collision with root package name */
    public final InputStream f10813a;
    public final boolean b;

    public BinaryKeysetReader(InputStream inputStream, boolean z) {
        this.f10813a = inputStream;
        this.b = z;
    }

    public static KeysetReader withBytes(byte[] bArr) {
        return new BinaryKeysetReader(new ByteArrayInputStream(bArr), true);
    }

    public static KeysetReader withFile(File file) throws IOException {
        return new BinaryKeysetReader(new FileInputStream(file), true);
    }

    public static KeysetReader withInputStream(InputStream inputStream) {
        return new BinaryKeysetReader(inputStream, false);
    }

    @Override // com.google.crypto.tink.KeysetReader
    public Keyset read() throws IOException {
        try {
            return Keyset.parseFrom(this.f10813a, ExtensionRegistryLite.getEmptyRegistry());
        } finally {
            if (this.b) {
                this.f10813a.close();
            }
        }
    }

    @Override // com.google.crypto.tink.KeysetReader
    public EncryptedKeyset readEncrypted() throws IOException {
        try {
            return EncryptedKeyset.parseFrom(this.f10813a, ExtensionRegistryLite.getEmptyRegistry());
        } finally {
            if (this.b) {
                this.f10813a.close();
            }
        }
    }
}
