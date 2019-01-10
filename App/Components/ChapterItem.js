import React from 'react';

//components
import { TouchableOpacity, Image, Text } from 'react-native';

// styles
import styles from './Styles/ChapterItem';

const ChapterItem = ({ title, onPress }) => (
    <TouchableOpacity key={title} style={[styles.mainContainer, styles.itemContainer]} onPress={onPress}>
        <Text style={[styles.mainContainer, styles.subtitle, styles.chapterText]} >{title}</Text>
    </TouchableOpacity>
);

export default ChapterItem;