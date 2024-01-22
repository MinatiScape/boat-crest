package org.bouncycastle.crypto.agreement.jpake;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.util.Arrays;
import org.jose4j.jwk.RsaJsonWebKey;
/* loaded from: classes6.dex */
public class JPAKEParticipant {
    public static final int STATE_INITIALIZED = 0;
    public static final int STATE_KEY_CALCULATED = 50;
    public static final int STATE_ROUND_1_CREATED = 10;
    public static final int STATE_ROUND_1_VALIDATED = 20;
    public static final int STATE_ROUND_2_CREATED = 30;
    public static final int STATE_ROUND_2_VALIDATED = 40;
    public static final int STATE_ROUND_3_CREATED = 60;
    public static final int STATE_ROUND_3_VALIDATED = 70;

    /* renamed from: a  reason: collision with root package name */
    public final String f14619a;
    public char[] b;
    public final Digest c;
    public final SecureRandom d;
    public final BigInteger e;
    public final BigInteger f;
    public final BigInteger g;
    public String h;
    public BigInteger i;
    public BigInteger j;
    public BigInteger k;
    public BigInteger l;
    public BigInteger m;
    public BigInteger n;
    public BigInteger o;
    public int p;

    public JPAKEParticipant(String str, char[] cArr) {
        this(str, cArr, JPAKEPrimeOrderGroups.NIST_3072);
    }

    public JPAKEParticipant(String str, char[] cArr, JPAKEPrimeOrderGroup jPAKEPrimeOrderGroup) {
        this(str, cArr, jPAKEPrimeOrderGroup, new SHA256Digest(), new SecureRandom());
    }

    public JPAKEParticipant(String str, char[] cArr, JPAKEPrimeOrderGroup jPAKEPrimeOrderGroup, Digest digest, SecureRandom secureRandom) {
        JPAKEUtil.validateNotNull(str, "participantId");
        JPAKEUtil.validateNotNull(cArr, "password");
        JPAKEUtil.validateNotNull(jPAKEPrimeOrderGroup, RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME);
        JPAKEUtil.validateNotNull(digest, CMSAttributeTableGenerator.DIGEST);
        JPAKEUtil.validateNotNull(secureRandom, "random");
        if (cArr.length == 0) {
            throw new IllegalArgumentException("Password must not be empty.");
        }
        this.f14619a = str;
        this.b = Arrays.copyOf(cArr, cArr.length);
        this.e = jPAKEPrimeOrderGroup.getP();
        this.f = jPAKEPrimeOrderGroup.getQ();
        this.g = jPAKEPrimeOrderGroup.getG();
        this.c = digest;
        this.d = secureRandom;
        this.p = 0;
    }

    public BigInteger calculateKeyingMaterial() {
        int i = this.p;
        if (i >= 50) {
            throw new IllegalStateException("Key already calculated for " + this.f14619a);
        } else if (i < 40) {
            throw new IllegalStateException("Round2 payload must be validated prior to creating key for " + this.f14619a);
        } else {
            BigInteger calculateS = JPAKEUtil.calculateS(this.b);
            Arrays.fill(this.b, (char) 0);
            this.b = null;
            BigInteger calculateKeyingMaterial = JPAKEUtil.calculateKeyingMaterial(this.e, this.f, this.n, this.j, calculateS, this.o);
            this.i = null;
            this.j = null;
            this.o = null;
            this.p = 50;
            return calculateKeyingMaterial;
        }
    }

    public JPAKERound1Payload createRound1PayloadToSend() {
        if (this.p >= 10) {
            throw new IllegalStateException("Round1 payload already created for " + this.f14619a);
        }
        this.i = JPAKEUtil.generateX1(this.f, this.d);
        this.j = JPAKEUtil.generateX2(this.f, this.d);
        this.k = JPAKEUtil.calculateGx(this.e, this.g, this.i);
        this.l = JPAKEUtil.calculateGx(this.e, this.g, this.j);
        BigInteger[] calculateZeroKnowledgeProof = JPAKEUtil.calculateZeroKnowledgeProof(this.e, this.f, this.g, this.k, this.i, this.f14619a, this.c, this.d);
        BigInteger[] calculateZeroKnowledgeProof2 = JPAKEUtil.calculateZeroKnowledgeProof(this.e, this.f, this.g, this.l, this.j, this.f14619a, this.c, this.d);
        this.p = 10;
        return new JPAKERound1Payload(this.f14619a, this.k, this.l, calculateZeroKnowledgeProof, calculateZeroKnowledgeProof2);
    }

    public JPAKERound2Payload createRound2PayloadToSend() {
        int i = this.p;
        if (i >= 30) {
            throw new IllegalStateException("Round2 payload already created for " + this.f14619a);
        } else if (i < 20) {
            throw new IllegalStateException("Round1 payload must be validated prior to creating Round2 payload for " + this.f14619a);
        } else {
            BigInteger calculateGA = JPAKEUtil.calculateGA(this.e, this.k, this.m, this.n);
            BigInteger calculateX2s = JPAKEUtil.calculateX2s(this.f, this.j, JPAKEUtil.calculateS(this.b));
            BigInteger calculateA = JPAKEUtil.calculateA(this.e, this.f, calculateGA, calculateX2s);
            BigInteger[] calculateZeroKnowledgeProof = JPAKEUtil.calculateZeroKnowledgeProof(this.e, this.f, calculateGA, calculateA, calculateX2s, this.f14619a, this.c, this.d);
            this.p = 30;
            return new JPAKERound2Payload(this.f14619a, calculateA, calculateZeroKnowledgeProof);
        }
    }

    public JPAKERound3Payload createRound3PayloadToSend(BigInteger bigInteger) {
        int i = this.p;
        if (i >= 60) {
            throw new IllegalStateException("Round3 payload already created for " + this.f14619a);
        } else if (i >= 50) {
            BigInteger calculateMacTag = JPAKEUtil.calculateMacTag(this.f14619a, this.h, this.k, this.l, this.m, this.n, bigInteger, this.c);
            this.p = 60;
            return new JPAKERound3Payload(this.f14619a, calculateMacTag);
        } else {
            throw new IllegalStateException("Keying material must be calculated prior to creating Round3 payload for " + this.f14619a);
        }
    }

    public int getState() {
        return this.p;
    }

    public void validateRound1PayloadReceived(JPAKERound1Payload jPAKERound1Payload) throws CryptoException {
        if (this.p >= 20) {
            throw new IllegalStateException("Validation already attempted for round1 payload for" + this.f14619a);
        }
        this.h = jPAKERound1Payload.getParticipantId();
        this.m = jPAKERound1Payload.getGx1();
        this.n = jPAKERound1Payload.getGx2();
        BigInteger[] knowledgeProofForX1 = jPAKERound1Payload.getKnowledgeProofForX1();
        BigInteger[] knowledgeProofForX2 = jPAKERound1Payload.getKnowledgeProofForX2();
        JPAKEUtil.validateParticipantIdsDiffer(this.f14619a, jPAKERound1Payload.getParticipantId());
        JPAKEUtil.validateGx4(this.n);
        JPAKEUtil.validateZeroKnowledgeProof(this.e, this.f, this.g, this.m, knowledgeProofForX1, jPAKERound1Payload.getParticipantId(), this.c);
        JPAKEUtil.validateZeroKnowledgeProof(this.e, this.f, this.g, this.n, knowledgeProofForX2, jPAKERound1Payload.getParticipantId(), this.c);
        this.p = 20;
    }

    public void validateRound2PayloadReceived(JPAKERound2Payload jPAKERound2Payload) throws CryptoException {
        int i = this.p;
        if (i >= 40) {
            throw new IllegalStateException("Validation already attempted for round2 payload for" + this.f14619a);
        } else if (i < 20) {
            throw new IllegalStateException("Round1 payload must be validated prior to validating Round2 payload for " + this.f14619a);
        } else {
            BigInteger calculateGA = JPAKEUtil.calculateGA(this.e, this.m, this.k, this.l);
            this.o = jPAKERound2Payload.getA();
            BigInteger[] knowledgeProofForX2s = jPAKERound2Payload.getKnowledgeProofForX2s();
            JPAKEUtil.validateParticipantIdsDiffer(this.f14619a, jPAKERound2Payload.getParticipantId());
            JPAKEUtil.validateParticipantIdsEqual(this.h, jPAKERound2Payload.getParticipantId());
            JPAKEUtil.validateGa(calculateGA);
            JPAKEUtil.validateZeroKnowledgeProof(this.e, this.f, calculateGA, this.o, knowledgeProofForX2s, jPAKERound2Payload.getParticipantId(), this.c);
            this.p = 40;
        }
    }

    public void validateRound3PayloadReceived(JPAKERound3Payload jPAKERound3Payload, BigInteger bigInteger) throws CryptoException {
        int i = this.p;
        if (i >= 70) {
            throw new IllegalStateException("Validation already attempted for round3 payload for" + this.f14619a);
        } else if (i < 50) {
            throw new IllegalStateException("Keying material must be calculated validated prior to validating Round3 payload for " + this.f14619a);
        } else {
            JPAKEUtil.validateParticipantIdsDiffer(this.f14619a, jPAKERound3Payload.getParticipantId());
            JPAKEUtil.validateParticipantIdsEqual(this.h, jPAKERound3Payload.getParticipantId());
            JPAKEUtil.validateMacTag(this.f14619a, this.h, this.k, this.l, this.m, this.n, bigInteger, this.c, jPAKERound3Payload.getMacTag());
            this.k = null;
            this.l = null;
            this.m = null;
            this.n = null;
            this.p = 70;
        }
    }
}
