package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;
/* loaded from: classes10.dex */
public interface RsaSsaPssParamsOrBuilder extends MessageLiteOrBuilder {
    HashType getMgf1Hash();

    int getMgf1HashValue();

    int getSaltLength();

    HashType getSigHash();

    int getSigHashValue();
}
