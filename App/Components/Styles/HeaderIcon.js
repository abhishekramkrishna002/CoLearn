import { StyleSheet } from 'react-native'
import { Metrics, ApplicationStyles, Colors } from '../../Themes/'

export default StyleSheet.create({
  ...ApplicationStyles.screen,
  iconContainer:{
    color: Colors.white,
    marginRight: 0,
    justifyContent: 'center',
    fontSize: 24
  }
})
