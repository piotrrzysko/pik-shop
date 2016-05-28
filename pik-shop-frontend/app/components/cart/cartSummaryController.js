(function () {
    'use strict';

    angular
        .module('app.cart')
        .controller('CartSummaryController', ['$scope', 'Product', '$state', 'toastr', CartSummaryController]);

    function CartSummaryController($scope, Product, $state) {
        var init = function () {
            $scope.order = {totalValue: 10.00, paymentType: "Przelew bankowy", deliveryValue: 5.00, deliveryType: "Poczta polska"};
            $scope.order.deliveryAddress = {
                firstName: "Piotr",
                lastName: "Rżysko",
                street: "Warszawska",
                houseNumber: "98",
                city: "Mińsk Mazowiecki",
                phoneNumber: "000000000000",
                postalCode: "05-300"
            };
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
