package org.jose4j.jwa;

import org.jose4j.keys.KeyPersuasion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes13.dex */
public abstract class AlgorithmInfo implements Algorithm {

    /* renamed from: a  reason: collision with root package name */
    public String f15512a;
    public String b;
    public KeyPersuasion c;
    public String d;
    public final Logger log = LoggerFactory.getLogger(getClass());

    @Override // org.jose4j.jwa.Algorithm
    public String getAlgorithmIdentifier() {
        return this.f15512a;
    }

    @Override // org.jose4j.jwa.Algorithm
    public String getJavaAlgorithm() {
        return this.b;
    }

    @Override // org.jose4j.jwa.Algorithm
    public KeyPersuasion getKeyPersuasion() {
        return this.c;
    }

    @Override // org.jose4j.jwa.Algorithm
    public String getKeyType() {
        return this.d;
    }

    public void setAlgorithmIdentifier(String str) {
        this.f15512a = str;
    }

    public void setJavaAlgorithm(String str) {
        this.b = str;
    }

    public void setKeyPersuasion(KeyPersuasion keyPersuasion) {
        this.c = keyPersuasion;
    }

    public void setKeyType(String str) {
        this.d = str;
    }

    public String toString() {
        return getClass().getName() + "(" + this.f15512a + "|" + this.b + ")";
    }
}
