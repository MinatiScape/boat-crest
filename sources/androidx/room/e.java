package androidx.room;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.io.File;
/* loaded from: classes.dex */
public class e implements SupportSQLiteOpenHelper.Factory {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final String f1669a;
    @Nullable
    public final File b;
    @NonNull
    public final SupportSQLiteOpenHelper.Factory c;

    public e(@Nullable String str, @Nullable File file, @NonNull SupportSQLiteOpenHelper.Factory factory) {
        this.f1669a = str;
        this.b = file;
        this.c = factory;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Factory
    public SupportSQLiteOpenHelper create(SupportSQLiteOpenHelper.Configuration configuration) {
        return new d(configuration.context, this.f1669a, this.b, configuration.callback.version, this.c.create(configuration));
    }
}
