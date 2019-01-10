import React, { Component } from 'react'

// components
import { ScrollView, Text, Image, View } from 'react-native'
import ToggleSwitch from '../Components/ToggleSwitch';
import Segment from '../Components/Segment';

// Styles
import styles from './Styles/SettingsScreen'

export default class WidgetSettingsScreen extends Component {

  constructor(props){
    super(props);
    this.state = {
      shouldShowNotification : false,
      isNonDismissable: false
    };
  }

  render () {
    const { shouldShowNotification,  isNonDismissable} = this.state;
    return (
      <View style={styles.mainContainer}>

        {/* auto refresh */}
        <View style={[styles.section, styles.horizonal,{justifyContent: 'space-between'}]}>
          <Text style={[styles.normal, styles.bold]}>Auto Refresh</Text>
          <ToggleSwitch
            style={[styles.fill,styles.vertical]}
            value={shouldShowNotification}
            onChange={(isOn) => this.setState({ shouldShowNotification: isOn })
            }
          />
        </View>

        {/* refresh frequency */}
        <View style={[styles.section, styles.vertical, { marginBottom: 10 }]}>
          <Text style={[styles.normal, styles.bold]}>Refresh frequency</Text>
          <Segment items={['30M', '60M', '180M', '360M']} activeIndex={1} />
        </View>


      </View>
    )
  }
}
