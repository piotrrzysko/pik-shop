(function () {
    'use strict';

    angular
        .module('app.cart', [])
        .controller('CartController', ['$scope', 'Product', '$state', 'toastr', CartController]);

    function CartController($scope, Product, $state) {
        var init = function () {
        };
        init();
    }
})();
