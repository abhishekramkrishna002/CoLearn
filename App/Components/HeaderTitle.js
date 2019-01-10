import React, { Fragment } from 'react';

// components
import { Title, Subtitle } from 'native-base';

// styles
import styles from './Styles/HeaderTitle';

const HeadertTtle = ({ title, subTitle }) => (
    <Fragment>
        <Title style={[styles.h5, styles.bold, styles.headerTitle]}>{title}
        </Title>
        {subTitle && (
            <Subtitle style={[styles.h6, styles.headerSubtitle]}>{subTitle}
            </Subtitle>
        )}
    </Fragment>
);

export default HeadertTtle;