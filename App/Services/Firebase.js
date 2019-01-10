import firebase from 'react-native-firebase';

// dto
import Category from '../dto/Category';
import Book from '../dto/Book';
import Chapter from '../dto/Chapter';
import Quote from '../dto/Quote';

const CATEGORIES_PATH = 'Categories';
const BOOKS_PATH = 'Books';
const DELIMITTER = '{}';
const CHAPTERS_PATH = `Books/${DELIMITTER}/chapters`;
const QUOTES_PATH = `/${DELIMITTER}/quotes`;

class Firebase {

    constructor() {

        //this.firebaseConnector = firebase.firestore();
    }

    getCategoriesCollectionReference = (collectionProcessor) => {
        const reference = this.firebaseConnector
            .collection(CATEGORIES_PATH)
            .onSnapshot(snapshot => {
                const categories = snapshot.size ? snapshot.docs : [];
                const categoriesDTO = categories.map(({ id, data }) => Object.assign(new Category(), data(), { id }));
                collectionProcessor(categoriesDTO);
            });

        return reference;
    };

    getBookCollectionReference = (categoryId, collectionProcessor) => {
        
        const ORDER_BY_COLUMN = 'internalId';
        const CATEGORY_ID_COLUMN = 'categoryId';
        const reference = this.firebaseConnector
            .collection(BOOKS_PATH)
            .where(CATEGORY_ID_COLUMN, '==', categoryId)
            .orderBy(ORDER_BY_COLUMN)
            .onSnapshot(snapshot => {
                const books = snapshot.size ? snapshot.docs : [];
                const booksTO = books.map(({ id, data }) => {
                    const bookData = data();
                    const book = Object.assign(new Book(), bookData, { id },{mimeType: bookData.displayProperties.mimeType});

                    return book;
                });
                collectionProcessor(booksTO);
            });

        return reference;
    };

    getChapterCollectionReference = (bookId, collectionProcessor) => {
        const ORDER_BY_COLUMN = 'chapterOrderId';
        const reference = this.firebaseConnector
            .collection(CHAPTERS_PATH.replace(DELIMITTER, bookId))
            .orderBy(ORDER_BY_COLUMN)
            .onSnapshot(snapshot => {
                const chapters = snapshot.size ? snapshot.docs : [];
                const captersDTO = chapters.map(({ id, data }) => Object.assign(new Chapter(), data(), { id }));
                collectionProcessor(captersDTO);
            });

        return reference;
    };

    getQuotesCollectionReference = (bookId, chapterId, collectionProcessor) => {
        const quotesPath = `${CHAPTERS_PATH.replace(DELIMITTER, bookId)}${QUOTES_PATH.replace(DELIMITTER, chapterId)}`;
        const ORDER_BY = 'quoteOrderId';
        const reference = this.firebaseConnector
            .collection(quotesPath)
            .orderBy(ORDER_BY)
            .onSnapshot(snapshot => {
                const quotes = snapshot.size ? snapshot.docs : [];
                const quotesDTO = quotes.map(({ id, data }) => Object.assign(new Quote(), data(), { id }));
                collectionProcessor(quotesDTO);
            });

        return reference;
    };

}

const FirebaseInstance = new Firebase();
export default FirebaseInstance; 