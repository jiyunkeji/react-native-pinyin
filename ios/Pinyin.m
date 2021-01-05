#import "Pinyin.h"

#define INVOKE_FAILED (@"Pinyin fail.")
#define CODE_FAILED (@"failed")
@implementation Pinyin

RCT_EXPORT_MODULE(PinyinModule)

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

+ (BOOL)requiresMainQueueSetup {
    return YES;
}

RCT_EXPORT_METHOD(init:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
    resolve([NSNumber numberWithBool:YES]);
}

RCT_EXPORT_METHOD(toPinyin:(NSString *)str separator:(NSString *)separator resolve:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
     @try {
         
         if(!str||str.length==0){
             reject(CODE_FAILED,INVOKE_FAILED,NULL);
             return;
         }
              
              // 将中文字符串转成可变字符串
              NSMutableString *pinyinText = [[NSMutableString alloc] initWithString:str];

              // 先转换为带声调的拼音
              CFStringTransform((__bridge CFMutableStringRef)pinyinText, 0, kCFStringTransformMandarinLatin, NO);
              // 再转换为不带声调的拼音
              CFStringTransform((__bridge CFMutableStringRef)pinyinText, 0, kCFStringTransformStripDiacritics, NO);

              // 转换为首字母大写拼音
              NSString *capitalPinyin = [pinyinText lowercaseString];
         
            capitalPinyin = [capitalPinyin stringByReplacingOccurrencesOfString:@" " withString:separator];
          
           if(capitalPinyin){
               resolve(capitalPinyin);
           }else{
                reject(CODE_FAILED,INVOKE_FAILED,NULL);;
           }
           
           } @catch (NSException *exception) {
                      reject([exception name],[exception reason],NULL);
                }
}

@end
