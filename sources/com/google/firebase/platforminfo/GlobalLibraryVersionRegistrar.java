package com.google.firebase.platforminfo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes10.dex */
public class GlobalLibraryVersionRegistrar {
    public static volatile GlobalLibraryVersionRegistrar b;

    /* renamed from: a  reason: collision with root package name */
    public final Set<c> f11474a = new HashSet();

    public static GlobalLibraryVersionRegistrar getInstance() {
        GlobalLibraryVersionRegistrar globalLibraryVersionRegistrar = b;
        if (globalLibraryVersionRegistrar == null) {
            synchronized (GlobalLibraryVersionRegistrar.class) {
                globalLibraryVersionRegistrar = b;
                if (globalLibraryVersionRegistrar == null) {
                    globalLibraryVersionRegistrar = new GlobalLibraryVersionRegistrar();
                    b = globalLibraryVersionRegistrar;
                }
            }
        }
        return globalLibraryVersionRegistrar;
    }

    public Set<c> a() {
        Set<c> unmodifiableSet;
        synchronized (this.f11474a) {
            unmodifiableSet = Collections.unmodifiableSet(this.f11474a);
        }
        return unmodifiableSet;
    }

    public void registerVersion(String str, String str2) {
        synchronized (this.f11474a) {
            this.f11474a.add(c.a(str, str2));
        }
    }
}
