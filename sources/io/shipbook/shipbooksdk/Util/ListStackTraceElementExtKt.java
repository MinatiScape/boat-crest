package io.shipbook.shipbooksdk.Util;

import io.shipbook.shipbooksdk.Models.StackTraceElement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.h;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0012\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\u0000\u001a\u001f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0012\u0010\b\u001a\u00020\u0000*\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0000¨\u0006\t"}, d2 = {"Lorg/json/JSONArray;", "", "Lio/shipbook/shipbooksdk/Models/StackTraceElement;", "toListStackTraceElement", "", "Ljava/lang/StackTraceElement;", "toInternal", "([Ljava/lang/StackTraceElement;)Ljava/util/List;", "toJson", "shipbooksdk_release"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class ListStackTraceElementExtKt {
    @NotNull
    public static final List<StackTraceElement> toInternal(@NotNull StackTraceElement[] receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        ArrayList arrayList = new ArrayList();
        for (StackTraceElement stackTraceElement : receiver$0) {
            String className = stackTraceElement.getClassName();
            Intrinsics.checkExpressionValueIsNotNull(className, "it.className");
            String methodName = stackTraceElement.getMethodName();
            Intrinsics.checkExpressionValueIsNotNull(methodName, "it.methodName");
            arrayList.add(new StackTraceElement(className, methodName, stackTraceElement.getFileName(), stackTraceElement.getLineNumber()));
        }
        return arrayList;
    }

    @NotNull
    public static final JSONArray toJson(@NotNull List<StackTraceElement> receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        JSONArray jSONArray = new JSONArray();
        for (StackTraceElement stackTraceElement : receiver$0) {
            jSONArray.put(stackTraceElement.toJson());
        }
        return jSONArray;
    }

    @NotNull
    public static final List<StackTraceElement> toListStackTraceElement(@NotNull JSONArray receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        ArrayList arrayList = new ArrayList(receiver$0.length());
        Iterator<Integer> it = h.until(0, receiver$0.length()).iterator();
        while (it.hasNext()) {
            int nextInt = ((IntIterator) it).nextInt();
            StackTraceElement.Companion companion = StackTraceElement.Companion;
            JSONObject jSONObject = receiver$0.getJSONObject(nextInt);
            Intrinsics.checkExpressionValueIsNotNull(jSONObject, "this.getJSONObject(it)");
            arrayList.add(companion.create(jSONObject));
        }
        return arrayList;
    }
}
