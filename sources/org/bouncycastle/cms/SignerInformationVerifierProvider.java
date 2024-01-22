package org.bouncycastle.cms;

import org.bouncycastle.operator.OperatorCreationException;
/* loaded from: classes12.dex */
public interface SignerInformationVerifierProvider {
    SignerInformationVerifier get(SignerId signerId) throws OperatorCreationException;
}
