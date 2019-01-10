import { StyleSheet } from 'react-native'
import { Metrics, ApplicationStyles, Fonts } from '../../../Themes/'

export default StyleSheet.create({
  ...ApplicationStyles.screen,
  ...Fonts.style,
  paragraph:{
    marginLeft: Metrics.doubleBaseMargin * 2,
    marginRight: Metrics.doubleBaseMargin * 2,
    marginTop: Metrics.doubleBaseMargin * 2,
    fontSize: Fonts.size.h6
  },
});
