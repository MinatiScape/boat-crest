package com.google.crypto.tink;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.errorprone.annotations.Immutable;
@Immutable
/* loaded from: classes10.dex */
public final class KeyTemplate {

    /* renamed from: a  reason: collision with root package name */
    public final com.google.crypto.tink.proto.KeyTemplate f10820a;

    /* loaded from: classes10.dex */
    public enum OutputPrefixType {
        TINK,
        LEGACY,
        RAW,
        CRUNCHY
    }

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10821a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[OutputPrefixType.values().length];
            b = iArr;
            try {
                iArr[OutputPrefixType.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[OutputPrefixType.LEGACY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[OutputPrefixType.RAW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[OutputPrefixType.CRUNCHY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[com.google.crypto.tink.proto.OutputPrefixType.values().length];
            f10821a = iArr2;
            try {
                iArr2[com.google.crypto.tink.proto.OutputPrefixType.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f10821a[com.google.crypto.tink.proto.OutputPrefixType.LEGACY.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f10821a[com.google.crypto.tink.proto.OutputPrefixType.RAW.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f10821a[com.google.crypto.tink.proto.OutputPrefixType.CRUNCHY.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public KeyTemplate(com.google.crypto.tink.proto.KeyTemplate keyTemplate) {
        this.f10820a = keyTemplate;
    }

    public static OutputPrefixType a(com.google.crypto.tink.proto.OutputPrefixType outputPrefixType) {
        int i = a.f10821a[outputPrefixType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        return OutputPrefixType.CRUNCHY;
                    }
                    throw new IllegalArgumentException("Unknown output prefix type");
                }
                return OutputPrefixType.RAW;
            }
            return OutputPrefixType.LEGACY;
        }
        return OutputPrefixType.TINK;
    }

    public static com.google.crypto.tink.proto.OutputPrefixType c(OutputPrefixType outputPrefixType) {
        int i = a.b[outputPrefixType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        return com.google.crypto.tink.proto.OutputPrefixType.CRUNCHY;
                    }
                    throw new IllegalArgumentException("Unknown output prefix type");
                }
                return com.google.crypto.tink.proto.OutputPrefixType.RAW;
            }
            return com.google.crypto.tink.proto.OutputPrefixType.LEGACY;
        }
        return com.google.crypto.tink.proto.OutputPrefixType.TINK;
    }

    public static KeyTemplate create(String str, byte[] bArr, OutputPrefixType outputPrefixType) {
        return new KeyTemplate(com.google.crypto.tink.proto.KeyTemplate.newBuilder().setTypeUrl(str).setValue(ByteString.copyFrom(bArr)).setOutputPrefixType(c(outputPrefixType)).build());
    }

    public com.google.crypto.tink.proto.KeyTemplate b() {
        return this.f10820a;
    }

    public OutputPrefixType getOutputPrefixType() {
        return a(this.f10820a.getOutputPrefixType());
    }

    public String getTypeUrl() {
        return this.f10820a.getTypeUrl();
    }

    public byte[] getValue() {
        return this.f10820a.getValue().toByteArray();
    }
}
