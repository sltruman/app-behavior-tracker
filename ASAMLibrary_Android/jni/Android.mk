LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_SRC_FILES := \
    lxasamcommon.cpp

LOCAL_MODULE := lxasamcommon

LOCAL_C_INCLUDES += \
    $(JNI_H_INCLUDE)

LOCAL_SHARED_LIBRARIES := \
    liblog \
    libcutils \
    libutils

include $(BUILD_SHARED_LIBRARY)
