(function () {
    'use strict';

    angular
        .module('app.product')
        .controller('AdminAddProductController', ['$scope', 'Product', '$state', 'toastr', 'File', AdminAddProductController]);

    function AdminAddProductController($scope, Product, $state, toastr) {
        var init = function () {
            $scope.productData = {};
            $scope.productImages = [];
            for (var i = 0; i < 4; i++) {
                $scope.productImages.push({
                    id: i,
                    dataUrl: "",
                    fileName: ""
                });
            }
        };
        init();

        $scope.addProduct = function () {
            $scope.submitButtonDisabled = true;
            Product.addProduct($scope.productData, $scope.productImages).then(function() {
                $scope.submitButtonDisabled = false;
                $state.go('^.products-list');
            }, function () {
                $scope.submitButtonDisabled = false;
                toastr.error('Dodanie produktu nie powiodło się.', 'Błędne dane');
            });
        };

        $scope.addImages = function (files) {
            (files || []).forEach(function (file) {
                var reader = new FileReader();
                reader.onload = function () {
                    $scope.productImages.every(function (img) {
                        if (!img.dataUrl) {
                            img.dataUrl = reader.result;
                            img.fileName = reader.fileName;
                            $scope.$apply();
                            return false;
                        }
                        return true;
                    });
                };
                reader.fileName = file.name;
                reader.readAsDataURL(file);
            });
        }
    }
})();
