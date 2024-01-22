package com.google.firebase.remoteconfig.internal;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue;
/* loaded from: classes10.dex */
public class FirebaseRemoteConfigValueImpl implements FirebaseRemoteConfigValue {

    /* renamed from: a  reason: collision with root package name */
    public final String f11502a;
    public final int b;

    public FirebaseRemoteConfigValueImpl(String str, int i) {
        this.f11502a = str;
        this.b = i;
    }

    public final String a() {
        return asString().trim();
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
    public boolean asBoolean() throws IllegalArgumentException {
        if (this.b == 0) {
            return false;
        }
        String a2 = a();
        if (ConfigGetParameterHandler.e.matcher(a2).matches()) {
            return true;
        }
        if (ConfigGetParameterHandler.f.matcher(a2).matches()) {
            return false;
        }
        throw new IllegalArgumentException(String.format("[Value: %s] cannot be converted to a %s.", a2, "boolean"));
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
    public byte[] asByteArray() {
        if (this.b == 0) {
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_BYTE_ARRAY;
        }
        return this.f11502a.getBytes(ConfigGetParameterHandler.FRC_BYTE_ARRAY_ENCODING);
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
    public double asDouble() {
        if (this.b == 0) {
            return 0.0d;
        }
        String a2 = a();
        try {
            return Double.valueOf(a2).doubleValue();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("[Value: %s] cannot be converted to a %s.", a2, "double"), e);
        }
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
    public long asLong() {
        if (this.b == 0) {
            return 0L;
        }
        String a2 = a();
        try {
            return Long.valueOf(a2).longValue();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("[Value: %s] cannot be converted to a %s.", a2, "long"), e);
        }
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
    public String asString() {
        if (this.b == 0) {
            return "";
        }
        b();
        return this.f11502a;
    }

    public final void b() {
        if (this.f11502a == null) {
            throw new IllegalArgumentException("Value is null, and cannot be converted to the desired type.");
        }
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
    public int getSource() {
        return this.b;
    }
}
