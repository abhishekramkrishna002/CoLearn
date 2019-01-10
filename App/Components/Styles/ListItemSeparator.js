import { StyleSheet } from 'react-native'
import { Metrics, ApplicationStyles, Colors, Fonts } from '../../Themes/'

export default StyleSheet.create({
  ...ApplicationStyles.screen,
  ...Fonts.style,
  separator: {
    backgroundColor: Colors.dicider,
    height: 1
  }
})
