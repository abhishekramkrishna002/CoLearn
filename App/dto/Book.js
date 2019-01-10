class Book {
    constructor(){
        this.id = '';
        this.title = '';
        this.imageUrl = '';
        this.description= '';
        this.mimeType= '';
        this.author= '';
        this.internalId = -1;
    }

    isEmpty = () => !this.id && !this.title && !this.imageUrl && !this.description;
}

export default Book;