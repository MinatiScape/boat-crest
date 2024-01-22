package org.bouncycastle.pqc.crypto.xmss;

import java.io.Serializable;
import java.util.Objects;
import java.util.Stack;
import org.bouncycastle.pqc.crypto.xmss.HashTreeAddress;
import org.bouncycastle.pqc.crypto.xmss.LTreeAddress;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
/* loaded from: classes13.dex */
public class a implements Serializable {
    private static final long serialVersionUID = 1;
    private int height;
    private final int initialHeight;
    private int nextIndex;
    private XMSSNode tailNode;
    private boolean initialized = false;
    private boolean finished = false;

    public a(int i) {
        this.initialHeight = i;
    }

    public int getHeight() {
        if (!this.initialized || this.finished) {
            return Integer.MAX_VALUE;
        }
        return this.height;
    }

    public int getIndexLeaf() {
        return this.nextIndex;
    }

    public XMSSNode getTailNode() {
        return this.tailNode.clone();
    }

    public void initialize(int i) {
        this.tailNode = null;
        this.height = this.initialHeight;
        this.nextIndex = i;
        this.initialized = true;
        this.finished = false;
    }

    public boolean isFinished() {
        return this.finished;
    }

    public boolean isInitialized() {
        return this.initialized;
    }

    public void setNode(XMSSNode xMSSNode) {
        this.tailNode = xMSSNode;
        int height = xMSSNode.getHeight();
        this.height = height;
        if (height == this.initialHeight) {
            this.finished = true;
        }
    }

    public void update(Stack<XMSSNode> stack, c cVar, byte[] bArr, byte[] bArr2, OTSHashAddress oTSHashAddress) {
        Objects.requireNonNull(oTSHashAddress, "otsHashAddress == null");
        if (this.finished || !this.initialized) {
            throw new IllegalStateException("finished or not initialized");
        }
        OTSHashAddress oTSHashAddress2 = (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withOTSAddress(this.nextIndex).withChainAddress(oTSHashAddress.a()).withHashAddress(oTSHashAddress.b()).withKeyAndMask(oTSHashAddress.getKeyAndMask()).build();
        HashTreeAddress hashTreeAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(oTSHashAddress2.getLayerAddress()).withTreeAddress(oTSHashAddress2.getTreeAddress()).withTreeIndex(this.nextIndex).build();
        cVar.j(cVar.i(bArr2, oTSHashAddress2), bArr);
        XMSSNode a2 = h.a(cVar, cVar.f(oTSHashAddress2), (LTreeAddress) new LTreeAddress.Builder().withLayerAddress(oTSHashAddress2.getLayerAddress()).withTreeAddress(oTSHashAddress2.getTreeAddress()).withLTreeAddress(this.nextIndex).build());
        while (!stack.isEmpty() && stack.peek().getHeight() == a2.getHeight() && stack.peek().getHeight() != this.initialHeight) {
            HashTreeAddress hashTreeAddress2 = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress()).withTreeAddress(hashTreeAddress.getTreeAddress()).withTreeHeight(hashTreeAddress.a()).withTreeIndex((hashTreeAddress.b() - 1) / 2).withKeyAndMask(hashTreeAddress.getKeyAndMask()).build();
            XMSSNode b = h.b(cVar, stack.pop(), a2, hashTreeAddress2);
            XMSSNode xMSSNode = new XMSSNode(b.getHeight() + 1, b.getValue());
            hashTreeAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress2.getLayerAddress()).withTreeAddress(hashTreeAddress2.getTreeAddress()).withTreeHeight(hashTreeAddress2.a() + 1).withTreeIndex(hashTreeAddress2.b()).withKeyAndMask(hashTreeAddress2.getKeyAndMask()).build();
            a2 = xMSSNode;
        }
        XMSSNode xMSSNode2 = this.tailNode;
        if (xMSSNode2 == null) {
            this.tailNode = a2;
        } else if (xMSSNode2.getHeight() == a2.getHeight()) {
            HashTreeAddress hashTreeAddress3 = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress()).withTreeAddress(hashTreeAddress.getTreeAddress()).withTreeHeight(hashTreeAddress.a()).withTreeIndex((hashTreeAddress.b() - 1) / 2).withKeyAndMask(hashTreeAddress.getKeyAndMask()).build();
            a2 = new XMSSNode(this.tailNode.getHeight() + 1, h.b(cVar, this.tailNode, a2, hashTreeAddress3).getValue());
            this.tailNode = a2;
            HashTreeAddress hashTreeAddress4 = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress3.getLayerAddress()).withTreeAddress(hashTreeAddress3.getTreeAddress()).withTreeHeight(hashTreeAddress3.a() + 1).withTreeIndex(hashTreeAddress3.b()).withKeyAndMask(hashTreeAddress3.getKeyAndMask()).build();
        } else {
            stack.push(a2);
        }
        if (this.tailNode.getHeight() == this.initialHeight) {
            this.finished = true;
            return;
        }
        this.height = a2.getHeight();
        this.nextIndex++;
    }
}
