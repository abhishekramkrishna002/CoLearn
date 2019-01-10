import { StyleSheet } from 'react-native'
import { Metrics, ApplicationStyles, Colors, Fonts } from '../../Themes/'

export default StyleSheet.create({
  ...ApplicationStyles.screen,
  ...Fonts.style,
  itemContainer: {
    backgroundColor: Colors.white,
    paddingLeft: Metrics.padding * 2,
    paddingRight: Metrics.padding * 2,
    paddingTop: Metrics.padding * 4,
    paddingBottom: Metrics.padding * 4,
    justifyContent: 'center',
    alignItems: 'center'
  },
  chapterText:{
    fontSize: Fonts.size.h6,
    color: Colors.white
  }
})
