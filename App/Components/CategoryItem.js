import React from 'react';

//components
import { TouchableOpacity, Image, Text } from 'react-native';

// styles
import styles from './Styles/CategoryItem';

import Colors from '../Themes/Colors';

const pastelColorSize = Colors.pasteColorPalette.length;

const CategoryItem = ({ title, onPress, index = 0 }) => (
    <TouchableOpacity key={title} style={[styles.mainContainer, styles.itemContainer, { backgroundColor: Colors.pasteColorPalette[index % pastelColorSize] }]} onPress={onPress}>
        <Text style={[ styles.chapterText] } >{title}</Text>
    </TouchableOpacity>
);

export default CategoryItem;