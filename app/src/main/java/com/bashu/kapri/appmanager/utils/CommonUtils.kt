package com.bashu.kapri.appmanager.utils

object CommonUtils {
    private val list = listOf(
        "TikTok".toLowerCase(),
        "Shareit".toLowerCase(),
        "Kwai".toLowerCase(),
        "UC Browser".toLowerCase(),
        "Baidu map".toLowerCase(),
        "Shein".toLowerCase(),
        "Clash of Kings".toLowerCase(),
        "DU battery saver".toLowerCase(),
        "Helo".toLowerCase(),
        "Likee".toLowerCase(),
        "YouCam makeup".toLowerCase(),
        "Mi Community".toLowerCase(),
        "CM Browers".toLowerCase(),
        "Virus Cleaner".toLowerCase(),
        "APUS Browser".toLowerCase(),
        "ROMWE".toLowerCase(),
        "Club Factory".toLowerCase(),
        "Newsdog".toLowerCase(),
        "Beutry Plus".toLowerCase(),
        "WeChat".toLowerCase(),
        "UC News".toLowerCase(),
        "QQ Mail".toLowerCase(),
        "Weibo".toLowerCase(),
        "Xender".toLowerCase(),
        "QQ Music".toLowerCase(),
        "QQ Newsfeed".toLowerCase(),
        "Bigo Live".toLowerCase(),
        "SelfieCity".toLowerCase(),
        "Mail Mast ".toLowerCase(),
        "Parallel Space".toLowerCase(),
        "Mi Video Call – Xiaomi".toLowerCase(),
        "WeSync".toLowerCase(),
        "ES File Explorer".toLowerCase(),
        "Viva Video – QU Video Inc".toLowerCase(),
        "Meitu".toLowerCase(),
        "Vigo Video".toLowerCase(),
        "New Video Status".toLowerCase(),
        "DU Recorder".toLowerCase(),
        "Vault- Hide".toLowerCase(),
        "Cache Cleaner DU App studio".toLowerCase(),
        "DU Cleaner".toLowerCase(),
        "DU Browser".toLowerCase(),
        "Hago Play With New Friends".toLowerCase(),
        "Cam Scanner".toLowerCase(),
        "Clean Master – Cheetah Mobile".toLowerCase(),
        "Wonder Camera".toLowerCase(),
        "Photo Wonder".toLowerCase(),
        "QQ Player".toLowerCase(),
        "We Meet".toLowerCase(),
        "Sweet Selfie".toLowerCase(),
        "Baidu".toLowerCase()
    )


    private val dangerousPermission = listOf(
        "READ_CALENDAR",
        "WRITE_CALENDAR",
        "CAMERA",
        "READ_CONTACTS",
        "WRITE_CONTACTS",
        "GET_ACCOUNTS",
        "ACCESS_FINE_LOCATION",
        "ACCESS_COARSE_LOCATION",
        "RECORD_AUDIO",
        "READ_PHONE_STATE",
        "READ_PHONE_NUMBERS",
        "CALL_PHONE",
        "ANSWER_PHONE_CALLS",
        "READ_CALL_LOG",
        "WRITE_CALL_LOG",
        "ADD_VOICEMAIL",
        "USE_SIP",
        "PROCESS_OUTGOING_CALLS",
        "BODY_SENSORS",
        "SEND_SMS",
        "RECEIVE_SMS",
        "READ_SMS",
        "RECEIVE_WAP_PUSH",
        "RECEIVE_MMS",
        "READ_EXTERNAL_STORAGE",
        "WRITE_EXTERNAL_STORAGE"
    )

    fun isAppMatched(name: String): Boolean {
        return list.contains(name.toLowerCase())
    }

    fun isDangerous(permission: String): Boolean {
        dangerousPermission.forEach {
            if (permission.contains(it)) {
                return true
            }
        }
        return false
    }


    /*fun isEmulator(context: Context): Boolean {
        val androidId = Secure.getString(context.contentResolver, "android_id")
        return "sdk" == Build.PRODUCT || "google_sdk" == Build.PRODUCT || androidId == null
    }

    fun isRooted(context: Context): Boolean {
        try {
            val emulator = isEmulator(context)
            val buildTags = Build.TAGS
            return if (!emulator && buildTags != null && buildTags.contains("test-keys")) {
                true
            } else {
                var file = File("/system/app/Superuser.apk")
                if (file.exists()) {
                    true
                } else {
                    !emulator && File("/system/xbin/su").exists()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return false
    }

    fun isDebuggerAttached(): Boolean {
        return Debug.isDebuggerConnected() || Debug.waitingForDebugger()
    }

    fun getDeviceState(context: Context): Int {
        var deviceState = 0
        if (isEmulator(context)) {
            deviceState = deviceState or 1
        }
        if (isRooted(context)) {
            deviceState = deviceState or 2
        }
        if (isDebuggerAttached()) {
            deviceState = deviceState or 4
        }
        return deviceState
    }

    fun getBatteryVelocity(
        context: Context,
        powerConnected: Boolean
    ): Int {
        val batteryLevel: Float? = getBatteryLevel(context)
        return if (powerConnected && batteryLevel != null) {
            if (batteryLevel >= 99.0) {
                3
            } else {
                if (batteryLevel < 99.0)
                    2
                else
                    0
            }
        } else {
            1
        }
    }

    private fun getBatteryLevel(context: Context): Float? {
        val filter = IntentFilter("android.intent.action.BATTERY_CHANGED")
        val battery = context.registerReceiver(null as BroadcastReceiver?, filter)
        return if (battery == null) {
            null
        } else {
            val level = battery.getIntExtra("level", -1)
            val scale = battery.getIntExtra("scale", -1)
            level.toFloat() / scale.toFloat()
        }
    }*/
}