//
//  ContentView.swift
//  KMPGetStartedCodelab
//
//

import SwiftUI
import sharedKit

struct ContentView: View {
    var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundStyle(.tint)
            Text("KMP on\n" +
                 "\(platform())\n" +
                 "\(Platform_iosKt.platform())\n" +
                 "\(getPlatform().name)\n" +
                 "\(getFakePlatform().name)\n" +
                 "\(randomUUID())")
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
