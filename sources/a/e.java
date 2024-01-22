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
public final class e {

    /* renamed from: a  reason: collision with root package name */
    public static final Descriptors.Descriptor f286a;
    public static final GeneratedMessageV3.FieldAccessorTable b;
    public static final Descriptors.Descriptor c;
    public static final GeneratedMessageV3.FieldAccessorTable d;
    public static final Descriptors.Descriptor e;
    public static final Descriptors.Descriptor f;
    public static Descriptors.FileDescriptor g;

    /* loaded from: classes.dex */
    public static final class a extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final a k = new a();
        public static final Parser<a> l = new C0098a();

        /* renamed from: a  reason: collision with root package name */
        public int f287a;
        public volatile Object b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;
        public int i;
        public byte j;

        /* renamed from: a.e$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0098a extends AbstractParser<a> {
            @Override // com.google.protobuf.Parser
            public Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new a(codedInputStream, extensionRegistryLite);
            }
        }

        /* loaded from: classes.dex */
        public static final class b extends GeneratedMessageV3.Builder<b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f288a;
            public Object b;
            public int c;
            public int d;
            public int e;
            public int f;
            public int g;
            public int h;
            public int i;

            public b() {
                this.f288a = 0;
                this.b = "";
                d();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public a.e.a.b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.e$a> r1 = a.e.a.l     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.e$a r3 = (a.e.a) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    a.e$a r4 = (a.e.a) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: a.e.a.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.e$a$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public a buildPartial() {
                a aVar = new a(this);
                aVar.f287a = this.f288a;
                aVar.b = this.b;
                aVar.c = this.c;
                aVar.d = this.d;
                aVar.e = this.e;
                aVar.f = this.f;
                aVar.g = this.g;
                aVar.h = this.h;
                aVar.i = this.i;
                onBuilt();
                return aVar;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: c */
            public b clear() {
                super.clear();
                this.f288a = 0;
                this.b = "";
                this.c = 0;
                this.d = 0;
                this.e = 0;
                this.f = 0;
                this.g = 0;
                this.h = 0;
                this.i = 0;
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

            public b e(int i) {
                this.h = i;
                onChanged();
                return this;
            }

            public b f(int i) {
                this.f = i;
                onChanged();
                return this;
            }

            public b g(int i) {
                this.c = i;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return a.k;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return e.c;
            }

            public b h(int i) {
                this.g = i;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return e.d.ensureFieldAccessorsInitialized(a.class, b.class);
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
                this.i = i;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return a.k;
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

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clone() {
                return (b) super.mo0clone();
            }

            public b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.f288a = 0;
                this.b = "";
                d();
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

            public b a(String str) {
                this.b = str;
                onChanged();
                return this;
            }

            public b c(int i) {
                this.f288a = i;
                onChanged();
                return this;
            }

            public b a(int i) {
                this.e = i;
                onChanged();
                return this;
            }

            public b a(a aVar) {
                if (aVar == a.k) {
                    return this;
                }
                int i = aVar.f287a;
                if (i != 0) {
                    this.f288a = i;
                    onChanged();
                }
                if (!aVar.c().isEmpty()) {
                    this.b = aVar.b;
                    onChanged();
                }
                int i2 = aVar.c;
                if (i2 != 0) {
                    this.c = i2;
                    onChanged();
                }
                int i3 = aVar.d;
                if (i3 != 0) {
                    this.d = i3;
                    onChanged();
                }
                int i4 = aVar.e;
                if (i4 != 0) {
                    this.e = i4;
                    onChanged();
                }
                int i5 = aVar.f;
                if (i5 != 0) {
                    this.f = i5;
                    onChanged();
                }
                int i6 = aVar.g;
                if (i6 != 0) {
                    this.g = i6;
                    onChanged();
                }
                int i7 = aVar.h;
                if (i7 != 0) {
                    this.h = i7;
                    onChanged();
                }
                int i8 = aVar.i;
                if (i8 != 0) {
                    this.i = i8;
                    onChanged();
                }
                b bVar = (b) super.mergeUnknownFields(aVar.unknownFields);
                onChanged();
                return this;
            }

            public b b(int i) {
                this.d = i;
                onChanged();
                return this;
            }
        }

        /* loaded from: classes.dex */
        public enum c implements ProtocolMessageEnum {
            apollo(0),
            stm32(1),
            res(2),
            tp(3),
            hr(4),
            gps(5),
            agps(6),
            user_wf(7),
            UNRECOGNIZED(-1);
            

            /* renamed from: a  reason: collision with root package name */
            public final int f289a;

            static {
                values();
            }

            c(int i) {
                this.f289a = i;
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return a.b().getEnumTypes().get(0);
            }

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.f289a;
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
            this.j = (byte) -1;
        }

        public static final Descriptors.Descriptor b() {
            return e.c;
        }

        public String c() {
            Object obj = this.b;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.b = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: d */
        public b toBuilder() {
            if (this == k) {
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
            return this.f287a == aVar.f287a && c().equals(aVar.c()) && this.c == aVar.c && this.d == aVar.d && this.e == aVar.e && this.f == aVar.f && this.g == aVar.g && this.h == aVar.h && this.i == aVar.i && this.unknownFields.equals(aVar.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Message getDefaultInstanceForType() {
            return k;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<a> getParserForType() {
            return l;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            ByteString byteString;
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = this.f287a != c.apollo.getNumber() ? 0 + CodedOutputStream.computeEnumSize(1, this.f287a) : 0;
            Object obj = this.b;
            if (obj instanceof String) {
                byteString = ByteString.copyFromUtf8((String) obj);
                this.b = byteString;
            } else {
                byteString = (ByteString) obj;
            }
            if (!byteString.isEmpty()) {
                computeEnumSize += GeneratedMessageV3.computeStringSize(2, this.b);
            }
            int i2 = this.c;
            if (i2 != 0) {
                computeEnumSize += CodedOutputStream.computeUInt32Size(3, i2);
            }
            int i3 = this.d;
            if (i3 != 0) {
                computeEnumSize += CodedOutputStream.computeUInt32Size(4, i3);
            }
            int i4 = this.e;
            if (i4 != 0) {
                computeEnumSize += CodedOutputStream.computeUInt32Size(5, i4);
            }
            int i5 = this.f;
            if (i5 != 0) {
                computeEnumSize += CodedOutputStream.computeUInt32Size(6, i5);
            }
            int i6 = this.g;
            if (i6 != 0) {
                computeEnumSize += CodedOutputStream.computeUInt32Size(7, i6);
            }
            int i7 = this.h;
            if (i7 != 0) {
                computeEnumSize += CodedOutputStream.computeUInt32Size(8, i7);
            }
            int i8 = this.i;
            if (i8 != 0) {
                computeEnumSize += CodedOutputStream.computeUInt32Size(9, i8);
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
            int hashCode = ((((((((((((((((((((((((((((((((((((((e.c.hashCode() + 779) * 37) + 1) * 53) + this.f287a) * 37) + 2) * 53) + c().hashCode()) * 37) + 3) * 53) + this.c) * 37) + 4) * 53) + this.d) * 37) + 5) * 53) + this.e) * 37) + 6) * 53) + this.f) * 37) + 7) * 53) + this.g) * 37) + 8) * 53) + this.h) * 37) + 9) * 53) + this.i) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return e.d.ensureFieldAccessorsInitialized(a.class, b.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b2 = this.j;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
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
            return new a();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) {
            ByteString byteString;
            if (this.f287a != c.apollo.getNumber()) {
                codedOutputStream.writeEnum(1, this.f287a);
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
            int i = this.c;
            if (i != 0) {
                codedOutputStream.writeUInt32(3, i);
            }
            int i2 = this.d;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(4, i2);
            }
            int i3 = this.e;
            if (i3 != 0) {
                codedOutputStream.writeUInt32(5, i3);
            }
            int i4 = this.f;
            if (i4 != 0) {
                codedOutputStream.writeUInt32(6, i4);
            }
            int i5 = this.g;
            if (i5 != 0) {
                codedOutputStream.writeUInt32(7, i5);
            }
            int i6 = this.h;
            if (i6 != 0) {
                codedOutputStream.writeUInt32(8, i6);
            }
            int i7 = this.i;
            if (i7 != 0) {
                codedOutputStream.writeUInt32(9, i7);
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

        public a() {
            this.j = (byte) -1;
            this.f287a = 0;
            this.b = "";
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
                                this.f287a = codedInputStream.readEnum();
                            } else if (readTag == 18) {
                                this.b = codedInputStream.readStringRequireUtf8();
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
    public static final class b extends GeneratedMessageV3 implements MessageOrBuilder {
        public static final b e = new b();
        public static final Parser<b> f = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f290a;
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

        /* renamed from: a.e$b$b  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0099b extends GeneratedMessageV3.Builder<C0099b> implements Object {

            /* renamed from: a  reason: collision with root package name */
            public int f291a;
            public int b;
            public int c;

            public C0099b() {
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
            public a.e.b.C0099b mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<a.e$b> r1 = a.e.b.f     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    a.e$b r3 = (a.e.b) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    a.e$b r4 = (a.e.b) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: a.e.b.C0099b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):a.e$b$b");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0099b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b */
            public C0099b clear() {
                super.clear();
                this.f291a = 0;
                this.b = 0;
                this.c = 0;
                return this;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message build() {
                b buildPartial = buildPartial();
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
                return (C0099b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0099b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder clone() {
                return (C0099b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Message getDefaultInstanceForType() {
                return b.e;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return e.f286a;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return e.b.ensureFieldAccessorsInitialized(b.class, C0099b.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public AbstractMessage.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0099b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0099b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0099b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0099b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0099b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (C0099b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0099b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AbstractMessageLite.Builder clone() {
                return (C0099b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return b.e;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0099b) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (C0099b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (C0099b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Message.Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0099b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (C0099b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneratedMessageV3.Builder clone() {
                return (C0099b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (C0099b) super.mergeUnknownFields(unknownFieldSet);
            }

            public C0099b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.b = 0;
                c();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite build() {
                b buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Message.Builder clone() {
                return (C0099b) super.mo0clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MessageLite.Builder clone() {
                return (C0099b) super.mo0clone();
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
                return (C0099b) super.mo0clone();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a */
            public b buildPartial() {
                b bVar = new b(this);
                bVar.f290a = this.f291a;
                bVar.b = this.b;
                bVar.c = this.c;
                onBuilt();
                return bVar;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Message.Builder mergeFrom(Message message) {
                if (message instanceof b) {
                    return a((b) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public C0099b a(b bVar) {
                if (bVar == b.e) {
                    return this;
                }
                int i = bVar.f290a;
                if (i != 0) {
                    this.f291a = i;
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
                C0099b c0099b = (C0099b) super.mergeUnknownFields(bVar.unknownFields);
                onChanged();
                return this;
            }
        }

        /* loaded from: classes.dex */
        public enum c implements ProtocolMessageEnum {
            accept(0),
            reject(1),
            reject_version_error(2),
            proceed(3),
            crc_error(4),
            complete(5),
            UNRECOGNIZED(-1);
            

            /* renamed from: a  reason: collision with root package name */
            public final int f292a;

            static {
                values();
            }

            c(int i2) {
                this.f292a = i2;
            }

            public static c a(int i2) {
                if (i2 != 0) {
                    if (i2 != 1) {
                        if (i2 != 2) {
                            if (i2 != 3) {
                                if (i2 != 4) {
                                    if (i2 != 5) {
                                        return null;
                                    }
                                    return complete;
                                }
                                return crc_error;
                            }
                            return proceed;
                        }
                        return reject_version_error;
                    }
                    return reject;
                }
                return accept;
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return b.b().getEnumTypes().get(0);
            }

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.f292a;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Override // com.google.protobuf.ProtocolMessageEnum
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                if (this != UNRECOGNIZED) {
                    return b.b().getEnumTypes().get(0).getValues().get(ordinal());
                }
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
        }

        public b(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.d = (byte) -1;
        }

        public static final Descriptors.Descriptor b() {
            return e.f286a;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: c */
        public C0099b toBuilder() {
            if (this == e) {
                return new C0099b();
            }
            return new C0099b().a(this);
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
            return this.f290a == bVar.f290a && this.b == bVar.b && this.c == bVar.c && this.unknownFields.equals(bVar.unknownFields);
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
            int i2 = this.f290a;
            int computeUInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeUInt32Size(1, i2) : 0;
            if (this.b != c.accept.getNumber()) {
                computeUInt32Size += CodedOutputStream.computeEnumSize(2, this.b);
            }
            int i3 = this.c;
            if (i3 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(3, i3);
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
            int hashCode = ((((((((((((((e.f286a.hashCode() + 779) * 37) + 1) * 53) + this.f290a) * 37) + 2) * 53) + this.b) * 37) + 3) * 53) + this.c) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return e.b.ensureFieldAccessorsInitialized(b.class, C0099b.class);
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
            int i = this.f290a;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            if (this.b != c.accept.getNumber()) {
                codedOutputStream.writeEnum(2, this.b);
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

        public b() {
            this.d = (byte) -1;
            this.b = 0;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Message.Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new C0099b(builderParent);
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
                                this.f290a = codedInputStream.readUInt32();
                            } else if (readTag == 16) {
                                this.b = codedInputStream.readEnum();
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

    static {
        Descriptors.FileDescriptor internalBuildGeneratedFileFrom = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001aeastapex_s9900_c9901.proto\"Ñ\u0001\n\u0011u9901_ota_respond\u0012\u0012\n\nrequest_id\u0018\u0001 \u0001(\r\u0012+\n\be_status\u0018\u0002 \u0001(\u000e2\u0019.u9901_ota_respond.status\u0012\u0015\n\rreceive_bytes\u0018\u0003 \u0001(\r\"d\n\u0006status\u0012\n\n\u0006accept\u0010\u0000\u0012\n\n\u0006reject\u0010\u0001\u0012\u0018\n\u0014reject_version_error\u0010\u0002\u0012\u000b\n\u0007proceed\u0010\u0003\u0012\r\n\tcrc_error\u0010\u0004\u0012\f\n\bcomplete\u0010\u0005\"²\u0002\n\u0011d9901_ota_request\u0012'\n\u0006e_type\u0018\u0001 \u0001(\u000e2\u0017.d9901_ota_request.type\u0012\u000f\n\u0007version\u0018\u0002 \u0001(\t\u0012\u0012\n\ntotal_size\u0018\u0003 \u0001(\r\u0012\u0014\n\fcurrent_size\u0018\u0004 \u0001(\r\u0012\u000b\n\u0003crc\u0018\u0005 \u0001(\r\u0012\u0010\n\bres_addr\u0018\u0006 \u0001(\r\u0012\u0012\n\nwait_bytes\u0018\u0007 \u0001(\r\u0012\u0018\n\u0010pop_up_interface\u0018\b \u0001(\r\u0012\u0014\n\fis_test_mode\u0018\t \u0001(\r\"V\n\u0004type\u0012\n\n\u0006apollo\u0010\u0000\u0012\t\n\u0005stm32\u0010\u0001\u0012\u0007\n\u0003res\u0010\u0002\u0012\u0006\n\u0002tp\u0010\u0003\u0012\u0006\n\u0002hr\u0010\u0004\u0012\u0007\n\u0003gps\u0010\u0005\u0012\b\n\u0004agps\u0010\u0006\u0012\u000b\n\u0007user_wf\u0010\u0007\" \u0006\n\u0019d9901_ota_user_ui_request\u00121\n\u0007s_index\u0018\u0001 \u0003(\u000b2 .d9901_ota_user_ui_request.index\u0012\u0014\n\fcurrent_size\u0018\u0002 \u0001(\r\u0012\u000b\n\u0003crc\u0018\u0003 \u0001(\r\u0012\u0012\n\nwait_bytes\u0018\u0004 \u0001(\r\u001aÓ\u0001\n\u0005index\u0012/\n\u0006e_type\u0018\u0001 \u0001(\u000e2\u001f.d9901_ota_user_ui_request.type\u0012\u000f\n\u0007font_id\u0018\u0002 \u0001(\r\u0012\u0013\n\u000bbuilt_in_id\u0018\u0003 \u0001(\r\u0012\t\n\u0001x\u0018\u0004 \u0001(\r\u0012\t\n\u0001y\u0018\u0005 \u0001(\r\u0012\t\n\u0001w\u0018\u0006 \u0001(\r\u0012\t\n\u0001h\u0018\u0007 \u0001(\r\u0012\u0016\n\u000eoffset_address\u0018\b \u0001(\r\u0012\f\n\u0004size\u0018\t \u0001(\r\u0012\u0011\n\tunit_size\u0018\n \u0001(\r\u0012\u000e\n\u0006colour\u0018\u000b \u0001(\r\"Â\u0003\n\u0004type\u0012\n\n\u0006unknow\u0010\u0000\u0012\u000e\n\nbackground\u0010\u0001\u0012\u000f\n\u000bnumber_font\u0010\u0002\u0012\b\n\u0004year\u0010\u0003\u0012\t\n\u0005month\u0010\u0004\u0012\u0007\n\u0003day\u0010\u0005\u0012\b\n\u0004hour\u0010\u0006\u0012\n\n\u0006minute\u0010\u0007\u0012\n\n\u0006second\u0010\b\u0012\r\n\thour_hand\u0010\t\u0012\u000f\n\u000bminute_hand\u0010\n\u0012\u000f\n\u000bsecond_hand\u0010\u000b\u0012\u0012\n\u000ehome_hour_hand\u0010\f\u0012\u0014\n\u0010home_minute_hand\u0010\r\u0012\u0014\n\u0010home_second_hand\u0010\u000e\u0012\t\n\u0005steps\u0010\u000f\u0012\u0015\n\u0011steps_number_font\u0010\u0010\u0012\u000b\n\u0007calorie\u0010\u0011\u0012\u0017\n\u0013calorie_number_font\u0010\u0012\u0012\f\n\bdistance\u0010\u0013\u0012\u0018\n\u0014distance_number_font\u0010\u0014\u0012\f\n\bduration\u0010\u0015\u0012\u0018\n\u0014duration_number_font\u0010\u0016\u0012\u000e\n\nheart_rate\u0010\u0017\u0012\u001a\n\u0016heart_rate_number_font\u0010\u0018\u0012\u000b\n\u0007weather\u0010\u0019\u0012\u000b\n\u0007battery\u0010\u001aB\f\n\nproto2javab\u0006proto3"}, new Descriptors.FileDescriptor[0]);
        g = internalBuildGeneratedFileFrom;
        Descriptors.Descriptor descriptor = internalBuildGeneratedFileFrom.getMessageTypes().get(0);
        f286a = descriptor;
        b = new GeneratedMessageV3.FieldAccessorTable(descriptor, new String[]{"RequestId", "EStatus", "ReceiveBytes"});
        Descriptors.Descriptor descriptor2 = g.getMessageTypes().get(1);
        c = descriptor2;
        d = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"EType", "Version", "TotalSize", "CurrentSize", "Crc", "ResAddr", "WaitBytes", "PopUpInterface", "IsTestMode"});
        Descriptors.Descriptor descriptor3 = g.getMessageTypes().get(2);
        e = descriptor3;
        new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"SIndex", "CurrentSize", "Crc", "WaitBytes"});
        Descriptors.Descriptor descriptor4 = descriptor3.getNestedTypes().get(0);
        f = descriptor4;
        new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"EType", "FontId", "BuiltInId", "X", "Y", ExifInterface.LONGITUDE_WEST, "H", "OffsetAddress", "Size", "UnitSize", "Colour"});
    }
}
