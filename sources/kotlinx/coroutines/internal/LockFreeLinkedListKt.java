package kotlinx.coroutines.internal;

import kotlin.PublishedApi;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class LockFreeLinkedListKt {
    public static final int FAILURE = 2;
    public static final int SUCCESS = 1;
    public static final int UNDECIDED = 0;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final Object f14177a = new Symbol("CONDITION_FALSE");
    @NotNull
    public static final Object b = new Symbol("LIST_EMPTY");

    @NotNull
    public static final Object getCONDITION_FALSE() {
        return f14177a;
    }

    @PublishedApi
    public static /* synthetic */ void getCONDITION_FALSE$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getFAILURE$annotations() {
    }

    @NotNull
    public static final Object getLIST_EMPTY() {
        return b;
    }

    @PublishedApi
    public static /* synthetic */ void getLIST_EMPTY$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getSUCCESS$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getUNDECIDED$annotations() {
    }

    @PublishedApi
    @NotNull
    public static final LockFreeLinkedListNode unwrap(@NotNull Object obj) {
        b bVar = obj instanceof b ? (b) obj : null;
        LockFreeLinkedListNode lockFreeLinkedListNode = bVar != null ? bVar.f14188a : null;
        return lockFreeLinkedListNode == null ? (LockFreeLinkedListNode) obj : lockFreeLinkedListNode;
    }
}
