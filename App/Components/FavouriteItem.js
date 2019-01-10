import React from 'react';

//components
import { TouchableOpacity, Text } from 'react-native'

// styles
import styles from './Styles/FavouriteItem';

const FavouriteItem = ({bookTitle, quote}) => (
    <TouchableOpacity style={[styles.mainContainer, styles.vertical, styles.itemContainer]}>
        <Text style={[styles.titleText]}>{bookTitle}</Text>
        <Text style={[styles.subtitle]}>{quote}</Text>
    </TouchableOpacity>
);

export default FavouriteItem;