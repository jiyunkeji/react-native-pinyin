import { NativeModules } from 'react-native';

const { PinyinModule } = NativeModules;

export default class PinyinUtils {
  static init(): Promise<boolean> {
    return PinyinModule.init();
  }
  static toPinyin(str: string, sperator: string = ' '): Promise<string> {
    return PinyinModule.toPinyin(str, sperator);
  }
  static toAgoraUid(rtcUid: string): Promise<number> {
    return PinyinModule.toAgoraUid(rtcUid);
  }
}
