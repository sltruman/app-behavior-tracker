#define LOG_TAG "LX_ASAM_COMMON"

#include "jni.h"
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <unistd.h>
#include <utils/Log.h>
#include <sys/errno.h>

jboolean Java_lx_asam_common_Datagram_send(JNIEnv *env, jclass, jbyteArray data)
{
	int sock = socket(AF_INET,SOCK_DGRAM,0);

	if(sock == -1)
	{
		ALOGE("uid:%d pid:%d %s",getuid(),getpid(),strerror(errno));
		return false;
	}

	sockaddr_in endpoint;
	endpoint.sin_family = AF_INET;
	endpoint.sin_port = htons(60001);
	endpoint.sin_addr.s_addr = inet_addr("127.0.0.1");
	
	jint length = env->GetArrayLength(data);
	jbyte* buffer = env->GetByteArrayElements(data,JNI_FALSE);

	int result = sendto(sock,buffer,length,0,(sockaddr*)&endpoint,sizeof(sockaddr));
 	env->ReleaseByteArrayElements(data,buffer,0);

 	if(result == -1)
	{
		ALOGE("uid:%d pid:%d %s",getuid(),getpid(),strerror(errno));
	}

	close(sock);
	return result != -1 ? true : false;
}

void Java_lx_asam_common_Log_e(JNIEnv* env,jclass,jbyteArray data)
{
	jbyte* buffer = env->GetByteArrayElements(data,JNI_FALSE);
	ALOGE("uid:%d pid:%d %s",getuid(),getpid(),(char*)buffer,getuid());
	env->ReleaseByteArrayElements(data,buffer,0);
}

int Java_lx_asam_common_Datagram_getpid(JNIEnv* env,jclass)
{
	return getpid();
}

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm,void* reserved)
{
	JNIEnv* env = NULL;
	JNINativeMethod nm[3];
	jclass cls;
	jint result = -1;

	if(vm->GetEnv((void**)&env,JNI_VERSION_1_4) != JNI_OK)
	{
		return JNI_ERR;
	}

	cls = env->FindClass("lx/asam/common/Datagram");
	nm[0].name = "send";
	nm[0].signature = "([B)Z";
	nm[0].fnPtr = (void*)Java_lx_asam_common_Datagram_send;
	env->RegisterNatives(cls,nm,1);

	cls = env->FindClass("lx/asam/common/Log");
	nm[1].name = "_e";
	nm[1].signature = "([B)V";
	nm[1].fnPtr = (void*)Java_lx_asam_common_Log_e;
	env->RegisterNatives(cls,nm + 1,1);

	cls = env->FindClass("lx/asam/common/Datagram");
	nm[2].name = "getpid";
	nm[2].signature = "()I";
	nm[2].fnPtr = (void*)Java_lx_asam_common_Datagram_getpid;
	env->RegisterNatives(cls,nm + 2,1);

	return JNI_VERSION_1_4;
}

