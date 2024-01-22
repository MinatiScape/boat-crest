package com.mappls.sdk.plugins.places.autocomplete.data.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.mappls.sdk.services.api.autosuggest.model.ELocation;
@Entity(tableName = "searchhistory")
/* loaded from: classes10.dex */
public final class a {
    @NonNull
    @PrimaryKey

    /* renamed from: a  reason: collision with root package name */
    public final String f13134a;
    @ColumnInfo(name = "eLocation")
    public final ELocation b;
    @ColumnInfo(name = "timestamp")
    public Long c;
    @ColumnInfo(name = "place_name")
    public final String d;
    @ColumnInfo(name = "place_address")
    public final String e;
    @Nullable
    @ColumnInfo(name = "alternate_name")
    public final String f;

    public a(@NonNull String str, ELocation eLocation, String str2, String str3, String str4, long j) {
        this.f13134a = str;
        this.b = eLocation;
        this.c = Long.valueOf(j);
        this.f = str4;
        this.d = str2;
        this.e = str3;
    }

    @Nullable
    public final String a() {
        return this.f;
    }

    public final ELocation b() {
        return this.b;
    }

    public final String c() {
        return this.e;
    }

    @NonNull
    public final String d() {
        return this.f13134a;
    }

    public final String e() {
        return this.d;
    }

    public final Long f() {
        return this.c;
    }
}
