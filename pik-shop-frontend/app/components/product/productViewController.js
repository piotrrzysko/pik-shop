(function () {
    'use strict';

    angular
        .module('app.product')
        .controller('ProductViewController', ['$scope', 'Product', '$stateParams', 'Cart', 'toastr', ProductViewController]);

    function ProductViewController($scope, Product, $stateParams, Cart, toastr) {
        var init = function () {
            Product.getProduct($stateParams.productId).then(function (product) {
                $scope.product = product;
            });
            $scope.orderItem = {count: 1};
        };
        init();

        $scope.addOrderItem = function() {
            Cart.addOrderItem($scope.product.id, $scope.orderItem.count).then(function() {
                toastr.success('Produkt został dodany do koszyka');
            }, function () {
                toastr.error('Skontaktuj się z biurem obsługi klienta', 'Dodanie produktu nie powiodło się');
            });
        }
    }
})();
