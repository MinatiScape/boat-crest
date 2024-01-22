package org.bouncycastle.jce.provider;

import com.clevertap.android.sdk.Constants;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralSubtree;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Strings;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes13.dex */
public class PKIXNameConstraintValidator {

    /* renamed from: a  reason: collision with root package name */
    public Set f15094a = new HashSet();
    public Set b = new HashSet();
    public Set c = new HashSet();
    public Set d = new HashSet();
    public Set e = new HashSet();
    public Set f;
    public Set g;
    public Set h;
    public Set i;
    public Set j;

    public static byte[] D(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr.length; i++) {
            if ((bArr[i] & 65535) > (65535 & bArr2[i])) {
                return bArr;
            }
        }
        return bArr2;
    }

    public static byte[] E(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr.length; i++) {
            if ((bArr[i] & 65535) < (65535 & bArr2[i])) {
                return bArr;
            }
        }
        return bArr2;
    }

    public static byte[] G(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr3[i] = (byte) (bArr[i] | bArr2[i]);
        }
        return bArr3;
    }

    public static boolean Q(ASN1Sequence aSN1Sequence, ASN1Sequence aSN1Sequence2) {
        if (aSN1Sequence2.size() >= 1 && aSN1Sequence2.size() <= aSN1Sequence.size()) {
            for (int size = aSN1Sequence2.size() - 1; size >= 0; size--) {
                if (!aSN1Sequence2.getObjectAt(size).equals(aSN1Sequence.getObjectAt(size))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static int l(byte[] bArr, byte[] bArr2) {
        if (Arrays.areEqual(bArr, bArr2)) {
            return 0;
        }
        return Arrays.areEqual(D(bArr, bArr2), bArr) ? 1 : -1;
    }

    public static String o(String str) {
        String substring = str.substring(str.indexOf(58) + 1);
        if (substring.indexOf("//") != -1) {
            substring = substring.substring(substring.indexOf("//") + 2);
        }
        if (substring.lastIndexOf(58) != -1) {
            substring = substring.substring(0, substring.lastIndexOf(58));
        }
        String substring2 = substring.substring(substring.indexOf(58) + 1);
        String substring3 = substring2.substring(substring2.indexOf(64) + 1);
        return substring3.indexOf(47) != -1 ? substring3.substring(0, substring3.indexOf(47)) : substring3;
    }

    public final byte[] A(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        byte[] bArr3 = new byte[length * 2];
        System.arraycopy(bArr, 0, bArr3, 0, length);
        System.arraycopy(bArr2, 0, bArr3, length, length);
        return bArr3;
    }

    public final boolean B(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        if (length != bArr2.length / 2) {
            return false;
        }
        byte[] bArr3 = new byte[length];
        System.arraycopy(bArr2, length, bArr3, 0, length);
        byte[] bArr4 = new byte[length];
        byte[] bArr5 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr4[i] = (byte) (bArr2[i] & bArr3[i]);
            bArr5[i] = (byte) (bArr[i] & bArr3[i]);
        }
        return Arrays.areEqual(bArr4, bArr5);
    }

    public final boolean C(String str, String str2) {
        String o = o(str);
        return !str2.startsWith(".") ? o.equalsIgnoreCase(str2) : R(o, str2);
    }

    public final byte[][] F(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        int length = bArr.length;
        byte[] bArr5 = new byte[length];
        byte[] bArr6 = new byte[length];
        byte[] bArr7 = new byte[length];
        byte[] bArr8 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr5[i] = (byte) (bArr[i] & bArr2[i]);
            bArr6[i] = (byte) ((bArr[i] & bArr2[i]) | (~bArr2[i]));
            bArr7[i] = (byte) (bArr3[i] & bArr4[i]);
            bArr8[i] = (byte) ((bArr3[i] & bArr4[i]) | (~bArr4[i]));
        }
        return new byte[][]{bArr5, bArr6, bArr7, bArr8};
    }

    public final String H(byte[] bArr) {
        String str = "";
        for (int i = 0; i < bArr.length / 2; i++) {
            str = str + Integer.toString(bArr[i] & 255) + ".";
        }
        String str2 = str.substring(0, str.length() - 1) + MqttTopic.TOPIC_LEVEL_SEPARATOR;
        for (int length = bArr.length / 2; length < bArr.length; length++) {
            str2 = str2 + Integer.toString(bArr[length] & 255) + ".";
        }
        return str2.substring(0, str2.length() - 1);
    }

    public final String I(Set set) {
        Iterator it;
        String str = "[";
        while (set.iterator().hasNext()) {
            str = str + H((byte[]) it.next()) + Constants.SEPARATOR_COMMA;
        }
        if (str.length() > 1) {
            str = str.substring(0, str.length() - 1);
        }
        return str + "]";
    }

    public final Set J(Set set, ASN1Sequence aSN1Sequence) {
        if (set.isEmpty()) {
            if (aSN1Sequence == null) {
                return set;
            }
            set.add(aSN1Sequence);
            return set;
        }
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            ASN1Sequence aSN1Sequence2 = (ASN1Sequence) it.next();
            if (Q(aSN1Sequence, aSN1Sequence2)) {
                hashSet.add(aSN1Sequence2);
            } else {
                if (!Q(aSN1Sequence2, aSN1Sequence)) {
                    hashSet.add(aSN1Sequence2);
                }
                hashSet.add(aSN1Sequence);
            }
        }
        return hashSet;
    }

    public final Set K(Set set, String str) {
        if (set.isEmpty()) {
            if (str == null) {
                return set;
            }
            set.add(str);
            return set;
        }
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            L((String) it.next(), str, hashSet);
        }
        return hashSet;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0052, code lost:
        if (R(r6.substring(r5.indexOf(64) + 1), r5) != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x006c, code lost:
        if (R(r6, r5) != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0073, code lost:
        if (R(r6, r5) != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x008a, code lost:
        if (r6.substring(r5.indexOf(64) + 1).equalsIgnoreCase(r5) != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0097, code lost:
        if (R(r5, r6) != false) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x009e, code lost:
        if (r5.equalsIgnoreCase(r6) != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00a0, code lost:
        r7.add(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001f, code lost:
        if (r5.equalsIgnoreCase(r6) != false) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void L(java.lang.String r5, java.lang.String r6, java.util.Set r7) {
        /*
            r4 = this;
            r0 = 64
            int r1 = r5.indexOf(r0)
            java.lang.String r2 = "."
            r3 = -1
            if (r1 == r3) goto L38
            int r1 = r5.indexOf(r0)
            int r1 = r1 + 1
            java.lang.String r1 = r5.substring(r1)
            int r0 = r6.indexOf(r0)
            if (r0 == r3) goto L23
            boolean r0 = r5.equalsIgnoreCase(r6)
            if (r0 == 0) goto La4
            goto La0
        L23:
            boolean r0 = r6.startsWith(r2)
            if (r0 == 0) goto L31
            boolean r0 = r4.R(r1, r6)
            if (r0 == 0) goto La4
            goto L99
        L31:
            boolean r0 = r1.equalsIgnoreCase(r6)
            if (r0 == 0) goto La4
            goto L99
        L38:
            boolean r1 = r5.startsWith(r2)
            if (r1 == 0) goto L76
            int r1 = r6.indexOf(r0)
            if (r1 == r3) goto L55
            int r0 = r5.indexOf(r0)
            int r0 = r0 + 1
            java.lang.String r0 = r6.substring(r0)
            boolean r0 = r4.R(r0, r5)
            if (r0 == 0) goto La4
            goto La0
        L55:
            boolean r0 = r6.startsWith(r2)
            if (r0 == 0) goto L6f
            boolean r0 = r4.R(r5, r6)
            if (r0 != 0) goto La7
            boolean r0 = r5.equalsIgnoreCase(r6)
            if (r0 == 0) goto L68
            goto La7
        L68:
            boolean r0 = r4.R(r6, r5)
            if (r0 == 0) goto La4
            goto La0
        L6f:
            boolean r0 = r4.R(r6, r5)
            if (r0 == 0) goto La4
            goto La0
        L76:
            int r1 = r6.indexOf(r0)
            if (r1 == r3) goto L8d
            int r0 = r5.indexOf(r0)
            int r0 = r0 + 1
            java.lang.String r0 = r6.substring(r0)
            boolean r0 = r0.equalsIgnoreCase(r5)
            if (r0 == 0) goto La4
            goto La0
        L8d:
            boolean r0 = r6.startsWith(r2)
            if (r0 == 0) goto L9a
            boolean r0 = r4.R(r5, r6)
            if (r0 == 0) goto La4
        L99:
            goto La7
        L9a:
            boolean r0 = r5.equalsIgnoreCase(r6)
            if (r0 == 0) goto La4
        La0:
            r7.add(r5)
            goto Laa
        La4:
            r7.add(r5)
        La7:
            r7.add(r6)
        Laa:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jce.provider.PKIXNameConstraintValidator.L(java.lang.String, java.lang.String, java.util.Set):void");
    }

    public final Set M(Set set, byte[] bArr) {
        if (set.isEmpty()) {
            if (bArr == null) {
                return set;
            }
            set.add(bArr);
            return set;
        }
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            hashSet.addAll(N((byte[]) it.next(), bArr));
        }
        return hashSet;
    }

    public final Set N(byte[] bArr, byte[] bArr2) {
        HashSet hashSet = new HashSet();
        boolean areEqual = Arrays.areEqual(bArr, bArr2);
        hashSet.add(bArr);
        if (!areEqual) {
            hashSet.add(bArr2);
        }
        return hashSet;
    }

    public final Set O(Set set, String str) {
        if (set.isEmpty()) {
            if (str == null) {
                return set;
            }
            set.add(str);
            return set;
        }
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            P((String) it.next(), str, hashSet);
        }
        return hashSet;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0052, code lost:
        if (R(r6.substring(r5.indexOf(64) + 1), r5) != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x006c, code lost:
        if (R(r6, r5) != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0073, code lost:
        if (R(r6, r5) != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x008a, code lost:
        if (r6.substring(r5.indexOf(64) + 1).equalsIgnoreCase(r5) != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0097, code lost:
        if (R(r5, r6) != false) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x009e, code lost:
        if (r5.equalsIgnoreCase(r6) != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00a0, code lost:
        r7.add(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001f, code lost:
        if (r5.equalsIgnoreCase(r6) != false) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void P(java.lang.String r5, java.lang.String r6, java.util.Set r7) {
        /*
            r4 = this;
            r0 = 64
            int r1 = r5.indexOf(r0)
            java.lang.String r2 = "."
            r3 = -1
            if (r1 == r3) goto L38
            int r1 = r5.indexOf(r0)
            int r1 = r1 + 1
            java.lang.String r1 = r5.substring(r1)
            int r0 = r6.indexOf(r0)
            if (r0 == r3) goto L23
            boolean r0 = r5.equalsIgnoreCase(r6)
            if (r0 == 0) goto La4
            goto La0
        L23:
            boolean r0 = r6.startsWith(r2)
            if (r0 == 0) goto L31
            boolean r0 = r4.R(r1, r6)
            if (r0 == 0) goto La4
            goto L99
        L31:
            boolean r0 = r1.equalsIgnoreCase(r6)
            if (r0 == 0) goto La4
            goto L99
        L38:
            boolean r1 = r5.startsWith(r2)
            if (r1 == 0) goto L76
            int r1 = r6.indexOf(r0)
            if (r1 == r3) goto L55
            int r0 = r5.indexOf(r0)
            int r0 = r0 + 1
            java.lang.String r0 = r6.substring(r0)
            boolean r0 = r4.R(r0, r5)
            if (r0 == 0) goto La4
            goto La0
        L55:
            boolean r0 = r6.startsWith(r2)
            if (r0 == 0) goto L6f
            boolean r0 = r4.R(r5, r6)
            if (r0 != 0) goto La7
            boolean r0 = r5.equalsIgnoreCase(r6)
            if (r0 == 0) goto L68
            goto La7
        L68:
            boolean r0 = r4.R(r6, r5)
            if (r0 == 0) goto La4
            goto La0
        L6f:
            boolean r0 = r4.R(r6, r5)
            if (r0 == 0) goto La4
            goto La0
        L76:
            int r1 = r6.indexOf(r0)
            if (r1 == r3) goto L8d
            int r0 = r5.indexOf(r0)
            int r0 = r0 + 1
            java.lang.String r0 = r6.substring(r0)
            boolean r0 = r0.equalsIgnoreCase(r5)
            if (r0 == 0) goto La4
            goto La0
        L8d:
            boolean r0 = r6.startsWith(r2)
            if (r0 == 0) goto L9a
            boolean r0 = r4.R(r5, r6)
            if (r0 == 0) goto La4
        L99:
            goto La7
        L9a:
            boolean r0 = r5.equalsIgnoreCase(r6)
            if (r0 == 0) goto La4
        La0:
            r7.add(r5)
            goto Laa
        La4:
            r7.add(r5)
        La7:
            r7.add(r6)
        Laa:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jce.provider.PKIXNameConstraintValidator.P(java.lang.String, java.lang.String, java.util.Set):void");
    }

    public final boolean R(String str, String str2) {
        if (str2.startsWith(".")) {
            str2 = str2.substring(1);
        }
        String[] split = Strings.split(str2, '.');
        String[] split2 = Strings.split(str, '.');
        if (split2.length <= split.length) {
            return false;
        }
        int length = split2.length - split.length;
        for (int i = -1; i < split.length; i++) {
            if (i == -1) {
                if (split2[i + length].equals("")) {
                    return false;
                }
            } else if (!split[i].equalsIgnoreCase(split2[i + length])) {
                return false;
            }
        }
        return true;
    }

    public final void a(Set set, ASN1Sequence aSN1Sequence) throws PKIXNameConstraintValidatorException {
        if (set.isEmpty()) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (Q(aSN1Sequence, (ASN1Sequence) it.next())) {
                throw new PKIXNameConstraintValidatorException("Subject distinguished name is from an excluded subtree");
            }
        }
    }

    public void addExcludedSubtree(GeneralSubtree generalSubtree) {
        GeneralName base = generalSubtree.getBase();
        int tagNo = base.getTagNo();
        if (tagNo == 1) {
            this.c = K(this.c, q(base));
        } else if (tagNo == 2) {
            this.b = unionDNS(this.b, q(base));
        } else if (tagNo == 4) {
            this.f15094a = J(this.f15094a, (ASN1Sequence) base.getName().toASN1Primitive());
        } else if (tagNo == 6) {
            this.d = O(this.d, q(base));
        } else if (tagNo != 7) {
        } else {
            this.e = M(this.e, ASN1OctetString.getInstance(base.getName()).getOctets());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b(java.util.Set r3, java.lang.String r4) throws org.bouncycastle.jce.provider.PKIXNameConstraintValidatorException {
        /*
            r2 = this;
            boolean r0 = r3.isEmpty()
            if (r0 == 0) goto L7
            return
        L7:
            java.util.Iterator r3 = r3.iterator()
        Lb:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L2c
            java.lang.Object r0 = r3.next()
            java.lang.String r0 = (java.lang.String) r0
            boolean r1 = r2.R(r4, r0)
            if (r1 != 0) goto L24
            boolean r0 = r4.equalsIgnoreCase(r0)
            if (r0 != 0) goto L24
            goto Lb
        L24:
            org.bouncycastle.jce.provider.PKIXNameConstraintValidatorException r3 = new org.bouncycastle.jce.provider.PKIXNameConstraintValidatorException
            java.lang.String r4 = "DNS is from an excluded subtree."
            r3.<init>(r4)
            throw r3
        L2c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jce.provider.PKIXNameConstraintValidator.b(java.util.Set, java.lang.String):void");
    }

    public final void c(Set set, String str) throws PKIXNameConstraintValidatorException {
        if (set.isEmpty()) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (m(str, (String) it.next())) {
                throw new PKIXNameConstraintValidatorException("Email address is from an excluded subtree.");
            }
        }
    }

    public void checkExcluded(GeneralName generalName) throws PKIXNameConstraintValidatorException {
        int tagNo = generalName.getTagNo();
        if (tagNo == 1) {
            c(this.c, q(generalName));
        } else if (tagNo == 2) {
            b(this.b, DERIA5String.getInstance(generalName.getName()).getString());
        } else if (tagNo == 4) {
            checkExcludedDN(ASN1Sequence.getInstance(generalName.getName().toASN1Primitive()));
        } else if (tagNo == 6) {
            e(this.d, DERIA5String.getInstance(generalName.getName()).getString());
        } else if (tagNo != 7) {
        } else {
            d(this.e, ASN1OctetString.getInstance(generalName.getName()).getOctets());
        }
    }

    public void checkExcludedDN(ASN1Sequence aSN1Sequence) throws PKIXNameConstraintValidatorException {
        a(this.f15094a, aSN1Sequence);
    }

    public void checkPermitted(GeneralName generalName) throws PKIXNameConstraintValidatorException {
        int tagNo = generalName.getTagNo();
        if (tagNo == 1) {
            h(this.h, q(generalName));
        } else if (tagNo == 2) {
            g(this.g, DERIA5String.getInstance(generalName.getName()).getString());
        } else if (tagNo == 4) {
            checkPermittedDN(ASN1Sequence.getInstance(generalName.getName().toASN1Primitive()));
        } else if (tagNo == 6) {
            j(this.i, DERIA5String.getInstance(generalName.getName()).getString());
        } else if (tagNo != 7) {
        } else {
            i(this.j, ASN1OctetString.getInstance(generalName.getName()).getOctets());
        }
    }

    public void checkPermittedDN(ASN1Sequence aSN1Sequence) throws PKIXNameConstraintValidatorException {
        f(this.f, aSN1Sequence);
    }

    public final void d(Set set, byte[] bArr) throws PKIXNameConstraintValidatorException {
        if (set.isEmpty()) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (B(bArr, (byte[]) it.next())) {
                throw new PKIXNameConstraintValidatorException("IP is from an excluded subtree.");
            }
        }
    }

    public final void e(Set set, String str) throws PKIXNameConstraintValidatorException {
        if (set.isEmpty()) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (C(str, (String) it.next())) {
                throw new PKIXNameConstraintValidatorException("URI is from an excluded subtree.");
            }
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof PKIXNameConstraintValidator) {
            PKIXNameConstraintValidator pKIXNameConstraintValidator = (PKIXNameConstraintValidator) obj;
            return k(pKIXNameConstraintValidator.f15094a, this.f15094a) && k(pKIXNameConstraintValidator.b, this.b) && k(pKIXNameConstraintValidator.c, this.c) && k(pKIXNameConstraintValidator.e, this.e) && k(pKIXNameConstraintValidator.d, this.d) && k(pKIXNameConstraintValidator.f, this.f) && k(pKIXNameConstraintValidator.g, this.g) && k(pKIXNameConstraintValidator.h, this.h) && k(pKIXNameConstraintValidator.j, this.j) && k(pKIXNameConstraintValidator.i, this.i);
        }
        return false;
    }

    public final void f(Set set, ASN1Sequence aSN1Sequence) throws PKIXNameConstraintValidatorException {
        if (set == null) {
            return;
        }
        if (set.isEmpty() && aSN1Sequence.size() == 0) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (Q(aSN1Sequence, (ASN1Sequence) it.next())) {
                return;
            }
        }
        throw new PKIXNameConstraintValidatorException("Subject distinguished name is not from a permitted subtree");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x000d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void g(java.util.Set r4, java.lang.String r5) throws org.bouncycastle.jce.provider.PKIXNameConstraintValidatorException {
        /*
            r3 = this;
            if (r4 != 0) goto L3
            return
        L3:
            java.util.Iterator r0 = r4.iterator()
        L7:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L20
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            boolean r2 = r3.R(r5, r1)
            if (r2 != 0) goto L1f
            boolean r1 = r5.equalsIgnoreCase(r1)
            if (r1 == 0) goto L7
        L1f:
            return
        L20:
            int r5 = r5.length()
            if (r5 != 0) goto L2d
            int r4 = r4.size()
            if (r4 != 0) goto L2d
            return
        L2d:
            org.bouncycastle.jce.provider.PKIXNameConstraintValidatorException r4 = new org.bouncycastle.jce.provider.PKIXNameConstraintValidatorException
            java.lang.String r5 = "DNS is not from a permitted subtree."
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jce.provider.PKIXNameConstraintValidator.g(java.util.Set, java.lang.String):void");
    }

    public final void h(Set set, String str) throws PKIXNameConstraintValidatorException {
        if (set == null) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (m(str, (String) it.next())) {
                return;
            }
        }
        if (str.length() != 0 || set.size() != 0) {
            throw new PKIXNameConstraintValidatorException("Subject email address is not from a permitted subtree.");
        }
    }

    public int hashCode() {
        return r(this.f15094a) + r(this.b) + r(this.c) + r(this.e) + r(this.d) + r(this.f) + r(this.g) + r(this.h) + r(this.j) + r(this.i);
    }

    public final void i(Set set, byte[] bArr) throws PKIXNameConstraintValidatorException {
        if (set == null) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (B(bArr, (byte[]) it.next())) {
                return;
            }
        }
        if (bArr.length != 0 || set.size() != 0) {
            throw new PKIXNameConstraintValidatorException("IP is not from a permitted subtree.");
        }
    }

    public void intersectEmptyPermittedSubtree(int i) {
        if (i == 1) {
            this.h = new HashSet();
        } else if (i == 2) {
            this.g = new HashSet();
        } else if (i == 4) {
            this.f = new HashSet();
        } else if (i == 6) {
            this.i = new HashSet();
        } else if (i != 7) {
        } else {
            this.j = new HashSet();
        }
    }

    public void intersectPermittedSubtree(GeneralSubtree generalSubtree) {
        intersectPermittedSubtree(new GeneralSubtree[]{generalSubtree});
    }

    public void intersectPermittedSubtree(GeneralSubtree[] generalSubtreeArr) {
        HashMap hashMap = new HashMap();
        for (int i = 0; i != generalSubtreeArr.length; i++) {
            GeneralSubtree generalSubtree = generalSubtreeArr[i];
            Integer valueOf = Integers.valueOf(generalSubtree.getBase().getTagNo());
            if (hashMap.get(valueOf) == null) {
                hashMap.put(valueOf, new HashSet());
            }
            ((Set) hashMap.get(valueOf)).add(generalSubtree);
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            int intValue = ((Integer) entry.getKey()).intValue();
            if (intValue == 1) {
                this.h = u(this.h, (Set) entry.getValue());
            } else if (intValue == 2) {
                this.g = t(this.g, (Set) entry.getValue());
            } else if (intValue == 4) {
                this.f = s(this.f, (Set) entry.getValue());
            } else if (intValue == 6) {
                this.i = y(this.i, (Set) entry.getValue());
            } else if (intValue == 7) {
                this.j = w(this.j, (Set) entry.getValue());
            }
        }
    }

    public final void j(Set set, String str) throws PKIXNameConstraintValidatorException {
        if (set == null) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (C(str, (String) it.next())) {
                return;
            }
        }
        if (str.length() != 0 || set.size() != 0) {
            throw new PKIXNameConstraintValidatorException("URI is not from a permitted subtree.");
        }
    }

    public final boolean k(Collection collection, Collection collection2) {
        boolean z;
        if (collection == collection2) {
            return true;
        }
        if (collection == null || collection2 == null || collection.size() != collection2.size()) {
            return false;
        }
        for (Object obj : collection) {
            Iterator it = collection2.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (n(obj, it.next())) {
                        z = true;
                        continue;
                        break;
                    }
                } else {
                    z = false;
                    continue;
                    break;
                }
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    public final boolean m(String str, String str2) {
        String substring = str.substring(str.indexOf(64) + 1);
        if (str2.indexOf(64) != -1) {
            if (str.equalsIgnoreCase(str2) || substring.equalsIgnoreCase(str2.substring(1))) {
                return true;
            }
        } else if (str2.charAt(0) != '.') {
            if (substring.equalsIgnoreCase(str2)) {
                return true;
            }
        } else if (R(substring, str2)) {
            return true;
        }
        return false;
    }

    public final boolean n(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return ((obj instanceof byte[]) && (obj2 instanceof byte[])) ? Arrays.areEqual((byte[]) obj, (byte[]) obj2) : obj.equals(obj2);
    }

    public final byte[][] p(byte[] bArr, byte[] bArr2) {
        int length = bArr.length / 2;
        byte[] bArr3 = new byte[length];
        byte[] bArr4 = new byte[length];
        System.arraycopy(bArr, 0, bArr3, 0, length);
        System.arraycopy(bArr, length, bArr4, 0, length);
        byte[] bArr5 = new byte[length];
        byte[] bArr6 = new byte[length];
        System.arraycopy(bArr2, 0, bArr5, 0, length);
        System.arraycopy(bArr2, length, bArr6, 0, length);
        return new byte[][]{bArr3, bArr4, bArr5, bArr6};
    }

    public final String q(GeneralName generalName) {
        return DERIA5String.getInstance(generalName.getName()).getString();
    }

    public final int r(Collection collection) {
        int i = 0;
        if (collection == null) {
            return 0;
        }
        for (Object obj : collection) {
            i += obj instanceof byte[] ? Arrays.hashCode((byte[]) obj) : obj.hashCode();
        }
        return i;
    }

    public final Set s(Set set, Set set2) {
        HashSet hashSet = new HashSet();
        Iterator it = set2.iterator();
        while (it.hasNext()) {
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(((GeneralSubtree) it.next()).getBase().getName().toASN1Primitive());
            if (set != null) {
                Iterator it2 = set.iterator();
                while (it2.hasNext()) {
                    ASN1Sequence aSN1Sequence2 = (ASN1Sequence) it2.next();
                    if (Q(aSN1Sequence, aSN1Sequence2)) {
                        hashSet.add(aSN1Sequence);
                    } else if (Q(aSN1Sequence2, aSN1Sequence)) {
                        hashSet.add(aSN1Sequence2);
                    }
                }
            } else if (aSN1Sequence != null) {
                hashSet.add(aSN1Sequence);
            }
        }
        return hashSet;
    }

    public final Set t(Set set, Set set2) {
        HashSet hashSet = new HashSet();
        Iterator it = set2.iterator();
        while (it.hasNext()) {
            String q = q(((GeneralSubtree) it.next()).getBase());
            if (set != null) {
                Iterator it2 = set.iterator();
                while (it2.hasNext()) {
                    String str = (String) it2.next();
                    if (R(str, q)) {
                        hashSet.add(str);
                    } else if (R(q, str)) {
                        hashSet.add(q);
                    }
                }
            } else if (q != null) {
                hashSet.add(q);
            }
        }
        return hashSet;
    }

    public String toString() {
        String str = "permitted:\n";
        if (this.f != null) {
            str = (str + "DN:\n") + this.f.toString() + "\n";
        }
        if (this.g != null) {
            str = (str + "DNS:\n") + this.g.toString() + "\n";
        }
        if (this.h != null) {
            str = (str + "Email:\n") + this.h.toString() + "\n";
        }
        if (this.i != null) {
            str = (str + "URI:\n") + this.i.toString() + "\n";
        }
        if (this.j != null) {
            str = (str + "IP:\n") + I(this.j) + "\n";
        }
        String str2 = str + "excluded:\n";
        if (!this.f15094a.isEmpty()) {
            str2 = (str2 + "DN:\n") + this.f15094a.toString() + "\n";
        }
        if (!this.b.isEmpty()) {
            str2 = (str2 + "DNS:\n") + this.b.toString() + "\n";
        }
        if (!this.c.isEmpty()) {
            str2 = (str2 + "Email:\n") + this.c.toString() + "\n";
        }
        if (!this.d.isEmpty()) {
            str2 = (str2 + "URI:\n") + this.d.toString() + "\n";
        }
        if (this.e.isEmpty()) {
            return str2;
        }
        return (str2 + "IP:\n") + I(this.e) + "\n";
    }

    public final Set u(Set set, Set set2) {
        HashSet hashSet = new HashSet();
        Iterator it = set2.iterator();
        while (it.hasNext()) {
            String q = q(((GeneralSubtree) it.next()).getBase());
            if (set != null) {
                Iterator it2 = set.iterator();
                while (it2.hasNext()) {
                    v(q, (String) it2.next(), hashSet);
                }
            } else if (q != null) {
                hashSet.add(q);
            }
        }
        return hashSet;
    }

    public Set unionDNS(Set set, String str) {
        if (set.isEmpty()) {
            if (str == null) {
                return set;
            }
            set.add(str);
            return set;
        }
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (!R(str2, str)) {
                boolean R = R(str, str2);
                hashSet.add(str2);
                if (R) {
                }
            }
            hashSet.add(str);
        }
        return hashSet;
    }

    public final void v(String str, String str2, Set set) {
        if (str.indexOf(64) != -1) {
            String substring = str.substring(str.indexOf(64) + 1);
            if (str2.indexOf(64) != -1) {
                if (!str.equalsIgnoreCase(str2)) {
                    return;
                }
            } else if (str2.startsWith(".")) {
                if (!R(substring, str2)) {
                    return;
                }
            } else if (!substring.equalsIgnoreCase(str2)) {
                return;
            }
        } else if (str.startsWith(".")) {
            if (str2.indexOf(64) != -1) {
                if (!R(str2.substring(str.indexOf(64) + 1), str)) {
                    return;
                }
            } else if (str2.startsWith(".")) {
                if (!R(str, str2) && !str.equalsIgnoreCase(str2)) {
                    if (!R(str2, str)) {
                        return;
                    }
                }
            } else if (!R(str2, str)) {
                return;
            }
            set.add(str2);
            return;
        } else if (str2.indexOf(64) != -1) {
            if (!str2.substring(str2.indexOf(64) + 1).equalsIgnoreCase(str)) {
                return;
            }
            set.add(str2);
            return;
        } else if (str2.startsWith(".")) {
            if (!R(str, str2)) {
                return;
            }
        } else if (!str.equalsIgnoreCase(str2)) {
            return;
        }
        set.add(str);
    }

    public final Set w(Set set, Set set2) {
        HashSet hashSet = new HashSet();
        Iterator it = set2.iterator();
        while (it.hasNext()) {
            byte[] octets = ASN1OctetString.getInstance(((GeneralSubtree) it.next()).getBase().getName()).getOctets();
            if (set != null) {
                Iterator it2 = set.iterator();
                while (it2.hasNext()) {
                    hashSet.addAll(x((byte[]) it2.next(), octets));
                }
            } else if (octets != null) {
                hashSet.add(octets);
            }
        }
        return hashSet;
    }

    public final Set x(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return Collections.EMPTY_SET;
        }
        byte[][] p = p(bArr, bArr2);
        byte[] bArr3 = p[0];
        byte[] bArr4 = p[1];
        byte[] bArr5 = p[2];
        byte[] bArr6 = p[3];
        byte[][] F = F(bArr3, bArr4, bArr5, bArr6);
        return l(D(F[0], F[2]), E(F[1], F[3])) == 1 ? Collections.EMPTY_SET : Collections.singleton(A(G(F[0], F[2]), G(bArr4, bArr6)));
    }

    public final Set y(Set set, Set set2) {
        HashSet hashSet = new HashSet();
        Iterator it = set2.iterator();
        while (it.hasNext()) {
            String q = q(((GeneralSubtree) it.next()).getBase());
            if (set != null) {
                Iterator it2 = set.iterator();
                while (it2.hasNext()) {
                    z((String) it2.next(), q, hashSet);
                }
            } else if (q != null) {
                hashSet.add(q);
            }
        }
        return hashSet;
    }

    public final void z(String str, String str2, Set set) {
        if (str.indexOf(64) != -1) {
            String substring = str.substring(str.indexOf(64) + 1);
            if (str2.indexOf(64) != -1) {
                if (!str.equalsIgnoreCase(str2)) {
                    return;
                }
            } else if (str2.startsWith(".")) {
                if (!R(substring, str2)) {
                    return;
                }
            } else if (!substring.equalsIgnoreCase(str2)) {
                return;
            }
        } else if (str.startsWith(".")) {
            if (str2.indexOf(64) != -1) {
                if (!R(str2.substring(str.indexOf(64) + 1), str)) {
                    return;
                }
            } else if (str2.startsWith(".")) {
                if (!R(str, str2) && !str.equalsIgnoreCase(str2)) {
                    if (!R(str2, str)) {
                        return;
                    }
                }
            } else if (!R(str2, str)) {
                return;
            }
            set.add(str2);
            return;
        } else if (str2.indexOf(64) != -1) {
            if (!str2.substring(str2.indexOf(64) + 1).equalsIgnoreCase(str)) {
                return;
            }
            set.add(str2);
            return;
        } else if (str2.startsWith(".")) {
            if (!R(str, str2)) {
                return;
            }
        } else if (!str.equalsIgnoreCase(str2)) {
            return;
        }
        set.add(str);
    }
}
