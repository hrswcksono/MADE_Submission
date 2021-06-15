//
// Created by USER on 15/06/2021.
//

#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_dicoding_submissionmade_core_data_source_remote_RemoteDataSource_getNativeKey1(JNIEnv *env, jobject instance) {

 return (*env)->  NewStringUTF(env, "ZDI1NTYxMGM2ODlkYjgyNGU2OTYzMjRmNWM3MDVlM2I=");
}
