angular
    .module('ProductService', ['restangular'])
    .factory('Product', ['$q', '$http', 'Restangular', ProductService]);

function ProductService($q, $http, Restangular) {

    return {
        getProducts: getProducts,
        addProduct: addProduct,
        deleteProduct: deleteProduct
    };

    function getProducts(params) {
        var sortingCol = Object.keys(params.sorting())[0];
        var request = {
            page: params.page() - 1,
            size: params.count(),
            name: params.filter().name,
            price: params.filter().price,
            availableCount: params.filter().availableCount,
            productState: params.filter().productState,
            sortCol: sortingCol,
            direction: params.sorting()[sortingCol]
        };

        return Restangular.one('products').get(request);
    }

    function addProduct(productData) {
        return Restangular.all('products')
            .post(productData);
    }

    function deleteProduct(product) {
        return Restangular.one("products", product.id).remove();

        // var url = 'products' + product.id;
        // return Restangular.all(url)
        //     .delete();
    }
}
