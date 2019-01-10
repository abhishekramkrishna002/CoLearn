import { createStackNavigator, createAppContainer } from 'react-navigation'

// screens
import { CategoriesScreen, BooksScreen, SettingsScreen, WidgetSettingsScreen, FavouriteScreen, QuotesScreen, ChaptersScreen } from '../Containers';

import styles from './Styles/NavigationStyles'

// Manifest of possible screens
const PrimaryNav = createStackNavigator({
  CategoriesScreen: {
    screen: CategoriesScreen,
  },
  BooksScreen: {
    screen: BooksScreen,
  },
  ChaptersScreen: {
    screen: ChaptersScreen,
  },
  QuotesScreen: {
    screen: QuotesScreen,
  },
  FavouriteScreen: {
    screen: FavouriteScreen,
  },
  SettingsScreen: {
    screen: SettingsScreen,
  },
  WidgetSettingsScreen: {
    screen: WidgetSettingsScreen,
  },
}, {
    // Default config for all screens
    headerMode: 'none',
    initialRouteName: 'CategoriesScreen',
    navigationOptions: {
      headerStyle: styles.header,
      cardStyle: styles.cardStyle
    }
  })

export default createAppContainer(PrimaryNav)
