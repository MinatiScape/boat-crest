package org.bouncycastle.est;

import com.mappls.sdk.services.api.alongroute.POICriteria;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import org.bouncycastle.est.b;
import org.bouncycastle.util.Properties;
import org.bouncycastle.util.Strings;
/* loaded from: classes13.dex */
public class ESTResponse {
    public static final Long l = 0L;

    /* renamed from: a  reason: collision with root package name */
    public final ESTRequest f14908a;
    public final b.a b;
    public final byte[] c;
    public final Source d;
    public String e;
    public int f;
    public String g;
    public InputStream h;
    public Long i;
    public long j = 0;
    public Long k;

    /* loaded from: classes13.dex */
    public class a extends InputStream {
        public a(ESTResponse eSTResponse) {
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            return -1;
        }
    }

    /* loaded from: classes13.dex */
    public class b extends InputStream {
        public final /* synthetic */ InputStream h;
        public final /* synthetic */ Long i;

        public b(InputStream inputStream, Long l) {
            this.h = inputStream;
            this.i = l;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (ESTResponse.this.i == null || ESTResponse.this.i.longValue() - 1 <= ESTResponse.this.j) {
                if (this.h.available() > 0) {
                    throw new IOException("Stream closed with extra content in pipe that exceeds content length.");
                }
                this.h.close();
                return;
            }
            throw new IOException("Stream closed before limit fully read, Read: " + ESTResponse.this.j + " ContentLength: " + ESTResponse.this.i);
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            int read = this.h.read();
            if (read > -1) {
                ESTResponse.b(ESTResponse.this);
                if (this.i != null && ESTResponse.this.j >= this.i.longValue()) {
                    throw new IOException("Absolute Read Limit exceeded: " + this.i);
                }
            }
            return read;
        }
    }

    /* loaded from: classes13.dex */
    public class c extends InputStream {
        public final InputStream h;

        public c(ESTResponse eSTResponse, InputStream inputStream) {
            this.h = inputStream;
        }

        public /* synthetic */ c(ESTResponse eSTResponse, InputStream inputStream, a aVar) {
            this(eSTResponse, inputStream);
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            return this.h.available();
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.h.close();
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            int read = this.h.read();
            System.out.print(String.valueOf((char) read));
            return read;
        }
    }

    public ESTResponse(ESTRequest eSTRequest, Source source) throws IOException {
        this.f14908a = eSTRequest;
        this.d = source;
        if (source instanceof LimitedSource) {
            this.k = ((LimitedSource) source).getAbsoluteReadLimit();
        }
        Set<String> asKeySet = Properties.asKeySet("org.bouncycastle.debug.est");
        this.h = (asKeySet.contains("input") || asKeySet.contains("all")) ? new c(this, source.getInputStream(), null) : source.getInputStream();
        this.b = new b.a();
        this.c = new byte[1024];
        d();
    }

    public static /* synthetic */ long b(ESTResponse eSTResponse) {
        long j = eSTResponse.j;
        eSTResponse.j = 1 + j;
        return j;
    }

    public void close() throws IOException {
        InputStream inputStream = this.h;
        if (inputStream != null) {
            inputStream.close();
        }
        this.d.close();
    }

    public final void d() throws IOException {
        this.e = readStringIncluding(' ');
        this.f = Integer.parseInt(readStringIncluding(' '));
        this.g = readStringIncluding('\n');
        while (true) {
            String readStringIncluding = readStringIncluding('\n');
            if (readStringIncluding.length() <= 0) {
                break;
            }
            int indexOf = readStringIncluding.indexOf(58);
            if (indexOf > -1) {
                this.b.add(Strings.toLowerCase(readStringIncluding.substring(0, indexOf).trim()), readStringIncluding.substring(indexOf + 1).trim());
            }
        }
        Long contentLength = getContentLength();
        this.i = contentLength;
        int i = this.f;
        if (i == 204 || i == 202) {
            if (contentLength == null) {
                this.i = 0L;
            } else if (i == 204 && contentLength.longValue() > 0) {
                throw new IOException("Got HTTP status 204 but Content-length > 0.");
            }
        }
        Long l2 = this.i;
        if (l2 == null) {
            throw new IOException("No Content-length header.");
        }
        if (l2.equals(l)) {
            this.h = new a(this);
        }
        Long l3 = this.i;
        if (l3 != null) {
            if (l3.longValue() < 0) {
                throw new IOException("Server returned negative content length: " + this.k);
            } else if (this.k != null && this.i.longValue() >= this.k.longValue()) {
                throw new IOException("Content length longer than absolute read limit: " + this.k + " Content-Length: " + this.i);
            }
        }
        this.h = wrapWithCounter(this.h, this.k);
        if (POICriteria.GEOMETRY_BASE64.equalsIgnoreCase(getHeader("content-transfer-encoding"))) {
            this.h = new org.bouncycastle.est.a(this.h, getContentLength());
        }
    }

    public Long getContentLength() {
        String firstValue = this.b.getFirstValue("Content-Length");
        if (firstValue == null) {
            return null;
        }
        try {
            return Long.valueOf(Long.parseLong(firstValue));
        } catch (RuntimeException e) {
            throw new RuntimeException("Content Length: '" + firstValue + "' invalid. " + e.getMessage());
        }
    }

    public String getHeader(String str) {
        return this.b.getFirstValue(str);
    }

    public b.a getHeaders() {
        return this.b;
    }

    public String getHttpVersion() {
        return this.e;
    }

    public InputStream getInputStream() {
        return this.h;
    }

    public ESTRequest getOriginalRequest() {
        return this.f14908a;
    }

    public Source getSource() {
        return this.d;
    }

    public int getStatusCode() {
        return this.f;
    }

    public String getStatusMessage() {
        return this.g;
    }

    public String readStringIncluding(char c2) throws IOException {
        int read;
        byte[] bArr;
        int i;
        int i2 = 0;
        while (true) {
            read = this.h.read();
            bArr = this.c;
            i = i2 + 1;
            bArr[i2] = (byte) read;
            if (i >= bArr.length) {
                throw new IOException("Server sent line > " + this.c.length);
            } else if (read == c2 || read <= -1) {
                break;
            } else {
                i2 = i;
            }
        }
        if (read != -1) {
            return new String(bArr, 0, i).trim();
        }
        throw new EOFException();
    }

    public InputStream wrapWithCounter(InputStream inputStream, Long l2) {
        return new b(inputStream, l2);
    }
}
