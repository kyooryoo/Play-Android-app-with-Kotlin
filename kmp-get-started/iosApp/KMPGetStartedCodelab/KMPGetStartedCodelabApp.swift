//
//  KMPGetStartedCodelabApp.swift
//  KMPGetStartedCodelab
//
//

import SwiftUI
import sharedKit

@main
struct KMPGetStartedCodelabApp: App {

    init() {
        PlatformKt.setupApp(platform: IOSPlatform())
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
