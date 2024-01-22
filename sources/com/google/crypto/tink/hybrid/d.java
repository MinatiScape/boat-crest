package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.proto.AesCtrHmacAeadKey;
import com.google.crypto.tink.proto.AesCtrHmacAeadKeyFormat;
import com.google.crypto.tink.proto.AesCtrKey;
import com.google.crypto.tink.proto.AesGcmKey;
import com.google.crypto.tink.proto.AesGcmKeyFormat;
import com.google.crypto.tink.proto.HmacKey;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EciesAeadHkdfDemHelper;
import java.security.GeneralSecurityException;
import java.util.Arrays;
/* loaded from: classes10.dex */
public class d implements EciesAeadHkdfDemHelper {

    /* renamed from: a  reason: collision with root package name */
    public final String f10847a;
    public final int b;
    public AesGcmKey c;
    public AesCtrHmacAeadKey d;
    public int e;

    public d(KeyTemplate keyTemplate) throws GeneralSecurityException {
        String typeUrl = keyTemplate.getTypeUrl();
        this.f10847a = typeUrl;
        if (typeUrl.equals(AeadConfig.AES_GCM_TYPE_URL)) {
            try {
                AesGcmKeyFormat parseFrom = AesGcmKeyFormat.parseFrom(keyTemplate.getValue(), ExtensionRegistryLite.getEmptyRegistry());
                this.c = (AesGcmKey) Registry.newKey(keyTemplate);
                this.b = parseFrom.getKeySize();
            } catch (InvalidProtocolBufferException e) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", e);
            }
        } else if (typeUrl.equals(AeadConfig.AES_CTR_HMAC_AEAD_TYPE_URL)) {
            try {
                AesCtrHmacAeadKeyFormat parseFrom2 = AesCtrHmacAeadKeyFormat.parseFrom(keyTemplate.getValue(), ExtensionRegistryLite.getEmptyRegistry());
                this.d = (AesCtrHmacAeadKey) Registry.newKey(keyTemplate);
                this.e = parseFrom2.getAesCtrKeyFormat().getKeySize();
                this.b = this.e + parseFrom2.getHmacKeyFormat().getKeySize();
            } catch (InvalidProtocolBufferException e2) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesCtrHmacAeadKeyFormat", e2);
            }
        } else {
            throw new GeneralSecurityException("unsupported AEAD DEM key type: " + typeUrl);
        }
    }

    @Override // com.google.crypto.tink.subtle.EciesAeadHkdfDemHelper
    public Aead getAead(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length == getSymmetricKeySizeInBytes()) {
            if (this.f10847a.equals(AeadConfig.AES_GCM_TYPE_URL)) {
                return (Aead) Registry.getPrimitive(this.f10847a, AesGcmKey.newBuilder().mergeFrom((AesGcmKey.Builder) this.c).setKeyValue(ByteString.copyFrom(bArr, 0, this.b)).build(), Aead.class);
            } else if (this.f10847a.equals(AeadConfig.AES_CTR_HMAC_AEAD_TYPE_URL)) {
                byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, this.e);
                byte[] copyOfRange2 = Arrays.copyOfRange(bArr, this.e, this.b);
                AesCtrHmacAeadKey.Builder aesCtrKey = AesCtrHmacAeadKey.newBuilder().setVersion(this.d.getVersion()).setAesCtrKey(AesCtrKey.newBuilder().mergeFrom((AesCtrKey.Builder) this.d.getAesCtrKey()).setKeyValue(ByteString.copyFrom(copyOfRange)).build());
                return (Aead) Registry.getPrimitive(this.f10847a, aesCtrKey.setHmacKey(HmacKey.newBuilder().mergeFrom((HmacKey.Builder) this.d.getHmacKey()).setKeyValue(ByteString.copyFrom(copyOfRange2)).build()).build(), Aead.class);
            } else {
                throw new GeneralSecurityException("unknown DEM key type");
            }
        }
        throw new GeneralSecurityException("Symmetric key has incorrect length");
    }

    @Override // com.google.crypto.tink.subtle.EciesAeadHkdfDemHelper
    public int getSymmetricKeySizeInBytes() {
        return this.b;
    }
}
