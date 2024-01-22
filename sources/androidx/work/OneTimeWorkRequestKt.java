package androidx.work;

import androidx.annotation.NonNull;
import androidx.exifinterface.media.ExifInterface;
import androidx.work.OneTimeWorkRequest;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0015\u0010\u0003\u001a\u00020\u0002\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000H\u0086\b\u001a\u001f\u0010\u0007\u001a\u00020\u0002*\u00020\u00022\u0010\b\u0001\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004H\u0086\b¨\u0006\b"}, d2 = {"Landroidx/work/ListenableWorker;", ExifInterface.LONGITUDE_WEST, "Landroidx/work/OneTimeWorkRequest$Builder;", "OneTimeWorkRequestBuilder", "Lkotlin/reflect/KClass;", "Landroidx/work/InputMerger;", "inputMerger", "setInputMerger", "work-runtime-ktx_release"}, k = 2, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class OneTimeWorkRequestKt {
    public static final /* synthetic */ <W extends ListenableWorker> OneTimeWorkRequest.Builder OneTimeWorkRequestBuilder() {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.LONGITUDE_WEST);
        return new OneTimeWorkRequest.Builder(ListenableWorker.class);
    }

    @NotNull
    public static final OneTimeWorkRequest.Builder setInputMerger(@NotNull OneTimeWorkRequest.Builder builder, @NonNull @NotNull KClass<? extends InputMerger> inputMerger) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(inputMerger, "inputMerger");
        OneTimeWorkRequest.Builder inputMerger2 = builder.setInputMerger(JvmClassMappingKt.getJavaClass((KClass) inputMerger));
        Intrinsics.checkNotNullExpressionValue(inputMerger2, "setInputMerger(inputMerger.java)");
        return inputMerger2;
    }
}
