(function () {
    'use strict';

    angular
        .module('app.product')
        .controller('ProductViewController', ['$scope', 'Product', '$stateParams', ProductViewController]);

    function ProductViewController($scope, Product, $stateParams) {
        var init = function () {
            Product.getProduct($stateParams.productId).then(function (product) {
                $scope.product = product;
            });
            $scope.orderItem = {count: 1};
        };
        init();
    }
})();
