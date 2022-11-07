#include <systemd-notify.h>
#include <systemd/sd-daemon.h>

JNIEXPORT jint JNICALL Java_io_fouad_systemd_SystemDNotifier_sdNotify(JNIEnv* env, jclass thisClass, jstring state) {
    const char *stateChars = env->GetStringUTFChars(state, 0);
    return sd_notify(0, stateChars);
}
