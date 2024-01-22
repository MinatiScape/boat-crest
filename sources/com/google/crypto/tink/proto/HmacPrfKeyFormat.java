package com.google.crypto.tink.proto;

import com.google.crypto.tink.proto.HmacPrfParams;
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
public final class HmacPrfKeyFormat extends GeneratedMessageLite<HmacPrfKeyFormat, Builder> implements HmacPrfKeyFormatOrBuilder {
    private static final HmacPrfKeyFormat DEFAULT_INSTANCE;
    public static final int KEY_SIZE_FIELD_NUMBER = 2;
    public static final int PARAMS_FIELD_NUMBER = 1;
    private static volatile Parser<HmacPrfKeyFormat> PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 3;
    private int keySize_;
    private HmacPrfParams params_;
    private int version_;

    /* loaded from: classes10.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<HmacPrfKeyFormat, Builder> implements HmacPrfKeyFormatOrBuilder {
        public /* synthetic */ Builder(a aVar) {
            this();
        }

        public Builder clearKeySize() {
            copyOnWrite();
            ((HmacPrfKeyFormat) this.instance).y();
            return this;
        }

        public Builder clearParams() {
            copyOnWrite();
            ((HmacPrfKeyFormat) this.instance).z();
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((HmacPrfKeyFormat) this.instance).A();
            return this;
        }

        @Override // com.google.crypto.tink.proto.HmacPrfKeyFormatOrBuilder
        public int getKeySize() {
            return ((HmacPrfKeyFormat) this.instance).getKeySize();
        }

        @Override // com.google.crypto.tink.proto.HmacPrfKeyFormatOrBuilder
        public HmacPrfParams getParams() {
            return ((HmacPrfKeyFormat) this.instance).getParams();
        }

        @Override // com.google.crypto.tink.proto.HmacPrfKeyFormatOrBuilder
        public int getVersion() {
            return ((HmacPrfKeyFormat) this.instance).getVersion();
        }

        @Override // com.google.crypto.tink.proto.HmacPrfKeyFormatOrBuilder
        public boolean hasParams() {
            return ((HmacPrfKeyFormat) this.instance).hasParams();
        }

        public Builder mergeParams(HmacPrfParams hmacPrfParams) {
            copyOnWrite();
            ((HmacPrfKeyFormat) this.instance).B(hmacPrfParams);
            return this;
        }

        public Builder setKeySize(int i) {
            copyOnWrite();
            ((HmacPrfKeyFormat) this.instance).C(i);
            return this;
        }

        public Builder setParams(HmacPrfParams hmacPrfParams) {
            copyOnWrite();
            ((HmacPrfKeyFormat) this.instance).D(hmacPrfParams);
            return this;
        }

        public Builder setVersion(int i) {
            copyOnWrite();
            ((HmacPrfKeyFormat) this.instance).E(i);
            return this;
        }

        public Builder() {
            super(HmacPrfKeyFormat.DEFAULT_INSTANCE);
        }

        public Builder setParams(HmacPrfParams.Builder builder) {
            copyOnWrite();
            ((HmacPrfKeyFormat) this.instance).D(builder.build());
            return this;
        }
    }

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10917a;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f10917a = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10917a[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10917a[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10917a[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f10917a[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f10917a[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f10917a[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    static {
        HmacPrfKeyFormat hmacPrfKeyFormat = new HmacPrfKeyFormat();
        DEFAULT_INSTANCE = hmacPrfKeyFormat;
        GeneratedMessageLite.registerDefaultInstance(HmacPrfKeyFormat.class, hmacPrfKeyFormat);
    }

    public static HmacPrfKeyFormat getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static HmacPrfKeyFormat parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (HmacPrfKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static HmacPrfKeyFormat parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (HmacPrfKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<HmacPrfKeyFormat> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    public final void A() {
        this.version_ = 0;
    }

    public final void B(HmacPrfParams hmacPrfParams) {
        hmacPrfParams.getClass();
        HmacPrfParams hmacPrfParams2 = this.params_;
        if (hmacPrfParams2 != null && hmacPrfParams2 != HmacPrfParams.getDefaultInstance()) {
            this.params_ = HmacPrfParams.newBuilder(this.params_).mergeFrom((HmacPrfParams.Builder) hmacPrfParams).buildPartial();
        } else {
            this.params_ = hmacPrfParams;
        }
    }

    public final void C(int i) {
        this.keySize_ = i;
    }

    public final void D(HmacPrfParams hmacPrfParams) {
        hmacPrfParams.getClass();
        this.params_ = hmacPrfParams;
    }

    public final void E(int i) {
        this.version_ = i;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (a.f10917a[methodToInvoke.ordinal()]) {
            case 1:
                return new HmacPrfKeyFormat();
            case 2:
                return new Builder(null);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\u000b\u0003\u000b", new Object[]{"params_", "keySize_", "version_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<HmacPrfKeyFormat> parser = PARSER;
                if (parser == null) {
                    synchronized (HmacPrfKeyFormat.class) {
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

    @Override // com.google.crypto.tink.proto.HmacPrfKeyFormatOrBuilder
    public int getKeySize() {
        return this.keySize_;
    }

    @Override // com.google.crypto.tink.proto.HmacPrfKeyFormatOrBuilder
    public HmacPrfParams getParams() {
        HmacPrfParams hmacPrfParams = this.params_;
        return hmacPrfParams == null ? HmacPrfParams.getDefaultInstance() : hmacPrfParams;
    }

    @Override // com.google.crypto.tink.proto.HmacPrfKeyFormatOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override // com.google.crypto.tink.proto.HmacPrfKeyFormatOrBuilder
    public boolean hasParams() {
        return this.params_ != null;
    }

    public final void y() {
        this.keySize_ = 0;
    }

    public final void z() {
        this.params_ = null;
    }

    public static Builder newBuilder(HmacPrfKeyFormat hmacPrfKeyFormat) {
        return DEFAULT_INSTANCE.createBuilder(hmacPrfKeyFormat);
    }

    public static HmacPrfKeyFormat parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HmacPrfKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static HmacPrfKeyFormat parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HmacPrfKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static HmacPrfKeyFormat parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (HmacPrfKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static HmacPrfKeyFormat parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HmacPrfKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static HmacPrfKeyFormat parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (HmacPrfKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static HmacPrfKeyFormat parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HmacPrfKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static HmacPrfKeyFormat parseFrom(InputStream inputStream) throws IOException {
        return (HmacPrfKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static HmacPrfKeyFormat parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HmacPrfKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static HmacPrfKeyFormat parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (HmacPrfKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static HmacPrfKeyFormat parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HmacPrfKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
