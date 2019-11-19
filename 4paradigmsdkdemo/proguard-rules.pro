# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-optimizationpasses 5
-dontusemixedcaseclassnames #【混淆时不会产生形形色色的类名 】
-dontskipnonpubliclibraryclasses #【指定不去忽略非公共的库类。 】
-dontpreverify #【不预校验】
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/* #【优化】
-keep public class * extends android.app.Activity　　#【不进行混淆保持原样】
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService


-keep public abstract interface *{
   public protected <methods>;  #【所有方法不进行混淆】
}



-keep public class com.sdk.a4paradigm.AdTagManager{
 public protected <methods>;  #【所有方法不进行混淆】
}
#-keepclasseswithmembernames class * { #【保护指定的类和类的成员的名称，如果所有指定的类成员出席（在压缩步骤之后）】
#native <methods>;
#}
-keepclasseswithmembers class * { #【保护指定的类和类的成员，但条件是所有指定的类和类成员是要存在。】
public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity {#【保护指定类的成员，如果此类受到保护他们会保护的更好 】
public void *(android.view.View);
}
-keepclassmembers enum * {
public static **[] values();
public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable {#【保护指定的类文件和类的成员】
public static final android.os.Parcelable$Creator *;
}
#不混淆指定包下的类
-keep class com.sdk.a4paradigm.view.**{
}

#不混淆指定包下的类
-keep class com.sdk.a4paradigm.model.**{
}
