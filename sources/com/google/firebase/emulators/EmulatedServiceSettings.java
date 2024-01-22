package com.google.firebase.emulators;

import androidx.annotation.NonNull;
/* loaded from: classes10.dex */
public final class EmulatedServiceSettings {

    /* renamed from: a  reason: collision with root package name */
    public final String f11267a;
    public final int b;

    public EmulatedServiceSettings(@NonNull String str, int i) {
        this.f11267a = str;
        this.b = i;
    }

    public String getHost() {
        return this.f11267a;
    }

    public int getPort() {
        return this.b;
    }
}
