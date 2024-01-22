package org.jose4j.jwa;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.jose4j.jwa.Algorithm;
import org.jose4j.lang.ExceptionHelp;
import org.jose4j.lang.InvalidAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes13.dex */
public class AlgorithmFactory<A extends Algorithm> {

    /* renamed from: a  reason: collision with root package name */
    public final Logger f15510a;
    public String b;
    public final Map<String, A> c = new LinkedHashMap();

    public AlgorithmFactory(String str, Class<A> cls) {
        this.b = str;
        this.f15510a = LoggerFactory.getLogger(getClass().getName() + "->" + cls.getSimpleName());
    }

    public final boolean a(A a2) {
        try {
            return a2.isAvailable();
        } catch (Throwable th) {
            Logger logger = this.f15510a;
            logger.debug("Unexpected problem checking for availability of " + a2.getAlgorithmIdentifier() + " algorithm: " + ExceptionHelp.toStringWithCauses(th));
            return false;
        }
    }

    public A getAlgorithm(String str) throws InvalidAlgorithmException {
        A a2 = this.c.get(str);
        if (a2 != null) {
            return a2;
        }
        throw new InvalidAlgorithmException(str + " is an unknown, unsupported or unavailable " + this.b + " algorithm (not one of " + getSupportedAlgorithms() + ").");
    }

    public Set<String> getSupportedAlgorithms() {
        return Collections.unmodifiableSet(this.c.keySet());
    }

    public boolean isAvailable(String str) {
        return this.c.containsKey(str);
    }

    public void registerAlgorithm(A a2) {
        String algorithmIdentifier = a2.getAlgorithmIdentifier();
        if (a(a2)) {
            this.c.put(algorithmIdentifier, a2);
            this.f15510a.debug("{} registered for {} algorithm {}", a2, this.b, algorithmIdentifier);
            return;
        }
        this.f15510a.debug("{} is unavailable so will not be registered for {} algorithms.", algorithmIdentifier, this.b);
    }

    public void unregisterAlgorithm(String str) {
        this.c.remove(str);
    }
}
