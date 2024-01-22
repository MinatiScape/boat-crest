package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;
/* loaded from: classes10.dex */
public interface HkdfPrfKeyOrBuilder extends MessageLiteOrBuilder {
    ByteString getKeyValue();

    HkdfPrfParams getParams();

    int getVersion();

    boolean hasParams();
}
