package itisme.com.cn.filedemo.Jni;

/**
 * Created by zhao on 5/9/15.
 */
public class SimpleOperation {
    private static final String libSoName = "NDK_03";
    /**
     * 该方法为native方法.
     *
     *         实现加法功能
     *
     * @param x    加数
     * @param y 加数
     *
     * @return x+y 的结果
     */
    public  native int add(int x, int y);

    /**
     * 载入JNI生成的so库文件
     */
    static {
        System.loadLibrary(libSoName);
    }

}
