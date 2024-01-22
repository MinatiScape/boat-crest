package org.bouncycastle.pqc.crypto.xmss;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.TreeMap;
import org.bouncycastle.pqc.crypto.xmss.HashTreeAddress;
import org.bouncycastle.pqc.crypto.xmss.LTreeAddress;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
/* loaded from: classes13.dex */
public final class BDS implements Serializable {
    private static final long serialVersionUID = 1;
    private List<XMSSNode> authenticationPath;
    private int index;
    private int k;
    private Map<Integer, XMSSNode> keep;
    private Map<Integer, LinkedList<XMSSNode>> retain;
    private XMSSNode root;
    private Stack<XMSSNode> stack;
    private final List<a> treeHashInstances;
    private final int treeHeight;
    private boolean used;
    private transient c wotsPlus;

    private BDS(BDS bds, byte[] bArr, byte[] bArr2, OTSHashAddress oTSHashAddress) {
        this.wotsPlus = bds.wotsPlus;
        this.treeHeight = bds.treeHeight;
        this.k = bds.k;
        this.root = bds.root;
        this.authenticationPath = new ArrayList(bds.authenticationPath);
        this.retain = bds.retain;
        this.stack = (Stack) bds.stack.clone();
        this.treeHashInstances = bds.treeHashInstances;
        this.keep = new TreeMap(bds.keep);
        this.index = bds.index;
        nextAuthenticationPath(bArr, bArr2, oTSHashAddress);
        bds.used = true;
    }

    public BDS(XMSSParameters xMSSParameters, int i) {
        this(xMSSParameters.c(), xMSSParameters.getHeight(), xMSSParameters.b());
        this.index = i;
        this.used = true;
    }

    public BDS(XMSSParameters xMSSParameters, byte[] bArr, byte[] bArr2, OTSHashAddress oTSHashAddress) {
        this(xMSSParameters.c(), xMSSParameters.getHeight(), xMSSParameters.b());
        initialize(bArr, bArr2, oTSHashAddress);
    }

    public BDS(XMSSParameters xMSSParameters, byte[] bArr, byte[] bArr2, OTSHashAddress oTSHashAddress, int i) {
        this(xMSSParameters.c(), xMSSParameters.getHeight(), xMSSParameters.b());
        initialize(bArr, bArr2, oTSHashAddress);
        while (this.index < i) {
            nextAuthenticationPath(bArr, bArr2, oTSHashAddress);
            this.used = false;
        }
    }

    private BDS(c cVar, int i, int i2) {
        this.wotsPlus = cVar;
        this.treeHeight = i;
        this.k = i2;
        if (i2 <= i && i2 >= 2) {
            int i3 = i - i2;
            if (i3 % 2 == 0) {
                this.authenticationPath = new ArrayList();
                this.retain = new TreeMap();
                this.stack = new Stack<>();
                this.treeHashInstances = new ArrayList();
                for (int i4 = 0; i4 < i3; i4++) {
                    this.treeHashInstances.add(new a(i4));
                }
                this.keep = new TreeMap();
                this.index = 0;
                this.used = false;
                return;
            }
        }
        throw new IllegalArgumentException("illegal value for BDS parameter k");
    }

    private a getBDSTreeHashInstanceForUpdate() {
        a aVar = null;
        for (a aVar2 : this.treeHashInstances) {
            if (!aVar2.isFinished() && aVar2.isInitialized() && (aVar == null || aVar2.getHeight() < aVar.getHeight() || (aVar2.getHeight() == aVar.getHeight() && aVar2.getIndexLeaf() < aVar.getIndexLeaf()))) {
                aVar = aVar2;
            }
        }
        return aVar;
    }

    private void initialize(byte[] bArr, byte[] bArr2, OTSHashAddress oTSHashAddress) {
        Objects.requireNonNull(oTSHashAddress, "otsHashAddress == null");
        LTreeAddress lTreeAddress = (LTreeAddress) new LTreeAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).build();
        HashTreeAddress hashTreeAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).build();
        for (int i = 0; i < (1 << this.treeHeight); i++) {
            oTSHashAddress = (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withOTSAddress(i).withChainAddress(oTSHashAddress.a()).withHashAddress(oTSHashAddress.b()).withKeyAndMask(oTSHashAddress.getKeyAndMask()).build();
            c cVar = this.wotsPlus;
            cVar.j(cVar.i(bArr2, oTSHashAddress), bArr);
            f f = this.wotsPlus.f(oTSHashAddress);
            lTreeAddress = (LTreeAddress) new LTreeAddress.Builder().withLayerAddress(lTreeAddress.getLayerAddress()).withTreeAddress(lTreeAddress.getTreeAddress()).withLTreeAddress(i).withTreeHeight(lTreeAddress.b()).withTreeIndex(lTreeAddress.c()).withKeyAndMask(lTreeAddress.getKeyAndMask()).build();
            XMSSNode a2 = h.a(this.wotsPlus, f, lTreeAddress);
            hashTreeAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress()).withTreeAddress(hashTreeAddress.getTreeAddress()).withTreeIndex(i).withKeyAndMask(hashTreeAddress.getKeyAndMask()).build();
            while (!this.stack.isEmpty() && this.stack.peek().getHeight() == a2.getHeight()) {
                int floor = (int) Math.floor(i / (1 << a2.getHeight()));
                if (floor == 1) {
                    this.authenticationPath.add(a2.clone());
                }
                if (floor == 3 && a2.getHeight() < this.treeHeight - this.k) {
                    this.treeHashInstances.get(a2.getHeight()).setNode(a2.clone());
                }
                if (floor >= 3 && (floor & 1) == 1 && a2.getHeight() >= this.treeHeight - this.k && a2.getHeight() <= this.treeHeight - 2) {
                    if (this.retain.get(Integer.valueOf(a2.getHeight())) == null) {
                        LinkedList<XMSSNode> linkedList = new LinkedList<>();
                        linkedList.add(a2.clone());
                        this.retain.put(Integer.valueOf(a2.getHeight()), linkedList);
                    } else {
                        this.retain.get(Integer.valueOf(a2.getHeight())).add(a2.clone());
                    }
                }
                HashTreeAddress hashTreeAddress2 = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress()).withTreeAddress(hashTreeAddress.getTreeAddress()).withTreeHeight(hashTreeAddress.a()).withTreeIndex((hashTreeAddress.b() - 1) / 2).withKeyAndMask(hashTreeAddress.getKeyAndMask()).build();
                XMSSNode b = h.b(this.wotsPlus, this.stack.pop(), a2, hashTreeAddress2);
                XMSSNode xMSSNode = new XMSSNode(b.getHeight() + 1, b.getValue());
                hashTreeAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress2.getLayerAddress()).withTreeAddress(hashTreeAddress2.getTreeAddress()).withTreeHeight(hashTreeAddress2.a() + 1).withTreeIndex(hashTreeAddress2.b()).withKeyAndMask(hashTreeAddress2.getKeyAndMask()).build();
                a2 = xMSSNode;
            }
            this.stack.push(a2);
        }
        this.root = this.stack.pop();
    }

    private void nextAuthenticationPath(byte[] bArr, byte[] bArr2, OTSHashAddress oTSHashAddress) {
        List<XMSSNode> list;
        XMSSNode removeFirst;
        Objects.requireNonNull(oTSHashAddress, "otsHashAddress == null");
        if (this.used) {
            throw new IllegalStateException("index already used");
        }
        if (this.index > (1 << this.treeHeight) - 2) {
            throw new IllegalStateException("index out of bounds");
        }
        LTreeAddress lTreeAddress = (LTreeAddress) new LTreeAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).build();
        HashTreeAddress hashTreeAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).build();
        int calculateTau = XMSSUtil.calculateTau(this.index, this.treeHeight);
        if (((this.index >> (calculateTau + 1)) & 1) == 0 && calculateTau < this.treeHeight - 1) {
            this.keep.put(Integer.valueOf(calculateTau), this.authenticationPath.get(calculateTau).clone());
        }
        if (calculateTau == 0) {
            oTSHashAddress = (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withOTSAddress(this.index).withChainAddress(oTSHashAddress.a()).withHashAddress(oTSHashAddress.b()).withKeyAndMask(oTSHashAddress.getKeyAndMask()).build();
            c cVar = this.wotsPlus;
            cVar.j(cVar.i(bArr2, oTSHashAddress), bArr);
            this.authenticationPath.set(0, h.a(this.wotsPlus, this.wotsPlus.f(oTSHashAddress), (LTreeAddress) new LTreeAddress.Builder().withLayerAddress(lTreeAddress.getLayerAddress()).withTreeAddress(lTreeAddress.getTreeAddress()).withLTreeAddress(this.index).withTreeHeight(lTreeAddress.b()).withTreeIndex(lTreeAddress.c()).withKeyAndMask(lTreeAddress.getKeyAndMask()).build()));
        } else {
            int i = calculateTau - 1;
            XMSSNode b = h.b(this.wotsPlus, this.authenticationPath.get(i), this.keep.get(Integer.valueOf(i)), (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress()).withTreeAddress(hashTreeAddress.getTreeAddress()).withTreeHeight(i).withTreeIndex(this.index >> calculateTau).withKeyAndMask(hashTreeAddress.getKeyAndMask()).build());
            this.authenticationPath.set(calculateTau, new XMSSNode(b.getHeight() + 1, b.getValue()));
            this.keep.remove(Integer.valueOf(i));
            for (int i2 = 0; i2 < calculateTau; i2++) {
                if (i2 < this.treeHeight - this.k) {
                    list = this.authenticationPath;
                    removeFirst = this.treeHashInstances.get(i2).getTailNode();
                } else {
                    list = this.authenticationPath;
                    removeFirst = this.retain.get(Integer.valueOf(i2)).removeFirst();
                }
                list.set(i2, removeFirst);
            }
            int min = Math.min(calculateTau, this.treeHeight - this.k);
            for (int i3 = 0; i3 < min; i3++) {
                int i4 = this.index + 1 + ((1 << i3) * 3);
                if (i4 < (1 << this.treeHeight)) {
                    this.treeHashInstances.get(i3).initialize(i4);
                }
            }
        }
        for (int i5 = 0; i5 < ((this.treeHeight - this.k) >> 1); i5++) {
            a bDSTreeHashInstanceForUpdate = getBDSTreeHashInstanceForUpdate();
            if (bDSTreeHashInstanceForUpdate != null) {
                bDSTreeHashInstanceForUpdate.update(this.stack, this.wotsPlus, bArr, bArr2, oTSHashAddress);
            }
        }
        this.index++;
    }

    public List<XMSSNode> getAuthenticationPath() {
        ArrayList arrayList = new ArrayList();
        for (XMSSNode xMSSNode : this.authenticationPath) {
            arrayList.add(xMSSNode.clone());
        }
        return arrayList;
    }

    public int getIndex() {
        return this.index;
    }

    public BDS getNextState(byte[] bArr, byte[] bArr2, OTSHashAddress oTSHashAddress) {
        return new BDS(this, bArr, bArr2, oTSHashAddress);
    }

    public XMSSNode getRoot() {
        return this.root.clone();
    }

    public int getTreeHeight() {
        return this.treeHeight;
    }

    public boolean isUsed() {
        return this.used;
    }

    public void setXMSS(XMSSParameters xMSSParameters) {
        if (this.treeHeight != xMSSParameters.getHeight()) {
            throw new IllegalStateException("wrong height");
        }
        this.wotsPlus = xMSSParameters.c();
    }

    public void validate() {
        if (this.authenticationPath == null) {
            throw new IllegalStateException("authenticationPath == null");
        }
        if (this.retain == null) {
            throw new IllegalStateException("retain == null");
        }
        if (this.stack == null) {
            throw new IllegalStateException("stack == null");
        }
        if (this.treeHashInstances == null) {
            throw new IllegalStateException("treeHashInstances == null");
        }
        if (this.keep == null) {
            throw new IllegalStateException("keep == null");
        }
        if (!XMSSUtil.isIndexValid(this.treeHeight, this.index)) {
            throw new IllegalStateException("index in BDS state out of bounds");
        }
    }
}
