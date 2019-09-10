//
// Created by xululu on 2019-07-31.
//

#include "Utils.h"
#include <android/log.h>

#include <jni.h>
#include <string>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <opencv2/opencv.hpp>
#include <android/bitmap.h>
#include "Utils.h"


#define ASSERT(status, ret)  if(!(status)) {return ;}
#define ASSERT_FALSE(status) ASSERT(status, false)

#define ASSERT1(status, ret)  if(!(status)) {return NULL;}
#define ASSERT_FALSE1(status) ASSERT1(status, false)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, "llxu4error", __VA_ARGS__)
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, "llxu4debug", __VA_ARGS__)
using namespace cv;

void bitmap2Mat(JNIEnv *env, jobject &bitmap, Mat &mat){
    void * bitmapPixels;
    AndroidBitmapInfo bitmapInfo;


    ASSERT_FALSE(AndroidBitmap_getInfo(env, bitmap, &bitmapInfo)>=0);
    ASSERT_FALSE(bitmapInfo.format == ANDROID_BITMAP_FORMAT_RGBA_8888
                 || bitmapInfo.format == ANDROID_BITMAP_FORMAT_RGB_565);

    ASSERT_FALSE( AndroidBitmap_lockPixels(env, bitmap, &bitmapPixels) >= 0 );  // 获取图片像素（锁定内存块）
    ASSERT_FALSE( bitmapPixels );

    if (bitmapInfo.format == ANDROID_BITMAP_FORMAT_RGBA_8888){
        Mat tmp(bitmapInfo.height, bitmapInfo.width, CV_8UC4, bitmapPixels);
        tmp.copyTo(mat);
    } else {
        Mat tmp(bitmapInfo.height, bitmapInfo.width, CV_8UC2, bitmapPixels);
        cvtColor(tmp, mat, COLOR_BGR5652RGB);
    }

    AndroidBitmap_unlockPixels(env, bitmap);
}

jobject createBitmap2(JNIEnv *env, Mat &srcData, jobject& obj_bitmap) {
    AndroidBitmapInfo info;
    void *pixels = 0;
    Mat &src = srcData;

    try {
        ASSERT_FALSE1(AndroidBitmap_getInfo(env, obj_bitmap, &info) >= 0);
        ASSERT_FALSE1(info.format == ANDROID_BITMAP_FORMAT_RGBA_8888 ||
                  info.format == ANDROID_BITMAP_FORMAT_RGB_565);
//        ASSERT_FALSE1(src.dims == 2 && info.height == (uint32_t) src.rows &&
//                  info.width == (uint32_t) src.cols);
        ASSERT_FALSE1(src.type() == CV_8UC1 || src.type() == CV_8UC3 || src.type() == CV_8UC4);
        ASSERT_FALSE1(AndroidBitmap_lockPixels(env, obj_bitmap, &pixels) >= 0);
        ASSERT_FALSE1(pixels);
        if (info.format == ANDROID_BITMAP_FORMAT_RGBA_8888) {
            Mat tmp(info.height, info.width, CV_8UC4, pixels);
            if (src.type() == CV_8UC1) {
                cvtColor(src, tmp, COLOR_GRAY2RGBA);
            } else if (src.type() == CV_8UC3) {
                cvtColor(src, tmp, COLOR_RGB2RGBA);
            } else if (src.type() == CV_8UC4) {
//                if (needPremultiplyAlpha)
//                    cvtColor(src, tmp, COLOR_RGBA2mRGBA);
//                else
                    src.copyTo(tmp);
            }
        } else {
            // info.format == ANDROID_BITMAP_FORMAT_RGB_565
            Mat tmp(info.height, info.width, CV_8UC2, pixels);
            if (src.type() == CV_8UC1) {
                cvtColor(src, tmp, COLOR_GRAY2BGR565);
            } else if (src.type() == CV_8UC3) {
                cvtColor(src, tmp, COLOR_RGB2BGR565);
            } else if (src.type() == CV_8UC4) {
                cvtColor(src, tmp, COLOR_RGBA2BGR565);
            }
        }
        AndroidBitmap_unlockPixels(env, obj_bitmap);
        return NULL;
    } catch (const cv::Exception &e) {
        AndroidBitmap_unlockPixels(env, obj_bitmap);
        jclass je = env->FindClass("org/opencv/core/CvException");
        if (!je) je = env->FindClass("java/lang/Exception");
        env->ThrowNew(je, e.what());
        return NULL;
    } catch (...) {
        AndroidBitmap_unlockPixels(env, obj_bitmap);
        jclass je = env->FindClass("java/lang/Exception");
        env->ThrowNew(je, "Unknown exception in JNI code {nMatToBitmap}");
        return NULL;
    }
}
