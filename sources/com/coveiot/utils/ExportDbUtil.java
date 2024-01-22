package com.coveiot.utils;

import android.content.Context;
import com.coveiot.utils.utility.ExportDbListener;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class ExportDbUtil {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7607a;

    /* loaded from: classes9.dex */
    public static final class Companion extends SingletonHolder<ExportDbUtil, Context> {

        /* loaded from: classes9.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, ExportDbUtil> {

            /* renamed from: a  reason: collision with root package name */
            public static final a f7608a = new a();

            public a() {
                super(1, ExportDbUtil.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            /* renamed from: a */
            public final ExportDbUtil invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new ExportDbUtil(p0, null);
            }
        }

        public Companion() {
            super(a.f7608a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ExportDbUtil(Context context) {
        this.f7607a = context;
    }

    public /* synthetic */ ExportDbUtil(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final void exportDatabases(@NotNull String[] dbNames, @NotNull ExportDbListener exporterListener) {
        Intrinsics.checkNotNullParameter(dbNames, "dbNames");
        Intrinsics.checkNotNullParameter(exporterListener, "exporterListener");
        exporterListener.onFailure("Export database not supported in release build");
    }

    @NotNull
    public final Context getContext() {
        return this.f7607a;
    }

    public final void initialize() {
    }
}
