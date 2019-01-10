import React, { PureComponent } from 'react'
import { View, FlatList, Text, DeviceEventEmitter } from 'react-native';

// components
import Header from '../Components/Header';
import HeaderIcon from '../Components/HeaderIcon';
import HeaderTitle from '../Components/HeaderTitle';
import CategoryItem from '../Components/CategoryItem';
import ListItemSeparator from '../Components/ListItemSeparator';
import Loader from '../Components/Loader';

// services
import { CategoryModule } from '../Services/Background';

// Styles
import styles from './Styles/CategoriesScreen'

class CategoriesScreen extends PureComponent {

  constructor(props) {
    super(props);
    this.state = {
      categories: [],
      initialised: false,
      latestOrderId: -1,
    };
  }

  componentDidMount() {

    this.categoriesReference = DeviceEventEmitter.addListener('RECEIVED_CATEGORIES', (newCategories) => {
      const { categories } = this.state;
      const [lastCategory, ...rest] = [...newCategories].reverse();
      const latestOrderId = lastCategory.orderId;
      categories.push(...newCategories);

      this.setState({ categories, initialised: true, latestOrderId });
    });

    this.loadList();
  }


  loadList = () => {
    const { latestOrderId } = this.state;
    CategoryModule.fetch(parseFloat(latestOrderId));
  };

  componentWillUnmount() {
    this.categoriesReference.remove();
  }

  render() {
    const { navigation } = this.props;
    const { categories, initialised } = this.state;

    // const HEADER_LEFT = (<HeaderIcon name='left' type='AntDesign' onPress={() => { navigation.goBack() }} />);
    const HEADER_BODY = (<HeaderTitle title='Categories' />);
    const HEADER_RIGHT = [
      (<HeaderIcon key='header_icon_1' style={styles.icon} name='star' type='AntDesign' onPress={() => { navigation.navigate('FavouriteScreen') }} />),
      (<HeaderIcon key='header_icon_2' style={styles.icon} name='settings' type='SimpleLineIcons' onPress={() => { navigation.navigate('SettingsScreen') }} />)
    ];

    return (
      <View style={[styles.mainContainer]}>
        <Header body={HEADER_BODY} right={HEADER_RIGHT} />
        {
          initialised && (
            <FlatList
              data={categories}
              renderItem={({ item, index }) => (<CategoryItem index={index} onPress={() => { navigation.navigate('BooksScreen', { category: item }) }} title={item.title} />)}
              ItemSeparatorComponent={() => (<ListItemSeparator />)}
            />
          )
        }
        {
          !initialised && (<Loader />)
        }
      </View>
    );
  }
}

export default CategoriesScreen;