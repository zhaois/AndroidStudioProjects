package itisme.com.cn.card;

import android.app.Application;

import itisme.com.cn.card.dao.AssetsDatabaseManager;

/**
 * Created by zhao on 5/14/15.
 */
public class CardApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AssetsDatabaseManager.initManager(CardApplication.this);
    }
}
