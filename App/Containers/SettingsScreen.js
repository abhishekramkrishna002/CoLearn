import React, { Component } from 'react'

// components
import { Text, View } from 'react-native'
import ToggleSwitch from '../Components/ToggleSwitch';
import Segment from '../Components/Segment';
import Header from '../Components/Header';
import HeaderTitle from '../Components/HeaderTitle';

// Styles
import styles from './Styles/SettingsScreen'

export default class SettingsScreen extends Component {

  constructor(props) {
    super(props);
    this.state = {
      shouldShowNotification: false,
      isNonDismissable: false
    };
  }

  render() {
    const { shouldShowNotification, isNonDismissable } = this.state;
    const HEADER_BODY = (<HeaderTitle title='Settings' />);

    return (
      <View style={styles.mainContainer}>
        
        {/* Header */}
        <Header body={HEADER_BODY} />

        {/* show notifications */}
        <View style={[styles.section, styles.horizonal, { justifyContent: 'space-between' }]}>
          <Text style={[styles.normal, styles.bold]}>Show Notifications</Text>
          <ToggleSwitch
            style={[styles.fill, styles.vertical]}
            value={shouldShowNotification}
            onChange={(isOn) => this.setState({ shouldShowNotification: isOn })
            }
          />
        </View>

        {/* notification settings */}
        <View style={[styles.section, styles.vertical]}>
          <Text style={[styles.normal, styles.bold]}>Notification Settings</Text>
          <View style={[styles.section, styles.vertical]}>
            {/* refresh frequency */}
            <View style={[styles.vertical, { marginBottom: 10 }]}>
              <Text style={[styles.titleText]}>Refresh frequency</Text>
              <Segment items={['30M', '60M', '180M', '360M']} activeIndex={1} />
            </View>
            {/* non dismissable */}
            <View style={[styles.horizonal, { justifyContent: 'space-between', marginBottom: 10 }]}>
              <Text style={[styles.titleText]}>Non dismissable</Text>
              <ToggleSwitch
                style={[styles.fill, styles.vertical]}
                value={isNonDismissable}
                onChange={(isOn) => this.setState({ isNonDismissable: isOn })
                }
              />
            </View>
          </View>
        </View>
      </View>
    )
  }
}
