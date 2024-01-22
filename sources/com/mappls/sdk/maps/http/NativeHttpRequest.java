package com.mappls.sdk.maps.http;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.Mappls;
import com.mappls.sdk.maps.http.LocalRequestTask;
import java.util.concurrent.locks.ReentrantLock;
@Keep
/* loaded from: classes11.dex */
public class NativeHttpRequest implements HttpResponder {
    private final HttpRequest httpRequest;
    private final ReentrantLock lock;
    @Keep
    private long nativePtr;

    /* loaded from: classes11.dex */
    public class a implements LocalRequestTask.OnLocalRequestResponse {
        public a() {
        }

        @Override // com.mappls.sdk.maps.http.LocalRequestTask.OnLocalRequestResponse
        public void onResponse(@Nullable byte[] bArr) {
            if (bArr != null) {
                NativeHttpRequest.this.lock.lock();
                if (NativeHttpRequest.this.nativePtr != 0) {
                    NativeHttpRequest.this.nativeOnResponse(200, null, null, null, null, null, null, bArr);
                }
                NativeHttpRequest.this.lock.unlock();
            }
        }
    }

    @Keep
    private NativeHttpRequest(long j, String str, String str2, String str3, boolean z) {
        HttpRequest createHttpRequest = Mappls.getModuleProvider().createHttpRequest();
        this.httpRequest = createHttpRequest;
        this.lock = new ReentrantLock();
        this.nativePtr = j;
        if (str.startsWith("local://")) {
            executeLocalRequest(str);
        } else {
            createHttpRequest.executeRequest(this, j, str, str2, str3, z);
        }
    }

    private void executeLocalRequest(String str) {
        new LocalRequestTask(new a()).execute(str);
    }

    @Keep
    private native void nativeOnFailure(int i, String str);

    /* JADX INFO: Access modifiers changed from: private */
    @Keep
    public native void nativeOnResponse(int i, String str, String str2, String str3, String str4, String str5, String str6, byte[] bArr);

    public void cancel() {
        this.httpRequest.cancelRequest();
        this.lock.lock();
        this.nativePtr = 0L;
        this.lock.unlock();
    }

    @Override // com.mappls.sdk.maps.http.HttpResponder
    public void handleFailure(int i, String str) {
        this.lock.lock();
        if (this.nativePtr != 0) {
            nativeOnFailure(i, str);
        }
        this.lock.unlock();
    }

    @Override // com.mappls.sdk.maps.http.HttpResponder
    public void onResponse(int i, String str, String str2, String str3, String str4, String str5, String str6, byte[] bArr) {
        this.lock.lock();
        if (this.nativePtr != 0) {
            nativeOnResponse(i, str, str2, str3, str4, str5, str6, bArr);
        }
        this.lock.unlock();
    }
}
