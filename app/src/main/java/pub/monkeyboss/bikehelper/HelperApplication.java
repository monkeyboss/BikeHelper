package pub.monkeyboss.bikehelper;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by LiangXu on 2017-03-22.
 */

public class HelperApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
