package com.google.crypto.tink.proto;

import com.google.crypto.tink.proto.KmsAeadKeyFormat;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.CodedInputStream;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes10.dex */
public final class KmsAeadKey extends GeneratedMessageLite<KmsAeadKey, Builder> implements KmsAeadKeyOrBuilder {
    private static final KmsAeadKey DEFAULT_INSTANCE;
    public static final int PARAMS_FIELD_NUMBER = 2;
    private static volatile Parser<KmsAeadKey> PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private KmsAeadKeyFormat params_;
    private int version_;

    /* loaded from: classes10.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<KmsAeadKey, Builder> implements KmsAeadKeyOrBuilder {
        public /* synthetic */ Builder(a aVar) {
            this();
        }

        public Builder clearParams() {
            copyOnWrite();
            ((KmsAeadKey) this.instance).w();
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((KmsAeadKey) this.instance).x();
            return this;
        }

        @Override // com.google.crypto.tink.proto.KmsAeadKeyOrBuilder
        public KmsAeadKeyFormat getParams() {
            return ((KmsAeadKey) this.instance).getParams();
        }

        @Override // com.google.crypto.tink.proto.KmsAeadKeyOrBuilder
        public int getVersion() {
            return ((KmsAeadKey) this.instance).getVersion();
        }

        @Override // com.google.crypto.tink.proto.KmsAeadKeyOrBuilder
        public boolean hasParams() {
            return ((KmsAeadKey) this.instance).hasParams();
        }

        public Builder mergeParams(KmsAeadKeyFormat kmsAeadKeyFormat) {
            copyOnWrite();
            ((KmsAeadKey) this.instance).y(kmsAeadKeyFormat);
            return this;
        }

        public Builder setParams(KmsAeadKeyFormat kmsAeadKeyFormat) {
            copyOnWrite();
            ((KmsAeadKey) this.instance).z(kmsAeadKeyFormat);
            return this;
        }

        public Builder setVersion(int i) {
            copyOnWrite();
            ((KmsAeadKey) this.instance).A(i);
            return this;
        }

        public Builder() {
            super(KmsAeadKey.DEFAULT_INSTANCE);
        }

        public Builder setParams(KmsAeadKeyFormat.Builder builder) {
            copyOnWrite();
            ((KmsAeadKey) this.instance).z(builder.build());
            return this;
        }
    }

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10926a;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f10926a = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10926a[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10926a[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10926a[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f10926a[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f10926a[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f10926a[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    static {
        KmsAeadKey kmsAeadKey = new KmsAeadKey();
        DEFAULT_INSTANCE = kmsAeadKey;
        GeneratedMessageLite.registerDefaultInstance(KmsAeadKey.class, kmsAeadKey);
    }

    public static KmsAeadKey getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static KmsAeadKey parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (KmsAeadKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static KmsAeadKey parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (KmsAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<KmsAeadKey> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    public final void A(int i) {
        this.version_ = i;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (a.f10926a[methodToInvoke.ordinal()]) {
            case 1:
                return new KmsAeadKey();
            case 2:
                return new Builder(null);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\t", new Object[]{"version_", "params_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<KmsAeadKey> parser = PARSER;
                if (parser == null) {
                    synchronized (KmsAeadKey.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // com.google.crypto.tink.proto.KmsAeadKeyOrBuilder
    public KmsAeadKeyFormat getParams() {
        KmsAeadKeyFormat kmsAeadKeyFormat = this.params_;
        return kmsAeadKeyFormat == null ? KmsAeadKeyFormat.getDefaultInstance() : kmsAeadKeyFormat;
    }

    @Override // com.google.crypto.tink.proto.KmsAeadKeyOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override // com.google.crypto.tink.proto.KmsAeadKeyOrBuilder
    public boolean hasParams() {
        return this.params_ != null;
    }

    public final void w() {
        this.params_ = null;
    }

    public final void x() {
        this.version_ = 0;
    }

    public final void y(KmsAeadKeyFormat kmsAeadKeyFormat) {
        kmsAeadKeyFormat.getClass();
        KmsAeadKeyFormat kmsAeadKeyFormat2 = this.params_;
        if (kmsAeadKeyFormat2 != null && kmsAeadKeyFormat2 != KmsAeadKeyFormat.getDefaultInstance()) {
            this.params_ = KmsAeadKeyFormat.newBuilder(this.params_).mergeFrom((KmsAeadKeyFormat.Builder) kmsAeadKeyFormat).buildPartial();
        } else {
            this.params_ = kmsAeadKeyFormat;
        }
    }

    public final void z(KmsAeadKeyFormat kmsAeadKeyFormat) {
        kmsAeadKeyFormat.getClass();
        this.params_ = kmsAeadKeyFormat;
    }

    public static Builder newBuilder(KmsAeadKey kmsAeadKey) {
        return DEFAULT_INSTANCE.createBuilder(kmsAeadKey);
    }

    public static KmsAeadKey parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KmsAeadKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static KmsAeadKey parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (KmsAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static KmsAeadKey parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (KmsAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static KmsAeadKey parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (KmsAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static KmsAeadKey parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (KmsAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static KmsAeadKey parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (KmsAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static KmsAeadKey parseFrom(InputStream inputStream) throws IOException {
        return (KmsAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static KmsAeadKey parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KmsAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static KmsAeadKey parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (KmsAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static KmsAeadKey parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KmsAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
