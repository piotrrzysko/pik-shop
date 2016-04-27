angular
  .module('ProductService', ['restangular'])
  .factory('Product', ['$q', '$http', 'Restangular', ProductService]);

function ProductService($q, $http, Restangular) {

  return {
    getProducts: getProducts,
    addProduct: addProduct
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
    }

    return Restangular.all('products').post(request);
  }

  function addProduct(productData) {
    return Restangular.all('products/add').post(productData);
  }
}
