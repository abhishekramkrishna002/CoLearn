import { StyleSheet } from 'react-native'
import { Metrics, ApplicationStyles, Colors, Fonts } from '../../Themes/'

export default StyleSheet.create({
  ...ApplicationStyles.screen,
  ...Fonts.style,
  iconContainer: {
    color: Colors.white,
    marginRight: Metrics.doubleBaseMargin
  },
  headerTitle: {
    color: Colors.white,
    fontSize: Fonts.size.h6,
    alignSelf: 'stretch',
    textAlign: 'left',
    marginLeft: 0
  },
  headerSubtitle: {
    color: Colors.white,
    fontSize: Fonts.size.small,
    alignSelf: 'stretch',
    textAlign: 'left',
    marginLeft: 0
  }
})
