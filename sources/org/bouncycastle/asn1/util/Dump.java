package org.bouncycastle.asn1.util;

import java.io.FileInputStream;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Primitive;
/* loaded from: classes12.dex */
public class Dump {
    public static void main(String[] strArr) throws Exception {
        ASN1InputStream aSN1InputStream = new ASN1InputStream(new FileInputStream(strArr[0]));
        while (true) {
            ASN1Primitive readObject = aSN1InputStream.readObject();
            if (readObject == null) {
                return;
            }
            System.out.println(ASN1Dump.dumpAsString(readObject));
        }
    }
}
