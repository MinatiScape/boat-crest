package org.bouncycastle.pqc.crypto.gmss;

import java.util.Enumeration;
import java.util.Vector;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class a {
    public static Vector[] a(Vector[] vectorArr) {
        if (vectorArr == null) {
            return null;
        }
        Vector[] vectorArr2 = new Vector[vectorArr.length];
        for (int i = 0; i != vectorArr.length; i++) {
            vectorArr2[i] = new Vector();
            Enumeration elements = vectorArr[i].elements();
            while (elements.hasMoreElements()) {
                vectorArr2[i].addElement(elements.nextElement());
            }
        }
        return vectorArr2;
    }

    public static Treehash[] b(Treehash[] treehashArr) {
        if (treehashArr == null) {
            return null;
        }
        Treehash[] treehashArr2 = new Treehash[treehashArr.length];
        System.arraycopy(treehashArr, 0, treehashArr2, 0, treehashArr.length);
        return treehashArr2;
    }

    public static byte[][] c(byte[][] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[][] bArr2 = new byte[bArr.length];
        for (int i = 0; i != bArr.length; i++) {
            bArr2[i] = Arrays.clone(bArr[i]);
        }
        return bArr2;
    }
}
