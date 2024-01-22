package okhttp3.internal.http;

import okhttp3.Interceptor;
/* loaded from: classes12.dex */
public final class CallServerInterceptor implements Interceptor {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f14270a;

    public CallServerInterceptor(boolean z) {
        this.f14270a = z;
    }

    public final boolean a(int i) {
        if (i != 100) {
            if (!(102 <= i && i < 200)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00df A[Catch: IOException -> 0x019f, TryCatch #3 {IOException -> 0x019f, blocks: (B:40:0x00a8, B:42:0x00b1, B:43:0x00b5, B:45:0x00df, B:47:0x00e8, B:48:0x00eb, B:49:0x010f, B:53:0x011a, B:55:0x0139, B:57:0x0147, B:64:0x015d, B:70:0x0170, B:74:0x0193, B:75:0x019d, B:73:0x018b, B:67:0x0166, B:59:0x0152, B:54:0x0129), top: B:89:0x00a8 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0166 A[Catch: IOException -> 0x019f, TryCatch #3 {IOException -> 0x019f, blocks: (B:40:0x00a8, B:42:0x00b1, B:43:0x00b5, B:45:0x00df, B:47:0x00e8, B:48:0x00eb, B:49:0x010f, B:53:0x011a, B:55:0x0139, B:57:0x0147, B:64:0x015d, B:70:0x0170, B:74:0x0193, B:75:0x019d, B:73:0x018b, B:67:0x0166, B:59:0x0152, B:54:0x0129), top: B:89:0x00a8 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0170 A[Catch: IOException -> 0x019f, TryCatch #3 {IOException -> 0x019f, blocks: (B:40:0x00a8, B:42:0x00b1, B:43:0x00b5, B:45:0x00df, B:47:0x00e8, B:48:0x00eb, B:49:0x010f, B:53:0x011a, B:55:0x0139, B:57:0x0147, B:64:0x015d, B:70:0x0170, B:74:0x0193, B:75:0x019d, B:73:0x018b, B:67:0x0166, B:59:0x0152, B:54:0x0129), top: B:89:0x00a8 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00a8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r9v14, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v25 */
    /* JADX WARN: Type inference failed for: r9v26 */
    /* JADX WARN: Type inference failed for: r9v27 */
    @Override // okhttp3.Interceptor
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public okhttp3.Response intercept(@org.jetbrains.annotations.NotNull okhttp3.Interceptor.Chain r14) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 425
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http.CallServerInterceptor.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }
}
