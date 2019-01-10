class Chapter {
    constructor() {
        this.id = '';
        this.title = '';
        this.chapterOrderId = '';
    }

    toJson = () => (
        {
            id: this.id,
            title: this.title,
            chapterOrderId: this.chapterOrderId
        }
    );

    isEmpty = () => !this.id && !this.title && !this.chapterOrderId;
}

export default Chapter;