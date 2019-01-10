import React from 'react';
import { ScrollView, Text, View, TouchableOpacity, WebView } from 'react-native';


// styles
import styles from '../Styles/Quotes/Template2';

const Template2 = ({quote}) => (
    <ScrollView contentContainerStyle={[styles.mainContainer]}>
        <WebView html={quote} style={[styles.paragraph]}/>
    </ScrollView>
);

export default Template2;