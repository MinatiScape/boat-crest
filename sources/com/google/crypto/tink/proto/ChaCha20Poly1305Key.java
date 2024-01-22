package com.google.crypto.tink.proto;

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
public final class ChaCha20Poly1305Key extends GeneratedMessageLite<ChaCha20Poly1305Key, Builder> implements ChaCha20Poly1305KeyOrBuilder {
    private static final ChaCha20Poly1305Key DEFAULT_INSTANCE;
    public static final int KEY_VALUE_FIELD_NUMBER = 2;
    private static volatile Parser<ChaCha20Poly1305Key> PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private ByteString keyValue_ = ByteString.EMPTY;
    private int version_;

    /* loaded from: classes10.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<ChaCha20Poly1305Key, Builder> implements ChaCha20Poly1305KeyOrBuilder {
        public /* synthetic */ Builder(a aVar) {
            this();
        }

        public Builder clearKeyValue() {
            copyOnWrite();
            ((ChaCha20Poly1305Key) this.instance).v();
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((ChaCha20Poly1305Key) this.instance).w();
            return this;
        }

        @Override // com.google.crypto.tink.proto.ChaCha20Poly1305KeyOrBuilder
        public ByteString getKeyValue() {
            return ((ChaCha20Poly1305Key) this.instance).getKeyValue();
        }

        @Override // com.google.crypto.tink.proto.ChaCha20Poly1305KeyOrBuilder
        public int getVersion() {
            return ((ChaCha20Poly1305Key) this.instance).getVersion();
        }

        public Builder setKeyValue(ByteString byteString) {
            copyOnWrite();
            ((ChaCha20Poly1305Key) this.instance).x(byteString);
            return this;
        }

        public Builder setVersion(int i) {
            copyOnWrite();
            ((ChaCha20Poly1305Key) this.instance).y(i);
            return this;
        }

        public Builder() {
            super(ChaCha20Poly1305Key.DEFAULT_INSTANCE);
        }
    }

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10890a;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f10890a = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10890a[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10890a[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10890a[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f10890a[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f10890a[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f10890a[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    static {
        ChaCha20Poly1305Key chaCha20Poly1305Key = new ChaCha20Poly1305Key();
        DEFAULT_INSTANCE = chaCha20Poly1305Key;
        GeneratedMessageLite.registerDefaultInstance(ChaCha20Poly1305Key.class, chaCha20Poly1305Key);
    }

    public static ChaCha20Poly1305Key getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static ChaCha20Poly1305Key parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ChaCha20Poly1305Key) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ChaCha20Poly1305Key parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ChaCha20Poly1305Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<ChaCha20Poly1305Key> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (a.f10890a[methodToInvoke.ordinal()]) {
            case 1:
                return new ChaCha20Poly1305Key();
            case 2:
                return new Builder(null);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\n", new Object[]{"version_", "keyValue_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<ChaCha20Poly1305Key> parser = PARSER;
                if (parser == null) {
                    synchronized (ChaCha20Poly1305Key.class) {
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

    @Override // com.google.crypto.tink.proto.ChaCha20Poly1305KeyOrBuilder
    public ByteString getKeyValue() {
        return this.keyValue_;
    }

    @Override // com.google.crypto.tink.proto.ChaCha20Poly1305KeyOrBuilder
    public int getVersion() {
        return this.version_;
    }

    public final void v() {
        this.keyValue_ = getDefaultInstance().getKeyValue();
    }

    public final void w() {
        this.version_ = 0;
    }

    public final void x(ByteString byteString) {
        byteString.getClass();
        this.keyValue_ = byteString;
    }

    public final void y(int i) {
        this.version_ = i;
    }

    public static Builder newBuilder(ChaCha20Poly1305Key chaCha20Poly1305Key) {
        return DEFAULT_INSTANCE.createBuilder(chaCha20Poly1305Key);
    }

    public static ChaCha20Poly1305Key parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ChaCha20Poly1305Key) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ChaCha20Poly1305Key parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ChaCha20Poly1305Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ChaCha20Poly1305Key parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ChaCha20Poly1305Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ChaCha20Poly1305Key parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ChaCha20Poly1305Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ChaCha20Poly1305Key parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ChaCha20Poly1305Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ChaCha20Poly1305Key parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ChaCha20Poly1305Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ChaCha20Poly1305Key parseFrom(InputStream inputStream) throws IOException {
        return (ChaCha20Poly1305Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ChaCha20Poly1305Key parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ChaCha20Poly1305Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ChaCha20Poly1305Key parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ChaCha20Poly1305Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ChaCha20Poly1305Key parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ChaCha20Poly1305Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
