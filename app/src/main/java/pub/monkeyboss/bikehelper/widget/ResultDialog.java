package pub.monkeyboss.bikehelper.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import pub.monkeyboss.bikehelper.R;
import pub.monkeyboss.bikehelper.util.MetricsUtil;

/**
 * Created by LiangXu on 2016-05-14.
 */
public class ResultDialog {

    public static Dialog showDialog(Activity activity,
                                    String msgStr,
                                    String brandStr,
                                    final OnClickListener onClickListener) {
        final Dialog dialog = new Dialog(activity, R.style.successDialog);

        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_scan_success, null);
        view.findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onClickListener) {
                    onClickListener.onclick();
                }
                dialog.dismiss();
            }
        });

        SimpleDraweeView iconResult = (SimpleDraweeView) view.findViewById(R.id.icon_result);
        TextView result = (TextView) view.findViewById(R.id.result);
        TextView brand = (TextView) view.findViewById(R.id.brand);

        iconResult.setImageURI("res:///" + R.drawable.icon_real);
        result.setText(msgStr);
        if (!TextUtils.isEmpty(brandStr)) brand.setText(brandStr);

        final int viewWidth = dip2px(activity, 300);
        int windowHeight = MetricsUtil.getWindowHeight(activity);

        view.setMinimumWidth(viewWidth);
        view.setMinimumHeight(windowHeight);
        view.setFitsSystemWindows(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(view);
        dialog.show();
        return dialog;
    }

    public static Dialog showDialog(Activity activity,
                                    boolean iconResultB,
                                    String msgStr,
                                    String brandStr,
                                    final OnClickListener onClickListener) {
        final Dialog dialog = new Dialog(activity, R.style.successDialog);

        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_scan_success, null);
        view.findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onClickListener) {
                    onClickListener.onclick();
                }
                dialog.dismiss();
            }
        });

        SimpleDraweeView iconResult = (SimpleDraweeView) view.findViewById(R.id.icon_result);
        TextView result = (TextView) view.findViewById(R.id.result);
        TextView brand = (TextView) view.findViewById(R.id.brand);

        if (iconResultB) iconResult.setImageURI("res:///" + R.drawable.icon_real);
        else iconResult.setImageURI("res:///" + R.drawable.icon_fake);
        result.setText(msgStr);
        if (!TextUtils.isEmpty(brandStr)) brand.setText(brandStr);

        final int viewWidth = dip2px(activity, 300);
        int windowHeight = MetricsUtil.getWindowHeight(activity);

        view.setMinimumWidth(viewWidth);
        view.setMinimumHeight(windowHeight);
        view.setFitsSystemWindows(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(view);
        dialog.show();
        return dialog;
    }

    public interface OnClickListener {
        public abstract void onclick();
    }

    private static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
