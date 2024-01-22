package com.jieli.jl_bt_ota.thread;

import android.os.Handler;
import android.os.Looper;
import com.jieli.jl_bt_ota.interfaces.IActionCallback;
import com.jieli.jl_bt_ota.model.base.BaseError;
/* loaded from: classes11.dex */
public class ReadFileThread extends Thread {

    /* renamed from: a  reason: collision with root package name */
    private final String f12356a;
    private final IActionCallback<byte[]> b;
    private final Handler c = new Handler(Looper.getMainLooper());

    public ReadFileThread(String str, IActionCallback<byte[]> iActionCallback) {
        this.f12356a = str;
        this.b = iActionCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(byte[] bArr) {
        IActionCallback<byte[]> iActionCallback = this.b;
        if (iActionCallback != null) {
            iActionCallback.onSuccess(bArr);
        }
    }

    private void b(final byte[] bArr) {
        this.c.post(new Runnable() { // from class: com.jieli.jl_bt_ota.thread.b
            @Override // java.lang.Runnable
            public final void run() {
                ReadFileThread.this.a(bArr);
            }
        });
    }

    public void finalize() throws Throwable {
        super.finalize();
        this.c.removeCallbacksAndMessages(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v14, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v22, types: [byte[], java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v26 */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v14, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r1v15, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x00a1 -> B:44:0x00a1). Please submit an issue!!! */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "mUpgradeFilePath : "
            r0.append(r1)
            java.lang.String r1 = r6.f12356a
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "ReadFileThread"
            com.jieli.jl_bt_ota.util.JL_Log.d(r1, r0)
            java.lang.String r0 = r6.f12356a
            boolean r0 = com.jieli.jl_bt_ota.util.FileUtil.checkFileExist(r0)
            if (r0 == 0) goto L96
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L59 java.io.FileNotFoundException -> L70
            java.lang.String r2 = r6.f12356a     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L59 java.io.FileNotFoundException -> L70
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L59 java.io.FileNotFoundException -> L70
            int r0 = r1.available()     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L4d java.io.FileNotFoundException -> L52
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L4d java.io.FileNotFoundException -> L52
            int r2 = r1.read(r0)     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L4d java.io.FileNotFoundException -> L52
            if (r2 < 0) goto L3e
            byte[] r3 = new byte[r2]     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L4d java.io.FileNotFoundException -> L52
            r4 = 0
            java.lang.System.arraycopy(r0, r4, r3, r4, r2)     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L4d java.io.FileNotFoundException -> L52
            r6.b(r3)     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L4d java.io.FileNotFoundException -> L52
            goto L47
        L3e:
            r0 = 20485(0x5005, float:2.8706E-41)
            com.jieli.jl_bt_ota.model.base.BaseError r0 = com.jieli.jl_bt_ota.model.OTAError.buildError(r0)     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L4d java.io.FileNotFoundException -> L52
            r6.b(r0)     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L4d java.io.FileNotFoundException -> L52
        L47:
            r1.close()     // Catch: java.io.IOException -> L86
            goto La1
        L4b:
            r0 = move-exception
            goto L8b
        L4d:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L5a
        L52:
            r0 = move-exception
            goto L74
        L54:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L8b
        L59:
            r1 = move-exception
        L5a:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L54
            r2 = 20486(0x5006, float:2.8707E-41)
            java.lang.String r1 = r1.getMessage()     // Catch: java.lang.Throwable -> L54
            com.jieli.jl_bt_ota.model.base.BaseError r1 = com.jieli.jl_bt_ota.model.OTAError.buildError(r2, r1)     // Catch: java.lang.Throwable -> L54
            r6.b(r1)     // Catch: java.lang.Throwable -> L54
            if (r0 == 0) goto La1
            r0.close()     // Catch: java.io.IOException -> L86
            goto La1
        L70:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L74:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L4b
            r0 = 20484(0x5004, float:2.8704E-41)
            com.jieli.jl_bt_ota.model.base.BaseError r0 = com.jieli.jl_bt_ota.model.OTAError.buildError(r0)     // Catch: java.lang.Throwable -> L4b
            r6.b(r0)     // Catch: java.lang.Throwable -> L4b
            if (r1 == 0) goto La1
            r1.close()     // Catch: java.io.IOException -> L86
            goto La1
        L86:
            r0 = move-exception
            r0.printStackTrace()
            goto La1
        L8b:
            if (r1 == 0) goto L95
            r1.close()     // Catch: java.io.IOException -> L91
            goto L95
        L91:
            r1 = move-exception
            r1.printStackTrace()
        L95:
            throw r0
        L96:
            r0 = 4097(0x1001, float:5.741E-42)
            java.lang.String r1 = "File path does not exist."
            com.jieli.jl_bt_ota.model.base.BaseError r0 = com.jieli.jl_bt_ota.model.OTAError.buildError(r0, r1)
            r6.b(r0)
        La1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jieli.jl_bt_ota.thread.ReadFileThread.run():void");
    }

    private void b(final BaseError baseError) {
        if (baseError == null) {
            return;
        }
        this.c.post(new Runnable() { // from class: com.jieli.jl_bt_ota.thread.a
            @Override // java.lang.Runnable
            public final void run() {
                ReadFileThread.this.a(baseError);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(BaseError baseError) {
        IActionCallback<byte[]> iActionCallback = this.b;
        if (iActionCallback != null) {
            iActionCallback.onError(baseError);
        }
    }
}
