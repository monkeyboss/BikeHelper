package pub.monkeyboss.bikehelper.util;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by LiangXu on 2017-03-23.
 */

public class MetricsUtil {

    public static DisplayMetrics getDisplayMetrics(Activity activity) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics;
    }

    public static int getWindowHeight(Activity activity) {
        return getDisplayMetrics(activity).heightPixels;
    }

    public static int getWindowWidth(Activity activity) {
        return getDisplayMetrics(activity).widthPixels;
    }
}
