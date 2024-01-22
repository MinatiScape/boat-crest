package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;
/* loaded from: classes10.dex */
public interface EciesAeadHkdfParamsOrBuilder extends MessageLiteOrBuilder {
    EciesAeadDemParams getDemParams();

    EcPointFormat getEcPointFormat();

    int getEcPointFormatValue();

    EciesHkdfKemParams getKemParams();

    boolean hasDemParams();

    boolean hasKemParams();
}
