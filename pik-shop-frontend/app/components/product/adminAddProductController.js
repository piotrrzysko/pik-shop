(function() {
  'use strict';

  angular
    .module('app.product')
    .controller('AdminAddProductController', ['$scope', 'Product', '$state', 'toastr', AdminAddProductController]);

  function AdminAddProductController($scope, Product, $state, toastr) {
    $scope.productData;
    $scope.addProduct = function(productForm) {
      $scope.submitButtonDisabled = true;
      Product.addProduct($scope.productData).then(function(response) {
        $scope.submitButtonDisabled = false;
        $state.go('^.products-list');
      }, function(response) {
        $scope.submitButtonDisabled = false;
        toastr.error('Dodanie produktu nie powiodło się.', 'Błędne dane');
      });
    }
  }
})();
