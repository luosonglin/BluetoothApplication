package com.kaifabang.bluetoothapplication;

import android.app.Application;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luosonglin on 6/16/18.
 */

public class BaseApplication extends Application {
    private static final String TAG = "BaseApplication";
    private static Context context;
    //记录当前栈里所有activity
    private List<Activity> activities = new ArrayList<>();
    //记录需要一次性关闭的页面
    private List<Activity> activitys = new ArrayList<>();

    @Override
    public void onCreate() {
        Log.i(TAG, "BlueApplication is running~~~~~~~hahaha~~~~!");
        super.onCreate();
        context = this;
        instance = this;

        //相应的准备工作
        Toast.makeText(this, "hhhhh", Toast.LENGTH_LONG).show();


    }

    public static Context getContext() {
        return context;
    }

    /**
     * 应用实例
     */
    private static BaseApplication instance;

    /**
     * 获得实例
     *
     * @return
     */
    public static BaseApplication getInstance() {
        return instance;
    }

    /**
     * 新建了一个activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            this.activities.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 给临时Activitys添加activity
     */
    public void addTemActivity(Activity activity) {
        activitys.add(activity);
    }

    public void finishTemActivity(Activity activity) {
        if (activity != null) {
            this.activitys.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 退出指定的Activity
     */
    public void exitActivitys() {
        for (Activity activity : activitys) {
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /**
     * 应用退出，结束所有的activity
     */
    public void exit() {
        for (Activity activity : activities) {
            if (activity != null) {
                activity.finish();
            }
        }
        System.exit(0);
    }
}