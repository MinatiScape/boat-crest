package org.jose4j.jwa;

import java.security.Key;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.Mac;
/* loaded from: classes13.dex */
public class CryptoPrimitive {

    /* renamed from: a  reason: collision with root package name */
    public final Signature f15513a;
    public final Cipher b;
    public final Mac c;
    public final Key d;
    public final KeyAgreement e;

    public CryptoPrimitive(Signature signature) {
        this(signature, null, null, null, null);
    }

    public Cipher getCipher() {
        return this.b;
    }

    public Key getKey() {
        return this.d;
    }

    public KeyAgreement getKeyAgreement() {
        return this.e;
    }

    public Mac getMac() {
        return this.c;
    }

    public Signature getSignature() {
        return this.f15513a;
    }

    public CryptoPrimitive(Cipher cipher) {
        this(null, cipher, null, null, null);
    }

    public CryptoPrimitive(Mac mac) {
        this(null, null, mac, null, null);
    }

    public CryptoPrimitive(Key key) {
        this(null, null, null, key, null);
    }

    public CryptoPrimitive(KeyAgreement keyAgreement) {
        this(null, null, null, null, keyAgreement);
    }

    public CryptoPrimitive(Signature signature, Cipher cipher, Mac mac, Key key, KeyAgreement keyAgreement) {
        this.f15513a = signature;
        this.b = cipher;
        this.c = mac;
        this.d = key;
        this.e = keyAgreement;
    }
}
