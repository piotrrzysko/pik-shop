(function () {
    'use strict';

    angular
        .module('app.cart')
        .controller('CartItemsListController', ['$scope', 'Cart', 'toastr', 'UserStorageService', '$state', CartItemsListController]);

    function CartItemsListController($scope, Cart, toastr, UserStorageService, $state) {
        var init = function () {
            $scope.loadCart();
        };
        init();

        $scope.isCartEmpty = function() {
            return !$scope.order || !$scope.order.orderItems || !$scope.order.orderItems.length;
        };

        $scope.isAuthenticated = function() {
            return UserStorageService.isSignedIn();
        };

        $scope.linkOrderWithUser = function() {
            $scope.nextStepButtonDisabled = true;
            Cart.linkOrderWithUser().then(function () {
                $state.go('^.delivery-payment');
            }, function () {
                $scope.nextStepButtonDisabled = false;
                toastr.error('Skontaktuj się z biurem obsługi klienta', 'Wystąpił błąd w działaniu aplikacji.');
            });
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
