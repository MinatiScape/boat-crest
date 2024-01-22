package a;

import androidx.exifinterface.media.ExifInterface;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public final class c {
    public static final Descriptors.Descriptor A;
    public static final GeneratedMessageV3.FieldAccessorTable B;
    public static final Descriptors.Descriptor C;
    public static final GeneratedMessageV3.FieldAccessorTable D;
    public static final Descriptors.Descriptor E;
    public static final GeneratedMessageV3.FieldAccessorTable F;
    public static final Descriptors.Descriptor G;
    public static final GeneratedMessageV3.FieldAccessorTable H;
    public static final Descriptors.Descriptor I;
    public static final GeneratedMessageV3.FieldAccessorTable J;
    public static final Descriptors.Descriptor K;
    public static final GeneratedMessageV3.FieldAccessorTable L;
    public static final Descriptors.Descriptor M;
    public static final GeneratedMessageV3.FieldAccessorTable N;
    public static final Descriptors.Descriptor O;
    public static final GeneratedMessageV3.FieldAccessorTable P;
    public static final Descriptors.Descriptor Q;
    public static final GeneratedMessageV3.FieldAccessorTable R;
    public static final Descriptors.Descriptor S;
    public static final GeneratedMessageV3.FieldAccessorTable T;
    public static final Descriptors.Descriptor U;
    public static final GeneratedMessageV3.FieldAccessorTable V;
    public static Descriptors.FileDescriptor W;

    /* renamed from: a  reason: collision with root package name */
    public static final Descriptors.Descriptor f208a;
    public static final GeneratedMessageV3.FieldAccessorTable b;
    public static final Descriptors.Descriptor c;
    public static final GeneratedMessageV3.FieldAccessorTable d;
    public static final Descriptors.Descriptor e;
    public static final GeneratedMessageV3.FieldAccessorTable f;
    public static final Descriptors.Descriptor g;
    public static final GeneratedMessageV3.FieldAccessorTable h;
    public static final Descriptors.Descriptor i;
    public static final GeneratedMessageV3.FieldAccessorTable j;
    public static final Descriptors.Descriptor k;
    public static final GeneratedMessageV3.FieldAccessorTable l;
    public static final Descriptors.Descriptor m;
    public static final GeneratedMessageV3.FieldAccessorTable n;
    public static final Descriptors.Descriptor o;
    public static final GeneratedMessageV3.FieldAccessorTable p;
    public static final Descriptors.Descriptor q;
    public static final GeneratedMessageV3.FieldAccessorTable r;
    public static final Descriptors.Descriptor s;
    public static final GeneratedMessageV3.FieldAccessorTable t;
    public static final Descriptors.Descriptor u;
    public static final GeneratedMessageV3.FieldAccessorTable v;
    public static final Descriptors.Descriptor w;
    public static final GeneratedMessageV3.FieldAccessorTable x;
    public static final Descriptors.Descriptor y;
    public static final GeneratedMessageV3.FieldAccessorTable z;

    /* loaded from: classes.dex */
    public enum a implements ProtocolMessageEnum {
        begin(0),
        proceed(1),
        end(2),
        begin_end(3),
        UNRECOGNIZED(-1);
        

        /* renamed from: a  reason: collision with root package name */
        public final int f209a;

        static {
            values();
        }

        a(int i) {
            this.f209a = i;
        }

        public static a a(int i) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            return null;
                        }
                        return begin_end;
                    }
                    return end;
                }
                return proceed;
            }
            return begin;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return c.W.getEnumTypes().get(0);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.f209a;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            if (this != UNRECOGNIZED) {
                return c.W.getEnumTypes().get(0).getValues().get(ordinal());
            }
            throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
        }
    }

    /* loaded from: classes.dex */
    public static final class b extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final b d = new b();
        public static final Parser<b> e = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f210a;
        public int b;
        public byte c;

        /* loaded from: classes.dex */
        public class a extends AbstractParser<b> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new b(codedInputStream, extensionRegistryLite);
            }
        }

        /* renamed from: a.c$b$b  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0079b extends GeneratedMessageV3.Builder<C0079b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f211a;
            public int b;

            public C0079b() {
                this.b = 0;
                d();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.c.b.C0079b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.c$b> r1 = a.c.b.e     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.c$b r3 = (a.c.b) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.a(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    a.c$b r4 = (a.c.b) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.a(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: a.c.b.C0079b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.c$b$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0079b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public b buildPartial() {
                b bVar = new b(this);
                bVar.f210a = this.f211a;
                bVar.b = this.b;
                onBuilt();
                return bVar;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: c */
            public C0079b clear() {
                super.clear();
                this.f211a = 0;
                this.b = 0;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (C0079b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0079b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (C0079b) super.mo0clone();
            }

            public final void d() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return b.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return c.f208a;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return c.b.ensureFieldAccessorsInitialized(b.class, C0079b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0079b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0079b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0079b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0079b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0079b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (C0079b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0079b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (C0079b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return b.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0079b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0079b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0079b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0079b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0079b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (C0079b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0079b) super.mergeUnknownFields(unknownFieldSet);
            }

            public C0079b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.b = 0;
                d();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clone() {
                return (C0079b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (C0079b) super.mo0clone();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeFrom(Message message) {
                if (message instanceof b) {
                    return a((b) message);
                }
                super.mergeFrom(message);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Object mo0clone() {
                return (C0079b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public b build() {
                b buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            public C0079b b(int i) {
                this.f211a = i;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof b) {
                    return a((b) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public C0079b a(int i) {
                this.b = i;
                onChanged();
                return this;
            }

            public C0079b a(b bVar) {
                if (bVar == b.d) {
                    return this;
                }
                int i = bVar.f210a;
                if (i != 0) {
                    this.f211a = i;
                    onChanged();
                }
                int i2 = bVar.b;
                if (i2 != 0) {
                    this.b = i2;
                    onChanged();
                }
                C0079b c0079b = (C0079b) super.mergeUnknownFields(bVar.unknownFields);
                onChanged();
                return this;
            }
        }

        public b(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.c = (byte) -1;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: b */
        public C0079b toBuilder() {
            if (this == d) {
                return new C0079b();
            }
            return new C0079b().a(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof b)) {
                return super.equals(obj);
            }
            b bVar = (b) obj;
            return this.f210a == bVar.f210a && this.b == bVar.b && this.unknownFields.equals(bVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<b> getParserForType() {
            return e;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.f210a;
            int computeUInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeUInt32Size(1, i2) : 0;
            if (this.b != a.begin.getNumber()) {
                computeUInt32Size += CodedOutputStream.computeEnumSize(2, this.b);
            }
            int serializedSize = computeUInt32Size + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = ((((((((((c.f208a.hashCode() + 779) * 37) + 1) * 53) + this.f210a) * 37) + 2) * 53) + this.b) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return c.b.ensureFieldAccessorsInitialized(b.class, C0079b.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.c;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.c = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Message.Builder newBuilderForType() {
            return d.toBuilder();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new b();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            int i = this.f210a;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            if (this.b != a.begin.getNumber()) {
                codedOutputStream.writeEnum(2, this.b);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public MessageLite.Builder newBuilderForType() {
            return d.toBuilder();
        }

        public b() {
            this.c = (byte) -1;
            this.b = 0;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new C0079b(builderParent);
        }

        public b(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            this();
            Objects.requireNonNull(extensionRegistryLite);
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.f210a = codedInputStream.readUInt32();
                                } else if (readTag != 16) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.b = codedInputStream.readEnum();
                                }
                            }
                            z = true;
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e3) {
                        throw e3.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }
    }

    /* renamed from: a.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0080c extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final C0080c d = new C0080c();
        public static final Parser<C0080c> e = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f212a;
        public List<C0081c> b;
        public byte c;

        /* renamed from: a.c$c$a */
        /* loaded from: classes.dex */
        public class a extends AbstractParser<C0080c> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new C0080c(codedInputStream, extensionRegistryLite);
            }
        }

        /* renamed from: a.c$c$b */
        /* loaded from: classes.dex */
        public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f213a;
            public int b;
            public List<C0081c> c;
            public RepeatedFieldBuilderV3<C0081c, C0081c.b, Object> d;

            public b() {
                this.b = 0;
                this.c = Collections.emptyList();
                d();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.c.C0080c.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.c$c> r1 = a.c.C0080c.e     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.c$c r3 = (a.c.C0080c) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.a(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    a.c$c r4 = (a.c.C0080c) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.a(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: a.c.C0080c.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.c$c$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public b clear() {
                super.clear();
                this.b = 0;
                RepeatedFieldBuilderV3<C0081c, C0081c.b, Object> repeatedFieldBuilderV3 = this.d;
                if (repeatedFieldBuilderV3 == null) {
                    this.c = Collections.emptyList();
                    this.f213a &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message build() {
                C0080c buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            public final RepeatedFieldBuilderV3<C0081c, C0081c.b, Object> c() {
                if (this.d == null) {
                    this.d = new RepeatedFieldBuilderV3<>(this.c, (this.f213a & 1) != 0, getParentForChildren(), isClean());
                    this.c = null;
                }
                return this.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (b) super.mo0clone();
            }

            public final void d() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    c();
                }
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return C0080c.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return c.w;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return c.x.ensureFieldAccessorsInitialized(C0080c.class, b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return C0080c.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite build() {
                C0080c buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clone() {
                return (b) super.mo0clone();
            }

            public b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.b = 0;
                this.c = Collections.emptyList();
                d();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeFrom(Message message) {
                if (message instanceof C0080c) {
                    return a((C0080c) message);
                }
                super.mergeFrom(message);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Object mo0clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public C0080c buildPartial() {
                C0080c c0080c = new C0080c(this);
                int i = this.f213a;
                c0080c.f212a = this.b;
                RepeatedFieldBuilderV3<C0081c, C0081c.b, Object> repeatedFieldBuilderV3 = this.d;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) != 0) {
                        this.c = Collections.unmodifiableList(this.c);
                        this.f213a &= -2;
                    }
                    c0080c.b = this.c;
                } else {
                    c0080c.b = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return c0080c;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof C0080c) {
                    return a((C0080c) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public b a(C0080c c0080c) {
                if (c0080c == C0080c.d) {
                    return this;
                }
                int i = c0080c.f212a;
                if (i != 0) {
                    this.b = i;
                    onChanged();
                }
                if (this.d == null) {
                    if (!c0080c.b.isEmpty()) {
                        if (this.c.isEmpty()) {
                            this.c = c0080c.b;
                            this.f213a &= -2;
                        } else {
                            if ((this.f213a & 1) == 0) {
                                this.c = new ArrayList(this.c);
                                this.f213a |= 1;
                            }
                            this.c.addAll(c0080c.b);
                        }
                        onChanged();
                    }
                } else if (!c0080c.b.isEmpty()) {
                    if (this.d.isEmpty()) {
                        this.d.dispose();
                        this.d = null;
                        this.c = c0080c.b;
                        this.f213a &= -2;
                        this.d = GeneratedMessageV3.alwaysUseFieldBuilders ? c() : null;
                    } else {
                        this.d.addAllMessages(c0080c.b);
                    }
                }
                b bVar = (b) super.mergeUnknownFields(c0080c.unknownFields);
                onChanged();
                return this;
            }
        }

        /* renamed from: a.c$c$c  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0081c extends GeneratedMessageV3 implements MessageOrBuilder {
            public static final C0081c d = new C0081c();
            public static final Parser<C0081c> e = new a();

            /* renamed from: a  reason: collision with root package name */
            public int f214a;
            public int b;
            public byte c;

            /* renamed from: a.c$c$c$a */
            /* loaded from: classes.dex */
            public class a extends AbstractParser<C0081c> {
                @Override // com.google.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    return new C0081c(codedInputStream, extensionRegistryLite);
                }
            }

            /* renamed from: a.c$c$c$b */
            /* loaded from: classes.dex */
            public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

                /* renamed from: a  reason: collision with root package name */
                public int f215a;
                public int b;

                public b() {
                    c();
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public a.c.C0080c.C0081c.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser<a.c$c$c> r1 = a.c.C0080c.C0081c.e     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        a.c$c$c r3 = (a.c.C0080c.C0081c) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        if (r3 == 0) goto Le
                        r2.a(r3)
                    Le:
                        return r2
                    Lf:
                        r3 = move-exception
                        goto L1f
                    L11:
                        r3 = move-exception
                        com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                        a.c$c$c r4 = (a.c.C0080c.C0081c) r4     // Catch: java.lang.Throwable -> Lf
                        java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                        throw r3     // Catch: java.lang.Throwable -> L1d
                    L1d:
                        r3 = move-exception
                        r0 = r4
                    L1f:
                        if (r0 == 0) goto L24
                        r2.a(r0)
                    L24:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: a.c.C0080c.C0081c.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.c$c$c$b");
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: b */
                public b clear() {
                    super.clear();
                    this.f215a = 0;
                    this.b = 0;
                    return this;
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Message build() {
                    C0081c buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
                }

                public final void c() {
                    boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (b) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public Message getDefaultInstanceForType() {
                    return C0081c.d;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return c.y;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return c.z.ensureFieldAccessorsInitialized(C0081c.class, b.class);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (b) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.setUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (b) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public AbstractMessageLite.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public MessageLite getDefaultInstanceForType() {
                    return C0081c.d;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (b) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.setUnknownFields(unknownFieldSet);
                }

                public b(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    c();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public MessageLite build() {
                    C0081c buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public MessageLite.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder mergeFrom(Message message) {
                    if (message instanceof C0081c) {
                        return a((C0081c) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone */
                public Object mo0clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a */
                public C0081c buildPartial() {
                    C0081c c0081c = new C0081c(this);
                    c0081c.f214a = this.f215a;
                    c0081c.b = this.b;
                    onBuilt();
                    return c0081c;
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder mergeFrom(Message message) {
                    if (message instanceof C0081c) {
                        return a((C0081c) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public b a(C0081c c0081c) {
                    if (c0081c == C0081c.d) {
                        return this;
                    }
                    int i = c0081c.f214a;
                    if (i != 0) {
                        this.f215a = i;
                        onChanged();
                    }
                    int i2 = c0081c.b;
                    if (i2 != 0) {
                        this.b = i2;
                        onChanged();
                    }
                    b bVar = (b) super.mergeUnknownFields(c0081c.unknownFields);
                    onChanged();
                    return this;
                }
            }

            public C0081c(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.c = (byte) -1;
            }

            public static Parser<C0081c> b() {
                return e;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: c */
            public b toBuilder() {
                if (this == d) {
                    return new b();
                }
                return new b().a(this);
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof C0081c)) {
                    return super.equals(obj);
                }
                C0081c c0081c = (C0081c) obj;
                return this.f214a == c0081c.f214a && this.b == c0081c.b && this.unknownFields.equals(c0081c.unknownFields);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<C0081c> getParserForType() {
                return e;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = this.f214a;
                int computeUInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeUInt32Size(1, i2) : 0;
                int i3 = this.b;
                if (i3 != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(2, i3);
                }
                int serializedSize = computeUInt32Size + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                int i = this.memoizedHashCode;
                if (i != 0) {
                    return i;
                }
                int hashCode = ((((((((((c.y.hashCode() + 779) * 37) + 1) * 53) + this.f214a) * 37) + 2) * 53) + this.b) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode;
                return hashCode;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return c.z.ensureFieldAccessorsInitialized(C0081c.class, b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                byte b2 = this.c;
                if (b2 == 1) {
                    return true;
                }
                if (b2 == 0) {
                    return false;
                }
                this.c = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Message.Builder newBuilderForType() {
                return d.toBuilder();
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new C0081c();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) {
                int i = this.f214a;
                if (i != 0) {
                    codedOutputStream.writeUInt32(1, i);
                }
                int i2 = this.b;
                if (i2 != 0) {
                    codedOutputStream.writeUInt32(2, i2);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return d;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public MessageLite.Builder newBuilderForType() {
                return d.toBuilder();
            }

            public C0081c() {
                this.c = (byte) -1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new b(builderParent);
            }

            public C0081c(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this();
                Objects.requireNonNull(extensionRegistryLite);
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag == 8) {
                                        this.f214a = codedInputStream.readUInt32();
                                    } else if (readTag != 16) {
                                        if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        }
                                    } else {
                                        this.b = codedInputStream.readUInt32();
                                    }
                                }
                                z = true;
                            } catch (IOException e2) {
                                throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                            }
                        } catch (InvalidProtocolBufferException e3) {
                            throw e3.setUnfinishedMessage(this);
                        }
                    } finally {
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }
        }

        public C0080c(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.c = (byte) -1;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: c */
        public b toBuilder() {
            if (this == d) {
                return new b();
            }
            return new b().a(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof C0080c)) {
                return super.equals(obj);
            }
            C0080c c0080c = (C0080c) obj;
            return this.f212a == c0080c.f212a && this.b.equals(c0080c.b) && this.unknownFields.equals(c0080c.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<C0080c> getParserForType() {
            return e;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = this.f212a != a.begin.getNumber() ? CodedOutputStream.computeEnumSize(1, this.f212a) + 0 : 0;
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                computeEnumSize += CodedOutputStream.computeMessageSize(2, this.b.get(i2));
            }
            int serializedSize = computeEnumSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = ((((c.w.hashCode() + 779) * 37) + 1) * 53) + this.f212a;
            if (this.b.size() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + this.b.hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return c.x.ensureFieldAccessorsInitialized(C0080c.class, b.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b2 = this.c;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            this.c = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Message.Builder newBuilderForType() {
            return d.toBuilder();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new C0080c();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            if (this.f212a != a.begin.getNumber()) {
                codedOutputStream.writeEnum(1, this.f212a);
            }
            for (int i = 0; i < this.b.size(); i++) {
                codedOutputStream.writeMessage(2, this.b.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public MessageLite.Builder newBuilderForType() {
            return d.toBuilder();
        }

        public C0080c() {
            this.c = (byte) -1;
            this.f212a = 0;
            this.b = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new b(builderParent);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public C0080c(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            this();
            Objects.requireNonNull(extensionRegistryLite);
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.f212a = codedInputStream.readEnum();
                            } else if (readTag != 18) {
                                if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                if (!(z2 & true)) {
                                    this.b = new ArrayList();
                                    z2 |= true;
                                }
                                this.b.add(codedInputStream.readMessage(C0081c.b(), extensionRegistryLite));
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    } catch (IOException e3) {
                        throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(this);
                    }
                } finally {
                    if (z2 & true) {
                        this.b = Collections.unmodifiableList(this.b);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class d extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final d d = new d();
        public static final Parser<d> e = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f216a;
        public List<C0082c> b;
        public byte c;

        /* loaded from: classes.dex */
        public class a extends AbstractParser<d> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new d(codedInputStream, extensionRegistryLite);
            }
        }

        /* loaded from: classes.dex */
        public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f217a;
            public int b;
            public List<C0082c> c;
            public RepeatedFieldBuilderV3<C0082c, C0082c.b, Object> d;

            public b() {
                this.b = 0;
                this.c = Collections.emptyList();
                d();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.c.d.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.c$d> r1 = a.c.d.e     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.c$d r3 = (a.c.d) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.a(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    a.c$d r4 = (a.c.d) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.a(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: a.c.d.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.c$d$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public b clear() {
                super.clear();
                this.b = 0;
                RepeatedFieldBuilderV3<C0082c, C0082c.b, Object> repeatedFieldBuilderV3 = this.d;
                if (repeatedFieldBuilderV3 == null) {
                    this.c = Collections.emptyList();
                    this.f217a &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message build() {
                d buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            public final RepeatedFieldBuilderV3<C0082c, C0082c.b, Object> c() {
                if (this.d == null) {
                    this.d = new RepeatedFieldBuilderV3<>(this.c, (this.f217a & 1) != 0, getParentForChildren(), isClean());
                    this.c = null;
                }
                return this.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (b) super.mo0clone();
            }

            public final void d() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    c();
                }
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return d.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return c.o;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return c.p.ensureFieldAccessorsInitialized(d.class, b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return d.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite build() {
                d buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clone() {
                return (b) super.mo0clone();
            }

            public b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.b = 0;
                this.c = Collections.emptyList();
                d();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeFrom(Message message) {
                if (message instanceof d) {
                    return a((d) message);
                }
                super.mergeFrom(message);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Object mo0clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public d buildPartial() {
                d dVar = new d(this);
                int i = this.f217a;
                dVar.f216a = this.b;
                RepeatedFieldBuilderV3<C0082c, C0082c.b, Object> repeatedFieldBuilderV3 = this.d;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) != 0) {
                        this.c = Collections.unmodifiableList(this.c);
                        this.f217a &= -2;
                    }
                    dVar.b = this.c;
                } else {
                    dVar.b = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return dVar;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof d) {
                    return a((d) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public b a(d dVar) {
                if (dVar == d.d) {
                    return this;
                }
                int i = dVar.f216a;
                if (i != 0) {
                    this.b = i;
                    onChanged();
                }
                if (this.d == null) {
                    if (!dVar.b.isEmpty()) {
                        if (this.c.isEmpty()) {
                            this.c = dVar.b;
                            this.f217a &= -2;
                        } else {
                            if ((this.f217a & 1) == 0) {
                                this.c = new ArrayList(this.c);
                                this.f217a |= 1;
                            }
                            this.c.addAll(dVar.b);
                        }
                        onChanged();
                    }
                } else if (!dVar.b.isEmpty()) {
                    if (this.d.isEmpty()) {
                        this.d.dispose();
                        this.d = null;
                        this.c = dVar.b;
                        this.f217a &= -2;
                        this.d = GeneratedMessageV3.alwaysUseFieldBuilders ? c() : null;
                    } else {
                        this.d.addAllMessages(dVar.b);
                    }
                }
                b bVar = (b) super.mergeUnknownFields(dVar.unknownFields);
                onChanged();
                return this;
            }
        }

        /* renamed from: a.c$d$c  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0082c extends GeneratedMessageV3 implements MessageOrBuilder {
            public static final C0082c e = new C0082c();
            public static final Parser<C0082c> f = new a();

            /* renamed from: a  reason: collision with root package name */
            public int f218a;
            public double b;
            public double c;
            public byte d;

            /* renamed from: a.c$d$c$a */
            /* loaded from: classes.dex */
            public class a extends AbstractParser<C0082c> {
                @Override // com.google.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    return new C0082c(codedInputStream, extensionRegistryLite);
                }
            }

            /* renamed from: a.c$d$c$b */
            /* loaded from: classes.dex */
            public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

                /* renamed from: a  reason: collision with root package name */
                public int f219a;
                public double b;
                public double c;

                public b() {
                    c();
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public a.c.d.C0082c.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser<a.c$d$c> r1 = a.c.d.C0082c.f     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        a.c$d$c r3 = (a.c.d.C0082c) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        if (r3 == 0) goto Le
                        r2.a(r3)
                    Le:
                        return r2
                    Lf:
                        r3 = move-exception
                        goto L1f
                    L11:
                        r3 = move-exception
                        com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                        a.c$d$c r4 = (a.c.d.C0082c) r4     // Catch: java.lang.Throwable -> Lf
                        java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                        throw r3     // Catch: java.lang.Throwable -> L1d
                    L1d:
                        r3 = move-exception
                        r0 = r4
                    L1f:
                        if (r0 == 0) goto L24
                        r2.a(r0)
                    L24:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: a.c.d.C0082c.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.c$d$c$b");
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: b */
                public b clear() {
                    super.clear();
                    this.f219a = 0;
                    this.b = 0.0d;
                    this.c = 0.0d;
                    return this;
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Message build() {
                    C0082c buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
                }

                public final void c() {
                    boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (b) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public Message getDefaultInstanceForType() {
                    return C0082c.e;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return c.q;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return c.r.ensureFieldAccessorsInitialized(C0082c.class, b.class);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (b) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.setUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (b) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public AbstractMessageLite.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public MessageLite getDefaultInstanceForType() {
                    return C0082c.e;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (b) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.setUnknownFields(unknownFieldSet);
                }

                public b(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    c();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public MessageLite build() {
                    C0082c buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public MessageLite.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder mergeFrom(Message message) {
                    if (message instanceof C0082c) {
                        return a((C0082c) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone */
                public Object mo0clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a */
                public C0082c buildPartial() {
                    C0082c c0082c = new C0082c(this);
                    c0082c.f218a = this.f219a;
                    c0082c.b = this.b;
                    c0082c.c = this.c;
                    onBuilt();
                    return c0082c;
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder mergeFrom(Message message) {
                    if (message instanceof C0082c) {
                        return a((C0082c) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public b a(C0082c c0082c) {
                    if (c0082c == C0082c.e) {
                        return this;
                    }
                    int i = c0082c.f218a;
                    if (i != 0) {
                        this.f219a = i;
                        onChanged();
                    }
                    double d = c0082c.b;
                    if (d != 0.0d) {
                        this.b = d;
                        onChanged();
                    }
                    double d2 = c0082c.c;
                    if (d2 != 0.0d) {
                        this.c = d2;
                        onChanged();
                    }
                    b bVar = (b) super.mergeUnknownFields(c0082c.unknownFields);
                    onChanged();
                    return this;
                }
            }

            public C0082c(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.d = (byte) -1;
            }

            public static Parser<C0082c> b() {
                return f;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: c */
            public b toBuilder() {
                if (this == e) {
                    return new b();
                }
                return new b().a(this);
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof C0082c)) {
                    return super.equals(obj);
                }
                C0082c c0082c = (C0082c) obj;
                return this.f218a == c0082c.f218a && Double.doubleToLongBits(this.b) == Double.doubleToLongBits(c0082c.b) && Double.doubleToLongBits(this.c) == Double.doubleToLongBits(c0082c.c) && this.unknownFields.equals(c0082c.unknownFields);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return e;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<C0082c> getParserForType() {
                return f;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = this.f218a;
                int computeUInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeUInt32Size(1, i2) : 0;
                double d = this.b;
                if (d != 0.0d) {
                    computeUInt32Size += CodedOutputStream.computeDoubleSize(2, d);
                }
                double d2 = this.c;
                if (d2 != 0.0d) {
                    computeUInt32Size += CodedOutputStream.computeDoubleSize(3, d2);
                }
                int serializedSize = computeUInt32Size + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                int i = this.memoizedHashCode;
                if (i != 0) {
                    return i;
                }
                int hashCode = ((((((((((((((c.q.hashCode() + 779) * 37) + 1) * 53) + this.f218a) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(this.b))) * 37) + 3) * 53) + Internal.hashLong(Double.doubleToLongBits(this.c))) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode;
                return hashCode;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return c.r.ensureFieldAccessorsInitialized(C0082c.class, b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                byte b2 = this.d;
                if (b2 == 1) {
                    return true;
                }
                if (b2 == 0) {
                    return false;
                }
                this.d = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Message.Builder newBuilderForType() {
                return e.toBuilder();
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new C0082c();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) {
                int i = this.f218a;
                if (i != 0) {
                    codedOutputStream.writeUInt32(1, i);
                }
                double d = this.b;
                if (d != 0.0d) {
                    codedOutputStream.writeDouble(2, d);
                }
                double d2 = this.c;
                if (d2 != 0.0d) {
                    codedOutputStream.writeDouble(3, d2);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return e;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public MessageLite.Builder newBuilderForType() {
                return e.toBuilder();
            }

            public C0082c() {
                this.d = (byte) -1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new b(builderParent);
            }

            public C0082c(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this();
                Objects.requireNonNull(extensionRegistryLite);
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.f218a = codedInputStream.readUInt32();
                                } else if (readTag == 17) {
                                    this.b = codedInputStream.readDouble();
                                } else if (readTag != 25) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.c = codedInputStream.readDouble();
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e2) {
                            throw e2.setUnfinishedMessage(this);
                        } catch (IOException e3) {
                            throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(this);
                        }
                    } finally {
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }
        }

        public d(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.c = (byte) -1;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: c */
        public b toBuilder() {
            if (this == d) {
                return new b();
            }
            return new b().a(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof d)) {
                return super.equals(obj);
            }
            d dVar = (d) obj;
            return this.f216a == dVar.f216a && this.b.equals(dVar.b) && this.unknownFields.equals(dVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<d> getParserForType() {
            return e;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = this.f216a != a.begin.getNumber() ? CodedOutputStream.computeEnumSize(1, this.f216a) + 0 : 0;
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                computeEnumSize += CodedOutputStream.computeMessageSize(2, this.b.get(i2));
            }
            int serializedSize = computeEnumSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = ((((c.o.hashCode() + 779) * 37) + 1) * 53) + this.f216a;
            if (this.b.size() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + this.b.hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return c.p.ensureFieldAccessorsInitialized(d.class, b.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b2 = this.c;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            this.c = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Message.Builder newBuilderForType() {
            return d.toBuilder();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new d();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            if (this.f216a != a.begin.getNumber()) {
                codedOutputStream.writeEnum(1, this.f216a);
            }
            for (int i = 0; i < this.b.size(); i++) {
                codedOutputStream.writeMessage(2, this.b.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public MessageLite.Builder newBuilderForType() {
            return d.toBuilder();
        }

        public d() {
            this.c = (byte) -1;
            this.f216a = 0;
            this.b = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new b(builderParent);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public d(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            this();
            Objects.requireNonNull(extensionRegistryLite);
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.f216a = codedInputStream.readEnum();
                            } else if (readTag != 18) {
                                if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                if (!(z2 & true)) {
                                    this.b = new ArrayList();
                                    z2 |= true;
                                }
                                this.b.add(codedInputStream.readMessage(C0082c.b(), extensionRegistryLite));
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    } catch (IOException e3) {
                        throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(this);
                    }
                } finally {
                    if (z2 & true) {
                        this.b = Collections.unmodifiableList(this.b);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class e extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final e d = new e();
        public static final Parser<e> e = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f220a;
        public List<C0084e> b;
        public byte c;

        /* loaded from: classes.dex */
        public class a extends AbstractParser<e> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new e(codedInputStream, extensionRegistryLite);
            }
        }

        /* loaded from: classes.dex */
        public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f221a;
            public int b;
            public List<C0084e> c;
            public RepeatedFieldBuilderV3<C0084e, C0084e.b, Object> d;

            public b() {
                this.b = 0;
                this.c = Collections.emptyList();
                d();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.c.e.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.c$e> r1 = a.c.e.e     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.c$e r3 = (a.c.e) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.a(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    a.c$e r4 = (a.c.e) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.a(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: a.c.e.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.c$e$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public b clear() {
                super.clear();
                this.b = 0;
                RepeatedFieldBuilderV3<C0084e, C0084e.b, Object> repeatedFieldBuilderV3 = this.d;
                if (repeatedFieldBuilderV3 == null) {
                    this.c = Collections.emptyList();
                    this.f221a &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message build() {
                e buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            public final RepeatedFieldBuilderV3<C0084e, C0084e.b, Object> c() {
                if (this.d == null) {
                    this.d = new RepeatedFieldBuilderV3<>(this.c, (this.f221a & 1) != 0, getParentForChildren(), isClean());
                    this.c = null;
                }
                return this.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (b) super.mo0clone();
            }

            public final void d() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    c();
                }
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return e.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return c.Q;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return c.R.ensureFieldAccessorsInitialized(e.class, b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return e.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite build() {
                e buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clone() {
                return (b) super.mo0clone();
            }

            public b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.b = 0;
                this.c = Collections.emptyList();
                d();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeFrom(Message message) {
                if (message instanceof e) {
                    return a((e) message);
                }
                super.mergeFrom(message);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Object mo0clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public e buildPartial() {
                e eVar = new e(this);
                int i = this.f221a;
                eVar.f220a = this.b;
                RepeatedFieldBuilderV3<C0084e, C0084e.b, Object> repeatedFieldBuilderV3 = this.d;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) != 0) {
                        this.c = Collections.unmodifiableList(this.c);
                        this.f221a &= -2;
                    }
                    eVar.b = this.c;
                } else {
                    eVar.b = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return eVar;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof e) {
                    return a((e) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public b a(e eVar) {
                if (eVar == e.d) {
                    return this;
                }
                int i = eVar.f220a;
                if (i != 0) {
                    this.b = i;
                    onChanged();
                }
                if (this.d == null) {
                    if (!eVar.b.isEmpty()) {
                        if (this.c.isEmpty()) {
                            this.c = eVar.b;
                            this.f221a &= -2;
                        } else {
                            if ((this.f221a & 1) == 0) {
                                this.c = new ArrayList(this.c);
                                this.f221a |= 1;
                            }
                            this.c.addAll(eVar.b);
                        }
                        onChanged();
                    }
                } else if (!eVar.b.isEmpty()) {
                    if (this.d.isEmpty()) {
                        this.d.dispose();
                        this.d = null;
                        this.c = eVar.b;
                        this.f221a &= -2;
                        this.d = GeneratedMessageV3.alwaysUseFieldBuilders ? c() : null;
                    } else {
                        this.d.addAllMessages(eVar.b);
                    }
                }
                b bVar = (b) super.mergeUnknownFields(eVar.unknownFields);
                onChanged();
                return this;
            }
        }

        /* renamed from: a.c$e$c  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public enum EnumC0083c implements ProtocolMessageEnum {
            initial(0),
            in_progress(1),
            completed(2),
            cancel(3),
            UNRECOGNIZED(-1);
            

            /* renamed from: a  reason: collision with root package name */
            public final int f222a;

            static {
                values();
            }

            EnumC0083c(int i) {
                this.f222a = i;
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return e.c().getEnumTypes().get(1);
            }

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.f222a;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                if (this != UNRECOGNIZED) {
                    return e.c().getEnumTypes().get(1).getValues().get(ordinal());
                }
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
        }

        /* loaded from: classes.dex */
        public enum d implements ProtocolMessageEnum {
            study_01(0),
            sleep_02(1),
            study_03(2),
            chores_04(3),
            havefun_05(4),
            drink_06(5),
            sun_07(6),
            teeth_08(7),
            calendar_09(8),
            piano_10(9),
            fruit_11(10),
            medicine_12(11),
            draw_13(12),
            target_14(13),
            dog_15(14),
            exercise_16(15),
            bed_17(16),
            tidyup_18(17),
            UNRECOGNIZED(-1);
            

            /* renamed from: a  reason: collision with root package name */
            public final int f223a;

            static {
                values();
            }

            d(int i) {
                this.f223a = i;
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return e.c().getEnumTypes().get(0);
            }

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.f223a;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                if (this != UNRECOGNIZED) {
                    return e.c().getEnumTypes().get(0).getValues().get(ordinal());
                }
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
        }

        /* renamed from: a.c$e$e  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0084e extends GeneratedMessageV3 implements MessageOrBuilder {
            public static final C0084e m = new C0084e();
            public static final Parser<C0084e> n = new a();

            /* renamed from: a  reason: collision with root package name */
            public int f224a;
            public int b;
            public int c;
            public int d;
            public int e;
            public int f;
            public int g;
            public int h;
            public int i;
            public int j;
            public volatile Object k;
            public byte l;

            /* renamed from: a.c$e$e$a */
            /* loaded from: classes.dex */
            public class a extends AbstractParser<C0084e> {
                @Override // com.google.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    return new C0084e(codedInputStream, extensionRegistryLite);
                }
            }

            /* renamed from: a.c$e$e$b */
            /* loaded from: classes.dex */
            public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

                /* renamed from: a  reason: collision with root package name */
                public int f225a;
                public int b;
                public int c;
                public int d;
                public int e;
                public int f;
                public int g;
                public int h;
                public int i;
                public int j;
                public Object k;

                public b() {
                    this.b = 0;
                    this.j = 0;
                    this.k = "";
                    c();
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public a.c.e.C0084e.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser<a.c$e$e> r1 = a.c.e.C0084e.n     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        a.c$e$e r3 = (a.c.e.C0084e) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        if (r3 == 0) goto Le
                        r2.a(r3)
                    Le:
                        return r2
                    Lf:
                        r3 = move-exception
                        goto L1f
                    L11:
                        r3 = move-exception
                        com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                        a.c$e$e r4 = (a.c.e.C0084e) r4     // Catch: java.lang.Throwable -> Lf
                        java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                        throw r3     // Catch: java.lang.Throwable -> L1d
                    L1d:
                        r3 = move-exception
                        r0 = r4
                    L1f:
                        if (r0 == 0) goto L24
                        r2.a(r0)
                    L24:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: a.c.e.C0084e.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.c$e$e$b");
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: b */
                public b clear() {
                    super.clear();
                    this.f225a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.f = 0;
                    this.g = 0;
                    this.h = 0;
                    this.i = 0;
                    this.j = 0;
                    this.k = "";
                    return this;
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Message build() {
                    C0084e buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
                }

                public final void c() {
                    boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (b) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public Message getDefaultInstanceForType() {
                    return C0084e.m;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return c.S;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return c.T.ensureFieldAccessorsInitialized(C0084e.class, b.class);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (b) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.setUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (b) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public AbstractMessageLite.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public MessageLite getDefaultInstanceForType() {
                    return C0084e.m;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (b) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.setUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public MessageLite build() {
                    C0084e buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public MessageLite.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder mergeFrom(Message message) {
                    if (message instanceof C0084e) {
                        return a((C0084e) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public b(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.b = 0;
                    this.j = 0;
                    this.k = "";
                    c();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone */
                public Object mo0clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a */
                public C0084e buildPartial() {
                    C0084e c0084e = new C0084e(this);
                    c0084e.f224a = this.f225a;
                    c0084e.b = this.b;
                    c0084e.c = this.c;
                    c0084e.d = this.d;
                    c0084e.e = this.e;
                    c0084e.f = this.f;
                    c0084e.g = this.g;
                    c0084e.h = this.h;
                    c0084e.i = this.i;
                    c0084e.j = this.j;
                    c0084e.k = this.k;
                    onBuilt();
                    return c0084e;
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder mergeFrom(Message message) {
                    if (message instanceof C0084e) {
                        return a((C0084e) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public b a(C0084e c0084e) {
                    if (c0084e == C0084e.m) {
                        return this;
                    }
                    int i = c0084e.f224a;
                    if (i != 0) {
                        this.f225a = i;
                        onChanged();
                    }
                    int i2 = c0084e.b;
                    if (i2 != 0) {
                        this.b = i2;
                        onChanged();
                    }
                    int i3 = c0084e.c;
                    if (i3 != 0) {
                        this.c = i3;
                        onChanged();
                    }
                    int i4 = c0084e.d;
                    if (i4 != 0) {
                        this.d = i4;
                        onChanged();
                    }
                    int i5 = c0084e.e;
                    if (i5 != 0) {
                        this.e = i5;
                        onChanged();
                    }
                    int i6 = c0084e.f;
                    if (i6 != 0) {
                        this.f = i6;
                        onChanged();
                    }
                    int i7 = c0084e.g;
                    if (i7 != 0) {
                        this.g = i7;
                        onChanged();
                    }
                    int i8 = c0084e.h;
                    if (i8 != 0) {
                        this.h = i8;
                        onChanged();
                    }
                    int i9 = c0084e.i;
                    if (i9 != 0) {
                        this.i = i9;
                        onChanged();
                    }
                    int i10 = c0084e.j;
                    if (i10 != 0) {
                        this.j = i10;
                        onChanged();
                    }
                    if (!c0084e.b().isEmpty()) {
                        this.k = c0084e.k;
                        onChanged();
                    }
                    b bVar = (b) super.mergeUnknownFields(c0084e.unknownFields);
                    onChanged();
                    return this;
                }
            }

            public C0084e(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.l = (byte) -1;
            }

            public static Parser<C0084e> c() {
                return n;
            }

            public String b() {
                Object obj = this.k;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.k = stringUtf8;
                return stringUtf8;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: d */
            public b toBuilder() {
                if (this == m) {
                    return new b();
                }
                return new b().a(this);
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof C0084e)) {
                    return super.equals(obj);
                }
                C0084e c0084e = (C0084e) obj;
                return this.f224a == c0084e.f224a && this.b == c0084e.b && this.c == c0084e.c && this.d == c0084e.d && this.e == c0084e.e && this.f == c0084e.f && this.g == c0084e.g && this.h == c0084e.h && this.i == c0084e.i && this.j == c0084e.j && b().equals(c0084e.b()) && this.unknownFields.equals(c0084e.unknownFields);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return m;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<C0084e> getParserForType() {
                return n;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                ByteString byteString;
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = this.f224a;
                int computeUInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeUInt32Size(1, i2) : 0;
                if (this.b != d.study_01.getNumber()) {
                    computeUInt32Size += CodedOutputStream.computeEnumSize(2, this.b);
                }
                int i3 = this.c;
                if (i3 != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(3, i3);
                }
                int i4 = this.d;
                if (i4 != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(4, i4);
                }
                int i5 = this.e;
                if (i5 != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(5, i5);
                }
                int i6 = this.f;
                if (i6 != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(6, i6);
                }
                int i7 = this.g;
                if (i7 != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(7, i7);
                }
                int i8 = this.h;
                if (i8 != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(8, i8);
                }
                int i9 = this.i;
                if (i9 != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(9, i9);
                }
                if (this.j != EnumC0083c.initial.getNumber()) {
                    computeUInt32Size += CodedOutputStream.computeEnumSize(10, this.j);
                }
                Object obj = this.k;
                if (obj instanceof String) {
                    byteString = ByteString.copyFromUtf8((String) obj);
                    this.k = byteString;
                } else {
                    byteString = (ByteString) obj;
                }
                if (!byteString.isEmpty()) {
                    computeUInt32Size += GeneratedMessageV3.computeStringSize(11, this.k);
                }
                int serializedSize = computeUInt32Size + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                int i = this.memoizedHashCode;
                if (i != 0) {
                    return i;
                }
                int hashCode = ((((((((((((((((((((((((((((((((((((((((((((((c.S.hashCode() + 779) * 37) + 1) * 53) + this.f224a) * 37) + 2) * 53) + this.b) * 37) + 3) * 53) + this.c) * 37) + 4) * 53) + this.d) * 37) + 5) * 53) + this.e) * 37) + 6) * 53) + this.f) * 37) + 7) * 53) + this.g) * 37) + 8) * 53) + this.h) * 37) + 9) * 53) + this.i) * 37) + 10) * 53) + this.j) * 37) + 11) * 53) + b().hashCode()) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode;
                return hashCode;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return c.T.ensureFieldAccessorsInitialized(C0084e.class, b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                byte b2 = this.l;
                if (b2 == 1) {
                    return true;
                }
                if (b2 == 0) {
                    return false;
                }
                this.l = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Message.Builder newBuilderForType() {
                return m.toBuilder();
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new C0084e();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) {
                ByteString byteString;
                int i = this.f224a;
                if (i != 0) {
                    codedOutputStream.writeUInt32(1, i);
                }
                if (this.b != d.study_01.getNumber()) {
                    codedOutputStream.writeEnum(2, this.b);
                }
                int i2 = this.c;
                if (i2 != 0) {
                    codedOutputStream.writeUInt32(3, i2);
                }
                int i3 = this.d;
                if (i3 != 0) {
                    codedOutputStream.writeUInt32(4, i3);
                }
                int i4 = this.e;
                if (i4 != 0) {
                    codedOutputStream.writeUInt32(5, i4);
                }
                int i5 = this.f;
                if (i5 != 0) {
                    codedOutputStream.writeUInt32(6, i5);
                }
                int i6 = this.g;
                if (i6 != 0) {
                    codedOutputStream.writeUInt32(7, i6);
                }
                int i7 = this.h;
                if (i7 != 0) {
                    codedOutputStream.writeUInt32(8, i7);
                }
                int i8 = this.i;
                if (i8 != 0) {
                    codedOutputStream.writeUInt32(9, i8);
                }
                if (this.j != EnumC0083c.initial.getNumber()) {
                    codedOutputStream.writeEnum(10, this.j);
                }
                Object obj = this.k;
                if (obj instanceof String) {
                    byteString = ByteString.copyFromUtf8((String) obj);
                    this.k = byteString;
                } else {
                    byteString = (ByteString) obj;
                }
                if (!byteString.isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 11, this.k);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return m;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public MessageLite.Builder newBuilderForType() {
                return m.toBuilder();
            }

            public C0084e() {
                this.l = (byte) -1;
                this.b = 0;
                this.j = 0;
                this.k = "";
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new b(builderParent);
            }

            public C0084e(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this();
                Objects.requireNonNull(extensionRegistryLite);
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            switch (readTag) {
                                case 0:
                                    break;
                                case 8:
                                    this.f224a = codedInputStream.readUInt32();
                                    continue;
                                case 16:
                                    this.b = codedInputStream.readEnum();
                                    continue;
                                case 24:
                                    this.c = codedInputStream.readUInt32();
                                    continue;
                                case 32:
                                    this.d = codedInputStream.readUInt32();
                                    continue;
                                case 40:
                                    this.e = codedInputStream.readUInt32();
                                    continue;
                                case 48:
                                    this.f = codedInputStream.readUInt32();
                                    continue;
                                case 56:
                                    this.g = codedInputStream.readUInt32();
                                    continue;
                                case 64:
                                    this.h = codedInputStream.readUInt32();
                                    continue;
                                case 72:
                                    this.i = codedInputStream.readUInt32();
                                    continue;
                                case 80:
                                    this.j = codedInputStream.readEnum();
                                    continue;
                                case 90:
                                    this.k = codedInputStream.readStringRequireUtf8();
                                    continue;
                                default:
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        break;
                                    } else {
                                        continue;
                                    }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } finally {
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }
        }

        public e(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.c = (byte) -1;
        }

        public static final Descriptors.Descriptor c() {
            return c.Q;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: d */
        public b toBuilder() {
            if (this == d) {
                return new b();
            }
            return new b().a(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof e)) {
                return super.equals(obj);
            }
            e eVar = (e) obj;
            return this.f220a == eVar.f220a && this.b.equals(eVar.b) && this.unknownFields.equals(eVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<e> getParserForType() {
            return e;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = this.f220a != a.begin.getNumber() ? CodedOutputStream.computeEnumSize(1, this.f220a) + 0 : 0;
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                computeEnumSize += CodedOutputStream.computeMessageSize(2, this.b.get(i2));
            }
            int serializedSize = computeEnumSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = ((((c.Q.hashCode() + 779) * 37) + 1) * 53) + this.f220a;
            if (this.b.size() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + this.b.hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return c.R.ensureFieldAccessorsInitialized(e.class, b.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b2 = this.c;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            this.c = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Message.Builder newBuilderForType() {
            return d.toBuilder();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new e();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            if (this.f220a != a.begin.getNumber()) {
                codedOutputStream.writeEnum(1, this.f220a);
            }
            for (int i = 0; i < this.b.size(); i++) {
                codedOutputStream.writeMessage(2, this.b.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public MessageLite.Builder newBuilderForType() {
            return d.toBuilder();
        }

        public e() {
            this.c = (byte) -1;
            this.f220a = 0;
            this.b = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new b(builderParent);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public e(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            this();
            Objects.requireNonNull(extensionRegistryLite);
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.f220a = codedInputStream.readEnum();
                            } else if (readTag != 18) {
                                if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                if (!(z2 & true)) {
                                    this.b = new ArrayList();
                                    z2 |= true;
                                }
                                this.b.add(codedInputStream.readMessage(C0084e.c(), extensionRegistryLite));
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    } catch (IOException e3) {
                        throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(this);
                    }
                } finally {
                    if (z2 & true) {
                        this.b = Collections.unmodifiableList(this.b);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class f extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final f d = new f();
        public static final Parser<f> e = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f226a;
        public List<C0085c> b;
        public byte c;

        /* loaded from: classes.dex */
        public class a extends AbstractParser<f> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new f(codedInputStream, extensionRegistryLite);
            }
        }

        /* loaded from: classes.dex */
        public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f227a;
            public int b;
            public List<C0085c> c;
            public RepeatedFieldBuilderV3<C0085c, C0085c.b, Object> d;

            public b() {
                this.b = 0;
                this.c = Collections.emptyList();
                d();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.c.f.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.c$f> r1 = a.c.f.e     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.c$f r3 = (a.c.f) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.a(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    a.c$f r4 = (a.c.f) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.a(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: a.c.f.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.c$f$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public b clear() {
                super.clear();
                this.b = 0;
                RepeatedFieldBuilderV3<C0085c, C0085c.b, Object> repeatedFieldBuilderV3 = this.d;
                if (repeatedFieldBuilderV3 == null) {
                    this.c = Collections.emptyList();
                    this.f227a &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message build() {
                f buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            public final RepeatedFieldBuilderV3<C0085c, C0085c.b, Object> c() {
                if (this.d == null) {
                    this.d = new RepeatedFieldBuilderV3<>(this.c, (this.f227a & 1) != 0, getParentForChildren(), isClean());
                    this.c = null;
                }
                return this.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (b) super.mo0clone();
            }

            public final void d() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    c();
                }
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return f.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return c.k;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return c.l.ensureFieldAccessorsInitialized(f.class, b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return f.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite build() {
                f buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clone() {
                return (b) super.mo0clone();
            }

            public b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.b = 0;
                this.c = Collections.emptyList();
                d();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeFrom(Message message) {
                if (message instanceof f) {
                    return a((f) message);
                }
                super.mergeFrom(message);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Object mo0clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public f buildPartial() {
                f fVar = new f(this);
                int i = this.f227a;
                fVar.f226a = this.b;
                RepeatedFieldBuilderV3<C0085c, C0085c.b, Object> repeatedFieldBuilderV3 = this.d;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) != 0) {
                        this.c = Collections.unmodifiableList(this.c);
                        this.f227a &= -2;
                    }
                    fVar.b = this.c;
                } else {
                    fVar.b = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return fVar;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof f) {
                    return a((f) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public b a(f fVar) {
                if (fVar == f.d) {
                    return this;
                }
                int i = fVar.f226a;
                if (i != 0) {
                    this.b = i;
                    onChanged();
                }
                if (this.d == null) {
                    if (!fVar.b.isEmpty()) {
                        if (this.c.isEmpty()) {
                            this.c = fVar.b;
                            this.f227a &= -2;
                        } else {
                            if ((this.f227a & 1) == 0) {
                                this.c = new ArrayList(this.c);
                                this.f227a |= 1;
                            }
                            this.c.addAll(fVar.b);
                        }
                        onChanged();
                    }
                } else if (!fVar.b.isEmpty()) {
                    if (this.d.isEmpty()) {
                        this.d.dispose();
                        this.d = null;
                        this.c = fVar.b;
                        this.f227a &= -2;
                        this.d = GeneratedMessageV3.alwaysUseFieldBuilders ? c() : null;
                    } else {
                        this.d.addAllMessages(fVar.b);
                    }
                }
                b bVar = (b) super.mergeUnknownFields(fVar.unknownFields);
                onChanged();
                return this;
            }
        }

        /* renamed from: a.c$f$c  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0085c extends GeneratedMessageV3 implements MessageOrBuilder {
            public static final C0085c d = new C0085c();
            public static final Parser<C0085c> e = new a();

            /* renamed from: a  reason: collision with root package name */
            public int f228a;
            public int b;
            public byte c;

            /* renamed from: a.c$f$c$a */
            /* loaded from: classes.dex */
            public class a extends AbstractParser<C0085c> {
                @Override // com.google.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    return new C0085c(codedInputStream, extensionRegistryLite);
                }
            }

            /* renamed from: a.c$f$c$b */
            /* loaded from: classes.dex */
            public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

                /* renamed from: a  reason: collision with root package name */
                public int f229a;
                public int b;

                public b() {
                    c();
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public a.c.f.C0085c.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser<a.c$f$c> r1 = a.c.f.C0085c.e     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        a.c$f$c r3 = (a.c.f.C0085c) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        if (r3 == 0) goto Le
                        r2.a(r3)
                    Le:
                        return r2
                    Lf:
                        r3 = move-exception
                        goto L1f
                    L11:
                        r3 = move-exception
                        com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                        a.c$f$c r4 = (a.c.f.C0085c) r4     // Catch: java.lang.Throwable -> Lf
                        java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                        throw r3     // Catch: java.lang.Throwable -> L1d
                    L1d:
                        r3 = move-exception
                        r0 = r4
                    L1f:
                        if (r0 == 0) goto L24
                        r2.a(r0)
                    L24:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: a.c.f.C0085c.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.c$f$c$b");
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: b */
                public b clear() {
                    super.clear();
                    this.f229a = 0;
                    this.b = 0;
                    return this;
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Message build() {
                    C0085c buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
                }

                public final void c() {
                    boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (b) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public Message getDefaultInstanceForType() {
                    return C0085c.d;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return c.m;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return c.n.ensureFieldAccessorsInitialized(C0085c.class, b.class);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (b) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.setUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (b) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public AbstractMessageLite.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public MessageLite getDefaultInstanceForType() {
                    return C0085c.d;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (b) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.setUnknownFields(unknownFieldSet);
                }

                public b(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    c();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public MessageLite build() {
                    C0085c buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public MessageLite.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder mergeFrom(Message message) {
                    if (message instanceof C0085c) {
                        return a((C0085c) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone */
                public Object mo0clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a */
                public C0085c buildPartial() {
                    C0085c c0085c = new C0085c(this);
                    c0085c.f228a = this.f229a;
                    c0085c.b = this.b;
                    onBuilt();
                    return c0085c;
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder mergeFrom(Message message) {
                    if (message instanceof C0085c) {
                        return a((C0085c) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public b a(C0085c c0085c) {
                    if (c0085c == C0085c.d) {
                        return this;
                    }
                    int i = c0085c.f228a;
                    if (i != 0) {
                        this.f229a = i;
                        onChanged();
                    }
                    int i2 = c0085c.b;
                    if (i2 != 0) {
                        this.b = i2;
                        onChanged();
                    }
                    b bVar = (b) super.mergeUnknownFields(c0085c.unknownFields);
                    onChanged();
                    return this;
                }
            }

            public C0085c(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.c = (byte) -1;
            }

            public static Parser<C0085c> b() {
                return e;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: c */
            public b toBuilder() {
                if (this == d) {
                    return new b();
                }
                return new b().a(this);
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof C0085c)) {
                    return super.equals(obj);
                }
                C0085c c0085c = (C0085c) obj;
                return this.f228a == c0085c.f228a && this.b == c0085c.b && this.unknownFields.equals(c0085c.unknownFields);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<C0085c> getParserForType() {
                return e;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = this.f228a;
                int computeUInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeUInt32Size(1, i2) : 0;
                int i3 = this.b;
                if (i3 != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(2, i3);
                }
                int serializedSize = computeUInt32Size + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                int i = this.memoizedHashCode;
                if (i != 0) {
                    return i;
                }
                int hashCode = ((((((((((c.m.hashCode() + 779) * 37) + 1) * 53) + this.f228a) * 37) + 2) * 53) + this.b) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode;
                return hashCode;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return c.n.ensureFieldAccessorsInitialized(C0085c.class, b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                byte b2 = this.c;
                if (b2 == 1) {
                    return true;
                }
                if (b2 == 0) {
                    return false;
                }
                this.c = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Message.Builder newBuilderForType() {
                return d.toBuilder();
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new C0085c();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) {
                int i = this.f228a;
                if (i != 0) {
                    codedOutputStream.writeUInt32(1, i);
                }
                int i2 = this.b;
                if (i2 != 0) {
                    codedOutputStream.writeUInt32(2, i2);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return d;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public MessageLite.Builder newBuilderForType() {
                return d.toBuilder();
            }

            public C0085c() {
                this.c = (byte) -1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new b(builderParent);
            }

            public C0085c(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this();
                Objects.requireNonNull(extensionRegistryLite);
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag == 8) {
                                        this.f228a = codedInputStream.readUInt32();
                                    } else if (readTag != 16) {
                                        if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        }
                                    } else {
                                        this.b = codedInputStream.readUInt32();
                                    }
                                }
                                z = true;
                            } catch (IOException e2) {
                                throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                            }
                        } catch (InvalidProtocolBufferException e3) {
                            throw e3.setUnfinishedMessage(this);
                        }
                    } finally {
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }
        }

        public f(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.c = (byte) -1;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: c */
        public b toBuilder() {
            if (this == d) {
                return new b();
            }
            return new b().a(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof f)) {
                return super.equals(obj);
            }
            f fVar = (f) obj;
            return this.f226a == fVar.f226a && this.b.equals(fVar.b) && this.unknownFields.equals(fVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<f> getParserForType() {
            return e;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = this.f226a != a.begin.getNumber() ? CodedOutputStream.computeEnumSize(1, this.f226a) + 0 : 0;
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                computeEnumSize += CodedOutputStream.computeMessageSize(2, this.b.get(i2));
            }
            int serializedSize = computeEnumSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = ((((c.k.hashCode() + 779) * 37) + 1) * 53) + this.f226a;
            if (this.b.size() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + this.b.hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return c.l.ensureFieldAccessorsInitialized(f.class, b.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b2 = this.c;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            this.c = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Message.Builder newBuilderForType() {
            return d.toBuilder();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new f();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            if (this.f226a != a.begin.getNumber()) {
                codedOutputStream.writeEnum(1, this.f226a);
            }
            for (int i = 0; i < this.b.size(); i++) {
                codedOutputStream.writeMessage(2, this.b.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public MessageLite.Builder newBuilderForType() {
            return d.toBuilder();
        }

        public f() {
            this.c = (byte) -1;
            this.f226a = 0;
            this.b = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new b(builderParent);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public f(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            this();
            Objects.requireNonNull(extensionRegistryLite);
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.f226a = codedInputStream.readEnum();
                            } else if (readTag != 18) {
                                if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                if (!(z2 & true)) {
                                    this.b = new ArrayList();
                                    z2 |= true;
                                }
                                this.b.add(codedInputStream.readMessage(C0085c.b(), extensionRegistryLite));
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    } catch (IOException e3) {
                        throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(this);
                    }
                } finally {
                    if (z2 & true) {
                        this.b = Collections.unmodifiableList(this.b);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class g extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final g c = new g();
        public static final Parser<g> d = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f230a;
        public byte b;

        /* loaded from: classes.dex */
        public class a extends AbstractParser<g> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new g(codedInputStream, extensionRegistryLite);
            }
        }

        /* loaded from: classes.dex */
        public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f231a;

            public b() {
                c();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.c.g.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.c$g> r1 = a.c.g.d     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.c$g r3 = (a.c.g) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.a(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    a.c$g r4 = (a.c.g) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.a(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: a.c.g.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.c$g$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public b clear() {
                super.clear();
                this.f231a = 0;
                return this;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message build() {
                g buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            public final void c() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return g.c;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return c.U;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return c.V.ensureFieldAccessorsInitialized(g.class, b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return g.c;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            public b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                c();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite build() {
                g buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeFrom(Message message) {
                if (message instanceof g) {
                    return a((g) message);
                }
                super.mergeFrom(message);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Object mo0clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public g buildPartial() {
                g gVar = new g(this);
                gVar.f230a = this.f231a;
                onBuilt();
                return gVar;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof g) {
                    return a((g) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public b a(g gVar) {
                if (gVar == g.c) {
                    return this;
                }
                int i = gVar.f230a;
                if (i != 0) {
                    this.f231a = i;
                    onChanged();
                }
                b bVar = (b) super.mergeUnknownFields(gVar.unknownFields);
                onChanged();
                return this;
            }
        }

        public g(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.b = (byte) -1;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: b */
        public b toBuilder() {
            if (this == c) {
                return new b();
            }
            return new b().a(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof g)) {
                return super.equals(obj);
            }
            g gVar = (g) obj;
            return this.f230a == gVar.f230a && this.unknownFields.equals(gVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return c;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<g> getParserForType() {
            return d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.f230a;
            int computeUInt32Size = (i2 != 0 ? 0 + CodedOutputStream.computeUInt32Size(1, i2) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeUInt32Size;
            return computeUInt32Size;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = ((((((c.U.hashCode() + 779) * 37) + 1) * 53) + this.f230a) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return c.V.ensureFieldAccessorsInitialized(g.class, b.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b2 = this.b;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            this.b = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Message.Builder newBuilderForType() {
            return c.toBuilder();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new g();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            int i = this.f230a;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return c;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public MessageLite.Builder newBuilderForType() {
            return c.toBuilder();
        }

        public g() {
            this.b = (byte) -1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new b(builderParent);
        }

        public g(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            this();
            Objects.requireNonNull(extensionRegistryLite);
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag != 8) {
                                if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.f230a = codedInputStream.readUInt32();
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class h extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final h d = new h();
        public static final Parser<h> e = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f232a;
        public List<C0086c> b;
        public byte c;

        /* loaded from: classes.dex */
        public class a extends AbstractParser<h> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new h(codedInputStream, extensionRegistryLite);
            }
        }

        /* loaded from: classes.dex */
        public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f233a;
            public int b;
            public List<C0086c> c;
            public RepeatedFieldBuilderV3<C0086c, C0086c.b, Object> d;

            public b() {
                this.b = 0;
                this.c = Collections.emptyList();
                d();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.c.h.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.c$h> r1 = a.c.h.e     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.c$h r3 = (a.c.h) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.a(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    a.c$h r4 = (a.c.h) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.a(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: a.c.h.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.c$h$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public b clear() {
                super.clear();
                this.b = 0;
                RepeatedFieldBuilderV3<C0086c, C0086c.b, Object> repeatedFieldBuilderV3 = this.d;
                if (repeatedFieldBuilderV3 == null) {
                    this.c = Collections.emptyList();
                    this.f233a &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message build() {
                h buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            public final RepeatedFieldBuilderV3<C0086c, C0086c.b, Object> c() {
                if (this.d == null) {
                    this.d = new RepeatedFieldBuilderV3<>(this.c, (this.f233a & 1) != 0, getParentForChildren(), isClean());
                    this.c = null;
                }
                return this.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (b) super.mo0clone();
            }

            public final void d() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    c();
                }
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return h.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return c.s;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return c.t.ensureFieldAccessorsInitialized(h.class, b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return h.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite build() {
                h buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clone() {
                return (b) super.mo0clone();
            }

            public b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.b = 0;
                this.c = Collections.emptyList();
                d();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeFrom(Message message) {
                if (message instanceof h) {
                    return a((h) message);
                }
                super.mergeFrom(message);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Object mo0clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public h buildPartial() {
                h hVar = new h(this);
                int i = this.f233a;
                hVar.f232a = this.b;
                RepeatedFieldBuilderV3<C0086c, C0086c.b, Object> repeatedFieldBuilderV3 = this.d;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) != 0) {
                        this.c = Collections.unmodifiableList(this.c);
                        this.f233a &= -2;
                    }
                    hVar.b = this.c;
                } else {
                    hVar.b = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return hVar;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof h) {
                    return a((h) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public b a(h hVar) {
                if (hVar == h.d) {
                    return this;
                }
                int i = hVar.f232a;
                if (i != 0) {
                    this.b = i;
                    onChanged();
                }
                if (this.d == null) {
                    if (!hVar.b.isEmpty()) {
                        if (this.c.isEmpty()) {
                            this.c = hVar.b;
                            this.f233a &= -2;
                        } else {
                            if ((this.f233a & 1) == 0) {
                                this.c = new ArrayList(this.c);
                                this.f233a |= 1;
                            }
                            this.c.addAll(hVar.b);
                        }
                        onChanged();
                    }
                } else if (!hVar.b.isEmpty()) {
                    if (this.d.isEmpty()) {
                        this.d.dispose();
                        this.d = null;
                        this.c = hVar.b;
                        this.f233a &= -2;
                        this.d = GeneratedMessageV3.alwaysUseFieldBuilders ? c() : null;
                    } else {
                        this.d.addAllMessages(hVar.b);
                    }
                }
                b bVar = (b) super.mergeUnknownFields(hVar.unknownFields);
                onChanged();
                return this;
            }
        }

        /* renamed from: a.c$h$c  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0086c extends GeneratedMessageV3 implements MessageOrBuilder {
            public static final C0086c x = new C0086c();
            public static final Parser<C0086c> y = new a();

            /* renamed from: a  reason: collision with root package name */
            public int f234a;
            public int b;
            public int c;
            public int d;
            public int e;
            public int f;
            public int g;
            public int h;
            public int i;
            public int j;
            public int k;
            public int l;
            public int m;
            public int n;
            public float o;
            public int p;
            public int q;
            public int r;
            public int s;
            public int t;
            public int u;
            public int v;
            public byte w;

            /* renamed from: a.c$h$c$a */
            /* loaded from: classes.dex */
            public class a extends AbstractParser<C0086c> {
                @Override // com.google.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    return new C0086c(codedInputStream, extensionRegistryLite);
                }
            }

            /* renamed from: a.c$h$c$b */
            /* loaded from: classes.dex */
            public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

                /* renamed from: a  reason: collision with root package name */
                public int f235a;
                public int b;
                public int c;
                public int d;
                public int e;
                public int f;
                public int g;
                public int h;
                public int i;
                public int j;
                public int k;
                public int l;
                public int m;
                public int n;
                public float o;
                public int p;
                public int q;
                public int r;
                public int s;
                public int t;
                public int u;
                public int v;

                public b() {
                    this.f235a = 0;
                    c();
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public a.c.h.C0086c.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser<a.c$h$c> r1 = a.c.h.C0086c.y     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        a.c$h$c r3 = (a.c.h.C0086c) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        if (r3 == 0) goto Le
                        r2.a(r3)
                    Le:
                        return r2
                    Lf:
                        r3 = move-exception
                        goto L1f
                    L11:
                        r3 = move-exception
                        com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                        a.c$h$c r4 = (a.c.h.C0086c) r4     // Catch: java.lang.Throwable -> Lf
                        java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                        throw r3     // Catch: java.lang.Throwable -> L1d
                    L1d:
                        r3 = move-exception
                        r0 = r4
                    L1f:
                        if (r0 == 0) goto L24
                        r2.a(r0)
                    L24:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: a.c.h.C0086c.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.c$h$c$b");
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: b */
                public b clear() {
                    super.clear();
                    this.f235a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.f = 0;
                    this.g = 0;
                    this.h = 0;
                    this.i = 0;
                    this.j = 0;
                    this.k = 0;
                    this.l = 0;
                    this.m = 0;
                    this.n = 0;
                    this.o = 0.0f;
                    this.p = 0;
                    this.q = 0;
                    this.r = 0;
                    this.s = 0;
                    this.t = 0;
                    this.u = 0;
                    this.v = 0;
                    return this;
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Message build() {
                    C0086c buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
                }

                public final void c() {
                    boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (b) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public Message getDefaultInstanceForType() {
                    return C0086c.x;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return c.u;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return c.v.ensureFieldAccessorsInitialized(C0086c.class, b.class);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (b) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.setUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (b) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public AbstractMessageLite.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public MessageLite getDefaultInstanceForType() {
                    return C0086c.x;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (b) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.setUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                public b(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.f235a = 0;
                    c();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public MessageLite build() {
                    C0086c buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public MessageLite.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder mergeFrom(Message message) {
                    if (message instanceof C0086c) {
                        return a((C0086c) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone */
                public Object mo0clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a */
                public C0086c buildPartial() {
                    C0086c c0086c = new C0086c(this);
                    c0086c.f234a = this.f235a;
                    c0086c.b = this.b;
                    c0086c.c = this.c;
                    c0086c.d = this.d;
                    c0086c.e = this.e;
                    c0086c.f = this.f;
                    c0086c.g = this.g;
                    c0086c.h = this.h;
                    c0086c.i = this.i;
                    c0086c.j = this.j;
                    c0086c.k = this.k;
                    c0086c.l = this.l;
                    c0086c.m = this.m;
                    c0086c.n = this.n;
                    c0086c.o = this.o;
                    c0086c.p = this.p;
                    c0086c.q = this.q;
                    c0086c.r = this.r;
                    c0086c.s = this.s;
                    c0086c.t = this.t;
                    c0086c.u = this.u;
                    c0086c.v = this.v;
                    onBuilt();
                    return c0086c;
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder mergeFrom(Message message) {
                    if (message instanceof C0086c) {
                        return a((C0086c) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public b a(C0086c c0086c) {
                    if (c0086c == C0086c.x) {
                        return this;
                    }
                    int i = c0086c.f234a;
                    if (i != 0) {
                        this.f235a = i;
                        onChanged();
                    }
                    int i2 = c0086c.b;
                    if (i2 != 0) {
                        this.b = i2;
                        onChanged();
                    }
                    int i3 = c0086c.c;
                    if (i3 != 0) {
                        this.c = i3;
                        onChanged();
                    }
                    int i4 = c0086c.d;
                    if (i4 != 0) {
                        this.d = i4;
                        onChanged();
                    }
                    int i5 = c0086c.e;
                    if (i5 != 0) {
                        this.e = i5;
                        onChanged();
                    }
                    int i6 = c0086c.f;
                    if (i6 != 0) {
                        this.f = i6;
                        onChanged();
                    }
                    int i7 = c0086c.g;
                    if (i7 != 0) {
                        this.g = i7;
                        onChanged();
                    }
                    int i8 = c0086c.h;
                    if (i8 != 0) {
                        this.h = i8;
                        onChanged();
                    }
                    int i9 = c0086c.i;
                    if (i9 != 0) {
                        this.i = i9;
                        onChanged();
                    }
                    int i10 = c0086c.j;
                    if (i10 != 0) {
                        this.j = i10;
                        onChanged();
                    }
                    int i11 = c0086c.k;
                    if (i11 != 0) {
                        this.k = i11;
                        onChanged();
                    }
                    int i12 = c0086c.l;
                    if (i12 != 0) {
                        this.l = i12;
                        onChanged();
                    }
                    int i13 = c0086c.m;
                    if (i13 != 0) {
                        this.m = i13;
                        onChanged();
                    }
                    int i14 = c0086c.n;
                    if (i14 != 0) {
                        this.n = i14;
                        onChanged();
                    }
                    float f = c0086c.o;
                    if (f != 0.0f) {
                        this.o = f;
                        onChanged();
                    }
                    int i15 = c0086c.p;
                    if (i15 != 0) {
                        this.p = i15;
                        onChanged();
                    }
                    int i16 = c0086c.q;
                    if (i16 != 0) {
                        this.q = i16;
                        onChanged();
                    }
                    int i17 = c0086c.r;
                    if (i17 != 0) {
                        this.r = i17;
                        onChanged();
                    }
                    int i18 = c0086c.s;
                    if (i18 != 0) {
                        this.s = i18;
                        onChanged();
                    }
                    int i19 = c0086c.t;
                    if (i19 != 0) {
                        this.t = i19;
                        onChanged();
                    }
                    int i20 = c0086c.u;
                    if (i20 != 0) {
                        this.u = i20;
                        onChanged();
                    }
                    int i21 = c0086c.v;
                    if (i21 != 0) {
                        this.v = i21;
                        onChanged();
                    }
                    b bVar = (b) super.mergeUnknownFields(c0086c.unknownFields);
                    onChanged();
                    return this;
                }
            }

            public C0086c(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.w = (byte) -1;
            }

            public static Parser<C0086c> b() {
                return y;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: c */
            public b toBuilder() {
                if (this == x) {
                    return new b();
                }
                return new b().a(this);
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof C0086c)) {
                    return super.equals(obj);
                }
                C0086c c0086c = (C0086c) obj;
                return this.f234a == c0086c.f234a && this.b == c0086c.b && this.c == c0086c.c && this.d == c0086c.d && this.e == c0086c.e && this.f == c0086c.f && this.g == c0086c.g && this.h == c0086c.h && this.i == c0086c.i && this.j == c0086c.j && this.k == c0086c.k && this.l == c0086c.l && this.m == c0086c.m && this.n == c0086c.n && Float.floatToIntBits(this.o) == Float.floatToIntBits(c0086c.o) && this.p == c0086c.p && this.q == c0086c.q && this.r == c0086c.r && this.s == c0086c.s && this.t == c0086c.t && this.u == c0086c.u && this.v == c0086c.v && this.unknownFields.equals(c0086c.unknownFields);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return x;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<C0086c> getParserForType() {
                return y;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int computeEnumSize = this.f234a != d.daily.getNumber() ? 0 + CodedOutputStream.computeEnumSize(1, this.f234a) : 0;
                int i2 = this.b;
                if (i2 != 0) {
                    computeEnumSize += CodedOutputStream.computeUInt32Size(2, i2);
                }
                int i3 = this.c;
                if (i3 != 0) {
                    computeEnumSize += CodedOutputStream.computeUInt32Size(3, i3);
                }
                int i4 = this.d;
                if (i4 != 0) {
                    computeEnumSize += CodedOutputStream.computeUInt32Size(4, i4);
                }
                int i5 = this.e;
                if (i5 != 0) {
                    computeEnumSize += CodedOutputStream.computeUInt32Size(5, i5);
                }
                int i6 = this.f;
                if (i6 != 0) {
                    computeEnumSize += CodedOutputStream.computeUInt32Size(6, i6);
                }
                int i7 = this.g;
                if (i7 != 0) {
                    computeEnumSize += CodedOutputStream.computeUInt32Size(7, i7);
                }
                int i8 = this.h;
                if (i8 != 0) {
                    computeEnumSize += CodedOutputStream.computeUInt32Size(8, i8);
                }
                int i9 = this.i;
                if (i9 != 0) {
                    computeEnumSize += CodedOutputStream.computeUInt32Size(9, i9);
                }
                int i10 = this.j;
                if (i10 != 0) {
                    computeEnumSize += CodedOutputStream.computeUInt32Size(10, i10);
                }
                int i11 = this.k;
                if (i11 != 0) {
                    computeEnumSize += CodedOutputStream.computeUInt32Size(11, i11);
                }
                int i12 = this.l;
                if (i12 != 0) {
                    computeEnumSize += CodedOutputStream.computeUInt32Size(12, i12);
                }
                int i13 = this.m;
                if (i13 != 0) {
                    computeEnumSize += CodedOutputStream.computeUInt32Size(13, i13);
                }
                int i14 = this.n;
                if (i14 != 0) {
                    computeEnumSize += CodedOutputStream.computeUInt32Size(14, i14);
                }
                float f = this.o;
                if (f != 0.0f) {
                    computeEnumSize += CodedOutputStream.computeFloatSize(15, f);
                }
                int i15 = this.p;
                if (i15 != 0) {
                    computeEnumSize += CodedOutputStream.computeUInt32Size(16, i15);
                }
                int i16 = this.q;
                if (i16 != 0) {
                    computeEnumSize += CodedOutputStream.computeUInt32Size(17, i16);
                }
                int i17 = this.r;
                if (i17 != 0) {
                    computeEnumSize += CodedOutputStream.computeUInt32Size(18, i17);
                }
                int i18 = this.s;
                if (i18 != 0) {
                    computeEnumSize += CodedOutputStream.computeUInt32Size(19, i18);
                }
                int i19 = this.t;
                if (i19 != 0) {
                    computeEnumSize += CodedOutputStream.computeUInt32Size(20, i19);
                }
                int i20 = this.u;
                if (i20 != 0) {
                    computeEnumSize += CodedOutputStream.computeUInt32Size(21, i20);
                }
                int i21 = this.v;
                if (i21 != 0) {
                    computeEnumSize += CodedOutputStream.computeUInt32Size(22, i21);
                }
                int serializedSize = computeEnumSize + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                int i = this.memoizedHashCode;
                if (i != 0) {
                    return i;
                }
                int hashCode = ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((c.u.hashCode() + 779) * 37) + 1) * 53) + this.f234a) * 37) + 2) * 53) + this.b) * 37) + 3) * 53) + this.c) * 37) + 4) * 53) + this.d) * 37) + 5) * 53) + this.e) * 37) + 6) * 53) + this.f) * 37) + 7) * 53) + this.g) * 37) + 8) * 53) + this.h) * 37) + 9) * 53) + this.i) * 37) + 10) * 53) + this.j) * 37) + 11) * 53) + this.k) * 37) + 12) * 53) + this.l) * 37) + 13) * 53) + this.m) * 37) + 14) * 53) + this.n) * 37) + 15) * 53) + Float.floatToIntBits(this.o)) * 37) + 16) * 53) + this.p) * 37) + 17) * 53) + this.q) * 37) + 18) * 53) + this.r) * 37) + 19) * 53) + this.s) * 37) + 20) * 53) + this.t) * 37) + 21) * 53) + this.u) * 37) + 22) * 53) + this.v) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode;
                return hashCode;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return c.v.ensureFieldAccessorsInitialized(C0086c.class, b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                byte b2 = this.w;
                if (b2 == 1) {
                    return true;
                }
                if (b2 == 0) {
                    return false;
                }
                this.w = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Message.Builder newBuilderForType() {
                return x.toBuilder();
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new C0086c();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) {
                if (this.f234a != d.daily.getNumber()) {
                    codedOutputStream.writeEnum(1, this.f234a);
                }
                int i = this.b;
                if (i != 0) {
                    codedOutputStream.writeUInt32(2, i);
                }
                int i2 = this.c;
                if (i2 != 0) {
                    codedOutputStream.writeUInt32(3, i2);
                }
                int i3 = this.d;
                if (i3 != 0) {
                    codedOutputStream.writeUInt32(4, i3);
                }
                int i4 = this.e;
                if (i4 != 0) {
                    codedOutputStream.writeUInt32(5, i4);
                }
                int i5 = this.f;
                if (i5 != 0) {
                    codedOutputStream.writeUInt32(6, i5);
                }
                int i6 = this.g;
                if (i6 != 0) {
                    codedOutputStream.writeUInt32(7, i6);
                }
                int i7 = this.h;
                if (i7 != 0) {
                    codedOutputStream.writeUInt32(8, i7);
                }
                int i8 = this.i;
                if (i8 != 0) {
                    codedOutputStream.writeUInt32(9, i8);
                }
                int i9 = this.j;
                if (i9 != 0) {
                    codedOutputStream.writeUInt32(10, i9);
                }
                int i10 = this.k;
                if (i10 != 0) {
                    codedOutputStream.writeUInt32(11, i10);
                }
                int i11 = this.l;
                if (i11 != 0) {
                    codedOutputStream.writeUInt32(12, i11);
                }
                int i12 = this.m;
                if (i12 != 0) {
                    codedOutputStream.writeUInt32(13, i12);
                }
                int i13 = this.n;
                if (i13 != 0) {
                    codedOutputStream.writeUInt32(14, i13);
                }
                float f = this.o;
                if (f != 0.0f) {
                    codedOutputStream.writeFloat(15, f);
                }
                int i14 = this.p;
                if (i14 != 0) {
                    codedOutputStream.writeUInt32(16, i14);
                }
                int i15 = this.q;
                if (i15 != 0) {
                    codedOutputStream.writeUInt32(17, i15);
                }
                int i16 = this.r;
                if (i16 != 0) {
                    codedOutputStream.writeUInt32(18, i16);
                }
                int i17 = this.s;
                if (i17 != 0) {
                    codedOutputStream.writeUInt32(19, i17);
                }
                int i18 = this.t;
                if (i18 != 0) {
                    codedOutputStream.writeUInt32(20, i18);
                }
                int i19 = this.u;
                if (i19 != 0) {
                    codedOutputStream.writeUInt32(21, i19);
                }
                int i20 = this.v;
                if (i20 != 0) {
                    codedOutputStream.writeUInt32(22, i20);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return x;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public MessageLite.Builder newBuilderForType() {
                return x.toBuilder();
            }

            public C0086c() {
                this.w = (byte) -1;
                this.f234a = 0;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new b(builderParent);
            }

            public C0086c(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this();
                Objects.requireNonNull(extensionRegistryLite);
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            switch (readTag) {
                                case 0:
                                    break;
                                case 8:
                                    this.f234a = codedInputStream.readEnum();
                                    continue;
                                case 16:
                                    this.b = codedInputStream.readUInt32();
                                    continue;
                                case 24:
                                    this.c = codedInputStream.readUInt32();
                                    continue;
                                case 32:
                                    this.d = codedInputStream.readUInt32();
                                    continue;
                                case 40:
                                    this.e = codedInputStream.readUInt32();
                                    continue;
                                case 48:
                                    this.f = codedInputStream.readUInt32();
                                    continue;
                                case 56:
                                    this.g = codedInputStream.readUInt32();
                                    continue;
                                case 64:
                                    this.h = codedInputStream.readUInt32();
                                    continue;
                                case 72:
                                    this.i = codedInputStream.readUInt32();
                                    continue;
                                case 80:
                                    this.j = codedInputStream.readUInt32();
                                    continue;
                                case 88:
                                    this.k = codedInputStream.readUInt32();
                                    continue;
                                case 96:
                                    this.l = codedInputStream.readUInt32();
                                    continue;
                                case 104:
                                    this.m = codedInputStream.readUInt32();
                                    continue;
                                case 112:
                                    this.n = codedInputStream.readUInt32();
                                    continue;
                                case 125:
                                    this.o = codedInputStream.readFloat();
                                    continue;
                                case 128:
                                    this.p = codedInputStream.readUInt32();
                                    continue;
                                case 136:
                                    this.q = codedInputStream.readUInt32();
                                    continue;
                                case 144:
                                    this.r = codedInputStream.readUInt32();
                                    continue;
                                case 152:
                                    this.s = codedInputStream.readUInt32();
                                    continue;
                                case 160:
                                    this.t = codedInputStream.readUInt32();
                                    continue;
                                case 168:
                                    this.u = codedInputStream.readUInt32();
                                    continue;
                                case 176:
                                    this.v = codedInputStream.readUInt32();
                                    continue;
                                default:
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        break;
                                    } else {
                                        continue;
                                    }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } finally {
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }
        }

        /* loaded from: classes.dex */
        public enum d implements ProtocolMessageEnum {
            daily(0),
            ourdoor_walking(1),
            ourdoor_running(2),
            ourdoor_on_foot(3),
            ourdoor_on_mountaineering(4),
            ourdoor_trail_running(5),
            ourdoor_cycling(6),
            outdoor_swimming(7),
            indoor_walking(8),
            indoor_running(9),
            indoor_exercise(10),
            indoor_cycling(11),
            elliptical(12),
            yoga(13),
            rowing(14),
            indoor_swimming(15),
            od_rock(16),
            od_skate(17),
            od_roller(18),
            od_parkour(19),
            od_parachute(20),
            train_HIT(21),
            train_weight(22),
            train_plank(23),
            train_jumping(24),
            train_stair(25),
            train_core(26),
            train_flex(27),
            train_pilates(28),
            train_stretch(29),
            train_strength(30),
            train_cross(31),
            train_dumbbell(32),
            train_deadlift(33),
            train_sit(34),
            train_funcition(35),
            train_upper(36),
            train_lower(37),
            train_abs(38),
            train_back(39),
            water_sailboat(40),
            water_sup(41),
            water_polo(42),
            water_thrash(43),
            water_kayak(44),
            water_drifting(45),
            water_boating(46),
            water_fin(47),
            water_diving(48),
            water_artistic(49),
            water_snorkel(50),
            water_kitesurfing(51),
            water_atv(52),
            water_beach(53),
            dance_dance(54),
            dance_delly(55),
            dance_gymnastics(56),
            dance_aerobics(57),
            dance_hip_hop(58),
            fight_boxing(59),
            fight_wushu(60),
            fight_wrestling(61),
            fight_taichi(62),
            fight_muay_thai(63),
            fight_judo(64),
            fight_taekwondo(65),
            fight_karate(66),
            fight_free_sparring(67),
            ball_soccer(68),
            ball_basketball(69),
            ball_volleyball(70),
            ball_badminton(71),
            ball_pingpong(72),
            ball_cricket(73),
            ball_football(74),
            ball_racqurball(75),
            ball_handball(76),
            ball_squash(77),
            ball_shuttlecock(78),
            ball_raga(79),
            snow_mobile(80),
            snow_skis(81),
            snow_puck(82),
            snow_skate(83),
            snow_curling(84),
            snow_board(85),
            snow_sled(86),
            leisure_meditation(87),
            leisure_kendo(88),
            leisure_fence(89),
            leisure_bowling(90),
            leisure_billiards(91),
            leisure_archery(92),
            leisure_darts(93),
            leisure_horse(94),
            leisure_hula(95),
            leisure_kite(96),
            leisure_fishing(97),
            leisure_fribee(98),
            leisure_equestrian(99),
            leisure_racing(100),
            other_free(101),
            other_rope(102),
            other_climb(103),
            other_push(104),
            other_horizontal(105),
            other_parallel(106),
            tennis(107),
            baseball(108),
            hockey(109),
            custom_sport(110),
            mark_time(111),
            walking_machine(112),
            athletics(113),
            lumbar_abdomen_training(114),
            latin_dance(115),
            ballet(116),
            golf(117),
            folk_dance(118),
            lacrosse(119),
            softball(120),
            peak_ball(121),
            trampoline(122),
            parkour(123),
            push_up(124),
            high_jump(125),
            long_jump(126),
            daily_ex(32768),
            ourdoor_walking_ex(32769),
            ourdoor_running_ex(32770),
            ourdoor_on_foot_ex(32771),
            ourdoor_on_mountaineering_ex(32772),
            ourdoor_trail_running_ex(ExifInterface.DATA_PACK_BITS_COMPRESSED),
            ourdoor_cycling_ex(32774),
            outdoor_swimming_ex(32775),
            indoor_walking_ex(32776),
            indoor_running_ex(32777),
            indoor_exercise_ex(32778),
            indoor_cycling_ex(32779),
            elliptical_ex(32780),
            yoga_ex(32781),
            rowing_ex(32782),
            indoor_swimming_ex(32783),
            UNRECOGNIZED(-1);
            

            /* renamed from: a  reason: collision with root package name */
            public final int f236a;

            static {
                values();
            }

            d(int i) {
                this.f236a = i;
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return h.c().getEnumTypes().get(0);
            }

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.f236a;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                if (this != UNRECOGNIZED) {
                    return h.c().getEnumTypes().get(0).getValues().get(ordinal());
                }
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
        }

        public h(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.c = (byte) -1;
        }

        public static final Descriptors.Descriptor c() {
            return c.s;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: d */
        public b toBuilder() {
            if (this == d) {
                return new b();
            }
            return new b().a(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof h)) {
                return super.equals(obj);
            }
            h hVar = (h) obj;
            return this.f232a == hVar.f232a && this.b.equals(hVar.b) && this.unknownFields.equals(hVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<h> getParserForType() {
            return e;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = this.f232a != a.begin.getNumber() ? CodedOutputStream.computeEnumSize(1, this.f232a) + 0 : 0;
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                computeEnumSize += CodedOutputStream.computeMessageSize(2, this.b.get(i2));
            }
            int serializedSize = computeEnumSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = ((((c.s.hashCode() + 779) * 37) + 1) * 53) + this.f232a;
            if (this.b.size() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + this.b.hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return c.t.ensureFieldAccessorsInitialized(h.class, b.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b2 = this.c;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            this.c = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Message.Builder newBuilderForType() {
            return d.toBuilder();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new h();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            if (this.f232a != a.begin.getNumber()) {
                codedOutputStream.writeEnum(1, this.f232a);
            }
            for (int i = 0; i < this.b.size(); i++) {
                codedOutputStream.writeMessage(2, this.b.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public MessageLite.Builder newBuilderForType() {
            return d.toBuilder();
        }

        public h() {
            this.c = (byte) -1;
            this.f232a = 0;
            this.b = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new b(builderParent);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public h(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            this();
            Objects.requireNonNull(extensionRegistryLite);
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.f232a = codedInputStream.readEnum();
                            } else if (readTag != 18) {
                                if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                if (!(z2 & true)) {
                                    this.b = new ArrayList();
                                    z2 |= true;
                                }
                                this.b.add(codedInputStream.readMessage(C0086c.b(), extensionRegistryLite));
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    } catch (IOException e3) {
                        throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(this);
                    }
                } finally {
                    if (z2 & true) {
                        this.b = Collections.unmodifiableList(this.b);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class i extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final i d = new i();
        public static final Parser<i> e = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f237a;
        public List<C0087c> b;
        public byte c;

        /* loaded from: classes.dex */
        public class a extends AbstractParser<i> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new i(codedInputStream, extensionRegistryLite);
            }
        }

        /* loaded from: classes.dex */
        public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f238a;
            public int b;
            public List<C0087c> c;
            public RepeatedFieldBuilderV3<C0087c, C0087c.b, Object> d;

            public b() {
                this.b = 0;
                this.c = Collections.emptyList();
                d();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.c.i.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.c$i> r1 = a.c.i.e     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.c$i r3 = (a.c.i) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.a(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    a.c$i r4 = (a.c.i) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.a(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: a.c.i.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.c$i$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public b clear() {
                super.clear();
                this.b = 0;
                RepeatedFieldBuilderV3<C0087c, C0087c.b, Object> repeatedFieldBuilderV3 = this.d;
                if (repeatedFieldBuilderV3 == null) {
                    this.c = Collections.emptyList();
                    this.f238a &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message build() {
                i buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            public final RepeatedFieldBuilderV3<C0087c, C0087c.b, Object> c() {
                if (this.d == null) {
                    this.d = new RepeatedFieldBuilderV3<>(this.c, (this.f238a & 1) != 0, getParentForChildren(), isClean());
                    this.c = null;
                }
                return this.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (b) super.mo0clone();
            }

            public final void d() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    c();
                }
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return i.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return c.M;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return c.N.ensureFieldAccessorsInitialized(i.class, b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return i.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite build() {
                i buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clone() {
                return (b) super.mo0clone();
            }

            public b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.b = 0;
                this.c = Collections.emptyList();
                d();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeFrom(Message message) {
                if (message instanceof i) {
                    return a((i) message);
                }
                super.mergeFrom(message);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Object mo0clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public i buildPartial() {
                i iVar = new i(this);
                int i = this.f238a;
                iVar.f237a = this.b;
                RepeatedFieldBuilderV3<C0087c, C0087c.b, Object> repeatedFieldBuilderV3 = this.d;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) != 0) {
                        this.c = Collections.unmodifiableList(this.c);
                        this.f238a &= -2;
                    }
                    iVar.b = this.c;
                } else {
                    iVar.b = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return iVar;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof i) {
                    return a((i) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public b a(i iVar) {
                if (iVar == i.d) {
                    return this;
                }
                int i = iVar.f237a;
                if (i != 0) {
                    this.b = i;
                    onChanged();
                }
                if (this.d == null) {
                    if (!iVar.b.isEmpty()) {
                        if (this.c.isEmpty()) {
                            this.c = iVar.b;
                            this.f238a &= -2;
                        } else {
                            if ((this.f238a & 1) == 0) {
                                this.c = new ArrayList(this.c);
                                this.f238a |= 1;
                            }
                            this.c.addAll(iVar.b);
                        }
                        onChanged();
                    }
                } else if (!iVar.b.isEmpty()) {
                    if (this.d.isEmpty()) {
                        this.d.dispose();
                        this.d = null;
                        this.c = iVar.b;
                        this.f238a &= -2;
                        this.d = GeneratedMessageV3.alwaysUseFieldBuilders ? c() : null;
                    } else {
                        this.d.addAllMessages(iVar.b);
                    }
                }
                b bVar = (b) super.mergeUnknownFields(iVar.unknownFields);
                onChanged();
                return this;
            }
        }

        /* renamed from: a.c$i$c  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0087c extends GeneratedMessageV3 implements MessageOrBuilder {
            public static final C0087c d = new C0087c();
            public static final Parser<C0087c> e = new a();

            /* renamed from: a  reason: collision with root package name */
            public int f239a;
            public int b;
            public byte c;

            /* renamed from: a.c$i$c$a */
            /* loaded from: classes.dex */
            public class a extends AbstractParser<C0087c> {
                @Override // com.google.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    return new C0087c(codedInputStream, extensionRegistryLite);
                }
            }

            /* renamed from: a.c$i$c$b */
            /* loaded from: classes.dex */
            public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

                /* renamed from: a  reason: collision with root package name */
                public int f240a;
                public int b;

                public b() {
                    c();
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public a.c.i.C0087c.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser<a.c$i$c> r1 = a.c.i.C0087c.e     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        a.c$i$c r3 = (a.c.i.C0087c) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        if (r3 == 0) goto Le
                        r2.a(r3)
                    Le:
                        return r2
                    Lf:
                        r3 = move-exception
                        goto L1f
                    L11:
                        r3 = move-exception
                        com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                        a.c$i$c r4 = (a.c.i.C0087c) r4     // Catch: java.lang.Throwable -> Lf
                        java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                        throw r3     // Catch: java.lang.Throwable -> L1d
                    L1d:
                        r3 = move-exception
                        r0 = r4
                    L1f:
                        if (r0 == 0) goto L24
                        r2.a(r0)
                    L24:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: a.c.i.C0087c.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.c$i$c$b");
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: b */
                public b clear() {
                    super.clear();
                    this.f240a = 0;
                    this.b = 0;
                    return this;
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Message build() {
                    C0087c buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
                }

                public final void c() {
                    boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (b) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public Message getDefaultInstanceForType() {
                    return C0087c.d;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return c.O;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return c.P.ensureFieldAccessorsInitialized(C0087c.class, b.class);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (b) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.setUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (b) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public AbstractMessageLite.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public MessageLite getDefaultInstanceForType() {
                    return C0087c.d;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (b) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.setUnknownFields(unknownFieldSet);
                }

                public b(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    c();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public MessageLite build() {
                    C0087c buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public MessageLite.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder mergeFrom(Message message) {
                    if (message instanceof C0087c) {
                        return a((C0087c) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone */
                public Object mo0clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a */
                public C0087c buildPartial() {
                    C0087c c0087c = new C0087c(this);
                    c0087c.f239a = this.f240a;
                    c0087c.b = this.b;
                    onBuilt();
                    return c0087c;
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder mergeFrom(Message message) {
                    if (message instanceof C0087c) {
                        return a((C0087c) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public b a(C0087c c0087c) {
                    if (c0087c == C0087c.d) {
                        return this;
                    }
                    int i = c0087c.f239a;
                    if (i != 0) {
                        this.f240a = i;
                        onChanged();
                    }
                    int i2 = c0087c.b;
                    if (i2 != 0) {
                        this.b = i2;
                        onChanged();
                    }
                    b bVar = (b) super.mergeUnknownFields(c0087c.unknownFields);
                    onChanged();
                    return this;
                }
            }

            public C0087c(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.c = (byte) -1;
            }

            public static Parser<C0087c> b() {
                return e;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: c */
            public b toBuilder() {
                if (this == d) {
                    return new b();
                }
                return new b().a(this);
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof C0087c)) {
                    return super.equals(obj);
                }
                C0087c c0087c = (C0087c) obj;
                return this.f239a == c0087c.f239a && this.b == c0087c.b && this.unknownFields.equals(c0087c.unknownFields);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<C0087c> getParserForType() {
                return e;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = this.f239a;
                int computeUInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeUInt32Size(1, i2) : 0;
                int i3 = this.b;
                if (i3 != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(2, i3);
                }
                int serializedSize = computeUInt32Size + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                int i = this.memoizedHashCode;
                if (i != 0) {
                    return i;
                }
                int hashCode = ((((((((((c.O.hashCode() + 779) * 37) + 1) * 53) + this.f239a) * 37) + 2) * 53) + this.b) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode;
                return hashCode;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return c.P.ensureFieldAccessorsInitialized(C0087c.class, b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                byte b2 = this.c;
                if (b2 == 1) {
                    return true;
                }
                if (b2 == 0) {
                    return false;
                }
                this.c = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Message.Builder newBuilderForType() {
                return d.toBuilder();
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new C0087c();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) {
                int i = this.f239a;
                if (i != 0) {
                    codedOutputStream.writeUInt32(1, i);
                }
                int i2 = this.b;
                if (i2 != 0) {
                    codedOutputStream.writeUInt32(2, i2);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return d;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public MessageLite.Builder newBuilderForType() {
                return d.toBuilder();
            }

            public C0087c() {
                this.c = (byte) -1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new b(builderParent);
            }

            public C0087c(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this();
                Objects.requireNonNull(extensionRegistryLite);
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag == 8) {
                                        this.f239a = codedInputStream.readUInt32();
                                    } else if (readTag != 16) {
                                        if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        }
                                    } else {
                                        this.b = codedInputStream.readUInt32();
                                    }
                                }
                                z = true;
                            } catch (IOException e2) {
                                throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                            }
                        } catch (InvalidProtocolBufferException e3) {
                            throw e3.setUnfinishedMessage(this);
                        }
                    } finally {
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }
        }

        public i(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.c = (byte) -1;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: c */
        public b toBuilder() {
            if (this == d) {
                return new b();
            }
            return new b().a(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof i)) {
                return super.equals(obj);
            }
            i iVar = (i) obj;
            return this.f237a == iVar.f237a && this.b.equals(iVar.b) && this.unknownFields.equals(iVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<i> getParserForType() {
            return e;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = this.f237a != a.begin.getNumber() ? CodedOutputStream.computeEnumSize(1, this.f237a) + 0 : 0;
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                computeEnumSize += CodedOutputStream.computeMessageSize(2, this.b.get(i2));
            }
            int serializedSize = computeEnumSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = ((((c.M.hashCode() + 779) * 37) + 1) * 53) + this.f237a;
            if (this.b.size() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + this.b.hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return c.N.ensureFieldAccessorsInitialized(i.class, b.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b2 = this.c;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            this.c = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Message.Builder newBuilderForType() {
            return d.toBuilder();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new i();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            if (this.f237a != a.begin.getNumber()) {
                codedOutputStream.writeEnum(1, this.f237a);
            }
            for (int i = 0; i < this.b.size(); i++) {
                codedOutputStream.writeMessage(2, this.b.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public MessageLite.Builder newBuilderForType() {
            return d.toBuilder();
        }

        public i() {
            this.c = (byte) -1;
            this.f237a = 0;
            this.b = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new b(builderParent);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public i(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            this();
            Objects.requireNonNull(extensionRegistryLite);
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.f237a = codedInputStream.readEnum();
                            } else if (readTag != 18) {
                                if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                if (!(z2 & true)) {
                                    this.b = new ArrayList();
                                    z2 |= true;
                                }
                                this.b.add(codedInputStream.readMessage(C0087c.b(), extensionRegistryLite));
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    } catch (IOException e3) {
                        throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(this);
                    }
                } finally {
                    if (z2 & true) {
                        this.b = Collections.unmodifiableList(this.b);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class j extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final j d = new j();
        public static final Parser<j> e = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f241a;
        public List<C0088c> b;
        public byte c;

        /* loaded from: classes.dex */
        public class a extends AbstractParser<j> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new j(codedInputStream, extensionRegistryLite);
            }
        }

        /* loaded from: classes.dex */
        public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f242a;
            public int b;
            public List<C0088c> c;
            public RepeatedFieldBuilderV3<C0088c, C0088c.b, Object> d;

            public b() {
                this.b = 0;
                this.c = Collections.emptyList();
                d();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.c.j.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.c$j> r1 = a.c.j.e     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.c$j r3 = (a.c.j) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.a(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    a.c$j r4 = (a.c.j) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.a(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: a.c.j.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.c$j$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public b clear() {
                super.clear();
                this.b = 0;
                RepeatedFieldBuilderV3<C0088c, C0088c.b, Object> repeatedFieldBuilderV3 = this.d;
                if (repeatedFieldBuilderV3 == null) {
                    this.c = Collections.emptyList();
                    this.f242a &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message build() {
                j buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            public final RepeatedFieldBuilderV3<C0088c, C0088c.b, Object> c() {
                if (this.d == null) {
                    this.d = new RepeatedFieldBuilderV3<>(this.c, (this.f242a & 1) != 0, getParentForChildren(), isClean());
                    this.c = null;
                }
                return this.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (b) super.mo0clone();
            }

            public final void d() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    c();
                }
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return j.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return c.g;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return c.h.ensureFieldAccessorsInitialized(j.class, b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return j.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite build() {
                j buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clone() {
                return (b) super.mo0clone();
            }

            public b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.b = 0;
                this.c = Collections.emptyList();
                d();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeFrom(Message message) {
                if (message instanceof j) {
                    return a((j) message);
                }
                super.mergeFrom(message);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Object mo0clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public j buildPartial() {
                j jVar = new j(this);
                int i = this.f242a;
                jVar.f241a = this.b;
                RepeatedFieldBuilderV3<C0088c, C0088c.b, Object> repeatedFieldBuilderV3 = this.d;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) != 0) {
                        this.c = Collections.unmodifiableList(this.c);
                        this.f242a &= -2;
                    }
                    jVar.b = this.c;
                } else {
                    jVar.b = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return jVar;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof j) {
                    return a((j) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public b a(j jVar) {
                if (jVar == j.d) {
                    return this;
                }
                int i = jVar.f241a;
                if (i != 0) {
                    this.b = i;
                    onChanged();
                }
                if (this.d == null) {
                    if (!jVar.b.isEmpty()) {
                        if (this.c.isEmpty()) {
                            this.c = jVar.b;
                            this.f242a &= -2;
                        } else {
                            if ((this.f242a & 1) == 0) {
                                this.c = new ArrayList(this.c);
                                this.f242a |= 1;
                            }
                            this.c.addAll(jVar.b);
                        }
                        onChanged();
                    }
                } else if (!jVar.b.isEmpty()) {
                    if (this.d.isEmpty()) {
                        this.d.dispose();
                        this.d = null;
                        this.c = jVar.b;
                        this.f242a &= -2;
                        this.d = GeneratedMessageV3.alwaysUseFieldBuilders ? c() : null;
                    } else {
                        this.d.addAllMessages(jVar.b);
                    }
                }
                b bVar = (b) super.mergeUnknownFields(jVar.unknownFields);
                onChanged();
                return this;
            }
        }

        /* renamed from: a.c$j$c  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0088c extends GeneratedMessageV3 implements MessageOrBuilder {
            public static final C0088c d = new C0088c();
            public static final Parser<C0088c> e = new a();

            /* renamed from: a  reason: collision with root package name */
            public int f243a;
            public int b;
            public byte c;

            /* renamed from: a.c$j$c$a */
            /* loaded from: classes.dex */
            public class a extends AbstractParser<C0088c> {
                @Override // com.google.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    return new C0088c(codedInputStream, extensionRegistryLite);
                }
            }

            /* renamed from: a.c$j$c$b */
            /* loaded from: classes.dex */
            public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

                /* renamed from: a  reason: collision with root package name */
                public int f244a;
                public int b;

                public b() {
                    this.b = 0;
                    c();
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public a.c.j.C0088c.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser<a.c$j$c> r1 = a.c.j.C0088c.e     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        a.c$j$c r3 = (a.c.j.C0088c) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        if (r3 == 0) goto Le
                        r2.a(r3)
                    Le:
                        return r2
                    Lf:
                        r3 = move-exception
                        goto L1f
                    L11:
                        r3 = move-exception
                        com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                        a.c$j$c r4 = (a.c.j.C0088c) r4     // Catch: java.lang.Throwable -> Lf
                        java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                        throw r3     // Catch: java.lang.Throwable -> L1d
                    L1d:
                        r3 = move-exception
                        r0 = r4
                    L1f:
                        if (r0 == 0) goto L24
                        r2.a(r0)
                    L24:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: a.c.j.C0088c.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.c$j$c$b");
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: b */
                public b clear() {
                    super.clear();
                    this.f244a = 0;
                    this.b = 0;
                    return this;
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Message build() {
                    C0088c buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
                }

                public final void c() {
                    boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (b) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public Message getDefaultInstanceForType() {
                    return C0088c.d;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return c.i;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return c.j.ensureFieldAccessorsInitialized(C0088c.class, b.class);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (b) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.setUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (b) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public AbstractMessageLite.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public MessageLite getDefaultInstanceForType() {
                    return C0088c.d;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (b) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.setUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                public b(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.b = 0;
                    c();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public MessageLite build() {
                    C0088c buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public MessageLite.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder mergeFrom(Message message) {
                    if (message instanceof C0088c) {
                        return a((C0088c) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone */
                public Object mo0clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a */
                public C0088c buildPartial() {
                    C0088c c0088c = new C0088c(this);
                    c0088c.f243a = this.f244a;
                    c0088c.b = this.b;
                    onBuilt();
                    return c0088c;
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder mergeFrom(Message message) {
                    if (message instanceof C0088c) {
                        return a((C0088c) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public b a(C0088c c0088c) {
                    if (c0088c == C0088c.d) {
                        return this;
                    }
                    int i = c0088c.f243a;
                    if (i != 0) {
                        this.f244a = i;
                        onChanged();
                    }
                    int i2 = c0088c.b;
                    if (i2 != 0) {
                        this.b = i2;
                        onChanged();
                    }
                    b bVar = (b) super.mergeUnknownFields(c0088c.unknownFields);
                    onChanged();
                    return this;
                }
            }

            /* renamed from: a.c$j$c$c  reason: collision with other inner class name */
            /* loaded from: classes.dex */
            public enum EnumC0089c implements ProtocolMessageEnum {
                activity(0),
                enter(1),
                wake(2),
                rem(3),
                light(4),
                deep(5),
                quit(6),
                unknown(7),
                summary(8),
                UNRECOGNIZED(-1);
                

                /* renamed from: a  reason: collision with root package name */
                public final int f245a;

                static {
                    values();
                }

                EnumC0089c(int i) {
                    this.f245a = i;
                }

                public static EnumC0089c a(int i) {
                    switch (i) {
                        case 0:
                            return activity;
                        case 1:
                            return enter;
                        case 2:
                            return wake;
                        case 3:
                            return rem;
                        case 4:
                            return light;
                        case 5:
                            return deep;
                        case 6:
                            return quit;
                        case 7:
                            return unknown;
                        case 8:
                            return summary;
                        default:
                            return null;
                    }
                }

                @Override // com.google.protobuf.ProtocolMessageEnum
                public final Descriptors.EnumDescriptor getDescriptorForType() {
                    return C0088c.b().getEnumTypes().get(0);
                }

                @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
                public final int getNumber() {
                    if (this != UNRECOGNIZED) {
                        return this.f245a;
                    }
                    throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
                }

                @Override // com.google.protobuf.ProtocolMessageEnum
                public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                    if (this != UNRECOGNIZED) {
                        return C0088c.b().getEnumTypes().get(0).getValues().get(ordinal());
                    }
                    throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
                }
            }

            public C0088c(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.c = (byte) -1;
            }

            public static final Descriptors.Descriptor b() {
                return c.i;
            }

            public static Parser<C0088c> c() {
                return e;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: d */
            public b toBuilder() {
                if (this == d) {
                    return new b();
                }
                return new b().a(this);
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof C0088c)) {
                    return super.equals(obj);
                }
                C0088c c0088c = (C0088c) obj;
                return this.f243a == c0088c.f243a && this.b == c0088c.b && this.unknownFields.equals(c0088c.unknownFields);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<C0088c> getParserForType() {
                return e;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = this.f243a;
                int computeUInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeUInt32Size(1, i2) : 0;
                if (this.b != EnumC0089c.activity.getNumber()) {
                    computeUInt32Size += CodedOutputStream.computeEnumSize(2, this.b);
                }
                int serializedSize = computeUInt32Size + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                int i = this.memoizedHashCode;
                if (i != 0) {
                    return i;
                }
                int hashCode = ((((((((((c.i.hashCode() + 779) * 37) + 1) * 53) + this.f243a) * 37) + 2) * 53) + this.b) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode;
                return hashCode;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return c.j.ensureFieldAccessorsInitialized(C0088c.class, b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                byte b2 = this.c;
                if (b2 == 1) {
                    return true;
                }
                if (b2 == 0) {
                    return false;
                }
                this.c = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Message.Builder newBuilderForType() {
                return d.toBuilder();
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new C0088c();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) {
                int i = this.f243a;
                if (i != 0) {
                    codedOutputStream.writeUInt32(1, i);
                }
                if (this.b != EnumC0089c.activity.getNumber()) {
                    codedOutputStream.writeEnum(2, this.b);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return d;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public MessageLite.Builder newBuilderForType() {
                return d.toBuilder();
            }

            public C0088c() {
                this.c = (byte) -1;
                this.b = 0;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new b(builderParent);
            }

            public C0088c(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this();
                Objects.requireNonNull(extensionRegistryLite);
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag == 8) {
                                        this.f243a = codedInputStream.readUInt32();
                                    } else if (readTag != 16) {
                                        if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        }
                                    } else {
                                        this.b = codedInputStream.readEnum();
                                    }
                                }
                                z = true;
                            } catch (IOException e2) {
                                throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                            }
                        } catch (InvalidProtocolBufferException e3) {
                            throw e3.setUnfinishedMessage(this);
                        }
                    } finally {
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }
        }

        public j(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.c = (byte) -1;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: c */
        public b toBuilder() {
            if (this == d) {
                return new b();
            }
            return new b().a(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof j)) {
                return super.equals(obj);
            }
            j jVar = (j) obj;
            return this.f241a == jVar.f241a && this.b.equals(jVar.b) && this.unknownFields.equals(jVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<j> getParserForType() {
            return e;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = this.f241a != a.begin.getNumber() ? CodedOutputStream.computeEnumSize(1, this.f241a) + 0 : 0;
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                computeEnumSize += CodedOutputStream.computeMessageSize(2, this.b.get(i2));
            }
            int serializedSize = computeEnumSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = ((((c.g.hashCode() + 779) * 37) + 1) * 53) + this.f241a;
            if (this.b.size() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + this.b.hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return c.h.ensureFieldAccessorsInitialized(j.class, b.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b2 = this.c;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            this.c = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Message.Builder newBuilderForType() {
            return d.toBuilder();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new j();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            if (this.f241a != a.begin.getNumber()) {
                codedOutputStream.writeEnum(1, this.f241a);
            }
            for (int i = 0; i < this.b.size(); i++) {
                codedOutputStream.writeMessage(2, this.b.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public MessageLite.Builder newBuilderForType() {
            return d.toBuilder();
        }

        public j() {
            this.c = (byte) -1;
            this.f241a = 0;
            this.b = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new b(builderParent);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public j(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            this();
            Objects.requireNonNull(extensionRegistryLite);
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.f241a = codedInputStream.readEnum();
                            } else if (readTag != 18) {
                                if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                if (!(z2 & true)) {
                                    this.b = new ArrayList();
                                    z2 |= true;
                                }
                                this.b.add(codedInputStream.readMessage(C0088c.c(), extensionRegistryLite));
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    } catch (IOException e3) {
                        throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(this);
                    }
                } finally {
                    if (z2 & true) {
                        this.b = Collections.unmodifiableList(this.b);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class k extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final k d = new k();
        public static final Parser<k> e = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f246a;
        public List<C0090c> b;
        public byte c;

        /* loaded from: classes.dex */
        public class a extends AbstractParser<k> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new k(codedInputStream, extensionRegistryLite);
            }
        }

        /* loaded from: classes.dex */
        public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f247a;
            public int b;
            public List<C0090c> c;
            public RepeatedFieldBuilderV3<C0090c, C0090c.b, Object> d;

            public b() {
                this.b = 0;
                this.c = Collections.emptyList();
                d();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.c.k.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.c$k> r1 = a.c.k.e     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.c$k r3 = (a.c.k) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.a(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    a.c$k r4 = (a.c.k) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.a(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: a.c.k.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.c$k$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public b clear() {
                super.clear();
                this.b = 0;
                RepeatedFieldBuilderV3<C0090c, C0090c.b, Object> repeatedFieldBuilderV3 = this.d;
                if (repeatedFieldBuilderV3 == null) {
                    this.c = Collections.emptyList();
                    this.f247a &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message build() {
                k buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            public final RepeatedFieldBuilderV3<C0090c, C0090c.b, Object> c() {
                if (this.d == null) {
                    this.d = new RepeatedFieldBuilderV3<>(this.c, (this.f247a & 1) != 0, getParentForChildren(), isClean());
                    this.c = null;
                }
                return this.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (b) super.mo0clone();
            }

            public final void d() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    c();
                }
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return k.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return c.c;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return c.d.ensureFieldAccessorsInitialized(k.class, b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return k.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite build() {
                k buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clone() {
                return (b) super.mo0clone();
            }

            public b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.b = 0;
                this.c = Collections.emptyList();
                d();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeFrom(Message message) {
                if (message instanceof k) {
                    return a((k) message);
                }
                super.mergeFrom(message);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Object mo0clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public k buildPartial() {
                k kVar = new k(this);
                int i = this.f247a;
                kVar.f246a = this.b;
                RepeatedFieldBuilderV3<C0090c, C0090c.b, Object> repeatedFieldBuilderV3 = this.d;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) != 0) {
                        this.c = Collections.unmodifiableList(this.c);
                        this.f247a &= -2;
                    }
                    kVar.b = this.c;
                } else {
                    kVar.b = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return kVar;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof k) {
                    return a((k) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public b a(k kVar) {
                if (kVar == k.d) {
                    return this;
                }
                int i = kVar.f246a;
                if (i != 0) {
                    this.b = i;
                    onChanged();
                }
                if (this.d == null) {
                    if (!kVar.b.isEmpty()) {
                        if (this.c.isEmpty()) {
                            this.c = kVar.b;
                            this.f247a &= -2;
                        } else {
                            if ((this.f247a & 1) == 0) {
                                this.c = new ArrayList(this.c);
                                this.f247a |= 1;
                            }
                            this.c.addAll(kVar.b);
                        }
                        onChanged();
                    }
                } else if (!kVar.b.isEmpty()) {
                    if (this.d.isEmpty()) {
                        this.d.dispose();
                        this.d = null;
                        this.c = kVar.b;
                        this.f247a &= -2;
                        this.d = GeneratedMessageV3.alwaysUseFieldBuilders ? c() : null;
                    } else {
                        this.d.addAllMessages(kVar.b);
                    }
                }
                b bVar = (b) super.mergeUnknownFields(kVar.unknownFields);
                onChanged();
                return this;
            }
        }

        /* renamed from: a.c$k$c  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0090c extends GeneratedMessageV3 implements MessageOrBuilder {
            public static final C0090c h = new C0090c();
            public static final Parser<C0090c> i = new a();

            /* renamed from: a  reason: collision with root package name */
            public int f248a;
            public int b;
            public int c;
            public int d;
            public int e;
            public int f;
            public byte g;

            /* renamed from: a.c$k$c$a */
            /* loaded from: classes.dex */
            public class a extends AbstractParser<C0090c> {
                @Override // com.google.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    return new C0090c(codedInputStream, extensionRegistryLite);
                }
            }

            /* renamed from: a.c$k$c$b */
            /* loaded from: classes.dex */
            public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

                /* renamed from: a  reason: collision with root package name */
                public int f249a;
                public int b;
                public int c;
                public int d;
                public int e;
                public int f;

                public b() {
                    c();
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public a.c.k.C0090c.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser<a.c$k$c> r1 = a.c.k.C0090c.i     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        a.c$k$c r3 = (a.c.k.C0090c) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        if (r3 == 0) goto Le
                        r2.a(r3)
                    Le:
                        return r2
                    Lf:
                        r3 = move-exception
                        goto L1f
                    L11:
                        r3 = move-exception
                        com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                        a.c$k$c r4 = (a.c.k.C0090c) r4     // Catch: java.lang.Throwable -> Lf
                        java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                        throw r3     // Catch: java.lang.Throwable -> L1d
                    L1d:
                        r3 = move-exception
                        r0 = r4
                    L1f:
                        if (r0 == 0) goto L24
                        r2.a(r0)
                    L24:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: a.c.k.C0090c.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.c$k$c$b");
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: b */
                public b clear() {
                    super.clear();
                    this.f249a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.f = 0;
                    return this;
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Message build() {
                    C0090c buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
                }

                public final void c() {
                    boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (b) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public Message getDefaultInstanceForType() {
                    return C0090c.h;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return c.e;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return c.f.ensureFieldAccessorsInitialized(C0090c.class, b.class);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (b) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.setUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (b) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public AbstractMessageLite.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public MessageLite getDefaultInstanceForType() {
                    return C0090c.h;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (b) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.setUnknownFields(unknownFieldSet);
                }

                public b(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    c();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public MessageLite build() {
                    C0090c buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public MessageLite.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder mergeFrom(Message message) {
                    if (message instanceof C0090c) {
                        return a((C0090c) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone */
                public Object mo0clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a */
                public C0090c buildPartial() {
                    C0090c c0090c = new C0090c(this);
                    c0090c.f248a = this.f249a;
                    c0090c.b = this.b;
                    c0090c.c = this.c;
                    c0090c.d = this.d;
                    c0090c.e = this.e;
                    c0090c.f = this.f;
                    onBuilt();
                    return c0090c;
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder mergeFrom(Message message) {
                    if (message instanceof C0090c) {
                        return a((C0090c) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public b a(C0090c c0090c) {
                    if (c0090c == C0090c.h) {
                        return this;
                    }
                    int i = c0090c.f248a;
                    if (i != 0) {
                        this.f249a = i;
                        onChanged();
                    }
                    int i2 = c0090c.b;
                    if (i2 != 0) {
                        this.b = i2;
                        onChanged();
                    }
                    int i3 = c0090c.c;
                    if (i3 != 0) {
                        this.c = i3;
                        onChanged();
                    }
                    int i4 = c0090c.d;
                    if (i4 != 0) {
                        this.d = i4;
                        onChanged();
                    }
                    int i5 = c0090c.e;
                    if (i5 != 0) {
                        this.e = i5;
                        onChanged();
                    }
                    int i6 = c0090c.f;
                    if (i6 != 0) {
                        this.f = i6;
                        onChanged();
                    }
                    b bVar = (b) super.mergeUnknownFields(c0090c.unknownFields);
                    onChanged();
                    return this;
                }
            }

            public C0090c(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.g = (byte) -1;
            }

            public static Parser<C0090c> b() {
                return i;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: c */
            public b toBuilder() {
                if (this == h) {
                    return new b();
                }
                return new b().a(this);
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof C0090c)) {
                    return super.equals(obj);
                }
                C0090c c0090c = (C0090c) obj;
                return this.f248a == c0090c.f248a && this.b == c0090c.b && this.c == c0090c.c && this.d == c0090c.d && this.e == c0090c.e && this.f == c0090c.f && this.unknownFields.equals(c0090c.unknownFields);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return h;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<C0090c> getParserForType() {
                return i;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i2 = this.memoizedSize;
                if (i2 != -1) {
                    return i2;
                }
                int i3 = this.f248a;
                int computeUInt32Size = i3 != 0 ? 0 + CodedOutputStream.computeUInt32Size(1, i3) : 0;
                int i4 = this.b;
                if (i4 != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(2, i4);
                }
                int i5 = this.c;
                if (i5 != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(3, i5);
                }
                int i6 = this.d;
                if (i6 != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(4, i6);
                }
                int i7 = this.e;
                if (i7 != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(5, i7);
                }
                int i8 = this.f;
                if (i8 != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(6, i8);
                }
                int serializedSize = computeUInt32Size + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                int i2 = this.memoizedHashCode;
                if (i2 != 0) {
                    return i2;
                }
                int hashCode = ((((((((((((((((((((((((((c.e.hashCode() + 779) * 37) + 1) * 53) + this.f248a) * 37) + 2) * 53) + this.b) * 37) + 3) * 53) + this.c) * 37) + 4) * 53) + this.d) * 37) + 5) * 53) + this.e) * 37) + 6) * 53) + this.f) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode;
                return hashCode;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return c.f.ensureFieldAccessorsInitialized(C0090c.class, b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                byte b2 = this.g;
                if (b2 == 1) {
                    return true;
                }
                if (b2 == 0) {
                    return false;
                }
                this.g = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Message.Builder newBuilderForType() {
                return h.toBuilder();
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new C0090c();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) {
                int i2 = this.f248a;
                if (i2 != 0) {
                    codedOutputStream.writeUInt32(1, i2);
                }
                int i3 = this.b;
                if (i3 != 0) {
                    codedOutputStream.writeUInt32(2, i3);
                }
                int i4 = this.c;
                if (i4 != 0) {
                    codedOutputStream.writeUInt32(3, i4);
                }
                int i5 = this.d;
                if (i5 != 0) {
                    codedOutputStream.writeUInt32(4, i5);
                }
                int i6 = this.e;
                if (i6 != 0) {
                    codedOutputStream.writeUInt32(5, i6);
                }
                int i7 = this.f;
                if (i7 != 0) {
                    codedOutputStream.writeUInt32(6, i7);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return h;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public MessageLite.Builder newBuilderForType() {
                return h.toBuilder();
            }

            public C0090c() {
                this.g = (byte) -1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new b(builderParent);
            }

            public C0090c(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this();
                Objects.requireNonNull(extensionRegistryLite);
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.f248a = codedInputStream.readUInt32();
                                } else if (readTag == 16) {
                                    this.b = codedInputStream.readUInt32();
                                } else if (readTag == 24) {
                                    this.c = codedInputStream.readUInt32();
                                } else if (readTag == 32) {
                                    this.d = codedInputStream.readUInt32();
                                } else if (readTag == 40) {
                                    this.e = codedInputStream.readUInt32();
                                } else if (readTag != 48) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.f = codedInputStream.readUInt32();
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } finally {
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }
        }

        public k(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.c = (byte) -1;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: c */
        public b toBuilder() {
            if (this == d) {
                return new b();
            }
            return new b().a(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof k)) {
                return super.equals(obj);
            }
            k kVar = (k) obj;
            return this.f246a == kVar.f246a && this.b.equals(kVar.b) && this.unknownFields.equals(kVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<k> getParserForType() {
            return e;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = this.f246a != a.begin.getNumber() ? CodedOutputStream.computeEnumSize(1, this.f246a) + 0 : 0;
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                computeEnumSize += CodedOutputStream.computeMessageSize(2, this.b.get(i2));
            }
            int serializedSize = computeEnumSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = ((((c.c.hashCode() + 779) * 37) + 1) * 53) + this.f246a;
            if (this.b.size() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + this.b.hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return c.d.ensureFieldAccessorsInitialized(k.class, b.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b2 = this.c;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            this.c = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Message.Builder newBuilderForType() {
            return d.toBuilder();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new k();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            if (this.f246a != a.begin.getNumber()) {
                codedOutputStream.writeEnum(1, this.f246a);
            }
            for (int i = 0; i < this.b.size(); i++) {
                codedOutputStream.writeMessage(2, this.b.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public MessageLite.Builder newBuilderForType() {
            return d.toBuilder();
        }

        public k() {
            this.c = (byte) -1;
            this.f246a = 0;
            this.b = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new b(builderParent);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public k(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            this();
            Objects.requireNonNull(extensionRegistryLite);
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.f246a = codedInputStream.readEnum();
                            } else if (readTag != 18) {
                                if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                if (!(z2 & true)) {
                                    this.b = new ArrayList();
                                    z2 |= true;
                                }
                                this.b.add(codedInputStream.readMessage(C0090c.b(), extensionRegistryLite));
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    } catch (IOException e3) {
                        throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(this);
                    }
                } finally {
                    if (z2 & true) {
                        this.b = Collections.unmodifiableList(this.b);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class l extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final l d = new l();
        public static final Parser<l> e = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f250a;
        public List<C0091c> b;
        public byte c;

        /* loaded from: classes.dex */
        public class a extends AbstractParser<l> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new l(codedInputStream, extensionRegistryLite);
            }
        }

        /* loaded from: classes.dex */
        public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f251a;
            public int b;
            public List<C0091c> c;
            public RepeatedFieldBuilderV3<C0091c, C0091c.b, Object> d;

            public b() {
                this.b = 0;
                this.c = Collections.emptyList();
                d();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.c.l.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.c$l> r1 = a.c.l.e     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.c$l r3 = (a.c.l) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.a(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    a.c$l r4 = (a.c.l) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.a(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: a.c.l.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.c$l$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public b clear() {
                super.clear();
                this.b = 0;
                RepeatedFieldBuilderV3<C0091c, C0091c.b, Object> repeatedFieldBuilderV3 = this.d;
                if (repeatedFieldBuilderV3 == null) {
                    this.c = Collections.emptyList();
                    this.f251a &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message build() {
                l buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            public final RepeatedFieldBuilderV3<C0091c, C0091c.b, Object> c() {
                if (this.d == null) {
                    this.d = new RepeatedFieldBuilderV3<>(this.c, (this.f251a & 1) != 0, getParentForChildren(), isClean());
                    this.c = null;
                }
                return this.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (b) super.mo0clone();
            }

            public final void d() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    c();
                }
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return l.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return c.E;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return c.F.ensureFieldAccessorsInitialized(l.class, b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return l.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite build() {
                l buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clone() {
                return (b) super.mo0clone();
            }

            public b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.b = 0;
                this.c = Collections.emptyList();
                d();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeFrom(Message message) {
                if (message instanceof l) {
                    return a((l) message);
                }
                super.mergeFrom(message);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Object mo0clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public l buildPartial() {
                l lVar = new l(this);
                int i = this.f251a;
                lVar.f250a = this.b;
                RepeatedFieldBuilderV3<C0091c, C0091c.b, Object> repeatedFieldBuilderV3 = this.d;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) != 0) {
                        this.c = Collections.unmodifiableList(this.c);
                        this.f251a &= -2;
                    }
                    lVar.b = this.c;
                } else {
                    lVar.b = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return lVar;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof l) {
                    return a((l) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public b a(l lVar) {
                if (lVar == l.d) {
                    return this;
                }
                int i = lVar.f250a;
                if (i != 0) {
                    this.b = i;
                    onChanged();
                }
                if (this.d == null) {
                    if (!lVar.b.isEmpty()) {
                        if (this.c.isEmpty()) {
                            this.c = lVar.b;
                            this.f251a &= -2;
                        } else {
                            if ((this.f251a & 1) == 0) {
                                this.c = new ArrayList(this.c);
                                this.f251a |= 1;
                            }
                            this.c.addAll(lVar.b);
                        }
                        onChanged();
                    }
                } else if (!lVar.b.isEmpty()) {
                    if (this.d.isEmpty()) {
                        this.d.dispose();
                        this.d = null;
                        this.c = lVar.b;
                        this.f251a &= -2;
                        this.d = GeneratedMessageV3.alwaysUseFieldBuilders ? c() : null;
                    } else {
                        this.d.addAllMessages(lVar.b);
                    }
                }
                b bVar = (b) super.mergeUnknownFields(lVar.unknownFields);
                onChanged();
                return this;
            }
        }

        /* renamed from: a.c$l$c  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0091c extends GeneratedMessageV3 implements MessageOrBuilder {
            public static final C0091c d = new C0091c();
            public static final Parser<C0091c> e = new a();

            /* renamed from: a  reason: collision with root package name */
            public int f252a;
            public int b;
            public byte c;

            /* renamed from: a.c$l$c$a */
            /* loaded from: classes.dex */
            public class a extends AbstractParser<C0091c> {
                @Override // com.google.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    return new C0091c(codedInputStream, extensionRegistryLite);
                }
            }

            /* renamed from: a.c$l$c$b */
            /* loaded from: classes.dex */
            public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

                /* renamed from: a  reason: collision with root package name */
                public int f253a;
                public int b;

                public b() {
                    c();
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public a.c.l.C0091c.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser<a.c$l$c> r1 = a.c.l.C0091c.e     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        a.c$l$c r3 = (a.c.l.C0091c) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        if (r3 == 0) goto Le
                        r2.a(r3)
                    Le:
                        return r2
                    Lf:
                        r3 = move-exception
                        goto L1f
                    L11:
                        r3 = move-exception
                        com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                        a.c$l$c r4 = (a.c.l.C0091c) r4     // Catch: java.lang.Throwable -> Lf
                        java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                        throw r3     // Catch: java.lang.Throwable -> L1d
                    L1d:
                        r3 = move-exception
                        r0 = r4
                    L1f:
                        if (r0 == 0) goto L24
                        r2.a(r0)
                    L24:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: a.c.l.C0091c.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.c$l$c$b");
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: b */
                public b clear() {
                    super.clear();
                    this.f253a = 0;
                    this.b = 0;
                    return this;
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Message build() {
                    C0091c buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
                }

                public final void c() {
                    boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (b) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public Message getDefaultInstanceForType() {
                    return C0091c.d;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return c.G;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return c.H.ensureFieldAccessorsInitialized(C0091c.class, b.class);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (b) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.setUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (b) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public AbstractMessageLite.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public MessageLite getDefaultInstanceForType() {
                    return C0091c.d;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (b) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.setUnknownFields(unknownFieldSet);
                }

                public b(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    c();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public MessageLite build() {
                    C0091c buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public MessageLite.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder mergeFrom(Message message) {
                    if (message instanceof C0091c) {
                        return a((C0091c) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone */
                public Object mo0clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a */
                public C0091c buildPartial() {
                    C0091c c0091c = new C0091c(this);
                    c0091c.f252a = this.f253a;
                    c0091c.b = this.b;
                    onBuilt();
                    return c0091c;
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder mergeFrom(Message message) {
                    if (message instanceof C0091c) {
                        return a((C0091c) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public b a(C0091c c0091c) {
                    if (c0091c == C0091c.d) {
                        return this;
                    }
                    int i = c0091c.f252a;
                    if (i != 0) {
                        this.f253a = i;
                        onChanged();
                    }
                    int i2 = c0091c.b;
                    if (i2 != 0) {
                        this.b = i2;
                        onChanged();
                    }
                    b bVar = (b) super.mergeUnknownFields(c0091c.unknownFields);
                    onChanged();
                    return this;
                }
            }

            public C0091c(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.c = (byte) -1;
            }

            public static Parser<C0091c> b() {
                return e;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: c */
            public b toBuilder() {
                if (this == d) {
                    return new b();
                }
                return new b().a(this);
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof C0091c)) {
                    return super.equals(obj);
                }
                C0091c c0091c = (C0091c) obj;
                return this.f252a == c0091c.f252a && this.b == c0091c.b && this.unknownFields.equals(c0091c.unknownFields);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<C0091c> getParserForType() {
                return e;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = this.f252a;
                int computeUInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeUInt32Size(1, i2) : 0;
                int i3 = this.b;
                if (i3 != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(2, i3);
                }
                int serializedSize = computeUInt32Size + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                int i = this.memoizedHashCode;
                if (i != 0) {
                    return i;
                }
                int hashCode = ((((((((((c.G.hashCode() + 779) * 37) + 1) * 53) + this.f252a) * 37) + 2) * 53) + this.b) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode;
                return hashCode;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return c.H.ensureFieldAccessorsInitialized(C0091c.class, b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                byte b2 = this.c;
                if (b2 == 1) {
                    return true;
                }
                if (b2 == 0) {
                    return false;
                }
                this.c = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Message.Builder newBuilderForType() {
                return d.toBuilder();
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new C0091c();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) {
                int i = this.f252a;
                if (i != 0) {
                    codedOutputStream.writeUInt32(1, i);
                }
                int i2 = this.b;
                if (i2 != 0) {
                    codedOutputStream.writeUInt32(2, i2);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return d;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public MessageLite.Builder newBuilderForType() {
                return d.toBuilder();
            }

            public C0091c() {
                this.c = (byte) -1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new b(builderParent);
            }

            public C0091c(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this();
                Objects.requireNonNull(extensionRegistryLite);
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag == 8) {
                                        this.f252a = codedInputStream.readUInt32();
                                    } else if (readTag != 16) {
                                        if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        }
                                    } else {
                                        this.b = codedInputStream.readUInt32();
                                    }
                                }
                                z = true;
                            } catch (IOException e2) {
                                throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                            }
                        } catch (InvalidProtocolBufferException e3) {
                            throw e3.setUnfinishedMessage(this);
                        }
                    } finally {
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }
        }

        public l(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.c = (byte) -1;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: c */
        public b toBuilder() {
            if (this == d) {
                return new b();
            }
            return new b().a(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof l)) {
                return super.equals(obj);
            }
            l lVar = (l) obj;
            return this.f250a == lVar.f250a && this.b.equals(lVar.b) && this.unknownFields.equals(lVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<l> getParserForType() {
            return e;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = this.f250a != a.begin.getNumber() ? CodedOutputStream.computeEnumSize(1, this.f250a) + 0 : 0;
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                computeEnumSize += CodedOutputStream.computeMessageSize(2, this.b.get(i2));
            }
            int serializedSize = computeEnumSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = ((((c.E.hashCode() + 779) * 37) + 1) * 53) + this.f250a;
            if (this.b.size() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + this.b.hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return c.F.ensureFieldAccessorsInitialized(l.class, b.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b2 = this.c;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            this.c = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Message.Builder newBuilderForType() {
            return d.toBuilder();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new l();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            if (this.f250a != a.begin.getNumber()) {
                codedOutputStream.writeEnum(1, this.f250a);
            }
            for (int i = 0; i < this.b.size(); i++) {
                codedOutputStream.writeMessage(2, this.b.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public MessageLite.Builder newBuilderForType() {
            return d.toBuilder();
        }

        public l() {
            this.c = (byte) -1;
            this.f250a = 0;
            this.b = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new b(builderParent);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public l(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            this();
            Objects.requireNonNull(extensionRegistryLite);
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.f250a = codedInputStream.readEnum();
                            } else if (readTag != 18) {
                                if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                if (!(z2 & true)) {
                                    this.b = new ArrayList();
                                    z2 |= true;
                                }
                                this.b.add(codedInputStream.readMessage(C0091c.b(), extensionRegistryLite));
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    } catch (IOException e3) {
                        throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(this);
                    }
                } finally {
                    if (z2 & true) {
                        this.b = Collections.unmodifiableList(this.b);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class m extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final m d = new m();
        public static final Parser<m> e = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f254a;
        public List<C0092c> b;
        public byte c;

        /* loaded from: classes.dex */
        public class a extends AbstractParser<m> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new m(codedInputStream, extensionRegistryLite);
            }
        }

        /* loaded from: classes.dex */
        public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f255a;
            public int b;
            public List<C0092c> c;
            public RepeatedFieldBuilderV3<C0092c, C0092c.b, Object> d;

            public b() {
                this.b = 0;
                this.c = Collections.emptyList();
                d();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.c.m.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.c$m> r1 = a.c.m.e     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.c$m r3 = (a.c.m) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.a(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    a.c$m r4 = (a.c.m) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.a(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: a.c.m.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.c$m$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public b clear() {
                super.clear();
                this.b = 0;
                RepeatedFieldBuilderV3<C0092c, C0092c.b, Object> repeatedFieldBuilderV3 = this.d;
                if (repeatedFieldBuilderV3 == null) {
                    this.c = Collections.emptyList();
                    this.f255a &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message build() {
                m buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            public final RepeatedFieldBuilderV3<C0092c, C0092c.b, Object> c() {
                if (this.d == null) {
                    this.d = new RepeatedFieldBuilderV3<>(this.c, (this.f255a & 1) != 0, getParentForChildren(), isClean());
                    this.c = null;
                }
                return this.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (b) super.mo0clone();
            }

            public final void d() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    c();
                }
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return m.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return c.I;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return c.J.ensureFieldAccessorsInitialized(m.class, b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return m.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite build() {
                m buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clone() {
                return (b) super.mo0clone();
            }

            public b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.b = 0;
                this.c = Collections.emptyList();
                d();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeFrom(Message message) {
                if (message instanceof m) {
                    return a((m) message);
                }
                super.mergeFrom(message);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Object mo0clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public m buildPartial() {
                m mVar = new m(this);
                int i = this.f255a;
                mVar.f254a = this.b;
                RepeatedFieldBuilderV3<C0092c, C0092c.b, Object> repeatedFieldBuilderV3 = this.d;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) != 0) {
                        this.c = Collections.unmodifiableList(this.c);
                        this.f255a &= -2;
                    }
                    mVar.b = this.c;
                } else {
                    mVar.b = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return mVar;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof m) {
                    return a((m) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public b a(m mVar) {
                if (mVar == m.d) {
                    return this;
                }
                int i = mVar.f254a;
                if (i != 0) {
                    this.b = i;
                    onChanged();
                }
                if (this.d == null) {
                    if (!mVar.b.isEmpty()) {
                        if (this.c.isEmpty()) {
                            this.c = mVar.b;
                            this.f255a &= -2;
                        } else {
                            if ((this.f255a & 1) == 0) {
                                this.c = new ArrayList(this.c);
                                this.f255a |= 1;
                            }
                            this.c.addAll(mVar.b);
                        }
                        onChanged();
                    }
                } else if (!mVar.b.isEmpty()) {
                    if (this.d.isEmpty()) {
                        this.d.dispose();
                        this.d = null;
                        this.c = mVar.b;
                        this.f255a &= -2;
                        this.d = GeneratedMessageV3.alwaysUseFieldBuilders ? c() : null;
                    } else {
                        this.d.addAllMessages(mVar.b);
                    }
                }
                b bVar = (b) super.mergeUnknownFields(mVar.unknownFields);
                onChanged();
                return this;
            }
        }

        /* renamed from: a.c$m$c  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0092c extends GeneratedMessageV3 implements MessageOrBuilder {
            public static final C0092c d = new C0092c();
            public static final Parser<C0092c> e = new a();

            /* renamed from: a  reason: collision with root package name */
            public int f256a;
            public int b;
            public byte c;

            /* renamed from: a.c$m$c$a */
            /* loaded from: classes.dex */
            public class a extends AbstractParser<C0092c> {
                @Override // com.google.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    return new C0092c(codedInputStream, extensionRegistryLite);
                }
            }

            /* renamed from: a.c$m$c$b */
            /* loaded from: classes.dex */
            public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

                /* renamed from: a  reason: collision with root package name */
                public int f257a;
                public int b;

                public b() {
                    c();
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public a.c.m.C0092c.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser<a.c$m$c> r1 = a.c.m.C0092c.e     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        a.c$m$c r3 = (a.c.m.C0092c) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        if (r3 == 0) goto Le
                        r2.a(r3)
                    Le:
                        return r2
                    Lf:
                        r3 = move-exception
                        goto L1f
                    L11:
                        r3 = move-exception
                        com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                        a.c$m$c r4 = (a.c.m.C0092c) r4     // Catch: java.lang.Throwable -> Lf
                        java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                        throw r3     // Catch: java.lang.Throwable -> L1d
                    L1d:
                        r3 = move-exception
                        r0 = r4
                    L1f:
                        if (r0 == 0) goto L24
                        r2.a(r0)
                    L24:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: a.c.m.C0092c.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.c$m$c$b");
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: b */
                public b clear() {
                    super.clear();
                    this.f257a = 0;
                    this.b = 0;
                    return this;
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Message build() {
                    C0092c buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
                }

                public final void c() {
                    boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (b) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public Message getDefaultInstanceForType() {
                    return C0092c.d;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return c.K;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return c.L.ensureFieldAccessorsInitialized(C0092c.class, b.class);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (b) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.setUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (b) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public AbstractMessageLite.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public MessageLite getDefaultInstanceForType() {
                    return C0092c.d;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (b) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.setUnknownFields(unknownFieldSet);
                }

                public b(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    c();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public MessageLite build() {
                    C0092c buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public MessageLite.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder mergeFrom(Message message) {
                    if (message instanceof C0092c) {
                        return a((C0092c) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone */
                public Object mo0clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a */
                public C0092c buildPartial() {
                    C0092c c0092c = new C0092c(this);
                    c0092c.f256a = this.f257a;
                    c0092c.b = this.b;
                    onBuilt();
                    return c0092c;
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder mergeFrom(Message message) {
                    if (message instanceof C0092c) {
                        return a((C0092c) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public b a(C0092c c0092c) {
                    if (c0092c == C0092c.d) {
                        return this;
                    }
                    int i = c0092c.f256a;
                    if (i != 0) {
                        this.f257a = i;
                        onChanged();
                    }
                    int i2 = c0092c.b;
                    if (i2 != 0) {
                        this.b = i2;
                        onChanged();
                    }
                    b bVar = (b) super.mergeUnknownFields(c0092c.unknownFields);
                    onChanged();
                    return this;
                }
            }

            public C0092c(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.c = (byte) -1;
            }

            public static Parser<C0092c> b() {
                return e;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: c */
            public b toBuilder() {
                if (this == d) {
                    return new b();
                }
                return new b().a(this);
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof C0092c)) {
                    return super.equals(obj);
                }
                C0092c c0092c = (C0092c) obj;
                return this.f256a == c0092c.f256a && this.b == c0092c.b && this.unknownFields.equals(c0092c.unknownFields);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<C0092c> getParserForType() {
                return e;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = this.f256a;
                int computeUInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeUInt32Size(1, i2) : 0;
                int i3 = this.b;
                if (i3 != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(2, i3);
                }
                int serializedSize = computeUInt32Size + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                int i = this.memoizedHashCode;
                if (i != 0) {
                    return i;
                }
                int hashCode = ((((((((((c.K.hashCode() + 779) * 37) + 1) * 53) + this.f256a) * 37) + 2) * 53) + this.b) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode;
                return hashCode;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return c.L.ensureFieldAccessorsInitialized(C0092c.class, b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                byte b2 = this.c;
                if (b2 == 1) {
                    return true;
                }
                if (b2 == 0) {
                    return false;
                }
                this.c = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Message.Builder newBuilderForType() {
                return d.toBuilder();
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new C0092c();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) {
                int i = this.f256a;
                if (i != 0) {
                    codedOutputStream.writeUInt32(1, i);
                }
                int i2 = this.b;
                if (i2 != 0) {
                    codedOutputStream.writeUInt32(2, i2);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return d;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public MessageLite.Builder newBuilderForType() {
                return d.toBuilder();
            }

            public C0092c() {
                this.c = (byte) -1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new b(builderParent);
            }

            public C0092c(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this();
                Objects.requireNonNull(extensionRegistryLite);
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag == 8) {
                                        this.f256a = codedInputStream.readUInt32();
                                    } else if (readTag != 16) {
                                        if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        }
                                    } else {
                                        this.b = codedInputStream.readUInt32();
                                    }
                                }
                                z = true;
                            } catch (IOException e2) {
                                throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                            }
                        } catch (InvalidProtocolBufferException e3) {
                            throw e3.setUnfinishedMessage(this);
                        }
                    } finally {
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }
        }

        public m(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.c = (byte) -1;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: c */
        public b toBuilder() {
            if (this == d) {
                return new b();
            }
            return new b().a(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof m)) {
                return super.equals(obj);
            }
            m mVar = (m) obj;
            return this.f254a == mVar.f254a && this.b.equals(mVar.b) && this.unknownFields.equals(mVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<m> getParserForType() {
            return e;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = this.f254a != a.begin.getNumber() ? CodedOutputStream.computeEnumSize(1, this.f254a) + 0 : 0;
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                computeEnumSize += CodedOutputStream.computeMessageSize(2, this.b.get(i2));
            }
            int serializedSize = computeEnumSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = ((((c.I.hashCode() + 779) * 37) + 1) * 53) + this.f254a;
            if (this.b.size() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + this.b.hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return c.J.ensureFieldAccessorsInitialized(m.class, b.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b2 = this.c;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            this.c = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Message.Builder newBuilderForType() {
            return d.toBuilder();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new m();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            if (this.f254a != a.begin.getNumber()) {
                codedOutputStream.writeEnum(1, this.f254a);
            }
            for (int i = 0; i < this.b.size(); i++) {
                codedOutputStream.writeMessage(2, this.b.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public MessageLite.Builder newBuilderForType() {
            return d.toBuilder();
        }

        public m() {
            this.c = (byte) -1;
            this.f254a = 0;
            this.b = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new b(builderParent);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public m(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            this();
            Objects.requireNonNull(extensionRegistryLite);
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.f254a = codedInputStream.readEnum();
                            } else if (readTag != 18) {
                                if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                if (!(z2 & true)) {
                                    this.b = new ArrayList();
                                    z2 |= true;
                                }
                                this.b.add(codedInputStream.readMessage(C0092c.b(), extensionRegistryLite));
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    } catch (IOException e3) {
                        throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(this);
                    }
                } finally {
                    if (z2 & true) {
                        this.b = Collections.unmodifiableList(this.b);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class n extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final n d = new n();
        public static final Parser<n> e = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f258a;
        public List<C0093c> b;
        public byte c;

        /* loaded from: classes.dex */
        public class a extends AbstractParser<n> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new n(codedInputStream, extensionRegistryLite);
            }
        }

        /* loaded from: classes.dex */
        public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f259a;
            public int b;
            public List<C0093c> c;
            public RepeatedFieldBuilderV3<C0093c, C0093c.b, Object> d;

            public b() {
                this.b = 0;
                this.c = Collections.emptyList();
                d();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.c.n.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.c$n> r1 = a.c.n.e     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.c$n r3 = (a.c.n) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.a(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    a.c$n r4 = (a.c.n) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.a(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: a.c.n.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.c$n$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public b clear() {
                super.clear();
                this.b = 0;
                RepeatedFieldBuilderV3<C0093c, C0093c.b, Object> repeatedFieldBuilderV3 = this.d;
                if (repeatedFieldBuilderV3 == null) {
                    this.c = Collections.emptyList();
                    this.f259a &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message build() {
                n buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            public final RepeatedFieldBuilderV3<C0093c, C0093c.b, Object> c() {
                if (this.d == null) {
                    this.d = new RepeatedFieldBuilderV3<>(this.c, (this.f259a & 1) != 0, getParentForChildren(), isClean());
                    this.c = null;
                }
                return this.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (b) super.mo0clone();
            }

            public final void d() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    c();
                }
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return n.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return c.A;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return c.B.ensureFieldAccessorsInitialized(n.class, b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return n.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite build() {
                n buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clone() {
                return (b) super.mo0clone();
            }

            public b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.b = 0;
                this.c = Collections.emptyList();
                d();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeFrom(Message message) {
                if (message instanceof n) {
                    return a((n) message);
                }
                super.mergeFrom(message);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Object mo0clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public n buildPartial() {
                n nVar = new n(this);
                int i = this.f259a;
                nVar.f258a = this.b;
                RepeatedFieldBuilderV3<C0093c, C0093c.b, Object> repeatedFieldBuilderV3 = this.d;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) != 0) {
                        this.c = Collections.unmodifiableList(this.c);
                        this.f259a &= -2;
                    }
                    nVar.b = this.c;
                } else {
                    nVar.b = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return nVar;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof n) {
                    return a((n) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public b a(n nVar) {
                if (nVar == n.d) {
                    return this;
                }
                int i = nVar.f258a;
                if (i != 0) {
                    this.b = i;
                    onChanged();
                }
                if (this.d == null) {
                    if (!nVar.b.isEmpty()) {
                        if (this.c.isEmpty()) {
                            this.c = nVar.b;
                            this.f259a &= -2;
                        } else {
                            if ((this.f259a & 1) == 0) {
                                this.c = new ArrayList(this.c);
                                this.f259a |= 1;
                            }
                            this.c.addAll(nVar.b);
                        }
                        onChanged();
                    }
                } else if (!nVar.b.isEmpty()) {
                    if (this.d.isEmpty()) {
                        this.d.dispose();
                        this.d = null;
                        this.c = nVar.b;
                        this.f259a &= -2;
                        this.d = GeneratedMessageV3.alwaysUseFieldBuilders ? c() : null;
                    } else {
                        this.d.addAllMessages(nVar.b);
                    }
                }
                b bVar = (b) super.mergeUnknownFields(nVar.unknownFields);
                onChanged();
                return this;
            }
        }

        /* renamed from: a.c$n$c  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0093c extends GeneratedMessageV3 implements MessageOrBuilder {
            public static final C0093c e = new C0093c();
            public static final Parser<C0093c> f = new a();

            /* renamed from: a  reason: collision with root package name */
            public int f260a;
            public int b;
            public int c;
            public byte d;

            /* renamed from: a.c$n$c$a */
            /* loaded from: classes.dex */
            public class a extends AbstractParser<C0093c> {
                @Override // com.google.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    return new C0093c(codedInputStream, extensionRegistryLite);
                }
            }

            /* renamed from: a.c$n$c$b */
            /* loaded from: classes.dex */
            public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

                /* renamed from: a  reason: collision with root package name */
                public int f261a;
                public int b;
                public int c;

                public b() {
                    this.c = 0;
                    c();
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public a.c.n.C0093c.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser<a.c$n$c> r1 = a.c.n.C0093c.f     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        a.c$n$c r3 = (a.c.n.C0093c) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                        if (r3 == 0) goto Le
                        r2.a(r3)
                    Le:
                        return r2
                    Lf:
                        r3 = move-exception
                        goto L1f
                    L11:
                        r3 = move-exception
                        com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                        a.c$n$c r4 = (a.c.n.C0093c) r4     // Catch: java.lang.Throwable -> Lf
                        java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                        throw r3     // Catch: java.lang.Throwable -> L1d
                    L1d:
                        r3 = move-exception
                        r0 = r4
                    L1f:
                        if (r0 == 0) goto L24
                        r2.a(r0)
                    L24:
                        throw r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: a.c.n.C0093c.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.c$n$c$b");
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: b */
                public b clear() {
                    super.clear();
                    this.f261a = 0;
                    this.b = 0;
                    this.c = 0;
                    return this;
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Message build() {
                    C0093c buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
                }

                public final void c() {
                    boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (b) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public Message getDefaultInstanceForType() {
                    return C0093c.e;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return c.C;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return c.D.ensureFieldAccessorsInitialized(C0093c.class, b.class);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (b) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.setUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (b) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public AbstractMessageLite.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public MessageLite getDefaultInstanceForType() {
                    return C0093c.e;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (b) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (b) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.setUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (b) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public GeneratedMessageV3.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (b) super.mergeUnknownFields(unknownFieldSet);
                }

                public b(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.c = 0;
                    c();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public MessageLite build() {
                    C0093c buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Message.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public MessageLite.Builder clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public AbstractMessage.Builder mergeFrom(Message message) {
                    if (message instanceof C0093c) {
                        return a((C0093c) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone */
                public Object mo0clone() {
                    return (b) super.mo0clone();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a */
                public C0093c buildPartial() {
                    C0093c c0093c = new C0093c(this);
                    c0093c.f260a = this.f261a;
                    c0093c.b = this.b;
                    c0093c.c = this.c;
                    onBuilt();
                    return c0093c;
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                public Message.Builder mergeFrom(Message message) {
                    if (message instanceof C0093c) {
                        return a((C0093c) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public b a(C0093c c0093c) {
                    if (c0093c == C0093c.e) {
                        return this;
                    }
                    int i = c0093c.f260a;
                    if (i != 0) {
                        this.f261a = i;
                        onChanged();
                    }
                    int i2 = c0093c.b;
                    if (i2 != 0) {
                        this.b = i2;
                        onChanged();
                    }
                    int i3 = c0093c.c;
                    if (i3 != 0) {
                        this.c = i3;
                        onChanged();
                    }
                    b bVar = (b) super.mergeUnknownFields(c0093c.unknownFields);
                    onChanged();
                    return this;
                }
            }

            public C0093c(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.d = (byte) -1;
            }

            public static Parser<C0093c> b() {
                return f;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: c */
            public b toBuilder() {
                if (this == e) {
                    return new b();
                }
                return new b().a(this);
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof C0093c)) {
                    return super.equals(obj);
                }
                C0093c c0093c = (C0093c) obj;
                return this.f260a == c0093c.f260a && this.b == c0093c.b && this.c == c0093c.c && this.unknownFields.equals(c0093c.unknownFields);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return e;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<C0093c> getParserForType() {
                return f;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = this.f260a;
                int computeUInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeUInt32Size(1, i2) : 0;
                int i3 = this.b;
                if (i3 != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(2, i3);
                }
                if (this.c != d.stress_unkown.getNumber()) {
                    computeUInt32Size += CodedOutputStream.computeEnumSize(3, this.c);
                }
                int serializedSize = computeUInt32Size + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                int i = this.memoizedHashCode;
                if (i != 0) {
                    return i;
                }
                int hashCode = ((((((((((((((c.C.hashCode() + 779) * 37) + 1) * 53) + this.f260a) * 37) + 2) * 53) + this.b) * 37) + 3) * 53) + this.c) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode;
                return hashCode;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return c.D.ensureFieldAccessorsInitialized(C0093c.class, b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                byte b2 = this.d;
                if (b2 == 1) {
                    return true;
                }
                if (b2 == 0) {
                    return false;
                }
                this.d = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Message.Builder newBuilderForType() {
                return e.toBuilder();
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new C0093c();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) {
                int i = this.f260a;
                if (i != 0) {
                    codedOutputStream.writeUInt32(1, i);
                }
                int i2 = this.b;
                if (i2 != 0) {
                    codedOutputStream.writeUInt32(2, i2);
                }
                if (this.c != d.stress_unkown.getNumber()) {
                    codedOutputStream.writeEnum(3, this.c);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return e;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public MessageLite.Builder newBuilderForType() {
                return e.toBuilder();
            }

            public C0093c() {
                this.d = (byte) -1;
                this.c = 0;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new b(builderParent);
            }

            public C0093c(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this();
                Objects.requireNonNull(extensionRegistryLite);
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.f260a = codedInputStream.readUInt32();
                                } else if (readTag == 16) {
                                    this.b = codedInputStream.readUInt32();
                                } else if (readTag != 24) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.c = codedInputStream.readEnum();
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e2) {
                            throw e2.setUnfinishedMessage(this);
                        } catch (IOException e3) {
                            throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(this);
                        }
                    } finally {
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }
        }

        /* loaded from: classes.dex */
        public enum d implements ProtocolMessageEnum {
            stress_unkown(0),
            stress_relax(1),
            stress_normal(2),
            stress_middle(3),
            stress_high(4),
            UNRECOGNIZED(-1);
            

            /* renamed from: a  reason: collision with root package name */
            public final int f262a;

            static {
                values();
            }

            d(int i) {
                this.f262a = i;
            }

            public static d a(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                if (i != 4) {
                                    return null;
                                }
                                return stress_high;
                            }
                            return stress_middle;
                        }
                        return stress_normal;
                    }
                    return stress_relax;
                }
                return stress_unkown;
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return n.c().getEnumTypes().get(0);
            }

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.f262a;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                if (this != UNRECOGNIZED) {
                    return n.c().getEnumTypes().get(0).getValues().get(ordinal());
                }
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
        }

        public n(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.c = (byte) -1;
        }

        public static final Descriptors.Descriptor c() {
            return c.A;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: d */
        public b toBuilder() {
            if (this == d) {
                return new b();
            }
            return new b().a(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof n)) {
                return super.equals(obj);
            }
            n nVar = (n) obj;
            return this.f258a == nVar.f258a && this.b.equals(nVar.b) && this.unknownFields.equals(nVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<n> getParserForType() {
            return e;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = this.f258a != a.begin.getNumber() ? CodedOutputStream.computeEnumSize(1, this.f258a) + 0 : 0;
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                computeEnumSize += CodedOutputStream.computeMessageSize(2, this.b.get(i2));
            }
            int serializedSize = computeEnumSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = ((((c.A.hashCode() + 779) * 37) + 1) * 53) + this.f258a;
            if (this.b.size() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + this.b.hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return c.B.ensureFieldAccessorsInitialized(n.class, b.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b2 = this.c;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            this.c = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Message.Builder newBuilderForType() {
            return d.toBuilder();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new n();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            if (this.f258a != a.begin.getNumber()) {
                codedOutputStream.writeEnum(1, this.f258a);
            }
            for (int i = 0; i < this.b.size(); i++) {
                codedOutputStream.writeMessage(2, this.b.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public MessageLite.Builder newBuilderForType() {
            return d.toBuilder();
        }

        public n() {
            this.c = (byte) -1;
            this.f258a = 0;
            this.b = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new b(builderParent);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public n(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            this();
            Objects.requireNonNull(extensionRegistryLite);
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.f258a = codedInputStream.readEnum();
                            } else if (readTag != 18) {
                                if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                if (!(z2 & true)) {
                                    this.b = new ArrayList();
                                    z2 |= true;
                                }
                                this.b.add(codedInputStream.readMessage(C0093c.b(), extensionRegistryLite));
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    } catch (IOException e3) {
                        throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(this);
                    }
                } finally {
                    if (z2 & true) {
                        this.b = Collections.unmodifiableList(this.b);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }
    }

    static {
        Descriptors.FileDescriptor internalBuildGeneratedFileFrom = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001aeastapex_s8800_c8803.proto\"M\n\u0012d8803_data_respond\u0012\u0012\n\nrequest_id\u0018\u0001 \u0001(\r\u0012#\n\re_common_flag\u0018\u0002 \u0001(\u000e2\f.common_flag\"Þ\u0001\n\u0010u8803_sport_data\u0012#\n\re_common_flag\u0018\u0001 \u0001(\u000e2\f.common_flag\u0012(\n\u0007s_index\u0018\u0002 \u0003(\u000b2\u0017.u8803_sport_data.index\u001a{\n\u0005index\u0012\u0012\n\ntime_stamp\u0018\u0001 \u0001(\r\u0012\r\n\u0005steps\u0018\u0002 \u0001(\r\u0012\u000f\n\u0007calorie\u0018\u0003 \u0001(\r\u0012\u0010\n\bdistance\u0018\u0004 \u0001(\r\u0012\u0010\n\bduration\u0018\u0005 \u0001(\r\u0012\u001a\n\u0012average_heart_rate\u0018\u0006 \u0001(\r\"¬\u0002\n\u0010u8803_sleep_data\u0012#\n\re_common_flag\u0018\u0001 \u0001(\u000e2\f.common_flag\u0012(\n\u0007s_index\u0018\u0002 \u0003(\u000b2\u0017.u8803_sleep_data.index\u001aÈ\u0001\n\u0005index\u0012\u0012\n\ntime_stamp\u0018\u0001 \u0001(\r\u00128\n\fe_sleep_node\u0018\u0002 \u0001(\u000e2\".u8803_sleep_data.index.sleep_node\"q\n\nsleep_node\u0012\f\n\bactivity\u0010\u0000\u0012\t\n\u0005enter\u0010\u0001\u0012\b\n\u0004wake\u0010\u0002\u0012\u0007\n\u0003rem\u0010\u0003\u0012\t\n\u0005light\u0010\u0004\u0012\b\n\u0004deep\u0010\u0005\u0012\b\n\u0004quit\u0010\u0006\u0012\u000b\n\u0007unknown\u0010\u0007\u0012\u000b\n\u0007summary\u0010\b\"\u008a\u0001\n\ru8803_hr_data\u0012#\n\re_common_flag\u0018\u0001 \u0001(\u000e2\f.common_flag\u0012%\n\u0007s_index\u0018\u0002 \u0003(\u000b2\u0014.u8803_hr_data.index\u001a-\n\u0005index\u0012\u0012\n\ntime_stamp\u0018\u0001 \u0001(\r\u0012\u0010\n\bhr_value\u0018\u0002 \u0001(\r\"\u009f\u0001\n\u000eu8803_gps_data\u0012#\n\re_common_flag\u0018\u0001 \u0001(\u000e2\f.common_flag\u0012&\n\u0007s_index\u0018\u0002 \u0003(\u000b2\u0015.u8803_gps_data.index\u001a@\n\u0005index\u0012\u0012\n\ntime_stamp\u0018\u0001 \u0001(\r\u0012\u0010\n\blatitude\u0018\u0002 \u0001(\u0001\u0012\u0011\n\tlongitude\u0018\u0003 \u0001(\u0001\"ß\u001a\n\u0017u8803_multi_sports_data\u0012#\n\re_common_flag\u0018\u0001 \u0001(\u000e2\f.common_flag\u0012/\n\u0007s_index\u0018\u0002 \u0003(\u000b2\u001e.u8803_multi_sports_data.index\u001aê\u0004\n\u0005index\u0012-\n\u0006e_type\u0018\u0001 \u0001(\u000e2\u001d.u8803_multi_sports_data.type\u0012\u0018\n\u0010begin_time_stamp\u0018\u0002 \u0001(\r\u0012\u0016\n\u000eend_time_stamp\u0018\u0003 \u0001(\r\u0012\r\n\u0005steps\u0018\u0004 \u0001(\r\u0012\u000f\n\u0007calorie\u0018\u0005 \u0001(\r\u0012\u0010\n\bdistance\u0018\u0006 \u0001(\r\u0012\u0010\n\bduration\u0018\u0007 \u0001(\r\u0012\u001e\n\u0016training_effect_normal\u0018\b \u0001(\r\u0012\u001e\n\u0016training_effect_warmUp\u0018\t \u0001(\r\u0012&\n\u001etraining_effect_fatconsumption\u0018\n \u0001(\r\u0012\u001f\n\u0017training_effect_aerobic\u0018\u000b \u0001(\r\u0012!\n\u0019training_effect_anaerobic\u0018\f \u0001(\r\u0012\u001d\n\u0015training_effect_limit\u0018\r \u0001(\r\u0012\u001a\n\u0012average_heart_rate\u0018\u000e \u0001(\r\u0012\u001b\n\u0013average_temperature\u0018\u000f \u0001(\u0002\u0012\u0015\n\raverage_speed\u0018\u0010 \u0001(\r\u0012\u0014\n\faverage_pace\u0018\u0011 \u0001(\r\u0012\u0019\n\u0011average_step_freq\u0018\u0012 \u0001(\r\u0012\u0016\n\u000eaverage_stride\u0018\u0013 \u0001(\r\u0012\u0018\n\u0010average_altitude\u0018\u0014 \u0001(\r\u0012\u001e\n\u0016average_heart_rate_max\u0018\u0015 \u0001(\r\u0012\u001e\n\u0016average_heart_rate_min\u0018\u0016 \u0001(\r\"\u0080\u0015\n\u0004type\u0012\t\n\u0005daily\u0010\u0000\u0012\u0013\n\u000fourdoor_walking\u0010\u0001\u0012\u0013\n\u000fourdoor_running\u0010\u0002\u0012\u0013\n\u000fourdoor_on_foot\u0010\u0003\u0012\u001d\n\u0019ourdoor_on_mountaineering\u0010\u0004\u0012\u0019\n\u0015ourdoor_trail_running\u0010\u0005\u0012\u0013\n\u000fourdoor_cycling\u0010\u0006\u0012\u0014\n\u0010outdoor_swimming\u0010\u0007\u0012\u0012\n\u000eindoor_walking\u0010\b\u0012\u0012\n\u000eindoor_running\u0010\t\u0012\u0013\n\u000findoor_exercise\u0010\n\u0012\u0012\n\u000eindoor_cycling\u0010\u000b\u0012\u000e\n\nelliptical\u0010\f\u0012\b\n\u0004yoga\u0010\r\u0012\n\n\u0006rowing\u0010\u000e\u0012\u0013\n\u000findoor_swimming\u0010\u000f\u0012\u000b\n\u0007od_rock\u0010\u0010\u0012\f\n\bod_skate\u0010\u0011\u0012\r\n\tod_roller\u0010\u0012\u0012\u000e\n\nod_parkour\u0010\u0013\u0012\u0010\n\fod_parachute\u0010\u0014\u0012\r\n\ttrain_HIT\u0010\u0015\u0012\u0010\n\ftrain_weight\u0010\u0016\u0012\u000f\n\u000btrain_plank\u0010\u0017\u0012\u0011\n\rtrain_jumping\u0010\u0018\u0012\u000f\n\u000btrain_stair\u0010\u0019\u0012\u000e\n\ntrain_core\u0010\u001a\u0012\u000e\n\ntrain_flex\u0010\u001b\u0012\u0011\n\rtrain_pilates\u0010\u001c\u0012\u0011\n\rtrain_stretch\u0010\u001d\u0012\u0012\n\u000etrain_strength\u0010\u001e\u0012\u000f\n\u000btrain_cross\u0010\u001f\u0012\u0012\n\u000etrain_dumbbell\u0010 \u0012\u0012\n\u000etrain_deadlift\u0010!\u0012\r\n\ttrain_sit\u0010\"\u0012\u0013\n\u000ftrain_funcition\u0010#\u0012\u000f\n\u000btrain_upper\u0010$\u0012\u000f\n\u000btrain_lower\u0010%\u0012\r\n\ttrain_abs\u0010&\u0012\u000e\n\ntrain_back\u0010'\u0012\u0012\n\u000ewater_sailboat\u0010(\u0012\r\n\twater_sup\u0010)\u0012\u000e\n\nwater_polo\u0010*\u0012\u0010\n\fwater_thrash\u0010+\u0012\u000f\n\u000bwater_kayak\u0010,\u0012\u0012\n\u000ewater_drifting\u0010-\u0012\u0011\n\rwater_boating\u0010.\u0012\r\n\twater_fin\u0010/\u0012\u0010\n\fwater_diving\u00100\u0012\u0012\n\u000ewater_artistic\u00101\u0012\u0011\n\rwater_snorkel\u00102\u0012\u0015\n\u0011water_kitesurfing\u00103\u0012\r\n\twater_atv\u00104\u0012\u000f\n\u000bwater_beach\u00105\u0012\u000f\n\u000bdance_dance\u00106\u0012\u000f\n\u000bdance_delly\u00107\u0012\u0014\n\u0010dance_gymnastics\u00108\u0012\u0012\n\u000edance_aerobics\u00109\u0012\u0011\n\rdance_hip_hop\u0010:\u0012\u0010\n\ffight_boxing\u0010;\u0012\u000f\n\u000bfight_wushu\u0010<\u0012\u0013\n\u000ffight_wrestling\u0010=\u0012\u0010\n\ffight_taichi\u0010>\u0012\u0013\n\u000ffight_muay_thai\u0010?\u0012\u000e\n\nfight_judo\u0010@\u0012\u0013\n\u000ffight_taekwondo\u0010A\u0012\u0010\n\ffight_karate\u0010B\u0012\u0017\n\u0013fight_free_sparring\u0010C\u0012\u000f\n\u000bball_soccer\u0010D\u0012\u0013\n\u000fball_basketball\u0010E\u0012\u0013\n\u000fball_volleyball\u0010F\u0012\u0012\n\u000eball_badminton\u0010G\u0012\u0011\n\rball_pingpong\u0010H\u0012\u0010\n\fball_cricket\u0010I\u0012\u0011\n\rball_football\u0010J\u0012\u0013\n\u000fball_racqurball\u0010K\u0012\u0011\n\rball_handball\u0010L\u0012\u000f\n\u000bball_squash\u0010M\u0012\u0014\n\u0010ball_shuttlecock\u0010N\u0012\r\n\tball_raga\u0010O\u0012\u000f\n\u000bsnow_mobile\u0010P\u0012\r\n\tsnow_skis\u0010Q\u0012\r\n\tsnow_puck\u0010R\u0012\u000e\n\nsnow_skate\u0010S\u0012\u0010\n\fsnow_curling\u0010T\u0012\u000e\n\nsnow_board\u0010U\u0012\r\n\tsnow_sled\u0010V\u0012\u0016\n\u0012leisure_meditation\u0010W\u0012\u0011\n\rleisure_kendo\u0010X\u0012\u0011\n\rleisure_fence\u0010Y\u0012\u0013\n\u000fleisure_bowling\u0010Z\u0012\u0015\n\u0011leisure_billiards\u0010[\u0012\u0013\n\u000fleisure_archery\u0010\\\u0012\u0011\n\rleisure_darts\u0010]\u0012\u0011\n\rleisure_horse\u0010^\u0012\u0010\n\fleisure_hula\u0010_\u0012\u0010\n\fleisure_kite\u0010`\u0012\u0013\n\u000fleisure_fishing\u0010a\u0012\u0012\n\u000eleisure_fribee\u0010b\u0012\u0016\n\u0012leisure_equestrian\u0010c\u0012\u0012\n\u000eleisure_racing\u0010d\u0012\u000e\n\nother_free\u0010e\u0012\u000e\n\nother_rope\u0010f\u0012\u000f\n\u000bother_climb\u0010g\u0012\u000e\n\nother_push\u0010h\u0012\u0014\n\u0010other_horizontal\u0010i\u0012\u0012\n\u000eother_parallel\u0010j\u0012\n\n\u0006tennis\u0010k\u0012\f\n\bbaseball\u0010l\u0012\n\n\u0006hockey\u0010m\u0012\u0010\n\fcustom_sport\u0010n\u0012\r\n\tmark_time\u0010o\u0012\u0013\n\u000fwalking_machine\u0010p\u0012\r\n\tathletics\u0010q\u0012\u001b\n\u0017lumbar_abdomen_training\u0010r\u0012\u000f\n\u000blatin_dance\u0010s\u0012\n\n\u0006ballet\u0010t\u0012\b\n\u0004golf\u0010u\u0012\u000e\n\nfolk_dance\u0010v\u0012\f\n\blacrosse\u0010w\u0012\f\n\bsoftball\u0010x\u0012\r\n\tpeak_ball\u0010y\u0012\u000e\n\ntrampoline\u0010z\u0012\u000b\n\u0007parkour\u0010{\u0012\u000b\n\u0007push_up\u0010|\u0012\r\n\thigh_jump\u0010}\u0012\r\n\tlong_jump\u0010~\u0012\u000e\n\bdaily_ex\u0010\u0080\u0080\u0002\u0012\u0018\n\u0012ourdoor_walking_ex\u0010\u0081\u0080\u0002\u0012\u0018\n\u0012ourdoor_running_ex\u0010\u0082\u0080\u0002\u0012\u0018\n\u0012ourdoor_on_foot_ex\u0010\u0083\u0080\u0002\u0012\"\n\u001courdoor_on_mountaineering_ex\u0010\u0084\u0080\u0002\u0012\u001e\n\u0018ourdoor_trail_running_ex\u0010\u0085\u0080\u0002\u0012\u0018\n\u0012ourdoor_cycling_ex\u0010\u0086\u0080\u0002\u0012\u0019\n\u0013outdoor_swimming_ex\u0010\u0087\u0080\u0002\u0012\u0017\n\u0011indoor_walking_ex\u0010\u0088\u0080\u0002\u0012\u0017\n\u0011indoor_running_ex\u0010\u0089\u0080\u0002\u0012\u0018\n\u0012indoor_exercise_ex\u0010\u008a\u0080\u0002\u0012\u0017\n\u0011indoor_cycling_ex\u0010\u008b\u0080\u0002\u0012\u0013\n\relliptical_ex\u0010\u008c\u0080\u0002\u0012\r\n\u0007yoga_ex\u0010\u008d\u0080\u0002\u0012\u000f\n\trowing_ex\u0010\u008e\u0080\u0002\u0012\u0018\n\u0012indoor_swimming_ex\u0010\u008f\u0080\u0002\"¨\u0001\n\u0017u8803_blood_oxygen_data\u0012#\n\re_common_flag\u0018\u0001 \u0001(\u000e2\f.common_flag\u0012/\n\u0007s_index\u0018\u0002 \u0003(\u000b2\u001e.u8803_blood_oxygen_data.index\u001a7\n\u0005index\u0012\u0012\n\ntime_stamp\u0018\u0001 \u0001(\r\u0012\u001a\n\u0012blood_oxygen_value\u0018\u0002 \u0001(\r\"¢\u0002\n\u0011u8803_stress_data\u0012#\n\re_common_flag\u0018\u0001 \u0001(\u000e2\f.common_flag\u0012)\n\u0007s_index\u0018\u0002 \u0003(\u000b2\u0018.u8803_stress_data.index\u001aY\n\u0005index\u0012\u0012\n\ntime_stamp\u0018\u0001 \u0001(\r\u0012\u0013\n\u000bstess_value\u0018\u0002 \u0001(\r\u0012'\n\u0006e_type\u0018\u0003 \u0001(\u000e2\u0017.u8803_stress_data.type\"b\n\u0004type\u0012\u0011\n\rstress_unkown\u0010\u0000\u0012\u0010\n\fstress_relax\u0010\u0001\u0012\u0011\n\rstress_normal\u0010\u0002\u0012\u0011\n\rstress_middle\u0010\u0003\u0012\u000f\n\u000bstress_high\u0010\u0004\"\u009f\u0001\n\u0014u8803_step_freq_data\u0012#\n\re_common_flag\u0018\u0001 \u0001(\u000e2\f.common_flag\u0012,\n\u0007s_index\u0018\u0002 \u0003(\u000b2\u001b.u8803_step_freq_data.index\u001a4\n\u0005index\u0012\u0012\n\ntime_stamp\u0018\u0001 \u0001(\r\u0012\u0017\n\u000fstep_freq_value\u0018\u0002 \u0001(\r\"\u009f\u0001\n\u0014u8803_step_pace_data\u0012#\n\re_common_flag\u0018\u0001 \u0001(\u000e2\f.common_flag\u0012,\n\u0007s_index\u0018\u0002 \u0003(\u000b2\u001b.u8803_step_pace_data.index\u001a4\n\u0005index\u0012\u0012\n\ntime_stamp\u0018\u0001 \u0001(\r\u0012\u0017\n\u000fstep_pace_value\u0018\u0002 \u0001(\r\"\u009a\u0001\n\u0015u8803_resting_hr_data\u0012#\n\re_common_flag\u0018\u0001 \u0001(\u000e2\f.common_flag\u0012-\n\u0007s_index\u0018\u0002 \u0003(\u000b2\u001c.u8803_resting_hr_data.index\u001a-\n\u0005index\u0012\u0012\n\ntime_stamp\u0018\u0001 \u0001(\r\u0012\u0010\n\bhr_value\u0018\u0002 \u0001(\r\"Ç\u0005\n\u0018u8803_habit_tracker_data\u0012#\n\re_common_flag\u0018\u0001 \u0001(\u000e2\f.common_flag\u00120\n\u0007s_index\u0018\u0002 \u0003(\u000b2\u001f.u8803_habit_tracker_data.index\u001a\u0083\u0002\n\u0005index\u0012\u0012\n\ntime_stamp\u0018\u0001 \u0001(\r\u00124\n\te_icon_id\u0018\u0002 \u0001(\u000e2!.u8803_habit_tracker_data.icon_id\u0012\u0012\n\nbegin_hour\u0018\u0003 \u0001(\r\u0012\u0014\n\fbegin_minute\u0018\u0004 \u0001(\r\u0012\u0010\n\bend_hour\u0018\u0005 \u0001(\r\u0012\u0012\n\nend_minute\u0018\u0006 \u0001(\r\u0012\t\n\u0001R\u0018\u0007 \u0001(\r\u0012\t\n\u0001G\u0018\b \u0001(\r\u0012\t\n\u0001B\u0018\t \u0001(\r\u0012.\n\u0006e_flag\u0018\n \u0001(\u000e2\u001e.u8803_habit_tracker_data.flag\u0012\u000f\n\u0007content\u0018\u000b \u0001(\t\"\u008c\u0002\n\u0007icon_id\u0012\f\n\bstudy_01\u0010\u0000\u0012\f\n\bsleep_02\u0010\u0001\u0012\f\n\bstudy_03\u0010\u0002\u0012\r\n\tchores_04\u0010\u0003\u0012\u000e\n\nhavefun_05\u0010\u0004\u0012\f\n\bdrink_06\u0010\u0005\u0012\n\n\u0006sun_07\u0010\u0006\u0012\f\n\bteeth_08\u0010\u0007\u0012\u000f\n\u000bcalendar_09\u0010\b\u0012\f\n\bpiano_10\u0010\t\u0012\f\n\bfruit_11\u0010\n\u0012\u000f\n\u000bmedicine_12\u0010\u000b\u0012\u000b\n\u0007draw_13\u0010\f\u0012\r\n\ttarget_14\u0010\r\u0012\n\n\u0006dog_15\u0010\u000e\u0012\u000f\n\u000bexercise_16\u0010\u000f\u0012\n\n\u0006bed_17\u0010\u0010\u0012\r\n\ttidyup_18\u0010\u0011\"?\n\u0004flag\u0012\u000b\n\u0007initial\u0010\u0000\u0012\u000f\n\u000bin_progress\u0010\u0001\u0012\r\n\tcompleted\u0010\u0002\u0012\n\n\u0006cancel\u0010\u0003\"&\n\u0012u8803_hr_test_data\u0012\u0010\n\bhr_value\u0018\u0001 \u0001(\r*=\n\u000bcommon_flag\u0012\t\n\u0005begin\u0010\u0000\u0012\u000b\n\u0007proceed\u0010\u0001\u0012\u0007\n\u0003end\u0010\u0002\u0012\r\n\tbegin_end\u0010\u0003B\f\n\nproto2javab\u0006proto3"}, new Descriptors.FileDescriptor[0]);
        W = internalBuildGeneratedFileFrom;
        Descriptors.Descriptor descriptor = internalBuildGeneratedFileFrom.getMessageTypes().get(0);
        f208a = descriptor;
        b = new GeneratedMessageV3.FieldAccessorTable(descriptor, new String[]{"RequestId", "ECommonFlag"});
        Descriptors.Descriptor descriptor2 = W.getMessageTypes().get(1);
        c = descriptor2;
        d = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"ECommonFlag", "SIndex"});
        Descriptors.Descriptor descriptor3 = descriptor2.getNestedTypes().get(0);
        e = descriptor3;
        f = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"TimeStamp", "Steps", "Calorie", "Distance", "Duration", "AverageHeartRate"});
        Descriptors.Descriptor descriptor4 = W.getMessageTypes().get(2);
        g = descriptor4;
        h = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"ECommonFlag", "SIndex"});
        Descriptors.Descriptor descriptor5 = descriptor4.getNestedTypes().get(0);
        i = descriptor5;
        j = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"TimeStamp", "ESleepNode"});
        Descriptors.Descriptor descriptor6 = W.getMessageTypes().get(3);
        k = descriptor6;
        l = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"ECommonFlag", "SIndex"});
        Descriptors.Descriptor descriptor7 = descriptor6.getNestedTypes().get(0);
        m = descriptor7;
        n = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"TimeStamp", "HrValue"});
        Descriptors.Descriptor descriptor8 = W.getMessageTypes().get(4);
        o = descriptor8;
        p = new GeneratedMessageV3.FieldAccessorTable(descriptor8, new String[]{"ECommonFlag", "SIndex"});
        Descriptors.Descriptor descriptor9 = descriptor8.getNestedTypes().get(0);
        q = descriptor9;
        r = new GeneratedMessageV3.FieldAccessorTable(descriptor9, new String[]{"TimeStamp", "Latitude", "Longitude"});
        Descriptors.Descriptor descriptor10 = W.getMessageTypes().get(5);
        s = descriptor10;
        t = new GeneratedMessageV3.FieldAccessorTable(descriptor10, new String[]{"ECommonFlag", "SIndex"});
        Descriptors.Descriptor descriptor11 = descriptor10.getNestedTypes().get(0);
        u = descriptor11;
        v = new GeneratedMessageV3.FieldAccessorTable(descriptor11, new String[]{"EType", "BeginTimeStamp", "EndTimeStamp", "Steps", "Calorie", "Distance", "Duration", "TrainingEffectNormal", "TrainingEffectWarmUp", "TrainingEffectFatconsumption", "TrainingEffectAerobic", "TrainingEffectAnaerobic", "TrainingEffectLimit", "AverageHeartRate", "AverageTemperature", "AverageSpeed", "AveragePace", "AverageStepFreq", "AverageStride", "AverageAltitude", "AverageHeartRateMax", "AverageHeartRateMin"});
        Descriptors.Descriptor descriptor12 = W.getMessageTypes().get(6);
        w = descriptor12;
        x = new GeneratedMessageV3.FieldAccessorTable(descriptor12, new String[]{"ECommonFlag", "SIndex"});
        Descriptors.Descriptor descriptor13 = descriptor12.getNestedTypes().get(0);
        y = descriptor13;
        z = new GeneratedMessageV3.FieldAccessorTable(descriptor13, new String[]{"TimeStamp", "BloodOxygenValue"});
        Descriptors.Descriptor descriptor14 = W.getMessageTypes().get(7);
        A = descriptor14;
        B = new GeneratedMessageV3.FieldAccessorTable(descriptor14, new String[]{"ECommonFlag", "SIndex"});
        Descriptors.Descriptor descriptor15 = descriptor14.getNestedTypes().get(0);
        C = descriptor15;
        D = new GeneratedMessageV3.FieldAccessorTable(descriptor15, new String[]{"TimeStamp", "StessValue", "EType"});
        Descriptors.Descriptor descriptor16 = W.getMessageTypes().get(8);
        E = descriptor16;
        F = new GeneratedMessageV3.FieldAccessorTable(descriptor16, new String[]{"ECommonFlag", "SIndex"});
        Descriptors.Descriptor descriptor17 = descriptor16.getNestedTypes().get(0);
        G = descriptor17;
        H = new GeneratedMessageV3.FieldAccessorTable(descriptor17, new String[]{"TimeStamp", "StepFreqValue"});
        Descriptors.Descriptor descriptor18 = W.getMessageTypes().get(9);
        I = descriptor18;
        J = new GeneratedMessageV3.FieldAccessorTable(descriptor18, new String[]{"ECommonFlag", "SIndex"});
        Descriptors.Descriptor descriptor19 = descriptor18.getNestedTypes().get(0);
        K = descriptor19;
        L = new GeneratedMessageV3.FieldAccessorTable(descriptor19, new String[]{"TimeStamp", "StepPaceValue"});
        Descriptors.Descriptor descriptor20 = W.getMessageTypes().get(10);
        M = descriptor20;
        N = new GeneratedMessageV3.FieldAccessorTable(descriptor20, new String[]{"ECommonFlag", "SIndex"});
        Descriptors.Descriptor descriptor21 = descriptor20.getNestedTypes().get(0);
        O = descriptor21;
        P = new GeneratedMessageV3.FieldAccessorTable(descriptor21, new String[]{"TimeStamp", "HrValue"});
        Descriptors.Descriptor descriptor22 = W.getMessageTypes().get(11);
        Q = descriptor22;
        R = new GeneratedMessageV3.FieldAccessorTable(descriptor22, new String[]{"ECommonFlag", "SIndex"});
        Descriptors.Descriptor descriptor23 = descriptor22.getNestedTypes().get(0);
        S = descriptor23;
        T = new GeneratedMessageV3.FieldAccessorTable(descriptor23, new String[]{"TimeStamp", "EIconId", "BeginHour", "BeginMinute", "EndHour", "EndMinute", "R", "G", "B", "EFlag", "Content"});
        Descriptors.Descriptor descriptor24 = W.getMessageTypes().get(12);
        U = descriptor24;
        V = new GeneratedMessageV3.FieldAccessorTable(descriptor24, new String[]{"HrValue"});
    }
}
