angular
    .module('ProductService', ['restangular'])
    .factory('Product', ['Restangular', 'File', ProductService]);

function ProductService(Restangular, File) {

    return {
        getProducts: getProducts,
        addProduct: addProduct,
        editProduct: editProduct,
        deleteProduct: deleteProduct,
        getProduct: getProduct
    };

    function getProduct(productId) {
        return Restangular.one('public/products', productId).get();
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

        return Restangular.one('public/products').get(request);
    }

    function addProduct(productData, images) {
        var blobFiles = File.convertFilesToBlob(images);

        var fd = new FormData();
        (blobFiles || []).forEach(function(file) {
            fd.append('images', file);
        });
        fd.append('productData', new Blob([JSON.stringify(productData)], {
            type: "application/json"
        }));

        return Restangular.all('public/products')
            .withHttpConfig({
                transformRequest: angular.identity
            })
            .customPOST(fd, undefined, undefined, {
                'Content-Type': undefined
            });
    }

    function editProduct(productData, images) {
        var fd = new FormData();
        fd.append('productData', new Blob([JSON.stringify(productData)], {
            type: "application/json"
        }));

        return Restangular.one('public/products', productData.id)
            .withHttpConfig({
                transformRequest: angular.identity
            })
            .customPUT(fd, undefined, undefined, {
                'Content-Type': undefined
            });
    }

    function deleteProduct(product) {
        return Restangular.one("public/products", product.id).remove();
    }
}
