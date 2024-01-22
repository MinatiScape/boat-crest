package androidx.security.crypto;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.StreamingAead;
import com.google.crypto.tink.integration.android.AndroidKeysetManager;
import com.google.crypto.tink.integration.android.AndroidKeystoreKmsClient;
import com.google.crypto.tink.streamingaead.AesGcmHkdfStreamingKeyManager;
import com.google.crypto.tink.streamingaead.StreamingAeadConfig;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
/* loaded from: classes.dex */
public final class EncryptedFile {

    /* renamed from: a  reason: collision with root package name */
    public final File f1676a;
    public final StreamingAead b;

    /* loaded from: classes.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public File f1677a;
        public final FileEncryptionScheme b;
        public final Context c;
        public final String d;
        public String e = "__androidx_security_crypto_encrypted_file_pref__";
        public String f = "__androidx_security_crypto_encrypted_file_keyset__";

        public Builder(@NonNull File file, @NonNull Context context, @NonNull String str, @NonNull FileEncryptionScheme fileEncryptionScheme) {
            this.f1677a = file;
            this.b = fileEncryptionScheme;
            this.c = context;
            this.d = str;
        }

        @NonNull
        public EncryptedFile build() throws GeneralSecurityException, IOException {
            StreamingAeadConfig.register();
            AndroidKeysetManager.Builder withSharedPref = new AndroidKeysetManager.Builder().withKeyTemplate(this.b.getKeyTemplate()).withSharedPref(this.c, this.f, this.e);
            return new EncryptedFile(this.f1677a, this.f, (StreamingAead) withSharedPref.withMasterKeyUri(AndroidKeystoreKmsClient.PREFIX + this.d).build().getKeysetHandle().getPrimitive(StreamingAead.class), this.c);
        }

        @NonNull
        public Builder setKeysetAlias(@NonNull String str) {
            this.f = str;
            return this;
        }

        @NonNull
        public Builder setKeysetPrefName(@NonNull String str) {
            this.e = str;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public enum FileEncryptionScheme {
        AES256_GCM_HKDF_4KB(AesGcmHkdfStreamingKeyManager.aes256GcmHkdf4KBTemplate());
        
        private final KeyTemplate mStreamingAeadKeyTemplate;

        FileEncryptionScheme(KeyTemplate keyTemplate) {
            this.mStreamingAeadKeyTemplate = keyTemplate;
        }

        public KeyTemplate getKeyTemplate() {
            return this.mStreamingAeadKeyTemplate;
        }
    }

    /* loaded from: classes.dex */
    public static final class a extends FileInputStream {
        public final InputStream h;

        public a(FileDescriptor fileDescriptor, InputStream inputStream) {
            super(fileDescriptor);
            this.h = inputStream;
        }

        @Override // java.io.FileInputStream, java.io.InputStream
        public int available() throws IOException {
            return this.h.available();
        }

        @Override // java.io.FileInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.h.close();
        }

        @Override // java.io.FileInputStream
        public FileChannel getChannel() {
            throw new UnsupportedOperationException("For encrypted files, please open the relevant FileInput/FileOutputStream.");
        }

        @Override // java.io.InputStream
        public synchronized void mark(int i) {
            this.h.mark(i);
        }

        @Override // java.io.InputStream
        public boolean markSupported() {
            return this.h.markSupported();
        }

        @Override // java.io.FileInputStream, java.io.InputStream
        public int read() throws IOException {
            return this.h.read();
        }

        @Override // java.io.InputStream
        public synchronized void reset() throws IOException {
            this.h.reset();
        }

        @Override // java.io.FileInputStream, java.io.InputStream
        public long skip(long j) throws IOException {
            return this.h.skip(j);
        }

        @Override // java.io.FileInputStream, java.io.InputStream
        public int read(@NonNull byte[] bArr) throws IOException {
            return this.h.read(bArr);
        }

        @Override // java.io.FileInputStream, java.io.InputStream
        public int read(@NonNull byte[] bArr, int i, int i2) throws IOException {
            return this.h.read(bArr, i, i2);
        }
    }

    /* loaded from: classes.dex */
    public static final class b extends FileOutputStream {
        public final OutputStream h;

        public b(FileDescriptor fileDescriptor, OutputStream outputStream) {
            super(fileDescriptor);
            this.h = outputStream;
        }

        @Override // java.io.FileOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.h.close();
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
            this.h.flush();
        }

        @Override // java.io.FileOutputStream
        @NonNull
        public FileChannel getChannel() {
            throw new UnsupportedOperationException("For encrypted files, please open the relevant FileInput/FileOutputStream.");
        }

        @Override // java.io.FileOutputStream, java.io.OutputStream
        public void write(@NonNull byte[] bArr) throws IOException {
            this.h.write(bArr);
        }

        @Override // java.io.FileOutputStream, java.io.OutputStream
        public void write(int i) throws IOException {
            this.h.write(i);
        }

        @Override // java.io.FileOutputStream, java.io.OutputStream
        public void write(@NonNull byte[] bArr, int i, int i2) throws IOException {
            this.h.write(bArr, i, i2);
        }
    }

    public EncryptedFile(@NonNull File file, @NonNull String str, @NonNull StreamingAead streamingAead, @NonNull Context context) {
        this.f1676a = file;
        this.b = streamingAead;
    }

    @NonNull
    public FileInputStream openFileInput() throws GeneralSecurityException, IOException {
        if (this.f1676a.exists()) {
            FileInputStream fileInputStream = new FileInputStream(this.f1676a);
            return new a(fileInputStream.getFD(), this.b.newDecryptingStream(fileInputStream, this.f1676a.getName().getBytes(StandardCharsets.UTF_8)));
        }
        throw new IOException("file doesn't exist: " + this.f1676a.getName());
    }

    @NonNull
    public FileOutputStream openFileOutput() throws GeneralSecurityException, IOException {
        if (!this.f1676a.exists()) {
            FileOutputStream fileOutputStream = new FileOutputStream(this.f1676a);
            return new b(fileOutputStream.getFD(), this.b.newEncryptingStream(fileOutputStream, this.f1676a.getName().getBytes(StandardCharsets.UTF_8)));
        }
        throw new IOException("output file already exists, please use a new file: " + this.f1676a.getName());
    }
}
