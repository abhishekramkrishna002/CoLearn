import React, { PureComponent } from 'react'
import { View, FlatList, DeviceEventEmitter } from 'react-native'

// components
import Header from '../Components/Header';
import HeaderIcon from '../Components/HeaderIcon';
import HeaderTitle from '../Components/HeaderTitle';
import ChapterItem from '../Components/ChapterItem';
import ListItemSeparator from '../Components/ListItemSeparator';
import Loader from '../Components/Loader';

// services
// import firebaseInstance from '../Services/Firebase';
import { ChapterModule } from '../Services/Background';

// Styles
import styles from './Styles/ChaptersScreen'

class ChaptersScreen extends PureComponent {

  constructor(props) {
    super(props);
    this.state = {
      chapters: [],
      initialised: false,
      latestInternalId: -1,
    };
  }

  componentDidMount() {
    this.chaptersReference = DeviceEventEmitter.addListener('RECEIVED_CHAPTERS', (newChapters) => {
      const { chapters } = this.state;
      const [lastChapter, ...rest] = [...newChapters].reverse();
      const latestInternalId = lastChapter.chapterOrderId;
      chapters.push(...newChapters);

      this.setState({ chapters, initialised: true, latestInternalId });
    });
    this.loadList();
  }

  loadList = () => {
    const { navigation } = this.props;
    const { latestInternalId } = this.state;
    const book = navigation.getParam('book', null);
    const bookId = book && book.id || -1;

    ChapterModule.fetch(parseFloat(latestInternalId), bookId);
  };

  componentWillUnmount() {
    this.chaptersReference.remove();
  }

  render() {
    const { navigation } = this.props;
    const { chapters, initialised } = this.state;
    const book = navigation.getParam('book', null);
    const bookName = book && book.title || '';

    const HEADER_LEFT = (<HeaderIcon name='left' type='AntDesign' onPress={() => { navigation.goBack() }} />);
    const HEADER_BODY = (<HeaderTitle title={bookName} />);

    return (
      <View style={[styles.mainContainer]}>
        <Header left={HEADER_LEFT} body={HEADER_BODY} right={<View />} />
        {
          initialised && (
            <FlatList
              data={chapters}
              renderItem={({ item, index }) => (<ChapterItem onPress={() => { navigation.navigate('QuotesScreen', { book, chapter: item }) }} title={item.title} />)}
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

export default ChaptersScreen;