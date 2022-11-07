#include <jni.h>

#ifndef _Included_io_fouad_systemd_SystemDNotifier
#define _Included_io_fouad_systemd_SystemDNotifier

extern "C" {
    JNIEXPORT jint JNICALL Java_io_fouad_systemd_SystemDNotifier_sdNotify(JNIEnv *, jclass, jstring);
}

#endif
