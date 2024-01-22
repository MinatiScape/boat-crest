package com.google.protobuf;

import com.google.protobuf.Descriptors;
/* loaded from: classes11.dex */
public interface RpcChannel {
    void callMethod(Descriptors.MethodDescriptor methodDescriptor, RpcController rpcController, Message message, Message message2, RpcCallback<Message> rpcCallback);
}
