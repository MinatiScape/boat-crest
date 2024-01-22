package com.clevertap.android.sdk.db;

import com.clevertap.android.sdk.db.DBAdapter;
import org.json.JSONArray;
/* loaded from: classes2.dex */
public final class QueueCursor {

    /* renamed from: a  reason: collision with root package name */
    public JSONArray f2606a;
    public String b;
    public DBAdapter.Table c;

    public String a() {
        return this.b;
    }

    public DBAdapter.Table b() {
        return this.c;
    }

    public void c(JSONArray jSONArray) {
        this.f2606a = jSONArray;
    }

    public void d(String str) {
        this.b = str;
    }

    public void e(DBAdapter.Table table) {
        this.c = table;
    }

    public JSONArray getData() {
        return this.f2606a;
    }

    public Boolean isEmpty() {
        JSONArray jSONArray;
        return Boolean.valueOf(this.b == null || (jSONArray = this.f2606a) == null || jSONArray.length() <= 0);
    }

    public String toString() {
        if (isEmpty().booleanValue()) {
            return "tableName: " + this.c + " | numItems: 0";
        }
        return "tableName: " + this.c + " | lastId: " + this.b + " | numItems: " + this.f2606a.length() + " | items: " + this.f2606a.toString();
    }
}
