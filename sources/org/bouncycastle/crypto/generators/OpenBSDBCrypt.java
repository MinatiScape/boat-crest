package org.bouncycastle.crypto.generators;

import com.jstyle.blesdk1860.constant.BleConst;
import java.io.ByteArrayOutputStream;
import java.util.HashSet;
import java.util.Set;
import kotlin.text.Typography;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
/* loaded from: classes12.dex */
public class OpenBSDBCrypt {

    /* renamed from: a  reason: collision with root package name */
    public static final byte[] f14739a = {46, 47, 65, 66, 67, 68, com.crrepa.c.a.E0, com.htsmart.wristband2.a.a.a.U0, 71, com.htsmart.wristband2.a.a.a.W0, 73, com.htsmart.wristband2.a.a.a.Y0, 75, com.htsmart.wristband2.a.a.a.a1, 77, com.htsmart.wristband2.a.a.a.c1, 79, com.htsmart.wristband2.a.a.a.d1, 81, 82, 83, 84, 85, 86, 87, com.htsmart.wristband2.a.a.a.o1, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, com.crrepa.c.a.Z0, com.htsmart.wristband2.a.a.a.J1, 113, 114, 115, 116, 117, com.htsmart.wristband2.a.a.a.R1, 119, 120, com.htsmart.wristband2.a.a.a.U1, com.htsmart.wristband2.a.a.a.V1, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57};
    public static final byte[] b = new byte[128];
    public static final Set<String> c;

    static {
        HashSet hashSet = new HashSet();
        c = hashSet;
        hashSet.add("2a");
        hashSet.add("2y");
        hashSet.add("2b");
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = b;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = -1;
            i2++;
        }
        while (true) {
            byte[] bArr2 = f14739a;
            if (i >= bArr2.length) {
                return;
            }
            b[bArr2[i]] = (byte) i;
            i++;
        }
    }

    public static String a(String str, byte[] bArr, byte[] bArr2, int i) {
        String num;
        if (!c.contains(str)) {
            throw new IllegalArgumentException("Version " + str + " is not accepted by this implementation.");
        }
        StringBuffer stringBuffer = new StringBuffer(60);
        stringBuffer.append(Typography.dollar);
        stringBuffer.append(str);
        stringBuffer.append(Typography.dollar);
        if (i < 10) {
            num = BleConst.GetDeviceTime + i;
        } else {
            num = Integer.toString(i);
        }
        stringBuffer.append(num);
        stringBuffer.append(Typography.dollar);
        stringBuffer.append(c(bArr2));
        stringBuffer.append(c(BCrypt.generate(bArr, bArr2, i)));
        return stringBuffer.toString();
    }

    public static byte[] b(String str) {
        char[] charArray = str.toCharArray();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(16);
        if (charArray.length != 22) {
            throw new DataLengthException("Invalid base64 salt length: " + charArray.length + " , 22 required.");
        }
        for (char c2 : charArray) {
            if (c2 > 'z' || c2 < '.' || (c2 > '9' && c2 < 'A')) {
                throw new IllegalArgumentException("Salt string contains invalid character: " + ((int) c2));
            }
        }
        char[] cArr = new char[24];
        System.arraycopy(charArray, 0, cArr, 0, charArray.length);
        for (int i = 0; i < 24; i += 4) {
            byte[] bArr = b;
            byte b2 = bArr[cArr[i]];
            byte b3 = bArr[cArr[i + 1]];
            byte b4 = bArr[cArr[i + 2]];
            byte b5 = bArr[cArr[i + 3]];
            byteArrayOutputStream.write((b2 << 2) | (b3 >> 4));
            byteArrayOutputStream.write((b3 << 4) | (b4 >> 2));
            byteArrayOutputStream.write(b5 | (b4 << 6));
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byte[] bArr2 = new byte[16];
        System.arraycopy(byteArray, 0, bArr2, 0, 16);
        return bArr2;
    }

    public static String c(byte[] bArr) {
        boolean z;
        if (bArr.length != 24 && bArr.length != 16) {
            throw new DataLengthException("Invalid length: " + bArr.length + ", 24 for key or 16 for salt expected");
        }
        if (bArr.length == 16) {
            byte[] bArr2 = new byte[18];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            bArr = bArr2;
            z = true;
        } else {
            bArr[bArr.length - 1] = 0;
            z = false;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int length = bArr.length;
        for (int i = 0; i < length; i += 3) {
            int i2 = bArr[i] & 255;
            int i3 = bArr[i + 1] & 255;
            int i4 = bArr[i + 2] & 255;
            byte[] bArr3 = f14739a;
            byteArrayOutputStream.write(bArr3[(i2 >>> 2) & 63]);
            byteArrayOutputStream.write(bArr3[((i2 << 4) | (i3 >>> 4)) & 63]);
            byteArrayOutputStream.write(bArr3[((i3 << 2) | (i4 >>> 6)) & 63]);
            byteArrayOutputStream.write(bArr3[i4 & 63]);
        }
        String fromByteArray = Strings.fromByteArray(byteArrayOutputStream.toByteArray());
        return fromByteArray.substring(0, z ? 22 : fromByteArray.length() - 1);
    }

    public static boolean checkPassword(String str, char[] cArr) {
        if (str.length() != 60) {
            throw new DataLengthException("Bcrypt String length: " + str.length() + ", 60 required.");
        } else if (str.charAt(0) == '$' && str.charAt(3) == '$' && str.charAt(6) == '$') {
            String substring = str.substring(1, 3);
            if (!c.contains(substring)) {
                throw new IllegalArgumentException("Bcrypt version '" + str.substring(1, 3) + "' is not supported by this implementation");
            }
            try {
                int parseInt = Integer.parseInt(str.substring(4, 6));
                if (parseInt >= 4 && parseInt <= 31) {
                    if (cArr != null) {
                        return str.equals(generate(substring, cArr, b(str.substring(str.lastIndexOf(36) + 1, str.length() - 31)), parseInt));
                    }
                    throw new IllegalArgumentException("Missing password.");
                }
                throw new IllegalArgumentException("Invalid cost factor: " + parseInt + ", 4 < cost < 31 expected.");
            } catch (NumberFormatException unused) {
                throw new IllegalArgumentException("Invalid cost factor: " + str.substring(4, 6));
            }
        } else {
            throw new IllegalArgumentException("Invalid Bcrypt String format.");
        }
    }

    public static String generate(String str, char[] cArr, byte[] bArr, int i) {
        if (!c.contains(str)) {
            throw new IllegalArgumentException("Version " + str + " is not accepted by this implementation.");
        } else if (cArr != null) {
            if (bArr != null) {
                if (bArr.length != 16) {
                    throw new DataLengthException("16 byte salt required: " + bArr.length);
                } else if (i < 4 || i > 31) {
                    throw new IllegalArgumentException("Invalid cost factor.");
                } else {
                    byte[] uTF8ByteArray = Strings.toUTF8ByteArray(cArr);
                    int length = uTF8ByteArray.length < 72 ? uTF8ByteArray.length + 1 : 72;
                    byte[] bArr2 = new byte[length];
                    if (length > uTF8ByteArray.length) {
                        length = uTF8ByteArray.length;
                    }
                    System.arraycopy(uTF8ByteArray, 0, bArr2, 0, length);
                    Arrays.fill(uTF8ByteArray, (byte) 0);
                    String a2 = a(str, bArr2, bArr, i);
                    Arrays.fill(bArr2, (byte) 0);
                    return a2;
                }
            }
            throw new IllegalArgumentException("Salt required.");
        } else {
            throw new IllegalArgumentException("Password required.");
        }
    }

    public static String generate(char[] cArr, byte[] bArr, int i) {
        return generate("2y", cArr, bArr, i);
    }
}
