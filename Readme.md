# Mag Otp

[![](https://jitpack.io/v/magneto3572/Magotp.svg)](https://jitpack.io/#magneto3572/Magotp)

## How to integrate into your app?
Integrating the project is simple. All you need to do is follow the below steps

Step 1. Add the JitPack repository to your build file. Add it in your root build.gradle at the end of repositories:

```java
allprojects {
  repositories {
    ...
    maven { url "https://jitpack.io" }
  }
}
```
Step 2. Add the dependency
```java
dependencies {
    implementation 'com.github.magneto3572:Magotp:release_version'
}
```

Step 3. After Adding the Dependency, All you need to add OTP composable
```java
1. Use Otpfilled for Otp type where you want to set custom background drawable

 OtpComposableFilled(
            heightInDP = 50.dp,
            widthInDp = 50.dp,
            backgroundColor = Color.Transparent,
            cornerRadius =8.dp ,
            passwordToggle = false,
            automaticCapture = true,
            arrangement = Arrangement.SpaceEvenly,
            backgroundDrawable = R.drawable.ic_rectangle_background,
            modifier = Modifier.fillMaxWidth())
        {
            Log.d("LogTag", it.toString())
        }
        
 2. Use Otp outline composable for otp type where you want focus and unfocus border
 
  OtpComposableOutlined(
            widthInDp = 50.dp,
            heightInDp = 50.dp,
            backgroundColor = Color.Transparent,
            passwordToggle = false,
            focusColor = Color.Green,
            unfocusColor = Color.DarkGray,
            modifier = Modifier.fillMaxWidth())
            cornerRadius = 8.dp,)
            {
                Log.d("LogTag", it.toString())
            }

```


