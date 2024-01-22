package org.bouncycastle.jce.interfaces;

import javax.crypto.interfaces.DHKey;
import org.bouncycastle.jce.spec.ElGamalParameterSpec;
/* loaded from: classes13.dex */
public interface ElGamalKey extends DHKey {
    ElGamalParameterSpec getParameters();
}
