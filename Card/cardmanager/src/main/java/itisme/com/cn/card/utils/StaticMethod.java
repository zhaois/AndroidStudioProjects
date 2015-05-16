package itisme.com.cn.card.utils;

/**
 * Created by zhao on 5/15/15.
 */
public class StaticMethod {
    public static   boolean isEmpty(String string) {
        if (string == null || string.equals("") || string.equals("null")) {
            return true;
        } else {
            return false;
        }
    }
    public  static boolean isGreateThanNine(int number){
        if(number>9){
            return true;
        }else {
            return false;
        }
    }
}
