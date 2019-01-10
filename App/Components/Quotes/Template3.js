import React from 'react';
import { ScrollView, Text} from 'react-native';


// styles
import styles from '../Styles/Quotes/Template2';

const Template3 = ({ quote, description }) => (
    <ScrollView>
        <Text style={[styles.titleText, styles.paragraph, styles.bold]}>{quote}</Text>
        <Text style={[styles.titleText, styles.paragraph]}>{description}</Text>
    </ScrollView>
);

export default Template3;