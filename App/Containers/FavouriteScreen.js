import React from 'react'
import { View, FlatList } from 'react-native'

// components
import Header from '../Components/Header';
import HeaderTitle from '../Components/HeaderTitle';
import FavouriteItem from '../Components/FavouriteItem';
import ListItemSeparator from '../Components/ListItemSeparator';

// Styles
import styles from './Styles/FavouriteScreen'

const data = [
  {
    bookTitle: 'Title 1',
    quote: 'quote 1',
    key: 'key 1'
  },
  {
    bookTitle: 'Title 2',
    quote: 'quote 1',
    key: 'key 2'
  },
  {
    bookTitle: 'Title 3',
    quote: 'quote 1',
    key: 'key 3'
  },
  {
    bookTitle: 'Title 4',
    quote: 'quote 1',
    key: 'key 4'
  },
  {
    bookTitle: 'Title 3',
    quote: 'quote 1',
    key: 'key 5'
  },
  {
    bookTitle: 'Title 4',
    quote: 'quote 1',
    key: 'key 6'
  },
  {
    bookTitle: 'Title 3',
    quote: 'quote 1',
    key: 'key 7'
  },
  {
    bookTitle: 'Title 4',
    quote: 'quote 1',
    key: 'key 8'
  }
];

const HEADER_BODY = (<HeaderTitle title='Favourite' />);
const FavouriteScreen = () => (
  
  <View style={[styles.mainContainer, styles.container]}>
    <Header body={HEADER_BODY} />

    <FlatList
      data={data}
      renderItem={({ item, index }) => (<FavouriteItem bookTitle={item.bookTitle} key={item.key} quote={item.quote} />)}
      ItemSeparatorComponent={() => (<ListItemSeparator />)}
    />
  </View>
);

export default FavouriteScreen;