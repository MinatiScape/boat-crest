package androidx.core.view.accessibility;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
/* loaded from: classes.dex */
public interface AccessibilityViewCommand {

    /* loaded from: classes.dex */
    public static abstract class CommandArguments {

        /* renamed from: a  reason: collision with root package name */
        public Bundle f1188a;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void setBundle(@Nullable Bundle bundle) {
            this.f1188a = bundle;
        }
    }

    /* loaded from: classes.dex */
    public static final class MoveAtGranularityArguments extends CommandArguments {
        public boolean getExtendSelection() {
            return this.f1188a.getBoolean(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN);
        }

        public int getGranularity() {
            return this.f1188a.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT);
        }
    }

    /* loaded from: classes.dex */
    public static final class MoveHtmlArguments extends CommandArguments {
        @Nullable
        public String getHTMLElement() {
            return this.f1188a.getString(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_HTML_ELEMENT_STRING);
        }
    }

    /* loaded from: classes.dex */
    public static final class MoveWindowArguments extends CommandArguments {
        public int getX() {
            return this.f1188a.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_MOVE_WINDOW_X);
        }

        public int getY() {
            return this.f1188a.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_MOVE_WINDOW_Y);
        }
    }

    /* loaded from: classes.dex */
    public static final class ScrollToPositionArguments extends CommandArguments {
        public int getColumn() {
            return this.f1188a.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_COLUMN_INT);
        }

        public int getRow() {
            return this.f1188a.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_ROW_INT);
        }
    }

    /* loaded from: classes.dex */
    public static final class SetProgressArguments extends CommandArguments {
        public float getProgress() {
            return this.f1188a.getFloat(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_PROGRESS_VALUE);
        }
    }

    /* loaded from: classes.dex */
    public static final class SetSelectionArguments extends CommandArguments {
        public int getEnd() {
            return this.f1188a.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SELECTION_END_INT);
        }

        public int getStart() {
            return this.f1188a.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SELECTION_START_INT);
        }
    }

    /* loaded from: classes.dex */
    public static final class SetTextArguments extends CommandArguments {
        @Nullable
        public CharSequence getText() {
            return this.f1188a.getCharSequence(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE);
        }
    }

    boolean perform(@NonNull View view, @Nullable CommandArguments commandArguments);
}