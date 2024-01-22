package com.mappls.sdk.plugins.places.autocomplete;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.mappls.sdk.plugins.places.autocomplete.data.SearchHistoryDatabase;
import java.util.List;
/* loaded from: classes10.dex */
public final class a {
    public static a c;

    /* renamed from: a  reason: collision with root package name */
    public final SearchHistoryDatabase f13128a;
    public final MediatorLiveData<List<com.mappls.sdk.plugins.places.autocomplete.data.entity.a>> b;

    /* renamed from: com.mappls.sdk.plugins.places.autocomplete.a$a  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public class C0659a implements Observer<List<com.mappls.sdk.plugins.places.autocomplete.data.entity.a>> {
        public final /* synthetic */ SearchHistoryDatabase h;

        public C0659a(SearchHistoryDatabase searchHistoryDatabase) {
            this.h = searchHistoryDatabase;
        }

        @Override // androidx.lifecycle.Observer
        public final void onChanged(@Nullable List<com.mappls.sdk.plugins.places.autocomplete.data.entity.a> list) {
            List<com.mappls.sdk.plugins.places.autocomplete.data.entity.a> list2 = list;
            if (this.h.a().getValue() != 0) {
                a.this.b.postValue(list2);
            }
        }
    }

    public a(SearchHistoryDatabase searchHistoryDatabase) {
        this.f13128a = searchHistoryDatabase;
        MediatorLiveData<List<com.mappls.sdk.plugins.places.autocomplete.data.entity.a>> mediatorLiveData = new MediatorLiveData<>();
        this.b = mediatorLiveData;
        mediatorLiveData.addSource(searchHistoryDatabase.b().getAll(), new C0659a(searchHistoryDatabase));
    }

    public static a a(SearchHistoryDatabase searchHistoryDatabase) {
        if (c == null) {
            c = new a(searchHistoryDatabase);
        }
        return c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(List list) {
        if (this.f13128a.a().getValue() != 0) {
            this.b.postValue(list);
        }
    }

    public final MediatorLiveData a() {
        return this.b;
    }

    public final void a(com.mappls.sdk.plugins.places.autocomplete.data.entity.a aVar) {
        SearchHistoryDatabase.a(this.f13128a, aVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(String str) {
        this.b.addSource(TextUtils.isEmpty(str) ? this.f13128a.b().getAll() : this.f13128a.b().a(str), new Observer() { // from class: com.mappls.sdk.plugins.places.autocomplete.b
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                a.this.c((List) obj);
            }
        });
    }
}
