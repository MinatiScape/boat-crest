package org.bouncycastle.crypto.params;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.util.Integers;
/* loaded from: classes13.dex */
public class SkeinParameters implements CipherParameters {
    public static final int PARAM_TYPE_CONFIG = 4;
    public static final int PARAM_TYPE_KEY = 0;
    public static final int PARAM_TYPE_KEY_IDENTIFIER = 16;
    public static final int PARAM_TYPE_MESSAGE = 48;
    public static final int PARAM_TYPE_NONCE = 20;
    public static final int PARAM_TYPE_OUTPUT = 63;
    public static final int PARAM_TYPE_PERSONALISATION = 8;
    public static final int PARAM_TYPE_PUBLIC_KEY = 12;
    public Hashtable h;

    /* loaded from: classes13.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Hashtable f14808a = new Hashtable();

        public Builder() {
        }

        public Builder(Hashtable hashtable) {
            Enumeration keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                Integer num = (Integer) keys.nextElement();
                this.f14808a.put(num, hashtable.get(num));
            }
        }

        public Builder(SkeinParameters skeinParameters) {
            Enumeration keys = skeinParameters.h.keys();
            while (keys.hasMoreElements()) {
                Integer num = (Integer) keys.nextElement();
                this.f14808a.put(num, skeinParameters.h.get(num));
            }
        }

        public SkeinParameters build() {
            return new SkeinParameters(this.f14808a);
        }

        public Builder set(int i, byte[] bArr) {
            if (bArr != null) {
                if (i == 0 || (i > 4 && i < 63 && i != 48)) {
                    if (i != 4) {
                        this.f14808a.put(Integers.valueOf(i), bArr);
                        return this;
                    }
                    throw new IllegalArgumentException("Parameter type 4 is reserved for internal use.");
                }
                throw new IllegalArgumentException("Parameter types must be in the range 0,5..47,49..62.");
            }
            throw new IllegalArgumentException("Parameter value must not be null.");
        }

        public Builder setKey(byte[] bArr) {
            return set(0, bArr);
        }

        public Builder setKeyIdentifier(byte[] bArr) {
            return set(16, bArr);
        }

        public Builder setNonce(byte[] bArr) {
            return set(20, bArr);
        }

        public Builder setPersonalisation(Date date, String str, String str2) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream, "UTF-8");
                outputStreamWriter.write(new SimpleDateFormat("YYYYMMDD").format(date));
                outputStreamWriter.write(HexStringBuilder.DEFAULT_SEPARATOR);
                outputStreamWriter.write(str);
                outputStreamWriter.write(HexStringBuilder.DEFAULT_SEPARATOR);
                outputStreamWriter.write(str2);
                outputStreamWriter.close();
                return set(8, byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                throw new IllegalStateException("Byte I/O failed: " + e);
            }
        }

        public Builder setPersonalisation(Date date, Locale locale, String str, String str2) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream, "UTF-8");
                outputStreamWriter.write(new SimpleDateFormat("YYYYMMDD", locale).format(date));
                outputStreamWriter.write(HexStringBuilder.DEFAULT_SEPARATOR);
                outputStreamWriter.write(str);
                outputStreamWriter.write(HexStringBuilder.DEFAULT_SEPARATOR);
                outputStreamWriter.write(str2);
                outputStreamWriter.close();
                return set(8, byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                throw new IllegalStateException("Byte I/O failed: " + e);
            }
        }

        public Builder setPersonalisation(byte[] bArr) {
            return set(8, bArr);
        }

        public Builder setPublicKey(byte[] bArr) {
            return set(12, bArr);
        }
    }

    public SkeinParameters() {
        this(new Hashtable());
    }

    public SkeinParameters(Hashtable hashtable) {
        this.h = hashtable;
    }

    public byte[] getKey() {
        return (byte[]) this.h.get(Integers.valueOf(0));
    }

    public byte[] getKeyIdentifier() {
        return (byte[]) this.h.get(Integers.valueOf(16));
    }

    public byte[] getNonce() {
        return (byte[]) this.h.get(Integers.valueOf(20));
    }

    public Hashtable getParameters() {
        return this.h;
    }

    public byte[] getPersonalisation() {
        return (byte[]) this.h.get(Integers.valueOf(8));
    }

    public byte[] getPublicKey() {
        return (byte[]) this.h.get(Integers.valueOf(12));
    }
}
