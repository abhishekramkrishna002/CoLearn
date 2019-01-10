import {Dimensions, Platform} from 'react-native'

const { width, height } = Dimensions.get('window')

// Used via Metrics.baseMargin
const metrics = {
  marginHorizontal: 5,
  marginVertical: 5,
  section: 15,
  baseMargin: 5,
  doubleBaseMargin: 10,
  smallMargin: 2,
  doubleSection: 25,
  horizontalLineHeight: 1,
  screenWidth: width < height ? width : height,
  screenHeight: width < height ? height : width,
  navBarHeight: (Platform.OS === 'ios') ? 64 : 54,
  buttonRadius: 4,
  icons: {
    tiny: 15,
    small: 20,
    medium: 30,
    large: 45,
    xl: 50
  },
  images: {
    small: 20,
    medium: 40,
    large: 60,
    logo: 200
  },
  border: 1,
  borderRadius: 5,
  padding: 5,
  bookItemHeight: 250
}

export default metrics
