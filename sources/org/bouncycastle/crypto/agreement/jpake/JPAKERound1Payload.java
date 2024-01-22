package org.bouncycastle.crypto.agreement.jpake;

import java.math.BigInteger;
import org.bouncycastle.util.Arrays;
/* loaded from: classes6.dex */
public class JPAKERound1Payload {

    /* renamed from: a  reason: collision with root package name */
    public final String f14621a;
    public final BigInteger b;
    public final BigInteger c;
    public final BigInteger[] d;
    public final BigInteger[] e;

    public JPAKERound1Payload(String str, BigInteger bigInteger, BigInteger bigInteger2, BigInteger[] bigIntegerArr, BigInteger[] bigIntegerArr2) {
        JPAKEUtil.validateNotNull(str, "participantId");
        JPAKEUtil.validateNotNull(bigInteger, "gx1");
        JPAKEUtil.validateNotNull(bigInteger2, "gx2");
        JPAKEUtil.validateNotNull(bigIntegerArr, "knowledgeProofForX1");
        JPAKEUtil.validateNotNull(bigIntegerArr2, "knowledgeProofForX2");
        this.f14621a = str;
        this.b = bigInteger;
        this.c = bigInteger2;
        this.d = Arrays.copyOf(bigIntegerArr, bigIntegerArr.length);
        this.e = Arrays.copyOf(bigIntegerArr2, bigIntegerArr2.length);
    }

    public BigInteger getGx1() {
        return this.b;
    }

    public BigInteger getGx2() {
        return this.c;
    }

    public BigInteger[] getKnowledgeProofForX1() {
        BigInteger[] bigIntegerArr = this.d;
        return Arrays.copyOf(bigIntegerArr, bigIntegerArr.length);
    }

    public BigInteger[] getKnowledgeProofForX2() {
        BigInteger[] bigIntegerArr = this.e;
        return Arrays.copyOf(bigIntegerArr, bigIntegerArr.length);
    }

    public String getParticipantId() {
        return this.f14621a;
    }
}
