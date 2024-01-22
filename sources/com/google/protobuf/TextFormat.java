package com.google.protobuf;

import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DynamicMessage;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.Message;
import com.google.protobuf.MessageReflection;
import com.google.protobuf.TextFormatParseInfoTree;
import com.google.protobuf.UnknownFieldSet;
import com.jstyle.blesdk1860.constant.BleConst;
import java.io.IOException;
import java.lang.Character;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jose4j.jwk.RsaJsonWebKey;
/* loaded from: classes11.dex */
public final class TextFormat {
    private static final Logger logger = Logger.getLogger(TextFormat.class.getName());
    private static final Parser PARSER = Parser.newBuilder().build();

    /* loaded from: classes11.dex */
    public static class InvalidEscapeSequenceException extends IOException {
        private static final long serialVersionUID = -8164033650142593304L;

        public InvalidEscapeSequenceException(String str) {
            super(str);
        }
    }

    /* loaded from: classes11.dex */
    public static class ParseException extends IOException {
        private static final long serialVersionUID = 3196188060225107702L;
        private final int column;
        private final int line;

        public ParseException(String str) {
            this(-1, -1, str);
        }

        public int getColumn() {
            return this.column;
        }

        public int getLine() {
            return this.line;
        }

        public ParseException(int i, int i2, String str) {
            super(Integer.toString(i) + ":" + i2 + ": " + str);
            this.line = i;
            this.column = i2;
        }
    }

    /* loaded from: classes11.dex */
    public static class Parser {
        private static final int BUFFER_SIZE = 4096;
        private final boolean allowUnknownEnumValues;
        private final boolean allowUnknownExtensions;
        private final boolean allowUnknownFields;
        private TextFormatParseInfoTree.Builder parseInfoTreeBuilder;
        private final SingularOverwritePolicy singularOverwritePolicy;
        private final TypeRegistry typeRegistry;

        /* loaded from: classes11.dex */
        public static class Builder {
            private boolean allowUnknownFields = false;
            private boolean allowUnknownEnumValues = false;
            private boolean allowUnknownExtensions = false;
            private SingularOverwritePolicy singularOverwritePolicy = SingularOverwritePolicy.ALLOW_SINGULAR_OVERWRITES;
            private TextFormatParseInfoTree.Builder parseInfoTreeBuilder = null;
            private TypeRegistry typeRegistry = TypeRegistry.getEmptyTypeRegistry();

            public Parser build() {
                return new Parser(this.typeRegistry, this.allowUnknownFields, this.allowUnknownEnumValues, this.allowUnknownExtensions, this.singularOverwritePolicy, this.parseInfoTreeBuilder, null);
            }

            public Builder setAllowUnknownExtensions(boolean z) {
                this.allowUnknownExtensions = z;
                return this;
            }

            public Builder setAllowUnknownFields(boolean z) {
                this.allowUnknownFields = z;
                return this;
            }

            public Builder setParseInfoTreeBuilder(TextFormatParseInfoTree.Builder builder) {
                this.parseInfoTreeBuilder = builder;
                return this;
            }

            public Builder setSingularOverwritePolicy(SingularOverwritePolicy singularOverwritePolicy) {
                this.singularOverwritePolicy = singularOverwritePolicy;
                return this;
            }

            public Builder setTypeRegistry(TypeRegistry typeRegistry) {
                this.typeRegistry = typeRegistry;
                return this;
            }
        }

        /* loaded from: classes11.dex */
        public enum SingularOverwritePolicy {
            ALLOW_SINGULAR_OVERWRITES,
            FORBID_SINGULAR_OVERWRITES
        }

        /* loaded from: classes11.dex */
        public static final class a {

            /* renamed from: a  reason: collision with root package name */
            public final String f11709a;
            public final EnumC0549a b;

            /* renamed from: com.google.protobuf.TextFormat$Parser$a$a  reason: collision with other inner class name */
            /* loaded from: classes11.dex */
            public enum EnumC0549a {
                FIELD,
                EXTENSION
            }

            public a(String str, EnumC0549a enumC0549a) {
                this.f11709a = str;
                this.b = enumC0549a;
            }
        }

        public /* synthetic */ Parser(TypeRegistry typeRegistry, boolean z, boolean z2, boolean z3, SingularOverwritePolicy singularOverwritePolicy, TextFormatParseInfoTree.Builder builder, a aVar) {
            this(typeRegistry, z, z2, z3, singularOverwritePolicy, builder);
        }

        private void checkUnknownFields(List<a> list) throws ParseException {
            int i;
            boolean z;
            if (list.isEmpty()) {
                return;
            }
            StringBuilder sb = new StringBuilder("Input contains unknown fields and/or extensions:");
            for (a aVar : list) {
                sb.append('\n');
                sb.append(aVar.f11709a);
            }
            if (this.allowUnknownFields) {
                TextFormat.logger.warning(sb.toString());
                return;
            }
            if (this.allowUnknownExtensions) {
                Iterator<a> it = list.iterator();
                i = 0;
                while (true) {
                    if (!it.hasNext()) {
                        z = true;
                        break;
                    } else if (it.next().b == a.EnumC0549a.FIELD) {
                        z = false;
                        break;
                    } else {
                        i++;
                    }
                }
                if (z) {
                    TextFormat.logger.warning(sb.toString());
                    return;
                }
            } else {
                i = 0;
            }
            String[] split = list.get(i).f11709a.split(":");
            throw new ParseException(Integer.parseInt(split[0]), Integer.parseInt(split[1]), sb.toString());
        }

        private void consumeFieldValue(c cVar, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget mergeTarget, Descriptors.FieldDescriptor fieldDescriptor, ExtensionRegistry.ExtensionInfo extensionInfo, TextFormatParseInfoTree.Builder builder, List<a> list) throws ParseException {
            String str;
            Object a2;
            if (this.singularOverwritePolicy == SingularOverwritePolicy.FORBID_SINGULAR_OVERWRITES && !fieldDescriptor.isRepeated()) {
                if (!mergeTarget.hasField(fieldDescriptor)) {
                    if (fieldDescriptor.getContainingOneof() != null && mergeTarget.hasOneof(fieldDescriptor.getContainingOneof())) {
                        Descriptors.OneofDescriptor containingOneof = fieldDescriptor.getContainingOneof();
                        throw cVar.y("Field \"" + fieldDescriptor.getFullName() + "\" is specified along with field \"" + mergeTarget.getOneofFieldDescriptor(containingOneof).getFullName() + "\", another member of oneof \"" + containingOneof.getName() + "\".");
                    }
                } else {
                    throw cVar.y("Non-repeated field \"" + fieldDescriptor.getFullName() + "\" cannot be overwritten.");
                }
            }
            Object obj = null;
            if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                if (cVar.A("<")) {
                    str = ">";
                } else {
                    cVar.c("{");
                    str = "}";
                }
                String str2 = str;
                if (fieldDescriptor.getMessageType().getFullName().equals("google.protobuf.Any") && cVar.A("[")) {
                    MessageReflection.MergeTarget b = mergeTarget.b(fieldDescriptor, DynamicMessage.getDefaultInstance(fieldDescriptor.getMessageType()));
                    mergeAnyFieldValue(cVar, extensionRegistry, b, builder, list, fieldDescriptor.getMessageType());
                    a2 = b.a();
                    cVar.c(str2);
                } else {
                    MessageReflection.MergeTarget b2 = mergeTarget.b(fieldDescriptor, extensionInfo != null ? extensionInfo.defaultInstance : null);
                    while (!cVar.A(str2)) {
                        if (!cVar.b()) {
                            mergeField(cVar, extensionRegistry, b2, builder, list);
                        } else {
                            throw cVar.x("Expected \"" + str2 + "\".");
                        }
                    }
                    a2 = b2.a();
                }
                obj = a2;
            } else {
                switch (a.b[fieldDescriptor.getType().ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                        obj = Integer.valueOf(cVar.j());
                        break;
                    case 4:
                    case 5:
                    case 6:
                        obj = Long.valueOf(cVar.k());
                        break;
                    case 7:
                        obj = Boolean.valueOf(cVar.d());
                        break;
                    case 8:
                        obj = Float.valueOf(cVar.h());
                        break;
                    case 9:
                        obj = Double.valueOf(cVar.g());
                        break;
                    case 10:
                    case 11:
                        obj = Integer.valueOf(cVar.m());
                        break;
                    case 12:
                    case 13:
                        obj = Long.valueOf(cVar.n());
                        break;
                    case 14:
                        obj = cVar.l();
                        break;
                    case 15:
                        obj = cVar.e();
                        break;
                    case 16:
                        Descriptors.EnumDescriptor enumType = fieldDescriptor.getEnumType();
                        if (cVar.v()) {
                            int j = cVar.j();
                            obj = enumType.findValueByNumber(j);
                            if (obj == null) {
                                String str3 = "Enum type \"" + enumType.getFullName() + "\" has no value with number " + j + '.';
                                if (this.allowUnknownEnumValues) {
                                    TextFormat.logger.warning(str3);
                                    return;
                                }
                                throw cVar.y("Enum type \"" + enumType.getFullName() + "\" has no value with number " + j + '.');
                            }
                        } else {
                            String i = cVar.i();
                            obj = enumType.findValueByName(i);
                            if (obj == null) {
                                String str4 = "Enum type \"" + enumType.getFullName() + "\" has no value named \"" + i + "\".";
                                if (this.allowUnknownEnumValues) {
                                    TextFormat.logger.warning(str4);
                                    return;
                                }
                                throw cVar.y(str4);
                            }
                        }
                        break;
                    case 17:
                    case 18:
                        throw new RuntimeException("Can't get here.");
                }
            }
            if (fieldDescriptor.isRepeated()) {
                mergeTarget.addRepeatedField(fieldDescriptor, obj);
            } else {
                mergeTarget.setField(fieldDescriptor, obj);
            }
        }

        private void consumeFieldValues(c cVar, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget mergeTarget, Descriptors.FieldDescriptor fieldDescriptor, ExtensionRegistry.ExtensionInfo extensionInfo, TextFormatParseInfoTree.Builder builder, List<a> list) throws ParseException {
            if (fieldDescriptor.isRepeated() && cVar.A("[")) {
                if (cVar.A("]")) {
                    return;
                }
                while (true) {
                    consumeFieldValue(cVar, extensionRegistry, mergeTarget, fieldDescriptor, extensionInfo, builder, list);
                    if (cVar.A("]")) {
                        return;
                    }
                    cVar.c(Constants.SEPARATOR_COMMA);
                }
            } else {
                consumeFieldValue(cVar, extensionRegistry, mergeTarget, fieldDescriptor, extensionInfo, builder, list);
            }
        }

        private void mergeAnyFieldValue(c cVar, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget mergeTarget, TextFormatParseInfoTree.Builder builder, List<a> list, Descriptors.Descriptor descriptor) throws ParseException {
            String str;
            StringBuilder sb = new StringBuilder();
            while (true) {
                sb.append(cVar.i());
                if (cVar.A("]")) {
                    cVar.A(":");
                    if (cVar.A("<")) {
                        str = ">";
                    } else {
                        cVar.c("{");
                        str = "}";
                    }
                    String str2 = str;
                    String sb2 = sb.toString();
                    try {
                        Descriptors.Descriptor descriptorForTypeUrl = this.typeRegistry.getDescriptorForTypeUrl(sb2);
                        if (descriptorForTypeUrl != null) {
                            DynamicMessage.Builder newBuilderForType = DynamicMessage.getDefaultInstance(descriptorForTypeUrl).newBuilderForType();
                            MessageReflection.b bVar = new MessageReflection.b(newBuilderForType);
                            while (!cVar.A(str2)) {
                                mergeField(cVar, extensionRegistry, bVar, builder, list);
                            }
                            mergeTarget.setField(descriptor.findFieldByName("type_url"), sb.toString());
                            mergeTarget.setField(descriptor.findFieldByName("value"), newBuilderForType.build().toByteString());
                            return;
                        }
                        throw cVar.x("Unable to parse Any of type: " + sb2 + ". Please make sure that the TypeRegistry contains the descriptors for the given types.");
                    } catch (InvalidProtocolBufferException unused) {
                        throw cVar.x("Invalid valid type URL. Found: " + sb2);
                    }
                } else if (cVar.A(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                    sb.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
                } else if (cVar.A(".")) {
                    sb.append(".");
                } else {
                    throw cVar.y("Expected a valid type URL.");
                }
            }
        }

        private void mergeField(c cVar, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget mergeTarget, List<a> list) throws ParseException {
            mergeField(cVar, extensionRegistry, mergeTarget, this.parseInfoTreeBuilder, list);
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        private static void skipField(c cVar) throws ParseException {
            if (cVar.A("[")) {
                do {
                    cVar.i();
                } while (cVar.A("."));
                cVar.c("]");
            } else {
                cVar.i();
            }
            if (cVar.A(":") && !cVar.u("<") && !cVar.u("{")) {
                skipFieldValue(cVar);
            } else {
                skipFieldMessage(cVar);
            }
            if (cVar.A(";")) {
                return;
            }
            cVar.A(Constants.SEPARATOR_COMMA);
        }

        private static void skipFieldMessage(c cVar) throws ParseException {
            String str;
            if (cVar.A("<")) {
                str = ">";
            } else {
                cVar.c("{");
                str = "}";
            }
            while (!cVar.u(">") && !cVar.u("}")) {
                skipField(cVar);
            }
            cVar.c(str);
        }

        private static void skipFieldValue(c cVar) throws ParseException {
            if (cVar.F()) {
                do {
                } while (cVar.F());
            } else if (cVar.D() || cVar.E() || cVar.G() || cVar.B() || cVar.C()) {
            } else {
                throw cVar.x("Invalid field value: " + cVar.c);
            }
        }

        private static StringBuilder toStringBuilder(Readable readable) throws IOException {
            StringBuilder sb = new StringBuilder();
            CharBuffer allocate = CharBuffer.allocate(4096);
            while (true) {
                int read = readable.read(allocate);
                if (read == -1) {
                    return sb;
                }
                allocate.flip();
                sb.append((CharSequence) allocate, 0, read);
            }
        }

        public void merge(Readable readable, Message.Builder builder) throws IOException {
            merge(readable, ExtensionRegistry.getEmptyRegistry(), builder);
        }

        private Parser(TypeRegistry typeRegistry, boolean z, boolean z2, boolean z3, SingularOverwritePolicy singularOverwritePolicy, TextFormatParseInfoTree.Builder builder) {
            this.typeRegistry = typeRegistry;
            this.allowUnknownFields = z;
            this.allowUnknownEnumValues = z2;
            this.allowUnknownExtensions = z3;
            this.singularOverwritePolicy = singularOverwritePolicy;
            this.parseInfoTreeBuilder = builder;
        }

        private void mergeField(c cVar, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget mergeTarget, TextFormatParseInfoTree.Builder builder, List<a> list) throws ParseException {
            Descriptors.FieldDescriptor findFieldByName;
            ExtensionRegistry.ExtensionInfo extensionInfo;
            int q = cVar.q();
            int p = cVar.p();
            Descriptors.Descriptor descriptorForType = mergeTarget.getDescriptorForType();
            if ("google.protobuf.Any".equals(descriptorForType.getFullName()) && cVar.A("[")) {
                mergeAnyFieldValue(cVar, extensionRegistry, mergeTarget, builder, list, descriptorForType);
                return;
            }
            Descriptors.FieldDescriptor fieldDescriptor = null;
            if (cVar.A("[")) {
                StringBuilder sb = new StringBuilder(cVar.i());
                while (cVar.A(".")) {
                    sb.append('.');
                    sb.append(cVar.i());
                }
                ExtensionRegistry.ExtensionInfo g = mergeTarget.g(extensionRegistry, sb.toString());
                if (g == null) {
                    list.add(new a((cVar.s() + 1) + ":" + (cVar.r() + 1) + ":\t" + descriptorForType.getFullName() + ".[" + ((Object) sb) + "]", a.EnumC0549a.EXTENSION));
                } else if (g.descriptor.getContainingType() == descriptorForType) {
                    fieldDescriptor = g.descriptor;
                } else {
                    throw cVar.y("Extension \"" + ((Object) sb) + "\" does not extend message type \"" + descriptorForType.getFullName() + "\".");
                }
                cVar.c("]");
                extensionInfo = g;
                findFieldByName = fieldDescriptor;
            } else {
                String i = cVar.i();
                findFieldByName = descriptorForType.findFieldByName(i);
                if (findFieldByName == null && (findFieldByName = descriptorForType.findFieldByName(i.toLowerCase(Locale.US))) != null && findFieldByName.getType() != Descriptors.FieldDescriptor.Type.GROUP) {
                    findFieldByName = null;
                }
                if (findFieldByName != null && findFieldByName.getType() == Descriptors.FieldDescriptor.Type.GROUP && !findFieldByName.getMessageType().getName().equals(i)) {
                    findFieldByName = null;
                }
                if (findFieldByName == null) {
                    list.add(new a((cVar.s() + 1) + ":" + (cVar.r() + 1) + ":\t" + descriptorForType.getFullName() + "." + i, a.EnumC0549a.FIELD));
                }
                extensionInfo = null;
            }
            if (findFieldByName == null) {
                if (cVar.A(":") && !cVar.u("{") && !cVar.u("<")) {
                    skipFieldValue(cVar);
                    return;
                } else {
                    skipFieldMessage(cVar);
                    return;
                }
            }
            if (findFieldByName.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                cVar.A(":");
                if (builder != null) {
                    consumeFieldValues(cVar, extensionRegistry, mergeTarget, findFieldByName, extensionInfo, builder.getBuilderForSubMessageField(findFieldByName), list);
                } else {
                    consumeFieldValues(cVar, extensionRegistry, mergeTarget, findFieldByName, extensionInfo, builder, list);
                }
            } else {
                cVar.c(":");
                consumeFieldValues(cVar, extensionRegistry, mergeTarget, findFieldByName, extensionInfo, builder, list);
            }
            if (builder != null) {
                builder.setLocation(findFieldByName, TextFormatParseLocation.create(q, p));
            }
            if (cVar.A(";")) {
                return;
            }
            cVar.A(Constants.SEPARATOR_COMMA);
        }

        public void merge(CharSequence charSequence, Message.Builder builder) throws ParseException {
            merge(charSequence, ExtensionRegistry.getEmptyRegistry(), builder);
        }

        public void merge(Readable readable, ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {
            merge(toStringBuilder(readable), extensionRegistry, builder);
        }

        public void merge(CharSequence charSequence, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
            c cVar = new c(charSequence, null);
            MessageReflection.b bVar = new MessageReflection.b(builder);
            ArrayList arrayList = new ArrayList();
            while (!cVar.b()) {
                mergeField(cVar, extensionRegistry, bVar, arrayList);
            }
            checkUnknownFields(arrayList);
        }
    }

    /* loaded from: classes11.dex */
    public static final class Printer {
        private static final Printer DEFAULT = new Printer(true, TypeRegistry.getEmptyTypeRegistry());
        private final boolean escapeNonAscii;
        private final TypeRegistry typeRegistry;

        /* loaded from: classes11.dex */
        public static class a implements Comparable<a> {
            public Object h;
            public MapEntry i;
            public final Descriptors.FieldDescriptor.JavaType j;

            public a(Object obj, Descriptors.FieldDescriptor fieldDescriptor) {
                if (obj instanceof MapEntry) {
                    this.i = (MapEntry) obj;
                } else {
                    this.h = obj;
                }
                this.j = b(fieldDescriptor);
            }

            public static Descriptors.FieldDescriptor.JavaType b(Descriptors.FieldDescriptor fieldDescriptor) {
                return fieldDescriptor.getMessageType().getFields().get(0).getJavaType();
            }

            @Override // java.lang.Comparable
            /* renamed from: a */
            public int compareTo(a aVar) {
                if (d() == null || aVar.d() == null) {
                    TextFormat.logger.info("Invalid key for map field.");
                    return -1;
                }
                int i = a.f11710a[this.j.ordinal()];
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                return 0;
                            }
                            String str = (String) d();
                            String str2 = (String) aVar.d();
                            if (str == null && str2 == null) {
                                return 0;
                            }
                            if (str != null || str2 == null) {
                                if (str == null || str2 != null) {
                                    return str.compareTo(str2);
                                }
                                return 1;
                            }
                            return -1;
                        }
                        return Integer.compare(((Integer) d()).intValue(), ((Integer) aVar.d()).intValue());
                    }
                    return Long.compare(((Long) d()).longValue(), ((Long) aVar.d()).longValue());
                }
                return Boolean.compare(((Boolean) d()).booleanValue(), ((Boolean) aVar.d()).booleanValue());
            }

            public Object c() {
                MapEntry mapEntry = this.i;
                return mapEntry != null ? mapEntry : this.h;
            }

            public Object d() {
                MapEntry mapEntry = this.i;
                if (mapEntry != null) {
                    return mapEntry.getKey();
                }
                return null;
            }
        }

        private Printer(boolean z, TypeRegistry typeRegistry) {
            this.escapeNonAscii = z;
            this.typeRegistry = typeRegistry;
        }

        private boolean printAny(MessageOrBuilder messageOrBuilder, b bVar) throws IOException {
            Descriptors.Descriptor descriptorForType = messageOrBuilder.getDescriptorForType();
            Descriptors.FieldDescriptor findFieldByNumber = descriptorForType.findFieldByNumber(1);
            Descriptors.FieldDescriptor findFieldByNumber2 = descriptorForType.findFieldByNumber(2);
            if (findFieldByNumber != null && findFieldByNumber.getType() == Descriptors.FieldDescriptor.Type.STRING && findFieldByNumber2 != null && findFieldByNumber2.getType() == Descriptors.FieldDescriptor.Type.BYTES) {
                String str = (String) messageOrBuilder.getField(findFieldByNumber);
                if (str.isEmpty()) {
                    return false;
                }
                Object field = messageOrBuilder.getField(findFieldByNumber2);
                try {
                    Descriptors.Descriptor descriptorForTypeUrl = this.typeRegistry.getDescriptorForTypeUrl(str);
                    if (descriptorForTypeUrl == null) {
                        return false;
                    }
                    DynamicMessage.Builder newBuilderForType = DynamicMessage.getDefaultInstance(descriptorForTypeUrl).newBuilderForType();
                    newBuilderForType.mergeFrom((ByteString) field);
                    bVar.d("[");
                    bVar.d(str);
                    bVar.d("] {");
                    bVar.a();
                    bVar.b();
                    print(newBuilderForType, bVar);
                    bVar.c();
                    bVar.d("}");
                    bVar.a();
                    return true;
                } catch (InvalidProtocolBufferException unused) {
                }
            }
            return false;
        }

        private void printMessage(MessageOrBuilder messageOrBuilder, b bVar) throws IOException {
            for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : messageOrBuilder.getAllFields().entrySet()) {
                printField(entry.getKey(), entry.getValue(), bVar);
            }
            printUnknownFields(messageOrBuilder.getUnknownFields(), bVar);
        }

        private void printSingleField(Descriptors.FieldDescriptor fieldDescriptor, Object obj, b bVar) throws IOException {
            if (fieldDescriptor.isExtension()) {
                bVar.d("[");
                if (fieldDescriptor.getContainingType().getOptions().getMessageSetWireFormat() && fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && fieldDescriptor.isOptional() && fieldDescriptor.getExtensionScope() == fieldDescriptor.getMessageType()) {
                    bVar.d(fieldDescriptor.getMessageType().getFullName());
                } else {
                    bVar.d(fieldDescriptor.getFullName());
                }
                bVar.d("]");
            } else if (fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.GROUP) {
                bVar.d(fieldDescriptor.getMessageType().getName());
            } else {
                bVar.d(fieldDescriptor.getName());
            }
            Descriptors.FieldDescriptor.JavaType javaType = fieldDescriptor.getJavaType();
            Descriptors.FieldDescriptor.JavaType javaType2 = Descriptors.FieldDescriptor.JavaType.MESSAGE;
            if (javaType == javaType2) {
                bVar.d(" {");
                bVar.a();
                bVar.b();
            } else {
                bVar.d(": ");
            }
            printFieldValue(fieldDescriptor, obj, bVar);
            if (fieldDescriptor.getJavaType() == javaType2) {
                bVar.c();
                bVar.d("}");
            }
            bVar.a();
        }

        private static void printUnknownField(int i, int i2, List<?> list, b bVar) throws IOException {
            for (Object obj : list) {
                bVar.d(String.valueOf(i));
                bVar.d(": ");
                printUnknownFieldValue(i2, obj, bVar);
                bVar.a();
            }
        }

        private static void printUnknownFieldValue(int i, Object obj, b bVar) throws IOException {
            int tagWireType = WireFormat.getTagWireType(i);
            if (tagWireType == 0) {
                bVar.d(TextFormat.unsignedToString(((Long) obj).longValue()));
            } else if (tagWireType == 1) {
                bVar.d(String.format(null, "0x%016x", (Long) obj));
            } else if (tagWireType != 2) {
                if (tagWireType == 3) {
                    printUnknownFields((UnknownFieldSet) obj, bVar);
                } else if (tagWireType == 5) {
                    bVar.d(String.format(null, "0x%08x", (Integer) obj));
                } else {
                    throw new IllegalArgumentException("Bad tag: " + i);
                }
            } else {
                try {
                    UnknownFieldSet parseFrom = UnknownFieldSet.parseFrom((ByteString) obj);
                    bVar.d("{");
                    bVar.a();
                    bVar.b();
                    printUnknownFields(parseFrom, bVar);
                    bVar.c();
                    bVar.d("}");
                } catch (InvalidProtocolBufferException unused) {
                    bVar.d("\"");
                    bVar.d(TextFormat.escapeBytes((ByteString) obj));
                    bVar.d("\"");
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void printUnknownFields(UnknownFieldSet unknownFieldSet, b bVar) throws IOException {
            for (Map.Entry<Integer, UnknownFieldSet.Field> entry : unknownFieldSet.asMap().entrySet()) {
                int intValue = entry.getKey().intValue();
                UnknownFieldSet.Field value = entry.getValue();
                printUnknownField(intValue, 0, value.getVarintList(), bVar);
                printUnknownField(intValue, 5, value.getFixed32List(), bVar);
                printUnknownField(intValue, 1, value.getFixed64List(), bVar);
                printUnknownField(intValue, 2, value.getLengthDelimitedList(), bVar);
                for (UnknownFieldSet unknownFieldSet2 : value.getGroupList()) {
                    bVar.d(entry.getKey().toString());
                    bVar.d(" {");
                    bVar.a();
                    bVar.b();
                    printUnknownFields(unknownFieldSet2, bVar);
                    bVar.c();
                    bVar.d("}");
                    bVar.a();
                }
            }
        }

        public Printer escapingNonAscii(boolean z) {
            return new Printer(z, this.typeRegistry);
        }

        public void print(MessageOrBuilder messageOrBuilder, Appendable appendable) throws IOException {
            print(messageOrBuilder, TextFormat.multiLineOutput(appendable));
        }

        public void printField(Descriptors.FieldDescriptor fieldDescriptor, Object obj, Appendable appendable) throws IOException {
            printField(fieldDescriptor, obj, TextFormat.multiLineOutput(appendable));
        }

        public String printFieldToString(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            try {
                StringBuilder sb = new StringBuilder();
                printField(fieldDescriptor, obj, sb);
                return sb.toString();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public void printFieldValue(Descriptors.FieldDescriptor fieldDescriptor, Object obj, Appendable appendable) throws IOException {
            printFieldValue(fieldDescriptor, obj, TextFormat.multiLineOutput(appendable));
        }

        public String printToString(MessageOrBuilder messageOrBuilder) {
            try {
                StringBuilder sb = new StringBuilder();
                print(messageOrBuilder, sb);
                return sb.toString();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public String shortDebugString(MessageOrBuilder messageOrBuilder) {
            try {
                StringBuilder sb = new StringBuilder();
                print(messageOrBuilder, TextFormat.singleLineOutput(sb));
                return sb.toString();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public Printer usingTypeRegistry(TypeRegistry typeRegistry) {
            if (this.typeRegistry == TypeRegistry.getEmptyTypeRegistry()) {
                return new Printer(this.escapeNonAscii, typeRegistry);
            }
            throw new IllegalArgumentException("Only one typeRegistry is allowed.");
        }

        private void printField(Descriptors.FieldDescriptor fieldDescriptor, Object obj, b bVar) throws IOException {
            if (fieldDescriptor.isMapField()) {
                ArrayList<a> arrayList = new ArrayList();
                for (Object obj2 : (List) obj) {
                    arrayList.add(new a(obj2, fieldDescriptor));
                }
                Collections.sort(arrayList);
                for (a aVar : arrayList) {
                    printSingleField(fieldDescriptor, aVar.c(), bVar);
                }
            } else if (fieldDescriptor.isRepeated()) {
                for (Object obj3 : (List) obj) {
                    printSingleField(fieldDescriptor, obj3, bVar);
                }
            } else {
                printSingleField(fieldDescriptor, obj, bVar);
            }
        }

        private void printFieldValue(Descriptors.FieldDescriptor fieldDescriptor, Object obj, b bVar) throws IOException {
            String replace;
            switch (a.b[fieldDescriptor.getType().ordinal()]) {
                case 1:
                case 2:
                case 3:
                    bVar.d(((Integer) obj).toString());
                    return;
                case 4:
                case 5:
                case 6:
                    bVar.d(((Long) obj).toString());
                    return;
                case 7:
                    bVar.d(((Boolean) obj).toString());
                    return;
                case 8:
                    bVar.d(((Float) obj).toString());
                    return;
                case 9:
                    bVar.d(((Double) obj).toString());
                    return;
                case 10:
                case 11:
                    bVar.d(TextFormat.unsignedToString(((Integer) obj).intValue()));
                    return;
                case 12:
                case 13:
                    bVar.d(TextFormat.unsignedToString(((Long) obj).longValue()));
                    return;
                case 14:
                    bVar.d("\"");
                    if (this.escapeNonAscii) {
                        replace = w0.e((String) obj);
                    } else {
                        replace = TextFormat.escapeDoubleQuotesAndBackslashes((String) obj).replace("\n", "\\n");
                    }
                    bVar.d(replace);
                    bVar.d("\"");
                    return;
                case 15:
                    bVar.d("\"");
                    if (obj instanceof ByteString) {
                        bVar.d(TextFormat.escapeBytes((ByteString) obj));
                    } else {
                        bVar.d(TextFormat.escapeBytes((byte[]) obj));
                    }
                    bVar.d("\"");
                    return;
                case 16:
                    bVar.d(((Descriptors.EnumValueDescriptor) obj).getName());
                    return;
                case 17:
                case 18:
                    print((Message) obj, bVar);
                    return;
                default:
                    return;
            }
        }

        public void print(UnknownFieldSet unknownFieldSet, Appendable appendable) throws IOException {
            printUnknownFields(unknownFieldSet, TextFormat.multiLineOutput(appendable));
        }

        private void print(MessageOrBuilder messageOrBuilder, b bVar) throws IOException {
            if (messageOrBuilder.getDescriptorForType().getFullName().equals("google.protobuf.Any") && printAny(messageOrBuilder, bVar)) {
                return;
            }
            printMessage(messageOrBuilder, bVar);
        }

        public String printToString(UnknownFieldSet unknownFieldSet) {
            try {
                StringBuilder sb = new StringBuilder();
                print(unknownFieldSet, sb);
                return sb.toString();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public String shortDebugString(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            try {
                StringBuilder sb = new StringBuilder();
                printField(fieldDescriptor, obj, TextFormat.singleLineOutput(sb));
                return sb.toString();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public String shortDebugString(UnknownFieldSet unknownFieldSet) {
            try {
                StringBuilder sb = new StringBuilder();
                printUnknownFields(unknownFieldSet, TextFormat.singleLineOutput(sb));
                return sb.toString();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /* loaded from: classes11.dex */
    public static class UnknownFieldParseException extends ParseException {
        private final String unknownField;

        public UnknownFieldParseException(String str) {
            this(-1, -1, "", str);
        }

        public String getUnknownField() {
            return this.unknownField;
        }

        public UnknownFieldParseException(int i, int i2, String str, String str2) {
            super(i, i2, str2);
            this.unknownField = str;
        }
    }

    /* loaded from: classes11.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f11710a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[Descriptors.FieldDescriptor.Type.values().length];
            b = iArr;
            try {
                iArr[Descriptors.FieldDescriptor.Type.INT32.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[Descriptors.FieldDescriptor.Type.SINT32.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[Descriptors.FieldDescriptor.Type.SFIXED32.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[Descriptors.FieldDescriptor.Type.INT64.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[Descriptors.FieldDescriptor.Type.SINT64.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[Descriptors.FieldDescriptor.Type.SFIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[Descriptors.FieldDescriptor.Type.BOOL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                b[Descriptors.FieldDescriptor.Type.FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                b[Descriptors.FieldDescriptor.Type.DOUBLE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                b[Descriptors.FieldDescriptor.Type.UINT32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                b[Descriptors.FieldDescriptor.Type.FIXED32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                b[Descriptors.FieldDescriptor.Type.UINT64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                b[Descriptors.FieldDescriptor.Type.FIXED64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                b[Descriptors.FieldDescriptor.Type.STRING.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                b[Descriptors.FieldDescriptor.Type.BYTES.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                b[Descriptors.FieldDescriptor.Type.ENUM.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                b[Descriptors.FieldDescriptor.Type.MESSAGE.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                b[Descriptors.FieldDescriptor.Type.GROUP.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            int[] iArr2 = new int[Descriptors.FieldDescriptor.JavaType.values().length];
            f11710a = iArr2;
            try {
                iArr2[Descriptors.FieldDescriptor.JavaType.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f11710a[Descriptors.FieldDescriptor.JavaType.LONG.ordinal()] = 2;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f11710a[Descriptors.FieldDescriptor.JavaType.INT.ordinal()] = 3;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f11710a[Descriptors.FieldDescriptor.JavaType.STRING.ordinal()] = 4;
            } catch (NoSuchFieldError unused22) {
            }
        }
    }

    /* loaded from: classes11.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public final Appendable f11711a;
        public final StringBuilder b;
        public final boolean c;
        public boolean d;

        public /* synthetic */ b(Appendable appendable, boolean z, a aVar) {
            this(appendable, z);
        }

        public void a() throws IOException {
            if (!this.c) {
                this.f11711a.append("\n");
            }
            this.d = true;
        }

        public void b() {
            this.b.append("  ");
        }

        public void c() {
            int length = this.b.length();
            if (length != 0) {
                this.b.setLength(length - 2);
                return;
            }
            throw new IllegalArgumentException(" Outdent() without matching Indent().");
        }

        public void d(CharSequence charSequence) throws IOException {
            if (this.d) {
                this.d = false;
                this.f11711a.append(this.c ? HexStringBuilder.DEFAULT_SEPARATOR : this.b);
            }
            this.f11711a.append(charSequence);
        }

        public b(Appendable appendable, boolean z) {
            this.b = new StringBuilder();
            this.d = false;
            this.f11711a = appendable;
            this.c = z;
        }
    }

    /* loaded from: classes11.dex */
    public static final class c {
        public static final Pattern i = Pattern.compile("(\\s|(#.*$))++", 8);
        public static final Pattern j = Pattern.compile("[a-zA-Z_][0-9a-zA-Z_+-]*+|[.]?[0-9+-][0-9a-zA-Z_.+-]*+|\"([^\"\n\\\\]|\\\\.)*+(\"|\\\\?$)|'([^'\n\\\\]|\\\\.)*+('|\\\\?$)", 8);
        public static final Pattern k = Pattern.compile("-?inf(inity)?", 2);
        public static final Pattern l = Pattern.compile("-?inf(inity)?f?", 2);
        public static final Pattern m = Pattern.compile("nanf?", 2);

        /* renamed from: a  reason: collision with root package name */
        public final CharSequence f11712a;
        public final Matcher b;
        public String c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;

        public /* synthetic */ c(CharSequence charSequence, a aVar) {
            this(charSequence);
        }

        public boolean A(String str) {
            if (this.c.equals(str)) {
                w();
                return true;
            }
            return false;
        }

        public boolean B() {
            try {
                g();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public boolean C() {
            try {
                h();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public boolean D() {
            try {
                i();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public boolean E() {
            try {
                k();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public boolean F() {
            try {
                l();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public boolean G() {
            try {
                n();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public boolean b() {
            return this.c.length() == 0;
        }

        public void c(String str) throws ParseException {
            if (A(str)) {
                return;
            }
            throw x("Expected \"" + str + "\".");
        }

        public boolean d() throws ParseException {
            if (!this.c.equals("true") && !this.c.equals("True") && !this.c.equals(RsaJsonWebKey.FACTOR_CRT_COEFFICIENT) && !this.c.equals("1")) {
                if (!this.c.equals("false") && !this.c.equals("False") && !this.c.equals("f") && !this.c.equals(BleConst.GetDeviceTime)) {
                    throw x("Expected \"true\" or \"false\". Found \"" + this.c + "\".");
                }
                w();
                return false;
            }
            w();
            return true;
        }

        public ByteString e() throws ParseException {
            ArrayList arrayList = new ArrayList();
            f(arrayList);
            while (true) {
                if (!this.c.startsWith("'") && !this.c.startsWith("\"")) {
                    return ByteString.copyFrom(arrayList);
                }
                f(arrayList);
            }
        }

        public final void f(List<ByteString> list) throws ParseException {
            char charAt = this.c.length() > 0 ? this.c.charAt(0) : (char) 0;
            if (charAt != '\"' && charAt != '\'') {
                throw x("Expected string.");
            }
            if (this.c.length() >= 2) {
                String str = this.c;
                if (str.charAt(str.length() - 1) == charAt) {
                    try {
                        String str2 = this.c;
                        ByteString unescapeBytes = TextFormat.unescapeBytes(str2.substring(1, str2.length() - 1));
                        w();
                        list.add(unescapeBytes);
                        return;
                    } catch (InvalidEscapeSequenceException e) {
                        throw x(e.getMessage());
                    }
                }
            }
            throw x("String missing ending quote.");
        }

        public double g() throws ParseException {
            if (k.matcher(this.c).matches()) {
                boolean startsWith = this.c.startsWith("-");
                w();
                return startsWith ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
            } else if (this.c.equalsIgnoreCase("nan")) {
                w();
                return Double.NaN;
            } else {
                try {
                    double parseDouble = Double.parseDouble(this.c);
                    w();
                    return parseDouble;
                } catch (NumberFormatException e) {
                    throw o(e);
                }
            }
        }

        public float h() throws ParseException {
            if (l.matcher(this.c).matches()) {
                boolean startsWith = this.c.startsWith("-");
                w();
                return startsWith ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
            } else if (m.matcher(this.c).matches()) {
                w();
                return Float.NaN;
            } else {
                try {
                    float parseFloat = Float.parseFloat(this.c);
                    w();
                    return parseFloat;
                } catch (NumberFormatException e) {
                    throw o(e);
                }
            }
        }

        public String i() throws ParseException {
            for (int i2 = 0; i2 < this.c.length(); i2++) {
                char charAt = this.c.charAt(i2);
                if (('a' > charAt || charAt > 'z') && (('A' > charAt || charAt > 'Z') && !(('0' <= charAt && charAt <= '9') || charAt == '_' || charAt == '.'))) {
                    throw x("Expected identifier. Found '" + this.c + "'");
                }
            }
            String str = this.c;
            w();
            return str;
        }

        public int j() throws ParseException {
            try {
                int parseInt32 = TextFormat.parseInt32(this.c);
                w();
                return parseInt32;
            } catch (NumberFormatException e) {
                throw t(e);
            }
        }

        public long k() throws ParseException {
            try {
                long parseInt64 = TextFormat.parseInt64(this.c);
                w();
                return parseInt64;
            } catch (NumberFormatException e) {
                throw t(e);
            }
        }

        public String l() throws ParseException {
            return e().toStringUtf8();
        }

        public int m() throws ParseException {
            try {
                int parseUInt32 = TextFormat.parseUInt32(this.c);
                w();
                return parseUInt32;
            } catch (NumberFormatException e) {
                throw t(e);
            }
        }

        public long n() throws ParseException {
            try {
                long parseUInt64 = TextFormat.parseUInt64(this.c);
                w();
                return parseUInt64;
            } catch (NumberFormatException e) {
                throw t(e);
            }
        }

        public final ParseException o(NumberFormatException numberFormatException) {
            return x("Couldn't parse number: " + numberFormatException.getMessage());
        }

        public int p() {
            return this.f;
        }

        public int q() {
            return this.e;
        }

        public int r() {
            return this.h;
        }

        public int s() {
            return this.g;
        }

        public final ParseException t(NumberFormatException numberFormatException) {
            return x("Couldn't parse integer: " + numberFormatException.getMessage());
        }

        public boolean u(String str) {
            return this.c.equals(str);
        }

        public boolean v() {
            if (this.c.length() == 0) {
                return false;
            }
            char charAt = this.c.charAt(0);
            return ('0' <= charAt && charAt <= '9') || charAt == '-' || charAt == '+';
        }

        public void w() {
            this.g = this.e;
            this.h = this.f;
            while (this.d < this.b.regionStart()) {
                if (this.f11712a.charAt(this.d) == '\n') {
                    this.e++;
                    this.f = 0;
                } else {
                    this.f++;
                }
                this.d++;
            }
            if (this.b.regionStart() == this.b.regionEnd()) {
                this.c = "";
                return;
            }
            this.b.usePattern(j);
            if (this.b.lookingAt()) {
                this.c = this.b.group();
                Matcher matcher = this.b;
                matcher.region(matcher.end(), this.b.regionEnd());
            } else {
                this.c = String.valueOf(this.f11712a.charAt(this.d));
                Matcher matcher2 = this.b;
                matcher2.region(this.d + 1, matcher2.regionEnd());
            }
            z();
        }

        public ParseException x(String str) {
            return new ParseException(this.e + 1, this.f + 1, str);
        }

        public ParseException y(String str) {
            return new ParseException(this.g + 1, this.h + 1, str);
        }

        public final void z() {
            this.b.usePattern(i);
            if (this.b.lookingAt()) {
                Matcher matcher = this.b;
                matcher.region(matcher.end(), this.b.regionEnd());
            }
        }

        public c(CharSequence charSequence) {
            this.d = 0;
            this.e = 0;
            this.f = 0;
            this.g = 0;
            this.h = 0;
            this.f11712a = charSequence;
            this.b = i.matcher(charSequence);
            z();
            w();
        }
    }

    private TextFormat() {
    }

    private static int digitValue(byte b2) {
        if (48 > b2 || b2 > 57) {
            return ((97 > b2 || b2 > 122) ? b2 - 65 : b2 - 97) + 10;
        }
        return b2 - 48;
    }

    public static String escapeBytes(ByteString byteString) {
        return w0.a(byteString);
    }

    public static String escapeDoubleQuotesAndBackslashes(String str) {
        return w0.d(str);
    }

    public static String escapeText(String str) {
        return escapeBytes(ByteString.copyFromUtf8(str));
    }

    public static Parser getParser() {
        return PARSER;
    }

    private static boolean isHex(byte b2) {
        return (48 <= b2 && b2 <= 57) || (97 <= b2 && b2 <= 102) || (65 <= b2 && b2 <= 70);
    }

    private static boolean isOctal(byte b2) {
        return 48 <= b2 && b2 <= 55;
    }

    public static void merge(Readable readable, Message.Builder builder) throws IOException {
        PARSER.merge(readable, builder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static b multiLineOutput(Appendable appendable) {
        return new b(appendable, false, null);
    }

    public static <T extends Message> T parse(CharSequence charSequence, Class<T> cls) throws ParseException {
        Message.Builder newBuilderForType = ((Message) Internal.getDefaultInstance(cls)).newBuilderForType();
        merge(charSequence, newBuilderForType);
        return (T) newBuilderForType.build();
    }

    public static int parseInt32(String str) throws NumberFormatException {
        return (int) parseInteger(str, true, false);
    }

    public static long parseInt64(String str) throws NumberFormatException {
        return parseInteger(str, true, true);
    }

    private static long parseInteger(String str, boolean z, boolean z2) throws NumberFormatException {
        int i = 0;
        boolean z3 = true;
        if (!str.startsWith("-", 0)) {
            z3 = false;
        } else if (!z) {
            throw new NumberFormatException("Number must be positive: " + str);
        } else {
            i = 1;
        }
        int i2 = 10;
        if (str.startsWith(HexStringBuilder.DEFAULT_PREFIX, i)) {
            i += 2;
            i2 = 16;
        } else if (str.startsWith(BleConst.GetDeviceTime, i)) {
            i2 = 8;
        }
        String substring = str.substring(i);
        if (substring.length() < 16) {
            long parseLong = Long.parseLong(substring, i2);
            if (z3) {
                parseLong = -parseLong;
            }
            if (z2) {
                return parseLong;
            }
            if (z) {
                if (parseLong > 2147483647L || parseLong < -2147483648L) {
                    throw new NumberFormatException("Number out of range for 32-bit signed integer: " + str);
                }
                return parseLong;
            } else if (parseLong >= 4294967296L || parseLong < 0) {
                throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + str);
            } else {
                return parseLong;
            }
        }
        BigInteger bigInteger = new BigInteger(substring, i2);
        if (z3) {
            bigInteger = bigInteger.negate();
        }
        if (z2) {
            if (z) {
                if (bigInteger.bitLength() > 63) {
                    throw new NumberFormatException("Number out of range for 64-bit signed integer: " + str);
                }
            } else if (bigInteger.bitLength() > 64) {
                throw new NumberFormatException("Number out of range for 64-bit unsigned integer: " + str);
            }
        } else if (z) {
            if (bigInteger.bitLength() > 31) {
                throw new NumberFormatException("Number out of range for 32-bit signed integer: " + str);
            }
        } else if (bigInteger.bitLength() > 32) {
            throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + str);
        }
        return bigInteger.longValue();
    }

    public static int parseUInt32(String str) throws NumberFormatException {
        return (int) parseInteger(str, false, false);
    }

    public static long parseUInt64(String str) throws NumberFormatException {
        return parseInteger(str, false, true);
    }

    @Deprecated
    public static void print(MessageOrBuilder messageOrBuilder, Appendable appendable) throws IOException {
        printer().print(messageOrBuilder, appendable);
    }

    @Deprecated
    public static void printField(Descriptors.FieldDescriptor fieldDescriptor, Object obj, Appendable appendable) throws IOException {
        printer().printField(fieldDescriptor, obj, appendable);
    }

    @Deprecated
    public static String printFieldToString(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
        return printer().printFieldToString(fieldDescriptor, obj);
    }

    @Deprecated
    public static void printFieldValue(Descriptors.FieldDescriptor fieldDescriptor, Object obj, Appendable appendable) throws IOException {
        printer().printFieldValue(fieldDescriptor, obj, appendable);
    }

    @Deprecated
    public static String printToString(MessageOrBuilder messageOrBuilder) {
        return printer().printToString(messageOrBuilder);
    }

    @Deprecated
    public static String printToUnicodeString(MessageOrBuilder messageOrBuilder) {
        return printer().escapingNonAscii(false).printToString(messageOrBuilder);
    }

    @Deprecated
    public static void printUnicode(MessageOrBuilder messageOrBuilder, Appendable appendable) throws IOException {
        printer().escapingNonAscii(false).print(messageOrBuilder, appendable);
    }

    @Deprecated
    public static void printUnicodeFieldValue(Descriptors.FieldDescriptor fieldDescriptor, Object obj, Appendable appendable) throws IOException {
        printer().escapingNonAscii(false).printFieldValue(fieldDescriptor, obj, appendable);
    }

    public static void printUnknownFieldValue(int i, Object obj, Appendable appendable) throws IOException {
        printUnknownFieldValue(i, obj, multiLineOutput(appendable));
    }

    public static Printer printer() {
        return Printer.DEFAULT;
    }

    public static String shortDebugString(MessageOrBuilder messageOrBuilder) {
        return printer().shortDebugString(messageOrBuilder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static b singleLineOutput(Appendable appendable) {
        return new b(appendable, true, null);
    }

    public static ByteString unescapeBytes(CharSequence charSequence) throws InvalidEscapeSequenceException {
        int i;
        int i2;
        int i3;
        int i4;
        int length;
        ByteString copyFromUtf8 = ByteString.copyFromUtf8(charSequence.toString());
        int size = copyFromUtf8.size();
        byte[] bArr = new byte[size];
        int i5 = 0;
        int i6 = 0;
        while (i5 < copyFromUtf8.size()) {
            byte byteAt = copyFromUtf8.byteAt(i5);
            if (byteAt == 92) {
                i5++;
                if (i5 < copyFromUtf8.size()) {
                    byte byteAt2 = copyFromUtf8.byteAt(i5);
                    if (isOctal(byteAt2)) {
                        int digitValue = digitValue(byteAt2);
                        int i7 = i5 + 1;
                        if (i7 < copyFromUtf8.size() && isOctal(copyFromUtf8.byteAt(i7))) {
                            digitValue = (digitValue * 8) + digitValue(copyFromUtf8.byteAt(i7));
                            i5 = i7;
                        }
                        int i8 = i5 + 1;
                        if (i8 < copyFromUtf8.size() && isOctal(copyFromUtf8.byteAt(i8))) {
                            digitValue = (digitValue * 8) + digitValue(copyFromUtf8.byteAt(i8));
                            i5 = i8;
                        }
                        i = i6 + 1;
                        bArr[i6] = (byte) digitValue;
                    } else {
                        if (byteAt2 == 34) {
                            i2 = i6 + 1;
                            bArr[i6] = 34;
                        } else if (byteAt2 != 39) {
                            if (byteAt2 == 85) {
                                int i9 = i5 + 1;
                                i3 = i9 + 7;
                                if (i3 >= copyFromUtf8.size()) {
                                    throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\U' with too few hex chars");
                                }
                                int i10 = 0;
                                int i11 = i9;
                                while (true) {
                                    if (i11 < i9 + 8) {
                                        byte byteAt3 = copyFromUtf8.byteAt(i11);
                                        if (isHex(byteAt3)) {
                                            i10 = (i10 << 4) | digitValue(byteAt3);
                                            i11++;
                                        } else {
                                            throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\U' with too few hex chars");
                                        }
                                    } else if (Character.isValidCodePoint(i10)) {
                                        Character.UnicodeBlock of = Character.UnicodeBlock.of(i10);
                                        if (!of.equals(Character.UnicodeBlock.LOW_SURROGATES) && !of.equals(Character.UnicodeBlock.HIGH_SURROGATES) && !of.equals(Character.UnicodeBlock.HIGH_PRIVATE_USE_SURROGATES)) {
                                            byte[] bytes = new String(new int[]{i10}, 0, 1).getBytes(StandardCharsets.UTF_8);
                                            System.arraycopy(bytes, 0, bArr, i6, bytes.length);
                                            length = bytes.length;
                                        } else {
                                            throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\U" + copyFromUtf8.substring(i9, i4).toStringUtf8() + "' refers to a surrogate code unit");
                                        }
                                    } else {
                                        throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\U" + copyFromUtf8.substring(i9, i4).toStringUtf8() + "' is not a valid code point value");
                                    }
                                }
                            } else if (byteAt2 == 92) {
                                i2 = i6 + 1;
                                bArr[i6] = 92;
                            } else if (byteAt2 == 102) {
                                i2 = i6 + 1;
                                bArr[i6] = 12;
                            } else if (byteAt2 == 110) {
                                i2 = i6 + 1;
                                bArr[i6] = 10;
                            } else if (byteAt2 == 114) {
                                i2 = i6 + 1;
                                bArr[i6] = 13;
                            } else if (byteAt2 == 120) {
                                i5++;
                                if (i5 < copyFromUtf8.size() && isHex(copyFromUtf8.byteAt(i5))) {
                                    int digitValue2 = digitValue(copyFromUtf8.byteAt(i5));
                                    int i12 = i5 + 1;
                                    if (i12 < copyFromUtf8.size() && isHex(copyFromUtf8.byteAt(i12))) {
                                        digitValue2 = (digitValue2 * 16) + digitValue(copyFromUtf8.byteAt(i12));
                                        i5 = i12;
                                    }
                                    i = i6 + 1;
                                    bArr[i6] = (byte) digitValue2;
                                } else {
                                    throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\x' with no digits");
                                }
                            } else if (byteAt2 == 97) {
                                i2 = i6 + 1;
                                bArr[i6] = 7;
                            } else if (byteAt2 != 98) {
                                switch (byteAt2) {
                                    case 116:
                                        i2 = i6 + 1;
                                        bArr[i6] = 9;
                                        break;
                                    case 117:
                                        int i13 = i5 + 1;
                                        i3 = i13 + 3;
                                        if (i3 < copyFromUtf8.size() && isHex(copyFromUtf8.byteAt(i13))) {
                                            int i14 = i13 + 1;
                                            if (isHex(copyFromUtf8.byteAt(i14))) {
                                                int i15 = i13 + 2;
                                                if (isHex(copyFromUtf8.byteAt(i15)) && isHex(copyFromUtf8.byteAt(i3))) {
                                                    char digitValue3 = (char) ((digitValue(copyFromUtf8.byteAt(i13)) << 12) | (digitValue(copyFromUtf8.byteAt(i14)) << 8) | (digitValue(copyFromUtf8.byteAt(i15)) << 4) | digitValue(copyFromUtf8.byteAt(i3)));
                                                    if (!Character.isSurrogate(digitValue3)) {
                                                        byte[] bytes2 = Character.toString(digitValue3).getBytes(StandardCharsets.UTF_8);
                                                        System.arraycopy(bytes2, 0, bArr, i6, bytes2.length);
                                                        length = bytes2.length;
                                                        break;
                                                    } else {
                                                        throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\u' refers to a surrogate");
                                                    }
                                                }
                                            }
                                        }
                                        throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\u' with too few hex chars");
                                    case 118:
                                        i2 = i6 + 1;
                                        bArr[i6] = 11;
                                        break;
                                    default:
                                        throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\" + ((char) byteAt2) + '\'');
                                }
                            } else {
                                i2 = i6 + 1;
                                bArr[i6] = 8;
                            }
                            i6 += length;
                            i5 = i3;
                            i5++;
                        } else {
                            i2 = i6 + 1;
                            bArr[i6] = 39;
                        }
                        i6 = i2;
                        i5++;
                    }
                } else {
                    throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\' at end of string.");
                }
            } else {
                i = i6 + 1;
                bArr[i6] = byteAt;
            }
            i6 = i;
            i5++;
        }
        if (size == i6) {
            return ByteString.wrap(bArr);
        }
        return ByteString.copyFrom(bArr, 0, i6);
    }

    public static String unescapeText(String str) throws InvalidEscapeSequenceException {
        return unescapeBytes(str).toStringUtf8();
    }

    public static String unsignedToString(int i) {
        if (i >= 0) {
            return Integer.toString(i);
        }
        return Long.toString(i & 4294967295L);
    }

    public static String escapeBytes(byte[] bArr) {
        return w0.c(bArr);
    }

    public static void merge(CharSequence charSequence, Message.Builder builder) throws ParseException {
        PARSER.merge(charSequence, builder);
    }

    @Deprecated
    public static void print(UnknownFieldSet unknownFieldSet, Appendable appendable) throws IOException {
        printer().print(unknownFieldSet, appendable);
    }

    @Deprecated
    public static String printToString(UnknownFieldSet unknownFieldSet) {
        return printer().printToString(unknownFieldSet);
    }

    @Deprecated
    public static String printToUnicodeString(UnknownFieldSet unknownFieldSet) {
        return printer().escapingNonAscii(false).printToString(unknownFieldSet);
    }

    @Deprecated
    public static void printUnicode(UnknownFieldSet unknownFieldSet, Appendable appendable) throws IOException {
        printer().escapingNonAscii(false).print(unknownFieldSet, appendable);
    }

    private static void printUnknownFieldValue(int i, Object obj, b bVar) throws IOException {
        int tagWireType = WireFormat.getTagWireType(i);
        if (tagWireType == 0) {
            bVar.d(unsignedToString(((Long) obj).longValue()));
        } else if (tagWireType == 1) {
            bVar.d(String.format(null, "0x%016x", (Long) obj));
        } else if (tagWireType != 2) {
            if (tagWireType == 3) {
                Printer.printUnknownFields((UnknownFieldSet) obj, bVar);
            } else if (tagWireType == 5) {
                bVar.d(String.format(null, "0x%08x", (Integer) obj));
            } else {
                throw new IllegalArgumentException("Bad tag: " + i);
            }
        } else {
            try {
                UnknownFieldSet parseFrom = UnknownFieldSet.parseFrom((ByteString) obj);
                bVar.d("{");
                bVar.a();
                bVar.b();
                Printer.printUnknownFields(parseFrom, bVar);
                bVar.c();
                bVar.d("}");
            } catch (InvalidProtocolBufferException unused) {
                bVar.d("\"");
                bVar.d(escapeBytes((ByteString) obj));
                bVar.d("\"");
            }
        }
    }

    @Deprecated
    public static String shortDebugString(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
        return printer().shortDebugString(fieldDescriptor, obj);
    }

    public static void merge(Readable readable, ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {
        PARSER.merge(readable, extensionRegistry, builder);
    }

    @Deprecated
    public static String shortDebugString(UnknownFieldSet unknownFieldSet) {
        return printer().shortDebugString(unknownFieldSet);
    }

    public static String unsignedToString(long j) {
        if (j >= 0) {
            return Long.toString(j);
        }
        return BigInteger.valueOf(j & Long.MAX_VALUE).setBit(63).toString();
    }

    public static void merge(CharSequence charSequence, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
        PARSER.merge(charSequence, extensionRegistry, builder);
    }

    public static <T extends Message> T parse(CharSequence charSequence, ExtensionRegistry extensionRegistry, Class<T> cls) throws ParseException {
        Message.Builder newBuilderForType = ((Message) Internal.getDefaultInstance(cls)).newBuilderForType();
        merge(charSequence, extensionRegistry, newBuilderForType);
        return (T) newBuilderForType.build();
    }
}
