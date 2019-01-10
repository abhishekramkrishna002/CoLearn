class Category {
    constructor() {
        this.id = '';
        this.title = '';
        this.categoryId = '';
        this.orderId = -1;
    }

    toJson = () => (
        {
            id: this.id,
            title: this.title,
            categoryId: this.categoryId
        }
    );

    isEmpty = () => !this.id && !this.title && !this.categoryId;
}

export default Category;