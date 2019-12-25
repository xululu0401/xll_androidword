# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.
#set(pathToOpenCv /Users/woodstream/Documents/dev/projects/android/OpenCV-android-sdk)#设置OpenCv的路径变量
cmake_minimum_required(VERSION 3.4.1)
#支持-std=gnu++11
set(CMAKE_VERBOSE_MAKEFILE on)
#set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")
#配置加载native依赖
include_directories(${PROJECT_SOURCE_DIR}/include)
#动态方式加载
add_library(lib_opencv SHARED IMPORTED ) #表示创建一个导入库
#引入libopencv_java3.so文件
set_target_properties(lib_opencv
        PROPERTIES
        IMPORTED_LOCATION /Users/xululu/project/AndroidStudioProjects/codepractice/MyNDKTest/app/src/main/jniLibs/${ANDROID_ABI}/libopencv_java4.so
        )

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
#自己的源文件
add_library(native-lib SHARED ${PROJECT_SOURCE_DIR}/native-lib.cpp ${PROJECT_SOURCE_DIR}/Utils.cpp)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        native-lib


        # Links the target library to the log library
        # included in the NDK.
        ${log-lib} #加入该依赖库
        jnigraphics lib_opencv)