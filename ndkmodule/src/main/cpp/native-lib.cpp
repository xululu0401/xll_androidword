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

extern "C"
JNIEXPORT jobject JNICALL
Java_com_xululu_ndkmodule_MainActivity_findIdNumber(JNIEnv *env, jobject instance, jobject bitmap) {
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
    createBitmap(env, dst, dstBitmap);

    return dstBitmap;

}

extern "C"
JNIEXPORT jobject JNICALL
Java_com_xululu_ndkmodule_MainActivity_getResizedBitmap(JNIEnv *env, jobject instance, jobject bitmap) {

    Mat src_img;
    bitmap2Mat(env, bitmap, src_img);
    resize(src_img, src_img, FIX_ICCARD_SIZE);
    jobject dstBitmap = createBitmap(env, src_img);
    createBitmap(env, src_img, dstBitmap);
    return dstBitmap;
}

extern "C"
JNIEXPORT jobject JNICALL
Java_com_xululu_ndkmodule_MainActivity_getBinaryBitmap(JNIEnv *env, jobject instance, jobject bitmap) {

    Mat src_img;
    Mat dst;
    bitmap2Mat(env, bitmap, src_img);

    cvtColor(src_img, dst, COLOR_RGB2GRAY);
    threshold(dst, dst, 100, 255, THRESH_BINARY);
    jobject dstBitmap = createBitmap(env, dst);
    createBitmap(env, dst, dstBitmap);
    return dstBitmap;
}

extern "C"
JNIEXPORT jobject JNICALL
Java_com_xululu_ndkmodule_MainActivity_getErodeBitmap(JNIEnv *env, jobject instance, jobject bitmap){
    Mat src_img;
    Mat dst;
    bitmap2Mat(env, bitmap, src_img);

    Mat erodeElement = getStructuringElement(MORPH_RECT, Size(20, 10));
    erode(src_img, src_img, erodeElement);

    jobject dstBitmap = createBitmap(env, src_img);
    createBitmap(env, src_img, dstBitmap);
    return dstBitmap;

}

extern "C"
JNIEXPORT jobject JNICALL
Java_com_xululu_ndkmodule_MainActivity_getResultBitmap(JNIEnv * env, jobject jobj, jobject bitmap, jobject srcBitmap){
    Mat src_img;
    Mat dst;
    bitmap2Mat(env, bitmap, dst);
    vector<vector<Point>> contours;
    vector<Rect> rects;
    bitmap2Mat(env, srcBitmap, src_img);
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
    jobject dstBitmap = createBitmap(env, dst);
    createBitmap(env, dst, dstBitmap);

    return dstBitmap;
}

