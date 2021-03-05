package com.reactnativepinyin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import androidx.annotation.Nullable;

import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.github.promeg.pinyinhelper.Pinyin;
import com.github.promeg.tinypinyin.lexicons.java.cncity.CnCityDict;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static java.lang.Integer.parseInt;

public class PinyinModule extends ReactContextBaseJavaModule{

  public   static  final  String Key_Pref = "Pinyin";
  public   static  final  String Value_Pref = "com.reactnativepinyin";

  public PinyinModule(ReactApplicationContext context) {
    super(context);
  }

  @Override
  public String getName() {
    return "PinyinModule";
  }
  @Override
  public Map<String, Object> getConstants() {
    final Map<String, Object> constants = new HashMap<>();
    constants.put(Key_Pref, Value_Pref);
    return constants;
  }

  public boolean canOverrideExistingModule() {
    return true;
  }


    @ReactMethod
  public void init(Promise promise) {
    try {
      Pinyin.init(Pinyin.newConfig().with(CnCityDict.getInstance()));
      promise.resolve(true);
    } catch (Exception exception) {
      promise.reject(exception);
    }
  }
  @ReactMethod
  public void toPinyin(String str, String separator,Promise promise) {
    try {
     String result =  Pinyin.toPinyin(str,separator);
      result = result.toLowerCase();
      promise.resolve(result);
    } catch (Exception exception) {
      promise.reject(exception);
    }
  }

  @ReactMethod
  public void toAgoraUid(String rtcUid,Promise promise) {
    try {
      int result = Convert.Companion.agoraId(rtcUid);
      promise.resolve(result);
    } catch (Exception exception) {
      promise.reject(exception);
    }
  }

//  @ReactMethod
//  public void toPinyinC(char c,Promise promise) {
//    try {
//      String result =  Pinyin.toPinyin(c);
//      promise.resolve(result);
//    } catch (Exception exception) {
//      promise.reject(exception);
//    }
//  }
//
//  @ReactMethod
//  public void isChinese(char c,Promise promise) {
//    try {
//      boolean result =  Pinyin.isChinese(c);
//      promise.resolve(result);
//    } catch (Exception exception) {
//      promise.reject(exception);
//    }
//  }
}
