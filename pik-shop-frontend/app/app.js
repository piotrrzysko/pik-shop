// Declare app level module which depends on views, and components
angular
    .module('PikShopFrontend', [
        'ui.router',
        'app.routes',
        'app.product',
        'app.home',
        'restangular',
        'toastr',
        'ngFileUpload',
        'ProductService',
        'FileService'
    ])
    .config(['RestangularProvider', function (RestangularProvider) {
        RestangularProvider.setBaseUrl('http://localhost:8080');
    }]);
