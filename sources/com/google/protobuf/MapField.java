package com.google.protobuf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes11.dex */
public class MapField<K, V> implements f0 {
    private final a<K, V> converter;
    private volatile boolean isMutable;
    private List<Message> listData;
    private c<K, V> mapData;
    private volatile d mode;

    /* loaded from: classes11.dex */
    public interface a<K, V> {
        Message a(K k, V v);

        Message b();

        void c(Message message, Map<K, V> map);
    }

    /* loaded from: classes11.dex */
    public static class b<K, V> implements a<K, V> {

        /* renamed from: a  reason: collision with root package name */
        public final MapEntry<K, V> f11701a;

        public b(MapEntry<K, V> mapEntry) {
            this.f11701a = mapEntry;
        }

        @Override // com.google.protobuf.MapField.a
        public Message a(K k, V v) {
            return this.f11701a.newBuilderForType().setKey(k).setValue(v).buildPartial();
        }

        @Override // com.google.protobuf.MapField.a
        public Message b() {
            return this.f11701a;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.protobuf.MapField.a
        public void c(Message message, Map<K, V> map) {
            MapEntry mapEntry = (MapEntry) message;
            map.put(mapEntry.getKey(), mapEntry.getValue());
        }
    }

    /* loaded from: classes11.dex */
    public static class c<K, V> implements Map<K, V> {
        public final f0 h;
        public final Map<K, V> i;

        /* loaded from: classes11.dex */
        public static class a<E> implements Collection<E> {
            public final f0 h;
            public final Collection<E> i;

            public a(f0 f0Var, Collection<E> collection) {
                this.h = f0Var;
                this.i = collection;
            }

            @Override // java.util.Collection
            public boolean add(E e) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.Collection
            public boolean addAll(Collection<? extends E> collection) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.Collection
            public void clear() {
                this.h.ensureMutable();
                this.i.clear();
            }

            @Override // java.util.Collection
            public boolean contains(Object obj) {
                return this.i.contains(obj);
            }

            @Override // java.util.Collection
            public boolean containsAll(Collection<?> collection) {
                return this.i.containsAll(collection);
            }

            @Override // java.util.Collection
            public boolean equals(Object obj) {
                return this.i.equals(obj);
            }

            @Override // java.util.Collection
            public int hashCode() {
                return this.i.hashCode();
            }

            @Override // java.util.Collection
            public boolean isEmpty() {
                return this.i.isEmpty();
            }

            @Override // java.util.Collection, java.lang.Iterable
            public Iterator<E> iterator() {
                return new b(this.h, this.i.iterator());
            }

            @Override // java.util.Collection
            public boolean remove(Object obj) {
                this.h.ensureMutable();
                return this.i.remove(obj);
            }

            @Override // java.util.Collection
            public boolean removeAll(Collection<?> collection) {
                this.h.ensureMutable();
                return this.i.removeAll(collection);
            }

            @Override // java.util.Collection
            public boolean retainAll(Collection<?> collection) {
                this.h.ensureMutable();
                return this.i.retainAll(collection);
            }

            @Override // java.util.Collection
            public int size() {
                return this.i.size();
            }

            @Override // java.util.Collection
            public Object[] toArray() {
                return this.i.toArray();
            }

            public String toString() {
                return this.i.toString();
            }

            @Override // java.util.Collection
            public <T> T[] toArray(T[] tArr) {
                return (T[]) this.i.toArray(tArr);
            }
        }

        /* loaded from: classes11.dex */
        public static class b<E> implements Iterator<E> {
            public final f0 h;
            public final Iterator<E> i;

            public b(f0 f0Var, Iterator<E> it) {
                this.h = f0Var;
                this.i = it;
            }

            public boolean equals(Object obj) {
                return this.i.equals(obj);
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.i.hasNext();
            }

            public int hashCode() {
                return this.i.hashCode();
            }

            @Override // java.util.Iterator
            public E next() {
                return this.i.next();
            }

            @Override // java.util.Iterator
            public void remove() {
                this.h.ensureMutable();
                this.i.remove();
            }

            public String toString() {
                return this.i.toString();
            }
        }

        /* renamed from: com.google.protobuf.MapField$c$c  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public static class C0548c<E> implements Set<E> {
            public final f0 h;
            public final Set<E> i;

            public C0548c(f0 f0Var, Set<E> set) {
                this.h = f0Var;
                this.i = set;
            }

            @Override // java.util.Set, java.util.Collection
            public boolean add(E e) {
                this.h.ensureMutable();
                return this.i.add(e);
            }

            @Override // java.util.Set, java.util.Collection
            public boolean addAll(Collection<? extends E> collection) {
                this.h.ensureMutable();
                return this.i.addAll(collection);
            }

            @Override // java.util.Set, java.util.Collection
            public void clear() {
                this.h.ensureMutable();
                this.i.clear();
            }

            @Override // java.util.Set, java.util.Collection
            public boolean contains(Object obj) {
                return this.i.contains(obj);
            }

            @Override // java.util.Set, java.util.Collection
            public boolean containsAll(Collection<?> collection) {
                return this.i.containsAll(collection);
            }

            @Override // java.util.Set, java.util.Collection
            public boolean equals(Object obj) {
                return this.i.equals(obj);
            }

            @Override // java.util.Set, java.util.Collection
            public int hashCode() {
                return this.i.hashCode();
            }

            @Override // java.util.Set, java.util.Collection
            public boolean isEmpty() {
                return this.i.isEmpty();
            }

            @Override // java.util.Set, java.util.Collection, java.lang.Iterable
            public Iterator<E> iterator() {
                return new b(this.h, this.i.iterator());
            }

            @Override // java.util.Set, java.util.Collection
            public boolean remove(Object obj) {
                this.h.ensureMutable();
                return this.i.remove(obj);
            }

            @Override // java.util.Set, java.util.Collection
            public boolean removeAll(Collection<?> collection) {
                this.h.ensureMutable();
                return this.i.removeAll(collection);
            }

            @Override // java.util.Set, java.util.Collection
            public boolean retainAll(Collection<?> collection) {
                this.h.ensureMutable();
                return this.i.retainAll(collection);
            }

            @Override // java.util.Set, java.util.Collection
            public int size() {
                return this.i.size();
            }

            @Override // java.util.Set, java.util.Collection
            public Object[] toArray() {
                return this.i.toArray();
            }

            public String toString() {
                return this.i.toString();
            }

            @Override // java.util.Set, java.util.Collection
            public <T> T[] toArray(T[] tArr) {
                return (T[]) this.i.toArray(tArr);
            }
        }

        public c(f0 f0Var, Map<K, V> map) {
            this.h = f0Var;
            this.i = map;
        }

        @Override // java.util.Map
        public void clear() {
            this.h.ensureMutable();
            this.i.clear();
        }

        @Override // java.util.Map
        public boolean containsKey(Object obj) {
            return this.i.containsKey(obj);
        }

        @Override // java.util.Map
        public boolean containsValue(Object obj) {
            return this.i.containsValue(obj);
        }

        @Override // java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            return new C0548c(this.h, this.i.entrySet());
        }

        @Override // java.util.Map
        public boolean equals(Object obj) {
            return this.i.equals(obj);
        }

        @Override // java.util.Map
        public V get(Object obj) {
            return this.i.get(obj);
        }

        @Override // java.util.Map
        public int hashCode() {
            return this.i.hashCode();
        }

        @Override // java.util.Map
        public boolean isEmpty() {
            return this.i.isEmpty();
        }

        @Override // java.util.Map
        public Set<K> keySet() {
            return new C0548c(this.h, this.i.keySet());
        }

        @Override // java.util.Map
        public V put(K k, V v) {
            this.h.ensureMutable();
            Internal.checkNotNull(k);
            Internal.checkNotNull(v);
            return this.i.put(k, v);
        }

        @Override // java.util.Map
        public void putAll(Map<? extends K, ? extends V> map) {
            this.h.ensureMutable();
            for (K k : map.keySet()) {
                Internal.checkNotNull(k);
                Internal.checkNotNull(map.get(k));
            }
            this.i.putAll(map);
        }

        @Override // java.util.Map
        public V remove(Object obj) {
            this.h.ensureMutable();
            return this.i.remove(obj);
        }

        @Override // java.util.Map
        public int size() {
            return this.i.size();
        }

        public String toString() {
            return this.i.toString();
        }

        @Override // java.util.Map
        public Collection<V> values() {
            return new a(this.h, this.i.values());
        }
    }

    /* loaded from: classes11.dex */
    public enum d {
        MAP,
        LIST,
        BOTH
    }

    private MapField(a<K, V> aVar, d dVar, Map<K, V> map) {
        this.converter = aVar;
        this.isMutable = true;
        this.mode = dVar;
        this.mapData = new c<>(this, map);
        this.listData = null;
    }

    private Message convertKeyAndValueToMessage(K k, V v) {
        return this.converter.a(k, v);
    }

    private c<K, V> convertListToMap(List<Message> list) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Message message : list) {
            convertMessageToKeyAndValue(message, linkedHashMap);
        }
        return new c<>(this, linkedHashMap);
    }

    private List<Message> convertMapToList(c<K, V> cVar) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<K, V> entry : cVar.entrySet()) {
            arrayList.add(convertKeyAndValueToMessage(entry.getKey(), entry.getValue()));
        }
        return arrayList;
    }

    private void convertMessageToKeyAndValue(Message message, Map<K, V> map) {
        this.converter.c(message, map);
    }

    public static <K, V> MapField<K, V> emptyMapField(MapEntry<K, V> mapEntry) {
        return new MapField<>(mapEntry, d.MAP, Collections.emptyMap());
    }

    public static <K, V> MapField<K, V> newMapField(MapEntry<K, V> mapEntry) {
        return new MapField<>(mapEntry, d.MAP, new LinkedHashMap());
    }

    public void clear() {
        this.mapData = new c<>(this, new LinkedHashMap());
        this.mode = d.MAP;
    }

    public MapField<K, V> copy() {
        return new MapField<>(this.converter, d.MAP, MapFieldLite.copy((Map) getMap()));
    }

    @Override // com.google.protobuf.f0
    public void ensureMutable() {
        if (!isMutable()) {
            throw new UnsupportedOperationException();
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof MapField) {
            return MapFieldLite.equals((Map) getMap(), (Map) ((MapField) obj).getMap());
        }
        return false;
    }

    public List<Message> getList() {
        d dVar = this.mode;
        d dVar2 = d.MAP;
        if (dVar == dVar2) {
            synchronized (this) {
                if (this.mode == dVar2) {
                    this.listData = convertMapToList(this.mapData);
                    this.mode = d.BOTH;
                }
            }
        }
        return Collections.unmodifiableList(this.listData);
    }

    public Map<K, V> getMap() {
        d dVar = this.mode;
        d dVar2 = d.LIST;
        if (dVar == dVar2) {
            synchronized (this) {
                if (this.mode == dVar2) {
                    this.mapData = convertListToMap(this.listData);
                    this.mode = d.BOTH;
                }
            }
        }
        return Collections.unmodifiableMap(this.mapData);
    }

    public Message getMapEntryMessageDefaultInstance() {
        return this.converter.b();
    }

    public List<Message> getMutableList() {
        d dVar = this.mode;
        d dVar2 = d.LIST;
        if (dVar != dVar2) {
            if (this.mode == d.MAP) {
                this.listData = convertMapToList(this.mapData);
            }
            this.mapData = null;
            this.mode = dVar2;
        }
        return this.listData;
    }

    public Map<K, V> getMutableMap() {
        d dVar = this.mode;
        d dVar2 = d.MAP;
        if (dVar != dVar2) {
            if (this.mode == d.LIST) {
                this.mapData = convertListToMap(this.listData);
            }
            this.listData = null;
            this.mode = dVar2;
        }
        return this.mapData;
    }

    public int hashCode() {
        return MapFieldLite.calculateHashCodeForMap(getMap());
    }

    public boolean isMutable() {
        return this.isMutable;
    }

    public void makeImmutable() {
        this.isMutable = false;
    }

    public void mergeFrom(MapField<K, V> mapField) {
        getMutableMap().putAll(MapFieldLite.copy((Map) mapField.getMap()));
    }

    private MapField(MapEntry<K, V> mapEntry, d dVar, Map<K, V> map) {
        this(new b(mapEntry), dVar, map);
    }
}
