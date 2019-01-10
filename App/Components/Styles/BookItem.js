import { StyleSheet } from 'react-native'
import { Metrics, ApplicationStyles, Colors, Fonts } from '../../Themes/'
import colors from '../../Themes/Colors';

export default StyleSheet.create({
  ...ApplicationStyles.screen,
  ...Fonts.style,
  bookItemContainer: {
    justifyContent: 'center',
    height: Metrics.bookItemHeight,
    margin: Metrics.doubleBaseMargin
  },
  bookTitleText:{
    textAlign: 'center',
    color: Colors.white,
    margin: Metrics.baseMargin
  },
  imageContainer: {
    backgroundColor: colors.imageBackground
  }
})
