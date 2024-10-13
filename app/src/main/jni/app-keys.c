#include <jni.h>  //For first API key JNIEXPORT jstring JNICALL

JNIEXPORT jstring JNICALL
Java_com_demo_demotask_data_dataSource_remote_source_RemoteDataSourceImp_getAPIKey(JNIEnv *env, jobject thiz) {
    return (*env)->  NewStringUTF(env, "36fcd6077339f78457e3ee5d44dba41a");
}
