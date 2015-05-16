#include <string.h>
#include <android/log.h>
#include <jni.h>
#include "Operate.h"



/**
 *  Java 中 声明的native add 方法的实现
 *
 *  jint x       参数X
 *  jint y       参数Y
 */

jint Java_cn_com_itisme_filedemo_Jni_SimpleOperation_add(JNIEnv* env, jobject thiz, jint x,jint y)
{
//该方法为打印的方法
__android_log_print(ANDROID_LOG_INFO, "JNIMsg", "Get Param:  x=%d y=%d ", x,y);
return add(x,y);

}