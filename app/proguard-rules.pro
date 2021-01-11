# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#for retrofit
# Retrofit
-keep class com.google.gson.** { *; }
-keep public class com.google.gson.** {public private protected *;}
-keep class com.google.inject.** { *; }
-keep class org.apache.http.** { *; }
-keep class org.apache.james.mime4j.** { *; }
-keep class javax.inject.** { *; }
-keep class javax.xml.stream.** { *; }
-keep class retrofit.** { *; }
-keepattributes *Annotation*
-keepattributes Signature
-dontwarn com.squareup.okhttp.*
-dontwarn rx.**
-dontwarn javax.xml.stream.**
-dontwarn com.google.appengine.**
-dontwarn java.nio.file.**
-dontwarn org.codehaus.



#for Gson
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn com.group.choloshopbd.Network.**



#for firebase
-keep class com.firebase.** { *; }

-keep class * extends androidx.fragment.app.Fragment{}
-keep class androidx.fragment.** { *; }

-keepnames class * extends android.os.Parcelable
-keepnames class * extends java.io.Serializable

-keepclassmembers class * extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite {
  <fields>;
}



-keep class * extends androidx.lifecycle.AndroidViewModel {
    <init>(android.app.Application);
}

-keepclassmembers class * implements android.arch.lifecycle.LifecycleObserver {
    <init>(...);
}

-keep class * implements android.arch.lifecycle.LifecycleObserver {
    <init>(...);
}
-keepclassmembers class android.arch.** { *; }
-keep class android.arch.** { *; }
-dontwarn android.arch.**

-keep class * implements android.arch.lifecycle.GeneratedAdapter {<init>(...);}

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
 <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}

# Uncomment for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule

-keep class com.group.etoko.Activity.** { *; }
-keep class com.group.etoko.Database.dao.** { *; }
-keep class com.group.etoko.Database.db.** { *; }
-keep class com.group.etoko.Fragment.about.** { *; }
-keep class com.group.etoko.Fragment.CategoryList.** { *; }
-keep class com.group.etoko.Fragment.Checkout.** { *; }
-keep class com.group.etoko.Fragment.HomeFragment.** { *; }
-keep class com.group.etoko.Fragment.OrderHistoryDetails.** { *; }
-keep class com.group.etoko.Fragment.productDetails.** { *; }
-keep class com.group.etoko.Fragment.ProductsGridFragment.** { *; }
-keep class com.group.etoko.model.** { *; }
-keep class com.group.etoko.Network.** { *; }
-keep class com.group.etoko.Service.** { *; }

-keep public class ** {
    public *;
    protected *;

}