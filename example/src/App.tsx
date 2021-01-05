import * as React from 'react';

import { StyleSheet, View, Button, Text } from 'react-native';
import PinyinUtils from 'react-native-pinyin';
interface Props {}
interface State {
  trans: string;
}

export default class App extends React.Component<Props, State> {
  state: State = {
    trans: '',
  };
  constructor(props: Props) {
    super(props);
  }
  async componentDidMount() {
    try {
      let isInit: boolean = await PinyinUtils.init();
      console.log(isInit ? '初始化成功' : '初始化失败');
    } catch (error) {
      console.log(error);
    }
  }
  componentWillUnmount() {}
  render() {
    return (
      <View style={styles.container}>
        <Button
          onPress={async () => {
            try {
              let s = await PinyinUtils.toPinyin('我爱你中国');
              console.log(`toPinyin:${s}`);
              this.setState({
                trans: s,
              });
            } catch (error) {
              console.log(error);
            }
          }}
          title="我爱你中国toPinyin"
        />
        <Text>{this.state.trans}</Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
