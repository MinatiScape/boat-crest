package com.google.crypto.tink.prf;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Immutable
/* loaded from: classes10.dex */
public class PrfSetWrapper implements PrimitiveWrapper<Prf, PrfSet> {

    /* loaded from: classes10.dex */
    public static class b extends PrfSet {

        /* renamed from: a  reason: collision with root package name */
        public final Map<Integer, Prf> f10864a;
        public final int b;

        @Override // com.google.crypto.tink.prf.PrfSet
        public Map<Integer, Prf> getPrfs() throws GeneralSecurityException {
            return this.f10864a;
        }

        @Override // com.google.crypto.tink.prf.PrfSet
        public int getPrimaryId() {
            return this.b;
        }

        public b(PrimitiveSet<Prf> primitiveSet) throws GeneralSecurityException {
            if (!primitiveSet.getRawPrimitives().isEmpty()) {
                if (primitiveSet.getPrimary() != null) {
                    this.b = primitiveSet.getPrimary().getKeyId();
                    List<PrimitiveSet.Entry<Prf>> rawPrimitives = primitiveSet.getRawPrimitives();
                    HashMap hashMap = new HashMap();
                    for (PrimitiveSet.Entry<Prf> entry : rawPrimitives) {
                        if (entry.getOutputPrefixType().equals(OutputPrefixType.RAW)) {
                            hashMap.put(Integer.valueOf(entry.getKeyId()), entry.getPrimitive());
                        } else {
                            throw new GeneralSecurityException("Key " + entry.getKeyId() + " has non raw prefix type");
                        }
                    }
                    this.f10864a = Collections.unmodifiableMap(hashMap);
                    return;
                }
                throw new GeneralSecurityException("Primary key not set.");
            }
            throw new GeneralSecurityException("No primitives provided.");
        }
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new PrfSetWrapper());
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<Prf> getInputPrimitiveClass() {
        return Prf.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<PrfSet> getPrimitiveClass() {
        return PrfSet.class;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public PrfSet wrap(PrimitiveSet<Prf> primitiveSet) throws GeneralSecurityException {
        return new b(primitiveSet);
    }
}
