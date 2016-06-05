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
        'filters',
        'AuthenticationService',
        'CookieStorageService',
        'AppNotificationsService',
        'HttpInterceptor',
        'UserStorageService',
        'ipCookie'
    ])
    .config(['$httpProvider', 'toastrConfig', 'RestangularProvider', function ($httpProvider, toastrConfig, RestangularProvider) {
        $httpProvider.interceptors.push('HttpInterceptor');
        angular.extend(toastrConfig, {
            preventOpenDuplicates: true
        });
        RestangularProvider
            .setBaseUrl(ENV.apiURL)
            .setDefaultHttpFields({withCredentials: true});
    }])
    .run(['CookieStorageService', 'Restangular', function run(CookieStorageService, Restangular) {
        Restangular.setDefaultHeaders(CookieStorageService.getAuthHeaders());
    }]);
