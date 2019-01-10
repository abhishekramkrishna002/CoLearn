import { StyleSheet } from 'react-native'
import { Colors, Metrics, Fonts, ApplicationStyles } from '../../Themes/'

const itemContainer = {
    padding: 10,
    borderColor: 'black',
    borderWidth: Metrics.border,
    borderRightWidth: 0
}
export default StyleSheet.create({
    ...ApplicationStyles.screen,
    itemContainer: {
        ...itemContainer,
    },
    itemContainerFirst: {
        ...itemContainer,
        borderTopLeftRadius: Metrics.borderRadius,
        borderBottomLeftRadius: Metrics.borderRadius,
        
    },
    itemContainerLast: {
        ...itemContainer,
        borderTopRightRadius: Metrics.borderRadius,
        borderBottomRightRadius: Metrics.borderRadius,
        borderRightWidth: Metrics.border
    },
    item: {
        textAlign: 'center'
    },
    itemActive: {
        color: Colors.black,
        ...Fonts.style.bold
    }
});