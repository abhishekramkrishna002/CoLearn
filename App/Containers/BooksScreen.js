import React, { PureComponent } from 'react';
import { View, FlatList, DeviceEventEmitter } from 'react-native';

// components
import BookItem from '../Components/BookItem';
import HeaderIcon from '../Components/HeaderIcon';
import HeaderTitle from '../Components/HeaderTitle';
import Header from '../Components/Header';
import Loader from '../Components/Loader';

// services
import { BookModule } from '../Services/Background';

// dtos
import Category from '../dto/Category';
import Book from '../dto/Book';

// styles
import styles from './Styles/BooksScreen';

const COLUMNS = 2;

class BookScreen extends PureComponent {

  constructor(props) {
    super(props);
    this.state = {
      books: [],
      initialised: false,
      lastestBookInternalId : -1
    };
  }

  componentDidMount() {
    
    this.booksReference = DeviceEventEmitter.addListener('RECEIVED_BOOKS', (newBooks) => {
      const { books } = this.state;
      const [lastBook, ...rest] = [...newBooks].reverse();
      const lastestBookInternalId = lastBook.internalId;
      newBooks.length && books.push(...newBooks);
      this.setState({ books, initialised: true, lastestBookInternalId });
    });
    this.loadList();

  }

  componentWillUnmount() {
    this.booksReference.remove();
  }

  loadList = () => {
    const { navigation } = this.props;
    const { lastestBookInternalId } = this.state;
    const category = navigation.getParam('category', new Category());
    const { categoryId } = category;
    BookModule.fetch(parseFloat(lastestBookInternalId), categoryId);
  };

  render() {
    const { navigation } = this.props;
    const { books, initialised } = this.state;
    const category = navigation.getParam('category', new Category());
    const { title } = category;
    const HEADER_LEFT = (<HeaderIcon name='left' type='AntDesign' onPress={() => { navigation.goBack() }} />);
    const HEADER_BODY = (<HeaderTitle title={title} />);
    const renderBooks = [...books];
    renderBooks.length % 2 != 0 && renderBooks.push(new Book());

    return (
      <View style={styles.mainContainer}>
        <Header left={HEADER_LEFT} body={HEADER_BODY} right={<View />} />
        {
          initialised && (
            <FlatList
              style={styles.mainContainer}
              columnWrapperStyle={styles.mainContainer}
              numColumns={COLUMNS}
              data={renderBooks}
              keyExtractor={(item, index) => item.title}
              renderItem={({ item, index }) => (<BookItem onPress={() => { navigation.navigate('ChaptersScreen', { book: item }) }} isEvenPosition={index % 2 === 0} title={item.title} author={item.author} imageUrl={item.imageUrl} key={item.id} index={index} />)}
              onEndReachedThreshold={0.5}
              onEndReached={() => { console.log('end reached...'); this.loadList(); }}
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

export default BookScreen;