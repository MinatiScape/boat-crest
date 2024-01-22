package org.jose4j.zip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import org.jose4j.keys.KeyPersuasion;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.UncheckedJoseException;
/* loaded from: classes13.dex */
public class DeflateRFC1951CompressionAlgorithm implements CompressionAlgorithm {
    @Override // org.jose4j.zip.CompressionAlgorithm
    public byte[] compress(byte[] bArr) {
        Deflater deflater = new Deflater(8, true);
        try {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
                    deflaterOutputStream.write(bArr);
                    deflaterOutputStream.finish();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    deflaterOutputStream.close();
                    byteArrayOutputStream.close();
                    return byteArray;
                } catch (Throwable th) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (IOException e) {
                throw new UncheckedJoseException("Problem compressing data.", e);
            }
        } finally {
            deflater.end();
        }
    }

    @Override // org.jose4j.zip.CompressionAlgorithm
    public byte[] decompress(byte[] bArr) throws JoseException {
        Inflater inflater = new Inflater(true);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                InflaterInputStream inflaterInputStream = new InflaterInputStream(new ByteArrayInputStream(bArr), inflater);
                try {
                    byte[] bArr2 = new byte[256];
                    while (true) {
                        int read = inflaterInputStream.read(bArr2);
                        if (read != -1) {
                            byteArrayOutputStream.write(bArr2, 0, read);
                        } else {
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            inflaterInputStream.close();
                            return byteArray;
                        }
                    }
                } catch (Throwable th) {
                    try {
                        inflaterInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (IOException e) {
                throw new JoseException("Problem decompressing data.", e);
            }
        } finally {
            inflater.end();
        }
    }

    @Override // org.jose4j.jwa.Algorithm
    public String getAlgorithmIdentifier() {
        return CompressionAlgorithmIdentifiers.DEFLATE;
    }

    @Override // org.jose4j.jwa.Algorithm
    public String getJavaAlgorithm() {
        return null;
    }

    @Override // org.jose4j.jwa.Algorithm
    public KeyPersuasion getKeyPersuasion() {
        return KeyPersuasion.NONE;
    }

    @Override // org.jose4j.jwa.Algorithm
    public String getKeyType() {
        return null;
    }

    @Override // org.jose4j.jwa.Algorithm
    public boolean isAvailable() {
        return true;
    }
}
