package com.google.protobuf;
/* loaded from: classes11.dex */
public final class DiscardUnknownFieldsParser {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes11.dex */
    public static class a<T> extends AbstractParser<T> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Parser f11668a;

        public a(Parser parser) {
            this.f11668a = parser;
        }

        /* JADX WARN: Incorrect return type in method signature: (Lcom/google/protobuf/CodedInputStream;Lcom/google/protobuf/ExtensionRegistryLite;)TT; */
        @Override // com.google.protobuf.Parser
        /* renamed from: a */
        public Message parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            try {
                codedInputStream.discardUnknownFields();
                return (Message) this.f11668a.parsePartialFrom(codedInputStream, extensionRegistryLite);
            } finally {
                codedInputStream.unsetDiscardUnknownFields();
            }
        }
    }

    private DiscardUnknownFieldsParser() {
    }

    public static final <T extends Message> Parser<T> wrap(Parser<T> parser) {
        return new a(parser);
    }
}
