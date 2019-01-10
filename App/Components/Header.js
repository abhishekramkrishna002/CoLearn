import React from 'react';

import { Header, Left, Body, Right } from 'native-base';

// styled
import styles from './Styles/Header';

const AppHeader = ({ left, body, right }) => (
    <Header style={[styles.headerContainer]}>
        {
            left && (
                <Left>
                    {left}
                </Left>
            )
        }
        {
            body && (
                <Body style={{flex: 1}}>
                    {body}
                </Body>
            )
        }
        {
            right && (
                <Right>
                    {right}
                </Right>
            )
        }
    </Header>
);

export default AppHeader;