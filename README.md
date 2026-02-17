# Android-app-in-Kotlin

# Fragment
* Fragment is a piece of activity 
* One activity can have multiple fragments
* And one fragment can be reused as a component of multiple activities
* Fragment can only work inside an activity and dependent on its life cycle

# KMP
For the following error with iOS app:
```
Starting a Gradle Daemon, 2 incompatible Daemons could not be reused, use --status for details
Calculating task graph as no cached configuration is available for tasks: :shared:embedAndSignAppleFrameworkForXcode

[Incubating] Problems report is available at: file:///Users/jiangling/Workspace/Play-Android-app-with-Kotlin/kmp-migrate-room/build/reports/problems/problems-report.html

FAILURE: Build failed with an exception.

* What went wrong:
Could not resolve all artifacts for configuration 'classpath'.
> Could not resolve org.gradle.toolchains:foojay-resolver:1.0.0.
  Required by:
      unspecified:unspecified:unspecified > org.gradle.toolchains.foojay-resolver-convention:org.gradle.toolchains.foojay-resolver-convention.gradle.plugin:1.0.0
   > Dependency requires at least JVM runtime version 17. This build uses a Java 8 JVM.

* Try:
> Run this build using a Java 17 or newer JVM.
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.
> Get more help at https://help.gradle.org.

Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

For more on this, please refer to https://docs.gradle.org/8.14.3/userguide/command_line_interface.html#sec:command_line_warnings in the Gradle documentation.

BUILD FAILED in 11s
Configuration cache entry stored.
Command PhaseScriptExecution failed with a nonzero exit code
```
Add the following code to project’s gradle.properties:
```
org.gradle.java.home=/Applications/Android Studio.app/Contents/jbr/Contents/Home
```
