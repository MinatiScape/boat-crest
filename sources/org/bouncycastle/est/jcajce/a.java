package org.bouncycastle.est.jcajce;

import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import org.bouncycastle.est.ESTClient;
import org.bouncycastle.est.ESTClientSourceProvider;
import org.bouncycastle.est.ESTException;
import org.bouncycastle.est.ESTRequest;
import org.bouncycastle.est.ESTRequestBuilder;
import org.bouncycastle.est.ESTResponse;
/* loaded from: classes13.dex */
public class a implements ESTClient {
    public static byte[] b;

    /* renamed from: a  reason: collision with root package name */
    public final ESTClientSourceProvider f14918a;

    /* renamed from: org.bouncycastle.est.jcajce.a$a  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public class C0906a extends OutputStream {
        public final OutputStream h;

        public C0906a(a aVar, OutputStream outputStream) {
            this.h = outputStream;
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            System.out.print(String.valueOf((char) i));
            this.h.write(i);
        }
    }

    static {
        Charset.forName("UTF-8");
        b = new byte[]{13, 10};
    }

    public a(ESTClientSourceProvider eSTClientSourceProvider) {
        this.f14918a = eSTClientSourceProvider;
    }

    public static void c(OutputStream outputStream, String str) throws IOException {
        outputStream.write(str.getBytes());
        outputStream.write(b);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0066 A[Catch: all -> 0x014f, TryCatch #0 {all -> 0x014f, blocks: (B:3:0x0003, B:5:0x001f, B:6:0x0027, B:8:0x0035, B:11:0x003e, B:13:0x004c, B:15:0x0066, B:17:0x0071, B:19:0x0087, B:20:0x008c, B:23:0x009a, B:24:0x00b4, B:26:0x00bd, B:27:0x00ed, B:29:0x00f3, B:30:0x0100, B:32:0x0103, B:33:0x0125, B:35:0x0139, B:40:0x0149, B:25:0x00b8, B:12:0x0043), top: B:46:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0087 A[Catch: all -> 0x014f, TryCatch #0 {all -> 0x014f, blocks: (B:3:0x0003, B:5:0x001f, B:6:0x0027, B:8:0x0035, B:11:0x003e, B:13:0x004c, B:15:0x0066, B:17:0x0071, B:19:0x0087, B:20:0x008c, B:23:0x009a, B:24:0x00b4, B:26:0x00bd, B:27:0x00ed, B:29:0x00f3, B:30:0x0100, B:32:0x0103, B:33:0x0125, B:35:0x0139, B:40:0x0149, B:25:0x00b8, B:12:0x0043), top: B:46:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x009a A[Catch: all -> 0x014f, TRY_ENTER, TryCatch #0 {all -> 0x014f, blocks: (B:3:0x0003, B:5:0x001f, B:6:0x0027, B:8:0x0035, B:11:0x003e, B:13:0x004c, B:15:0x0066, B:17:0x0071, B:19:0x0087, B:20:0x008c, B:23:0x009a, B:24:0x00b4, B:26:0x00bd, B:27:0x00ed, B:29:0x00f3, B:30:0x0100, B:32:0x0103, B:33:0x0125, B:35:0x0139, B:40:0x0149, B:25:0x00b8, B:12:0x0043), top: B:46:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b8 A[Catch: all -> 0x014f, TryCatch #0 {all -> 0x014f, blocks: (B:3:0x0003, B:5:0x001f, B:6:0x0027, B:8:0x0035, B:11:0x003e, B:13:0x004c, B:15:0x0066, B:17:0x0071, B:19:0x0087, B:20:0x008c, B:23:0x009a, B:24:0x00b4, B:26:0x00bd, B:27:0x00ed, B:29:0x00f3, B:30:0x0100, B:32:0x0103, B:33:0x0125, B:35:0x0139, B:40:0x0149, B:25:0x00b8, B:12:0x0043), top: B:46:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00f3 A[Catch: all -> 0x014f, TryCatch #0 {all -> 0x014f, blocks: (B:3:0x0003, B:5:0x001f, B:6:0x0027, B:8:0x0035, B:11:0x003e, B:13:0x004c, B:15:0x0066, B:17:0x0071, B:19:0x0087, B:20:0x008c, B:23:0x009a, B:24:0x00b4, B:26:0x00bd, B:27:0x00ed, B:29:0x00f3, B:30:0x0100, B:32:0x0103, B:33:0x0125, B:35:0x0139, B:40:0x0149, B:25:0x00b8, B:12:0x0043), top: B:46:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0139 A[Catch: all -> 0x014f, TRY_LEAVE, TryCatch #0 {all -> 0x014f, blocks: (B:3:0x0003, B:5:0x001f, B:6:0x0027, B:8:0x0035, B:11:0x003e, B:13:0x004c, B:15:0x0066, B:17:0x0071, B:19:0x0087, B:20:0x008c, B:23:0x009a, B:24:0x00b4, B:26:0x00bd, B:27:0x00ed, B:29:0x00f3, B:30:0x0100, B:32:0x0103, B:33:0x0125, B:35:0x0139, B:40:0x0149, B:25:0x00b8, B:12:0x0043), top: B:46:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0149 A[Catch: all -> 0x014f, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x014f, blocks: (B:3:0x0003, B:5:0x001f, B:6:0x0027, B:8:0x0035, B:11:0x003e, B:13:0x004c, B:15:0x0066, B:17:0x0071, B:19:0x0087, B:20:0x008c, B:23:0x009a, B:24:0x00b4, B:26:0x00bd, B:27:0x00ed, B:29:0x00f3, B:30:0x0100, B:32:0x0103, B:33:0x0125, B:35:0x0139, B:40:0x0149, B:25:0x00b8, B:12:0x0043), top: B:46:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.bouncycastle.est.ESTResponse a(org.bouncycastle.est.ESTRequest r10) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 342
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.est.jcajce.a.a(org.bouncycastle.est.ESTRequest):org.bouncycastle.est.ESTResponse");
    }

    public ESTRequest b(ESTResponse eSTResponse) throws IOException {
        ESTRequest eSTRequest;
        ESTRequestBuilder withURL;
        if (eSTResponse.getStatusCode() < 300 || eSTResponse.getStatusCode() > 399) {
            eSTRequest = null;
        } else {
            switch (eSTResponse.getStatusCode()) {
                case 301:
                case 302:
                case 303:
                case 306:
                case 307:
                    String header = eSTResponse.getHeader(HttpHeaders.LOCATION);
                    if (!"".equals(header)) {
                        ESTRequestBuilder eSTRequestBuilder = new ESTRequestBuilder(eSTResponse.getOriginalRequest());
                        if (header.startsWith("http")) {
                            withURL = eSTRequestBuilder.withURL(new URL(header));
                        } else {
                            URL url = eSTResponse.getOriginalRequest().getURL();
                            withURL = eSTRequestBuilder.withURL(new URL(url.getProtocol(), url.getHost(), url.getPort(), header));
                        }
                        eSTRequest = withURL.build();
                        break;
                    } else {
                        throw new ESTException("Redirect status type: " + eSTResponse.getStatusCode() + " but no location header");
                    }
                case 304:
                case 305:
                default:
                    throw new ESTException("Client does not handle http status code: " + eSTResponse.getStatusCode());
            }
        }
        if (eSTRequest != null) {
            eSTResponse.close();
        }
        return eSTRequest;
    }

    @Override // org.bouncycastle.est.ESTClient
    public ESTResponse doRequest(ESTRequest eSTRequest) throws IOException {
        ESTResponse a2;
        int i = 15;
        while (true) {
            a2 = a(eSTRequest);
            ESTRequest b2 = b(a2);
            if (b2 == null || i - 1 <= 0) {
                break;
            }
            eSTRequest = b2;
        }
        if (i != 0) {
            return a2;
        }
        throw new ESTException("Too many redirects..");
    }
}
