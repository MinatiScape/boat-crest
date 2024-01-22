package a;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.util.Objects;
/* loaded from: classes.dex */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    public static final Descriptors.Descriptor f263a;
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
    public static Descriptors.FileDescriptor s;

    /* loaded from: classes.dex */
    public static final class a extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final a f = new a();
        public static final Parser<a> g = new C0094a();

        /* renamed from: a  reason: collision with root package name */
        public int f264a;
        public int b;
        public int c;
        public int d;
        public byte e;

        /* renamed from: a.d$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0094a extends AbstractParser<a> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new a(codedInputStream, extensionRegistryLite);
            }
        }

        /* loaded from: classes.dex */
        public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f265a;
            public int b;
            public int c;
            public int d;

            public b() {
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
            public a.d.a.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.d$a> r1 = a.d.a.g     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.d$a r3 = (a.d.a) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    a.d$a r4 = (a.d.a) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: a.d.a.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.d$a$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public a buildPartial() {
                a aVar = new a(this);
                aVar.f264a = this.f265a;
                aVar.b = this.b;
                aVar.c = this.c;
                aVar.d = this.d;
                onBuilt();
                return aVar;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: c */
            public b clear() {
                super.clear();
                this.f265a = 0;
                this.b = 0;
                this.c = 0;
                this.d = 0;
                return this;
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
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return a.f;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return d.f263a;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return d.b.ensureFieldAccessorsInitialized(a.class, b.class);
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

            public b d(int i) {
                this.f265a = i;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return a.f;
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
                d();
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
                if (message instanceof a) {
                    return a((a) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public b c(int i) {
                this.c = i;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Object mo0clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public a build() {
                a buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof a) {
                    return a((a) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public b a(int i) {
                this.d = i;
                onChanged();
                return this;
            }

            public b b(int i) {
                this.b = i;
                onChanged();
                return this;
            }

            public b a(a aVar) {
                if (aVar == a.f) {
                    return this;
                }
                int i = aVar.f264a;
                if (i != 0) {
                    this.f265a = i;
                    onChanged();
                }
                int i2 = aVar.b;
                if (i2 != 0) {
                    this.b = i2;
                    onChanged();
                }
                int i3 = aVar.c;
                if (i3 != 0) {
                    this.c = i3;
                    onChanged();
                }
                int i4 = aVar.d;
                if (i4 != 0) {
                    this.d = i4;
                    onChanged();
                }
                b bVar = (b) super.mergeUnknownFields(aVar.unknownFields);
                onChanged();
                return this;
            }
        }

        /* loaded from: classes.dex */
        public enum c implements ProtocolMessageEnum {
            ourdoor_default(0),
            ourdoor_walking(1),
            ourdoor_running(2),
            ourdoor_on_foot(3),
            ourdoor_on_mountaineering(4),
            ourdoor_trail_running(5),
            ourdoor_cycling(6),
            UNRECOGNIZED(-1);
            

            /* renamed from: a  reason: collision with root package name */
            public final int f266a;

            static {
                values();
            }

            c(int i) {
                this.f266a = i;
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return a.b().getEnumTypes().get(0);
            }

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.f266a;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                if (this != UNRECOGNIZED) {
                    return a.b().getEnumTypes().get(0).getValues().get(ordinal());
                }
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
        }

        public a(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.e = (byte) -1;
        }

        public static final Descriptors.Descriptor b() {
            return d.f263a;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: c */
        public b toBuilder() {
            if (this == f) {
                return new b();
            }
            return new b().a(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof a)) {
                return super.equals(obj);
            }
            a aVar = (a) obj;
            return this.f264a == aVar.f264a && this.b == aVar.b && this.c == aVar.c && this.d == aVar.d && this.unknownFields.equals(aVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return f;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<a> getParserForType() {
            return g;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.f264a;
            int computeUInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeUInt32Size(1, i2) : 0;
            if (this.b != c.ourdoor_default.getNumber()) {
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
            int hashCode = ((((((((((((((((((d.f263a.hashCode() + 779) * 37) + 1) * 53) + this.f264a) * 37) + 2) * 53) + this.b) * 37) + 3) * 53) + this.c) * 37) + 4) * 53) + this.d) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return d.b.ensureFieldAccessorsInitialized(a.class, b.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b2 = this.e;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            this.e = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Message.Builder newBuilderForType() {
            return f.toBuilder();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new a();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            int i = this.f264a;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            if (this.b != c.ourdoor_default.getNumber()) {
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
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return f;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public MessageLite.Builder newBuilderForType() {
            return f.toBuilder();
        }

        public a() {
            this.e = (byte) -1;
            this.b = 0;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new b(builderParent);
        }

        public a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
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
                                this.f264a = codedInputStream.readUInt32();
                            } else if (readTag == 16) {
                                this.b = codedInputStream.readEnum();
                            } else if (readTag == 24) {
                                this.c = codedInputStream.readUInt32();
                            } else if (readTag != 32) {
                                if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.d = codedInputStream.readUInt32();
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
    public static final class b extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final b e = new b();
        public static final Parser<b> f = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f267a;
        public int b;
        public int c;
        public byte d;

        /* loaded from: classes.dex */
        public class a extends AbstractParser<b> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new b(codedInputStream, extensionRegistryLite);
            }
        }

        /* renamed from: a.d$b$b  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0095b extends GeneratedMessageV3.Builder<C0095b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f268a;
            public int b;
            public int c;

            public C0095b() {
                d();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.d.b.C0095b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.d$b> r1 = a.d.b.f     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.d$b r3 = (a.d.b) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    a.d$b r4 = (a.d.b) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: a.d.b.C0095b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.d$b$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0095b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public b buildPartial() {
                b bVar = new b(this);
                bVar.f267a = this.f268a;
                bVar.b = this.b;
                bVar.c = this.c;
                onBuilt();
                return bVar;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: c */
            public C0095b clear() {
                super.clear();
                this.f268a = 0;
                this.b = 0;
                this.c = 0;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (C0095b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0095b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (C0095b) super.mo0clone();
            }

            public final void d() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return b.e;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return d.c;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return d.d.ensureFieldAccessorsInitialized(b.class, C0095b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0095b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0095b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0095b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0095b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0095b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (C0095b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0095b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (C0095b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return b.e;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0095b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0095b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0095b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0095b) super.setUnknownFields(unknownFieldSet);
            }

            public C0095b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                d();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0095b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (C0095b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0095b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clone() {
                return (C0095b) super.mo0clone();
            }

            public C0095b c(int i) {
                this.c = i;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (C0095b) super.mo0clone();
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
                return (C0095b) super.mo0clone();
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

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof b) {
                    return a((b) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public C0095b b(int i) {
                this.f268a = i;
                onChanged();
                return this;
            }

            public C0095b a(int i) {
                this.b = i;
                onChanged();
                return this;
            }

            public C0095b a(b bVar) {
                if (bVar == b.e) {
                    return this;
                }
                int i = bVar.f267a;
                if (i != 0) {
                    this.f268a = i;
                    onChanged();
                }
                int i2 = bVar.b;
                if (i2 != 0) {
                    this.b = i2;
                    onChanged();
                }
                int i3 = bVar.c;
                if (i3 != 0) {
                    this.c = i3;
                    onChanged();
                }
                C0095b c0095b = (C0095b) super.mergeUnknownFields(bVar.unknownFields);
                onChanged();
                return this;
            }
        }

        public b(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.d = (byte) -1;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: b */
        public C0095b toBuilder() {
            if (this == e) {
                return new C0095b();
            }
            return new C0095b().a(this);
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
            return this.f267a == bVar.f267a && this.b == bVar.b && this.c == bVar.c && this.unknownFields.equals(bVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return e;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<b> getParserForType() {
            return f;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.f267a;
            int computeUInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeUInt32Size(1, i2) : 0;
            int i3 = this.b;
            if (i3 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(2, i3);
            }
            int i4 = this.c;
            if (i4 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(3, i4);
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
            int hashCode = ((((((((((((((d.c.hashCode() + 779) * 37) + 1) * 53) + this.f267a) * 37) + 2) * 53) + this.b) * 37) + 3) * 53) + this.c) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return d.d.ensureFieldAccessorsInitialized(b.class, C0095b.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.d;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
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
            return new b();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            int i = this.f267a;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            int i2 = this.b;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(2, i2);
            }
            int i3 = this.c;
            if (i3 != 0) {
                codedOutputStream.writeUInt32(3, i3);
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

        public b() {
            this.d = (byte) -1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new C0095b(builderParent);
        }

        public b(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
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
                                this.f267a = codedInputStream.readUInt32();
                            } else if (readTag == 16) {
                                this.b = codedInputStream.readUInt32();
                            } else if (readTag != 24) {
                                if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.c = codedInputStream.readUInt32();
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
    public static final class c extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final c e = new c();
        public static final Parser<c> f = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f269a;
        public int b;
        public int c;
        public byte d;

        /* loaded from: classes.dex */
        public class a extends AbstractParser<c> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new c(codedInputStream, extensionRegistryLite);
            }
        }

        /* loaded from: classes.dex */
        public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f270a;
            public int b;
            public int c;

            public b() {
                this.f270a = 0;
                d();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.d.c.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.d$c> r1 = a.d.c.f     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.d$c r3 = (a.d.c) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    a.d$c r4 = (a.d.c) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: a.d.c.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.d$c$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public c buildPartial() {
                c cVar = new c(this);
                cVar.f269a = this.f270a;
                cVar.b = this.b;
                cVar.c = this.c;
                onBuilt();
                return cVar;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: c */
            public b clear() {
                super.clear();
                this.f270a = 0;
                this.b = 0;
                this.c = 0;
                return this;
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
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return c.e;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return d.o;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return d.p.ensureFieldAccessorsInitialized(c.class, b.class);
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
                return c.e;
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
                this.f270a = 0;
                d();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clone() {
                return (b) super.mo0clone();
            }

            public b c(int i) {
                this.b = i;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeFrom(Message message) {
                if (message instanceof c) {
                    return a((c) message);
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
            public c build() {
                c buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof c) {
                    return a((c) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public b b(int i) {
                this.c = i;
                onChanged();
                return this;
            }

            public b a(int i) {
                this.f270a = i;
                onChanged();
                return this;
            }

            public b a(c cVar) {
                if (cVar == c.e) {
                    return this;
                }
                int i = cVar.f269a;
                if (i != 0) {
                    this.f270a = i;
                    onChanged();
                }
                int i2 = cVar.b;
                if (i2 != 0) {
                    this.b = i2;
                    onChanged();
                }
                int i3 = cVar.c;
                if (i3 != 0) {
                    this.c = i3;
                    onChanged();
                }
                b bVar = (b) super.mergeUnknownFields(cVar.unknownFields);
                onChanged();
                return this;
            }
        }

        /* renamed from: a.d$c$c  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public enum EnumC0096c implements ProtocolMessageEnum {
            ourdoor_null(0),
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
            snow_board(80),
            snow_skis(81),
            snow_puck(82),
            snow_skate(83),
            snow_curling(84),
            snow_mobile(85),
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
            UNRECOGNIZED(-1);
            

            /* renamed from: a  reason: collision with root package name */
            public final int f271a;

            static {
                values();
            }

            EnumC0096c(int i) {
                this.f271a = i;
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return c.b().getEnumTypes().get(0);
            }

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.f271a;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                if (this != UNRECOGNIZED) {
                    return c.b().getEnumTypes().get(0).getValues().get(ordinal());
                }
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
        }

        public c(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.d = (byte) -1;
        }

        public static final Descriptors.Descriptor b() {
            return d.o;
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
            if (!(obj instanceof c)) {
                return super.equals(obj);
            }
            c cVar = (c) obj;
            return this.f269a == cVar.f269a && this.b == cVar.b && this.c == cVar.c && this.unknownFields.equals(cVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return e;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<c> getParserForType() {
            return f;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = this.f269a != EnumC0096c.ourdoor_null.getNumber() ? 0 + CodedOutputStream.computeEnumSize(1, this.f269a) : 0;
            int i2 = this.b;
            if (i2 != 0) {
                computeEnumSize += CodedOutputStream.computeUInt32Size(2, i2);
            }
            int i3 = this.c;
            if (i3 != 0) {
                computeEnumSize += CodedOutputStream.computeUInt32Size(3, i3);
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
            int hashCode = ((((((((((((((d.o.hashCode() + 779) * 37) + 1) * 53) + this.f269a) * 37) + 2) * 53) + this.b) * 37) + 3) * 53) + this.c) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return d.p.ensureFieldAccessorsInitialized(c.class, b.class);
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
            return new c();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            if (this.f269a != EnumC0096c.ourdoor_null.getNumber()) {
                codedOutputStream.writeEnum(1, this.f269a);
            }
            int i = this.b;
            if (i != 0) {
                codedOutputStream.writeUInt32(2, i);
            }
            int i2 = this.c;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(3, i2);
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

        public c() {
            this.d = (byte) -1;
            this.f269a = 0;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new b(builderParent);
        }

        public c(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
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
                                this.f269a = codedInputStream.readEnum();
                            } else if (readTag == 16) {
                                this.b = codedInputStream.readUInt32();
                            } else if (readTag != 24) {
                                if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.c = codedInputStream.readUInt32();
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

    /* renamed from: a.d$d  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0097d extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final C0097d d = new C0097d();
        public static final Parser<C0097d> e = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f272a;
        public int b;
        public byte c;

        /* renamed from: a.d$d$a */
        /* loaded from: classes.dex */
        public class a extends AbstractParser<C0097d> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new C0097d(codedInputStream, extensionRegistryLite);
            }
        }

        /* renamed from: a.d$d$b */
        /* loaded from: classes.dex */
        public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f273a;
            public int b;

            public b() {
                this.f273a = 0;
                d();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.d.C0097d.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.d$d> r1 = a.d.C0097d.e     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.d$d r3 = (a.d.C0097d) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    a.d$d r4 = (a.d.C0097d) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: a.d.C0097d.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.d$d$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public C0097d buildPartial() {
                C0097d c0097d = new C0097d(this);
                c0097d.f272a = this.f273a;
                c0097d.b = this.b;
                onBuilt();
                return c0097d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: c */
            public b clear() {
                super.clear();
                this.f273a = 0;
                this.b = 0;
                return this;
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
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return C0097d.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return d.e;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return d.f.ensureFieldAccessorsInitialized(C0097d.class, b.class);
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
                return C0097d.d;
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
                this.f273a = 0;
                d();
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
                if (message instanceof C0097d) {
                    return a((C0097d) message);
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
            public C0097d build() {
                C0097d buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            public b b(int i) {
                this.b = i;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof C0097d) {
                    return a((C0097d) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public b a(int i) {
                this.f273a = i;
                onChanged();
                return this;
            }

            public b a(C0097d c0097d) {
                if (c0097d == C0097d.d) {
                    return this;
                }
                int i = c0097d.f272a;
                if (i != 0) {
                    this.f273a = i;
                    onChanged();
                }
                int i2 = c0097d.b;
                if (i2 != 0) {
                    this.b = i2;
                    onChanged();
                }
                b bVar = (b) super.mergeUnknownFields(c0097d.unknownFields);
                onChanged();
                return this;
            }
        }

        /* renamed from: a.d$d$c */
        /* loaded from: classes.dex */
        public enum c implements ProtocolMessageEnum {
            default_type(0),
            hr(1),
            stress(2),
            blood_oxygen(3),
            breathe(4),
            UNRECOGNIZED(-1);
            

            /* renamed from: a  reason: collision with root package name */
            public final int f274a;

            static {
                values();
            }

            c(int i) {
                this.f274a = i;
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return C0097d.b().getEnumTypes().get(0);
            }

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.f274a;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                if (this != UNRECOGNIZED) {
                    return C0097d.b().getEnumTypes().get(0).getValues().get(ordinal());
                }
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
        }

        public C0097d(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.c = (byte) -1;
        }

        public static final Descriptors.Descriptor b() {
            return d.e;
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
            if (!(obj instanceof C0097d)) {
                return super.equals(obj);
            }
            C0097d c0097d = (C0097d) obj;
            return this.f272a == c0097d.f272a && this.b == c0097d.b && this.unknownFields.equals(c0097d.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<C0097d> getParserForType() {
            return e;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = this.f272a != c.default_type.getNumber() ? 0 + CodedOutputStream.computeEnumSize(1, this.f272a) : 0;
            int i2 = this.b;
            if (i2 != 0) {
                computeEnumSize += CodedOutputStream.computeUInt32Size(2, i2);
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
            int hashCode = ((((((((((d.e.hashCode() + 779) * 37) + 1) * 53) + this.f272a) * 37) + 2) * 53) + this.b) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return d.f.ensureFieldAccessorsInitialized(C0097d.class, b.class);
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
            return new C0097d();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            if (this.f272a != c.default_type.getNumber()) {
                codedOutputStream.writeEnum(1, this.f272a);
            }
            int i = this.b;
            if (i != 0) {
                codedOutputStream.writeUInt32(2, i);
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

        public C0097d() {
            this.c = (byte) -1;
            this.f272a = 0;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new b(builderParent);
        }

        public C0097d(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
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
                                    this.f272a = codedInputStream.readEnum();
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

    /* loaded from: classes.dex */
    public static final class e extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final e f = new e();
        public static final Parser<e> g = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f275a;
        public int b;
        public int c;
        public int d;
        public byte e;

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
            public int f276a;
            public int b;
            public int c;
            public int d;

            public b() {
                d();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.d.e.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.d$e> r1 = a.d.e.g     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.d$e r3 = (a.d.e) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    a.d$e r4 = (a.d.e) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: a.d.e.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.d$e$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public e buildPartial() {
                e eVar = new e(this);
                eVar.f275a = this.f276a;
                eVar.b = this.b;
                eVar.c = this.c;
                eVar.d = this.d;
                onBuilt();
                return eVar;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: c */
            public b clear() {
                super.clear();
                this.f276a = 0;
                this.b = 0;
                this.c = 0;
                this.d = 0;
                return this;
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
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return e.f;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return d.q;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return d.r.ensureFieldAccessorsInitialized(e.class, b.class);
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

            public b d(int i) {
                this.b = i;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return e.f;
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
                d();
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
                if (message instanceof e) {
                    return a((e) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public b c(int i) {
                this.f276a = i;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Object mo0clone() {
                return (b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public e build() {
                e buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof e) {
                    return a((e) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public b a(int i) {
                this.c = i;
                onChanged();
                return this;
            }

            public b b(int i) {
                this.d = i;
                onChanged();
                return this;
            }

            public b a(e eVar) {
                if (eVar == e.f) {
                    return this;
                }
                int i = eVar.f275a;
                if (i != 0) {
                    this.f276a = i;
                    onChanged();
                }
                int i2 = eVar.b;
                if (i2 != 0) {
                    this.b = i2;
                    onChanged();
                }
                int i3 = eVar.c;
                if (i3 != 0) {
                    this.c = i3;
                    onChanged();
                }
                int i4 = eVar.d;
                if (i4 != 0) {
                    this.d = i4;
                    onChanged();
                }
                b bVar = (b) super.mergeUnknownFields(eVar.unknownFields);
                onChanged();
                return this;
            }
        }

        public e(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.e = (byte) -1;
        }

        public int b() {
            return this.c;
        }

        public int c() {
            return this.d;
        }

        public int d() {
            return this.f275a;
        }

        public int e() {
            return this.b;
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
            return this.f275a == eVar.f275a && this.b == eVar.b && this.c == eVar.c && this.d == eVar.d && this.unknownFields.equals(eVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: f */
        public b toBuilder() {
            if (this == f) {
                return new b();
            }
            return new b().a(this);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return f;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<e> getParserForType() {
            return g;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.f275a;
            int computeUInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeUInt32Size(1, i2) : 0;
            int i3 = this.b;
            if (i3 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(2, i3);
            }
            int i4 = this.c;
            if (i4 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(3, i4);
            }
            int i5 = this.d;
            if (i5 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(4, i5);
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
            int hashCode = ((((((((((((((((((d.q.hashCode() + 779) * 37) + 1) * 53) + this.f275a) * 37) + 2) * 53) + this.b) * 37) + 3) * 53) + this.c) * 37) + 4) * 53) + this.d) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return d.r.ensureFieldAccessorsInitialized(e.class, b.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b2 = this.e;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            this.e = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Message.Builder newBuilderForType() {
            return f.toBuilder();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new e();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            int i = this.f275a;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            int i2 = this.b;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(2, i2);
            }
            int i3 = this.c;
            if (i3 != 0) {
                codedOutputStream.writeUInt32(3, i3);
            }
            int i4 = this.d;
            if (i4 != 0) {
                codedOutputStream.writeUInt32(4, i4);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return f;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public MessageLite.Builder newBuilderForType() {
            return f.toBuilder();
        }

        public e() {
            this.e = (byte) -1;
        }

        public static e a(byte[] bArr) {
            return g.parseFrom(bArr);
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new b(builderParent);
        }

        public e(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
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
                                this.f275a = codedInputStream.readUInt32();
                            } else if (readTag == 16) {
                                this.b = codedInputStream.readUInt32();
                            } else if (readTag == 24) {
                                this.c = codedInputStream.readUInt32();
                            } else if (readTag != 32) {
                                if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.d = codedInputStream.readUInt32();
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
    public static final class f extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final f c = new f();
        public static final Parser<f> d = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f277a;
        public byte b;

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
            public int f278a;

            public b() {
                this.f278a = 0;
                d();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.d.f.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.d$f> r1 = a.d.f.d     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.d$f r3 = (a.d.f) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    a.d$f r4 = (a.d.f) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: a.d.f.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.d$f$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public f buildPartial() {
                f fVar = new f(this);
                fVar.f277a = this.f278a;
                onBuilt();
                return fVar;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: c */
            public b clear() {
                super.clear();
                this.f278a = 0;
                return this;
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
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return f.c;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return d.g;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return d.h.ensureFieldAccessorsInitialized(f.class, b.class);
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
                return f.c;
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
                this.f278a = 0;
                d();
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
            public f build() {
                f buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof f) {
                    return a((f) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public b a(int i) {
                this.f278a = i;
                onChanged();
                return this;
            }

            public b a(f fVar) {
                if (fVar == f.c) {
                    return this;
                }
                int i = fVar.f277a;
                if (i != 0) {
                    this.f278a = i;
                    onChanged();
                }
                b bVar = (b) super.mergeUnknownFields(fVar.unknownFields);
                onChanged();
                return this;
            }
        }

        /* loaded from: classes.dex */
        public enum c implements ProtocolMessageEnum {
            default_data(0),
            steps_data(1),
            sleep_data(2),
            hr_data(3),
            gps_data(4),
            multi_sports_data(5),
            blood_oxygen_data_data(6),
            stress_data(7),
            step_freq_data(8),
            step_pace_data(9),
            resting_hr_data(10),
            habit_tracker_data(11),
            UNRECOGNIZED(-1);
            

            /* renamed from: a  reason: collision with root package name */
            public final int f279a;

            static {
                values();
            }

            c(int i) {
                this.f279a = i;
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return f.b().getEnumTypes().get(0);
            }

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.f279a;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                if (this != UNRECOGNIZED) {
                    return f.b().getEnumTypes().get(0).getValues().get(ordinal());
                }
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
        }

        public f(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.b = (byte) -1;
        }

        public static final Descriptors.Descriptor b() {
            return d.g;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: c */
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
            if (!(obj instanceof f)) {
                return super.equals(obj);
            }
            f fVar = (f) obj;
            return this.f277a == fVar.f277a && this.unknownFields.equals(fVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return c;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<f> getParserForType() {
            return d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = (this.f277a != c.default_data.getNumber() ? 0 + CodedOutputStream.computeEnumSize(1, this.f277a) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeEnumSize;
            return computeEnumSize;
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
            int hashCode = ((((((d.g.hashCode() + 779) * 37) + 1) * 53) + this.f277a) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return d.h.ensureFieldAccessorsInitialized(f.class, b.class);
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
            return new f();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            if (this.f277a != c.default_data.getNumber()) {
                codedOutputStream.writeEnum(1, this.f277a);
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

        public f() {
            this.b = (byte) -1;
            this.f277a = 0;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new b(builderParent);
        }

        public f(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
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
                                this.f277a = codedInputStream.readEnum();
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
    public static final class g extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final g c = new g();
        public static final Parser<g> d = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f280a;
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
            public int f281a;

            public b() {
                d();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.d.g.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.d$g> r1 = a.d.g.d     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.d$g r3 = (a.d.g) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    a.d$g r4 = (a.d.g) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: a.d.g.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.d$g$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public g buildPartial() {
                g gVar = new g(this);
                gVar.f280a = this.f281a;
                onBuilt();
                return gVar;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: c */
            public b clear() {
                super.clear();
                this.f281a = 0;
                return this;
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
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return g.c;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return d.m;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return d.n.ensureFieldAccessorsInitialized(g.class, b.class);
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
                d();
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
            public g build() {
                g buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof g) {
                    return a((g) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public b a(int i) {
                this.f281a = i;
                onChanged();
                return this;
            }

            public b a(g gVar) {
                if (gVar == g.c) {
                    return this;
                }
                int i = gVar.f280a;
                if (i != 0) {
                    this.f281a = i;
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
            return this.f280a == gVar.f280a && this.unknownFields.equals(gVar.unknownFields);
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
            int i2 = this.f280a;
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
            int hashCode = ((((((d.m.hashCode() + 779) * 37) + 1) * 53) + this.f280a) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return d.n.ensureFieldAccessorsInitialized(g.class, b.class);
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
            int i = this.f280a;
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
                                this.f280a = codedInputStream.readUInt32();
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
        public int f282a;
        public int b;
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
            public int f283a;
            public int b;

            public b() {
                d();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.d.h.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.d$h> r1 = a.d.h.e     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.d$h r3 = (a.d.h) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    a.d$h r4 = (a.d.h) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: a.d.h.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.d$h$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public h buildPartial() {
                h hVar = new h(this);
                hVar.f282a = this.f283a;
                hVar.b = this.b;
                onBuilt();
                return hVar;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: c */
            public b clear() {
                super.clear();
                this.f283a = 0;
                this.b = 0;
                return this;
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
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return h.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return d.i;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return d.j.ensureFieldAccessorsInitialized(h.class, b.class);
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

            public b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                d();
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
            public h build() {
                h buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            public b b(int i) {
                this.f283a = i;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof h) {
                    return a((h) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public b a(int i) {
                this.b = i;
                onChanged();
                return this;
            }

            public b a(h hVar) {
                if (hVar == h.d) {
                    return this;
                }
                int i = hVar.f282a;
                if (i != 0) {
                    this.f283a = i;
                    onChanged();
                }
                int i2 = hVar.b;
                if (i2 != 0) {
                    this.b = i2;
                    onChanged();
                }
                b bVar = (b) super.mergeUnknownFields(hVar.unknownFields);
                onChanged();
                return this;
            }
        }

        public h(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.c = (byte) -1;
        }

        public int b() {
            return this.b;
        }

        public int c() {
            return this.f282a;
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
            return this.f282a == hVar.f282a && this.b == hVar.b && this.unknownFields.equals(hVar.unknownFields);
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
            int i2 = this.f282a;
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
            int hashCode = ((((((((((d.i.hashCode() + 779) * 37) + 1) * 53) + this.f282a) * 37) + 2) * 53) + this.b) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return d.j.ensureFieldAccessorsInitialized(h.class, b.class);
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
            int i = this.f282a;
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

        public h() {
            this.c = (byte) -1;
        }

        public static h a(byte[] bArr) {
            return e.parseFrom(bArr);
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new b(builderParent);
        }

        public h(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
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
                                    this.f282a = codedInputStream.readUInt32();
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

    /* loaded from: classes.dex */
    public static final class i extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final i c = new i();
        public static final Parser<i> d = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f284a;
        public byte b;

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
            public int f285a;

            public b() {
                d();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.d.i.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.d$i> r1 = a.d.i.d     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.d$i r3 = (a.d.i) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    a.d$i r4 = (a.d.i) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: a.d.i.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.d$i$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public i buildPartial() {
                i iVar = new i(this);
                iVar.f284a = this.f285a;
                onBuilt();
                return iVar;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: c */
            public b clear() {
                super.clear();
                this.f285a = 0;
                return this;
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
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return i.c;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return d.k;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return d.l.ensureFieldAccessorsInitialized(i.class, b.class);
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
                return i.c;
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
                d();
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
            public i build() {
                i buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof i) {
                    return a((i) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public b a(int i) {
                this.f285a = i;
                onChanged();
                return this;
            }

            public b a(i iVar) {
                if (iVar == i.c) {
                    return this;
                }
                int i = iVar.f284a;
                if (i != 0) {
                    this.f285a = i;
                    onChanged();
                }
                b bVar = (b) super.mergeUnknownFields(iVar.unknownFields);
                onChanged();
                return this;
            }
        }

        public i(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.b = (byte) -1;
        }

        public int b() {
            return this.f284a;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: c */
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
            if (!(obj instanceof i)) {
                return super.equals(obj);
            }
            i iVar = (i) obj;
            return this.f284a == iVar.f284a && this.unknownFields.equals(iVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return c;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<i> getParserForType() {
            return d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.f284a;
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
            int hashCode = ((((((d.k.hashCode() + 779) * 37) + 1) * 53) + this.f284a) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return d.l.ensureFieldAccessorsInitialized(i.class, b.class);
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
            return new i();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            int i = this.f284a;
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

        public i() {
            this.b = (byte) -1;
        }

        public static i a(byte[] bArr) {
            return d.parseFrom(bArr);
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new b(builderParent);
        }

        public i(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
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
                                this.f284a = codedInputStream.readUInt32();
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

    static {
        Descriptors.FileDescriptor internalBuildGeneratedFileFrom = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001aeastapex_s8800_c8804.proto\"Ä\u0002\n\u001bud8801_app_launch_map_sport\u0012\u000e\n\u0006status\u0018\u0001 \u0001(\r\u0012=\n\fe_sport_type\u0018\u0002 \u0001(\u000e2'.ud8801_app_launch_map_sport.sport_type\u0012\u0010\n\binterval\u0018\u0003 \u0001(\r\u0012\u0012\n\ncheckSport\u0018\u0004 \u0001(\r\"¯\u0001\n\nsport_type\u0012\u0013\n\u000fourdoor_default\u0010\u0000\u0012\u0013\n\u000fourdoor_walking\u0010\u0001\u0012\u0013\n\u000fourdoor_running\u0010\u0002\u0012\u0013\n\u000fourdoor_on_foot\u0010\u0003\u0012\u001d\n\u0019ourdoor_on_mountaineering\u0010\u0004\u0012\u0019\n\u0015ourdoor_trail_running\u0010\u0005\u0012\u0013\n\u000fourdoor_cycling\u0010\u0006\"W\n#ud8801_app_launch_map_sport_details\u0012\u0010\n\bduration\u0018\u0001 \u0001(\r\u0012\u0010\n\bdistance\u0018\u0002 \u0001(\r\u0012\f\n\u0004pace\u0018\u0003 \u0001(\r\"§\u0001\n\u000eud8801_app_ops\u00124\n\u000ee_app_ops_type\u0018\u0001 \u0001(\u000e2\u001c.ud8801_app_ops.app_ops_type\u0012\n\n\u0002sw\u0018\u0002 \u0001(\r\"S\n\fapp_ops_type\u0012\u0010\n\fdefault_type\u0010\u0000\u0012\u0006\n\u0002hr\u0010\u0001\u0012\n\n\u0006stress\u0010\u0002\u0012\u0010\n\fblood_oxygen\u0010\u0003\u0012\u000b\n\u0007breathe\u0010\u0004\"Ô\u0002\n\u0018ud8801_only_get_big_data\u0012@\n\u000fe_big_data_type\u0018\u0001 \u0001(\u000e2'.ud8801_only_get_big_data.big_data_type\"õ\u0001\n\rbig_data_type\u0012\u0010\n\fdefault_data\u0010\u0000\u0012\u000e\n\nsteps_data\u0010\u0001\u0012\u000e\n\nsleep_data\u0010\u0002\u0012\u000b\n\u0007hr_data\u0010\u0003\u0012\f\n\bgps_data\u0010\u0004\u0012\u0015\n\u0011multi_sports_data\u0010\u0005\u0012\u001a\n\u0016blood_oxygen_data_data\u0010\u0006\u0012\u000f\n\u000bstress_data\u0010\u0007\u0012\u0012\n\u000estep_freq_data\u0010\b\u0012\u0012\n\u000estep_pace_data\u0010\t\u0012\u0013\n\u000fresting_hr_data\u0010\n\u0012\u0016\n\u0012habit_tracker_data\u0010\u000b\"A\n!ud8801_sleep_blood_oxygen_monitor\u0012\n\n\u0002sw\u0018\u0001 \u0001(\r\u0012\u0010\n\binterval\u0018\u0002 \u0001(\r\"#\n\u0015ud8801_stress_monitor\u0012\n\n\u0002sw\u0018\u0001 \u0001(\r\"(\n\u001aud8801_send_real_time_data\u0012\n\n\u0002sw\u0018\u0001 \u0001(\r\"\u0089\u0013\n\u001eud8801_app_launch_screen_sport\u0012@\n\fe_sport_type\u0018\u0001 \u0001(\u000e2*.ud8801_app_launch_screen_sport.sport_type\u0012\u000e\n\u0006status\u0018\u0002 \u0001(\r\u0012\u0010\n\binterval\u0018\u0003 \u0001(\r\"\u0082\u0012\n\nsport_type\u0012\u0010\n\fourdoor_null\u0010\u0000\u0012\u0013\n\u000fourdoor_walking\u0010\u0001\u0012\u0013\n\u000fourdoor_running\u0010\u0002\u0012\u0013\n\u000fourdoor_on_foot\u0010\u0003\u0012\u001d\n\u0019ourdoor_on_mountaineering\u0010\u0004\u0012\u0019\n\u0015ourdoor_trail_running\u0010\u0005\u0012\u0013\n\u000fourdoor_cycling\u0010\u0006\u0012\u0014\n\u0010outdoor_swimming\u0010\u0007\u0012\u0012\n\u000eindoor_walking\u0010\b\u0012\u0012\n\u000eindoor_running\u0010\t\u0012\u0013\n\u000findoor_exercise\u0010\n\u0012\u0012\n\u000eindoor_cycling\u0010\u000b\u0012\u000e\n\nelliptical\u0010\f\u0012\b\n\u0004yoga\u0010\r\u0012\n\n\u0006rowing\u0010\u000e\u0012\u0013\n\u000findoor_swimming\u0010\u000f\u0012\u000b\n\u0007od_rock\u0010\u0010\u0012\f\n\bod_skate\u0010\u0011\u0012\r\n\tod_roller\u0010\u0012\u0012\u000e\n\nod_parkour\u0010\u0013\u0012\u0010\n\fod_parachute\u0010\u0014\u0012\r\n\ttrain_HIT\u0010\u0015\u0012\u0010\n\ftrain_weight\u0010\u0016\u0012\u000f\n\u000btrain_plank\u0010\u0017\u0012\u0011\n\rtrain_jumping\u0010\u0018\u0012\u000f\n\u000btrain_stair\u0010\u0019\u0012\u000e\n\ntrain_core\u0010\u001a\u0012\u000e\n\ntrain_flex\u0010\u001b\u0012\u0011\n\rtrain_pilates\u0010\u001c\u0012\u0011\n\rtrain_stretch\u0010\u001d\u0012\u0012\n\u000etrain_strength\u0010\u001e\u0012\u000f\n\u000btrain_cross\u0010\u001f\u0012\u0012\n\u000etrain_dumbbell\u0010 \u0012\u0012\n\u000etrain_deadlift\u0010!\u0012\r\n\ttrain_sit\u0010\"\u0012\u0013\n\u000ftrain_funcition\u0010#\u0012\u000f\n\u000btrain_upper\u0010$\u0012\u000f\n\u000btrain_lower\u0010%\u0012\r\n\ttrain_abs\u0010&\u0012\u000e\n\ntrain_back\u0010'\u0012\u0012\n\u000ewater_sailboat\u0010(\u0012\r\n\twater_sup\u0010)\u0012\u000e\n\nwater_polo\u0010*\u0012\u0010\n\fwater_thrash\u0010+\u0012\u000f\n\u000bwater_kayak\u0010,\u0012\u0012\n\u000ewater_drifting\u0010-\u0012\u0011\n\rwater_boating\u0010.\u0012\r\n\twater_fin\u0010/\u0012\u0010\n\fwater_diving\u00100\u0012\u0012\n\u000ewater_artistic\u00101\u0012\u0011\n\rwater_snorkel\u00102\u0012\u0015\n\u0011water_kitesurfing\u00103\u0012\r\n\twater_atv\u00104\u0012\u000f\n\u000bwater_beach\u00105\u0012\u000f\n\u000bdance_dance\u00106\u0012\u000f\n\u000bdance_delly\u00107\u0012\u0014\n\u0010dance_gymnastics\u00108\u0012\u0012\n\u000edance_aerobics\u00109\u0012\u0011\n\rdance_hip_hop\u0010:\u0012\u0010\n\ffight_boxing\u0010;\u0012\u000f\n\u000bfight_wushu\u0010<\u0012\u0013\n\u000ffight_wrestling\u0010=\u0012\u0010\n\ffight_taichi\u0010>\u0012\u0013\n\u000ffight_muay_thai\u0010?\u0012\u000e\n\nfight_judo\u0010@\u0012\u0013\n\u000ffight_taekwondo\u0010A\u0012\u0010\n\ffight_karate\u0010B\u0012\u0017\n\u0013fight_free_sparring\u0010C\u0012\u000f\n\u000bball_soccer\u0010D\u0012\u0013\n\u000fball_basketball\u0010E\u0012\u0013\n\u000fball_volleyball\u0010F\u0012\u0012\n\u000eball_badminton\u0010G\u0012\u0011\n\rball_pingpong\u0010H\u0012\u0010\n\fball_cricket\u0010I\u0012\u0011\n\rball_football\u0010J\u0012\u0013\n\u000fball_racqurball\u0010K\u0012\u0011\n\rball_handball\u0010L\u0012\u000f\n\u000bball_squash\u0010M\u0012\u0014\n\u0010ball_shuttlecock\u0010N\u0012\r\n\tball_raga\u0010O\u0012\u000e\n\nsnow_board\u0010P\u0012\r\n\tsnow_skis\u0010Q\u0012\r\n\tsnow_puck\u0010R\u0012\u000e\n\nsnow_skate\u0010S\u0012\u0010\n\fsnow_curling\u0010T\u0012\u000f\n\u000bsnow_mobile\u0010U\u0012\r\n\tsnow_sled\u0010V\u0012\u0016\n\u0012leisure_meditation\u0010W\u0012\u0011\n\rleisure_kendo\u0010X\u0012\u0011\n\rleisure_fence\u0010Y\u0012\u0013\n\u000fleisure_bowling\u0010Z\u0012\u0015\n\u0011leisure_billiards\u0010[\u0012\u0013\n\u000fleisure_archery\u0010\\\u0012\u0011\n\rleisure_darts\u0010]\u0012\u0011\n\rleisure_horse\u0010^\u0012\u0010\n\fleisure_hula\u0010_\u0012\u0010\n\fleisure_kite\u0010`\u0012\u0013\n\u000fleisure_fishing\u0010a\u0012\u0012\n\u000eleisure_fribee\u0010b\u0012\u0016\n\u0012leisure_equestrian\u0010c\u0012\u0012\n\u000eleisure_racing\u0010d\u0012\u000e\n\nother_free\u0010e\u0012\u000e\n\nother_rope\u0010f\u0012\u000f\n\u000bother_climb\u0010g\u0012\u000e\n\nother_push\u0010h\u0012\u0014\n\u0010other_horizontal\u0010i\u0012\u0012\n\u000eother_parallel\u0010j\u0012\n\n\u0006tennis\u0010k\u0012\f\n\bbaseball\u0010l\u0012\n\n\u0006hockey\u0010m\u0012\u0010\n\fcustom_sport\u0010n\u0012\r\n\tmark_time\u0010o\u0012\u0013\n\u000fwalking_machine\u0010p\u0012\r\n\tathletics\u0010q\u0012\u001b\n\u0017lumbar_abdomen_training\u0010r\u0012\u000f\n\u000blatin_dance\u0010s\u0012\n\n\u0006ballet\u0010t\u0012\b\n\u0004golf\u0010u\u0012\u000e\n\nfolk_dance\u0010v\u0012\f\n\blacrosse\u0010w\u0012\f\n\bsoftball\u0010x\u0012\r\n\tpeak_ball\u0010y\u0012\u000e\n\ntrampoline\u0010z\u0012\u000b\n\u0007parkour\u0010{\u0012\u000b\n\u0007push_up\u0010|\u0012\r\n\thigh_jump\u0010}\u0012\r\n\tlong_jump\u0010~\"\u0091\u0001\n\u0019ud8801_menstrual_reminder\u0012\u001a\n\u0012menstrual_begin_sw\u0018\u0001 \u0001(\r\u0012\u0018\n\u0010menstrual_end_sw\u0018\u0002 \u0001(\r\u0012\u001f\n\u0017easy_pregnancy_begin_sw\u0018\u0003 \u0001(\r\u0012\u001d\n\u0015easy_pregnancy_end_sw\u0018\u0004 \u0001(\rB\f\n\nproto2javab\u0006proto3"}, new Descriptors.FileDescriptor[0]);
        s = internalBuildGeneratedFileFrom;
        Descriptors.Descriptor descriptor = internalBuildGeneratedFileFrom.getMessageTypes().get(0);
        f263a = descriptor;
        b = new GeneratedMessageV3.FieldAccessorTable(descriptor, new String[]{"Status", "ESportType", "Interval", "CheckSport"});
        Descriptors.Descriptor descriptor2 = s.getMessageTypes().get(1);
        c = descriptor2;
        d = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Duration", "Distance", "Pace"});
        Descriptors.Descriptor descriptor3 = s.getMessageTypes().get(2);
        e = descriptor3;
        f = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"EAppOpsType", "Sw"});
        Descriptors.Descriptor descriptor4 = s.getMessageTypes().get(3);
        g = descriptor4;
        h = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"EBigDataType"});
        Descriptors.Descriptor descriptor5 = s.getMessageTypes().get(4);
        i = descriptor5;
        j = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"Sw", "Interval"});
        Descriptors.Descriptor descriptor6 = s.getMessageTypes().get(5);
        k = descriptor6;
        l = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"Sw"});
        Descriptors.Descriptor descriptor7 = s.getMessageTypes().get(6);
        m = descriptor7;
        n = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"Sw"});
        Descriptors.Descriptor descriptor8 = s.getMessageTypes().get(7);
        o = descriptor8;
        p = new GeneratedMessageV3.FieldAccessorTable(descriptor8, new String[]{"ESportType", "Status", "Interval"});
        Descriptors.Descriptor descriptor9 = s.getMessageTypes().get(8);
        q = descriptor9;
        r = new GeneratedMessageV3.FieldAccessorTable(descriptor9, new String[]{"MenstrualBeginSw", "MenstrualEndSw", "EasyPregnancyBeginSw", "EasyPregnancyEndSw"});
    }
}
