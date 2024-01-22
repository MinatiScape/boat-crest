package org.bouncycastle.pqc.crypto.xmss;

import java.util.Objects;
import org.bouncycastle.pqc.crypto.xmss.HashTreeAddress;
import org.bouncycastle.pqc.crypto.xmss.LTreeAddress;
/* loaded from: classes13.dex */
public class h {
    public static XMSSNode a(c cVar, f fVar, LTreeAddress lTreeAddress) {
        double d;
        Objects.requireNonNull(fVar, "publicKey == null");
        Objects.requireNonNull(lTreeAddress, "address == null");
        int c = cVar.e().c();
        byte[][] a2 = fVar.a();
        XMSSNode[] xMSSNodeArr = new XMSSNode[a2.length];
        for (int i = 0; i < a2.length; i++) {
            xMSSNodeArr[i] = new XMSSNode(0, a2[i]);
        }
        LTreeAddress.Builder withKeyAndMask = new LTreeAddress.Builder().withLayerAddress(lTreeAddress.getLayerAddress()).withTreeAddress(lTreeAddress.getTreeAddress()).withLTreeAddress(lTreeAddress.a()).withTreeHeight(0).withTreeIndex(lTreeAddress.c()).withKeyAndMask(lTreeAddress.getKeyAndMask());
        while (true) {
            LTreeAddress lTreeAddress2 = (LTreeAddress) withKeyAndMask.build();
            if (c <= 1) {
                return xMSSNodeArr[0];
            }
            int i2 = 0;
            while (true) {
                d = c / 2;
                if (i2 >= ((int) Math.floor(d))) {
                    break;
                }
                lTreeAddress2 = (LTreeAddress) new LTreeAddress.Builder().withLayerAddress(lTreeAddress2.getLayerAddress()).withTreeAddress(lTreeAddress2.getTreeAddress()).withLTreeAddress(lTreeAddress2.a()).withTreeHeight(lTreeAddress2.b()).withTreeIndex(i2).withKeyAndMask(lTreeAddress2.getKeyAndMask()).build();
                int i3 = i2 * 2;
                xMSSNodeArr[i2] = b(cVar, xMSSNodeArr[i3], xMSSNodeArr[i3 + 1], lTreeAddress2);
                i2++;
            }
            if (c % 2 == 1) {
                xMSSNodeArr[(int) Math.floor(d)] = xMSSNodeArr[c - 1];
            }
            c = (int) Math.ceil(c / 2.0d);
            withKeyAndMask = new LTreeAddress.Builder().withLayerAddress(lTreeAddress2.getLayerAddress()).withTreeAddress(lTreeAddress2.getTreeAddress()).withLTreeAddress(lTreeAddress2.a()).withTreeHeight(lTreeAddress2.b() + 1).withTreeIndex(lTreeAddress2.c()).withKeyAndMask(lTreeAddress2.getKeyAndMask());
        }
    }

    public static XMSSNode b(c cVar, XMSSNode xMSSNode, XMSSNode xMSSNode2, XMSSAddress xMSSAddress) {
        Objects.requireNonNull(xMSSNode, "left == null");
        Objects.requireNonNull(xMSSNode2, "right == null");
        if (xMSSNode.getHeight() == xMSSNode2.getHeight()) {
            Objects.requireNonNull(xMSSAddress, "address == null");
            byte[] h = cVar.h();
            if (xMSSAddress instanceof LTreeAddress) {
                LTreeAddress lTreeAddress = (LTreeAddress) xMSSAddress;
                xMSSAddress = (LTreeAddress) new LTreeAddress.Builder().withLayerAddress(lTreeAddress.getLayerAddress()).withTreeAddress(lTreeAddress.getTreeAddress()).withLTreeAddress(lTreeAddress.a()).withTreeHeight(lTreeAddress.b()).withTreeIndex(lTreeAddress.c()).withKeyAndMask(0).build();
            } else if (xMSSAddress instanceof HashTreeAddress) {
                HashTreeAddress hashTreeAddress = (HashTreeAddress) xMSSAddress;
                xMSSAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress()).withTreeAddress(hashTreeAddress.getTreeAddress()).withTreeHeight(hashTreeAddress.a()).withTreeIndex(hashTreeAddress.b()).withKeyAndMask(0).build();
            }
            byte[] d = cVar.d().d(h, xMSSAddress.toByteArray());
            if (xMSSAddress instanceof LTreeAddress) {
                LTreeAddress lTreeAddress2 = (LTreeAddress) xMSSAddress;
                xMSSAddress = (LTreeAddress) new LTreeAddress.Builder().withLayerAddress(lTreeAddress2.getLayerAddress()).withTreeAddress(lTreeAddress2.getTreeAddress()).withLTreeAddress(lTreeAddress2.a()).withTreeHeight(lTreeAddress2.b()).withTreeIndex(lTreeAddress2.c()).withKeyAndMask(1).build();
            } else if (xMSSAddress instanceof HashTreeAddress) {
                HashTreeAddress hashTreeAddress2 = (HashTreeAddress) xMSSAddress;
                xMSSAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress2.getLayerAddress()).withTreeAddress(hashTreeAddress2.getTreeAddress()).withTreeHeight(hashTreeAddress2.a()).withTreeIndex(hashTreeAddress2.b()).withKeyAndMask(1).build();
            }
            byte[] d2 = cVar.d().d(h, xMSSAddress.toByteArray());
            if (xMSSAddress instanceof LTreeAddress) {
                LTreeAddress lTreeAddress3 = (LTreeAddress) xMSSAddress;
                xMSSAddress = (LTreeAddress) new LTreeAddress.Builder().withLayerAddress(lTreeAddress3.getLayerAddress()).withTreeAddress(lTreeAddress3.getTreeAddress()).withLTreeAddress(lTreeAddress3.a()).withTreeHeight(lTreeAddress3.b()).withTreeIndex(lTreeAddress3.c()).withKeyAndMask(2).build();
            } else if (xMSSAddress instanceof HashTreeAddress) {
                HashTreeAddress hashTreeAddress3 = (HashTreeAddress) xMSSAddress;
                xMSSAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress3.getLayerAddress()).withTreeAddress(hashTreeAddress3.getTreeAddress()).withTreeHeight(hashTreeAddress3.a()).withTreeIndex(hashTreeAddress3.b()).withKeyAndMask(2).build();
            }
            byte[] d3 = cVar.d().d(h, xMSSAddress.toByteArray());
            int b = cVar.e().b();
            byte[] bArr = new byte[b * 2];
            for (int i = 0; i < b; i++) {
                bArr[i] = (byte) (xMSSNode.getValue()[i] ^ d2[i]);
            }
            for (int i2 = 0; i2 < b; i2++) {
                bArr[i2 + b] = (byte) (xMSSNode2.getValue()[i2] ^ d3[i2]);
            }
            return new XMSSNode(xMSSNode.getHeight(), cVar.d().b(d, bArr));
        }
        throw new IllegalStateException("height of both nodes must be equal");
    }
}
