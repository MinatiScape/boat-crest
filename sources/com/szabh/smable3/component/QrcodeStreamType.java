package com.szabh.smable3.component;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class QrcodeStreamType {
    @NotNull
    public static final QrcodeStreamType INSTANCE = new QrcodeStreamType();
    public static final int MY_CARD_CODE = 2;
    public static final int NUCLEIC_ACID_CODE = 0;
    public static final int RECEIPT_CODE = 1;

    private QrcodeStreamType() {
    }
}
