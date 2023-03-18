#include <jni.h>
#include <string>
#include "ESP.h"
#include "Hacks.h"
//#include "Includes/obfuscate.h"
//#include "Includes/Logger.h"

ESP espOverlay;
int type=1,utype=2;
extern "C" JNIEXPORT void JNICALL
Java_com_plexus_lieshooter_Overlay_DrawOn(JNIEnv *env, jclass , jobject espView, jobject canvas) {
    espOverlay = ESP(env, espView, canvas);
    if (espOverlay.isValid()){
        DrawESP(espOverlay, espOverlay.getWidth(), espOverlay.getHeight());
    }
}
extern "C" JNIEXPORT void JNICALL
Java_com_plexus_lieshooter_Overlay_Close(JNIEnv *,  jobject ) {
    Close();
}
extern "C" JNIEXPORT jstring JNICALL
Java_com_plexus_lieshooter_Auth_URLSERVER(JNIEnv *env, jobject thiz ) {
    return env->NewStringUTF("https://t.me/JxLCHEAT_OFFICIAL");
}
extern "C" JNIEXPORT jstring JNICALL
Java_com_plexus_lieshooter_LoginActivity_MainAct(JNIEnv *env, jobject thiz ) {
    return env->NewStringUTF("com.plexus.lieshooter.LoaderActivity");
}
extern "C" JNIEXPORT jstring JNICALL
Java_com_plexus_lieshooter_FloatLogo_TITLE(JNIEnv *env, jobject thiz ) {
    return env->NewStringUTF("JxL CHEATS");
}
extern "C" JNIEXPORT jstring JNICALL
Java_com_plexus_lieshooter_LoaderActivity_titleLie(JNIEnv *env, jobject thiz ) {
    return env->NewStringUTF("JxL");
}
extern "C" JNIEXPORT jstring JNICALL
Java_com_plexus_lieshooter_LoaderActivity_titleShooter(JNIEnv *env, jobject thiz ) {
    return env->NewStringUTF("CHEATS");
}
extern "C" JNIEXPORT jstring JNICALL
Java_com_plexus_lieshooter_LoginActivity_titleLie(JNIEnv *env, jobject thiz ) {
    return env->NewStringUTF("JxL");
}
extern "C" JNIEXPORT jstring JNICALL
Java_com_plexus_lieshooter_LoginActivity_titleShooter(JNIEnv *env, jobject thiz ) {
    return env->NewStringUTF("CHEATS");
}
extern "C" JNIEXPORT jstring JNICALL
Java_com_plexus_lieshooter_LoginActivity_getkey(JNIEnv *env, jobject thiz ) {
    return env->NewStringUTF("https://t.me/JxLCHEAT_OFFICIAL");
}
extern "C" JNIEXPORT jstring JNICALL
Java_com_plexus_lieshooter_MainActivity_TITLECHECK(JNIEnv *env, jobject thiz ) {
    return env->NewStringUTF("Injector By JxL CHEATS");
}
extern "C" JNIEXPORT jstring JNICALL
Java_com_plexus_lieshooter_MainActivity_TG(JNIEnv *env, jobject thiz ) {
    return env->NewStringUTF("https://t.me/JxLCHEAT_OFFICIAL");
}
extern "C" JNIEXPORT jstring JNICALL
Java_com_plexus_lieshooter_MainActivity_WEBSITE(JNIEnv *env, jobject thiz ) {
    return env->NewStringUTF("https://t.me/JxLCHEAT_OFFICIAL");
}
extern "C" JNIEXPORT void JNICALL
Java_com_plexus_lieshooter_FloatLogo_SettingValue(JNIEnv *,  jobject ,jint code,jboolean jboolean1) {
    switch((int)code){
        case 1:
            isPlayerBox = jboolean1;   break;
        case 2:
            isPlayerLineTop = jboolean1;  break;
        case 3:
            isPlayerDist = jboolean1;  break;
        case 4:
            isPlayerHealth = jboolean1;  break;
        case 5:
            isPlayerName = jboolean1;  break;
        case 6:
            isPlayerHead = jboolean1;  break;
        case 7:
            isr360Alert = jboolean1;  break;
        case 8:
            isSkelton = jboolean1;  break;
        case 9:
            isGrenadeWarning = jboolean1;  break;
        case 10:
            isEnemyWeapon=jboolean1;  break;
        case 11:
            if(jboolean1 != 0)
                options.openState=0;
            else
                options.openState=-1;
            break;
        case 12:
            options.tracingStatus=jboolean1;
            break;
        case 13:
            options.pour=jboolean1;
            break;
        case 14:
            isPlayerBoxFill = jboolean1;   break;
        case 15:
            isPlayerLineMiddle = jboolean1;   break;
        case 16:
            isPlayerLineBottom = jboolean1;   break;
                
        case 35:
		request.memory.LessRecoil = jboolean1;
		break;
	case 36:
		request.memory.InstantHit = jboolean1;
		break;
	case 37:
		request.memory.FastShootInterval = jboolean1;
		break;
	case 38:
		request.memory.SmallCrosshair = jboolean1;
		break;
	case 39:
		request.memory.ZeroRecoil = jboolean1;
		break;
	case 40:
		request.memory.HitX = jboolean1;
		break;
	case 41:
		request.memory.NoShake = jboolean1;
		break;
	case 42:
		request.memory.FastSwitchWeapon = jboolean1;
		break;
    }
}

extern "C" JNIEXPORT void JNICALL
Java_com_plexus_lieshooter_FloatAimbot_AimbotPOV(JNIEnv *,  jobject ,jint code,jboolean jboolean1) {
    switch((int)code){
        case 11:
            if(jboolean1 != 0)
                options.openState=0;
            else
                options.openState=-1;
            break;
    }
}

extern "C" JNIEXPORT void JNICALL
Java_com_plexus_lieshooter_FloatLogo_Range(JNIEnv *,  jobject ,jint range) {
    options.aimingRange=1+range;
}
extern "C" JNIEXPORT void JNICALL
Java_com_plexus_lieshooter_FloatLogo_Target(JNIEnv *,  jobject ,jint target) {
    options.aimbotmode=target;
}
extern "C" JNIEXPORT void JNICALL
Java_com_plexus_lieshooter_FloatLogo_AimWhen(JNIEnv *,  jobject ,jint state) {
    options.aimingState=state;
}
extern "C" JNIEXPORT void JNICALL
Java_com_plexus_lieshooter_FloatLogo_AimBy(JNIEnv *,  jobject ,jint aimby) {
    options.priority=aimby;
}

extern "C" JNIEXPORT jboolean JNICALL
Java_com_plexus_lieshooter_Overlay_getReady(JNIEnv *, jclass ,int typeofgame) {
    int sockCheck=1;
    if (!Create()) {
        perror("Creation failed");
        return false;
    }
    setsockopt(sock,SOL_SOCKET,SO_REUSEADDR,&sockCheck, sizeof(int));
    if (!Bind()) {
        perror("Bind failed");
        return false;
    }

    if (!Listen()) {
        perror("Listen failed");
        return false;
    }
    if (Accept()) {
        SetValue sv{};
        sv.mode=typeofgame;
        sv.type=utype;
        send((void*)&sv,sizeof(sv));

        return true;
    }
}
int Register1(JNIEnv *env) {
    JNINativeMethod methods[] = {{"Check", "(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;", (void *) native_Check}};

    jclass clazz = env->FindClass("com/plexus/lieshooter/LoginActivity");
    if (!clazz)
        return -1;

    if (env->RegisterNatives(clazz, methods, sizeof(methods) / sizeof(methods[0])) != 0)
        return -1;

    return 0;
}

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env;
    vm->GetEnv((void **) &env, JNI_VERSION_1_6);
    if (Register1(env) != 0)
        return -1;
    return JNI_VERSION_1_6;
}

