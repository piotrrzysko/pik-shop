// Declare app level module which depends on views, and components
angular
    .module('PikShopFrontend', [
        'ui.router',
        'app.routes',
        'app.product',
        'app.home',
        'app.signIn',
        'app.signUp',
        'ui.bootstrap',
        'angular-carousel',
        'angular-advanced-searchbox',
        'restangular',
        'toastr',
        'ProductService'
    ])
    .config(['RestangularProvider', function (RestangularProvider) {
        RestangularProvider.setBaseUrl(ENV.apiURL);
    }]);
