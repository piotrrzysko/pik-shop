(function () {
    'use strict';

    angular
        .module('app.product')
        .controller('AdminEditProductController', ['$scope', 'Product', '$state', '$stateParams', 'toastr', 'File', AdminEditProductController]);

    function AdminEditProductController($scope, Product, $state, $stateParams, toastr) {
        var init = function () {
            Product.getProduct($stateParams.productId).then(function (product) {
                $scope.productData = product;
            });
        };
        init();
        $scope.edit = true;

        $scope.submitProduct = function () {
            $scope.submitButtonDisabled = true;
            Product.editProduct($scope.productData).then(function() {
                $scope.submitButtonDisabled = false;
                $state.go('^.products-list');
            }, function () {
                $scope.submitButtonDisabled = false;
                toastr.error('Nie zapisano zmian', 'Błędne dane');
            });
        };
    }
})();
