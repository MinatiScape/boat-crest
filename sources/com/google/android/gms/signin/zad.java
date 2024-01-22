package com.google.android.gms.signin;

import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ShowFirstParty;
/* loaded from: classes10.dex */
public final class zad {

    /* renamed from: a  reason: collision with root package name */
    public static final Api.AbstractClientBuilder f10172a;
    public static final Api.ClientKey zaa;
    @ShowFirstParty
    public static final Api.ClientKey zab;
    public static final Api.AbstractClientBuilder zac;
    public static final Scope zae;
    public static final Scope zaf;
    public static final Api zag;
    public static final Api zah;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zaa = clientKey;
        Api.ClientKey clientKey2 = new Api.ClientKey();
        zab = clientKey2;
        a aVar = new a();
        zac = aVar;
        b bVar = new b();
        f10172a = bVar;
        zae = new Scope(Scopes.PROFILE);
        zaf = new Scope("email");
        zag = new Api("SignIn.API", aVar, clientKey);
        zah = new Api("SignIn.INTERNAL_API", bVar, clientKey2);
    }
}
