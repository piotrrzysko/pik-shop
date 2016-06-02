(function () {
    'use strict';

    angular
        .module('app.cart')
        .controller('CartSummaryController', ['$scope', 'Cart', '$state', 'toastr', CartSummaryController]);

    function CartSummaryController($scope, Cart, $state, toastr) {
        var init = function () {
            $scope.loadCart();
        };
        init();

        $scope.confirmOrder = function() {
            $scope.confirmButtonDisabled = true;
            Cart.confirmOrder().then(function() {
                $state.go('home');
            }, function() {
                $scope.confirmButtonDisabled = false;
                toastr.error('Skontaktuj się z biurem obsługi klienta', 'Złożenie zamówienia nie powiodło się');
            });
        }
    }
})();
