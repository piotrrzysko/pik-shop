// Declare app level module which depends on views, and components
angular
    .module('PikShopFrontend', [
        'ui.router',
        'app.routes',
        'app.product',
        'app.home',
        'app.cart',
        'app.signIn',
        'app.signUp',
        'app.profile',
        'app.profile.dashboard',
        'ui.bootstrap',
        'angular-carousel',
        'angular-advanced-searchbox',
        'restangular',
        'toastr',
        'ngFileUpload',
        'ProductService',
        'FileService',
        'CartService',
        'filters'
    ])
    .config(['RestangularProvider', function (RestangularProvider) {
        RestangularProvider.setBaseUrl(ENV.apiURL).setDefaultHttpFields({withCredentials: true});
    }]);
