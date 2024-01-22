package com.google.protobuf;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes11.dex */
public class LazyField extends LazyFieldLite {
    private final MessageLite defaultInstance;

    /* loaded from: classes11.dex */
    public static class b<K> implements Map.Entry<K, Object> {
        public Map.Entry<K, LazyField> h;

        public LazyField a() {
            return this.h.getValue();
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.h.getKey();
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            LazyField value = this.h.getValue();
            if (value == null) {
                return null;
            }
            return value.getValue();
        }

        @Override // java.util.Map.Entry
        public Object setValue(Object obj) {
            if (obj instanceof MessageLite) {
                return this.h.getValue().setValue((MessageLite) obj);
            }
            throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
        }

        public b(Map.Entry<K, LazyField> entry) {
            this.h = entry;
        }
    }

    /* loaded from: classes11.dex */
    public static class c<K> implements Iterator<Map.Entry<K, Object>> {
        public Iterator<Map.Entry<K, Object>> h;

        public c(Iterator<Map.Entry<K, Object>> it) {
            this.h = it;
        }

        @Override // java.util.Iterator
        /* renamed from: a */
        public Map.Entry<K, Object> next() {
            Map.Entry<K, Object> next = this.h.next();
            return next.getValue() instanceof LazyField ? new b(next) : next;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.h.hasNext();
        }

        @Override // java.util.Iterator
        public void remove() {
            this.h.remove();
        }
    }

    public LazyField(MessageLite messageLite, ExtensionRegistryLite extensionRegistryLite, ByteString byteString) {
        super(extensionRegistryLite, byteString);
        this.defaultInstance = messageLite;
    }

    @Override // com.google.protobuf.LazyFieldLite
    public boolean containsDefaultInstance() {
        return super.containsDefaultInstance() || this.value == this.defaultInstance;
    }

    @Override // com.google.protobuf.LazyFieldLite
    public boolean equals(Object obj) {
        return getValue().equals(obj);
    }

    public MessageLite getValue() {
        return getValue(this.defaultInstance);
    }

    @Override // com.google.protobuf.LazyFieldLite
    public int hashCode() {
        return getValue().hashCode();
    }

    public String toString() {
        return getValue().toString();
    }
}
