import React from 'react';

// components
import {View, Text, TouchableOpacity} from 'react-native';

// styles
import styles from './Styles/Segment';

const Segment = ({ items = [], activeIndex = 0 }) => (
    <View style={[styles.horizonal]}>
        {items.map( (item, index) => (
            <TouchableOpacity key={item} style={[styles.fill, styles.itemContainer, index == 0 && styles.itemContainerFirst, index + 1 == items.length && styles.itemContainerLast]}>
                <Text style={[styles.item, index == activeIndex && styles.itemActive]} >{item}</Text>
            </TouchableOpacity>))}
    </View>
);

export default Segment;