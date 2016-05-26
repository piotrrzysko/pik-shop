(function () {
    'use strict';

    angular
        .module('app.product', ['ngTable'])
        .controller('AdminProductsController', ['$scope', AdminProductsController]);

    function AdminProductsController($scope) {
        $scope.stateIdToName = [{
            id: undefined,
            title: ""
        }, {
            id: "PUBLISHED",
            title: "Opublikowany"
        }, {
            id: "DELETED",
            title: "Usunięty"
        }, {
            id: "UNPUBLISHED",
            title: "Nieopublikowany"
        }, {
            id: "DISABLED",
            title: "Niedostępny"
        }];
    }
})();
