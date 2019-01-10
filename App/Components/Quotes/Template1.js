import React, { Fragment } from 'react';
import { ScrollView, Text, WebView } from 'react-native';

// styles
import styles from '../Styles/Quotes/Template1';

const Template1 = ({ quote, quoteNumber, mimeType }) => {
    const htmlContent = `<div id='wrapper' style='display: flex; height:100vh; flex-direction: column; justify-content: center; align-items: center;text-align: center; color: #000; font-size: 18px; overflow-y:hidden'>${quote}</div>`;

    return (
        <ScrollView contentContainerStyle={[styles.mainContainer, styles.centeredSection, { alignItems: 'stretch' }]}>
            {mimeType === 'application/html' &&
                (<WebView key={htmlContent} html={htmlContent} />) ||
                (
                    <Fragment>
                        <Text style={[styles.center, styles.subtitle, styles.quote]}>{quote}</Text>
                        <Text style={[styles.center, styles.quote]}>{quoteNumber}</Text>
                    </Fragment>
                )}
        </ScrollView>
    );
}

export default Template1;