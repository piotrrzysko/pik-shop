(function () {
    'use strict';

    angular
        .module('app.cart')
        .controller('CartItemsListController', ['$scope', 'Cart', 'toastr', CartItemsListController]);

    function CartItemsListController($scope, Cart, toastr) {
        var init = function () {
            $scope.loadCart();
        };
        init();

        $scope.isCartEmpty = function() {
            return !$scope.order || !$scope.order.orderItems || !$scope.order.orderItems.length;
        };

        $scope.deleteOrderItem = function(itemId) {
            Cart.deleteOrderItem(itemId).then(function(order) {
                $scope.order = order;
            }, function() {
                toastr.error('Skontaktuj się z biurem obsługi klienta', 'Usunięcie produktu nie powiodło się');
            });
        };
    }
})();
