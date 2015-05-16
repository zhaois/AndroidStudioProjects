package itisme.com.cn.card.dao;


import java.util.List;

/**
 * Created by zhao on 5/15/15.
 */
public interface ISQLiteOperate {
    public long getMaxId(String tableName);
    public boolean insertItems(String tableName,String sqlString);
    public String getContent(String whereString,int itemNumber);
    public List<List<String>> getAllRules(String tableName);
    public List<List<String>> getAllContent(String tableName);
}
