import React, { PureComponent, Fragment } from 'react';

// components
import { View, TouchableOpacity, DeviceEventEmitter } from 'react-native';
import { Icon } from 'native-base';
import Header from '../Components/Header';
import Loader from '../Components/Loader';
import HeaderTitle from '../Components/HeaderTitle';
import HeaderIcon from '../Components/HeaderIcon';
import Template1 from '../Components/Quotes/Template1';
import Template2 from '../Components/Quotes/Template2';
import Template3 from '../Components/Quotes/Template3';

// styles
import styles from './Styles/QuotesScreen';

// services
// import firebaseInstance from '../Services/Firebase';
import { QuoteModule } from '../Services/Background';

class QuotesScreen extends PureComponent {

    constructor(props) {
        super(props);
        this.state = {
            quotes: [],
            initialised: false,
            presentQuoteIndex: 0,
            isSelect: false,
            latestInternalId: -1,
        };
    }

    componentDidMount() {

        this.quotesReference = DeviceEventEmitter.addListener('RECEIVED_QUOTES', (newQuotes) => {

            const { quotes } = this.state;
            const [lastQuote, ...rest] = [...newQuotes].reverse();
            const latestInternalId = lastQuote.quoteOrderId;
            quotes.push(...newQuotes);

            this.setState({ quotes, initialised: true, latestInternalId });
        });

        this.loadList();
    }

    loadList = () => {
        const { navigation } = this.props;
        const { latestInternalId } = this.state;
        const book = navigation.getParam('book', null);
        const chapter = navigation.getParam('chapter', null);
        const bookId = book && book.id || -1;
        const chapterId = chapter && chapter.id || -1;

        QuoteModule.fetch(parseFloat(latestInternalId), bookId, chapterId);
    };

    componentWillUnmount() {
        this.quotesReference.remove();
    }

    render() {
        const { navigation } = this.props;
        const book = navigation.getParam('book', null);
        const chapter = navigation.getParam('chapter', null);
        const { presentQuoteIndex, isSelect, quotes, initialised } = this.state;
        const mimeType = book.mimeType;

        // header data
        const HEADER_LEFT = (<HeaderIcon name='left' type='AntDesign' onPress={() => { navigation.goBack() }} />);
        const HEADER_BODY = (<HeaderTitle title={book.title} subTitle={chapter.title} />);
        const HEADER_RIGHT = (<HeaderIcon name={isSelect && 'star' || 'staro'} type='AntDesign' onPress={() => { this.setState({ isSelect: !isSelect }) }} />);

        return (
            <View style={styles.mainContainer}>
                <Header left={HEADER_LEFT} body={HEADER_BODY} right={HEADER_RIGHT} />
                {
                    !initialised && <Loader />
                }
                {
                    initialised && quotes.length && (
                        <Fragment>
                            {book.templateType.toLowerCase() === 'template_1' && <Template1 mimeType={mimeType || 'application/text'} quote={quotes[presentQuoteIndex].quote} quoteNumber={quotes[presentQuoteIndex].quoteNumber} />}
                            {book.templateType.toLowerCase() === 'template_2' && <Template2 quote={quotes[presentQuoteIndex].notes} />}
                            {book.templateType.toLowerCase() === 'template_3' && <Template3 quote={quotes[presentQuoteIndex].quote} description={quotes[presentQuoteIndex].description} />}
                            {/* <Template3 /> */}

                            <View style={[styles.horizonal, { margin: 10 }]}>
                                <TouchableOpacity style={{ padding: 10 }} onPress={() => { presentQuoteIndex > 0 && this.setState({ presentQuoteIndex: presentQuoteIndex - 1 }) }} >
                                    {
                                        (quotes.length > 1 && presentQuoteIndex > 0) && (<Icon name='caretleft' type='AntDesign' />)
                                    }
                                </TouchableOpacity>
                                <View style={styles.mainContainer} />
                                <TouchableOpacity style={{ padding: 10 }} onPress={() => { presentQuoteIndex < quotes.length - 1 && this.setState({ presentQuoteIndex: presentQuoteIndex + 1 }) }}>
                                    {
                                        (quotes.length > 1 && presentQuoteIndex < quotes.length - 1) && (<Icon name='caretright' type='AntDesign' />)
                                    }

                                </TouchableOpacity>
                            </View>
                        </Fragment>
                    )
                }
            </View>
        );
    }
}

export default QuotesScreen;