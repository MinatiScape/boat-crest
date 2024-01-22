package com.google.crypto.tink;

import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.OutputPrefixType;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes10.dex */
public final class KeysetManager {
    @GuardedBy("this")

    /* renamed from: a  reason: collision with root package name */
    public final Keyset.Builder f10826a;

    public KeysetManager(Keyset.Builder builder) {
        this.f10826a = builder;
    }

    public static int d() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bArr = new byte[4];
        int i = 0;
        while (i == 0) {
            secureRandom.nextBytes(bArr);
            i = ((bArr[0] & Byte.MAX_VALUE) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255);
        }
        return i;
    }

    public static KeysetManager withEmptyKeyset() {
        return new KeysetManager(Keyset.newBuilder());
    }

    public static KeysetManager withKeysetHandle(KeysetHandle keysetHandle) {
        return new KeysetManager(keysetHandle.f().toBuilder());
    }

    public final synchronized boolean a(int i) {
        for (Keyset.Key key : this.f10826a.getKeyList()) {
            if (key.getKeyId() == i) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public synchronized KeysetManager add(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
        addNewKey(keyTemplate, false);
        return this;
    }

    @Deprecated
    public synchronized int addNewKey(com.google.crypto.tink.proto.KeyTemplate keyTemplate, boolean z) throws GeneralSecurityException {
        Keyset.Key b;
        b = b(keyTemplate);
        this.f10826a.addKey(b);
        if (z) {
            this.f10826a.setPrimaryKeyId(b.getKeyId());
        }
        return b.getKeyId();
    }

    public final synchronized Keyset.Key b(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
        KeyData newKeyData;
        int c;
        OutputPrefixType outputPrefixType;
        newKeyData = Registry.newKeyData(keyTemplate);
        c = c();
        outputPrefixType = keyTemplate.getOutputPrefixType();
        if (outputPrefixType == OutputPrefixType.UNKNOWN_PREFIX) {
            outputPrefixType = OutputPrefixType.TINK;
        }
        return Keyset.Key.newBuilder().setKeyData(newKeyData).setKeyId(c).setStatus(KeyStatusType.ENABLED).setOutputPrefixType(outputPrefixType).build();
    }

    public final synchronized int c() {
        int d;
        d = d();
        while (a(d)) {
            d = d();
        }
        return d;
    }

    public synchronized KeysetManager delete(int i) throws GeneralSecurityException {
        if (i != this.f10826a.getPrimaryKeyId()) {
            for (int i2 = 0; i2 < this.f10826a.getKeyCount(); i2++) {
                if (this.f10826a.getKey(i2).getKeyId() == i) {
                    this.f10826a.removeKey(i2);
                }
            }
            throw new GeneralSecurityException("key not found: " + i);
        }
        throw new GeneralSecurityException("cannot delete the primary key");
        return this;
    }

    public synchronized KeysetManager destroy(int i) throws GeneralSecurityException {
        if (i != this.f10826a.getPrimaryKeyId()) {
            for (int i2 = 0; i2 < this.f10826a.getKeyCount(); i2++) {
                Keyset.Key key = this.f10826a.getKey(i2);
                if (key.getKeyId() == i) {
                    if (key.getStatus() != KeyStatusType.ENABLED && key.getStatus() != KeyStatusType.DISABLED && key.getStatus() != KeyStatusType.DESTROYED) {
                        throw new GeneralSecurityException("cannot destroy key with id " + i + " and status " + key.getStatus());
                    }
                    this.f10826a.setKey(i2, key.toBuilder().setStatus(KeyStatusType.DESTROYED).clearKeyData().build());
                }
            }
            throw new GeneralSecurityException("key not found: " + i);
        }
        throw new GeneralSecurityException("cannot destroy the primary key");
        return this;
    }

    public synchronized KeysetManager disable(int i) throws GeneralSecurityException {
        if (i != this.f10826a.getPrimaryKeyId()) {
            for (int i2 = 0; i2 < this.f10826a.getKeyCount(); i2++) {
                Keyset.Key key = this.f10826a.getKey(i2);
                if (key.getKeyId() == i) {
                    if (key.getStatus() != KeyStatusType.ENABLED && key.getStatus() != KeyStatusType.DISABLED) {
                        throw new GeneralSecurityException("cannot disable key with id " + i + " and status " + key.getStatus());
                    }
                    this.f10826a.setKey(i2, key.toBuilder().setStatus(KeyStatusType.DISABLED).build());
                }
            }
            throw new GeneralSecurityException("key not found: " + i);
        }
        throw new GeneralSecurityException("cannot disable the primary key");
        return this;
    }

    public synchronized KeysetManager enable(int i) throws GeneralSecurityException {
        for (int i2 = 0; i2 < this.f10826a.getKeyCount(); i2++) {
            Keyset.Key key = this.f10826a.getKey(i2);
            if (key.getKeyId() == i) {
                KeyStatusType status = key.getStatus();
                KeyStatusType keyStatusType = KeyStatusType.ENABLED;
                if (status != keyStatusType && key.getStatus() != KeyStatusType.DISABLED) {
                    throw new GeneralSecurityException("cannot enable key with id " + i + " and status " + key.getStatus());
                }
                this.f10826a.setKey(i2, key.toBuilder().setStatus(keyStatusType).build());
            }
        }
        throw new GeneralSecurityException("key not found: " + i);
        return this;
    }

    public synchronized KeysetHandle getKeysetHandle() throws GeneralSecurityException {
        return KeysetHandle.e(this.f10826a.build());
    }

    @Deprecated
    public synchronized KeysetManager promote(int i) throws GeneralSecurityException {
        return setPrimary(i);
    }

    @Deprecated
    public synchronized KeysetManager rotate(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
        addNewKey(keyTemplate, true);
        return this;
    }

    public synchronized KeysetManager setPrimary(int i) throws GeneralSecurityException {
        for (int i2 = 0; i2 < this.f10826a.getKeyCount(); i2++) {
            Keyset.Key key = this.f10826a.getKey(i2);
            if (key.getKeyId() == i) {
                if (key.getStatus().equals(KeyStatusType.ENABLED)) {
                    this.f10826a.setPrimaryKeyId(i);
                } else {
                    throw new GeneralSecurityException("cannot set key as primary because it's not enabled: " + i);
                }
            }
        }
        throw new GeneralSecurityException("key not found: " + i);
        return this;
    }

    public synchronized KeysetManager add(KeyTemplate keyTemplate) throws GeneralSecurityException {
        addNewKey(keyTemplate.b(), false);
        return this;
    }
}
