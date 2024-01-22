package com.google.android.material.color;

import android.content.Context;
import android.util.Pair;
import androidx.annotation.ColorInt;
import com.clevertap.android.sdk.Constants;
import com.touchgui.sdk.TGEventListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes10.dex */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    public static byte f10261a;
    public static final C0428d b = new C0428d(1, Constants.KEY_ANDROID);
    public static final Comparator<b> c = new a();

    /* loaded from: classes10.dex */
    public class a implements Comparator<b> {
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(b bVar, b bVar2) {
            return bVar.c - bVar2.c;
        }
    }

    /* loaded from: classes10.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final byte f10262a;
        public final byte b;
        public final short c;
        public final String d;
        @ColorInt
        public final int e;

        public b(int i, String str, int i2) {
            this.d = str;
            this.e = i2;
            this.c = (short) (65535 & i);
            this.b = (byte) ((i >> 16) & 255);
            this.f10262a = (byte) ((i >> 24) & 255);
        }
    }

    /* loaded from: classes10.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public final e f10263a;
        public final C0428d b;
        public final h c = new h(false, "?1", "?2", "?3", "?4", "?5", "color");
        public final h d;
        public final k e;

        public c(C0428d c0428d, List<b> list) {
            this.b = c0428d;
            String[] strArr = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                strArr[i] = list.get(i).d;
            }
            this.d = new h(true, strArr);
            this.e = new k(list);
            this.f10263a = new e((short) 512, (short) 288, a());
        }

        public int a() {
            return this.c.a() + TGEventListener.WORKOUT_START + this.d.a() + this.e.b();
        }

        public void b(ByteArrayOutputStream byteArrayOutputStream) throws IOException {
            this.f10263a.a(byteArrayOutputStream);
            byteArrayOutputStream.write(d.j(this.b.f10264a));
            char[] charArray = this.b.b.toCharArray();
            for (int i = 0; i < 128; i++) {
                if (i < charArray.length) {
                    byteArrayOutputStream.write(d.h(charArray[i]));
                } else {
                    byteArrayOutputStream.write(d.h((char) 0));
                }
            }
            byteArrayOutputStream.write(d.j(TGEventListener.WORKOUT_START));
            byteArrayOutputStream.write(d.j(0));
            byteArrayOutputStream.write(d.j(this.c.a() + TGEventListener.WORKOUT_START));
            byteArrayOutputStream.write(d.j(0));
            byteArrayOutputStream.write(d.j(0));
            this.c.c(byteArrayOutputStream);
            this.d.c(byteArrayOutputStream);
            this.e.c(byteArrayOutputStream);
        }
    }

    /* renamed from: com.google.android.material.color.d$d  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public static class C0428d {

        /* renamed from: a  reason: collision with root package name */
        public final int f10264a;
        public final String b;

        public C0428d(int i, String str) {
            this.f10264a = i;
            this.b = str;
        }
    }

    /* loaded from: classes10.dex */
    public static class e {

        /* renamed from: a  reason: collision with root package name */
        public final short f10265a;
        public final short b;
        public final int c;

        public e(short s, short s2, int i) {
            this.f10265a = s;
            this.b = s2;
            this.c = i;
        }

        public void a(ByteArrayOutputStream byteArrayOutputStream) throws IOException {
            byteArrayOutputStream.write(d.k(this.f10265a));
            byteArrayOutputStream.write(d.k(this.b));
            byteArrayOutputStream.write(d.j(this.c));
        }
    }

    /* loaded from: classes10.dex */
    public static class f {

        /* renamed from: a  reason: collision with root package name */
        public final int f10266a;
        public final int b;

        public f(int i, @ColorInt int i2) {
            this.f10266a = i;
            this.b = i2;
        }

        public void a(ByteArrayOutputStream byteArrayOutputStream) throws IOException {
            byteArrayOutputStream.write(d.k((short) 8));
            byteArrayOutputStream.write(d.k((short) 2));
            byteArrayOutputStream.write(d.j(this.f10266a));
            byteArrayOutputStream.write(d.k((short) 8));
            byteArrayOutputStream.write(new byte[]{0, 28});
            byteArrayOutputStream.write(d.j(this.b));
        }
    }

    /* loaded from: classes10.dex */
    public static class g {

        /* renamed from: a  reason: collision with root package name */
        public final e f10267a;
        public final int b;
        public final List<c> d = new ArrayList();
        public final h c = new h(new String[0]);

        public g(Map<C0428d, List<b>> map) {
            this.b = map.size();
            for (Map.Entry<C0428d, List<b>> entry : map.entrySet()) {
                List<b> value = entry.getValue();
                Collections.sort(value, d.c);
                this.d.add(new c(entry.getKey(), value));
            }
            this.f10267a = new e((short) 2, (short) 12, a());
        }

        public final int a() {
            int i = 0;
            for (c cVar : this.d) {
                i += cVar.a();
            }
            return this.c.a() + 12 + i;
        }

        public void b(ByteArrayOutputStream byteArrayOutputStream) throws IOException {
            this.f10267a.a(byteArrayOutputStream);
            byteArrayOutputStream.write(d.j(this.b));
            this.c.c(byteArrayOutputStream);
            for (c cVar : this.d) {
                cVar.b(byteArrayOutputStream);
            }
        }
    }

    /* loaded from: classes10.dex */
    public static class h {

        /* renamed from: a  reason: collision with root package name */
        public final e f10268a;
        public final int b;
        public final int c;
        public final int d;
        public final int e;
        public final List<Integer> f;
        public final List<Integer> g;
        public final List<byte[]> h;
        public final List<List<i>> i;
        public final boolean j;
        public final int k;
        public final int l;

        public h(String... strArr) {
            this(false, strArr);
        }

        public int a() {
            return this.l;
        }

        public final Pair<byte[], List<i>> b(String str) {
            return new Pair<>(this.j ? d.m(str) : d.l(str), Collections.emptyList());
        }

        public void c(ByteArrayOutputStream byteArrayOutputStream) throws IOException {
            this.f10268a.a(byteArrayOutputStream);
            byteArrayOutputStream.write(d.j(this.b));
            byteArrayOutputStream.write(d.j(this.c));
            byteArrayOutputStream.write(d.j(this.j ? 256 : 0));
            byteArrayOutputStream.write(d.j(this.d));
            byteArrayOutputStream.write(d.j(this.e));
            for (Integer num : this.f) {
                byteArrayOutputStream.write(d.j(num.intValue()));
            }
            for (Integer num2 : this.g) {
                byteArrayOutputStream.write(d.j(num2.intValue()));
            }
            for (byte[] bArr : this.h) {
                byteArrayOutputStream.write(bArr);
            }
            int i = this.k;
            if (i > 0) {
                byteArrayOutputStream.write(new byte[i]);
            }
            for (List<i> list : this.i) {
                for (i iVar : list) {
                    iVar.b(byteArrayOutputStream);
                }
                byteArrayOutputStream.write(d.j(-1));
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public h(boolean z, String... strArr) {
            this.f = new ArrayList();
            this.g = new ArrayList();
            this.h = new ArrayList();
            this.i = new ArrayList();
            this.j = z;
            int i = 0;
            for (String str : strArr) {
                Pair<byte[], List<i>> b = b(str);
                this.f.add(Integer.valueOf(i));
                Object obj = b.first;
                i += ((byte[]) obj).length;
                this.h.add(obj);
                this.i.add(b.second);
            }
            int i2 = 0;
            for (List<i> list : this.i) {
                for (i iVar : list) {
                    this.f.add(Integer.valueOf(i));
                    i += iVar.f10269a.length;
                    this.h.add(iVar.f10269a);
                }
                this.g.add(Integer.valueOf(i2));
                i2 += (list.size() * 12) + 4;
            }
            int i3 = i % 4;
            int i4 = i3 == 0 ? 0 : 4 - i3;
            this.k = i4;
            int size = this.h.size();
            this.b = size;
            this.c = this.h.size() - strArr.length;
            boolean z2 = this.h.size() - strArr.length > 0;
            if (!z2) {
                this.g.clear();
                this.i.clear();
            }
            int size2 = (size * 4) + 28 + (this.g.size() * 4);
            this.d = size2;
            int i5 = i + i4;
            this.e = z2 ? size2 + i5 : 0;
            int i6 = size2 + i5 + (z2 ? i2 : 0);
            this.l = i6;
            this.f10268a = new e((short) 1, (short) 28, i6);
        }
    }

    /* loaded from: classes10.dex */
    public static class i {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f10269a;
        public int b;
        public int c;
        public int d;

        public void b(ByteArrayOutputStream byteArrayOutputStream) throws IOException {
            byteArrayOutputStream.write(d.j(this.b));
            byteArrayOutputStream.write(d.j(this.c));
            byteArrayOutputStream.write(d.j(this.d));
        }
    }

    /* loaded from: classes10.dex */
    public static class j {

        /* renamed from: a  reason: collision with root package name */
        public final e f10270a;
        public final int b;
        public final byte[] c;
        public final int[] d;
        public final f[] e;

        public j(List<b> list, Set<Short> set, int i) {
            byte[] bArr = new byte[64];
            this.c = bArr;
            this.b = i;
            bArr[0] = 64;
            this.e = new f[list.size()];
            for (int i2 = 0; i2 < list.size(); i2++) {
                this.e[i2] = new f(i2, list.get(i2).e);
            }
            this.d = new int[i];
            int i3 = 0;
            for (short s = 0; s < i; s = (short) (s + 1)) {
                if (set.contains(Short.valueOf(s))) {
                    this.d[s] = i3;
                    i3 += 16;
                } else {
                    this.d[s] = -1;
                }
            }
            this.f10270a = new e((short) 513, (short) 84, a());
        }

        public int a() {
            return b() + (this.e.length * 16);
        }

        public final int b() {
            return c() + 84;
        }

        public final int c() {
            return this.d.length * 4;
        }

        public void d(ByteArrayOutputStream byteArrayOutputStream) throws IOException {
            this.f10270a.a(byteArrayOutputStream);
            byteArrayOutputStream.write(new byte[]{d.f10261a, 0, 0, 0});
            byteArrayOutputStream.write(d.j(this.b));
            byteArrayOutputStream.write(d.j(b()));
            byteArrayOutputStream.write(this.c);
            for (int i : this.d) {
                byteArrayOutputStream.write(d.j(i));
            }
            for (f fVar : this.e) {
                fVar.a(byteArrayOutputStream);
            }
        }
    }

    /* loaded from: classes10.dex */
    public static class k {

        /* renamed from: a  reason: collision with root package name */
        public final e f10271a;
        public final int b;
        public final int[] c;
        public final j d;

        public k(List<b> list) {
            this.b = list.get(list.size() - 1).c + 1;
            HashSet hashSet = new HashSet();
            for (b bVar : list) {
                hashSet.add(Short.valueOf(bVar.c));
            }
            this.c = new int[this.b];
            for (short s = 0; s < this.b; s = (short) (s + 1)) {
                if (hashSet.contains(Short.valueOf(s))) {
                    this.c[s] = 1073741824;
                }
            }
            this.f10271a = new e((short) 514, (short) 16, a());
            this.d = new j(list, hashSet, this.b);
        }

        public final int a() {
            return (this.b * 4) + 16;
        }

        public int b() {
            return a() + this.d.a();
        }

        public void c(ByteArrayOutputStream byteArrayOutputStream) throws IOException {
            this.f10271a.a(byteArrayOutputStream);
            byteArrayOutputStream.write(new byte[]{d.f10261a, 0, 0, 0});
            byteArrayOutputStream.write(d.j(this.b));
            for (int i : this.c) {
                byteArrayOutputStream.write(d.j(i));
            }
            this.d.d(byteArrayOutputStream);
        }
    }

    public static byte[] h(char c2) {
        return new byte[]{(byte) (c2 & 255), (byte) ((c2 >> '\b') & 255)};
    }

    public static byte[] i(Context context, Map<Integer, Integer> map) throws IOException {
        C0428d c0428d;
        if (!map.entrySet().isEmpty()) {
            C0428d c0428d2 = new C0428d(127, context.getPackageName());
            HashMap hashMap = new HashMap();
            b bVar = null;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                b bVar2 = new b(entry.getKey().intValue(), context.getResources().getResourceName(entry.getKey().intValue()), entry.getValue().intValue());
                if (context.getResources().getResourceTypeName(entry.getKey().intValue()).equals("color")) {
                    if (bVar2.f10262a != 1) {
                        if (bVar2.f10262a != Byte.MAX_VALUE) {
                            throw new IllegalArgumentException("Not supported with unknown package id: " + ((int) bVar2.f10262a));
                        }
                        c0428d = c0428d2;
                    } else {
                        c0428d = b;
                    }
                    if (!hashMap.containsKey(c0428d)) {
                        hashMap.put(c0428d, new ArrayList());
                    }
                    ((List) hashMap.get(c0428d)).add(bVar2);
                    bVar = bVar2;
                } else {
                    throw new IllegalArgumentException("Non color resource found: name=" + bVar2.d + ", typeId=" + Integer.toHexString(bVar2.b & 255));
                }
            }
            byte b2 = bVar.b;
            f10261a = b2;
            if (b2 != 0) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                new g(hashMap).b(byteArrayOutputStream);
                return byteArrayOutputStream.toByteArray();
            }
            throw new IllegalArgumentException("No color resources found for harmonization.");
        }
        throw new IllegalArgumentException("No color resources provided for harmonization.");
    }

    public static byte[] j(int i2) {
        return new byte[]{(byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 24) & 255)};
    }

    public static byte[] k(short s) {
        return new byte[]{(byte) (s & 255), (byte) ((s >> 8) & 255)};
    }

    public static byte[] l(String str) {
        char[] charArray = str.toCharArray();
        int length = (charArray.length * 2) + 4;
        byte[] bArr = new byte[length];
        byte[] k2 = k((short) charArray.length);
        bArr[0] = k2[0];
        bArr[1] = k2[1];
        for (int i2 = 0; i2 < charArray.length; i2++) {
            byte[] h2 = h(charArray[i2]);
            int i3 = i2 * 2;
            bArr[i3 + 2] = h2[0];
            bArr[i3 + 3] = h2[1];
        }
        bArr[length - 2] = 0;
        bArr[length - 1] = 0;
        return bArr;
    }

    public static byte[] m(String str) {
        byte[] bytes = str.getBytes(Charset.forName("UTF-8"));
        byte length = (byte) bytes.length;
        int length2 = bytes.length + 3;
        byte[] bArr = new byte[length2];
        System.arraycopy(bytes, 0, bArr, 2, length);
        bArr[1] = length;
        bArr[0] = length;
        bArr[length2 - 1] = 0;
        return bArr;
    }
}
