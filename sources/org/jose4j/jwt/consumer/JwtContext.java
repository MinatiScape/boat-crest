package org.jose4j.jwt.consumer;

import java.util.List;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwx.JsonWebStructure;
/* loaded from: classes13.dex */
public class JwtContext {

    /* renamed from: a  reason: collision with root package name */
    public String f15543a;
    public JwtClaims b;
    public List<JsonWebStructure> c;

    public JwtContext(JwtClaims jwtClaims, List<JsonWebStructure> list) {
        this.b = jwtClaims;
        this.c = list;
    }

    public void a(JwtClaims jwtClaims) {
        this.b = jwtClaims;
    }

    public List<JsonWebStructure> getJoseObjects() {
        return this.c;
    }

    public String getJwt() {
        return this.f15543a;
    }

    public JwtClaims getJwtClaims() {
        return this.b;
    }

    public JwtContext(String str, JwtClaims jwtClaims, List<JsonWebStructure> list) {
        this.f15543a = str;
        this.b = jwtClaims;
        this.c = list;
    }
}
