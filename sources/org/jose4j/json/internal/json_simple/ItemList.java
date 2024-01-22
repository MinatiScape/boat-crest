package org.jose4j.json.internal.json_simple;

import com.clevertap.android.sdk.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/* loaded from: classes13.dex */
public class ItemList {

    /* renamed from: a  reason: collision with root package name */
    public String f15504a;
    public List b;

    public ItemList() {
        this.f15504a = Constants.SEPARATOR_COMMA;
        this.b = new ArrayList();
    }

    public void add(int i, String str) {
        if (str == null) {
            return;
        }
        this.b.add(i, str.trim());
    }

    public void addAll(ItemList itemList) {
        this.b.addAll(itemList.b);
    }

    public void clear() {
        this.b.clear();
    }

    public String get(int i) {
        return (String) this.b.get(i);
    }

    public String[] getArray() {
        return (String[]) this.b.toArray();
    }

    public List getItems() {
        return this.b;
    }

    public void reset() {
        this.f15504a = Constants.SEPARATOR_COMMA;
        this.b.clear();
    }

    public void setSP(String str) {
        this.f15504a = str;
    }

    public int size() {
        return this.b.size();
    }

    public void split(String str, String str2, List list, boolean z) {
        if (str == null || str2 == null) {
            return;
        }
        if (z) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, str2);
            while (stringTokenizer.hasMoreTokens()) {
                list.add(stringTokenizer.nextToken().trim());
            }
            return;
        }
        split(str, str2, list);
    }

    public String toString() {
        return toString(this.f15504a);
    }

    public void add(String str) {
        if (str == null) {
            return;
        }
        this.b.add(str.trim());
    }

    public void addAll(String str) {
        split(str, this.f15504a, this.b);
    }

    public String toString(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.b.size(); i++) {
            if (i == 0) {
                sb.append(this.b.get(i));
            } else {
                sb.append(str);
                sb.append(this.b.get(i));
            }
        }
        return sb.toString();
    }

    public void addAll(String str, String str2) {
        split(str, str2, this.b);
    }

    public ItemList(String str) {
        this.f15504a = Constants.SEPARATOR_COMMA;
        ArrayList arrayList = new ArrayList();
        this.b = arrayList;
        split(str, this.f15504a, arrayList);
    }

    public void addAll(String str, String str2, boolean z) {
        split(str, str2, this.b, z);
    }

    public void split(String str, String str2, List list) {
        if (str == null || str2 == null) {
            return;
        }
        int i = 0;
        while (true) {
            int indexOf = str.indexOf(str2, i);
            if (indexOf == -1) {
                break;
            }
            list.add(str.substring(i, indexOf).trim());
            int length = indexOf + str2.length();
            if (length == -1) {
                break;
            }
            i = length;
        }
        list.add(str.substring(i).trim());
    }

    public ItemList(String str, String str2) {
        this.f15504a = Constants.SEPARATOR_COMMA;
        ArrayList arrayList = new ArrayList();
        this.b = arrayList;
        this.f15504a = str;
        split(str, str2, arrayList);
    }

    public ItemList(String str, String str2, boolean z) {
        this.f15504a = Constants.SEPARATOR_COMMA;
        ArrayList arrayList = new ArrayList();
        this.b = arrayList;
        split(str, str2, arrayList, z);
    }
}
