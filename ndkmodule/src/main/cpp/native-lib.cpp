//
// Created by xululu on 2019-07-30.
//
#include <jni.h>
#include <string>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <opencv2/opencv.hpp>
#include <android/bitmap.h>
#include "Utils.h"
#define ASSERT(status, ret)  if(!(status)) {return ret;}
#define ASSERT_FALSE(status) ASSERT(status, false)
#define DEFAULT_CARD_WIDTH  996
#define DEFAULT_CARD_HEIGHT 600
#define FIX_ICCARD_SIZE Size(DEFAULT_CARD_WIDTH, DEFAULT_CARD_HEIGHT)

using namespace cv;
using namespace std;

extern "C" JNIEXPORT jstring JNICALL
Java_com_xululu_ndkmodule_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jintArray JNICALL
Java_com_xululu_ndkmodule_MainActivity_gray(JNIEnv* env, jobject instance,
        jintArray buf, jint w, jint h){
    jint *cbuf = env->GetIntArrayElements(buf, NULL);
    if (cbuf == NULL){
        return 0;
    }

    Mat imgData(h, w, CV_8UC4, (unsigned char *) cbuf);

    uchar* ptr = imgData.ptr(0);
    for(int i = 0; i < w*h; i ++){
        //计算公式：Y(亮度) = 0.299*R + 0.587*G + 0.114*B
        //对于一个int四字节，其彩色值存储方式为：BGRA
        int grayScale = (int)(ptr[4*i+2]*0.299 + ptr[4*i+1]*0.587 + ptr[4*i+0]*0.114);
        ptr[4*i+1] = grayScale;
        ptr[4*i+2] = grayScale;
        ptr[4*i+0] = grayScale;
    }

    int size = w * h;
    jintArray result = env->NewIntArray(size);
    env->SetIntArrayRegion(result, 0, size, cbuf);
    env->ReleaseIntArrayElements(buf, cbuf, 0);
    return result;
}

//bool Bitmap2Mat(JNIEnv *env, jobject bitmap, Mat &mat);
void BitmapToMat2(JNIEnv *env, jobject& bitmap, Mat& mat, jboolean needUnPremultiplyAlpha);
void MatToBitmap2(JNIEnv *env, Mat& mat, jobject& bitmap, jboolean needPremultiplyAlpha);
jobject createBitmap(JNIEnv *env, cv::Mat &pngimage);
extern "C"
JNIEXPORT jobject JNICALL
Java_com_xululu_ndkmodule_MainActivity_findIdNumber(JNIEnv *env, jobject instance, jobject bitmap,
                                                    jobject argb8888) {

//    //bitmap转mat
//    Mat src_img;
//    Mat dst;
//    bitmap2Mat(env, bitmap, src_img);
//
//    resize(src_img, dst, FIX_ICCARD_SIZE);
//    cvtColor(dst, dst, COLOR_RGB2GRAY);
//    blur(src_img, dst, FIX_ICCARD_SIZE);
//
//
//    jobject bitmapTest;
//    createBitmap(env, dst, bitmapTest);
    Mat src_img;
    Mat dst;
    Mat dst_img;
    vector<vector<Point>> contours;
    vector<Rect> rects;
    bitmap2Mat(env, bitmap, src_img);
    resize(src_img, src_img, FIX_ICCARD_SIZE);
    //灰度化
    cvtColor(src_img, dst, COLOR_RGB2GRAY);
    threshold(dst, dst, 100, 255, THRESH_BINARY);
    Mat erodeElement = getStructuringElement(MORPH_RECT, Size(20, 10));
    erode(dst, dst, erodeElement);
    findContours(dst, contours, RETR_TREE, CHAIN_APPROX_SIMPLE, Point(0,0));
    for (int i = 0; i < contours.size(); ++i) {
        Rect rect = boundingRect(contours.at(i));
        if (rect.width>rect.height*10 && rect.width<rect.height*16){
            rects.push_back(rect);
        }

    }

    int lowPoint = 0;
    Rect finalRect;
    for (int i = 0; i <rects.size(); i++) {
        Rect rect = rects.at(i);
        Point point = rect.tl();
        if (point.y>lowPoint) {
            lowPoint = point.y;
            finalRect = rect;
        }
    }

    dst = src_img(finalRect);
//    rectangle(dst, finalRect, Scalar(0,0,255));
    jobject dstBitmap = createBitmap(env, dst);
    createBitmap2(env, dst, dstBitmap);

    return dstBitmap;

}

void BitmapToMat2(JNIEnv *env, jobject& bitmap, Mat& mat, jboolean needUnPremultiplyAlpha) {
    AndroidBitmapInfo info;
    void *pixels = 0;
    Mat &dst = mat;

    try {
        CV_Assert(AndroidBitmap_getInfo(env, bitmap, &info) >= 0);
        CV_Assert(info.format == ANDROID_BITMAP_FORMAT_RGBA_8888 ||
                  info.format == ANDROID_BITMAP_FORMAT_RGB_565);
        CV_Assert(AndroidBitmap_lockPixels(env, bitmap, &pixels) >= 0);
        CV_Assert(pixels);
        dst.create(info.height, info.width, CV_8UC4);
        if (info.format == ANDROID_BITMAP_FORMAT_RGBA_8888) {
            Mat tmp(info.height, info.width, CV_8UC4, pixels);
            if (needUnPremultiplyAlpha) cvtColor(tmp, dst, COLOR_mRGBA2RGBA);
            else tmp.copyTo(dst);
        } else {
//             info.format == ANDROID_BITMAP_FORMAT_RGB_565
            Mat tmp(info.height, info.width, CV_8UC2, pixels);
            cvtColor(tmp, dst, COLOR_BGR5652RGBA);
        }
        AndroidBitmap_unlockPixels(env, bitmap);
        return;
    } catch (const cv::Exception &e) {
        AndroidBitmap_unlockPixels(env, bitmap);
        jclass je = env->FindClass("org/opencv/core/CvException");
        if (!je) je = env->FindClass("java/lang/Exception");
        env->ThrowNew(je, e.what());
        return;
    } catch (...) {
        AndroidBitmap_unlockPixels(env, bitmap);
        jclass je = env->FindClass("java/lang/Exception");
        env->ThrowNew(je, "Unknown exception in JNI code {nBitmapToMat}");
        return;
    }

}


void MatToBitmap2(JNIEnv *env, Mat& mat, jobject& bitmap, jboolean needPremultiplyAlpha) {
    AndroidBitmapInfo info;
    void *pixels = 0;
    Mat &src = mat;

    try {
        CV_Assert(AndroidBitmap_getInfo(env, bitmap, &info) >= 0);
        CV_Assert(info.format == ANDROID_BITMAP_FORMAT_RGBA_8888 ||
                  info.format == ANDROID_BITMAP_FORMAT_RGB_565);
        CV_Assert(src.dims == 2 && info.height == (uint32_t) src.rows &&
                  info.width == (uint32_t) src.cols);
        CV_Assert(src.type() == CV_8UC1 || src.type() == CV_8UC3 || src.type() == CV_8UC4);
        CV_Assert(AndroidBitmap_lockPixels(env, bitmap, &pixels) >= 0);
        CV_Assert(pixels);
        if (info.format == ANDROID_BITMAP_FORMAT_RGBA_8888) {
            Mat tmp(info.height, info.width, CV_8UC4, pixels);
            if (src.type() == CV_8UC1) {
                cvtColor(src, tmp, COLOR_GRAY2RGBA);
            } else if (src.type() == CV_8UC3) {
                cvtColor(src, tmp, COLOR_RGB2RGBA);
            } else if (src.type() == CV_8UC4) {
                if (needPremultiplyAlpha)
                    cvtColor(src, tmp, COLOR_RGBA2mRGBA);
                else
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
        AndroidBitmap_unlockPixels(env, bitmap);
        return;
    } catch (const cv::Exception &e) {
        AndroidBitmap_unlockPixels(env, bitmap);
        jclass je = env->FindClass("org/opencv/core/CvException");
        if (!je) je = env->FindClass("java/lang/Exception");
        env->ThrowNew(je, e.what());
        return;
    } catch (...) {
        AndroidBitmap_unlockPixels(env, bitmap);
        jclass je = env->FindClass("java/lang/Exception");
        env->ThrowNew(je, "Unknown exception in JNI code {nMatToBitmap}");
        return;
    }
}


jobject createBitmap(JNIEnv *env, cv::Mat &pngimage) {
    // Image Details
    int imgWidth = pngimage.cols;
    int imgHeight = pngimage.rows;
    int numPix = imgWidth * imgHeight;
    // Creaing Bitmap Config Class
    jclass bmpCfgCls = env->FindClass("android/graphics/Bitmap$Config");
    jmethodID bmpClsValueOfMid = env->GetStaticMethodID(bmpCfgCls, "valueOf",
                                                        "(Ljava/lang/String;)Landroid/graphics/Bitmap$Config;");
    jobject jBmpCfg = env->CallStaticObjectMethod(bmpCfgCls, bmpClsValueOfMid,
                                                  env->NewStringUTF("RGB_565" /*or*/ /*"ARGB_8888"*/));
    // Creating a Bitmap Class
    jclass bmpCls = env->FindClass("android/graphics/Bitmap");
    jmethodID createBitmapMid = env->GetStaticMethodID(bmpCls, "createBitmap",
                                                       "(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;");
    return env->CallStaticObjectMethod(bmpCls, createBitmapMid, imgWidth, imgHeight, jBmpCfg);
}

