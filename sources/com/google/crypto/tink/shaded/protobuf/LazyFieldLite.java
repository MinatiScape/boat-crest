package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.util.Objects;
/* loaded from: classes10.dex */
public class LazyFieldLite {

    /* renamed from: a  reason: collision with root package name */
    public ByteString f10959a;
    public ExtensionRegistryLite b;
    public volatile ByteString c;
    public volatile MessageLite value;

    static {
        ExtensionRegistryLite.getEmptyRegistry();
    }

    public LazyFieldLite(ExtensionRegistryLite extensionRegistryLite, ByteString byteString) {
        a(extensionRegistryLite, byteString);
        this.b = extensionRegistryLite;
        this.f10959a = byteString;
    }

    public static void a(ExtensionRegistryLite extensionRegistryLite, ByteString byteString) {
        Objects.requireNonNull(extensionRegistryLite, "found null ExtensionRegistry");
        Objects.requireNonNull(byteString, "found null ByteString");
    }

    public static MessageLite b(MessageLite messageLite, ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        try {
            return messageLite.toBuilder().mergeFrom(byteString, extensionRegistryLite).build();
        } catch (InvalidProtocolBufferException unused) {
            return messageLite;
        }
    }

    public static LazyFieldLite fromValue(MessageLite messageLite) {
        LazyFieldLite lazyFieldLite = new LazyFieldLite();
        lazyFieldLite.setValue(messageLite);
        return lazyFieldLite;
    }

    public void clear() {
        this.f10959a = null;
        this.value = null;
        this.c = null;
    }

    public boolean containsDefaultInstance() {
        ByteString byteString;
        ByteString byteString2 = this.c;
        ByteString byteString3 = ByteString.EMPTY;
        return byteString2 == byteString3 || (this.value == null && ((byteString = this.f10959a) == null || byteString == byteString3));
    }

    public void ensureInitialized(MessageLite messageLite) {
        if (this.value != null) {
            return;
        }
        synchronized (this) {
            if (this.value != null) {
                return;
            }
            try {
                if (this.f10959a != null) {
                    this.value = messageLite.getParserForType().parseFrom(this.f10959a, this.b);
                    this.c = this.f10959a;
                } else {
                    this.value = messageLite;
                    this.c = ByteString.EMPTY;
                }
            } catch (InvalidProtocolBufferException unused) {
                this.value = messageLite;
                this.c = ByteString.EMPTY;
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LazyFieldLite) {
            LazyFieldLite lazyFieldLite = (LazyFieldLite) obj;
            MessageLite messageLite = this.value;
            MessageLite messageLite2 = lazyFieldLite.value;
            if (messageLite == null && messageLite2 == null) {
                return toByteString().equals(lazyFieldLite.toByteString());
            }
            if (messageLite == null || messageLite2 == null) {
                if (messageLite != null) {
                    return messageLite.equals(lazyFieldLite.getValue(messageLite.getDefaultInstanceForType()));
                }
                return getValue(messageLite2.getDefaultInstanceForType()).equals(messageLite2);
            }
            return messageLite.equals(messageLite2);
        }
        return false;
    }

    public int getSerializedSize() {
        if (this.c != null) {
            return this.c.size();
        }
        ByteString byteString = this.f10959a;
        if (byteString != null) {
            return byteString.size();
        }
        if (this.value != null) {
            return this.value.getSerializedSize();
        }
        return 0;
    }

    public MessageLite getValue(MessageLite messageLite) {
        ensureInitialized(messageLite);
        return this.value;
    }

    public int hashCode() {
        return 1;
    }

    public void merge(LazyFieldLite lazyFieldLite) {
        ByteString byteString;
        if (lazyFieldLite.containsDefaultInstance()) {
            return;
        }
        if (containsDefaultInstance()) {
            set(lazyFieldLite);
            return;
        }
        if (this.b == null) {
            this.b = lazyFieldLite.b;
        }
        ByteString byteString2 = this.f10959a;
        if (byteString2 != null && (byteString = lazyFieldLite.f10959a) != null) {
            this.f10959a = byteString2.concat(byteString);
        } else if (this.value == null && lazyFieldLite.value != null) {
            setValue(b(lazyFieldLite.value, this.f10959a, this.b));
        } else if (this.value != null && lazyFieldLite.value == null) {
            setValue(b(this.value, lazyFieldLite.f10959a, lazyFieldLite.b));
        } else {
            setValue(this.value.toBuilder().mergeFrom(lazyFieldLite.value).build());
        }
    }

    public void mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        if (containsDefaultInstance()) {
            setByteString(codedInputStream.readBytes(), extensionRegistryLite);
            return;
        }
        if (this.b == null) {
            this.b = extensionRegistryLite;
        }
        ByteString byteString = this.f10959a;
        if (byteString != null) {
            setByteString(byteString.concat(codedInputStream.readBytes()), this.b);
            return;
        }
        try {
            setValue(this.value.toBuilder().mergeFrom(codedInputStream, extensionRegistryLite).build());
        } catch (InvalidProtocolBufferException unused) {
        }
    }

    public void set(LazyFieldLite lazyFieldLite) {
        this.f10959a = lazyFieldLite.f10959a;
        this.value = lazyFieldLite.value;
        this.c = lazyFieldLite.c;
        ExtensionRegistryLite extensionRegistryLite = lazyFieldLite.b;
        if (extensionRegistryLite != null) {
            this.b = extensionRegistryLite;
        }
    }

    public void setByteString(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        a(extensionRegistryLite, byteString);
        this.f10959a = byteString;
        this.b = extensionRegistryLite;
        this.value = null;
        this.c = null;
    }

    public MessageLite setValue(MessageLite messageLite) {
        MessageLite messageLite2 = this.value;
        this.f10959a = null;
        this.c = null;
        this.value = messageLite;
        return messageLite2;
    }

    public ByteString toByteString() {
        if (this.c != null) {
            return this.c;
        }
        ByteString byteString = this.f10959a;
        if (byteString != null) {
            return byteString;
        }
        synchronized (this) {
            if (this.c != null) {
                return this.c;
            }
            if (this.value == null) {
                this.c = ByteString.EMPTY;
            } else {
                this.c = this.value.toByteString();
            }
            return this.c;
        }
    }

    public LazyFieldLite() {
    }
}
