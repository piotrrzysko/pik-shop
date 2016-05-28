(function () {
    'use strict';

    angular
        .module('app.cart')
        .controller('CartItemsListController', ['$scope', 'Product', '$state', 'toastr', CartItemsListController]);

    function CartItemsListController($scope, Product, $state) {
        var init = function () {
            $scope.order = {totalValue: 10.00};
            $scope.order.orderItems = [{
                id: 1,
                productName: "Zbawcy książek",
                price: 23,
                amount: 1,
                productImage: "files/file_115134026775279002.jpg"
            }, {
                id: 3,
                productName: "Zbawcy książek",
                price: 23,
                amount: 1,
                productImage: "files/file_115134026775279002.jpg"
            }];
        };
        init();

        $scope.isCartEmpty = function() {
            return !$scope.order.orderItems || !$scope.order.orderItems.length;
        }
    }
})();
