import { StyleSheet } from 'react-native'
import { Metrics, ApplicationStyles, Fonts } from '../../../Themes/'

export default StyleSheet.create({
  ...ApplicationStyles.screen,
  ...Fonts.style,
  quote:{
    fontSize: Fonts.size.h5,
    margin: Metrics.doubleBaseMargin
  }
});
