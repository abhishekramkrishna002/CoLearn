import React from 'react';

import { View } from 'react-native';
import { Spinner } from 'native-base';

// styled
import styles from './Styles/Header';

// colors
import { Colors } from '../Themes';

const Loader = () => (
    <View style={[styles.mainContainer, styles.centeredSection]}>
        <Spinner color={Colors.accent}/>
    </View>
);

export default Loader;