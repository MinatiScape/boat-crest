package org.bouncycastle.cms;

import org.bouncycastle.util.Selector;
/* loaded from: classes12.dex */
public abstract class RecipientId implements Selector {
    public static final int kek = 1;
    public static final int keyAgree = 2;
    public static final int keyTrans = 0;
    public static final int password = 3;
    public final int h;

    public RecipientId(int i) {
        this.h = i;
    }

    @Override // org.bouncycastle.util.Selector
    public abstract Object clone();

    public int getType() {
        return this.h;
    }
}
