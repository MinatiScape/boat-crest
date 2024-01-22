package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;
/* loaded from: classes10.dex */
public interface Ed25519PrivateKeyOrBuilder extends MessageLiteOrBuilder {
    ByteString getKeyValue();

    Ed25519PublicKey getPublicKey();

    int getVersion();

    boolean hasPublicKey();
}
