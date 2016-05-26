(function () {
    'use strict';

    angular
        .module('app.product')
        .controller('AdminProductsListController', ['NgTableParams', '$scope', 'Product', '$state', AdminProductsListController]);

    function AdminProductsListController(NgTableParams, $scope, Product, $state) {

        $scope.getProductStates = function () {
            return $scope.stateIdToName;
        };

        $scope.deleteProduct = function (product) {
            Product.deleteProduct(product).then(function () {
                $state.go(".", {}, {reload: true});
            });
        };

        $scope.getProductStateName = function (id) {
            var displayState = "";
            $scope.stateIdToName.forEach(function (state) {
                if (state.id === id)
                    displayState = state.title;
            });
            return displayState;
        };

        $scope.productTableParams = new NgTableParams({
            page: 1,
            count: 10
        }, {
            filterDelay: 300,
            getData: function ($defer, params) {
                Product.getProducts(params).then(function (response) {
                    params.total(response.totalElements);
                    $defer.resolve(response.content);
                });
            }
        });
    }
})();
