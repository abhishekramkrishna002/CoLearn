import React from 'react'

import ToggleSwitch from 'toggle-switch-react-native'
import { Colors } from '../Themes'

const TOGGLE_SIZE = 'medium';

const SettingsScreen = ({ value, onChange }) => (
    <ToggleSwitch
        isOn={value}
        onColor={Colors.black}
        offColor={Colors.cloud}
        size={TOGGLE_SIZE}
        onToggle={onChange}
    />
);

export default SettingsScreen;
