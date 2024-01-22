package com.google.android.gms.internal.firebase_ml;

import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes7.dex */
public final class zzjj extends ByteArrayOutputStream {
    public int h;
    public final int i;
    public boolean j;
    public final Level k;
    public final Logger l;

    public zzjj(Logger logger, Level level, int i) {
        this.l = (Logger) zzml.checkNotNull(logger);
        this.k = (Level) zzml.checkNotNull(level);
        zzml.checkArgument(i >= 0);
        this.i = i;
    }

    public static void a(StringBuilder sb, int i) {
        if (i == 1) {
            sb.append("1 byte");
            return;
        }
        sb.append(NumberFormat.getInstance().format(i));
        sb.append(" bytes");
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() throws IOException {
        if (!this.j) {
            if (this.h != 0) {
                StringBuilder sb = new StringBuilder("Total: ");
                a(sb, this.h);
                int i = ((ByteArrayOutputStream) this).count;
                if (i != 0 && i < this.h) {
                    sb.append(" (logging first ");
                    a(sb, ((ByteArrayOutputStream) this).count);
                    sb.append(")");
                }
                this.l.logp(Level.CONFIG, "com.google.api.client.util.LoggingByteArrayOutputStream", Constants.KEY_HIDE_CLOSE, sb.toString());
                if (((ByteArrayOutputStream) this).count != 0) {
                    this.l.logp(this.k, "com.google.api.client.util.LoggingByteArrayOutputStream", Constants.KEY_HIDE_CLOSE, toString("UTF-8").replaceAll("[\\x00-\\x09\\x0B\\x0C\\x0E-\\x1F\\x7F]", HexStringBuilder.DEFAULT_SEPARATOR));
                }
            }
            this.j = true;
        }
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream
    public final synchronized void write(int i) {
        zzml.checkArgument(!this.j);
        this.h++;
        if (((ByteArrayOutputStream) this).count < this.i) {
            super.write(i);
        }
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream
    public final synchronized void write(byte[] bArr, int i, int i2) {
        zzml.checkArgument(!this.j);
        this.h += i2;
        int i3 = ((ByteArrayOutputStream) this).count;
        int i4 = this.i;
        if (i3 < i4) {
            int i5 = i3 + i2;
            if (i5 > i4) {
                i2 += i4 - i5;
            }
            super.write(bArr, i, i2);
        }
    }
}
