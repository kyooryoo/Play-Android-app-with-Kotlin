package com.test.shared

import platform.Foundation.NSUUID
import platform.UIKit.UIDevice

actual fun platform() = UIDevice.currentDevice.systemName() + " " +
    UIDevice.currentDevice.systemVersion

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " +
            UIDevice.currentDevice.systemVersion
}
actual fun getPlatform(): Platform = IOSPlatform()

class FakeIOSPlatform: Platform {
    override val name: String = "Fake iOS 999"
}
actual fun getFakePlatform(): Platform = FakeIOSPlatform()

actual fun randomUUID(): String = NSUUID().UUIDString()