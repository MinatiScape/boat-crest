package org.apache.commons.codec.cli;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Locale;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.slf4j.Marker;
/* loaded from: classes12.dex */
public class Digest {

    /* renamed from: a  reason: collision with root package name */
    public final String f14336a;
    public final String[] b;
    public final String[] c;

    public Digest(String[] strArr) {
        if (strArr != null) {
            if (strArr.length != 0) {
                this.b = strArr;
                this.f14336a = strArr[0];
                if (strArr.length <= 1) {
                    this.c = null;
                    return;
                }
                String[] strArr2 = new String[strArr.length - 1];
                this.c = strArr2;
                System.arraycopy(strArr, 1, strArr2, 0, strArr2.length);
                return;
            }
            throw new IllegalArgumentException(String.format("Usage: java %s [algorithm] [FILE|DIRECTORY|string] ...", Digest.class.getName()));
        }
        throw new IllegalArgumentException("args");
    }

    public static void main(String[] strArr) throws IOException {
        new Digest(strArr).c();
    }

    public final void a(String str, byte[] bArr) {
        b(str, bArr, null);
    }

    public final void b(String str, byte[] bArr, String str2) {
        String str3;
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(Hex.encodeHexString(bArr));
        if (str2 != null) {
            str3 = "  " + str2;
        } else {
            str3 = "";
        }
        sb.append(str3);
        printStream.println(sb.toString());
    }

    public final void c() throws IOException {
        if (!this.f14336a.equalsIgnoreCase("ALL") && !this.f14336a.equals(Marker.ANY_MARKER)) {
            MessageDigest digest = DigestUtils.getDigest(this.f14336a, null);
            if (digest != null) {
                e("", digest);
                return;
            } else {
                e("", DigestUtils.getDigest(this.f14336a.toUpperCase(Locale.ROOT)));
                return;
            }
        }
        g(MessageDigestAlgorithms.values());
    }

    public final void d(String str, String str2) throws IOException {
        e(str, DigestUtils.getDigest(str2));
    }

    public final void e(String str, MessageDigest messageDigest) throws IOException {
        String[] strArr = this.c;
        if (strArr == null) {
            a(str, DigestUtils.digest(messageDigest, System.in));
            return;
        }
        for (String str2 : strArr) {
            File file = new File(str2);
            if (file.isFile()) {
                b(str, DigestUtils.digest(messageDigest, file), str2);
            } else if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    f(str, messageDigest, listFiles);
                }
            } else {
                a(str, DigestUtils.digest(messageDigest, str2.getBytes(Charset.defaultCharset())));
            }
        }
    }

    public final void f(String str, MessageDigest messageDigest, File[] fileArr) throws IOException {
        for (File file : fileArr) {
            if (file.isFile()) {
                b(str, DigestUtils.digest(messageDigest, file), file.getName());
            }
        }
    }

    public final void g(String[] strArr) throws IOException {
        for (String str : strArr) {
            if (DigestUtils.isAvailable(str)) {
                d(str + HexStringBuilder.DEFAULT_SEPARATOR, str);
            }
        }
    }

    public String toString() {
        return String.format("%s %s", super.toString(), Arrays.toString(this.b));
    }
}
