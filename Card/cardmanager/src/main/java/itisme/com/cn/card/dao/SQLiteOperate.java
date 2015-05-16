package itisme.com.cn.card.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import itisme.com.cn.card.utils.StaticMethod;

/**
 * Created by zhao on 5/15/15.
 */
public class SQLiteOperate implements ISQLiteOperate {
    private static final String TAG = "SQLiteOperate";
    private static final String CALL_ = "call_";
    private static final String CALL_RULES_ = "call_rules_";
    private static final String TABLE_NAME = "rules";

    AssetsDatabaseManager manager;
    private SQLiteDatabase database;

    public SQLiteOperate(String dbName) {
        manager = AssetsDatabaseManager.getManager();
        database = manager.getDatabase(dbName);
    }

    /**
     * 获得当前最大的ID
     *
     * @param tableName
     * @return
     */
    @Override
    public long getMaxId(String tableName) {
        long result = -1;
        Cursor cursor = database.query(tableName, new String[]{"_id"}, null, null, null, null, "_id asc", null);
        if (cursor.getCount() == 0) {
            return result;
        } else {
            cursor.moveToLast();
            result = cursor.getInt(cursor.getColumnIndex("_id"));
        }
        if (cursor != null) {
            cursor.close();
        }
        return result;
    }

    /**
     * 根据sql 语句插入一条数据
     *
     * @param sqlString
     * @return
     */
    @Override
    public boolean insertItems(String tableName, String sqlString) {
        Log.i(TAG, tableName + "  " + sqlString);
        database.execSQL(configureSqlString(tableName, sqlString));
        return true;
    }


    private String configureSqlString(String tableName, String sqlContent) {
        String sql = null;
        if (tableName == null) {
            sql = "insert into " + TABLE_NAME + " values (" + (getMaxId(tableName) + 1) + sqlContent + ")";
        } else {
            sql = "insert into " + tableName + " values (" + (getMaxId(tableName) + 1) + sqlContent + ")";
        }
        return sql;
    }

    /**
     * 根据筛选条件获得内容
     *
     * @param whereString
     * @param itemNumber
     * @return
     */
    @Override
    public String getContent(String whereString, int itemNumber) {
        String call_rules_number = CALL_RULES_;
        String result = "";
        if (StaticMethod.isGreateThanNine(itemNumber)) {
            call_rules_number += itemNumber;
        } else {
            call_rules_number += 0;
            call_rules_number += itemNumber;
        }
        Cursor cursor = database.query(TABLE_NAME, new String[]{call_rules_number}, whereString, null, null, null, null, null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            result = cursor.getString(cursor.getColumnIndex(call_rules_number));
        }
        if (cursor != null) {
            cursor.close();
        }
        if (result == null) {
            result = "";
        }
        Log.i(TAG, whereString + "  " + result);
        return result;

    }

    @Override
    public List<List<String>> getAllRules(String tableName) {
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null, null);
        List<List<String>> lists = new ArrayList<List<String>>();
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }
        for (int i = 0; i < cursor.getCount(); i++) {
            List<String> list = new ArrayList<String>();
            if (cursor.moveToNext()) {

                for (int j = 1; j <= 40; j++) {
                    String str = "";
                    str += "call_";
                    if (StaticMethod.isGreateThanNine(j)) {
                        str += j;
                    } else {
                        str += 0;
                        str += j;
                    }
                    list.add(cursor.getString(cursor.getColumnIndex(str)));
                }
            }
            lists.add(list);
        }
        if(cursor!=null){
            cursor.close();
        }

        return lists;
    }
    @Override
    public List<List<String>> getAllContent(String tableName) {
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null, null);
        List<List<String>> lists = new ArrayList<List<String>>();
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }
        for (int i = 0; i < cursor.getCount(); i++) {
            List<String> list = new ArrayList<String>();
            if (cursor.moveToNext()) {
                for (int j = 1; j <= 40; j++) {
                    String str = "";
                    str += "call_rules_";
                    if (StaticMethod.isGreateThanNine(j )) {
                        str += j;
                    } else {
                        str += 0;
                        str += j;
                    }
                    Log.i(TAG,str);
                    list.add(cursor.getString(cursor.getColumnIndex(str)));
                }
            }
            lists.add(list);
        }
        if(cursor!=null){
            cursor.close();
        }
        return lists;
    }



}
