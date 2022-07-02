# Mag Otp

[![Build Status](https://travis-ci.com/phplicengine/bitly.svg?branch=master)](https://travis-ci.com/phplicengine/bitly)
[![](https://jitpack.io/v/magneto3572/Magotp.svg)](https://jitpack.io/#magneto3572/Magotp)
[![Latest Stable Version](https://img.shields.io/packagist/v/phplicengine/bitly?label=version)](https://packagist.org/packages/phplicengine/bitly)


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

a. Use Otp filled for Otp type where you want to set custom background drawable

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
        
 b. Use Otp outline composable for otp type where you want focus and unfocus border
 
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
## Features and property

## Automatic Otp capture(Dafault false) :-

1. automaticCapture = true,  // True when you want to detect otp automatically with user consent
2. automaticCapture = false, // By default it is false

## Size :-

1. widthInDp = 50.dp,  // Size in dp for otp box size
2. heightinDp = 50.dp, // Size in dp for otp box size

## Color :-

1.backgroundColor = Color.Transparent, // for transparent background else your color choice for box background
2.focusColor = Color.Green,  // border Color when user on active box
3.unfocusColor = Color.DarkGray, // border Color when user not on active box

## Password Toggle :-

1. passwordToggle = false, // if you want to display digit on box
2. passwordToggle = true, // if you want to display dot in box for privacy

## Modifier :-

1. modifier = Modifier.fillMaxWidth()) //Parent Row modfier

## Arrangment :-

1.arrangement = Arrangement.SpaceEvenly, // Arrangement in parent row

## Corner Radius :-

1.cornerRadius = 8.dp // Border radius for OTP box

## OnvalueChange:-

1. Return the string when the all the box is filled

## OTP composable Type :-

1. otpComposableType = ComposableType.TYPE_FOUR // When you want otp composable with four boxes
2. otpComposableType = ComposableType.TYPE_SIX // When you want otp composable with six boxes


