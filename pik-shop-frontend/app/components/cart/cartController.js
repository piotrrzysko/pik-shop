(function () {
    'use strict';

    angular
        .module('app.cart', [])
        .controller('CartController', ['$scope', 'Cart', 'toastr', CartController]);

    function CartController($scope, Cart, toastr) {
        $scope.loadCart = function() {
            Cart.getOrder().then(function(order) {
                $scope.order = order;
            }, function() {
                toastr.error('Wystąpił błąd podczas wczytywania zawartości koszyka');
            });
        }
    }
})();
