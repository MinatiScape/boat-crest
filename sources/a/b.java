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
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public static final Descriptors.Descriptor f180a;
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
    public static Descriptors.FileDescriptor u;

    /* loaded from: classes.dex */
    public static final class a extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final a h = new a();
        public static final Parser<a> i = new C0067a();

        /* renamed from: a  reason: collision with root package name */
        public int f181a;
        public int b;
        public volatile Object c;
        public volatile Object d;
        public int e;
        public int f;
        public byte g;

        /* renamed from: a.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0067a extends AbstractParser<a> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new a(codedInputStream, extensionRegistryLite);
            }
        }

        /* renamed from: a.b$a$b  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0068b extends GeneratedMessageV3.Builder<C0068b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f182a;
            public int b;
            public Object c;
            public Object d;
            public int e;
            public int f;

            public C0068b() {
                this.f182a = 0;
                this.c = "";
                this.d = "";
                d();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.b.a.C0068b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.b$a> r1 = a.b.a.i     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.b$a r3 = (a.b.a) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    a.b$a r4 = (a.b.a) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: a.b.a.C0068b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.b$a$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0068b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public a buildPartial() {
                a aVar = new a(this);
                aVar.f181a = this.f182a;
                aVar.b = this.b;
                aVar.c = this.c;
                aVar.d = this.d;
                aVar.e = this.e;
                aVar.f = this.f;
                onBuilt();
                return aVar;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: c */
            public C0068b clear() {
                super.clear();
                this.f182a = 0;
                this.b = 0;
                this.c = "";
                this.d = "";
                this.e = 0;
                this.f = 0;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (C0068b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0068b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (C0068b) super.mo0clone();
            }

            public final void d() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return a.h;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return b.g;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return b.h.ensureFieldAccessorsInitialized(a.class, C0068b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0068b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0068b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0068b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0068b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0068b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (C0068b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0068b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (C0068b) super.mo0clone();
            }

            public C0068b d(int i) {
                this.b = i;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return a.h;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0068b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0068b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0068b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0068b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0068b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (C0068b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0068b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clone() {
                return (C0068b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (C0068b) super.mo0clone();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeFrom(Message message) {
                if (message instanceof a) {
                    return a((a) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public C0068b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.f182a = 0;
                this.c = "";
                this.d = "";
                d();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Object mo0clone() {
                return (C0068b) super.mo0clone();
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

            public C0068b c(int i) {
                this.f = i;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof a) {
                    return a((a) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public C0068b a(String str) {
                this.d = str;
                onChanged();
                return this;
            }

            public C0068b a(int i) {
                this.e = i;
                onChanged();
                return this;
            }

            public C0068b a(a aVar) {
                if (aVar == a.h) {
                    return this;
                }
                int i = aVar.f181a;
                if (i != 0) {
                    this.f182a = i;
                    onChanged();
                }
                int i2 = aVar.b;
                if (i2 != 0) {
                    this.b = i2;
                    onChanged();
                }
                if (!aVar.c().isEmpty()) {
                    this.c = aVar.c;
                    onChanged();
                }
                if (!aVar.b().isEmpty()) {
                    this.d = aVar.d;
                    onChanged();
                }
                int i3 = aVar.e;
                if (i3 != 0) {
                    this.e = i3;
                    onChanged();
                }
                int i4 = aVar.f;
                if (i4 != 0) {
                    this.f = i4;
                    onChanged();
                }
                C0068b c0068b = (C0068b) super.mergeUnknownFields(aVar.unknownFields);
                onChanged();
                return this;
            }

            public C0068b b(int i) {
                this.f182a = i;
                onChanged();
                return this;
            }

            public C0068b b(String str) {
                this.c = str;
                onChanged();
                return this;
            }
        }

        /* loaded from: classes.dex */
        public enum c implements ProtocolMessageEnum {
            not_play(0),
            playing(1),
            stop_play(2),
            UNRECOGNIZED(-1);
            

            /* renamed from: a  reason: collision with root package name */
            public final int f183a;

            static {
                values();
            }

            c(int i) {
                this.f183a = i;
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return a.d().getEnumTypes().get(0);
            }

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.f183a;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                if (this != UNRECOGNIZED) {
                    return a.d().getEnumTypes().get(0).getValues().get(ordinal());
                }
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
        }

        public a(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.g = (byte) -1;
        }

        public static final Descriptors.Descriptor d() {
            return b.g;
        }

        public String b() {
            Object obj = this.d;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.d = stringUtf8;
            return stringUtf8;
        }

        public String c() {
            Object obj = this.c;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.c = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: e */
        public C0068b toBuilder() {
            if (this == h) {
                return new C0068b();
            }
            return new C0068b().a(this);
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
            return this.f181a == aVar.f181a && this.b == aVar.b && c().equals(aVar.c()) && b().equals(aVar.b()) && this.e == aVar.e && this.f == aVar.f && this.unknownFields.equals(aVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return h;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<a> getParserForType() {
            return i;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            ByteString byteString;
            ByteString byteString2;
            int i2 = this.memoizedSize;
            if (i2 != -1) {
                return i2;
            }
            int computeEnumSize = this.f181a != c.not_play.getNumber() ? 0 + CodedOutputStream.computeEnumSize(1, this.f181a) : 0;
            int i3 = this.b;
            if (i3 != 0) {
                computeEnumSize += CodedOutputStream.computeUInt32Size(2, i3);
            }
            Object obj = this.c;
            if (obj instanceof String) {
                byteString = ByteString.copyFromUtf8((String) obj);
                this.c = byteString;
            } else {
                byteString = (ByteString) obj;
            }
            if (!byteString.isEmpty()) {
                computeEnumSize += GeneratedMessageV3.computeStringSize(3, this.c);
            }
            Object obj2 = this.d;
            if (obj2 instanceof String) {
                byteString2 = ByteString.copyFromUtf8((String) obj2);
                this.d = byteString2;
            } else {
                byteString2 = (ByteString) obj2;
            }
            if (!byteString2.isEmpty()) {
                computeEnumSize += GeneratedMessageV3.computeStringSize(4, this.d);
            }
            int i4 = this.e;
            if (i4 != 0) {
                computeEnumSize += CodedOutputStream.computeUInt32Size(5, i4);
            }
            int i5 = this.f;
            if (i5 != 0) {
                computeEnumSize += CodedOutputStream.computeUInt32Size(6, i5);
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
            int i2 = this.memoizedHashCode;
            if (i2 != 0) {
                return i2;
            }
            int hashCode = ((((((((((((((((((((((((((b.g.hashCode() + 779) * 37) + 1) * 53) + this.f181a) * 37) + 2) * 53) + this.b) * 37) + 3) * 53) + c().hashCode()) * 37) + 4) * 53) + b().hashCode()) * 37) + 5) * 53) + this.e) * 37) + 6) * 53) + this.f) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return b.h.ensureFieldAccessorsInitialized(a.class, C0068b.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.g;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
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
            return new a();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            ByteString byteString;
            ByteString byteString2;
            if (this.f181a != c.not_play.getNumber()) {
                codedOutputStream.writeEnum(1, this.f181a);
            }
            int i2 = this.b;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(2, i2);
            }
            Object obj = this.c;
            if (obj instanceof String) {
                byteString = ByteString.copyFromUtf8((String) obj);
                this.c = byteString;
            } else {
                byteString = (ByteString) obj;
            }
            if (!byteString.isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.c);
            }
            Object obj2 = this.d;
            if (obj2 instanceof String) {
                byteString2 = ByteString.copyFromUtf8((String) obj2);
                this.d = byteString2;
            } else {
                byteString2 = (ByteString) obj2;
            }
            if (!byteString2.isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 4, this.d);
            }
            int i3 = this.e;
            if (i3 != 0) {
                codedOutputStream.writeUInt32(5, i3);
            }
            int i4 = this.f;
            if (i4 != 0) {
                codedOutputStream.writeUInt32(6, i4);
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

        public a() {
            this.g = (byte) -1;
            this.f181a = 0;
            this.c = "";
            this.d = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new C0068b(builderParent);
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
                                this.f181a = codedInputStream.readEnum();
                            } else if (readTag == 16) {
                                this.b = codedInputStream.readUInt32();
                            } else if (readTag == 26) {
                                this.c = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 34) {
                                this.d = codedInputStream.readStringRequireUtf8();
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

    /* renamed from: a.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0069b extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final C0069b d = new C0069b();
        public static final Parser<C0069b> e = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f184a;
        public int b;
        public byte c;

        /* renamed from: a.b$b$a */
        /* loaded from: classes.dex */
        public class a extends AbstractParser<C0069b> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new C0069b(codedInputStream, extensionRegistryLite);
            }
        }

        /* renamed from: a.b$b$b  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0070b extends GeneratedMessageV3.Builder<C0070b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f185a;
            public int b;

            public C0070b() {
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
            public a.b.C0069b.C0070b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.b$b> r1 = a.b.C0069b.e     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.b$b r3 = (a.b.C0069b) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    a.b$b r4 = (a.b.C0069b) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: a.b.C0069b.C0070b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.b$b$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0070b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public C0069b buildPartial() {
                C0069b c0069b = new C0069b(this);
                c0069b.f184a = this.f185a;
                c0069b.b = this.b;
                onBuilt();
                return c0069b;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: c */
            public C0070b clear() {
                super.clear();
                this.f185a = 0;
                this.b = 0;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (C0070b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0070b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (C0070b) super.mo0clone();
            }

            public final void d() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return C0069b.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return b.f180a;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return b.b.ensureFieldAccessorsInitialized(C0069b.class, C0070b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0070b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0070b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0070b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0070b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0070b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (C0070b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0070b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (C0070b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return C0069b.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0070b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0070b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0070b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0070b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0070b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (C0070b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0070b) super.mergeUnknownFields(unknownFieldSet);
            }

            public C0070b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.b = 0;
                d();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clone() {
                return (C0070b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (C0070b) super.mo0clone();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeFrom(Message message) {
                if (message instanceof C0069b) {
                    return a((C0069b) message);
                }
                super.mergeFrom(message);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Object mo0clone() {
                return (C0070b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public C0069b build() {
                C0069b buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            public C0070b b(int i) {
                this.f185a = i;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof C0069b) {
                    return a((C0069b) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public C0070b a(int i) {
                this.b = i;
                onChanged();
                return this;
            }

            public C0070b a(C0069b c0069b) {
                if (c0069b == C0069b.d) {
                    return this;
                }
                int i = c0069b.f184a;
                if (i != 0) {
                    this.f185a = i;
                    onChanged();
                }
                int i2 = c0069b.b;
                if (i2 != 0) {
                    this.b = i2;
                    onChanged();
                }
                C0070b c0070b = (C0070b) super.mergeUnknownFields(c0069b.unknownFields);
                onChanged();
                return this;
            }
        }

        /* renamed from: a.b$b$c */
        /* loaded from: classes.dex */
        public enum c implements ProtocolMessageEnum {
            success(0),
            fail(1),
            UNRECOGNIZED(-1);
            

            /* renamed from: a  reason: collision with root package name */
            public final int f186a;

            static {
                values();
            }

            c(int i) {
                this.f186a = i;
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return C0069b.b().getEnumTypes().get(0);
            }

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.f186a;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                if (this != UNRECOGNIZED) {
                    return C0069b.b().getEnumTypes().get(0).getValues().get(ordinal());
                }
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
        }

        public C0069b(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.c = (byte) -1;
        }

        public static final Descriptors.Descriptor b() {
            return b.f180a;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: c */
        public C0070b toBuilder() {
            if (this == d) {
                return new C0070b();
            }
            return new C0070b().a(this);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof C0069b)) {
                return super.equals(obj);
            }
            C0069b c0069b = (C0069b) obj;
            return this.f184a == c0069b.f184a && this.b == c0069b.b && this.unknownFields.equals(c0069b.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<C0069b> getParserForType() {
            return e;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.f184a;
            int computeUInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeUInt32Size(1, i2) : 0;
            if (this.b != c.success.getNumber()) {
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
            int hashCode = ((((((((((b.f180a.hashCode() + 779) * 37) + 1) * 53) + this.f184a) * 37) + 2) * 53) + this.b) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return b.b.ensureFieldAccessorsInitialized(C0069b.class, C0070b.class);
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
            return new C0069b();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            int i = this.f184a;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            if (this.b != c.success.getNumber()) {
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

        public C0069b() {
            this.c = (byte) -1;
            this.b = 0;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new C0070b(builderParent);
        }

        public C0069b(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
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
                                    this.f184a = codedInputStream.readUInt32();
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

    /* loaded from: classes.dex */
    public static final class c extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final c d = new c();
        public static final Parser<c> e = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f187a;
        public volatile Object b;
        public byte c;

        /* loaded from: classes.dex */
        public class a extends AbstractParser<c> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new c(codedInputStream, extensionRegistryLite);
            }
        }

        /* renamed from: a.b$c$b  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0071b extends GeneratedMessageV3.Builder<C0071b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f188a;
            public Object b;

            public C0071b() {
                this.b = "";
                c();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.b.c.C0071b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.b$c> r1 = a.b.c.e     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.b$c r3 = (a.b.c) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    a.b$c r4 = (a.b.c) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: a.b.c.C0071b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.b$c$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0071b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public C0071b clear() {
                super.clear();
                this.f188a = 0;
                this.b = "";
                return this;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message build() {
                c buildPartial = buildPartial();
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
                return (C0071b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0071b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (C0071b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return c.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return b.k;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return b.l.ensureFieldAccessorsInitialized(c.class, C0071b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0071b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0071b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0071b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0071b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0071b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (C0071b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0071b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (C0071b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return c.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0071b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0071b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0071b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0071b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0071b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (C0071b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0071b) super.mergeUnknownFields(unknownFieldSet);
            }

            public C0071b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.b = "";
                c();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite build() {
                c buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clone() {
                return (C0071b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (C0071b) super.mo0clone();
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
                return (C0071b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public c buildPartial() {
                c cVar = new c(this);
                cVar.f187a = this.f188a;
                cVar.b = this.b;
                onBuilt();
                return cVar;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof c) {
                    return a((c) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public C0071b a(c cVar) {
                if (cVar == c.d) {
                    return this;
                }
                int i = cVar.f187a;
                if (i != 0) {
                    this.f188a = i;
                    onChanged();
                }
                if (!cVar.b().isEmpty()) {
                    this.b = cVar.b;
                    onChanged();
                }
                C0071b c0071b = (C0071b) super.mergeUnknownFields(cVar.unknownFields);
                onChanged();
                return this;
            }
        }

        public c(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.c = (byte) -1;
        }

        public String b() {
            Object obj = this.b;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.b = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: c */
        public C0071b toBuilder() {
            if (this == d) {
                return new C0071b();
            }
            return new C0071b().a(this);
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
            return this.f187a == cVar.f187a && b().equals(cVar.b()) && this.unknownFields.equals(cVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<c> getParserForType() {
            return e;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            ByteString byteString;
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.f187a;
            int computeUInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeUInt32Size(1, i2) : 0;
            Object obj = this.b;
            if (obj instanceof String) {
                byteString = ByteString.copyFromUtf8((String) obj);
                this.b = byteString;
            } else {
                byteString = (ByteString) obj;
            }
            if (!byteString.isEmpty()) {
                computeUInt32Size += GeneratedMessageV3.computeStringSize(2, this.b);
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
            int hashCode = ((((((((((b.k.hashCode() + 779) * 37) + 1) * 53) + this.f187a) * 37) + 2) * 53) + b().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return b.l.ensureFieldAccessorsInitialized(c.class, C0071b.class);
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
            return new c();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            ByteString byteString;
            int i = this.f187a;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            Object obj = this.b;
            if (obj instanceof String) {
                byteString = ByteString.copyFromUtf8((String) obj);
                this.b = byteString;
            } else {
                byteString = (ByteString) obj;
            }
            if (!byteString.isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.b);
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

        public c() {
            this.c = (byte) -1;
            this.b = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new C0071b(builderParent);
        }

        public c(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
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
                                    this.f187a = codedInputStream.readUInt32();
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.b = codedInputStream.readStringRequireUtf8();
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
    public static final class d extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final d e = new d();
        public static final Parser<d> f = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f189a;
        public int b;
        public int c;
        public byte d;

        /* loaded from: classes.dex */
        public class a extends AbstractParser<d> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new d(codedInputStream, extensionRegistryLite);
            }
        }

        /* renamed from: a.b$d$b  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0072b extends GeneratedMessageV3.Builder<C0072b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f190a;
            public int b;
            public int c;

            public C0072b() {
                this.f190a = 0;
                c();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.b.d.C0072b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.b$d> r1 = a.b.d.f     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.b$d r3 = (a.b.d) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    a.b$d r4 = (a.b.d) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: a.b.d.C0072b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.b$d$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0072b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public C0072b clear() {
                super.clear();
                this.f190a = 0;
                this.b = 0;
                this.c = 0;
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

            public final void c() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (C0072b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0072b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (C0072b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return d.e;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return b.s;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return b.t.ensureFieldAccessorsInitialized(d.class, C0072b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0072b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0072b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0072b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0072b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0072b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (C0072b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0072b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (C0072b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return d.e;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0072b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0072b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0072b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0072b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0072b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (C0072b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0072b) super.mergeUnknownFields(unknownFieldSet);
            }

            public C0072b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.f190a = 0;
                c();
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
                return (C0072b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (C0072b) super.mo0clone();
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
                return (C0072b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public d buildPartial() {
                d dVar = new d(this);
                dVar.f189a = this.f190a;
                dVar.b = this.b;
                dVar.c = this.c;
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

            public C0072b a(d dVar) {
                if (dVar == d.e) {
                    return this;
                }
                int i = dVar.f189a;
                if (i != 0) {
                    this.f190a = i;
                    onChanged();
                }
                int i2 = dVar.b;
                if (i2 != 0) {
                    this.b = i2;
                    onChanged();
                }
                int i3 = dVar.c;
                if (i3 != 0) {
                    this.c = i3;
                    onChanged();
                }
                C0072b c0072b = (C0072b) super.mergeUnknownFields(dVar.unknownFields);
                onChanged();
                return this;
            }
        }

        /* loaded from: classes.dex */
        public enum c implements ProtocolMessageEnum {
            default_type(0),
            hr(1),
            stress(2),
            blood_oxygen(3),
            breathe(4),
            UNRECOGNIZED(-1);
            

            /* renamed from: a  reason: collision with root package name */
            public final int f191a;

            static {
                values();
            }

            c(int i) {
                this.f191a = i;
            }

            public static c a(int i) {
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                if (i != 4) {
                                    return null;
                                }
                                return breathe;
                            }
                            return blood_oxygen;
                        }
                        return stress;
                    }
                    return hr;
                }
                return default_type;
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return d.b().getEnumTypes().get(0);
            }

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.f191a;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                if (this != UNRECOGNIZED) {
                    return d.b().getEnumTypes().get(0).getValues().get(ordinal());
                }
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
        }

        public d(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.d = (byte) -1;
        }

        public static final Descriptors.Descriptor b() {
            return b.s;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: c */
        public C0072b toBuilder() {
            if (this == e) {
                return new C0072b();
            }
            return new C0072b().a(this);
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
            return this.f189a == dVar.f189a && this.b == dVar.b && this.c == dVar.c && this.unknownFields.equals(dVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return e;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<d> getParserForType() {
            return f;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = this.f189a != c.default_type.getNumber() ? 0 + CodedOutputStream.computeEnumSize(1, this.f189a) : 0;
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
            int hashCode = ((((((((((((((b.s.hashCode() + 779) * 37) + 1) * 53) + this.f189a) * 37) + 2) * 53) + this.b) * 37) + 3) * 53) + this.c) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return b.t.ensureFieldAccessorsInitialized(d.class, C0072b.class);
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
            return new d();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            if (this.f189a != c.default_type.getNumber()) {
                codedOutputStream.writeEnum(1, this.f189a);
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

        public d() {
            this.d = (byte) -1;
            this.f189a = 0;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new C0072b(builderParent);
        }

        public d(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
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
                                this.f189a = codedInputStream.readEnum();
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
    public static final class e extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final e k = new e();
        public static final Parser<e> l = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f192a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;
        public int i;
        public byte j;

        /* loaded from: classes.dex */
        public class a extends AbstractParser<e> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new e(codedInputStream, extensionRegistryLite);
            }
        }

        /* renamed from: a.b$e$b  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0073b extends GeneratedMessageV3.Builder<C0073b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f193a;
            public int b;
            public int c;
            public int d;
            public int e;
            public int f;
            public int g;
            public int h;
            public int i;

            public C0073b() {
                c();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.b.e.C0073b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.b$e> r1 = a.b.e.l     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.b$e r3 = (a.b.e) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    a.b$e r4 = (a.b.e) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: a.b.e.C0073b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.b$e$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0073b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public C0073b clear() {
                super.clear();
                this.f193a = 0;
                this.b = 0;
                this.c = 0;
                this.d = 0;
                this.e = 0;
                this.f = 0;
                this.g = 0;
                this.h = 0;
                this.i = 0;
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

            public final void c() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (C0073b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0073b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (C0073b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return e.k;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return b.q;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return b.r.ensureFieldAccessorsInitialized(e.class, C0073b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0073b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0073b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0073b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0073b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0073b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (C0073b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0073b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (C0073b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return e.k;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0073b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0073b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0073b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0073b) super.setUnknownFields(unknownFieldSet);
            }

            public C0073b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                c();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0073b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (C0073b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0073b) super.mergeUnknownFields(unknownFieldSet);
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
                return (C0073b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (C0073b) super.mo0clone();
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
                return (C0073b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public e buildPartial() {
                e eVar = new e(this);
                eVar.f192a = this.f193a;
                eVar.b = this.b;
                eVar.c = this.c;
                eVar.d = this.d;
                eVar.e = this.e;
                eVar.f = this.f;
                eVar.g = this.g;
                eVar.h = this.h;
                eVar.i = this.i;
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

            public C0073b a(e eVar) {
                if (eVar == e.k) {
                    return this;
                }
                int i = eVar.f192a;
                if (i != 0) {
                    this.f193a = i;
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
                int i5 = eVar.e;
                if (i5 != 0) {
                    this.e = i5;
                    onChanged();
                }
                int i6 = eVar.f;
                if (i6 != 0) {
                    this.f = i6;
                    onChanged();
                }
                int i7 = eVar.g;
                if (i7 != 0) {
                    this.g = i7;
                    onChanged();
                }
                int i8 = eVar.h;
                if (i8 != 0) {
                    this.h = i8;
                    onChanged();
                }
                int i9 = eVar.i;
                if (i9 != 0) {
                    this.i = i9;
                    onChanged();
                }
                C0073b c0073b = (C0073b) super.mergeUnknownFields(eVar.unknownFields);
                onChanged();
                return this;
            }
        }

        public e(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.j = (byte) -1;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: b */
        public C0073b toBuilder() {
            if (this == k) {
                return new C0073b();
            }
            return new C0073b().a(this);
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
            return this.f192a == eVar.f192a && this.b == eVar.b && this.c == eVar.c && this.d == eVar.d && this.e == eVar.e && this.f == eVar.f && this.g == eVar.g && this.h == eVar.h && this.i == eVar.i && this.unknownFields.equals(eVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return k;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<e> getParserForType() {
            return l;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.f192a;
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
            int i6 = this.e;
            if (i6 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(5, i6);
            }
            int i7 = this.f;
            if (i7 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(6, i7);
            }
            int i8 = this.g;
            if (i8 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(7, i8);
            }
            int i9 = this.h;
            if (i9 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(8, i9);
            }
            int i10 = this.i;
            if (i10 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(9, i10);
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
            int hashCode = ((((((((((((((((((((((((((((((((((((((b.q.hashCode() + 779) * 37) + 1) * 53) + this.f192a) * 37) + 2) * 53) + this.b) * 37) + 3) * 53) + this.c) * 37) + 4) * 53) + this.d) * 37) + 5) * 53) + this.e) * 37) + 6) * 53) + this.f) * 37) + 7) * 53) + this.g) * 37) + 8) * 53) + this.h) * 37) + 9) * 53) + this.i) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return b.r.ensureFieldAccessorsInitialized(e.class, C0073b.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.j;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.j = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Message.Builder newBuilderForType() {
            return k.toBuilder();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new e();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            int i = this.f192a;
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
            int i5 = this.e;
            if (i5 != 0) {
                codedOutputStream.writeUInt32(5, i5);
            }
            int i6 = this.f;
            if (i6 != 0) {
                codedOutputStream.writeUInt32(6, i6);
            }
            int i7 = this.g;
            if (i7 != 0) {
                codedOutputStream.writeUInt32(7, i7);
            }
            int i8 = this.h;
            if (i8 != 0) {
                codedOutputStream.writeUInt32(8, i8);
            }
            int i9 = this.i;
            if (i9 != 0) {
                codedOutputStream.writeUInt32(9, i9);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return k;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public MessageLite.Builder newBuilderForType() {
            return k.toBuilder();
        }

        public e() {
            this.j = (byte) -1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new C0073b(builderParent);
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
                                this.f192a = codedInputStream.readUInt32();
                            } else if (readTag == 16) {
                                this.b = codedInputStream.readUInt32();
                            } else if (readTag == 24) {
                                this.c = codedInputStream.readUInt32();
                            } else if (readTag == 32) {
                                this.d = codedInputStream.readUInt32();
                            } else if (readTag == 40) {
                                this.e = codedInputStream.readUInt32();
                            } else if (readTag == 48) {
                                this.f = codedInputStream.readUInt32();
                            } else if (readTag == 56) {
                                this.g = codedInputStream.readUInt32();
                            } else if (readTag == 64) {
                                this.h = codedInputStream.readUInt32();
                            } else if (readTag != 72) {
                                if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.i = codedInputStream.readUInt32();
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
        public int f194a;
        public byte b;

        /* loaded from: classes.dex */
        public class a extends AbstractParser<f> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new f(codedInputStream, extensionRegistryLite);
            }
        }

        /* renamed from: a.b$f$b  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0074b extends GeneratedMessageV3.Builder<C0074b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f195a;

            public C0074b() {
                c();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.b.f.C0074b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.b$f> r1 = a.b.f.d     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.b$f r3 = (a.b.f) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    a.b$f r4 = (a.b.f) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: a.b.f.C0074b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.b$f$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0074b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public C0074b clear() {
                super.clear();
                this.f195a = 0;
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

            public final void c() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (C0074b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0074b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (C0074b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return f.c;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return b.m;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return b.n.ensureFieldAccessorsInitialized(f.class, C0074b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0074b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0074b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0074b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0074b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0074b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (C0074b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0074b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (C0074b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return f.c;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0074b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0074b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0074b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0074b) super.setUnknownFields(unknownFieldSet);
            }

            public C0074b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                c();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0074b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (C0074b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0074b) super.mergeUnknownFields(unknownFieldSet);
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
                return (C0074b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (C0074b) super.mo0clone();
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
                return (C0074b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public f buildPartial() {
                f fVar = new f(this);
                fVar.f194a = this.f195a;
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

            public C0074b a(f fVar) {
                if (fVar == f.c) {
                    return this;
                }
                int i = fVar.f194a;
                if (i != 0) {
                    this.f195a = i;
                    onChanged();
                }
                C0074b c0074b = (C0074b) super.mergeUnknownFields(fVar.unknownFields);
                onChanged();
                return this;
            }
        }

        public f(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.b = (byte) -1;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: b */
        public C0074b toBuilder() {
            if (this == c) {
                return new C0074b();
            }
            return new C0074b().a(this);
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
            return this.f194a == fVar.f194a && this.unknownFields.equals(fVar.unknownFields);
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
            int i2 = this.f194a;
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
            int hashCode = ((((((b.m.hashCode() + 779) * 37) + 1) * 53) + this.f194a) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return b.n.ensureFieldAccessorsInitialized(f.class, C0074b.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.b;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
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
            int i = this.f194a;
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

        public f() {
            this.b = (byte) -1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new C0074b(builderParent);
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
                                this.f194a = codedInputStream.readUInt32();
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
        public static final g e = new g();
        public static final Parser<g> f = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f196a;
        public int b;
        public int c;
        public byte d;

        /* loaded from: classes.dex */
        public class a extends AbstractParser<g> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new g(codedInputStream, extensionRegistryLite);
            }
        }

        /* renamed from: a.b$g$b  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0075b extends GeneratedMessageV3.Builder<C0075b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f197a;
            public int b;
            public int c;

            public C0075b() {
                this.f197a = 0;
                c();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.b.g.C0075b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.b$g> r1 = a.b.g.f     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.b$g r3 = (a.b.g) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    a.b$g r4 = (a.b.g) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: a.b.g.C0075b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.b$g$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0075b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public C0075b clear() {
                super.clear();
                this.f197a = 0;
                this.b = 0;
                this.c = 0;
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
                return (C0075b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0075b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (C0075b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return g.e;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return b.i;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return b.j.ensureFieldAccessorsInitialized(g.class, C0075b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0075b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0075b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0075b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0075b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0075b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (C0075b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0075b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (C0075b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return g.e;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0075b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0075b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0075b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0075b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0075b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (C0075b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0075b) super.mergeUnknownFields(unknownFieldSet);
            }

            public C0075b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.f197a = 0;
                c();
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
                return (C0075b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (C0075b) super.mo0clone();
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
                return (C0075b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public g buildPartial() {
                g gVar = new g(this);
                gVar.f196a = this.f197a;
                gVar.b = this.b;
                gVar.c = this.c;
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

            public C0075b a(g gVar) {
                if (gVar == g.e) {
                    return this;
                }
                int i = gVar.f196a;
                if (i != 0) {
                    this.f197a = i;
                    onChanged();
                }
                int i2 = gVar.b;
                if (i2 != 0) {
                    this.b = i2;
                    onChanged();
                }
                int i3 = gVar.c;
                if (i3 != 0) {
                    this.c = i3;
                    onChanged();
                }
                C0075b c0075b = (C0075b) super.mergeUnknownFields(gVar.unknownFields);
                onChanged();
                return this;
            }
        }

        /* loaded from: classes.dex */
        public enum c implements ProtocolMessageEnum {
            play_start(0),
            play_stop(1),
            previous_song(2),
            next_song(3),
            volume_up(4),
            volume_reduction(5),
            UNRECOGNIZED(-1);
            

            /* renamed from: a  reason: collision with root package name */
            public final int f198a;

            static {
                values();
            }

            c(int i2) {
                this.f198a = i2;
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return g.b().getEnumTypes().get(0);
            }

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.f198a;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                if (this != UNRECOGNIZED) {
                    return g.b().getEnumTypes().get(0).getValues().get(ordinal());
                }
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
        }

        public g(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.d = (byte) -1;
        }

        public static final Descriptors.Descriptor b() {
            return b.i;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: c */
        public C0075b toBuilder() {
            if (this == e) {
                return new C0075b();
            }
            return new C0075b().a(this);
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
            return this.f196a == gVar.f196a && this.b == gVar.b && this.c == gVar.c && this.unknownFields.equals(gVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return e;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<g> getParserForType() {
            return f;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = this.f196a != c.play_start.getNumber() ? 0 + CodedOutputStream.computeEnumSize(1, this.f196a) : 0;
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
            int hashCode = ((((((((((((((b.i.hashCode() + 779) * 37) + 1) * 53) + this.f196a) * 37) + 2) * 53) + this.b) * 37) + 3) * 53) + this.c) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return b.j.ensureFieldAccessorsInitialized(g.class, C0075b.class);
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
            return new g();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            if (this.f196a != c.play_start.getNumber()) {
                codedOutputStream.writeEnum(1, this.f196a);
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

        public g() {
            this.d = (byte) -1;
            this.f196a = 0;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new C0075b(builderParent);
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
                            if (readTag == 8) {
                                this.f196a = codedInputStream.readEnum();
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
    public static final class h extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final h c = new h();
        public static final Parser<h> d = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f199a;
        public byte b;

        /* loaded from: classes.dex */
        public class a extends AbstractParser<h> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new h(codedInputStream, extensionRegistryLite);
            }
        }

        /* renamed from: a.b$h$b  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0076b extends GeneratedMessageV3.Builder<C0076b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f200a;

            public C0076b() {
                this.f200a = 0;
                c();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.b.h.C0076b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.b$h> r1 = a.b.h.d     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.b$h r3 = (a.b.h) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    a.b$h r4 = (a.b.h) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: a.b.h.C0076b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.b$h$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0076b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public C0076b clear() {
                super.clear();
                this.f200a = 0;
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

            public final void c() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (C0076b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0076b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (C0076b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return h.c;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return b.e;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return b.f.ensureFieldAccessorsInitialized(h.class, C0076b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0076b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0076b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0076b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0076b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0076b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (C0076b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0076b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (C0076b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return h.c;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0076b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0076b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0076b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0076b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0076b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (C0076b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0076b) super.mergeUnknownFields(unknownFieldSet);
            }

            public C0076b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.f200a = 0;
                c();
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
                return (C0076b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (C0076b) super.mo0clone();
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
                return (C0076b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public h buildPartial() {
                h hVar = new h(this);
                hVar.f199a = this.f200a;
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

            public C0076b a(h hVar) {
                if (hVar == h.c) {
                    return this;
                }
                int i = hVar.f199a;
                if (i != 0) {
                    this.f200a = i;
                    onChanged();
                }
                C0076b c0076b = (C0076b) super.mergeUnknownFields(hVar.unknownFields);
                onChanged();
                return this;
            }
        }

        /* loaded from: classes.dex */
        public enum c implements ProtocolMessageEnum {
            default_type(0),
            apple_music(1),
            deeze(2),
            spotify(3),
            UNRECOGNIZED(-1);
            

            /* renamed from: a  reason: collision with root package name */
            public final int f201a;

            static {
                values();
            }

            c(int i) {
                this.f201a = i;
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return h.b().getEnumTypes().get(0);
            }

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.f201a;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                if (this != UNRECOGNIZED) {
                    return h.b().getEnumTypes().get(0).getValues().get(ordinal());
                }
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
        }

        public h(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.b = (byte) -1;
        }

        public static final Descriptors.Descriptor b() {
            return b.e;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: c */
        public C0076b toBuilder() {
            if (this == c) {
                return new C0076b();
            }
            return new C0076b().a(this);
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
            return this.f199a == hVar.f199a && this.unknownFields.equals(hVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return c;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<h> getParserForType() {
            return d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = (this.f199a != c.default_type.getNumber() ? 0 + CodedOutputStream.computeEnumSize(1, this.f199a) : 0) + this.unknownFields.getSerializedSize();
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
            int hashCode = ((((((b.e.hashCode() + 779) * 37) + 1) * 53) + this.f199a) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return b.f.ensureFieldAccessorsInitialized(h.class, C0076b.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.b;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
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
            return new h();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            if (this.f199a != c.default_type.getNumber()) {
                codedOutputStream.writeEnum(1, this.f199a);
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

        public h() {
            this.b = (byte) -1;
            this.f199a = 0;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new C0076b(builderParent);
        }

        public h(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
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
                                this.f199a = codedInputStream.readEnum();
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
    public static final class i extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final i j = new i();
        public static final Parser<i> k = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f202a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;
        public byte i;

        /* loaded from: classes.dex */
        public class a extends AbstractParser<i> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new i(codedInputStream, extensionRegistryLite);
            }
        }

        /* renamed from: a.b$i$b  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0077b extends GeneratedMessageV3.Builder<C0077b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f203a;
            public int b;
            public int c;
            public int d;
            public int e;
            public int f;
            public int g;
            public int h;

            public C0077b() {
                c();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.b.i.C0077b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.b$i> r1 = a.b.i.k     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.b$i r3 = (a.b.i) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    a.b$i r4 = (a.b.i) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: a.b.i.C0077b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.b$i$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0077b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public C0077b clear() {
                super.clear();
                this.f203a = 0;
                this.b = 0;
                this.c = 0;
                this.d = 0;
                this.e = 0;
                this.f = 0;
                this.g = 0;
                this.h = 0;
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

            public final void c() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (C0077b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0077b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (C0077b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return i.j;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return b.o;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return b.p.ensureFieldAccessorsInitialized(i.class, C0077b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0077b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0077b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0077b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0077b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0077b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (C0077b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0077b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (C0077b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return i.j;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0077b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0077b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0077b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0077b) super.setUnknownFields(unknownFieldSet);
            }

            public C0077b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                c();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0077b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (C0077b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0077b) super.mergeUnknownFields(unknownFieldSet);
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
                return (C0077b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (C0077b) super.mo0clone();
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
                return (C0077b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public i buildPartial() {
                i iVar = new i(this);
                iVar.f202a = this.f203a;
                iVar.b = this.b;
                iVar.c = this.c;
                iVar.d = this.d;
                iVar.e = this.e;
                iVar.f = this.f;
                iVar.g = this.g;
                iVar.h = this.h;
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

            public C0077b a(i iVar) {
                if (iVar == i.j) {
                    return this;
                }
                int i = iVar.f202a;
                if (i != 0) {
                    this.f203a = i;
                    onChanged();
                }
                int i2 = iVar.b;
                if (i2 != 0) {
                    this.b = i2;
                    onChanged();
                }
                int i3 = iVar.c;
                if (i3 != 0) {
                    this.c = i3;
                    onChanged();
                }
                int i4 = iVar.d;
                if (i4 != 0) {
                    this.d = i4;
                    onChanged();
                }
                int i5 = iVar.e;
                if (i5 != 0) {
                    this.e = i5;
                    onChanged();
                }
                int i6 = iVar.f;
                if (i6 != 0) {
                    this.f = i6;
                    onChanged();
                }
                int i7 = iVar.g;
                if (i7 != 0) {
                    this.g = i7;
                    onChanged();
                }
                int i8 = iVar.h;
                if (i8 != 0) {
                    this.h = i8;
                    onChanged();
                }
                C0077b c0077b = (C0077b) super.mergeUnknownFields(iVar.unknownFields);
                onChanged();
                return this;
            }
        }

        public i(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.i = (byte) -1;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: b */
        public C0077b toBuilder() {
            if (this == j) {
                return new C0077b();
            }
            return new C0077b().a(this);
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
            return this.f202a == iVar.f202a && this.b == iVar.b && this.c == iVar.c && this.d == iVar.d && this.e == iVar.e && this.f == iVar.f && this.g == iVar.g && this.h == iVar.h && this.unknownFields.equals(iVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return j;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<i> getParserForType() {
            return k;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.f202a;
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
            int i6 = this.e;
            if (i6 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(5, i6);
            }
            int i7 = this.f;
            if (i7 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(6, i7);
            }
            int i8 = this.g;
            if (i8 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(7, i8);
            }
            int i9 = this.h;
            if (i9 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(8, i9);
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
            int hashCode = ((((((((((((((((((((((((((((((((((b.o.hashCode() + 779) * 37) + 1) * 53) + this.f202a) * 37) + 2) * 53) + this.b) * 37) + 3) * 53) + this.c) * 37) + 4) * 53) + this.d) * 37) + 5) * 53) + this.e) * 37) + 6) * 53) + this.f) * 37) + 7) * 53) + this.g) * 37) + 8) * 53) + this.h) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return b.p.ensureFieldAccessorsInitialized(i.class, C0077b.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.i;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.i = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Message.Builder newBuilderForType() {
            return j.toBuilder();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new i();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            int i = this.f202a;
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
            int i5 = this.e;
            if (i5 != 0) {
                codedOutputStream.writeUInt32(5, i5);
            }
            int i6 = this.f;
            if (i6 != 0) {
                codedOutputStream.writeUInt32(6, i6);
            }
            int i7 = this.g;
            if (i7 != 0) {
                codedOutputStream.writeUInt32(7, i7);
            }
            int i8 = this.h;
            if (i8 != 0) {
                codedOutputStream.writeUInt32(8, i8);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return j;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public MessageLite.Builder newBuilderForType() {
            return j.toBuilder();
        }

        public i() {
            this.i = (byte) -1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new C0077b(builderParent);
        }

        public i(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
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
                                    this.f202a = codedInputStream.readUInt32();
                                } else if (readTag == 16) {
                                    this.b = codedInputStream.readUInt32();
                                } else if (readTag == 24) {
                                    this.c = codedInputStream.readUInt32();
                                } else if (readTag == 32) {
                                    this.d = codedInputStream.readUInt32();
                                } else if (readTag == 40) {
                                    this.e = codedInputStream.readUInt32();
                                } else if (readTag == 48) {
                                    this.f = codedInputStream.readUInt32();
                                } else if (readTag == 56) {
                                    this.g = codedInputStream.readUInt32();
                                } else if (readTag != 64) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.h = codedInputStream.readUInt32();
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        }
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
    public static final class j extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final j d = new j();
        public static final Parser<j> e = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f204a;
        public int b;
        public byte c;

        /* loaded from: classes.dex */
        public class a extends AbstractParser<j> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new j(codedInputStream, extensionRegistryLite);
            }
        }

        /* renamed from: a.b$j$b  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0078b extends GeneratedMessageV3.Builder<C0078b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f205a;
            public int b;

            public C0078b() {
                this.f205a = 0;
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
            public a.b.j.C0078b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.b$j> r1 = a.b.j.e     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.b$j r3 = (a.b.j) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    a.b$j r4 = (a.b.j) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: a.b.j.C0078b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.b$j$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0078b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public j buildPartial() {
                j jVar = new j(this);
                jVar.f204a = this.f205a;
                jVar.b = this.b;
                onBuilt();
                return jVar;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: c */
            public C0078b clear() {
                super.clear();
                this.f205a = 0;
                this.b = 0;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (C0078b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0078b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (C0078b) super.mo0clone();
            }

            public final void d() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return j.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return b.c;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return b.d.ensureFieldAccessorsInitialized(j.class, C0078b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0078b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0078b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0078b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0078b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0078b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (C0078b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0078b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (C0078b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return j.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0078b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0078b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0078b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0078b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0078b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (C0078b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0078b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clone() {
                return (C0078b) super.mo0clone();
            }

            public C0078b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.f205a = 0;
                this.b = 0;
                d();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (C0078b) super.mo0clone();
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
                return (C0078b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public j build() {
                j buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            public C0078b b(int i) {
                this.f205a = i;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof j) {
                    return a((j) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public C0078b a(int i) {
                this.b = i;
                onChanged();
                return this;
            }

            public C0078b a(j jVar) {
                if (jVar == j.d) {
                    return this;
                }
                int i = jVar.f204a;
                if (i != 0) {
                    this.f205a = i;
                    onChanged();
                }
                int i2 = jVar.b;
                if (i2 != 0) {
                    this.b = i2;
                    onChanged();
                }
                C0078b c0078b = (C0078b) super.mergeUnknownFields(jVar.unknownFields);
                onChanged();
                return this;
            }
        }

        /* loaded from: classes.dex */
        public enum c implements ProtocolMessageEnum {
            search_phone(0),
            stop_search_phone(1),
            connect_the_camera(2),
            start_taking_pictures(3),
            stop_taking_pictures(4),
            request_the_latest_weather(5),
            request_the_agps(6),
            request_the_menstrual_cycle(7),
            big_8803data_update_finish(8),
            mic_record_ready(9),
            mic_record_stop(10),
            stop_search_watch(11),
            request_bt_one_key_connect(12),
            app_sport_pause(13),
            app_sport_continue(14),
            app_sport_end(15),
            incoming_call_accept(16),
            incoming_call_reject(17),
            not_disturb_open(18),
            not_disturb_close(19),
            gestures_open(20),
            gestures_close(21),
            UNRECOGNIZED(-1);
            

            /* renamed from: a  reason: collision with root package name */
            public final int f206a;

            static {
                values();
            }

            c(int i) {
                this.f206a = i;
            }

            public static c a(int i) {
                switch (i) {
                    case 0:
                        return search_phone;
                    case 1:
                        return stop_search_phone;
                    case 2:
                        return connect_the_camera;
                    case 3:
                        return start_taking_pictures;
                    case 4:
                        return stop_taking_pictures;
                    case 5:
                        return request_the_latest_weather;
                    case 6:
                        return request_the_agps;
                    case 7:
                        return request_the_menstrual_cycle;
                    case 8:
                        return big_8803data_update_finish;
                    case 9:
                        return mic_record_ready;
                    case 10:
                        return mic_record_stop;
                    case 11:
                        return stop_search_watch;
                    case 12:
                        return request_bt_one_key_connect;
                    case 13:
                        return app_sport_pause;
                    case 14:
                        return app_sport_continue;
                    case 15:
                        return app_sport_end;
                    case 16:
                        return incoming_call_accept;
                    case 17:
                        return incoming_call_reject;
                    case 18:
                        return not_disturb_open;
                    case 19:
                        return not_disturb_close;
                    case 20:
                        return gestures_open;
                    case 21:
                        return gestures_close;
                    default:
                        return null;
                }
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return j.b().getEnumTypes().get(0);
            }

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.f206a;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                if (this != UNRECOGNIZED) {
                    return j.b().getEnumTypes().get(0).getValues().get(ordinal());
                }
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
        }

        /* loaded from: classes.dex */
        public enum d implements ProtocolMessageEnum {
            execute(0),
            success(1),
            fail(2),
            UNRECOGNIZED(-1);
            

            /* renamed from: a  reason: collision with root package name */
            public final int f207a;

            static {
                values();
            }

            d(int i) {
                this.f207a = i;
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return j.b().getEnumTypes().get(1);
            }

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.f207a;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                if (this != UNRECOGNIZED) {
                    return j.b().getEnumTypes().get(1).getValues().get(ordinal());
                }
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
        }

        public j(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.c = (byte) -1;
        }

        public static final Descriptors.Descriptor b() {
            return b.c;
        }

        public c c() {
            c a2 = c.a(this.f204a);
            return a2 == null ? c.UNRECOGNIZED : a2;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: d */
        public C0078b toBuilder() {
            if (this == d) {
                return new C0078b();
            }
            return new C0078b().a(this);
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
            return this.f204a == jVar.f204a && this.b == jVar.b && this.unknownFields.equals(jVar.unknownFields);
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
            int computeEnumSize = this.f204a != c.search_phone.getNumber() ? 0 + CodedOutputStream.computeEnumSize(1, this.f204a) : 0;
            if (this.b != d.execute.getNumber()) {
                computeEnumSize += CodedOutputStream.computeEnumSize(2, this.b);
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
            int hashCode = ((((((((((b.c.hashCode() + 779) * 37) + 1) * 53) + this.f204a) * 37) + 2) * 53) + this.b) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return b.d.ensureFieldAccessorsInitialized(j.class, C0078b.class);
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
            return new j();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            if (this.f204a != c.search_phone.getNumber()) {
                codedOutputStream.writeEnum(1, this.f204a);
            }
            if (this.b != d.execute.getNumber()) {
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

        public j() {
            this.c = (byte) -1;
            this.f204a = 0;
            this.b = 0;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new C0078b(builderParent);
        }

        public j(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
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
                                    this.f204a = codedInputStream.readEnum();
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

    static {
        Descriptors.FileDescriptor internalBuildGeneratedFileFrom = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001aeastapex_s8800_c8802.proto\"y\n\rd8802_respond\u0012\u0012\n\nrequest_id\u0018\u0001 \u0001(\r\u0012/\n\fe_error_code\u0018\u0002 \u0001(\u000e2\u0019.d8802_respond.error_code\"#\n\nerror_code\u0012\u000b\n\u0007success\u0010\u0000\u0012\b\n\u0004fail\u0010\u0001\"½\u0005\n\u0010ud8802_phone_ops\u0012$\n\u0005e_ops\u0018\u0001 \u0001(\u000e2\u0015.ud8802_phone_ops.ops\u00122\n\fe_ops_status\u0018\u0002 \u0001(\u000e2\u001c.ud8802_phone_ops.ops_status\"\u009c\u0004\n\u0003ops\u0012\u0010\n\fsearch_phone\u0010\u0000\u0012\u0015\n\u0011stop_search_phone\u0010\u0001\u0012\u0016\n\u0012connect_the_camera\u0010\u0002\u0012\u0019\n\u0015start_taking_pictures\u0010\u0003\u0012\u0018\n\u0014stop_taking_pictures\u0010\u0004\u0012\u001e\n\u001arequest_the_latest_weather\u0010\u0005\u0012\u0014\n\u0010request_the_agps\u0010\u0006\u0012\u001f\n\u001brequest_the_menstrual_cycle\u0010\u0007\u0012\u001e\n\u001abig_8803data_update_finish\u0010\b\u0012\u0014\n\u0010mic_record_ready\u0010\t\u0012\u0013\n\u000fmic_record_stop\u0010\n\u0012\u0015\n\u0011stop_search_watch\u0010\u000b\u0012\u001e\n\u001arequest_bt_one_key_connect\u0010\f\u0012\u0013\n\u000fapp_sport_pause\u0010\r\u0012\u0016\n\u0012app_sport_continue\u0010\u000e\u0012\u0011\n\rapp_sport_end\u0010\u000f\u0012\u0018\n\u0014incoming_call_accept\u0010\u0010\u0012\u0018\n\u0014incoming_call_reject\u0010\u0011\u0012\u0014\n\u0010not_disturb_open\u0010\u0012\u0012\u0015\n\u0011not_disturb_close\u0010\u0013\u0012\u0011\n\rgestures_open\u0010\u0014\u0012\u0012\n\u000egestures_close\u0010\u0015\"0\n\nops_status\u0012\u000b\n\u0007execute\u0010\u0000\u0012\u000b\n\u0007success\u0010\u0001\u0012\b\n\u0004fail\u0010\u0002\"\u0080\u0001\n\u0013u8802_music_request\u0012'\n\u0005e_app\u0018\u0001 \u0001(\u000e2\u0018.u8802_music_request.app\"@\n\u0003app\u0012\u0010\n\fdefault_type\u0010\u0000\u0012\u000f\n\u000bapple_music\u0010\u0001\u0012\t\n\u0005deeze\u0010\u0002\u0012\u000b\n\u0007spotify\u0010\u0003\"Ð\u0001\n\u0013d8802_music_respond\u0012-\n\be_status\u0018\u0001 \u0001(\u000e2\u001b.d8802_music_respond.status\u0012\u000e\n\u0006volume\u0018\u0002 \u0001(\r\u0012\u000f\n\u0007content\u0018\u0003 \u0001(\t\u0012\u000e\n\u0006artist\u0018\u0004 \u0001(\t\u0012\u0010\n\bduration\u0018\u0005 \u0001(\r\u0012\u0013\n\u000belapsedtime\u0018\u0006 \u0001(\r\"2\n\u0006status\u0012\f\n\bnot_play\u0010\u0000\u0012\u000b\n\u0007playing\u0010\u0001\u0012\r\n\tstop_play\u0010\u0002\"Ð\u0001\n\u0013u8802_music_control\u0012'\n\u0005e_ops\u0018\u0001 \u0001(\u000e2\u0018.u8802_music_control.ops\u0012\u000e\n\u0006volume\u0018\u0002 \u0001(\r\u0012\u0013\n\u000belapsedtime\u0018\u0003 \u0001(\r\"k\n\u0003ops\u0012\u000e\n\nplay_start\u0010\u0000\u0012\r\n\tplay_stop\u0010\u0001\u0012\u0011\n\rprevious_song\u0010\u0002\u0012\r\n\tnext_song\u0010\u0003\u0012\r\n\tvolume_up\u0010\u0004\u0012\u0014\n\u0010volume_reduction\u0010\u0005\"7\n\u0018u8802_android_ancs_reply\u0012\n\n\u0002id\u0018\u0001 \u0001(\r\u0012\u000f\n\u0007content\u0018\u0002 \u0001(\t\"\u001e\n\tu8802_mtu\u0012\u0011\n\tmtu_value\u0018\u0001 \u0001(\r\"\u009f\u0001\n\u0014u8802_real_time_data\u0012\r\n\u0005steps\u0018\u0001 \u0001(\r\u0012\u000f\n\u0007calorie\u0018\u0002 \u0001(\r\u0012\u0010\n\bdistance\u0018\u0003 \u0001(\r\u0012\u0010\n\bduration\u0018\u0004 \u0001(\r\u0012\n\n\u0002hr\u0018\u0005 \u0001(\r\u0012\u0010\n\bpressure\u0018\u0006 \u0001(\r\u0012\u0014\n\fblood_oxygen\u0018\u0007 \u0001(\r\u0012\u000f\n\u0007bat_lev\u0018\b \u0001(\r\"º\u0001\n\u001eu8802_app_sport_real_time_data\u0012\r\n\u0005steps\u0018\u0001 \u0001(\r\u0012\u000f\n\u0007calorie\u0018\u0002 \u0001(\r\u0012\n\n\u0002hr\u0018\u0003 \u0001(\r\u0012\u0011\n\ttimestamp\u0018\u0004 \u0001(\r\u0012\u0010\n\bdistance\u0018\u0005 \u0001(\r\u0012\r\n\u0005count\u0018\u0006 \u0001(\r\u0012\u0010\n\baltitude\u0018\u0007 \u0001(\r\u0012\f\n\u0004pace\u0018\b \u0001(\r\u0012\u0018\n\u0010stride_frequency\u0018\t \u0001(\r\"Â\u0001\n\u0012u8802_app_ops_data\u00128\n\u000ee_app_ops_type\u0018\u0001 \u0001(\u000e2 .u8802_app_ops_data.app_ops_type\u0012\r\n\u0005value\u0018\u0002 \u0001(\r\u0012\u000e\n\u0006status\u0018\u0003 \u0001(\r\"S\n\fapp_ops_type\u0012\u0010\n\fdefault_type\u0010\u0000\u0012\u0006\n\u0002hr\u0010\u0001\u0012\n\n\u0006stress\u0010\u0002\u0012\u0010\n\fblood_oxygen\u0010\u0003\u0012\u000b\n\u0007breathe\u0010\u0004B\f\n\nproto2javab\u0006proto3"}, new Descriptors.FileDescriptor[0]);
        u = internalBuildGeneratedFileFrom;
        Descriptors.Descriptor descriptor = internalBuildGeneratedFileFrom.getMessageTypes().get(0);
        f180a = descriptor;
        b = new GeneratedMessageV3.FieldAccessorTable(descriptor, new String[]{"RequestId", "EErrorCode"});
        Descriptors.Descriptor descriptor2 = u.getMessageTypes().get(1);
        c = descriptor2;
        d = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"EOps", "EOpsStatus"});
        Descriptors.Descriptor descriptor3 = u.getMessageTypes().get(2);
        e = descriptor3;
        f = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"EApp"});
        Descriptors.Descriptor descriptor4 = u.getMessageTypes().get(3);
        g = descriptor4;
        h = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"EStatus", "Volume", "Content", ExifInterface.TAG_ARTIST, "Duration", "Elapsedtime"});
        Descriptors.Descriptor descriptor5 = u.getMessageTypes().get(4);
        i = descriptor5;
        j = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"EOps", "Volume", "Elapsedtime"});
        Descriptors.Descriptor descriptor6 = u.getMessageTypes().get(5);
        k = descriptor6;
        l = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"Id", "Content"});
        Descriptors.Descriptor descriptor7 = u.getMessageTypes().get(6);
        m = descriptor7;
        n = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"MtuValue"});
        Descriptors.Descriptor descriptor8 = u.getMessageTypes().get(7);
        o = descriptor8;
        p = new GeneratedMessageV3.FieldAccessorTable(descriptor8, new String[]{"Steps", "Calorie", "Distance", "Duration", "Hr", "Pressure", "BloodOxygen", "BatLev"});
        Descriptors.Descriptor descriptor9 = u.getMessageTypes().get(8);
        q = descriptor9;
        r = new GeneratedMessageV3.FieldAccessorTable(descriptor9, new String[]{"Steps", "Calorie", "Hr", "Timestamp", "Distance", "Count", "Altitude", "Pace", "StrideFrequency"});
        Descriptors.Descriptor descriptor10 = u.getMessageTypes().get(9);
        s = descriptor10;
        t = new GeneratedMessageV3.FieldAccessorTable(descriptor10, new String[]{"EAppOpsType", "Value", "Status"});
    }
}
