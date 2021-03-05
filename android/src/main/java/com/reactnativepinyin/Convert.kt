package com.reactnativepinyin

abstract class Convert {

  companion object {

    fun agoraId(rtcUid: String): Int {
      val rtcId = rtcUid.toLong();
      val uid = (rtcId and 0xffffffffL)
      val id =  uid.toInt();
      return id;
    }
  }
}
