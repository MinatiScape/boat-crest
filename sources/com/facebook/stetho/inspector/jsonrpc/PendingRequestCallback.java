package com.facebook.stetho.inspector.jsonrpc;

import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcResponse;
/* loaded from: classes9.dex */
public interface PendingRequestCallback {
    void onResponse(JsonRpcPeer jsonRpcPeer, JsonRpcResponse jsonRpcResponse);
}
