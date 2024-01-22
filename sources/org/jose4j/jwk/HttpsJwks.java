package org.jose4j.jwk;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.jose4j.http.Get;
import org.jose4j.http.SimpleGet;
import org.jose4j.http.SimpleResponse;
import org.jose4j.lang.ExceptionHelp;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes13.dex */
public class HttpsJwks {
    public static final Logger h = LoggerFactory.getLogger(HttpsJwks.class);

    /* renamed from: a  reason: collision with root package name */
    public final String f15525a;
    public volatile long b = 3600;
    public volatile SimpleGet c = new Get();
    public volatile long d = 0;
    public volatile b e = new b(Collections.emptyList(), 0);
    public final ReentrantLock f = new ReentrantLock();
    public long g = 300;

    /* loaded from: classes13.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final List<JsonWebKey> f15526a;
        public final long b;
        public final long c;

        public b(List<JsonWebKey> list, long j) {
            this.c = System.currentTimeMillis();
            this.f15526a = list;
            this.b = j;
        }
    }

    public HttpsJwks(String str) {
        this.f15525a = str;
    }

    public static long a(SimpleResponse simpleResponse) {
        return b(simpleResponse, System.currentTimeMillis());
    }

    public static long b(SimpleResponse simpleResponse, long j) {
        String lowerCase;
        long d = (d(simpleResponse) - j) / 1000;
        for (String str : e(simpleResponse, "cache-control")) {
            if (str == null) {
                lowerCase = "";
            } else {
                try {
                    lowerCase = str.toLowerCase();
                } catch (Exception unused) {
                }
            }
            int indexOf = lowerCase.indexOf("max-age");
            int indexOf2 = lowerCase.indexOf(44, indexOf);
            if (indexOf2 == -1) {
                indexOf2 = lowerCase.length();
            }
            String substring = lowerCase.substring(indexOf, indexOf2);
            d = Long.parseLong(substring.substring(substring.indexOf(61) + 1).trim());
        }
        return d;
    }

    public static long c(SimpleResponse simpleResponse, String str, long j) {
        Iterator<String> it = e(simpleResponse, str).iterator();
        while (it.hasNext()) {
            String next = it.next();
            try {
                if (!next.endsWith("GMT")) {
                    next = next + " GMT";
                }
                return Date.parse(next);
            } catch (Exception unused) {
            }
        }
        return j;
    }

    public static long d(SimpleResponse simpleResponse) {
        return c(simpleResponse, "expires", 0L);
    }

    public static List<String> e(SimpleResponse simpleResponse, String str) {
        List<String> headerValues = simpleResponse.getHeaderValues(str);
        return headerValues == null ? Collections.emptyList() : headerValues;
    }

    public List<JsonWebKey> getJsonWebKeys() throws JoseException, IOException {
        b bVar;
        long currentTimeMillis = System.currentTimeMillis();
        b bVar2 = this.e;
        if (bVar2.b > currentTimeMillis) {
            return bVar2.f15526a;
        }
        if (!this.f.tryLock()) {
            if (!bVar2.f15526a.isEmpty()) {
                return bVar2.f15526a;
            }
            this.f.lock();
        }
        try {
            try {
                refresh();
                bVar = this.e;
            } catch (Exception e) {
                if (this.d > 0 && !bVar2.f15526a.isEmpty()) {
                    b bVar3 = new b(bVar2.f15526a, currentTimeMillis + this.d);
                    this.e = bVar3;
                    h.info("Because of {} unable to refresh JWKS content from {} so will continue to use cached keys for more {} seconds until about {} -> {}", ExceptionHelp.toStringWithCauses(e), this.f15525a, Long.valueOf(this.d / 1000), new Date(bVar3.b), bVar3.f15526a);
                    this.f.unlock();
                    bVar = bVar3;
                } else {
                    throw e;
                }
            }
            return bVar.f15526a;
        } finally {
            this.f.unlock();
        }
    }

    public String getLocation() {
        return this.f15525a;
    }

    public void refresh() throws JoseException, IOException {
        this.f.lock();
        try {
            long currentTimeMillis = System.currentTimeMillis() - this.e.c;
            if (currentTimeMillis < this.g && !this.e.f15526a.isEmpty()) {
                h.debug("NOT refreshing/loading JWKS from {} because it just happened {} mills ago", this.f15525a, Long.valueOf(currentTimeMillis));
            } else {
                Logger logger = h;
                logger.debug("Refreshing/loading JWKS from {}", this.f15525a);
                SimpleResponse simpleResponse = this.c.get(this.f15525a);
                List<JsonWebKey> jsonWebKeys = new JsonWebKeySet(simpleResponse.getBody()).getJsonWebKeys();
                long a2 = a(simpleResponse);
                if (a2 <= 0) {
                    logger.debug("Will use default cache duration of {} seconds for content from {}", Long.valueOf(this.b), this.f15525a);
                    a2 = this.b;
                }
                long currentTimeMillis2 = System.currentTimeMillis() + (1000 * a2);
                logger.debug("Updated JWKS content from {} will be cached for {} seconds until about {} -> {}", this.f15525a, Long.valueOf(a2), new Date(currentTimeMillis2), jsonWebKeys);
                this.e = new b(jsonWebKeys, currentTimeMillis2);
            }
        } finally {
            this.f.unlock();
        }
    }

    public void setDefaultCacheDuration(long j) {
        this.b = j;
    }

    public void setRefreshReprieveThreshold(long j) {
        this.g = j;
    }

    public void setRetainCacheOnErrorDuration(long j) {
        this.d = j * 1000;
    }

    public void setSimpleHttpGet(SimpleGet simpleGet) {
        this.c = simpleGet;
    }
}
