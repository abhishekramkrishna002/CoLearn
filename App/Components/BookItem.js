import React, { Fragment } from 'react';
import { isEmpty } from 'lodash';

//components
import { TouchableOpacity, Image, Text } from 'react-native';

// styles
import styles from './Styles/BookItem';
import { Colors } from '../Themes';

const BookItem = ({ title = '', author = '', imageUrl, onPress }) => (
    <Fragment>
        <TouchableOpacity onPress={ title && author ? onPress: null} key={title} style={[styles.mainContainer, styles.vertical, styles.bookItemContainer, !!title && !!author && {backgroundColor: Colors.bookItemBackgroundColor[Math.floor(Math.random() * 4) + 0]}]}>
            {!imageUrl && (
                <Fragment>
                    <Text style={[styles.titleText, styles.h5, styles.bookTitleText, styles.bold]}>{title}</Text>
                    <Text style={[styles.titleText, styles.h6, styles.bookTitleText]}>{author}</Text>
                </Fragment>
            )}
            {!!imageUrl && <Image style={[styles.mainContainer, styles.imageContainer]} resizeMode='stretch' source={{ uri: imageUrl }} />}
        </TouchableOpacity>
    </Fragment>

);

export default BookItem;