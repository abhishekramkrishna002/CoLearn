import { StyleSheet } from 'react-native'
import { Metrics, ApplicationStyles, Colors, Fonts } from '../../Themes/'

export default StyleSheet.create({
  ...ApplicationStyles.screen,
  ...Fonts.style,
  itemContainer: {
    backgroundColor: Colors.white,
    padding: Metrics.padding * 2
  },
  chapterText:{
    fontSize: Fonts.size.h6
  }
})
