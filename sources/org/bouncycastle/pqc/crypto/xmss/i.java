package org.bouncycastle.pqc.crypto.xmss;

import java.util.Objects;
import org.bouncycastle.pqc.crypto.xmss.HashTreeAddress;
import org.bouncycastle.pqc.crypto.xmss.LTreeAddress;
/* loaded from: classes13.dex */
public class i {
    public static XMSSNode a(c cVar, int i, byte[] bArr, XMSSReducedSignature xMSSReducedSignature, OTSHashAddress oTSHashAddress, int i2) {
        if (bArr.length == cVar.e().b()) {
            Objects.requireNonNull(xMSSReducedSignature, "signature == null");
            Objects.requireNonNull(oTSHashAddress, "otsHashAddress == null");
            HashTreeAddress hashTreeAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withTreeIndex(oTSHashAddress.c()).build();
            XMSSNode[] xMSSNodeArr = new XMSSNode[2];
            xMSSNodeArr[0] = h.a(cVar, cVar.g(bArr, xMSSReducedSignature.getWOTSPlusSignature(), oTSHashAddress), (LTreeAddress) new LTreeAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withLTreeAddress(oTSHashAddress.c()).build());
            for (int i3 = 0; i3 < i; i3++) {
                HashTreeAddress hashTreeAddress2 = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress()).withTreeAddress(hashTreeAddress.getTreeAddress()).withTreeHeight(i3).withTreeIndex(hashTreeAddress.b()).withKeyAndMask(hashTreeAddress.getKeyAndMask()).build();
                if (Math.floor(i2 / (1 << i3)) % 2.0d == 0.0d) {
                    hashTreeAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress2.getLayerAddress()).withTreeAddress(hashTreeAddress2.getTreeAddress()).withTreeHeight(hashTreeAddress2.a()).withTreeIndex(hashTreeAddress2.b() / 2).withKeyAndMask(hashTreeAddress2.getKeyAndMask()).build();
                    xMSSNodeArr[1] = h.b(cVar, xMSSNodeArr[0], xMSSReducedSignature.getAuthPath().get(i3), hashTreeAddress);
                    xMSSNodeArr[1] = new XMSSNode(xMSSNodeArr[1].getHeight() + 1, xMSSNodeArr[1].getValue());
                } else {
                    hashTreeAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress2.getLayerAddress()).withTreeAddress(hashTreeAddress2.getTreeAddress()).withTreeHeight(hashTreeAddress2.a()).withTreeIndex((hashTreeAddress2.b() - 1) / 2).withKeyAndMask(hashTreeAddress2.getKeyAndMask()).build();
                    xMSSNodeArr[1] = h.b(cVar, xMSSReducedSignature.getAuthPath().get(i3), xMSSNodeArr[0], hashTreeAddress);
                    xMSSNodeArr[1] = new XMSSNode(xMSSNodeArr[1].getHeight() + 1, xMSSNodeArr[1].getValue());
                }
                xMSSNodeArr[0] = xMSSNodeArr[1];
            }
            return xMSSNodeArr[0];
        }
        throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
    }
}
