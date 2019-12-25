//
// Created by xululu on 2019-07-31.
//

#ifndef ANDROIDWROLD_UTILS_H
#define ANDROIDWROLD_UTILS_H


#include <android/bitmap.h>
#include <opencv2/opencv.hpp>
#include "../../../../../../../../Library/Android/sdk/ndk-bundle/toolchains/llvm/prebuilt/darwin-x86_64/sysroot/usr/include/jni.h"
using namespace cv;

void bitmap2Mat(JNIEnv *env, jobject &bitmap, Mat &mat);

void mat2Bitmap(JNIEnv *env, Mat mat, jobject bitmap, bool needPremultiplyAlpha = 0);

jobject createBitmap(JNIEnv *env, Mat &srcData, jobject &obj_bitmap);

jobject createBitmap(JNIEnv *env, cv::Mat &pngimage);


#endif //ANDROIDWROLD_UTILS_H
