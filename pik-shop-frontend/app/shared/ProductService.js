angular
    .module('ProductService', ['restangular'])
    .factory('Product', ['Restangular', 'File', ProductService]);

function ProductService(Restangular, File) {

    return {
        getProducts: getProducts,
        addProduct: addProduct,
        deleteProduct: deleteProduct,
        getProduct: getProduct
    };

    function getProduct(productId) {
        return Restangular.one('products', productId).get();
    }

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

    function addProduct(productData, images) {
        var blobFiles = File.convertFilesToBlob(images);

        var fd = new FormData();
        fd.append('images', blobFiles);
        fd.append('productData', new Blob([JSON.stringify(productData)], {
            type: "application/json"
        }));

        return Restangular.all('products')
            .withHttpConfig({
                transformRequest: angular.identity
            })
            .customPOST(fd, undefined, undefined, {
                'Content-Type': undefined
            });
    }

    function deleteProduct(product) {
        return Restangular.one("products", product.id).remove();
    }
}
