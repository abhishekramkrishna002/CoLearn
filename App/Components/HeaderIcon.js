import React from 'react';

// components
import { TouchableOpacity } from 'react-native';
import { Icon } from 'native-base';

// styles
import styles from './Styles/HeaderIcon';

const HeaderIcon = ({name, type, onPress, style}) => (
    <TouchableOpacity onPress={onPress}>
        <Icon name={name} type={type} style={[styles.iconContainer, style]} />
    </TouchableOpacity>
);

export default HeaderIcon;