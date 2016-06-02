(function () {
    'use strict';

    angular
        .module('app.cart')
        .controller('CartDeliveryAndPaymentController', ['$scope', 'Cart', '$state', 'toastr', CartDeliveryAndPaymentController]);

    function CartDeliveryAndPaymentController($scope, Cart, $state, toastr) {
        var init = function () {
            $scope.loadCart();
            Cart.getPaymentTypes().then(function(paymentTypes) {
                $scope.paymentTypes = paymentTypes;
            });
            Cart.getDeliveryFormTypes().then(function(deliveryFormTypes) {
                $scope.deliveryFormTypes = deliveryFormTypes;
            });
        };
        init();

        $scope.updateOrder = function () {
            $scope.nextStepButtonDisabled = true;
            Cart.updateOrder($scope.order).then(function () {
                $state.go('^.summary');
            }, function () {
                $scope.nextStepButtonDisabled = false;
                toastr.error('Skontaktuj się z biurem obsługi klienta', 'Przejście do podsumowania nie powiodło się');
            });
        };

        $scope.deliveryFormTypeChange = function() {
            ($scope.deliveryFormTypes || []).forEach(function(type) {
                if (type.id === $scope.order.deliveryFormType.id)
                    $scope.order.deliveryFormType.deliveryValue = type.deliveryValue;
            });
        };
    }
})();
