class Quote {
    constructor() {
        this.id = '';
        this.quote = '';
        this.author = '';
        this.quoteNumber = 0;
    }

    isEmpty = () => !this.quote && !this.author && !this.quoteNumber;
}

export default Quote;